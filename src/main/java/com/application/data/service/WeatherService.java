package com.application.data.service;


import com.application.data.restpojo.DataDay;
import com.application.data.restpojo.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@PropertySource("classpath:application-dev.properties")
public class WeatherService {

    private final WebClient webClient;

    @Value("${weatherKey}")
    private String weatherKey;

    public WeatherService(WebClient.Builder builder) {
        webClient = builder.baseUrl("http://my.meteoblue.com/packages/").build();
    }

    public Weather getForecast(String latitude, String longitude) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("basic-day")
                        .queryParam("lat", latitude)
                        .queryParam("lon", longitude)
                        .queryParam("apikey", weatherKey)
                        .build())
                .retrieve()
                .bodyToMono(Weather.class).block();
    }

    public String getForecastString(String latitude, String longitude) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("basic-day")
                        .queryParam("lat", latitude)
                        .queryParam("lon", longitude)
                        .queryParam("apikey", weatherKey)
                        .build())
                .retrieve()
                .bodyToMono(String.class).block();
    }

    public DataDay getForecastDataDay(String latitude, String longitude) {
        Weather w = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("basic-day")
                        .queryParam("lat", latitude)
                        .queryParam("lon", longitude)
                        .queryParam("apikey", weatherKey)
                        .build())
                .retrieve()
                .bodyToMono(Weather.class).block();
        return w.getDataDay();
    }


}
