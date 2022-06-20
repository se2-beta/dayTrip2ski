package com.application.views.components;

import com.application.data.entity.User;
import com.application.data.service.UserService;
import com.application.security.AuthenticatedUser;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Optional;

public class SkiResortFilterForm extends FormLayout {

    RatingComponent freshSnow = new RatingComponent();
    RatingComponent totalLength = new RatingComponent();
    RatingComponent travelTime = new RatingComponent();
    RatingComponent currentUtilizationPercent = new RatingComponent();
    Button save = new Button("Speichern");
    Button cancel = new Button("Abbrechen");
    Dialog dialog;

    User user;

    UserService service;

    public SkiResortFilterForm(CustomDialog dialog, AuthenticatedUser authenticatedUser, UserService service) {
        this.dialog = dialog;
        this.service = service;

        Optional<User> maybeUser = authenticatedUser.get();
        maybeUser.ifPresent(value -> user = value);


        addClassName("skigebieteFilter");
        addClassNames("px-0");

        setWeightValues();

        Component content = generateContent();

        add(
                content

        );
    }

    private Component generateContent() {


        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setWidth("100%");
        verticalLayout.addClassNames("px-0");
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        verticalLayout.add(
                titleRatingLayout("Neuschnee", freshSnow),
                titleRatingLayout("Pistenlänge", totalLength),
                titleRatingLayout("Anfahrtszeit", travelTime),
                titleRatingLayout("Auslastung", currentUtilizationPercent),
                createButtonLayout()
        );
        return verticalLayout;
    }

    private Component titleRatingLayout(String title, RatingComponent starRating) {

        Label ratingLabel = new Label(title);
        ratingLabel.getStyle().set("font-size", "20opx");

        VerticalLayout layout = new VerticalLayout(ratingLabel, starRating);
        layout.setWidth("100%");
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setSpacing(false);
        layout.addClassNames("py-0", "my-0");

        return layout;
    }

    private void setWeightValues() {

        freshSnow.setValue(user.getWeightFreshSnow());
        totalLength.setValue(user.getWeightSlopeLength());
        travelTime.setValue(user.getWeightTravelTime());
        currentUtilizationPercent.setValue(user.getWeightOccupancy());

    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        cancel.addClickListener(e -> dialog.close());
        save.addClickListener(event -> {
            updateWeightValues();
            dialog.close();
            Notification.show("Präferenzen aktualisiert!");
        });

        HorizontalLayout layout = new HorizontalLayout(save, cancel);
        layout.addClassNames("pt-m", "px-l");
        layout.setWidth("100%");
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        return layout;
    }

    private void updateWeightValues() {
        user.setWeightFreshSnow(freshSnow.getValue());
        user.setWeightSlopeLength(totalLength.getValue());
        user.setWeightTravelTime(travelTime.getValue());
        user.setWeightOccupancy(currentUtilizationPercent.getValue());

        service.update(user);

    }

}
