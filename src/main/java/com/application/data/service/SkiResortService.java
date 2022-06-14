package com.application.data.service;

import com.application.data.entity.SkiResort;
import com.application.data.restpojo.DataDay;
import com.application.data.restpojo.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkiResortService {
    private final SkiResortRepository repository;
    private final WeatherService service;

    @Autowired
    public SkiResortService(SkiResortRepository repository, WeatherService service) {
        this.repository = repository;
        this.service = service;
    }

    public Optional<SkiResort> get(Integer id) {
        return repository.findById(id);
    }

    public Optional<SkiResort> get(String name) {
        return repository.findSkiResortByName(name);
    }

    public SkiResort update(SkiResort entity) {
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

    public List<SkiResort> findAllSkiResort(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            return repository.findAll();
        } else {
            return repository.search(filterText);
        }
    }

    public int count() {
        return (int) repository.count();
    }

    public void updateWeather(SkiResort skiResort){
        String lat,lon;
        lat = String.valueOf(skiResort.getPosLat());
        lon = String.valueOf(skiResort.getPosLon());
        DataDay data = service.getForecastDataDay(lat,lon);
        skiResort.setWeatherCurrentSnowfallForecastAmountMM(data.getPrecipitation().get(0).intValue());
        skiResort.setWeatherCurrentSnowfallForecastPercent(data.getPrecipitationProbability().get(0));
        skiResort.setWeatherCurrentTemperature(data.getTemperatureMean().get(0));
        skiResort.setWeatherCurrentWindspeed(data.getWindspeedMean().get(0));
        repository.save(skiResort);
    }



}
