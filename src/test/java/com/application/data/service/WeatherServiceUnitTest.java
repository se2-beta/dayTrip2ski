package com.application.data.service;

import com.application.data.restpojo.Metadata;
import com.application.data.restpojo.Weather;
import org.apiguardian.api.API;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class WeatherServiceUnitTest {

    @Mock
    WeatherService weatherService;

    @Test
    void getWeatherObjectWhenCallingGetForecast() {
        Weather weather = new Weather();
        Metadata metadata = new Metadata();

        double lati = 10.0;
        double longi = 10.0;

        metadata.setLatitude(lati);
        metadata.setLongitude(longi);

        weather.setMetadata(metadata);

        String searchLati = String.valueOf(lati);
        String searchLongi = String.valueOf(longi);

        when(weatherService.getForecast(searchLati, searchLongi)).thenReturn(weather);

        assertEquals(weatherService.getForecast(searchLati, searchLongi), weather);
    }
}