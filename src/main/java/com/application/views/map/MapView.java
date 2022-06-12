package com.application.views.map;

import com.application.data.entity.SkiResort;
import com.application.data.entity.User;
import com.application.data.service.SkiResortService;
import com.application.security.AuthenticatedUser;
import com.application.views.MainLayout;
import com.vaadin.flow.component.map.Map;
import com.vaadin.flow.component.map.configuration.Coordinate;
import com.vaadin.flow.component.map.configuration.feature.MarkerFeature;
import com.vaadin.flow.component.map.configuration.style.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;
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
        map.getView().setCenter(Coordinate.fromLonLat(user.getHomeLon(), user.getHomeLat()));
        map.getView().setZoom(10);
        addAndExpand(map);
        createMarker(user.getHomeLon(), user.getHomeLat(), user.getProfilePictureUrl(), true);

        for (SkiResort skiResort : skiResortService.getAllSkiResort()) {
            createMarker(skiResort.getPosLon(), skiResort.getPosLat(), null, false);
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
