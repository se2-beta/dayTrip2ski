package com.application.data.service;


import com.application.data.restPOJO.Weather;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherService {
    private final WebClient webClient;
    private final String key  = "l6gRrOTmayCWKws3";

    public WeatherService(WebClient.Builder builder){
        webClient = builder.baseUrl("http://my.meteoblue.com/packages/").build();
    }

    public Weather getForecast(String latitude, String longitude){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("basic-day")
                        .queryParam("lat", latitude)
                        .queryParam("lon",longitude)
                        .queryParam("apikey",key)
                        .build())
                .retrieve()
                .bodyToMono(Weather.class).block();



    }
}
