package com.application.data.generator;

import com.application.data.Role;
import com.application.data.entity.SkiResort;
import com.application.data.entity.User;
import com.application.data.service.SampleBookRepository;
import com.application.data.service.SkiResortRepository;
import com.application.data.service.UserRepository;
import com.vaadin.flow.spring.annotation.SpringComponent;

import java.util.Collections;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(PasswordEncoder passwordEncoder, SampleBookRepository sampleBookRepository,
                                      UserRepository userRepository, SkiResortRepository skiResortRepository) {
        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (sampleBookRepository.count() != 0L) {
                logger.info("Using existing database");
                return;
            }
            int seed = 123;

            logger.info("Generating demo data");

            logger.info("... generating 2 User entities...");
            User user = new User();
            user.setName("John Normal");
            user.setUsername("user");
            user.setHashedPassword(passwordEncoder.encode("user"));
            user.setProfilePictureUrl(
                    "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80");
            user.setRoles(Collections.singleton(Role.USER));
            user.setHomeLat(47.259659); // Innsbruck
            user.setHomeLon(11.400375);
            userRepository.save(user);

            User admin = new User();
            admin.setName("Emma Powerful");
            admin.setUsername("admin");
            admin.setHashedPassword(passwordEncoder.encode("admin"));
            admin.setProfilePictureUrl(
                    "https://images.unsplash.com/photo-1607746882042-944635dfe10e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80");
            admin.setRoles(Set.of(Role.USER, Role.ADMIN));
            admin.setHomeLat(47.076668); // Graz
            admin.setHomeLon(15.421371);
            userRepository.save(admin);

            logger.info("Generating ski resorts");

            SkiResort skiResort1 = new SkiResort();
            skiResort1.setName("Schladminger Planai & Hochwurzen");
            skiResort1.setRegion("Steiermark");
            skiResort1.setOperator("Planai-Hochwurzen-Bahnen Gesellschaft m.b.H.");
            skiResort1.setAddress("Coburgstrasse 52");
            skiResort1.setZip(8970);
            skiResort1.setCity("Schladming");
            skiResort1.setHeightMin(268);
            skiResort1.setHeightMax(1906);
            skiResort1.setTotalLength(122.7);
            skiResort1.setRopeways(14);
            skiResort1.setPosLon(13.6785045);
            skiResort1.setPosLat(47.3901116);
            skiResort1.setDateSeasonStart("01.10.2022");
            skiResort1.setDateSeasonEnd("31.03.2023");
            skiResort1.setTimeServiceStart("09:00");
            skiResort1.setTimeServiceEnd("16.00");
            skiResort1.setCurrentUtilizationPercent(65);
            skiResort1.setUserRating(90);
            skiResort1.setWeatherCurrentWindspeed(23.50);
            skiResort1.setWeatherCurrentTemperature(25.80);
            skiResort1.setWeatherCurrentSnowfallForecastPercent(70);
            skiResort1.setWeatherCurrentSnowfallForecastAmountMM(20);
            skiResort1.setWeatherDatetimeLastRead("05.06.2022 16:05:14");
            skiResort1.setSnowDepthMin(10);
            skiResort1.setSnowDepthMax(35);
            skiResort1.setAmountFreshSnow(40);
            skiResort1.setDateLastSnowfall("04.06.2022");
            skiResort1.setURLTicketpage("https://www.planai.at/de/tickets-preise/preise-winter");
            skiResort1.setAvalancheWarningLevel(2);
            skiResort1.setURLImageFront("https://urlaubsgeschichten.at/wp-content/uploads/2019/03/schladming-planai-20.jpg");
            skiResort1.setURLImageSlope("https://hikeandbike.de/wp-content/uploads/2014/07/Pistenplan-Planai.jpg");
            skiResortRepository.save(skiResort1);
            logger.info("Resort added: " + skiResort1.getName());

            SkiResort skiResort2 = new SkiResort();
            skiResort2.setName("Flachau - Snow Space");
            skiResort2.setRegion("Salzburg");
            skiResort2.setOperator("Snow Space Salzburg Bergbahnen AG");
            skiResort2.setAddress("Hauptstraße 159");
            skiResort2.setZip(5542);
            skiResort2.setCity("Flachau");
            skiResort2.setHeightMin(950);
            skiResort2.setHeightMax(1990);
            skiResort2.setTotalLength(120.0);
            skiResort2.setRopeways(45);
            skiResort2.setPosLon(13.3894209);
            skiResort2.setPosLat(47.3432997);
            skiResort2.setDateSeasonStart("10.12.2022");
            skiResort2.setDateSeasonEnd("16.04.2023");
            skiResort2.setTimeServiceStart("09:00");
            skiResort2.setTimeServiceEnd("16.00");
            skiResort2.setCurrentUtilizationPercent(35);
            skiResort2.setUserRating(80);
            skiResort2.setWeatherCurrentWindspeed(11.50);
            skiResort2.setWeatherCurrentTemperature(-4.5);
            skiResort2.setWeatherCurrentSnowfallForecastPercent(0);
            skiResort2.setWeatherCurrentSnowfallForecastAmountMM(0);
            skiResort2.setWeatherDatetimeLastRead("05.06.2022 16:05:14");
            skiResort2.setSnowDepthMin(45);
            skiResort2.setSnowDepthMax(98);
            skiResort2.setAmountFreshSnow(20);
            skiResort2.setDateLastSnowfall("04.06.2022");
            skiResort2.setURLTicketpage("https://snow-space.skiperformance.com/de/store#/de/buy");
            skiResort2.setAvalancheWarningLevel(1);
            skiResort2.setURLImageFront("https://www.ramses.at/wp-content/uploads/2017/11/ramses_werbeagentur_snowspace_flachau_hoerfunk_02.jpg");
            skiResort2.setURLImageSlope("https://www.snow-space.com/Bilderpool/Winter/interaktive-Karten/Panorama_SSPS_Saison_2021_22_Legende.jpg");
            skiResortRepository.save(skiResort2);
            logger.info("Resort added: " + skiResort2.getName());

            SkiResort skiResort3 = new SkiResort();
            skiResort3.setName("Saalbach Hinterglemm Leogang Fieberbrunn Skigebiet");
            skiResort3.setRegion("Salzburg, Zell am See - Kaprun");
            skiResort3.setOperator("Skicircus Saalbach Hinterglemm Leogang Fieberbrunn");
            skiResort3.setAddress("Eberhartweg 308");
            skiResort3.setZip(5753);
            skiResort3.setCity("Saalbach");
            skiResort3.setHeightMin(840);
            skiResort3.setHeightMax(2096);
            skiResort3.setTotalLength(270.0);
            skiResort3.setRopeways(70);
            skiResort3.setPosLon(12.641559);
            skiResort3.setPosLat(47.391941);
            skiResort3.setDateSeasonStart("08.10.2022");
            skiResort3.setDateSeasonEnd("19.07.2023");
            skiResort3.setTimeServiceStart("09:00");
            skiResort3.setTimeServiceEnd("16.00");
            skiResort3.setCurrentUtilizationPercent(88);
            skiResort3.setUserRating(95);
            skiResort3.setWeatherCurrentWindspeed(25.36);
            skiResort3.setWeatherCurrentTemperature(-10.1);
            skiResort3.setWeatherCurrentSnowfallForecastPercent(100);
            skiResort3.setWeatherCurrentSnowfallForecastAmountMM(150);
            skiResort3.setWeatherDatetimeLastRead("07.06.2022 10:08:14");
            skiResort3.setSnowDepthMin(114);
            skiResort3.setSnowDepthMax(286);
            skiResort3.setAmountFreshSnow(40);
            skiResort3.setDateLastSnowfall("06.06.2022");
            skiResort3.setURLTicketpage("https://www.saalbach.com/de/winter/skitickets/ticketubersicht");
            skiResort3.setAvalancheWarningLevel(1);
            skiResort3.setURLImageFront("https://www.saalbach.com/static/img/saalbach-logo.svg");
            skiResort3.setURLImageSlope("https://www.saalbach.com/de/download__6027784");
            skiResortRepository.save(skiResort3);
            logger.info("Resort added: " + skiResort3.getName());

            SkiResort skiResort4 = new SkiResort();
            skiResort4.setName("Patscherkofel");
            skiResort4.setRegion("Tirol");
            skiResort4.setOperator("Patscherkofelbahnen GmbH");
            skiResort4.setAddress("Römerstraße 81");
            skiResort4.setZip(6080);
            skiResort4.setCity("Igls");
            skiResort4.setHeightMin(870);
            skiResort4.setHeightMax(1376);
            skiResort4.setTotalLength(20.0);
            skiResort4.setRopeways(7);
            skiResort4.setPosLon(11.413878652485634);
            skiResort4.setPosLat(47.229466906215436);
            skiResort4.setDateSeasonStart("18.12.2022");
            skiResort4.setDateSeasonEnd("03.04.2023");
            skiResort4.setTimeServiceStart("08:30");
            skiResort4.setTimeServiceEnd("16.00");
            skiResort4.setCurrentUtilizationPercent(63);
            skiResort4.setUserRating(91);
            skiResort4.setWeatherCurrentWindspeed(10.46);
            skiResort4.setWeatherCurrentTemperature(-3.1);
            skiResort4.setWeatherCurrentSnowfallForecastPercent(20);
            skiResort4.setWeatherCurrentSnowfallForecastAmountMM(24);
            skiResort4.setWeatherDatetimeLastRead("09.06.2022 11:48:12");
            skiResort4.setSnowDepthMin(93);
            skiResort4.setSnowDepthMax(211);
            skiResort4.setAmountFreshSnow(20);
            skiResort4.setDateLastSnowfall("06.06.2022");
            skiResort4.setURLTicketpage("https://www.patscherkofelbahn.at/de/winter#preise");
            skiResort4.setAvalancheWarningLevel(4);
            skiResort4.setURLImageFront("https://www.tyrol.tl/images/cms/main/754x435/B_1568280284.jpg");
            skiResort4.setURLImageSlope("https://www.patscherkofelbahn.at/de/get-image/984/1/0/Plugin%252FApp%252FWidget%252FPanoramaWinter%252Fassets%252Fimg%252Fmap%252Fmap_lg.png");
            skiResortRepository.save(skiResort4);
            logger.info("Resort added: " + skiResort4.getName());

            SkiResort skiResort5 = new SkiResort();
            skiResort5.setName("Axamer Lizum");
            skiResort5.setRegion("Tirol");
            skiResort5.setOperator("Axamer Lizum GmbH & Co KG");
            skiResort5.setAddress("Axamer Lizum 6");
            skiResort5.setZip(6094);
            skiResort5.setCity("Axams");
            skiResort5.setHeightMin(874);
            skiResort5.setHeightMax(2340);
            skiResort5.setTotalLength(40.0);
            skiResort5.setRopeways(10);
            skiResort5.setPosLon(11.302754852198207);
            skiResort5.setPosLat(47.19589141504654);
            skiResort5.setDateSeasonStart("03.12.2022");
            skiResort5.setDateSeasonEnd("18.04.2023");
            skiResort5.setTimeServiceStart("09:00");
            skiResort5.setTimeServiceEnd("16.00");
            skiResort5.setCurrentUtilizationPercent(36);
            skiResort5.setUserRating(92);
            skiResort5.setWeatherCurrentWindspeed(18.46);
            skiResort5.setWeatherCurrentTemperature(-5.9);
            skiResort5.setWeatherCurrentSnowfallForecastPercent(80);
            skiResort5.setWeatherCurrentSnowfallForecastAmountMM(130);
            skiResort5.setWeatherDatetimeLastRead("07.06.2022 10:08:14");
            skiResort5.setSnowDepthMin(213);
            skiResort5.setSnowDepthMax(378);
            skiResort5.setAmountFreshSnow(56);
            skiResort5.setDateLastSnowfall("010.06.2022");
            skiResort5.setURLTicketpage("https://www.axamer-lizum.at/de/");
            skiResort5.setAvalancheWarningLevel(3);
            skiResort5.setURLImageFront("https://www.axamer-lizum.at/media/animation/skier-neil-williman-photo-by-tove-kockum-location-axamer-lizum-bearb-neil-williman-zur-freien-verwendung-mit-c-fotogfraf-rider-siehe-jeweiliges-bild.jpg");
            skiResort5.setURLImageSlope("https://vcdn.bergfex.at/images/resized/93/5cf2fa2aab2aba93_6a1bfd6e98988e25@2x.jpg");
            skiResortRepository.save(skiResort5);
            logger.info("Resort added: " + skiResort5.getName());

            logger.info("Done with generating ski resorts");
        };
    }
}
