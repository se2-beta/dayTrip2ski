package com.application.views.map;

import com.application.data.entity.SkiResort;
import com.application.data.entity.User;
import com.application.data.service.SkiResortService;
import com.application.data.service.UserService;
import com.application.security.AuthenticatedUser;
import com.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.map.Map;
import com.vaadin.flow.component.map.configuration.Coordinate;
import com.vaadin.flow.component.map.configuration.Feature;
import com.vaadin.flow.component.map.configuration.View;
import com.vaadin.flow.component.map.configuration.feature.MarkerFeature;
import com.vaadin.flow.component.map.configuration.style.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.security.RolesAllowed;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@PageTitle("Map")
@Route(value = "map", layout = MainLayout.class)
@RolesAllowed("USER")
public class MapView extends VerticalLayout {

    private Map map = new Map();

    private SkiResortService skiResortService;
    private AuthenticatedUser authenticatedUser;

    public MapView(AuthenticatedUser authenticatedUser, SkiResortService skiResortService) {
        this.authenticatedUser = authenticatedUser;
        this.skiResortService = skiResortService;

        Optional<User> maybeUser = authenticatedUser.get();
        User user = maybeUser.get();

        setSizeFull();
        setPadding(false);

        configureMap(user);


    }

    private void configureMap(User user) {

        map.getElement().setAttribute("theme", "borderless");
        map.getView().setCenter(Coordinate.fromLonLat(user.getHome_lon(), user.getHome_lat()));
        map.getView().setZoom(10);
        addAndExpand(map);
        createMarker(user.getHome_lon(), user.getHome_lat(), user.getProfilePictureUrl(), true);

        for (SkiResort skiResort : skiResortService.getAllSkiResort()) {
            createMarker(skiResort.getPos_lon(), skiResort.getPos_lat(), null, false);
        }


    }

    private void createMarker(Double lon, Double lat, String iconSrc, boolean userPosition) {

        Coordinate coordinates = Coordinate.fromLonLat(lon, lat);
        MarkerFeature marker;

        if (userPosition) {
            Icon.Options markerOptions = new Icon.Options();
            markerOptions.setSrc(iconSrc);
            markerOptions.setScale(0.5f);
            Icon markerIcon = new Icon(markerOptions);
            marker = new MarkerFeature(coordinates, markerIcon);
        } else {

            marker = new MarkerFeature(coordinates);
        }

        map.getFeatureLayer().addFeature(marker);

    }


}
