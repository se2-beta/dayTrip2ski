package com.application.views.components;

import com.application.data.entity.SkiResort;
import com.application.data.entity.User;
import com.application.data.service.RatingService;
import com.application.security.AuthenticatedUser;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarVariant;
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

        // Picture Settings
        Div imageHeader = new Div();
        imageHeader.addClassNames("bg-contrast", "flex items-center", "justify-center", "mb-m", "overflow-hidden",
                "rounded-m w-full");
        imageHeader.setHeight("160px");

        Image image = new Image();
        image.setWidth("100%");
        image.setSrc(skiResort.getURLImageFront());
        imageHeader.add(image);

        add(
                imageHeader,
                createContent(skiResort)
        );

    }

    private Component createHeader(SkiResort skiResort) {

        Avatar avatar = new Avatar("fr");
        avatar.addThemeVariants(AvatarVariant.LUMO_LARGE);
        avatar.setAbbreviation((int) ratingService.getFrontend(user, skiResort).getRating() + "%");
        avatar.setColorIndex(3);
        avatar.getStyle().set("font-weight", "700");

        // Title Settings
        Label header = new Label();
        header.addClassNames("text-l", "font-semibold", "pb-0");
        header.setMaxWidth("100%");
        header.setText(skiResort.getName());

        HorizontalLayout headerIcon = new HorizontalLayout(header, avatar);
        headerIcon.addClassNames("pb-0");
        headerIcon.setWidth("100%");
        headerIcon.setAlignItems(FlexComponent.Alignment.START);
        headerIcon.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        Span subtitle = new Span();
        subtitle.addClassNames("text-s", "text-secondary", "pb-m");
        subtitle.setText(skiResort.getRegion());

        VerticalLayout headerSubTitle = new VerticalLayout(headerIcon, subtitle);
        headerSubTitle.setSpacing(false);
        headerSubTitle.addClassNames("m-0", "p-0", "px-s");

        return headerSubTitle;
    }

    private Component createInformationContent(SkiResort skiResort) {

        Component tempDriveLayout = horizontalComponents(IconText(VaadinIcon.CLOUD, skiResort.getWeatherCurrentTemperature(), " °C", "", ""),
                IconText(VaadinIcon.CAR, ratingService.getFrontend(user, skiResort).getDurationStr(), " min", "", ""));
        Component snowLayout = horizontalComponents(IconText(VaadinIcon.ASTERISK, skiResort.getSnowDepthMin(), " - ", skiResort.getSnowDepthMax(), " cm"),
                IconText(VaadinIcon.TRENDING_UP, skiResort.getAmountFreshSnow(), " cm", "", ""));

        VerticalLayout informationContent = new VerticalLayout();
        informationContent.add(tempDriveLayout);
        informationContent.add(snowLayout);
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


    private <T> Component IconText(VaadinIcon icon, T first_text, String suffix_first, T second_text, String suffix_second) {
        Icon ic = new Icon(icon);
        String convert_first = first_text.toString();
        String convert_second = second_text.toString();

        ic.setSize("15px");
        Label l1 = new Label(convert_first + suffix_first + convert_second + suffix_second);
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
