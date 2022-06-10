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

    @BeforeAll
    public void setup() {
        // no code yet
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

        ratingService.setRating(testUser, testsSkiResort, 1.5);

        Optional<Rating> testRating = ratingService.get(testUser, testsSkiResort);
        if (testRating != null) {
            if (testRating.isPresent()) {
                Assert.assertEquals(testRating.get().getRating(), 1.5, 0);
            } else {
                Assert.assertTrue(testRating.isPresent());
            }
        } else {
            Assert.assertNotEquals(testRating, null);
        }

        this.logger.info("... end");
    }
}
