package com.application.data.service;

import com.application.data.entity.Rating;
import com.application.data.entity.SkiResort;
import com.application.data.entity.User;
import com.github.dockerjava.api.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger;

    public RatingServiceIntegrationTest() {
        this.logger = LoggerFactory.getLogger(getClass());
    }

    @Test
    public void CreateAndGet() {
        this.logger.info("start ...");

        User testUser = new User();
        testUser.setName("TestName");
        testUser.setUsername("usertest");
        testUser.setProfilePictureUrl("http://...");
        testUser.setHomeLat(1.0);
        testUser.setHomeLon(2.0);
        userRepository.save(testUser);

        User testUser2 = new User();
        testUser2.setName("TestName2");
        testUser2.setUsername("usertest2");
        testUser2.setProfilePictureUrl("http://...");
        testUser2.setHomeLat(1.0);
        testUser2.setHomeLon(2.0);
        userRepository.save(testUser2);

        SkiResort testsSkiResort = new SkiResort();
        testsSkiResort.setName("MyTest");
        testsSkiResort.setRegion("Steiermark");
        testsSkiResort.setOperator("Planai-Hochwurzen-Bahnen Gesellschaft m.b.H.");
        testsSkiResort.setAddress("Coburgstrasse 52");
        testsSkiResort.setZip(8970);
        testsSkiResort.setCity("Schladming");
        testsSkiResort.setHeightMin(268);
        testsSkiResort.setHeightMax(1906);
        testsSkiResort.setTotalLength(122.7);
        testsSkiResort.setRopeways(14);
        testsSkiResort.setPosLon(13.6785045);
        testsSkiResort.setPosLat(47.3901116);
        testsSkiResort.setDateSeasonStart("01.10.2022");
        testsSkiResort.setDateSeasonEnd("31.03.2023");
        testsSkiResort.setTimeServiceStart("09:00");
        testsSkiResort.setTimeServiceEnd("16.00");
        testsSkiResort.setCurrentUtilizationPercent(65);
        testsSkiResort.setUserRating(90);
        testsSkiResort.setWeatherCurrentWindspeed(23.50);
        testsSkiResort.setWeatherCurrentTemperature(25.80);
        testsSkiResort.setWeatherCurrentSnowfallForecastPercent(70);
        testsSkiResort.setWeatherCurrentSnowfallForecastAmountMM(20);
        testsSkiResort.setWeatherDatetimeLastRead("05.06.2022 16:05:14");
        testsSkiResort.setSnowDepthMin(10);
        testsSkiResort.setSnowDepthMax(35);
        testsSkiResort.setAmountFreshSnow(40);
        testsSkiResort.setDateLastSnowfall("04.06.2022");
        testsSkiResort.setURLTicketpage("https://www.planai.at/de/tickets-preise/preise-winter");
        testsSkiResort.setAvalancheWarningLevel(2);
        testsSkiResort.setURLImageFront("https://urlaubsgeschichten.at/wp-content/uploads/2019/03/schladming-planai-20.jpg");
        testsSkiResort.setURLImageSlope("https://hikeandbike.de/wp-content/uploads/2014/07/Pistenplan-Planai.jpg");
        skiResortRepository.save(testsSkiResort);

        ratingService.setRating(testUser, testsSkiResort, 1.5, "45", 45.0, "100", 100.0);
        ratingService.setRating(testUser2, testsSkiResort, 5.0, "45", 45.0, "100", 100.0);

        Optional<Rating> testRating = ratingService.get(testUser, testsSkiResort);
        if (testRating != null) {
            if (testRating.isPresent()) {
                Assert.assertEquals(testRating.get().getUser().getName(), "TestName");
                Assert.assertEquals(testRating.get().getSkiResort().getName(), "MyTest");
                Assert.assertEquals(testRating.get().getRating(), 1.5, 0);
                Assert.assertEquals(testRating.get().getDistanceStr(), "45");
                Assert.assertEquals(testRating.get().getDistanceVal(), 45.0, 0);
                Assert.assertEquals(testRating.get().getDurationStr(), "100");
                Assert.assertEquals(testRating.get().getDurationVal(), 100.0, 0);
            } else {
                Assert.assertTrue(testRating.isPresent());
            }
        } else {
            Assert.assertNotEquals(testRating, null);
        }

        // Demonstration of reading multiple ratings for a single ski-resort
        Optional<SkiResort> testResort = skiResortService.get("MyTest");
        if (testResort.isPresent()) {
            for (int i = 0; i < testResort.get().getRatings().size(); i++) {
                this.logger.info("" + testResort.get().getRatings().get(i).getRating());
            }
        }

        this.logger.info("... end");
    }

    /*
    @Test
    public void callRest() {
        this.logger.info("start ...");

        User testUser = new User();
        testUser.setName("TestName");
        testUser.setUsername("usertest");
        testUser.setHomeLat(47.2692);
        testUser.setHomeLon(11.4041);
        userRepository.save(testUser);

        SkiResort testsSkiResort = new SkiResort();
        testsSkiResort.setName("MyTest");
        testsSkiResort.setPosLat(47.2804);
        testsSkiResort.setPosLon(11.5058);
        skiResortRepository.save(testsSkiResort);

        ratingService.setDistDur(testUser, testsSkiResort);

        Optional<Rating> testRating = ratingService.get(testUser, testsSkiResort);


        this.logger.info("is not null");
        if (testRating.isPresent()) {
            this.logger.info("is present");
            this.logger.info(testRating.get().getDistanceStr());
            this.logger.info(testRating.get().getDurationStr());
        }


        this.logger.info("... end");
    }
    */
    /*
    @Test
    public void callRest() {
        this.logger.info("start ...");

        User testUser = new User();
        testUser.setName("TestName");
        testUser.setUsername("usertest");
        userRepository.save(testUser);

        SkiResort testsSkiResort = new SkiResort();
        testsSkiResort.setName("MyTest");
        skiResortRepository.save(testsSkiResort);

        Rating testrating = new Rating(testUser, testsSkiResort);
        repository.save(testrating);
        this.logger.info(String.valueOf(testrating.getDurationVal()));
        this.logger.info(String.valueOf(testrating.getId()));
        this.logger.info(String.valueOf(testUser.getId()));

    }
    */

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
        //Optional<SkiResort> skiResort1 = Optional.of(new SkiResort());

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
        skiResort1.setRopeways(ropeways);
        skiResort1.setDateSeasonStart(dateSeasonStart);
        skiResort1.setDateSeasonEnd(dateSeasonEnd);
        skiResort1.setTimeServiceStart(timeServiceStart);
        skiResort1.setTimeServiceEnd(timeServiceEnd);
        skiResort1.setCurrentUtilizationPercent(currentUtilizationPercent);
        skiResort1.setSnowDepthMin(snowDepthMin);
        skiResort1.setSnowDepthMax(snowDepthMax);
        skiResort1.setAmountFreshSnow(amountFreshSnow);
        skiResort1.setDateLastSnowfall(dateLastSnowfall);
        skiResort1.setURLTicketpage(URLTicketpage);
        skiResort1.setAvalancheWarningLevel(avalancheWarningLevel);
        skiResort1.setURLImageFront(URLImageFront);
        skiResort1.setURLImageSlope(URLImageSlope);

        skiResortRepository.save(skiResort1);

        ratingService.setRating(
                testUser,
                skiResort1,
                1.5,
                "45",
                45.0,
                "100",
                100.0);

        Optional<Rating> testRating = ratingService.get(testUser, skiResort1);
        if(testRating.isPresent()){
            Assert.assertEquals(testRating.get().getUser().getUsername(), username);
            Assert.assertEquals(testRating.get().getSkiResort().getName(), resortName);
            Assert.assertEquals(testRating.get().getRating(), 1.5, 0);
            Assert.assertEquals(testRating.get().getDistanceStr(), "45");
            Assert.assertEquals(testRating.get().getDistanceVal(), 45.0, 0);
            Assert.assertEquals(testRating.get().getDurationStr(), "100");
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
        skiResort1.setRopeways(ropeways);
        skiResort1.setDateSeasonStart(dateSeasonStart);
        skiResort1.setDateSeasonEnd(dateSeasonEnd);
        skiResort1.setTimeServiceStart(timeServiceStart);
        skiResort1.setTimeServiceEnd(timeServiceEnd);
        skiResort1.setCurrentUtilizationPercent(currentUtilizationPercent);
        skiResort1.setSnowDepthMin(snowDepthMin);
        skiResort1.setSnowDepthMax(snowDepthMax);
        skiResort1.setAmountFreshSnow(amountFreshSnow);
        skiResort1.setDateLastSnowfall(dateLastSnowfall);
        skiResort1.setURLTicketpage(URLTicketpage);
        skiResort1.setAvalancheWarningLevel(avalancheWarningLevel);
        skiResort1.setURLImageFront(URLImageFront);
        skiResort1.setURLImageSlope(URLImageSlope);

        skiResortRepository.save(skiResort1);

        ratingService.setRating(
                testUser,
                skiResort1,
                1.5,
                "45",
                45.0,
                "100",
                100.0);

        if (ratingRepository.findByUserAndSkiResort(testUser, skiResort1).isPresent()) {
            Integer repoId = ratingRepository.findByUserAndSkiResort(testUser, skiResort1).get().getId();
            Optional<Rating> returnRating = ratingService.get(repoId);
            Assert.assertEquals(returnRating.get().getUser().getUsername(), username);
            Assert.assertEquals(returnRating.get().getSkiResort().getName(), resortName);
            Assert.assertEquals(returnRating.get().getRating(), 1.5, 0);
            Assert.assertEquals(returnRating.get().getDistanceStr(), "45");
            Assert.assertEquals(returnRating.get().getDistanceVal(), 45.0, 0);
            Assert.assertEquals(returnRating.get().getDurationStr(), "100");
            Assert.assertEquals(returnRating.get().getDurationVal(), 100.0, 0);
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
        skiResort1.setRopeways(ropeways);
        skiResort1.setDateSeasonStart(dateSeasonStart);
        skiResort1.setDateSeasonEnd(dateSeasonEnd);
        skiResort1.setTimeServiceStart(timeServiceStart);
        skiResort1.setTimeServiceEnd(timeServiceEnd);
        skiResort1.setCurrentUtilizationPercent(currentUtilizationPercent);
        skiResort1.setSnowDepthMin(snowDepthMin);
        skiResort1.setSnowDepthMax(snowDepthMax);
        skiResort1.setAmountFreshSnow(amountFreshSnow);
        skiResort1.setDateLastSnowfall(dateLastSnowfall);
        skiResort1.setURLTicketpage(URLTicketpage);
        skiResort1.setAvalancheWarningLevel(avalancheWarningLevel);
        skiResort1.setURLImageFront(URLImageFront);
        skiResort1.setURLImageSlope(URLImageSlope);

        skiResortRepository.save(skiResort1);

        ratingService.setRating(
                testUser,
                skiResort1,
                1.5,
                "45",
                45.0,
                "100",
                100.0);

        if (ratingService.get(testUser, skiResort1).isPresent()) {
            Optional<Rating> returnRating = ratingService.get(testUser, skiResort1);
            Assert.assertEquals(returnRating.get().getUser().getUsername(), username);
            Assert.assertEquals(returnRating.get().getSkiResort().getName(), resortName);
            Assert.assertEquals(returnRating.get().getRating(), 1.5, 0);
            Assert.assertEquals(returnRating.get().getDistanceStr(), "45");
            Assert.assertEquals(returnRating.get().getDistanceVal(), 45.0, 0);
            Assert.assertEquals(returnRating.get().getDurationStr(), "100");
            Assert.assertEquals(returnRating.get().getDurationVal(), 100.0, 0);
        } else {
            throw new NoSuchElementException("There is no rating with this id");
        }
    }

    @Test
    public void checkIfRatingGotSavedOrUpdatedIntoDB(){
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
        //Optional<SkiResort> skiResort1 = Optional.of(new SkiResort());

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
        skiResort1.setRopeways(ropeways);
        skiResort1.setDateSeasonStart(dateSeasonStart);
        skiResort1.setDateSeasonEnd(dateSeasonEnd);
        skiResort1.setTimeServiceStart(timeServiceStart);
        skiResort1.setTimeServiceEnd(timeServiceEnd);
        skiResort1.setCurrentUtilizationPercent(currentUtilizationPercent);
        skiResort1.setSnowDepthMin(snowDepthMin);
        skiResort1.setSnowDepthMax(snowDepthMax);
        skiResort1.setAmountFreshSnow(amountFreshSnow);
        skiResort1.setDateLastSnowfall(dateLastSnowfall);
        skiResort1.setURLTicketpage(URLTicketpage);
        skiResort1.setAvalancheWarningLevel(avalancheWarningLevel);
        skiResort1.setURLImageFront(URLImageFront);
        skiResort1.setURLImageSlope(URLImageSlope);

        skiResortRepository.save(skiResort1);

        Optional<Rating> testRating = ratingService.get(testUser, skiResort1);

        //Check if there is a rating for this combination of User and skiresort
        Assert.assertTrue(testRating.isEmpty());

        double rating = ratingService.calculateRating(testUser, skiResort1);

        if (ratingService.get(testUser, skiResort1).isPresent()) {
            Optional<Rating> returnRating = ratingService.get(testUser, skiResort1);
            Assert.assertEquals(rating, returnRating.get().getRating(), 0.5);
            System.out.println("Rating is: " + returnRating.get().getRating());
        } else {
            throw new NoSuchElementException("There is no rating regarding this combination");
        }

        //check if rating got updated
        Integer weightFreshSnow_new = 10;
        testUser.setWeightFreshSnow(weightFreshSnow_new);

        userRepository.save(testUser);

        double newRating = ratingService.calculateRating(testUser, skiResort1);

        if (ratingService.get(testUser, skiResort1).isPresent()) {
            Optional<Rating> returnRating = ratingService.get(testUser, skiResort1);
            Assert.assertEquals(newRating, returnRating.get().getRating(), 0.5);
            System.out.println("Rating is: " + returnRating.get().getRating());
        } else {
            throw new NoSuchElementException("There is no rating regarding this combination");
        }
    }

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
        skiResort1.setRopeways(ropeways);
        skiResort1.setDateSeasonStart(dateSeasonStart);
        skiResort1.setDateSeasonEnd(dateSeasonEnd);
        skiResort1.setTimeServiceStart(timeServiceStart);
        skiResort1.setTimeServiceEnd(timeServiceEnd);
        skiResort1.setCurrentUtilizationPercent(currentUtilizationPercent);
        skiResort1.setSnowDepthMin(snowDepthMin);
        skiResort1.setSnowDepthMax(snowDepthMax);
        skiResort1.setAmountFreshSnow(amountFreshSnow);
        skiResort1.setDateLastSnowfall(dateLastSnowfall);
        skiResort1.setURLTicketpage(URLTicketpage);
        skiResort1.setAvalancheWarningLevel(avalancheWarningLevel);
        skiResort1.setURLImageFront(URLImageFront);
        skiResort1.setURLImageSlope(URLImageSlope);

        skiResortRepository.save(skiResort1);

        double rating = ratingService.calculateRating(testUser, skiResort1);

        double rating1 = ratingService.calculateRating(testUser1, skiResort1);

        ratingService.calculateAllRating();

        if (ratingService.get(testUser, skiResort1).isPresent()) {
            Optional<Rating> returnRating = ratingService.get(testUser, skiResort1);
            Assert.assertEquals(rating, returnRating.get().getRating(), 0.5);
            System.out.println("Rating is: " + returnRating.get().getRating());
        } else {
            throw new NoSuchElementException("There is no rating regarding this combination");
        }

        if (ratingService.get(testUser1, skiResort1).isPresent()) {
            Optional<Rating> returnRating = ratingService.get(testUser1, skiResort1);
            Assert.assertEquals(rating1, returnRating.get().getRating(), 0.5);
            System.out.println("Rating is: " + returnRating.get().getRating());
        } else {
            throw new NoSuchElementException("There is no rating regarding this combination");
        }

    }

}