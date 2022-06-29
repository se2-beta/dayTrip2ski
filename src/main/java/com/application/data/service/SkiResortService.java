package com.application.data.service;

import com.application.data.entity.SkiResort;
import com.application.data.entity.User;
import com.application.data.restpojo.DataDay;
import com.application.data.restpojo.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkiResortService {
    private final SkiResortRepository repository;
    private final WeatherService weatherService;
    private final DistanceService distanceService;

    @Autowired
    public SkiResortService(SkiResortRepository repository, WeatherService service, DistanceService distanceService) {
        this.repository = repository;
        this.weatherService = service;
        this.distanceService = distanceService;
    }

    public Optional<SkiResort> get(Integer id) {
        return repository.findById(id);
    }

    public Optional<SkiResort> get(String name) {
        return repository.findSkiResortByName(name);
    }

    public SkiResort update(SkiResort entity) {
        if (get(entity.getName()).isEmpty()) {
            if (entity.getPosLat() == null) {
                String address = String.join("+", String.valueOf(entity.getZip()), entity.getAddress());
                Location l = distanceService.getLocation(address);
                entity.setPosLon(l.getLng());
                entity.setPosLat(l.getLat());
            }
            if (entity.getWeatherDatetimeLastRead() == null) {
                //updateWeather(entity);
                entity.setWeatherCurrentSnowfallForecastAmountMM(0);
                entity.setWeatherCurrentSnowfallForecastPercent(0);
                entity.setWeatherCurrentTemperature(20.0);
                entity.setWeatherCurrentWindspeed(0.0);
                entity.setWeatherDatetimeLastRead(String.valueOf(java.time.LocalDateTime.now()));
            }
        }
        return repository.save(entity);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Page<SkiResort> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<SkiResort> getAllSkiResort() {
        return repository.findAll();
    }

    public List<SkiResort> findAllSkiResort(String filterText, User user) {
        List<SkiResort> resorts;
        if (filterText == null || filterText.isEmpty()) {
            resorts = repository.findAll();
        } else {
            resorts = repository.search(filterText);
        }
        resorts.sort((o1, o2) -> o2.getRatingByUser(user).compareTo(o1.getRatingByUser(user)));
        return resorts;
    }

    public int count() {
        return (int) repository.count();
    }

    public void updateWeather(SkiResort skiResort) {
        String lat, lon;
        lat = String.valueOf(skiResort.getPosLat());
        lon = String.valueOf(skiResort.getPosLon());
        DataDay data = weatherService.getForecastDataDay(lat, lon);
        if (data.getSnowfraction().get(0) > 0) {
            skiResort.setWeatherCurrentSnowfallForecastAmountMM(data.getPrecipitation().get(0).intValue());
            skiResort.setWeatherCurrentSnowfallForecastPercent(data.getPrecipitationProbability().get(0));
        } else {
            skiResort.setWeatherCurrentSnowfallForecastAmountMM(0);
            skiResort.setWeatherCurrentSnowfallForecastPercent(0);
        }

        skiResort.setWeatherCurrentTemperature(data.getTemperatureMean().get(0));
        skiResort.setWeatherCurrentWindspeed(data.getWindspeedMean().get(0));
        skiResort.setWeatherDatetimeLastRead(String.valueOf(java.time.LocalDateTime.now()));
        repository.save(skiResort);
    }

    public void updateAllWeather() {
        List<SkiResort> list = repository.findAll();
        for (SkiResort resort : list) {
            updateWeather(resort);
        }

    }


}
