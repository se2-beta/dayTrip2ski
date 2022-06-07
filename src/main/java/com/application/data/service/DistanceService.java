package com.application.data.service;

import com.application.data.restPOJO.Distance;
import com.application.data.restPOJO.Weather;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class DistanceService {
    private final WebClient webClient;
    private final String key  = "AIzaSyD5UiKTQlGUo3ukCDvp3Wj71PSK2S_f94M";

    public DistanceService(WebClient.Builder builder){
        webClient = builder.baseUrl("https://maps.googleapis.com/maps/api/").build();
    }

    public Distance getDistance(String dlatitude, String dlongitude, String olatitude, String olongitude){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("distancematrix/json")
                        .queryParam("destinations", String.join("," ,dlatitude,dlongitude))
                        .queryParam("origins",String.join(",",olatitude,olongitude))
                        .queryParam("key",key)
                        .build())
                .retrieve()
                .bodyToMono(Distance.class).block();
    }

    public String getDistanceString(String dlatitude, String dlongitude, String olatitude, String olongitude){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("distancematrix/json")
                        .queryParam("destinations", String.join("," ,dlatitude,dlongitude))
                        .queryParam("origins",String.join(",",olatitude,olongitude))
                        .queryParam("key",key)
                        .build())
                .retrieve()
                .bodyToMono(String.class).block();
    }


}
