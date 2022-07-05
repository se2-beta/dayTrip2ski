package com.application.data.service;

import com.application.data.entity.SkiResort;
import com.application.data.entity.User;
import com.vaadin.flow.router.NotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SkiResortServiceIntegrationTest {
    @Autowired
    SkiResortService skiResortService;
    @Autowired
    SkiResortRepository skiResortRepository;

    @Test
    public void getById() {
        Optional<SkiResort> skiResort1 = createOptionalSkiResort();

        String name = "Sledingresort";

        if(skiResort1.isPresent()){
            skiResort1.get().setName(name);
        } else {
            throw new NotFoundException("Failure in creating Skiresort");
        }

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
        Optional<SkiResort> skiResort1 = createOptionalSkiResort();

        String name = "SkiingResort";

        if (skiResort1.isPresent()) {
            skiResort1.get().setName(name);
        } else {
            throw new NotFoundException("Failure in creating Skiresort");
        }

        skiResortRepository.save(skiResort1.get());

        if (skiResortService.get(name).isPresent()) {
            Assert.assertEquals(skiResortService.get(name).get().getName(), name);
        } else {
            throw new NotFoundException(name + " not found");
        }
    }

    @Test
    public void findSkiResortByName() {
        Optional<SkiResort> skiResort1 = createOptionalSkiResort();

        String name = "BoardingResort";

        if (skiResort1.isPresent()) {
            skiResort1.get().setName(name);
        } else {
            throw new NotFoundException("Failure in creating Skiresort");
        }

        skiResortRepository.save(skiResort1.get());

        Assert.assertEquals(skiResort1, skiResortService.get(name));
    }

    @Test
    public void update() {
        SkiResort skiResort1 = createSkiResort();

        String name = "SledgingResort";

        skiResort1.setName(name);

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
        SkiResort skiResort1 = createSkiResort();

        String name = "WeatherResort";

        skiResort1.setName(name);

        skiResort1.setPosLat(12.0);

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
        Optional<SkiResort> skiResort1 = createOptionalSkiResort();

        String name = "PartyResort";

        if (skiResort1.isPresent()) {
            skiResort1.get().setName(name);
        } else {
            throw new NotFoundException("Failure in creating Skiresort");
        }

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
        Optional<SkiResort> skiResort1 = createOptionalSkiResort();

        String name = "SnowboardResort1";

        if (skiResort1.isPresent()) {
            skiResort1.get().setName(name);
        } else {
            throw new NotFoundException("Failure in creating Skiresort");
        }

        skiResortRepository.save(skiResort1.get());

        Optional<SkiResort> skiResort2 = createOptionalSkiResort();

        String name2 = "JumpResort";

        if (skiResort2.isPresent()) {
            skiResort2.get().setName(name2);
        } else {
            throw new NotFoundException("Failure in creating Skiresort");
        }

        skiResortRepository.save(skiResort2.get());

        User testUser = createUser();

        String userName = "Lu";
        testUser.setUsername(userName);

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

    public Optional<SkiResort> createOptionalSkiResort(){
        Optional<SkiResort> skiResort = Optional.of(new SkiResort());

        String region = "Tirol";
        String operator = "Fake Gmbh";
        String address = "Talstraße1";
        Integer zip = 12345;
        String city = "FakeCity";
        Integer heightMin = 0;
        Integer heightMax = 1000;
        Double totalLength = 10.00;
        Integer ropeWays = 10;
        String dateSeasonStart = "01-12-20xx";
        String dateSeasonEnd = "01-05-20xx";
        String timeServiceStart = "01-12-20xx";
        String timeServiceEnd = "01-12-20xx";
        Integer currentUtilizationPercent = 1;
        Integer snowDepthMin = 1;
        Integer snowDepthMax = 1;
        Integer amountFreshSnow = 1;
        String dateLastSnowfall = "01-12-20xx";
        String URLTicketpage = "01-12-20xx";
        Integer avalancheWarningLevel = 1;
        String URLImageFront = "01-12-20xx";
        String URLImageSlope = "01-12-20xx";

        skiResort.get().setRegion(region);
        skiResort.get().setOperator(operator);
        skiResort.get().setAddress(address);
        skiResort.get().setZip(zip);
        skiResort.get().setCity(city);
        skiResort.get().setHeightMin(heightMin);
        skiResort.get().setHeightMax(heightMax);
        skiResort.get().setTotalLength(totalLength);
        skiResort.get().setRopeWays(ropeWays);
        skiResort.get().setDateSeasonStart(dateSeasonStart);
        skiResort.get().setDateSeasonEnd(dateSeasonEnd);
        skiResort.get().setTimeServiceStart(timeServiceStart);
        skiResort.get().setTimeServiceEnd(timeServiceEnd);
        skiResort.get().setCurrentUtilizationPercent(currentUtilizationPercent);
        skiResort.get().setSnowDepthMin(snowDepthMin);
        skiResort.get().setSnowDepthMax(snowDepthMax);
        skiResort.get().setAmountFreshSnow(amountFreshSnow);
        skiResort.get().setDateLastSnowfall(dateLastSnowfall);
        skiResort.get().setUrlTicketPage(URLTicketpage);
        skiResort.get().setAvalancheWarningLevel(avalancheWarningLevel);
        skiResort.get().setUrlImageFront(URLImageFront);
        skiResort.get().setUrlImageSlope(URLImageSlope);

        return skiResort;
    }

    public SkiResort createSkiResort(){
        SkiResort skiResort = new SkiResort();

        String region = "Tirol";
        String operator = "Fake Gmbh";
        String address = "Talstraße1";
        Integer zip = 12345;
        String city = "FakeCity";
        Integer heightMin = 0;
        Integer heightMax = 1000;
        Double totalLength = 10.00;
        Integer ropeWays = 10;
        String dateSeasonStart = "01-12-20xx";
        String dateSeasonEnd = "01-05-20xx";
        String timeServiceStart = "01-12-20xx";
        String timeServiceEnd = "01-12-20xx";
        Integer currentUtilizationPercent = 1;
        Integer snowDepthMin = 1;
        Integer snowDepthMax = 1;
        Integer amountFreshSnow = 1;
        String dateLastSnowfall = "01-12-20xx";
        String URLTicketpage = "01-12-20xx";
        Integer avalancheWarningLevel = 1;
        String URLImageFront = "01-12-20xx";
        String URLImageSlope = "01-12-20xx";

        skiResort.setRegion(region);
        skiResort.setOperator(operator);
        skiResort.setAddress(address);
        skiResort.setZip(zip);
        skiResort.setCity(city);
        skiResort.setHeightMin(heightMin);
        skiResort.setHeightMax(heightMax);
        skiResort.setTotalLength(totalLength);
        skiResort.setRopeWays(ropeWays);
        skiResort.setDateSeasonStart(dateSeasonStart);
        skiResort.setDateSeasonEnd(dateSeasonEnd);
        skiResort.setTimeServiceStart(timeServiceStart);
        skiResort.setTimeServiceEnd(timeServiceEnd);
        skiResort.setCurrentUtilizationPercent(currentUtilizationPercent);
        skiResort.setSnowDepthMin(snowDepthMin);
        skiResort.setSnowDepthMax(snowDepthMax);
        skiResort.setAmountFreshSnow(amountFreshSnow);
        skiResort.setDateLastSnowfall(dateLastSnowfall);
        skiResort.setUrlTicketPage(URLTicketpage);
        skiResort.setAvalancheWarningLevel(avalancheWarningLevel);
        skiResort.setUrlImageFront(URLImageFront);
        skiResort.setUrlImageSlope(URLImageSlope);

        return skiResort;
    }

    public User createUser(){
        User user = new User();

        String name1 = "Horst";
        String profilePictureUrl = "asdf";
        Double homeLat = 46.00;
        Double homeLon = 46.00;

        user.setName(name1);
        user.setProfilePictureUrl(profilePictureUrl);
        user.setHomeLat(homeLat);
        user.setHomeLon(homeLon);

        return user;
    }
}


