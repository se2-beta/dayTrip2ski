package com.application.data.service;

import com.application.data.restpojo.Distance;
import com.application.data.restpojo.Element;
import com.application.data.restpojo.GoogleDistance;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DistanceServiceUnitTest {

    @Autowired
    DistanceService service;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SkiResortRepository skiResortRepository;

    @Mock
    private RestTemplate restTemplate;
    private WebClient webClient;

    WebClient.Builder builder = mock(WebClient.Builder.class);

    //@InjectMocks
    //private DistanceService distanceService = new DistanceService(builder);

    @Test
    public void getDistance() {
        DistanceService distanceService = mock(DistanceService.class);

        GoogleDistance googleDistance = new GoogleDistance();

        String address1 = "Testaddress1";

        ArrayList<String> distanceList= new ArrayList<>();

        distanceList.add(address1);

        googleDistance.setOriginAddresses(distanceList);

        Mockito.when(restTemplate.
                getForEntity("https://maps.googleapis.com/maps/api/", GoogleDistance.class)).
                thenReturn(new ResponseEntity(googleDistance, HttpStatus.OK));

        String dlatitude = "Test";
        String dlongitude = "Test";
        String olatitude = "Test";
        String olongitude = "Test";

        GoogleDistance googleDistance1 = distanceService.getDistance(
                dlatitude,
                dlongitude,
                olatitude,
                olongitude
        );

        //Assert.assertEquals(googleDistance1.getOriginAddresses().get(0), address1);
    }
}


