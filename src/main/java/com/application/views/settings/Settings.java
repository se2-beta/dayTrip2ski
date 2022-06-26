package com.application.views.settings;

import com.application.data.entity.User;
import com.application.data.restpojo.Location;
import com.application.data.service.DistanceService;
import com.application.data.service.UserService;
import com.application.security.AuthenticatedUser;
import com.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarVariant;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.map.Assets;
import com.vaadin.flow.component.map.Map;
import com.vaadin.flow.component.map.configuration.Coordinate;
import com.vaadin.flow.component.map.configuration.feature.MarkerFeature;
import com.vaadin.flow.component.map.configuration.style.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@PageTitle("user-profile")
@Route(value = "user-profile", layout = MainLayout.class)
@RolesAllowed("USER")
public class Settings extends FormLayout {


    User user;
    private Map map = new Map();
    private Coordinate centerCoordinates;
    MarkerFeature marker;
    Location userLocation;
    DistanceService distanceService;
    UserService userService;

    TextField firstName = new TextField("Vorname");
    TextField lastName = new TextField("Nachname");
    TextField profilePictureUrl = new TextField();
    TextField username = new TextField();

    TextField roles = new TextField();
    TextField zip = new TextField();
    TextField address = new TextField();
    NumberField lon;
    NumberField lat;

    Button save;
    Button reset;
    Button getAddress;

    public Settings(AuthenticatedUser authenticatedUser, DistanceService distanceService, UserService userService) {
        this.distanceService = distanceService;
        this.userService = userService;

        Optional<User> maybeUser = authenticatedUser.get();
        maybeUser.ifPresent(value -> user = value);

        centerCoordinates = Coordinate.fromLonLat(user.getHomeLon(), user.getHomeLat());

        setSizeFull();
        addClassNames("pb-l");
        setResponsiveSteps(
                new ResponsiveStep("0", 1),
                new ResponsiveStep("1000", 2)
        );

        configureMap();
        configureLayout();

    }

    private void configureLayout() {

        removeAll();

        Avatar avatar = new Avatar(user.getName());
        avatar.setImage(user.getProfilePictureUrl());
        avatar.addClassNames("ml-m");
        avatar.addThemeVariants(AvatarVariant.LUMO_XLARGE);

        HorizontalLayout avatarTitle = new HorizontalLayout(avatar, new H2("Benutzerprofil " + user.getName()));
        avatarTitle.setAlignItems(FlexComponent.Alignment.BASELINE);
        avatarTitle.setWidthFull();
        setColspan(avatarTitle, 2);


        String[] fullName = user.getName().split(" ");
        VerticalLayout fullNameLayout = verticalLayoutText(firstName, "Vorname", fullName[0], false,
                lastName, "Nachname", fullName[1], false);

        String role = Arrays.toString(user.getRoles().toArray()).replace("[", "").replace("]", "");
        VerticalLayout roleLayout = verticalLayoutText(username, "Benutzername", user.getUsername(), false,
                roles, "Rollen", role, true);


        profilePictureUrl = new TextField("Profilbild-Url");
        profilePictureUrl.setValue(user.getProfilePictureUrl());
        profilePictureUrl.setWidthFull();
        profilePictureUrl.addClassNames("pl-m");

        HorizontalLayout urlLayout = new HorizontalLayout(profilePictureUrl);
        urlLayout.setWidthFull();
        setColspan(urlLayout, 2);


        zip.setPlaceholder("Bsp.: 6020");
        address.setPlaceholder("Bsp.: Teststrasse 69");

        VerticalLayout addressLayout = verticalLayoutText(zip, "Postleitzahl", "", false,
                address, "Adresse", "", false);


        lon = new NumberField("Längengrad Zuhause");
        lon.setValue(user.getHomeLon());
        lon.setWidthFull();
        lon.addClassNames("pt-0");

        lat = new NumberField("Breitengrad Zuhause");
        lat.setWidthFull();
        lat.setValue(user.getHomeLat());
        lat.addClassNames("pb-0");

        VerticalLayout locationLayout = new VerticalLayout(lon, lat);
        locationLayout.setSpacing(false);
        locationLayout.addClassNames("px-0", "px-0", "pt-0");

        VerticalLayout left = new VerticalLayout(fullNameLayout, roleLayout, configureDataButtonLayout());
        VerticalLayout right = new VerticalLayout(addressLayout, locationLayout, configureAddressButtonLayout(), configureMapLayout());

        add(
                avatarTitle,
                urlLayout,
                left, right
        );

    }

    private void updateUser() {

        user.setHomeLon(lon.getValue());
        user.setHomeLat(lat.getValue());
        user.setName(firstName.getValue() + " " + lastName.getValue());
        user.setUsername(username.getValue());
        user.setProfilePictureUrl(profilePictureUrl.getValue());

        userService.update(user);

        Notification.show("Benutzerdaten gespeichert!");

    }

    private Component configureDataButtonLayout() {

        save = new Button("Daten aktualisieren");
        save.addClickListener(e -> updateUser());

        save.addClassNames("bg-primary");

        reset = new Button("Daten zurücksetzen");
        reset.addClickListener(e -> {
            resetForm();
            Notification.show("Benutzerdaten zurückgesetzt!");
        });

        reset.addClassNames("bg-error-50");

        HorizontalLayout layout = new HorizontalLayout(save, reset);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        layout.setWidthFull();

        return layout;
    }

    private Component configureAddressButtonLayout() {
        getAddress = new Button("Adresse aktualisieren");
        getAddress.addClickListener(e -> {
            if (Objects.equals(zip.getValue(), "") || Objects.equals(address.getValue(), "")) {
                Notification.show("Bitte geben sie eine gültige Postleitzahl und Adresse ein!");
            } else {
                userLocation = distanceService.getLocation(String.join("+", String.valueOf(zip.getValue()), address.getValue()));
                lon.setValue(userLocation.getLng());
                lat.setValue(userLocation.getLat());

                map.getView().setCenter(Coordinate.fromLonLat(userLocation.getLng(), userLocation.getLat()));
                map.getFeatureLayer().removeFeature(marker);
                createMarker(userLocation.getLng(), userLocation.getLat());
                zip.setValue("");
                address.setValue("");
            }
        });

        HorizontalLayout layout = new HorizontalLayout(getAddress);
        layout.setWidthFull();
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        return layout;
    }

    private VerticalLayout verticalLayoutText(TextField top, String titleTop, String valueTop, boolean readOnlyTop,
                                              TextField bottom, String titleBottom, String valueBottom, boolean readOnlyBottom) {

        top.setLabel(titleTop);
        top.setReadOnly(readOnlyTop);
        top.setValue(valueTop);
        top.setWidthFull();
        top.addClassNames("pt-0");

        bottom.setLabel(titleBottom);
        bottom.setReadOnly(readOnlyBottom);
        bottom.setValue(valueBottom);
        bottom.setWidthFull();
        bottom.addClassNames("pb-0");

        VerticalLayout layout = new VerticalLayout(top, bottom);
        layout.setSpacing(false);
        layout.addClassNames("px-0", "pt-0");
        layout.setWidthFull();

        return layout;
    }

    private void resetForm() {

        String[] fullName = user.getName().split(" ");

        firstName.setValue(fullName[0]);
        lastName.setValue(fullName[1]);
        profilePictureUrl.setValue(user.getProfilePictureUrl());
        username.setValue(user.getUsername());
        zip.setValue("");
        address.setValue("");
        lon.setValue(user.getHomeLon());
        lat.setValue(user.getHomeLat());
        map.getFeatureLayer().removeFeature(marker);
        createMarker(user.getHomeLon(), user.getHomeLat());
        map.getView().setCenter(Coordinate.fromLonLat(user.getHomeLon(), user.getHomeLat()));

    }

    private Component configureMapLayout() {
        VerticalLayout layout = new VerticalLayout(map);
        layout.setWidthFull();
        layout.addClassNames("px-0");

        return layout;
    }

    private void configureMap() {

        map.getElement().setAttribute("theme", "borderless");
        map.getView().setCenter(centerCoordinates);
        map.getView().setZoom(15);
        createMarker(user.getHomeLon(), user.getHomeLat());

    }

    private void createMarker(Double lon, Double lat) {

        Coordinate coordinates = Coordinate.fromLonLat(lon, lat);
        MarkerFeature newMarker = new MarkerFeature(coordinates, createMapIcon());
        if (marker == null ||
                coordinates.getX() != marker.getCoordinates().getX() &&
                        coordinates.getY() != marker.getCoordinates().getY()
        ) {
            marker = newMarker;
            map.getFeatureLayer().addFeature(marker);
        }
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
