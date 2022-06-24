package com.application.views.settings;

import com.application.data.Role;
import com.application.data.entity.SkiResort;
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
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

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

    TextField firstName;
    TextField lastName;
    TextField profilePictureUrl;
    TextField username;
    Set<Role> roles;
    TextField rolesTest;
    TextField zip;
    TextField address;
    NumberField lon;
    NumberField lat;

    Button save;
    Button reset;

    public Settings(AuthenticatedUser authenticatedUser, DistanceService distanceService, UserService userService) {
        this.distanceService = distanceService;
        this.userService = userService;

        Optional<User> maybeUser = authenticatedUser.get();
        maybeUser.ifPresent(value -> user = value);

        centerCoordinates = Coordinate.fromLonLat(user.getHomeLon(), user.getHomeLat());

        setSizeFull();
        addClassNames("pb-l");

        configureLayout();

    }

    private void configureLayout() {

        Avatar avatar = new Avatar(user.getName(), user.getProfilePictureUrl());
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
                rolesTest, "Rollen", role, true);


        TextField profilePictureUrl = new TextField("Profilbild-Url");
        profilePictureUrl.setValue(user.getProfilePictureUrl());
        profilePictureUrl.setMaxWidth("80%");
        profilePictureUrl.addClassNames("pl-l");


        zip = new TextField("Postleitzahl");
        zip.setValue("6200");
        zip.setWidth("50%");

        address = new TextField("Adresse");
        address.setHelperText("Format: Teststrasse 69");
        address.setValue("Kienbergstrasse 5");
        address.setWidth("50%");
        HorizontalLayout addressLayout = new HorizontalLayout(zip, address);
        addressLayout.setWidth("90%");
        addressLayout.addClassNames("pb-s");

        Button getAddress = new Button("Adresse aktualisieren");
        getAddress.addClickListener(e -> {
            userLocation = distanceService.getLocation(String.join("+", String.valueOf(zip.getValue()), address.getValue()));
            lon.setValue(userLocation.getLng());
            lat.setValue(userLocation.getLat());

            map.getView().setCenter(Coordinate.fromLonLat(userLocation.getLng(), userLocation.getLat()));
            map.getFeatureLayer().removeFeature(marker);
            createMarker(userLocation.getLng(), userLocation.getLat());


        });

        VerticalLayout addressVLayout = new VerticalLayout(addressLayout, getAddress);


        lon = new NumberField("Längengrad Zuhause");
        lon.setValue(user.getHomeLon());
        lon.setWidth("90%");

        lat = new NumberField("Breitengrad Zuhause");
        lat.setValue(user.getHomeLat());
        lat.setWidth("90%");

        VerticalLayout locationLayout = new VerticalLayout(lon, lat);
        locationLayout.setSpacing(false);


        add(
                avatarTitle,
                profilePictureUrl,
                fullNameLayout, roleLayout,
                addressVLayout, locationLayout,
                configureMap(), buttonLayout()
        );

    }

    private void updateUser() {

        Notification.show("Values: " +
                lon.getValue() + " \n" +
                lat.getValue() + " \n" +
                firstName.getValue() + " " + lastName.getValue() + " \n" +
                username.getValue() + " \n" +
                profilePictureUrl.getValue()
        );


        user.setHomeLon(lon.getValue());
        user.setHomeLat(lat.getValue());
        user.setName(firstName.getValue() + " " + lastName.getValue());
        user.setUsername(username.getValue());
        user.setProfilePictureUrl(profilePictureUrl.getValue());

        userService.update(user);
    }

    private Component buttonLayout() {

        save = new Button("speichern");
        save.addClickListener(e -> {
            Notification.show("Benutzerdaten gespeichert!");
            updateUser();
        });

        save.addClassNames("bg-primary");

        reset = new Button("zurücksetzen");
        reset.addClickListener(e -> {
            Notification.show("Benutzerdaten zurückgesetzt!");
            configureLayout();
        });

        reset.addClassNames("bg-primary-50");

        HorizontalLayout layout = new HorizontalLayout(save, reset);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.AROUND);

        return layout;
    }

    private VerticalLayout verticalLayoutText(TextField top, String titleTop, String valueTop, boolean readOnlyTop,
                                              TextField bottom, String titleBottom, String valueBottom, boolean readOnlyBottom) {

        top = new TextField(titleTop);
        top.setReadOnly(readOnlyTop);
        top.setValue(valueTop);
        top.setWidth("90%");

        bottom = new TextField(titleBottom);
        bottom.setReadOnly(readOnlyBottom);
        bottom.setValue(valueBottom);
        bottom.setWidth("90%");

        VerticalLayout layout = new VerticalLayout(top, bottom);
        layout.setSpacing(false);

        return layout;
    }

    private Map configureMap() {

        map.getElement().setAttribute("theme", "borderless");
        map.getView().setCenter(centerCoordinates);
        map.getView().setZoom(8);
        createMarker(user.getHomeLon(), user.getHomeLat());

        return map;
    }

    private void createMarker(Double lon, Double lat) {

        Coordinate coordinates = Coordinate.fromLonLat(lon, lat);
        marker = new MarkerFeature(coordinates, createMapIcon());
        map.getFeatureLayer().addFeature(marker);

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
