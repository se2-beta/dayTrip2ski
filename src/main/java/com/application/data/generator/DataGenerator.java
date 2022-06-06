package com.application.data.generator;

import com.application.data.Role;
import com.application.data.entity.SkiResort;
import com.application.data.entity.User;
import com.application.data.service.SampleBookRepository;
import com.application.data.service.SkiResortRepository;
import com.application.data.service.UserRepository;
import com.vaadin.flow.spring.annotation.SpringComponent;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import org.hibernate.cache.spi.Region;
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
            user.setHome_lat(47.259659); // Innsbruck
            user.setHome_lon(11.400375);
            userRepository.save(user);
            User admin = new User();
            admin.setName("Emma Powerful");
            admin.setUsername("admin");
            admin.setHashedPassword(passwordEncoder.encode("admin"));
            admin.setProfilePictureUrl(
                    "https://images.unsplash.com/photo-1607746882042-944635dfe10e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80");
            admin.setRoles(Set.of(Role.USER, Role.ADMIN));
            admin.setHome_lat(47.076668); // Graz
            admin.setHome_lon(15.421371);
            userRepository.save(admin);

            logger.info("Generating ski resorts");

            SkiResort skiResort1 = new SkiResort();
            skiResort1.setName("Schladminger Planai & Hochwurzen");
            skiResort1.setRegion("Steiermark");
            skiResort1.setOperator("Planai-Hochwurzen-Bahnen Gesellschaft m.b.H.");
            skiResort1.setAddress("Coburgstrasse 52");
            skiResort1.setZip(8970);
            skiResort1.setCity("Schladming");
            skiResort1.setHeight_min(268);
            skiResort1.setHeight_max(1906);
            skiResort1.setTotal_length(122.7);
            skiResort1.setRopeways(14);
            skiResort1.setPos_lon(13.6785045);
            skiResort1.setPos_lat(47.3901116);
            skiResort1.setDate_season_start("01.10.2022");
            skiResort1.setDate_season_end("31.03.2023");
            skiResort1.setTime_service_start("09:00");
            skiResort1.setTime_service_end("16.00");
            skiResort1.setCurrent_utilization_percent(65);
            skiResort1.setUser_rating(90);
            skiResort1.setWeather_current_windspeed(23.50);
            skiResort1.setWeather_current_temperature(25.80);
            skiResort1.setWeather_current_snowfall_forecast_percent(70);
            skiResort1.setWeather_current_snowfall_forecast_amount_mm(20);
            skiResort1.setWeather_datetime_lastread("05.06.2022 16:05:14");
            skiResort1.setSnow_depth_min(10);
            skiResort1.setSnow_depth_max(35);
            skiResort1.setAmount_fresh_snow(40);
            skiResort1.setDate_last_snowfall("04.06.2022");
            skiResort1.setUrl_ticketpage("https://www.planai.at/de/tickets-preise/preise-winter");
            skiResort1.setAvalanche_warning_level(2);
            skiResort1.setImage_front_url("https://urlaubsgeschichten.at/wp-content/uploads/2019/03/schladming-planai-20.jpg");
            skiResort1.setImage_slope_url("https://hikeandbike.de/wp-content/uploads/2014/07/Pistenplan-Planai.jpg");
            skiResortRepository.save(skiResort1);
            logger.info("Resort added: " + skiResort1.getName());

            SkiResort skiResort2 = new SkiResort();
            skiResort2.setName("Flachau - Snow Space");
            skiResort2.setRegion("Salzburg");
            skiResort2.setOperator("Snow Space Salzburg Bergbahnen AG");
            skiResort2.setAddress("Hauptstraße 159");
            skiResort2.setZip(5542);
            skiResort2.setCity("Flachau");
            skiResort2.setHeight_min(950);
            skiResort2.setHeight_max(1990);
            skiResort2.setTotal_length(120.0);
            skiResort2.setRopeways(45);
            skiResort2.setPos_lon(13.3894209);
            skiResort2.setPos_lat(47.3432997);
            skiResort2.setDate_season_start("10.12.2022");
            skiResort2.setDate_season_end("16.04.2023");
            skiResort2.setTime_service_start("09:00");
            skiResort2.setTime_service_end("16.00");
            skiResort2.setCurrent_utilization_percent(35);
            skiResort2.setUser_rating(80);
            skiResort2.setWeather_current_windspeed(11.50);
            skiResort2.setWeather_current_temperature(-4.5);
            skiResort2.setWeather_current_snowfall_forecast_percent(0);
            skiResort2.setWeather_current_snowfall_forecast_amount_mm(0);
            skiResort2.setWeather_datetime_lastread("05.06.2022 16:05:14");
            skiResort2.setSnow_depth_min(45);
            skiResort2.setSnow_depth_max(98);
            skiResort2.setAmount_fresh_snow(20);
            skiResort2.setDate_last_snowfall("04.06.2022");
            skiResort2.setUrl_ticketpage("https://snow-space.skiperformance.com/de/store#/de/buy");
            skiResort2.setAvalanche_warning_level(1);
            skiResort2.setImage_front_url("https://www.ramses.at/wp-content/uploads/2017/11/ramses_werbeagentur_snowspace_flachau_hoerfunk_02.jpg");
            skiResort2.setImage_slope_url("https://www.snow-space.com/Bilderpool/Winter/interaktive-Karten/Panorama_SSPS_Saison_2021_22_Legende.jpg");
            skiResortRepository.save(skiResort2);
            logger.info("Resort added: " + skiResort2.getName());

            logger.info("Done with generating ski resorts");
        };
    }
}
