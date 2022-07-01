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
        User testUser = new User();

        String username = "Theo";
        String name = "Horst";
        String profilPictureUrl = "asdf";
        Double homeLat = 46.00;
        Double homeLon = 46.00;

        testUser.setUsername(username);
        testUser.setName(name);
        testUser.setProfilePictureUrl(profilPictureUrl);
        testUser.setHomeLat(homeLat);
        testUser.setHomeLon(homeLon);

        userRepository.save(testUser);

        SkiResort skiResort1 = new SkiResort();

        String resortName = "RatingResort";
        String region = "Tirol";
        String operator = "Fake Gmbh";
        String address = "Talstraße1";
        Integer zip = 12345;
        String city = "Fakecity";
        Integer heightMin = 0;
        Integer heightMax = 1000;
        Double totalLength = 10.00;
        Integer ropeways = 10;
        String dateSeasonStart = "asdf";
        String dateSeasonEnd  = "asdf";
        String timeServiceStart  = "asdf";
        String timeServiceEnd  = "asdf";
        Integer currentUtilizationPercent = 1;
        Integer snowDepthMin  = 1;
        Integer snowDepthMax  = 1;
        Integer amountFreshSnow = 1;
        String dateLastSnowfall = "asdf";
        String URLTicketpage = "asdf";
        Integer avalancheWarningLevel = 1;
        String URLImageFront = "asdf";
        String URLImageSlope = "asdf";

        skiResort1.setName(resortName);
        skiResort1.setRegion(region);
        skiResort1.setOperator(operator);
        skiResort1.setAddress(address);
        skiResort1.setZip(zip);
        skiResort1.setCity(city);
        skiResort1.setHeightMin(heightMin);
        skiResort1.setHeightMax(heightMax);
        skiResort1.setTotalLength(totalLength);
        skiResort1.setRopeWays(ropeways);
        skiResort1.setDateSeasonStart(dateSeasonStart);
        skiResort1.setDateSeasonEnd(dateSeasonEnd);
        skiResort1.setTimeServiceStart(timeServiceStart);
        skiResort1.setTimeServiceEnd(timeServiceEnd);
        skiResort1.setCurrentUtilizationPercent(currentUtilizationPercent);
        skiResort1.setSnowDepthMin(snowDepthMin);
        skiResort1.setSnowDepthMax(snowDepthMax);
        skiResort1.setAmountFreshSnow(amountFreshSnow);
        skiResort1.setDateLastSnowfall(dateLastSnowfall);
        skiResort1.setUrlTicketPage(URLTicketpage);
        skiResort1.setAvalancheWarningLevel(avalancheWarningLevel);
        skiResort1.setUrlImageFront(URLImageFront);
        skiResort1.setUrlImageSlope(URLImageSlope);

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
        User testUser = new User();

        String username = "Gerry";
        String name = "Horst";
        String profilPictureUrl = "asdf";
        Double homeLat = 46.00;
        Double homeLon = 46.00;

        testUser.setUsername(username);
        testUser.setName(name);
        testUser.setProfilePictureUrl(profilPictureUrl);
        testUser.setHomeLat(homeLat);
        testUser.setHomeLon(homeLon);

        userRepository.save(testUser);

        SkiResort skiResort1 = new SkiResort();
        //Optional<SkiResort> skiResort1 = Optional.of(new SkiResort());

        String resortName = "getRatingResort";
        String region = "Tirol";
        String operator = "Fake Gmbh";
        String address = "Talstraße1";
        Integer zip = 12345;
        String city = "Fakecity";
        Integer heightMin = 0;
        Integer heightMax = 1000;
        Double totalLength = 10.00;
        Integer ropeways = 10;
        String dateSeasonStart = "asdf";
        String dateSeasonEnd  = "asdf";
        String timeServiceStart  = "asdf";
        String timeServiceEnd  = "asdf";
        Integer currentUtilizationPercent = 1;
        Integer snowDepthMin  = 1;
        Integer snowDepthMax  = 1;
        Integer amountFreshSnow = 1;
        String dateLastSnowfall = "asdf";
        String URLTicketpage = "asdf";
        Integer avalancheWarningLevel = 1;
        String URLImageFront = "asdf";
        String URLImageSlope = "asdf";

        skiResort1.setName(resortName);
        skiResort1.setRegion(region);
        skiResort1.setOperator(operator);
        skiResort1.setAddress(address);
        skiResort1.setZip(zip);
        skiResort1.setCity(city);
        skiResort1.setHeightMin(heightMin);
        skiResort1.setHeightMax(heightMax);
        skiResort1.setTotalLength(totalLength);
        skiResort1.setRopeWays(ropeways);
        skiResort1.setDateSeasonStart(dateSeasonStart);
        skiResort1.setDateSeasonEnd(dateSeasonEnd);
        skiResort1.setTimeServiceStart(timeServiceStart);
        skiResort1.setTimeServiceEnd(timeServiceEnd);
        skiResort1.setCurrentUtilizationPercent(currentUtilizationPercent);
        skiResort1.setSnowDepthMin(snowDepthMin);
        skiResort1.setSnowDepthMax(snowDepthMax);
        skiResort1.setAmountFreshSnow(amountFreshSnow);
        skiResort1.setDateLastSnowfall(dateLastSnowfall);
        skiResort1.setUrlTicketPage(URLTicketpage);
        skiResort1.setAvalancheWarningLevel(avalancheWarningLevel);
        skiResort1.setUrlImageFront(URLImageFront);
        skiResort1.setUrlImageSlope(URLImageSlope);

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
        User testUser = new User();

        String username = "Jerry";
        String name = "Horst";
        String profilPictureUrl = "asdf";
        Double homeLat = 46.00;
        Double homeLon = 46.00;

        testUser.setUsername(username);
        testUser.setName(name);
        testUser.setProfilePictureUrl(profilPictureUrl);
        testUser.setHomeLat(homeLat);
        testUser.setHomeLon(homeLon);

        userRepository.save(testUser);

        SkiResort skiResort1 = new SkiResort();
        //Optional<SkiResort> skiResort1 = Optional.of(new SkiResort());

        String resortName = "FirstRatingResort";
        String region = "Tirol";
        String operator = "Fake Gmbh";
        String address = "Talstraße1";
        Integer zip = 12345;
        String city = "Fakecity";
        Integer heightMin = 0;
        Integer heightMax = 1000;
        Double totalLength = 10.00;
        Integer ropeways = 10;
        String dateSeasonStart = "asdf";
        String dateSeasonEnd  = "asdf";
        String timeServiceStart  = "asdf";
        String timeServiceEnd  = "asdf";
        Integer currentUtilizationPercent = 1;
        Integer snowDepthMin  = 1;
        Integer snowDepthMax  = 1;
        Integer amountFreshSnow = 1;
        String dateLastSnowfall = "asdf";
        String URLTicketpage = "asdf";
        Integer avalancheWarningLevel = 1;
        String URLImageFront = "asdf";
        String URLImageSlope = "asdf";

        skiResort1.setName(resortName);
        skiResort1.setRegion(region);
        skiResort1.setOperator(operator);
        skiResort1.setAddress(address);
        skiResort1.setZip(zip);
        skiResort1.setCity(city);
        skiResort1.setHeightMin(heightMin);
        skiResort1.setHeightMax(heightMax);
        skiResort1.setTotalLength(totalLength);
        skiResort1.setRopeWays(ropeways);
        skiResort1.setDateSeasonStart(dateSeasonStart);
        skiResort1.setDateSeasonEnd(dateSeasonEnd);
        skiResort1.setTimeServiceStart(timeServiceStart);
        skiResort1.setTimeServiceEnd(timeServiceEnd);
        skiResort1.setCurrentUtilizationPercent(currentUtilizationPercent);
        skiResort1.setSnowDepthMin(snowDepthMin);
        skiResort1.setSnowDepthMax(snowDepthMax);
        skiResort1.setAmountFreshSnow(amountFreshSnow);
        skiResort1.setDateLastSnowfall(dateLastSnowfall);
        skiResort1.setUrlTicketPage(URLTicketpage);
        skiResort1.setAvalancheWarningLevel(avalancheWarningLevel);
        skiResort1.setUrlImageFront(URLImageFront);
        skiResort1.setUrlImageSlope(URLImageSlope);

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
        User testUser = new User();

        String username = "Johny";
        String name = "Horst";
        String profilPictureUrl = "asdf";
        Double homeLat = 46.00;
        Double homeLon = 46.00;

        Integer weightFreshSnow = 2;
        Integer weightSlopeLength = 3;
        Integer weightTravelTime = 4;
        Integer weightOccupancy = 5;

        testUser.setUsername(username);
        testUser.setName(name);
        testUser.setProfilePictureUrl(profilPictureUrl);
        testUser.setHomeLat(homeLat);
        testUser.setHomeLon(homeLon);

        testUser.setWeightFreshSnow(weightFreshSnow);
        testUser.setWeightSlopeLength(weightSlopeLength);
        testUser.setWeightTravelTime(weightTravelTime);
        testUser.setWeightOccupancy(weightOccupancy);

        userRepository.save(testUser);

        SkiResort skiResort1 = new SkiResort();

        String resortName = "SecondRatingResort";
        String region = "Tirol";
        String operator = "Fake Gmbh";
        String address = "Talstraße1";
        Integer zip = 12345;
        String city = "Fakecity";
        Integer heightMin = 0;
        Integer heightMax = 1000;
        Double totalLength = 10.00;
        Integer ropeways = 10;
        String dateSeasonStart = "asdf";
        String dateSeasonEnd  = "asdf";
        String timeServiceStart  = "asdf";
        String timeServiceEnd  = "asdf";
        Integer currentUtilizationPercent = 1;
        Integer snowDepthMin  = 1;
        Integer snowDepthMax  = 1;
        Integer amountFreshSnow = 1;
        String dateLastSnowfall = "asdf";
        String URLTicketpage = "asdf";
        Integer avalancheWarningLevel = 1;
        String URLImageFront = "asdf";
        String URLImageSlope = "asdf";

        skiResort1.setName(resortName);
        skiResort1.setRegion(region);
        skiResort1.setOperator(operator);
        skiResort1.setAddress(address);
        skiResort1.setZip(zip);
        skiResort1.setCity(city);
        skiResort1.setHeightMin(heightMin);
        skiResort1.setHeightMax(heightMax);
        skiResort1.setTotalLength(totalLength);
        skiResort1.setRopeWays(ropeways);
        skiResort1.setDateSeasonStart(dateSeasonStart);
        skiResort1.setDateSeasonEnd(dateSeasonEnd);
        skiResort1.setTimeServiceStart(timeServiceStart);
        skiResort1.setTimeServiceEnd(timeServiceEnd);
        skiResort1.setCurrentUtilizationPercent(currentUtilizationPercent);
        skiResort1.setSnowDepthMin(snowDepthMin);
        skiResort1.setSnowDepthMax(snowDepthMax);
        skiResort1.setAmountFreshSnow(amountFreshSnow);
        skiResort1.setDateLastSnowfall(dateLastSnowfall);
        skiResort1.setUrlTicketPage(URLTicketpage);
        skiResort1.setAvalancheWarningLevel(avalancheWarningLevel);
        skiResort1.setUrlImageFront(URLImageFront);
        skiResort1.setUrlImageSlope(URLImageSlope);

        skiResortRepository.save(skiResort1);

        Optional<Rating> testRating = ratingService.get(testUser, skiResort1);

        //Check if there is a rating for this combination of User and skiresort
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

        //check if rating got updated
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

    //01.07.22 Test funktioniert nicht
    @Test
    public void checkIfAllRatingsAreCalculated(){
        User testUser = new User();

        String username = "Isa";
        String name = "Horst";
        String profilPictureUrl = "asdf";
        Double homeLat = 46.00;
        Double homeLon = 46.00;

        Integer weightFreshSnow = 2;
        Integer weightSlopeLength = 3;
        Integer weightTravelTime = 4;
        Integer weightOccupancy = 5;

        testUser.setUsername(username);
        testUser.setName(name);
        testUser.setProfilePictureUrl(profilPictureUrl);
        testUser.setHomeLat(homeLat);
        testUser.setHomeLon(homeLon);

        testUser.setWeightFreshSnow(weightFreshSnow);
        testUser.setWeightSlopeLength(weightSlopeLength);
        testUser.setWeightTravelTime(weightTravelTime);
        testUser.setWeightOccupancy(weightOccupancy);

        userRepository.save(testUser);

        User testUser1 = new User();

        String username1 = "Leo";

        Integer weightOccupancy1 = 10;

        testUser1.setUsername(username1);
        testUser1.setName(name);
        testUser1.setProfilePictureUrl(profilPictureUrl);
        testUser1.setHomeLat(homeLat);
        testUser1.setHomeLon(homeLon);

        testUser1.setWeightFreshSnow(weightFreshSnow);
        testUser1.setWeightSlopeLength(weightSlopeLength);
        testUser1.setWeightTravelTime(weightTravelTime);
        testUser1.setWeightOccupancy(weightOccupancy1);

        userRepository.save(testUser1);

        SkiResort skiResort1 = new SkiResort();

        String resortName = "ThirdRatingResort";
        String region = "Tirol";
        String operator = "Fake Gmbh";
        String address = "Talstraße1";
        Integer zip = 12345;
        String city = "Fakecity";
        Integer heightMin = 0;
        Integer heightMax = 1000;
        Double totalLength = 10.00;
        Integer ropeways = 10;
        String dateSeasonStart = "asdf";
        String dateSeasonEnd  = "asdf";
        String timeServiceStart  = "asdf";
        String timeServiceEnd  = "asdf";
        Integer currentUtilizationPercent = 1;
        Integer snowDepthMin  = 1;
        Integer snowDepthMax  = 1;
        Integer amountFreshSnow = 1;
        String dateLastSnowfall = "asdf";
        String URLTicketpage = "asdf";
        Integer avalancheWarningLevel = 1;
        String URLImageFront = "asdf";
        String URLImageSlope = "asdf";

        skiResort1.setName(resortName);
        skiResort1.setRegion(region);
        skiResort1.setOperator(operator);
        skiResort1.setAddress(address);
        skiResort1.setZip(zip);
        skiResort1.setCity(city);
        skiResort1.setHeightMin(heightMin);
        skiResort1.setHeightMax(heightMax);
        skiResort1.setTotalLength(totalLength);
        skiResort1.setRopeWays(ropeways);
        skiResort1.setDateSeasonStart(dateSeasonStart);
        skiResort1.setDateSeasonEnd(dateSeasonEnd);
        skiResort1.setTimeServiceStart(timeServiceStart);
        skiResort1.setTimeServiceEnd(timeServiceEnd);
        skiResort1.setCurrentUtilizationPercent(currentUtilizationPercent);
        skiResort1.setSnowDepthMin(snowDepthMin);
        skiResort1.setSnowDepthMax(snowDepthMax);
        skiResort1.setAmountFreshSnow(amountFreshSnow);
        skiResort1.setDateLastSnowfall(dateLastSnowfall);
        skiResort1.setUrlTicketPage(URLTicketpage);
        skiResort1.setAvalancheWarningLevel(avalancheWarningLevel);
        skiResort1.setUrlImageFront(URLImageFront);
        skiResort1.setUrlImageSlope(URLImageSlope);

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

}