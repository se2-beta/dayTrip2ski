package com.application.data.service;

import com.application.data.entity.SkiResort;
import com.application.data.entity.User;
import com.application.data.restpojo.Element;
import com.application.data.restpojo.GoogleDistance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistanceServiceTest {

    @Autowired
    DistanceService service;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SkiResortRepository skiResortRepository;
    Logger logger;

    public DistanceServiceTest() {
        this.logger = LoggerFactory.getLogger(getClass());
    }

   /* @Test
    public void getDistDur() {
        this.logger.info("start ...");

        Element element = service.getDistDur("47.2692","11.4041", "47.2804","11.5058");
        this.logger.info(element.getDistance().getText());

    }*/
}

