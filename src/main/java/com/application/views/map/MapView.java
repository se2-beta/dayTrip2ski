package com.application.views.map;

import com.application.data.entity.SkiResort;
import com.application.data.entity.User;
import com.application.data.service.SkiResortService;
import com.application.security.AuthenticatedUser;
import com.application.views.MainLayout;
import com.application.views.imagelist.SkiResortDetailView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.map.Assets;
import com.vaadin.flow.component.map.Map;
import com.vaadin.flow.component.map.configuration.Coordinate;
import com.vaadin.flow.component.map.configuration.Feature;
import com.vaadin.flow.component.map.configuration.feature.MarkerFeature;
import com.vaadin.flow.component.map.configuration.style.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouteParameters;

import javax.annotation.security.RolesAllowed;
import java.util.HashMap;
import java.util.Optional;

@PageTitle("Map")
@Route(value = "map", layout = MainLayout.class)
@RolesAllowed("USER")
public class MapView extends VerticalLayout {
    private final Map map = new Map();
    private final SkiResortService skiResortService;
    private final Coordinate centerCoordinates;
    private User user;
    HashMap<Feature, SkiResort> skiResortLookup = new HashMap<>();


    public MapView(AuthenticatedUser authenticatedUser, SkiResortService skiResortService) {
        this.skiResortService = skiResortService;

        Optional<User> maybeUser = authenticatedUser.get();
        maybeUser.ifPresent(value -> user = value);

        centerCoordinates = Coordinate.fromLonLat(user.getHomeLon(), user.getHomeLat());

        setSizeFull();
        setPadding(false);

        configureMap(user);

    }

    private void configureMap(User user) {

        map.getElement().setAttribute("theme", "borderless");
        map.getView().setCenter(centerCoordinates);
        map.getView().setZoom(8);
        addAndExpand(map);
        createUserMarker(user.getHomeLon(), user.getHomeLat(), user.getProfilePictureUrl());

        for (SkiResort skiResort : skiResortService.getAllSkiResort()) {
            RouteConfiguration.forSessionScope().getUrl(SkiResortDetailView.class, new RouteParameters("id", String.valueOf(skiResort.getId())));
            createSkiResortMarker(skiResort);
        }

        map.addFeatureClickListener(e -> {
            MarkerFeature feature = (MarkerFeature) e.getFeature();
            SkiResort clickedSkiResort = skiResortLookup.get(feature);
            if (clickedSkiResort == null) {
                Notification.show("Benutzerposition geklickt!");
            } else {
                UI.getCurrent().navigate(SkiResortDetailView.class, new RouteParameters("id", String.valueOf(clickedSkiResort.getId())));
            }
        });
    }

    private void createUserMarker(Double lon, Double lat, String iconSrc) {

        Coordinate coordinates = Coordinate.fromLonLat(lon, lat);
        MarkerFeature marker;
        Icon.Options markerOptions = new Icon.Options();
        markerOptions.setSrc(iconSrc);
        markerOptions.setScale(0.5f);
        Icon markerIcon = new Icon(markerOptions);
        marker = new MarkerFeature(coordinates, markerIcon);
        map.getFeatureLayer().addFeature(marker);
    }

    private void createSkiResortMarker(SkiResort skiResort) {

        Coordinate coordinates = Coordinate.fromLonLat(skiResort.getPosLon(), skiResort.getPosLat());
        MarkerFeature marker = new MarkerFeature(coordinates, createMapIcon());

        map.getFeatureLayer().addFeature(marker);
        skiResortLookup.put(marker, skiResort);
    }

    private Icon createMapIcon() {

        Icon.ImageSize pinImageSize = new Icon.ImageSize(Assets.PIN.getWidth(), Assets.PIN.getHeight());
        return new Icon((new Icon.Options())
                .setImg(Assets.PIN.getResource())
                .setImgSize(pinImageSize)
                .setScale(0.5F)
                .setAnchorOrigin(Icon.AnchorOrigin.BOTTOM_LEFT)
                .setAnchor(new Icon.Anchor(0.5F, 0.12F))
        );
    }

}