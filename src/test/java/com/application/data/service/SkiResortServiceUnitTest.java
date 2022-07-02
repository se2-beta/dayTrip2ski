package com.application.data.service;

import com.application.data.entity.SkiResort;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SkiResortServiceUnitTest {

    @Autowired
    SkiResortService skiResortService;

    @Mock
    SkiResortRepository repository;
    WeatherService weatherService;
    DistanceService distanceService;
    SkiResort skiresort;

    @Test
    public void update(){
        SkiResort skiResort = mock(SkiResort.class);
        Mockito.when(skiResort.getName()).thenReturn(null);
        Mockito.when(skiResort.getPosLat()).thenReturn(null);
        Mockito.when(skiResort.getZip()).thenReturn(12345);
        Mockito.when(skiResort.getAddress()).thenReturn("Test");
        Mockito.when(skiResort.getWeatherDatetimeLastRead()).thenReturn("12-12-2022");

        //SkiResort skiResort1 = skiResortService.update(skiResort);

        //Assert.assertEquals(skiResort1.getAddress(), "12345 + Test");

    }

}
