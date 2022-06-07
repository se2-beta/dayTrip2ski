package com.application.views.imagelist;

import ch.qos.logback.core.Layout;
import com.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
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
import com.vaadin.flow.data.provider.DataView;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import javax.annotation.security.RolesAllowed;

@PageTitle("Skigebiet-Details")
@Route(value = "resort-details", layout = MainLayout.class)
@RolesAllowed("USER")
public class SkiRsortDetailView extends Main implements HasComponents, HasStyle { // implements HasUrlParameter<Long>


//    @Override
//    public void setParameter(BeforeEvent beforeEvent, Long aLong) {
//
//        Div d = new Div(new Label("Image Details"));
//
//        add(
//             d
//        );
//    }

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

    Integer current_utilization_percent = 50;

    String date_season_start ="01.10.2022";
    String date_season_end = "31.03.2023";
    String time_service_start = "08:00:00";
    String time_service_end = "16:00:00";

    public SkiRsortDetailView() {


        // Zum Testen DELETE
        ImageListViewCard vorlage = new ImageListViewCard(
                10,
                "Schladminger Planai",
                "Steiermark",
                "Planai-Hochwurzen-Bahnen Gesellschaft m.b.H. ",
                "Coburgstrasse 52 ",
                8970,
                "Schladming ",
                268,
                1906,
                550,
                34,
                13.6785045,
                47.3901116,
                "01.10.2022",
                "31.03.2023 ",
                "08:00:00",
                "16:00:00 ",
                50,
                4,
                40.0,
                -5.0,
                10,
                30,
                "22.10.2022 13:34:00 ",
                34,
                84,
                40,
                "01.01.2021",
                "https://www.planai.at/de/tickets-preise/preise-winter ",
                1,
                "https://www.planai.at/_planai/2_winter/winter-bilder-allgemein/image-thumb__21927__header-image_auto_a532a6968365f70264b8c62e24bae48a/PLANAI%20GIPFEL%20RICHTUNG%20GRAHBERGZINKEN.webp",
                "https://hikeandbike.de/wp-content/uploads/2014/07/Pistenplan-Planai.jpg",
                98,
                1);

        //setSpacing(false);


        // Headerimage
        Div skiresort_div = new Div();
        skiresort_div.addClassNames("bg-contrast", "flex items-center", "mb-m", "overflow-hidden", "rounded-m w-full");
        skiresort_div.setMaxHeight("200px");
        Image skiresort_image = new Image();
        skiresort_image.setWidth("100%");
        skiresort_image.setSrc(image_url);
        skiresort_div.add(skiresort_image);


        //add(new H2("This place intentionally left empty"));
        //add(new Paragraph("It’s a place where you can grow your own UI 🤗"));

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
        H4 slope_title = new H4("Pistenübersicht " + name);
        Image slope_image = new Image();
        slope_image.setMaxWidth("100%");
        slope_image.setSrc(slope_image_url);

        Component chart = configureChart();

        HorizontalLayout utilization_layout = new HorizontalLayout(new Label("Auslastung"), chart);


        VerticalLayout left_layout = new VerticalLayout(
                slope_title,
                utilization_layout,
                slope_image
        );




        // Right vertical Layout
        // Title Details
        H4 slope_title_right = new H4("Skigebietdetails");
        slope_title_right.addClassNames("pl-m", "pb-m");


        // Season start-end
        Component season_layout =
                VerticalDataView("Saison", VaadinIcon.CALENDAR, date_season_start, " - ", date_season_end, "");
        // service times
        Component times_layout =
                VerticalDataView("Öffnungszeiten", VaadinIcon.CLOCK, time_service_start, " - ", time_service_end, "");
        // Horizontal Layout temperature & snow height
        HorizontalLayout season_time_layout = new HorizontalLayout(season_layout, times_layout);
        season_time_layout.setWidth("100%");

        // Adress
        Component adress_layout =
                VerticalDataView("Adresse", VaadinIcon.ROAD, zip, " - ", address, "");
        // Region
        Component region_layout =
                VerticalDataView("Region", VaadinIcon.HOME, city, ", ", region, "");
        // Horizontal Layout temperature & snow height
        HorizontalLayout adress_region_layout = new HorizontalLayout(adress_layout, region_layout);
        adress_region_layout.setWidth("100%");



        // current Information title
        H4 information_title_right = new H4("Aktuelle Informationen");
        information_title_right.addClassNames("pl-m", "pb-m", "pt-m");

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
                VerticalDataView("Neuschnee", VaadinIcon.TRENDING_UP, amount_fresh_snow, " cm", "", "");
        // Horizontal Layout windspeed & fresh snow
        HorizontalLayout windspeed_freshSnow_layout = new HorizontalLayout(windspeed_layout, fresh_snow_layout);
        windspeed_freshSnow_layout.setWidth("100%");


        VerticalLayout right_layout = new VerticalLayout(
                slope_title_right,
                adress_region_layout,
                season_time_layout,
                information_title_right,
                temperature_snowHeight_layout,
                windspeed_freshSnow_layout
        );
        right_layout.setSpacing(false);

        // Horizontal Layout of Vertical Layouts
        HorizontalLayout layout = new HorizontalLayout(left_layout, right_layout);
        layout.setWidth("100%");
        layout.setSpacing(false);

        return layout;
    }

    private Component configureChart() {


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
        yAxis.addPlotBand(new PlotBand(current_utilization_percent, 100, new SolidColor("#999999")));
        yAxis.setMax(100);
        conf.getxAxis().addCategory(""); // <br/>U.S. $ (1,000s)

        return chart;
    }

    private <T> Component IconText(VaadinIcon icon, T first_text, String suffix_first, T second_text, String suffix_second) {
        Icon ic = new Icon(icon);
        String convert_first = first_text.toString();
        String convert_second = second_text.toString();
        ic.setSize("20px");

        Label l1 = new Label(convert_first + suffix_first + convert_second + suffix_second);
        l1.getStyle().set("font-size", "20px");
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
