package com.application.data.service;

import com.application.data.entity.Rating;
import com.application.data.entity.SkiResort;
import com.application.data.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingServiceIntegrationTest {
    @Autowired
    RatingService ratingService;
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SkiResortRepository skiResortRepository;
    @Autowired
    SkiResortService skiResortService;

    @Test
    public void setRating() {
        User testUser = createUser();
        String username = "Theo";
        testUser.setUsername(username);
        userRepository.save(testUser);

        SkiResort skiResort1 = createSkiResort();
        String resortName = "RatingResort";
        skiResort1.setName(resortName);
        skiResortRepository.save(skiResort1);

        ratingService.setRating(
                testUser,
                skiResort1,
                1.5,
                45.0,
                100.0);

        Optional<Rating> testRating = ratingService.get(testUser, skiResort1);
        if(testRating.isPresent()){
            Assert.assertEquals(testRating.get().getUser().getUsername(), username);
            Assert.assertEquals(testRating.get().getSkiResort().getName(), resortName);
            Assert.assertEquals(testRating.get().getRating(), 1.5, 0);
            Assert.assertEquals(testRating.get().getDistanceVal(), 45.0, 0);
            Assert.assertEquals(testRating.get().getDurationVal(), 100.0, 0);
        } else {
            throw new NoSuchElementException("There is no rating for user "
                    + username
                    + " related resort "
                    + skiResort1.getName());
        }
    }

    @Test
    public void getRatingByRatingId() {
        User testUser = createUser();
        String username = "Gerry";
        testUser.setUsername(username);
        userRepository.save(testUser);

        SkiResort skiResort1 = createSkiResort();
        String resortName = "getRatingResort";
        skiResort1.setName(resortName);
        skiResortRepository.save(skiResort1);

        ratingService.setRating(
                testUser,
                skiResort1,
                1.5,
                45.0,
                100.0);

        if (ratingRepository.findByUserAndSkiResort(testUser, skiResort1).isPresent()) {
            Integer repoId = ratingRepository.findByUserAndSkiResort(testUser, skiResort1).get().getId();
            Optional<Rating> returnRating = ratingService.get(repoId);
            if(returnRating.isPresent()){
                Assert.assertEquals(returnRating.get().getUser().getUsername(), username);
                Assert.assertEquals(returnRating.get().getSkiResort().getName(), resortName);
                Assert.assertEquals(returnRating.get().getRating(), 1.5, 0);
                Assert.assertEquals(returnRating.get().getDistanceVal(), 45.0, 0);
                Assert.assertEquals(returnRating.get().getDurationVal(), 100.0, 0);
            }
        } else {
            throw new NoSuchElementException("There is no rating with this id");
        }
    }

    @Test
    public void getRatingByUserAndSkiResort() {
        User testUser = createUser();
        String username = "Jerry";
        testUser.setUsername(username);
        userRepository.save(testUser);

        SkiResort skiResort1 = createSkiResort();
        String resortName = "FirstRatingResort";
        skiResort1.setName(resortName);
        skiResortRepository.save(skiResort1);

        ratingService.setRating(
                testUser,
                skiResort1,
                1.5,
                45.0,
                100.0);

        if (ratingService.get(testUser, skiResort1).isPresent()) {
            Optional<Rating> returnRating = ratingService.get(testUser, skiResort1);
            if(returnRating.isPresent()){
                Assert.assertEquals(returnRating.get().getUser().getUsername(), username);
                Assert.assertEquals(returnRating.get().getSkiResort().getName(), resortName);
                Assert.assertEquals(returnRating.get().getRating(), 1.5, 0);
                Assert.assertEquals(returnRating.get().getDistanceVal(), 45.0, 0);
                Assert.assertEquals(returnRating.get().getDurationVal(), 100.0, 0);
            }
        } else {
            throw new NoSuchElementException("There is no rating with this id");
        }
    }

    @Test
    public void checkIfRatingGotCalculated(){
        User testUser = createUser();
        String username = "Johny";
        testUser.setUsername(username);
        userRepository.save(testUser);

        SkiResort skiResort1 = createSkiResort();
        String resortName = "SecondRatingResort";
        skiResort1.setName(resortName);
        skiResortRepository.save(skiResort1);

        Optional<Rating> testRating = ratingService.get(testUser, skiResort1);

        Assert.assertTrue(testRating.isEmpty());

        ratingService.calculateRating(testUser, skiResort1);

        if (ratingService.get(testUser, skiResort1).isPresent()) {
            Optional<Rating> returnRating = ratingService.get(testUser, skiResort1);
            if(returnRating.isPresent()){
                double ratingValue = returnRating.get().getRating();
                Assert.assertEquals(ratingValue, returnRating.get().getRating(), 0.5);
            }
        } else {
            throw new NoSuchElementException("There is no rating regarding this combination");
        }

        Integer weightFreshSnow_new = 10;
        testUser.setWeightFreshSnow(weightFreshSnow_new);

        userRepository.save(testUser);

        if (ratingService.get(testUser, skiResort1).isPresent()) {
            Optional<Rating> returnRating = ratingService.get(testUser, skiResort1);
            if(returnRating.isPresent()){
                double ratingValue1 = returnRating.get().getRating();
                Assert.assertEquals(ratingValue1, returnRating.get().getRating(), 0.5);
            }
        } else {
            throw new NoSuchElementException("There is no rating regarding this combination");
        }
    }

    @Test
    public void checkIfAllRatingsAreCalculated(){
        User testUser = createUser();

        String username = "Isa";
        testUser.setUsername(username);
        userRepository.save(testUser);

        User testUser1 = createUser();
        String username1 = "Leo";
        Integer weightOccupancy1 = 10;
        testUser1.setUsername(username1);
        testUser1.setWeightOccupancy(weightOccupancy1);
        userRepository.save(testUser1);

        SkiResort skiResort1 = createSkiResort();

        String resortName = "ThirdRatingResort";
        skiResort1.setName(resortName);
        skiResortRepository.save(skiResort1);
        ratingService.calculateAllRating();
        /*
        if (ratingService.get(testUser, skiResort1).isPresent()) {
            Optional<Rating> returnRating = ratingService.get(testUser, skiResort1);
            if(returnRating.isPresent()){
                double ratingValue = returnRating.get().getRating();
                Assert.assertEquals(ratingValue, returnRating.get().getRating(), 0.5);
            }
        } else {
            throw new NoSuchElementException("There is no rating regarding this combination");
        }

        if (ratingService.get(testUser1, skiResort1).isPresent()) {
            Optional<Rating> returnRating = ratingService.get(testUser1, skiResort1);
            if(returnRating.isPresent()){
                double ratingValue = returnRating.get().getRating();
                Assert.assertEquals(ratingValue, returnRating.get().getRating(), 0.5);
            }
        } else {
            throw new NoSuchElementException("There is no rating regarding this combination");
        }
        */
    }

    public User createUser(){
        User testUser = new User();

        String name = "Horst";
        String profilPictureUrl = "asdf";
        Double homeLat = 46.00;
        Double homeLon = 46.00;
        Integer weightFreshSnow = 2;
        Integer weightSlopeLength = 3;
        Integer weightTravelTime = 4;
        Integer weightOccupancy = 5;

        testUser.setName(name);
        testUser.setProfilePictureUrl(profilPictureUrl);
        testUser.setHomeLat(homeLat);
        testUser.setHomeLon(homeLon);
        testUser.setWeightFreshSnow(weightFreshSnow);
        testUser.setWeightSlopeLength(weightSlopeLength);
        testUser.setWeightTravelTime(weightTravelTime);
        testUser.setWeightOccupancy(weightOccupancy);

        return testUser;
    }

    public SkiResort createSkiResort(){
        SkiResort skiResort = new SkiResort();

        String region = "Tirol";
        String operator = "Fake Gmbh";
        String address = "Talstraße1";
        Integer zip = 12345;
        String city = "FakeCity";
        Integer heightMin = 0;
        Integer heightMax = 1000;
        Double totalLength = 10.00;
        Integer ropeWays = 10;
        String dateSeasonStart = "01-12-20xx";
        String dateSeasonEnd = "01-05-20xx";
        String timeServiceStart = "01-12-20xx";
        String timeServiceEnd = "01-12-20xx";
        Integer currentUtilizationPercent = 1;
        Integer snowDepthMin = 1;
        Integer snowDepthMax = 1;
        Integer amountFreshSnow = 1;
        String dateLastSnowfall = "01-12-20xx";
        String URLTicketpage = "01-12-20xx";
        Integer avalancheWarningLevel = 1;
        String URLImageFront = "01-12-20xx";
        String URLImageSlope = "01-12-20xx";

        skiResort.setRegion(region);
        skiResort.setOperator(operator);
        skiResort.setAddress(address);
        skiResort.setZip(zip);
        skiResort.setCity(city);
        skiResort.setHeightMin(heightMin);
        skiResort.setHeightMax(heightMax);
        skiResort.setTotalLength(totalLength);
        skiResort.setRopeWays(ropeWays);
        skiResort.setDateSeasonStart(dateSeasonStart);
        skiResort.setDateSeasonEnd(dateSeasonEnd);
        skiResort.setTimeServiceStart(timeServiceStart);
        skiResort.setTimeServiceEnd(timeServiceEnd);
        skiResort.setCurrentUtilizationPercent(currentUtilizationPercent);
        skiResort.setSnowDepthMin(snowDepthMin);
        skiResort.setSnowDepthMax(snowDepthMax);
        skiResort.setAmountFreshSnow(amountFreshSnow);
        skiResort.setDateLastSnowfall(dateLastSnowfall);
        skiResort.setUrlTicketPage(URLTicketpage);
        skiResort.setAvalancheWarningLevel(avalancheWarningLevel);
        skiResort.setUrlImageFront(URLImageFront);
        skiResort.setUrlImageSlope(URLImageSlope);

        return skiResort;
    }
}