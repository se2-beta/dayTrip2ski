package com.application.data.service;

import com.application.data.entity.SkiResort;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/*@RunWith(SpringRunner.class)
@SpringBootTest
public class SkiResortServiceTest {
    @Autowired
    SkiResortService service;
    @Autowired
    SkiResortRepository skiResortRepository;

    Logger logger;

    public SkiResortServiceTest() {
        this.logger = LoggerFactory.getLogger(getClass());
    }

    @BeforeAll
    public void setup() {
        // no code yet
    }

    @Test
    public void get() {
        this.logger.info("SkiResortServiceTest - Starting Test whether resort exists");
        Optional<SkiResort> testResort = service.get("Flachau - Snow Space");
        //assertTrue(testResort.isPresent());
        this.logger.info("SkiResortServiceTest - Ending Test whether resort exists");
    }

    @Test
    public void update() throws Exception {
        String original_name = "Flachau - Snow Space";
        String changed_name = "Flachau - Snow Space [tested]";*/

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


/*    @Test
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

/*    @Test
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
        skiResort1.setRopeways(14);
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
        skiResort1.setURLTicketpage("https://www.planai.at/de/tickets-preise/preise-winter");
        skiResort1.setAvalancheWarningLevel(2);
        skiResort1.setURLImageFront("https://urlaubsgeschichten.at/wp-content/uploads/2019/03/schladming-planai-20.jpg");
        skiResort1.setURLImageSlope("https://hikeandbike.de/wp-content/uploads/2014/07/Pistenplan-Planai.jpg");
        service.update(skiResort1);
    }
}*/
