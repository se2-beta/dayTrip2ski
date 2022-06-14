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

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingServiceTest {
    @Autowired
    RatingService ratingService;
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
        userRepository.save(testUser);

        SkiResort testsSkiResort = new SkiResort();
        testsSkiResort.setName("MyTest");
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
    }
    @Test
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
    }
}
