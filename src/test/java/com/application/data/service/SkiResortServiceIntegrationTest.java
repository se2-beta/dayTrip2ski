package com.application.data.service;

import com.application.data.entity.SkiResort;
import com.vaadin.flow.router.NotFoundException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger;

    public SkiResortServiceIntegrationTest() {
        this.logger = LoggerFactory.getLogger(getClass());
    }

    @BeforeAll
    public void setup() {
        // no code yet
    }

    @Test
    public void get() {
        this.logger.info("SkiResortServiceTest - Starting Test whether resort exists");
        Optional<SkiResort> testResort = skiResortService.get("Flachau - Snow Space");
        //assertTrue(testResort.isPresent());
        this.logger.info("SkiResortServiceTest - Ending Test whether resort exists");
    }
    /*
    @Test
    public void update() throws Exception {
        String original_name = "Flachau - Snow Space";
        String changed_name = "Flachau - Snow Space [tested]";

//        this.logger.info("SkiResortServiceTest - Starting Test whether resort exists");
//        Optional<SkiResort> testResort = service.get(original_name);
//        if (testResort.isPresent()) {
//            testResort.get().setName(changed_name);
//            service.update(testResort.get());
//            Optional<SkiResort> testResort2 = service.get(changed_name);
//            Assert.assertEquals(changed_name, testResort2.get().getName());
//        } else {
//            throw new Exception("Expected Ski-Resort could not be found.");
//        }
//        assertTrue(testResort.isPresent());
//        this.logger.info("SkiResortServiceTest - Ending Test whether resort exists" + testResort.get().getName());
    */

    /*
   @Test
    public void weatherUpdateTest() {
        SkiResort testsSkiResort = new SkiResort();
        testsSkiResort.setName("MyTest");
        testsSkiResort.setPosLat(47.2804);
        testsSkiResort.setPosLon(11.5058);
        skiResortRepository.save(testsSkiResort);

        service.updateWeather(testsSkiResort);

        this.logger.info(String.valueOf(testsSkiResort.getWeatherCurrentSnowfallForecastPercent()));
        this.logger.info(String.valueOf(testsSkiResort.getWeatherCurrentTemperature()));
        this.logger.info(String.valueOf(testsSkiResort.getWeatherCurrentWindspeed()));
    }

    }*/

/*  @Test
    public void insertNew() throws Exception{
        SkiResort skiResort1 = new SkiResort();
        skiResort1.setName("Schladminger Planai & Hochwurzen");
        skiResort1.setRegion("Steiermark");
        skiResort1.setOperator("Planai-Hochwurzen-Bahnen Gesellschaft m.b.H.");
        skiResort1.setAddress("Coburgstrasse 52");
        skiResort1.setZip(8970);
        skiResort1.setCity("Schladming");
        skiResort1.setHeightMin(268);
        skiResort1.setHeightMax(1906);
        skiResort1.setTotalLength(122.7);
        skiResort1.setRopeWays();(14);
        skiResort1.setPosLon(13.6785045);
        skiResort1.setPosLat(47.3901116);
        skiResort1.setDateSeasonStart("01.10.2022");
        skiResort1.setDateSeasonEnd("31.03.2023");
        skiResort1.setTimeServiceStart("09:00");
        skiResort1.setTimeServiceEnd("16.00");
        skiResort1.setCurrentUtilizationPercent(65);
        skiResort1.setUserRating(90);
        skiResort1.setWeatherCurrentWindspeed(23.50);
        skiResort1.setWeatherCurrentTemperature(25.80);
        skiResort1.setWeatherCurrentSnowfallForecastPercent(70);
        skiResort1.setWeatherCurrentSnowfallForecastAmountMM(20);
        skiResort1.setWeatherDatetimeLastRead("05.06.2022 16:05:14");
        skiResort1.setSnowDepthMin(10);
        skiResort1.setSnowDepthMax(35);
        skiResort1.setAmountFreshSnow(40);
        skiResort1.setDateLastSnowfall("04.06.2022");
        skiResort1.setUrlTicketPage();("https://www.planai.at/de/tickets-preise/preise-winter");
        skiResort1.setAvalancheWarningLevel(2);
        skiResort1.setUrlImageFront();("https://urlaubsgeschichten.at/wp-content/uploads/2019/03/schladming-planai-20.jpg");
        skiResort1.setUrlImageSlope();("https://hikeandbike.de/wp-content/uploads/2014/07/Pistenplan-Planai.jpg");
        service.update(skiResort1);
    }

        /*
    @Before
    public void setup() {
        Optional<SkiResort> skiResort1 = Optional.of(new SkiResort());
        Optional<SkiResort> skiResort2 = Optional.of(new SkiResort());

        String newName1 = "Skiresort1";
        String newName2 = "Skiresort2";

        skiResort1.get().setName(newName1);
        skiResort2.get().setName(newName2);

        skiResortRepository.save(skiResort1.get());
        skiResortRepository.save(skiResort2.get());
    }
     */

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
        Integer ropeways = 10;
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
        skiResort1.get().setRopeWays(ropeways);
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
        Integer ropeways = 10;
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
        skiResort1.get().setRopeWays(ropeways);
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

    //Tested within this class instead of Interface SkiresortRepository
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
        Integer ropeways = 10;
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
        skiResort1.get().setRopeWays(ropeways);
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

    /*
    //25.06.22 - throws indexoutofbounds exception when calling update method
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
        Integer ropeways = 10;
        String dateSeasonStart = "asdf";
        String dateSeasonEnd  = "asdf";
        String timeServiceStart  = "asdf";
        String timeServiceEnd  = "asdf";
        Integer currentUtilizationPercent = 1;
        Integer snowDepthMin  = 1;
        Integer snowDepthMax  = 1;
        Integer amountFreshSnow = 1;
        String dateLastSnowfall = "asdf";
        String URLTicketpage = "asdf";
        Integer avalancheWarningLevel = 1;
        String URLImageFront = "asdf";
        String URLImageSlope = "asdf";

        skiResort1.setName(name);
        skiResort1.setRegion(region);
        skiResort1.setOperator(operator);
        skiResort1.setAddress(address);
        skiResort1.setZip(zip);
        skiResort1.setCity(city);
        skiResort1.setHeightMin(heightMin);
        skiResort1.setHeightMax(heightMax);
        skiResort1.setTotalLength(totalLength);
        skiResort1.setRopeWays();(ropeways);
        skiResort1.setDateSeasonStart(dateSeasonStart);
        skiResort1.setDateSeasonEnd(dateSeasonEnd);
        skiResort1.setTimeServiceStart(timeServiceStart);
        skiResort1.setTimeServiceEnd(timeServiceEnd);
        skiResort1.setCurrentUtilizationPercent(currentUtilizationPercent);
        skiResort1.setSnowDepthMin(snowDepthMin);
        skiResort1.setSnowDepthMax(snowDepthMax);
        skiResort1.setAmountFreshSnow(amountFreshSnow);
        skiResort1.setDateLastSnowfall(dateLastSnowfall);
        skiResort1.setUrlTicketPage();(URLTicketpage);
        skiResort1.setAvalancheWarningLevel(avalancheWarningLevel);
        skiResort1.setUrlImageFront();(URLImageFront);
        skiResort1.setUrlImageSlope();(URLImageSlope);

        skiResortRepository.save(skiResort1);

        String newName = "SledgingResort_New";

        skiResort1.setName(newName);

        skiResortService.update(skiResort1);

        if(skiResortRepository.findSkiResortByName(newName).isPresent()){
            Assert.assertEquals(skiResortRepository.findSkiResortByName(newName).get().getName(), newName);
        } else {
            throw new NotFoundException(newName + " not found");
        }
    }
    */

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
        Integer ropeways = 10;
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
        skiResort1.get().setRopeWays(ropeways);
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

    /*
    //Not yet implemented, test necessary?
    @Test
    public void list(){
    }
    */

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
        Integer ropeways = 10;
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
        skiResort1.get().setRopeWays(ropeways);
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

        String searchName = "Snowboard";
        /*
        if (skiResortService.findAllSkiResort(searchName).isEmpty()) {
            throw new NotFoundException("No Skiresorts found with filter: " + searchName);
        } else {
            Assert.assertEquals(skiResortService.findAllSkiResort(searchName).size(), 1);
        }
         */
    }

}

