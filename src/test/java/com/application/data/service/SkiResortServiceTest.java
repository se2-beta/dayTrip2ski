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

@RunWith(SpringRunner.class)
@SpringBootTest
public class SkiResortServiceTest {
    @Autowired
    SkiResortService service;

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
    }
}
