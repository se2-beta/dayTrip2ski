package com.application.views.imagelist;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ImageListViewCard extends ListItem {


    public ImageListViewCard(String name, String region, String operator, String address, Integer zip, String city, Integer heigt_min, Integer height_max,
                             Integer total_length, Integer ropeways, Double pos_lon, Double pos_lat, String date_season_start, String date_season_end,
                             String time_service_start, String time_service_end, Integer current_utilization_percent, Integer user_rating,
                             Double weather_current_windspeed, Double weather_current_temperature, Integer weather_current_snowfall_forecast_percent,
                             Integer weather_current_snowfall_forecast_amount_mm, String weather_datetime_lastread, Integer snow_depth_min, Integer snow_depth_max,
                             Integer amount_fresh_snow, String date_last_snowfall, String url_ticketpage, Integer avalanche_warning, String image_url, String snope_image_url
            , Integer filter_rating, Integer colorIndex) {
        addClassNames("bg-contrast-5", "flex", "flex-col", "items-start", "p-m", "rounded-l");


        // Picture Settings
        Div div = new Div();
        div.addClassNames("bg-contrast", "flex items-center", "justify-center", "mb-m", "overflow-hidden",
                "rounded-m w-full");
        div.setHeight("160px");

        Image image = new Image();
        image.setHeight("100%");
        image.setSrc(image_url);


        div.add(image);

        Avatar avatar = new Avatar("98%");
        avatar.addThemeVariants(AvatarVariant.LUMO_LARGE);
        avatar.setAbbreviation(filter_rating + "%");
        avatar.setColorIndex(colorIndex);

        // Title Settings
        Span header = new Span();
        header.addClassNames("text-xl", "font-semibold");
        header.setText(name);

        HorizontalLayout headerIcon = new HorizontalLayout(header, avatar);
        headerIcon.setWidth("100%");
        headerIcon.setAlignItems(FlexComponent.Alignment.CENTER);
        headerIcon.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        Span subtitle = new Span();
        subtitle.addClassNames("text-s", "text-secondary", "pb-m");
        subtitle.setText(region);

        add(
                div,
                headerIcon,
                subtitle,
                horizontalComponents(IconText(VaadinIcon.CLOUD, weather_current_temperature, " °C", "", ""),
                        IconText(VaadinIcon.CAR, "40", " min", "", "")),
                horizontalComponents(IconText(VaadinIcon.ASTERISK, snow_depth_min, " - ", snow_depth_max, " cm"),
                        IconText(VaadinIcon.TRENDING_UP, amount_fresh_snow, " cm", "", ""))

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
        vl1.addClassNames("p-0",  "m-0");
        vl2.addClassNames("p-0",  "m-0");

        HorizontalLayout layout = new HorizontalLayout(vl1, vl2);
        layout.setWidth("100%");
        layout.setHeight("50px");
        layout.setSpacing(false);

        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        return layout;
    }


}
