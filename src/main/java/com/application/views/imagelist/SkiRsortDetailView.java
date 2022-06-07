package com.application.views.imagelist;

import ch.qos.logback.core.Layout;
import com.application.views.MainLayout;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarVariant;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.charts.model.style.SolidColor;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataView;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import javax.annotation.security.RolesAllowed;

@PageTitle("Skigebiet Details")
@Route(value = "resort-details", layout = MainLayout.class)
@RolesAllowed("USER")
public class SkiRsortDetailView extends Main implements HasComponents, HasStyle { // implements HasUrlParameter<Long>

// Für Routing wenn DB Daten vorhanden
//    @Override
//    public void setParameter(BeforeEvent beforeEvent, Long aLong) {
//
//        Div d = new Div(new Label("Image Details"));
//
//        add(
//             d
//        );
//    }

    // Durch DB Daten ersetzen
    String name = "Schladminger Planai";

    String operator = "Planai-Hochwurzen-Bahnen Gesellschaft m.b.H.";
    Integer zip = 8970;
    String address = "Coburgstrasse 52";
    String region = "Steiermark";
    String city = "Schladming";
    String image_url = "https://www.planai.at/_planai/2_winter/winter-bilder-allgemein/image-thumb__21927__header-image_auto_a532a6968365f70264b8c62e24bae48a/PLANAI%20GIPFEL%20RICHTUNG%20GRAHBERGZINKEN.webp";
    String slope_image_url = "https://hikeandbike.de/wp-content/uploads/2014/07/Pistenplan-Planai.jpg";
    Double weather_current_temperature = -5.0;
    Integer snow_depth_min = 43;
    Integer snow_depth_max = 84;
    Integer amount_fresh_snow = 40;
    Double weather_current_windspeed = 40.0;

    Integer current_utilization_percent = 66;

    String date_season_start = "01.10.2022";
    String date_season_end = "31.03.2023";
    String time_service_start = "08:00:00";
    String time_service_end = "16:00:00";

    Integer total_length = 550;

    Integer ropeways = 34;

    Integer height_min = 268;
    Integer height_max = 1906;

    Integer weather_current_snowfall_forecast_percent = 10;
    Integer weather_current_snowfall_forecast_amount_mm = 30;
    String date_last_snowfall = "01.01.2021";
    Integer avalanche_warning = 1;
    String ticket_page = "https://www.planai.at/de/tickets-preise/preise-winter";

    public SkiRsortDetailView() {

        // Headerimage
        Div skiresort_div = new Div();
        skiresort_div.addClassNames("bg-contrast", "flex items-center", "mb-m", "overflow-hidden", "rounded-m w-full");
        skiresort_div.setMaxHeight("200px");
        Image skiresort_image = new Image();
        skiresort_image.setWidth("100%");
        skiresort_image.setSrc(image_url);
        skiresort_div.add(skiresort_image);

        setSizeFull();
        getStyle().set("text-align", "center");

        add(
                skiresort_div,
                createContent()

        );

    }

    private Component createContent() {

        // Left vertical Layout
        // Title Slope Image
        H4 slope_title = new H4("" + name + " (Talstation: " + height_min + "m - Bergstation: " + height_max + "m)");
        Image slope_image = new Image();
        slope_image.setMaxWidth("100%");
        slope_image.setSrc(slope_image_url);

        Component chart = configureUtilizationChart();

        HorizontalLayout utilization_layout = new HorizontalLayout(new Label("Auslastung"), chart);
        utilization_layout.setWidth("100%");


        VerticalLayout left_layout = new VerticalLayout(
                slope_title,
                utilization_layout,
                slope_image
        );


        // Right vertical Layout
        // Title Details
        H4 slope_title_right = new H4("Skigebietdetails");
        slope_title_right.addClassNames("pl-m", "pb-m");

        Avatar avatar = new Avatar(avalanche_warning.toString());
        avatar.addThemeVariants(AvatarVariant.LUMO_LARGE);
        avatar.setAbbreviation(avalanche_warning.toString());
        avatar.setColorIndex(avalanche_warning);


        // Adress
        Component adress_layout =
                VerticalDataView("Adresse", VaadinIcon.ROAD, zip, " - ", address, "");
        // Region
        Component region_layout =
                VerticalDataView("Region", VaadinIcon.HOME, city, ", ", region, "");
        // Horizontal Layout temperature & snow height
        HorizontalLayout adress_region_layout = new HorizontalLayout(adress_layout, region_layout);
        adress_region_layout.setWidth("100%");

        // Season start-end
        Component season_layout =
                VerticalDataView("Saison", VaadinIcon.CALENDAR, date_season_start, " - ", date_season_end, "");
        // service times
        Component times_layout =
                VerticalDataView("Öffnungszeiten", VaadinIcon.CLOCK, time_service_start, " - ", time_service_end, "");
        // Horizontal Layout temperature & snow height
        HorizontalLayout season_time_layout = new HorizontalLayout(season_layout, times_layout);
        season_time_layout.setWidth("100%");

        // total length
        Component totel_length_layout =
                VerticalDataView("Gesamte Pistenkilometer", VaadinIcon.FORWARD, total_length, " km", "", "");
        // ropeways
        Component ropeways_layout =
                VerticalDataView("Gesamte Seilbahnkilometer", VaadinIcon.CARET_RIGHT, ropeways, " km", "", "");
        // Horizontal Layout temperature & snow height
        HorizontalLayout length_ropeways_layout = new HorizontalLayout(totel_length_layout, ropeways_layout);
        length_ropeways_layout.setWidth("100%");


        // current Information title
        H4 information_title_right = new H4("Aktuelle Informationen");
        information_title_right.addClassNames("pl-m", "pb-s", "pt-m");

        // Temperature
        Component temperatur_layout =
                VerticalDataView("Aktuelle Temperatur", VaadinIcon.CLOUD, weather_current_temperature, " °C", "", "");
        // Snow height min-max
        Component snow_height_layout =
                VerticalDataView("Aktuelle Schneehöhe", VaadinIcon.ASTERISK, snow_depth_min, " - ", snow_depth_max, " cm");
        // Horizontal Layout temperature & snow height
        HorizontalLayout temperature_snowHeight_layout = new HorizontalLayout(temperatur_layout, snow_height_layout);
        temperature_snowHeight_layout.setWidth("100%");


        // Wind Speed
        Component windspeed_layout =
                VerticalDataView("Windgeschwindigkeit", VaadinIcon.LOCATION_ARROW, weather_current_windspeed, " m/s", "", "");
        // Fresh Snow
        Component fresh_snow_layout =
                VerticalDataView("Neuschnee heute", VaadinIcon.TRENDING_UP, amount_fresh_snow, " cm", "", "");
        // Horizontal Layout windspeed & fresh snow
        HorizontalLayout windspeed_freshSnow_layout = new HorizontalLayout(windspeed_layout, fresh_snow_layout);
        windspeed_freshSnow_layout.setWidth("100%");


        // Snowfall forecast
        Component forecast_layout =
                VerticalDataView("Vorhersage Neuschnee", VaadinIcon.TRENDING_UP, weather_current_snowfall_forecast_percent, "% / ", weather_current_snowfall_forecast_amount_mm, " mm");
        // date Last snowfall
        Component last_snowfall_layout =
                VerticalDataView("Datum letzer Schneefall", VaadinIcon.CALENDAR, date_last_snowfall, "", "", "");
        // Horizontal Layout forecast & snowfall
        HorizontalLayout forecast_lastSnowfall_layout = new HorizontalLayout(forecast_layout, last_snowfall_layout);
        forecast_lastSnowfall_layout.setWidth("100%");


        VerticalLayout right_layout = new VerticalLayout(
                slope_title_right,
                getAvalancheLevel(avalanche_warning),
                adress_region_layout,
                season_time_layout,
                length_ropeways_layout,

                information_title_right,
                temperature_snowHeight_layout,
                windspeed_freshSnow_layout,
                forecast_lastSnowfall_layout
        );
        right_layout.setSpacing(false);

        // Horizontal Layout of Vertical Layouts
        HorizontalLayout layout = new HorizontalLayout(left_layout, right_layout);
        layout.setWidth("100%");
        layout.setSpacing(false);

        return layout;
    }

    private Component getAvalancheLevel(Integer level) {

        Label title = new Label("Lawinenwarnstufe");
        title.addClassNames("pr-m", "pl-m");

        HorizontalLayout avalanche_layout = new HorizontalLayout(title);
        avalanche_layout.setMaxWidth("100%");
        avalanche_layout.setHeight("30px");
        avalanche_layout.setSpacing(false);
        avalanche_layout.addClassNames("mb-l");
        avalanche_layout.setAlignItems(FlexComponent.Alignment.CENTER);

        for (int i = 0; i < 5; i++) {
            Integer label = i + 1;
            Label lb = new Label(label.toString());
            lb.getStyle().set("color", "#000000");
            lb.addClassNames("border", "p-0", "m-0");
            lb.setWidth("50px");
            lb.addClassNames("bg-contrast-80");

            if (label.equals(level)) {
                lb.getStyle().set("color", "#ffffff");
                if (level == 1) lb.addClassNames("bg-success");
                if (level == 2) lb.getStyle().set("background-color", "#7EAA46");
                if (level == 3) lb.getStyle().set("background-color", "#AAA546");
                if (level == 4) lb.getStyle().set("background-color", "#DDA835");
                if (level == 5) lb.addClassNames("bg-error");
            }

            avalanche_layout.add(lb);
        }

        Button ticket = new Button("Tickets");
        ticket.addClickListener(e -> UI.getCurrent().getPage().executeJs("window.open($0);",ticket_page));
        ticket.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout layout = new HorizontalLayout(avalanche_layout, ticket);
        layout.setWidth("90%");
        layout.setAlignItems(FlexComponent.Alignment.BASELINE);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        return layout;
    }

    private Component configureUtilizationChart() {

// Create a bullet chart
        Chart chart = new Chart(ChartType.BULLET);
        chart.setHeight("60px");

// Modify the default configuration
        Configuration conf = chart.getConfiguration();
        conf.getChart().setInverted(true);
        conf.getLegend().setEnabled(false);
        conf.getChart().setBackgroundColor(new SolidColor(255, 255, 255, 0));

// Add data
        PlotOptionsBullet options = new PlotOptionsBullet();
        options.setBorderWidth(0);
        options.setColor(SolidColor.BLACK);
        options.getTargetOptions().setWidth("200%");
        DataSeries series = new DataSeries();
        series.add(new DataSeriesItemBullet(0, current_utilization_percent));
        series.setPlotOptions(options);
        conf.addSeries(series);

// Configure the axes
        YAxis yAxis = conf.getyAxis();
        yAxis.setGridLineWidth(0);
        yAxis.setTitle("");
        yAxis.addPlotBand(new PlotBand(0, current_utilization_percent, new SolidColor("#af3d5e")));
        yAxis.addPlotBand(new PlotBand(current_utilization_percent, 100, new SolidColor("#ffffff ")));
        yAxis.setMax(100);
        conf.getxAxis().addCategory(""); // <br/>U.S. $ (1,000s)

        return chart;
    }

    private <T> Component IconText(VaadinIcon icon, T first_text, String suffix_first, T second_text, String suffix_second) {
        Icon ic = new Icon(icon);
        String convert_first = first_text.toString();
        String convert_second = second_text.toString();
        ic.setSize("19px");

        Label l1 = new Label(convert_first + suffix_first + convert_second + suffix_second);
        l1.getStyle().set("font-size", "19px");
        HorizontalLayout hl = new HorizontalLayout(ic, l1);
        hl.addClassNames("pt-0", "mt-0");
        hl.setAlignItems(FlexComponent.Alignment.CENTER);

        return hl;
    }

    private <T> Component VerticalDataView(String title, VaadinIcon icon, T first_text, String suffix_first, T second_text, String suffix_second) {

        Label layout_title = new Label(title);
        layout_title.addClassNames("pb-0", "mb-0");
        Component snow_height = IconText(icon, first_text, suffix_first, second_text, suffix_second);
        VerticalLayout layout = new VerticalLayout(layout_title, snow_height);
        layout.addClassNames("pb-s");
        layout.setSpacing(false);

        return layout;
    }

}
