package com.application.data.service;

import com.application.data.entity.SkiResort;
import com.application.data.entity.User;
import com.application.data.restpojo.Location;
import com.helger.commons.exception.mock.IMockException;
import com.vaadin.flow.router.NotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SkiResortServiceIntegrationTest {
    @Autowired
    SkiResortService skiResortService;
    @Autowired
    SkiResortRepository skiResortRepository;

    @Test
    public void getById() {
        Optional<SkiResort> skiResort1 = Optional.of(new SkiResort());

        String name = "Sledingresort";
        String region = "Tirol";
        String operator = "Fake Gmbh";
        String address = "Talstraße1";
        Integer zip = 12345;
        String city = "Fakecity";
        Integer heightMin = 0;
        Integer heightMax = 1000;
        Double totalLength = 10.00;
        Integer ropeWays = 10;
        String dateSeasonStart = "asdf";
        String dateSeasonEnd = "asdf";
        String timeServiceStart = "asdf";
        String timeServiceEnd = "asdf";
        Integer currentUtilizationPercent = 1;
        Integer snowDepthMin = 1;
        Integer snowDepthMax = 1;
        Integer amountFreshSnow = 1;
        String dateLastSnowfall = "asdf";
        String URLTicketpage = "asdf";
        Integer avalancheWarningLevel = 1;
        String URLImageFront = "asdf";
        String URLImageSlope = "asdf";

        skiResort1.get().setName(name);
        skiResort1.get().setRegion(region);
        skiResort1.get().setOperator(operator);
        skiResort1.get().setAddress(address);
        skiResort1.get().setZip(zip);
        skiResort1.get().setCity(city);
        skiResort1.get().setHeightMin(heightMin);
        skiResort1.get().setHeightMax(heightMax);
        skiResort1.get().setTotalLength(totalLength);
        skiResort1.get().setRopeWays(ropeWays);
        skiResort1.get().setDateSeasonStart(dateSeasonStart);
        skiResort1.get().setDateSeasonEnd(dateSeasonEnd);
        skiResort1.get().setTimeServiceStart(timeServiceStart);
        skiResort1.get().setTimeServiceEnd(timeServiceEnd);
        skiResort1.get().setCurrentUtilizationPercent(currentUtilizationPercent);
        skiResort1.get().setSnowDepthMin(snowDepthMin);
        skiResort1.get().setSnowDepthMax(snowDepthMax);
        skiResort1.get().setAmountFreshSnow(amountFreshSnow);
        skiResort1.get().setDateLastSnowfall(dateLastSnowfall);
        skiResort1.get().setUrlTicketPage(URLTicketpage);
        skiResort1.get().setAvalancheWarningLevel(avalancheWarningLevel);
        skiResort1.get().setUrlImageFront(URLImageFront);
        skiResort1.get().setUrlImageSlope(URLImageSlope);

        skiResortRepository.save(skiResort1.get());

        Integer repoId = skiResortRepository.search(name).get(0).getId();
        System.out.println(repoId);

        if (skiResortService.get(repoId).isPresent()) {
            Assert.assertEquals(skiResortService.get(repoId).get().getName(), name);
        } else {
            throw new NotFoundException("Skiresort with ID" + repoId + " not found");
        }
    }

    @Test
    public void getByName() {
        Optional<SkiResort> skiResort1 = Optional.of(new SkiResort());

        String name = "SkiingResort";
        String region = "Tirol";
        String operator = "Fake Gmbh";
        String address = "Talstraße1";
        Integer zip = 12345;
        String city = "Fakecity";
        Integer heightMin = 0;
        Integer heightMax = 1000;
        Double totalLength = 10.00;
        Integer ropeWays = 10;
        String dateSeasonStart = "asdf";
        String dateSeasonEnd = "asdf";
        String timeServiceStart = "asdf";
        String timeServiceEnd = "asdf";
        Integer currentUtilizationPercent = 1;
        Integer snowDepthMin = 1;
        Integer snowDepthMax = 1;
        Integer amountFreshSnow = 1;
        String dateLastSnowfall = "asdf";
        String URLTicketpage = "asdf";
        Integer avalancheWarningLevel = 1;
        String URLImageFront = "asdf";
        String URLImageSlope = "asdf";

        skiResort1.get().setName(name);
        skiResort1.get().setRegion(region);
        skiResort1.get().setOperator(operator);
        skiResort1.get().setAddress(address);
        skiResort1.get().setZip(zip);
        skiResort1.get().setCity(city);
        skiResort1.get().setHeightMin(heightMin);
        skiResort1.get().setHeightMax(heightMax);
        skiResort1.get().setTotalLength(totalLength);
        skiResort1.get().setRopeWays(ropeWays);
        skiResort1.get().setDateSeasonStart(dateSeasonStart);
        skiResort1.get().setDateSeasonEnd(dateSeasonEnd);
        skiResort1.get().setTimeServiceStart(timeServiceStart);
        skiResort1.get().setTimeServiceEnd(timeServiceEnd);
        skiResort1.get().setCurrentUtilizationPercent(currentUtilizationPercent);
        skiResort1.get().setSnowDepthMin(snowDepthMin);
        skiResort1.get().setSnowDepthMax(snowDepthMax);
        skiResort1.get().setAmountFreshSnow(amountFreshSnow);
        skiResort1.get().setDateLastSnowfall(dateLastSnowfall);
        skiResort1.get().setUrlTicketPage(URLTicketpage);
        skiResort1.get().setAvalancheWarningLevel(avalancheWarningLevel);
        skiResort1.get().setUrlImageFront(URLImageFront);
        skiResort1.get().setUrlImageSlope(URLImageSlope);

        skiResortRepository.save(skiResort1.get());

        if (skiResortService.get(name).isPresent()) {
            Assert.assertEquals(skiResortService.get(name).get().getName(), name);
        } else {
            throw new NotFoundException(name + " not found");
        }
    }

    @Test
    public void findSkiResortByName() {
        Optional<SkiResort> skiResort1 = Optional.of(new SkiResort());

        String name = "BoardingResort";
        String region = "Tirol";
        String operator = "Fake Gmbh";
        String address = "Talstraße1";
        Integer zip = 12345;
        String city = "Fakecity";
        Integer heightMin = 0;
        Integer heightMax = 1000;
        Double totalLength = 10.00;
        Integer ropeWays = 10;
        String dateSeasonStart = "asdf";
        String dateSeasonEnd = "asdf";
        String timeServiceStart = "asdf";
        String timeServiceEnd = "asdf";
        Integer currentUtilizationPercent = 1;
        Integer snowDepthMin = 1;
        Integer snowDepthMax = 1;
        Integer amountFreshSnow = 1;
        String dateLastSnowfall = "asdf";
        String URLTicketpage = "asdf";
        Integer avalancheWarningLevel = 1;
        String URLImageFront = "asdf";
        String URLImageSlope = "asdf";

        skiResort1.get().setName(name);
        skiResort1.get().setRegion(region);
        skiResort1.get().setOperator(operator);
        skiResort1.get().setAddress(address);
        skiResort1.get().setZip(zip);
        skiResort1.get().setCity(city);
        skiResort1.get().setHeightMin(heightMin);
        skiResort1.get().setHeightMax(heightMax);
        skiResort1.get().setTotalLength(totalLength);
        skiResort1.get().setRopeWays(ropeWays);
        skiResort1.get().setDateSeasonStart(dateSeasonStart);
        skiResort1.get().setDateSeasonEnd(dateSeasonEnd);
        skiResort1.get().setTimeServiceStart(timeServiceStart);
        skiResort1.get().setTimeServiceEnd(timeServiceEnd);
        skiResort1.get().setCurrentUtilizationPercent(currentUtilizationPercent);
        skiResort1.get().setSnowDepthMin(snowDepthMin);
        skiResort1.get().setSnowDepthMax(snowDepthMax);
        skiResort1.get().setAmountFreshSnow(amountFreshSnow);
        skiResort1.get().setDateLastSnowfall(dateLastSnowfall);
        skiResort1.get().setUrlTicketPage(URLTicketpage);
        skiResort1.get().setAvalancheWarningLevel(avalancheWarningLevel);
        skiResort1.get().setUrlImageFront(URLImageFront);
        skiResort1.get().setUrlImageSlope(URLImageSlope);

        skiResort1.get().setName(name);

        skiResortRepository.save(skiResort1.get());

        Assert.assertEquals(skiResort1, skiResortService.get(name));
    }

    @Test
    public void update() {
        SkiResort skiResort1 = new SkiResort();

        String name = "SledgingResort";
        String region = "Tirol";
        String operator = "Fake Gmbh";
        String address = "Talstraße1";
        Integer zip = 12345;
        String city = "Fakecity";
        Integer heightMin = 0;
        Integer heightMax = 1000;
        Double totalLength = 10.00;
        Integer ropeWays = 10;
        String dateSeasonStart = "asdf";
        String dateSeasonEnd  = "asdf";
        String timeServiceStart  = "asdf";
        String timeServiceEnd  = "asdf";
        Integer currentUtilizationPercent = 1;
        Integer snowDepthMin  = 1;
        Integer snowDepthMax  = 1;
        Integer amountFreshSnow = 1;
        String dateLastSnowfall = "asdf";
        String urlTicketPage = "asdf";
        Integer avalancheWarningLevel = 1;
        String urlImageFront = "asdf";
        String urlImageSlope = "asdf";

        skiResort1.setName(name);
        skiResort1.setRegion(region);
        skiResort1.setOperator(operator);
        skiResort1.setAddress(address);
        skiResort1.setZip(zip);
        skiResort1.setCity(city);
        skiResort1.setHeightMin(heightMin);
        skiResort1.setHeightMax(heightMax);
        skiResort1.setTotalLength(totalLength);
        skiResort1.setRopeWays(ropeWays);
        skiResort1.setDateSeasonStart(dateSeasonStart);
        skiResort1.setDateSeasonEnd(dateSeasonEnd);
        skiResort1.setTimeServiceStart(timeServiceStart);
        skiResort1.setTimeServiceEnd(timeServiceEnd);
        skiResort1.setCurrentUtilizationPercent(currentUtilizationPercent);
        skiResort1.setSnowDepthMin(snowDepthMin);
        skiResort1.setSnowDepthMax(snowDepthMax);
        skiResort1.setAmountFreshSnow(amountFreshSnow);
        skiResort1.setDateLastSnowfall(dateLastSnowfall);
        skiResort1.setUrlTicketPage(urlTicketPage);
        skiResort1.setAvalancheWarningLevel(avalancheWarningLevel);
        skiResort1.setUrlImageFront(urlImageFront);
        skiResort1.setUrlImageSlope(urlImageSlope);

        skiResortRepository.save(skiResort1);

        String newOperator = "Operator_New";

        skiResort1.setOperator(newOperator);

        skiResortService.update(skiResort1);

        if(skiResortRepository.findSkiResortByName(name).isPresent()){
            Assert.assertEquals(skiResortRepository.findSkiResortByName(name).get().getOperator(), newOperator);
        } else {
            throw new NotFoundException(newOperator + " not found");
        }
    }

    @Test
    public void weatherUpdate() {
        SkiResort skiResort1 = new SkiResort();

        String name = "WeatherResort";
        String region = "Tirol";
        String operator = "Fake Gmbh";
        String address = "Talstraße1";
        Integer zip = 12345;
        String city = "Fakecity";
        Integer heightMin = 0;
        Integer heightMax = 1000;
        Double totalLength = 10.00;
        Integer ropeWays = 10;
        String dateSeasonStart = "asdf";
        String dateSeasonEnd  = "asdf";
        String timeServiceStart  = "asdf";
        String timeServiceEnd  = "asdf";
        Integer currentUtilizationPercent = 1;
        Integer snowDepthMin  = 1;
        Integer snowDepthMax  = 1;
        Integer amountFreshSnow = 1;
        String dateLastSnowfall = "asdf";
        String urlTicketPage = "asdf";
        Integer avalancheWarningLevel = 1;
        String urlImageFront = "asdf";
        String urlImageSlope = "asdf";
        Double posLat = 12.0;

        skiResort1.setName(name);
        skiResort1.setRegion(region);
        skiResort1.setOperator(operator);
        skiResort1.setAddress(address);
        skiResort1.setZip(zip);
        skiResort1.setCity(city);
        skiResort1.setHeightMin(heightMin);
        skiResort1.setHeightMax(heightMax);
        skiResort1.setTotalLength(totalLength);
        skiResort1.setRopeWays(ropeWays);
        skiResort1.setDateSeasonStart(dateSeasonStart);
        skiResort1.setDateSeasonEnd(dateSeasonEnd);
        skiResort1.setTimeServiceStart(timeServiceStart);
        skiResort1.setTimeServiceEnd(timeServiceEnd);
        skiResort1.setCurrentUtilizationPercent(currentUtilizationPercent);
        skiResort1.setSnowDepthMin(snowDepthMin);
        skiResort1.setSnowDepthMax(snowDepthMax);
        skiResort1.setAmountFreshSnow(amountFreshSnow);
        skiResort1.setDateLastSnowfall(dateLastSnowfall);
        skiResort1.setUrlTicketPage(urlTicketPage);
        skiResort1.setAvalancheWarningLevel(avalancheWarningLevel);
        skiResort1.setUrlImageFront(urlImageFront);
        skiResort1.setUrlImageSlope(urlImageSlope);
        skiResort1.setPosLat(posLat);

        skiResortService.update(skiResort1);

        if(skiResortService.get(name).isPresent()){
            Assert.assertEquals(0, skiResortService.get(name).get().getWeatherCurrentSnowfallForecastAmountMM(), 0);
            Assert.assertEquals(0, skiResortService.get(name).get().getWeatherCurrentSnowfallForecastPercent(), 0);
            Assert.assertEquals(20, skiResortService.get(name).get().getWeatherCurrentTemperature(), 0);
            Assert.assertEquals(0, skiResortService.get(name).get().getWeatherCurrentWindSpeed(), 0);
        }
    }

    @Test
    public void deleteById() {
        Optional<SkiResort> skiResort1 = Optional.of(new SkiResort());

        String name = "PartyResort";
        String region = "Tirol";
        String operator = "Fake Gmbh";
        String address = "Talstraße1";
        Integer zip = 12345;
        String city = "Fakecity";
        Integer heightMin = 0;
        Integer heightMax = 1000;
        Double totalLength = 10.00;
        Integer ropeWays = 10;
        String dateSeasonStart = "asdf";
        String dateSeasonEnd = "asdf";
        String timeServiceStart = "asdf";
        String timeServiceEnd = "asdf";
        Integer currentUtilizationPercent = 1;
        Integer snowDepthMin = 1;
        Integer snowDepthMax = 1;
        Integer amountFreshSnow = 1;
        String dateLastSnowfall = "asdf";
        String URLTicketpage = "asdf";
        Integer avalancheWarningLevel = 1;
        String URLImageFront = "asdf";
        String URLImageSlope = "asdf";

        skiResort1.get().setName(name);
        skiResort1.get().setRegion(region);
        skiResort1.get().setOperator(operator);
        skiResort1.get().setAddress(address);
        skiResort1.get().setZip(zip);
        skiResort1.get().setCity(city);
        skiResort1.get().setHeightMin(heightMin);
        skiResort1.get().setHeightMax(heightMax);
        skiResort1.get().setTotalLength(totalLength);
        skiResort1.get().setRopeWays(ropeWays);
        skiResort1.get().setDateSeasonStart(dateSeasonStart);
        skiResort1.get().setDateSeasonEnd(dateSeasonEnd);
        skiResort1.get().setTimeServiceStart(timeServiceStart);
        skiResort1.get().setTimeServiceEnd(timeServiceEnd);
        skiResort1.get().setCurrentUtilizationPercent(currentUtilizationPercent);
        skiResort1.get().setSnowDepthMin(snowDepthMin);
        skiResort1.get().setSnowDepthMax(snowDepthMax);
        skiResort1.get().setAmountFreshSnow(amountFreshSnow);
        skiResort1.get().setDateLastSnowfall(dateLastSnowfall);
        skiResort1.get().setUrlTicketPage(URLTicketpage);
        skiResort1.get().setAvalancheWarningLevel(avalancheWarningLevel);
        skiResort1.get().setUrlImageFront(URLImageFront);
        skiResort1.get().setUrlImageSlope(URLImageSlope);

        skiResortRepository.save(skiResort1.get());

        Integer repoId = skiResortRepository.search(name).get(0).getId();

        //Test if Skiresort is saved, not necessary but done anyways
        if (skiResortRepository.findById(repoId).isPresent()) {
            System.out.println("Name of the Skiresort: " + skiResortRepository.findById(repoId).get().getName());
        } else {
            throw new NotFoundException(skiResortRepository.findById(repoId).get().getName() + " not found");
        }

        skiResortService.delete(repoId);

        if (skiResortRepository.findById(repoId).isEmpty()) {
            System.out.println("Skiresort " + name + " with ID " + repoId + " deleted successfully");
        } else {
            //throw new Exception(skiResortRepository.findById(repoId).get().getName() + " not found");
            throw new NoSuchElementException("Skiresort " + name + " and ID " + repoId + "still in database");
        }
    }

    @Test
    public void findAllSkiResort() {
        Assert.assertEquals(skiResortRepository.count(), skiResortService.getAllSkiResort().size());
    }

    @Test
    public void findAllSkiResortWithTextFilter() {
        Optional<SkiResort> skiResort1 = Optional.of(new SkiResort());

        String name = "SnowboardResort1";
        String region = "Tirol";
        String operator = "Fake Gmbh";
        String address = "Talstraße1";
        Integer zip = 12345;
        String city = "Fakecity";
        Integer heightMin = 0;
        Integer heightMax = 1000;
        Double totalLength = 10.00;
        Integer ropeWays = 10;
        String dateSeasonStart = "asdf";
        String dateSeasonEnd = "asdf";
        String timeServiceStart = "asdf";
        String timeServiceEnd = "asdf";
        Integer currentUtilizationPercent = 1;
        Integer snowDepthMin = 1;
        Integer snowDepthMax = 1;
        Integer amountFreshSnow = 1;
        String dateLastSnowfall = "asdf";
        String URLTicketpage = "asdf";
        Integer avalancheWarningLevel = 1;
        String URLImageFront = "asdf";
        String URLImageSlope = "asdf";

        skiResort1.get().setName(name);
        skiResort1.get().setRegion(region);
        skiResort1.get().setOperator(operator);
        skiResort1.get().setAddress(address);
        skiResort1.get().setZip(zip);
        skiResort1.get().setCity(city);
        skiResort1.get().setHeightMin(heightMin);
        skiResort1.get().setHeightMax(heightMax);
        skiResort1.get().setTotalLength(totalLength);
        skiResort1.get().setRopeWays(ropeWays);
        skiResort1.get().setDateSeasonStart(dateSeasonStart);
        skiResort1.get().setDateSeasonEnd(dateSeasonEnd);
        skiResort1.get().setTimeServiceStart(timeServiceStart);
        skiResort1.get().setTimeServiceEnd(timeServiceEnd);
        skiResort1.get().setCurrentUtilizationPercent(currentUtilizationPercent);
        skiResort1.get().setSnowDepthMin(snowDepthMin);
        skiResort1.get().setSnowDepthMax(snowDepthMax);
        skiResort1.get().setAmountFreshSnow(amountFreshSnow);
        skiResort1.get().setDateLastSnowfall(dateLastSnowfall);
        skiResort1.get().setUrlTicketPage(URLTicketpage);
        skiResort1.get().setAvalancheWarningLevel(avalancheWarningLevel);
        skiResort1.get().setUrlImageFront(URLImageFront);
        skiResort1.get().setUrlImageSlope(URLImageSlope);

        skiResortRepository.save(skiResort1.get());

        Optional<SkiResort> skiResort2 = Optional.of(new SkiResort());

        String name2 = "JumpResort";

        skiResort2.get().setName(name2);
        skiResort2.get().setRegion(region);
        skiResort2.get().setOperator(operator);
        skiResort2.get().setAddress(address);
        skiResort2.get().setZip(zip);
        skiResort2.get().setCity(city);
        skiResort2.get().setHeightMin(heightMin);
        skiResort2.get().setHeightMax(heightMax);
        skiResort2.get().setTotalLength(totalLength);
        skiResort2.get().setRopeWays(ropeWays);
        skiResort2.get().setDateSeasonStart(dateSeasonStart);
        skiResort2.get().setDateSeasonEnd(dateSeasonEnd);
        skiResort2.get().setTimeServiceStart(timeServiceStart);
        skiResort2.get().setTimeServiceEnd(timeServiceEnd);
        skiResort2.get().setCurrentUtilizationPercent(currentUtilizationPercent);
        skiResort2.get().setSnowDepthMin(snowDepthMin);
        skiResort2.get().setSnowDepthMax(snowDepthMax);
        skiResort2.get().setAmountFreshSnow(amountFreshSnow);
        skiResort2.get().setDateLastSnowfall(dateLastSnowfall);
        skiResort2.get().setUrlTicketPage(URLTicketpage);
        skiResort2.get().setAvalancheWarningLevel(avalancheWarningLevel);
        skiResort2.get().setUrlImageFront(URLImageFront);
        skiResort2.get().setUrlImageSlope(URLImageSlope);

        skiResortRepository.save(skiResort2.get());

        User testUser = new User();

        String userName = "Lu";
        String name1 = "Horst";
        String profilePictureUrl = "asdf";
        Double homeLat = 46.00;
        Double homeLon = 46.00;

        testUser.setUsername(userName);
        testUser.setName(name1);
        testUser.setProfilePictureUrl(profilePictureUrl);
        testUser.setHomeLat(homeLat);
        testUser.setHomeLon(homeLon);

        //userRepository.save(testUser);

        String searchName = "";

        if (skiResortService.findAllSkiResort(searchName, testUser).isEmpty()) {
            throw new NotFoundException("No Skiresorts found with empty filter");
        } else {
            Assert.assertEquals(skiResortService.findAllSkiResort(searchName, testUser).size(), skiResortRepository.count());
        }

        searchName = "Jump";

        if (skiResortService.findAllSkiResort(searchName, testUser).isEmpty()) {
            throw new NotFoundException("No Skiresorts found with filter " + searchName);
        } else {
            Assert.assertEquals(skiResortService.findAllSkiResort(searchName, testUser).size(), 1);
        }
    }
}

