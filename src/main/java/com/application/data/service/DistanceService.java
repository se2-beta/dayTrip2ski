package com.application.data.service;

import com.application.data.restpojo.Element;
import com.application.data.restpojo.GoogleDistance;
import com.application.data.restpojo.Row;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@Service
public class DistanceService {
    private final WebClient webClient;
    private final String key = "AIzaSyD5UiKTQlGUo3ukCDvp3Wj71PSK2S_f94M";

    public DistanceService(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://maps.googleapis.com/maps/api/").build();
    }

    public GoogleDistance getDistance(String dlatitude, String dlongitude, String olatitude, String olongitude) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("distancematrix/json")
                        .queryParam("destinations", String.join(",", dlatitude, dlongitude))
                        .queryParam("origins", String.join(",", olatitude, olongitude))
                        .queryParam("key", key)
                        .build())
                .retrieve()
                .bodyToMono(GoogleDistance.class).block();
    }

    public String getDistanceString(String dlatitude, String dlongitude, String olatitude, String olongitude) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("distancematrix/json")
                        .queryParam("destinations", String.join(",", dlatitude, dlongitude))
                        .queryParam("origins", String.join(",", olatitude, olongitude))
                        .queryParam("key", key)
                        .build())
                .retrieve()
                .bodyToMono(String.class).block();
    }

    public Element getDistDur(String dlatitude, String dlongitude, String olatitude, String olongitude) {
        GoogleDistance gd = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("distancematrix/json")
                        .queryParam("destinations", String.join(",", dlatitude, dlongitude))
                        .queryParam("origins", String.join(",", olatitude, olongitude))
                        .queryParam("key", key)
                        .build())
                .retrieve()
                .bodyToMono(GoogleDistance.class).block();
        List<Row> rows = gd.getRows();
        return rows.get(0).getElements().get(0);

    }


}
