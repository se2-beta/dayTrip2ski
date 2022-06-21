package com.application.data.service;

import com.application.data.entity.Rating;
import com.application.data.entity.SkiResort;
import com.application.data.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
/*
@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingServiceTest {
    @Autowired
    RatingService ratingService;
    @Autowired
    RatingRepository repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SkiResortRepository skiResortRepository;
    Logger logger;

    public RatingServiceTest() {
        this.logger = LoggerFactory.getLogger(getClass());
    }


    @Test
    public void CreateAndGet() {
        this.logger.info("start ...");

        User testUser = new User();
        testUser.setName("TestName");
        testUser.setUsername("usertest");
        testUser.setProfilePictureUrl("http://...");
        testUser.setHomeLat(1.0);
        testUser.setHomeLon(2.0);
        userRepository.save(testUser);

        SkiResort testsSkiResort = new SkiResort();
        testsSkiResort.setName("MyTest");
        testsSkiResort.setRegion("Steiermark");
        testsSkiResort.setOperator("Planai-Hochwurzen-Bahnen Gesellschaft m.b.H.");
        testsSkiResort.setAddress("Coburgstrasse 52");
        testsSkiResort.setZip(8970);
        testsSkiResort.setCity("Schladming");
        testsSkiResort.setHeightMin(268);
        testsSkiResort.setHeightMax(1906);
        testsSkiResort.setTotalLength(122.7);
        testsSkiResort.setRopeways(14);
        testsSkiResort.setPosLon(13.6785045);
        testsSkiResort.setPosLat(47.3901116);
        testsSkiResort.setDateSeasonStart("01.10.2022");
        testsSkiResort.setDateSeasonEnd("31.03.2023");
        testsSkiResort.setTimeServiceStart("09:00");
        testsSkiResort.setTimeServiceEnd("16.00");
        testsSkiResort.setCurrentUtilizationPercent(65);
        testsSkiResort.setUserRating(90);
        testsSkiResort.setWeatherCurrentWindspeed(23.50);
        testsSkiResort.setWeatherCurrentTemperature(25.80);
        testsSkiResort.setWeatherCurrentSnowfallForecastPercent(70);
        testsSkiResort.setWeatherCurrentSnowfallForecastAmountMM(20);
        testsSkiResort.setWeatherDatetimeLastRead("05.06.2022 16:05:14");
        testsSkiResort.setSnowDepthMin(10);
        testsSkiResort.setSnowDepthMax(35);
        testsSkiResort.setAmountFreshSnow(40);
        testsSkiResort.setDateLastSnowfall("04.06.2022");
        testsSkiResort.setURLTicketpage("https://www.planai.at/de/tickets-preise/preise-winter");
        testsSkiResort.setAvalancheWarningLevel(2);
        testsSkiResort.setURLImageFront("https://urlaubsgeschichten.at/wp-content/uploads/2019/03/schladming-planai-20.jpg");
        testsSkiResort.setURLImageSlope("https://hikeandbike.de/wp-content/uploads/2014/07/Pistenplan-Planai.jpg");
        skiResortRepository.save(testsSkiResort);

        ratingService.setRating(testUser, testsSkiResort, 1.5, "45", 45.0, "100", 100.0);

        Optional<Rating> testRating = ratingService.get(testUser, testsSkiResort);
        if (testRating != null) {
            if (testRating.isPresent()) {
                Assert.assertEquals(testRating.get().getUser().getName(), "TestName");
                Assert.assertEquals(testRating.get().getSkiResort().getName(), "MyTest");
                Assert.assertEquals(testRating.get().getRating(), 1.5, 0);
                Assert.assertEquals(testRating.get().getDistanceStr(), "45");
                Assert.assertEquals(testRating.get().getDistanceVal(), 45.0, 0);
                Assert.assertEquals(testRating.get().getDurationStr(), "100");
                Assert.assertEquals(testRating.get().getDurationVal(), 100.0, 0);
            } else {
                Assert.assertTrue(testRating.isPresent());
            }
        } else {
            Assert.assertNotEquals(testRating, null);
        }

        this.logger.info("... end");
    }*/
/*    @Test
    public void callRest() {
        this.logger.info("start ...");

        User testUser = new User();
        testUser.setName("TestName");
        testUser.setUsername("usertest");
        testUser.setHomeLat(47.2692);
        testUser.setHomeLon(11.4041);
        userRepository.save(testUser);

        SkiResort testsSkiResort = new SkiResort();
        testsSkiResort.setName("MyTest");
        testsSkiResort.setPosLat(47.2804);
        testsSkiResort.setPosLon(11.5058);
        skiResortRepository.save(testsSkiResort);

        ratingService.setDistDur(testUser,testsSkiResort);

        Optional<Rating> testRating = ratingService.get(testUser, testsSkiResort);


        this.logger.info("is not null");
        if (testRating.isPresent()) {
            this.logger.info("is present");
            this.logger.info(testRating.get().getDistanceStr());
            this.logger.info(testRating.get().getDurationStr());
        }


        this.logger.info("... end");
    }*/
/*    @Test
    public void callRest() {
        this.logger.info("start ...");

        User testUser = new User();
        testUser.setName("TestName");
        testUser.setUsername("usertest");
        userRepository.save(testUser);

        SkiResort testsSkiResort = new SkiResort();
        testsSkiResort.setName("MyTest");
        skiResortRepository.save(testsSkiResort);

        Rating testrating = new Rating(testUser, testsSkiResort);
        repository.save(testrating);
        this.logger.info(String.valueOf(testrating.getDurationVal()));
        this.logger.info(String.valueOf(testrating.getId()));
        this.logger.info(String.valueOf(testUser.getId()));

    }*/

