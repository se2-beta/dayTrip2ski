package com.application.views.components;

import com.application.data.entity.SkiResort;
import com.application.data.entity.User;
import com.application.data.service.RatingService;
import com.application.security.AuthenticatedUser;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Optional;

public class SkiResortListViewCard extends ListItem {

    RatingService ratingService;
    User user;

    public SkiResortListViewCard(SkiResort skiResort, AuthenticatedUser authenticatedUser, RatingService ratingService) {
        this.ratingService = ratingService;
        Optional<User> maybeUser = authenticatedUser.get();
        maybeUser.ifPresent(value -> user = value);

        addClassNames("bg-contrast-10", "flex", "flex-col", "items-start", "p-s", "rounded-l");

        add(
                createImageHeader(skiResort),
                createContent(skiResort)
        );

    }

    private Component createImageHeader(SkiResort skiResort) {

        Div imageHeader = new Div();
        imageHeader.addClassNames("bg-contrast", "flex items-center", "justify-center", "mb-m", "overflow-hidden",
                "rounded-m w-full");
        imageHeader.setHeight("160px");
        imageHeader.getElement().getStyle()
                .set("background-image", "url(" + skiResort.getURLImageFront() + ")")
                .set("background-size", "100%")
                .set("background-position", "center")
                .set("background-repeat", "no-repeat");
        imageHeader.addClassNames("relative");
        return imageHeader;
    }

    private Component createHeader(SkiResort skiResort) {

        // Wertung
        Label scoreTitle = new Label("Score:");
        scoreTitle.addClassNames("m-0", "pb-s");
        H4 score = new H4((int) ratingService.getFrontend(user, skiResort).getRating() + "%");
        score.addClassNames("m-0", "pb-s");

        VerticalLayout scoreLayout = new VerticalLayout(scoreTitle, score);
        scoreLayout.setWidth("70px");
        scoreLayout.setHeight("70px");
        scoreLayout.setSpacing(false);
        scoreLayout.setPadding(false);
        scoreLayout.setMargin(false);
        scoreLayout.addClassNames("border", "rounded-l", "px-s");
        scoreLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        scoreLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        // Title Settings
        Label header = new Label();
        header.addClassNames("text-l", "font-semibold", "pb-0");
        header.setMaxWidth("100%");
        header.setText(skiResort.getName());

        Span subtitle = new Span();
        subtitle.addClassNames("text-s", "text-secondary", "pb-m");
        subtitle.setText(skiResort.getRegion());

        VerticalLayout headerSubTitle = new VerticalLayout(header, subtitle);
        headerSubTitle.setSpacing(false);
        headerSubTitle.addClassNames("m-0", "p-0", "px-s");

        HorizontalLayout headerLayout = new HorizontalLayout(
                headerSubTitle,
                (int) ratingService.getFrontend(user, skiResort).getRating() == 0 ? new Label() : scoreLayout);
        headerLayout.addClassNames("pb-0");
        headerLayout.setWidth("100%");
        headerLayout.setAlignItems(FlexComponent.Alignment.START);
        headerLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        return headerLayout;
    }

    private Component createInformationContent(SkiResort skiResort) {

        HorizontalLayout userRatingLayout = new HorizontalLayout();
        userRatingLayout.add(IconText(VaadinIcon.STAR, skiResort.getUserRating(), "%", " - ", "Zufriedenheit"));

        userRatingLayout.setWidth("100%");
        userRatingLayout.setHeight("40px");
        userRatingLayout.setSpacing(false);
        userRatingLayout.addClassNames("px-s", "pb-m");

        Component tempDriveLayout = horizontalComponents(IconText(VaadinIcon.CLOUD, skiResort.getWeatherCurrentTemperature(), " °C", "", ""),
                IconText(VaadinIcon.CAR, ratingService.getFrontend(user, skiResort).getDurationStr(), " min", "", ""));
        Component snowLayout = horizontalComponents(IconText(VaadinIcon.ASTERISK, skiResort.getSnowDepthMin(), " - ", skiResort.getSnowDepthMax(), " cm"),
                IconText(VaadinIcon.TRENDING_UP, skiResort.getAmountFreshSnow(), " cm", "", ""));

        VerticalLayout informationContent = new VerticalLayout();
        informationContent.add(userRatingLayout, tempDriveLayout, snowLayout);
        informationContent.addClassNames("m-0", "p-0");
        informationContent.setSpacing(false);

        return informationContent;
    }

    private Component createContent(SkiResort skiResort) {

        VerticalLayout content = new VerticalLayout();
        content.add(createHeader(skiResort));
        content.add(createInformationContent(skiResort));
        content.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        content.setHeight("60%");
        content.setSpacing(false);
        content.addClassNames("p-0");

        return content;
    }


    private <T> Component IconText(VaadinIcon icon, T firstText, String suffixFirst, T secondText, String suffixSecond) {
        Icon ic = new Icon(icon);

        String convertFirst = firstText == null ? "Invalid" : firstText.toString();
        String convertSecond = secondText == null ? "Invalid" : secondText.toString();

        ic.setSize("15px");
        Label l1 = new Label(convertFirst + suffixFirst + convertSecond + suffixSecond);
        l1.getStyle().set("font-size", "15px");
        HorizontalLayout hl = new HorizontalLayout(ic, l1);
        hl.setAlignItems(FlexComponent.Alignment.CENTER);

        return hl;
    }


    private Component horizontalComponents(Component left, Component right) {

        VerticalLayout vl1 = new VerticalLayout(left);
        VerticalLayout vl2 = new VerticalLayout(right);
        vl1.addClassNames("p-0", "m-0");
        vl2.addClassNames("p-0", "m-0");

        HorizontalLayout layout = new HorizontalLayout(vl1, vl2);
        layout.setWidth("100%");
        layout.setHeight("40px");
        layout.setSpacing(false);
        layout.addClassNames("pb-0", "px-s");

        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        return layout;
    }
}
