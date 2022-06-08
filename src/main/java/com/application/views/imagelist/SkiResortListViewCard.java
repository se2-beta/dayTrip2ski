package com.application.views.imagelist;

import com.application.data.entity.SkiResort;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class SkiResortListViewCard extends ListItem {

    Integer filter_rating = 98;

    public SkiResortListViewCard(SkiResort skiResort) {
        addClassNames("bg-contrast-5", "flex", "flex-col", "items-start", "p-m", "rounded-l");


        // Picture Settings
        Div div = new Div();
        div.addClassNames("bg-contrast", "flex items-center", "justify-center", "mb-m", "overflow-hidden",
                "rounded-m w-full");
        div.setHeight("160px");

        Image image = new Image();
        image.setHeight("100%");
        image.setSrc(skiResort.getImage_front_url());
        //image.addClickListener(e -> Notification.show("Clicked on image: " + skiResort.getId())); // TODO


        div.add(image);

        Avatar avatar = new Avatar("fr");
        avatar.addThemeVariants(AvatarVariant.LUMO_LARGE);
        avatar.setAbbreviation(filter_rating + "%");
        avatar.setColorIndex(3);
        avatar.getStyle().set("font-weight", "700");

        // Title Settings
        Span header = new Span();
        header.addClassNames("text-xl", "font-semibold", "pb-0");
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


        add(
                div,
                headerSubTitle,
                horizontalComponents(IconText(VaadinIcon.CLOUD, skiResort.getWeather_current_temperature(), " °C", "", ""),
                        IconText(VaadinIcon.CAR, "40", " min", "", "")),
                horizontalComponents(IconText(VaadinIcon.ASTERISK, skiResort.getSnow_depth_min(), " - ", skiResort.getSnow_depth_max(), " cm"),
                        IconText(VaadinIcon.TRENDING_UP, skiResort.getAmount_fresh_snow(), " cm", "", ""))

        );

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
        layout.setHeight("50px");
        layout.setSpacing(false);

        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        return layout;
    }
// TODO
//    private void viewDetails() { // Skigebiet skigebiet
//        UI.getCurrent().navigate(ImageDetailView.class, 11); //
//    }


}
