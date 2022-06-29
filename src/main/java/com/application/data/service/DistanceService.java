package com.application.data.service;

import com.application.data.restpojo.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@Service
@PropertySource("classpath:application-dev.properties")
public class DistanceService {
    private final WebClient webClient;
    @Value("${googleKey}")
    private String googleKey;

    public DistanceService(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://maps.googleapis.com/maps/api/").build();
    }

    public GoogleDistance getDistance(String dlatitude, String dlongitude, String olatitude, String olongitude) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("distancematrix/json")
                        .queryParam("destinations", String.join(",", dlatitude, dlongitude))
                        .queryParam("origins", String.join(",", olatitude, olongitude))
                        .queryParam("key", googleKey)
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
                        .queryParam("key", googleKey)
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
                        .queryParam("key", googleKey)
                        .build())
                .retrieve()
                .bodyToMono(GoogleDistance.class).block();
        if (gd != null && gd.getStatus().equals("OK")) {
            List<Row> rows = gd.getRows();
            return rows.get(0).getElements().get(0);
        }
        return null;
    }

    public String getLatLonString(String address) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("geocode/json")
                        .queryParam("address", address)
                        .queryParam("key", googleKey)
                        .build())
                .retrieve()
                .bodyToMono(String.class).block();
    }

    public Location getLocation(String address) {
        LatLon ll = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("geocode/json")
                        .queryParam("address", address)
                        .queryParam("key", googleKey)
                        .build())
                .retrieve()
                .bodyToMono(LatLon.class).block();
        if (ll != null && ll.getStatus().equals("OK")) {
            return ll.getResults().get(0).getGeometry().getLocation();
        }
        return null;
    }


}
