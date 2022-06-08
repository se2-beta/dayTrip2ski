package com.application.views.imagelist;

import com.application.data.entity.SkiResort;
import com.application.data.service.SkiResortService;
import com.application.views.MainLayout;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.charts.model.style.SolidColor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;
import java.util.UUID;

@PageTitle("Skigebiet Details")
@Route(value = "skigebietdetail/:id", layout = MainLayout.class)
@RolesAllowed("USER")
public class SkiResortDetailView extends Main implements HasComponents, HasStyle, BeforeEnterObserver { // implements HasUrlParameter<UUID>, , BeforeEnterObserver

    private UUID resortId;

    private SkiResortService service;

    private Optional<SkiResort> skiResort;

    @Override
    public void beforeEnter(BeforeEnterEvent event) {

        resortId = UUID.fromString(event.getRouteParameters().get("id").get());
        //skiResort = service.get("Schladminger Planai & Hochwurzen");
        skiResort = service.get(resortId);

        // Headerimage
        Div skiresortDiv = new Div();
        skiresortDiv.addClassNames("bg-contrast", "flex items-center", "mb-m", "overflow-hidden", "rounded-m w-full");
        skiresortDiv.setMaxHeight("200px");
        Image skiresortImage = new Image();
        skiresortImage.setWidth("100%");
        skiresortImage.setSrc(skiResort.get().getImage_front_url());

        skiresortDiv.add(skiresortImage);


        setSizeFull();
        getStyle().set("text-align", "center");

        add(
                skiresortDiv,
                createContent()

        );
    }

    public SkiResortDetailView(SkiResortService service) {
        this.service = service;
    }

    private Component createContent() {

        // Left vertical Layout
        // Title Slope Image
        H4 slopeTitle = new H4("" + skiResort.get().getName() + " (Talstation: " + skiResort.get().getHeight_min() + "m - Bergstation: " + skiResort.get().getHeight_max() + "m)");
        Image slopeImage = new Image();
        slopeImage.setMaxWidth("100%");
        slopeImage.setSrc(skiResort.get().getImage_slope_url());

        Component chart = configureUtilizationChart();

        HorizontalLayout utilizationLayout = new HorizontalLayout(new Label("Auslastung"), chart);
        utilizationLayout.setWidth("100%");


        VerticalLayout leftLayout = new VerticalLayout(
                slopeTitle,
                utilizationLayout,
                slopeImage
        );


        // Right vertical Layout
        // Title Details
        H4 slopeTitleRight = new H4("Skigebietdetails");
        slopeTitleRight.addClassNames("pl-m", "pb-m");

        // Adress
        Component adressLayout =
                VerticalDataView("Adresse", VaadinIcon.ROAD, skiResort.get().getZip(), " - ", skiResort.get().getAddress(), "");
        // Region
        Component regionLayout =
                VerticalDataView("Region", VaadinIcon.HOME, skiResort.get().getCity(), ", ", skiResort.get().getRegion(), "");
        // Horizontal Layout temperature & snow height
        HorizontalLayout adressRegionLayout = new HorizontalLayout(adressLayout, regionLayout);
        adressRegionLayout.setWidth("100%");

        // Season start-end
        Component seasonLayout =
                VerticalDataView("Saison", VaadinIcon.CALENDAR, skiResort.get().getDate_season_start(), " - ", skiResort.get().getDate_season_end(), "");
        // service times
        Component timesLayout =
                VerticalDataView("Öffnungszeiten", VaadinIcon.CLOCK, skiResort.get().getTime_service_start(), " - ", skiResort.get().getTime_service_end(), "");
        // Horizontal Layout temperature & snow height
        HorizontalLayout seasonTimeLayout = new HorizontalLayout(seasonLayout, timesLayout);
        seasonTimeLayout.setWidth("100%");

        // total length
        Component totelLengthLayout =
                VerticalDataView("Gesamte Pistenkilometer", VaadinIcon.FORWARD, skiResort.get().getTotal_length(), " km", "", "");
        // ropeways
        Component ropewaysLayout =
                VerticalDataView("Gesamte Seilbahnkilometer", VaadinIcon.CARET_RIGHT, skiResort.get().getRopeways(), " km", "", "");
        // Horizontal Layout temperature & snow height
        HorizontalLayout lengthRopewaysLayout = new HorizontalLayout(totelLengthLayout, ropewaysLayout);
        lengthRopewaysLayout.setWidth("100%");


        // current Information title
        H4 informationTitleRight = new H4("Aktuelle Informationen");
        informationTitleRight.addClassNames("pl-m", "pb-s", "pt-m");

        // Temperature
        Component temperaturLayout =
                VerticalDataView("Aktuelle Temperatur", VaadinIcon.CLOUD, skiResort.get().getWeather_current_temperature(), " °C", "", "");
        // Snow height min-max
        Component snowHeightLayout =
                VerticalDataView("Aktuelle Schneehöhe", VaadinIcon.ASTERISK, skiResort.get().getSnow_depth_min(), " - ", skiResort.get().getSnow_depth_max(), " cm");
        // Horizontal Layout temperature & snow height
        HorizontalLayout temperatureSnowHeightLayout = new HorizontalLayout(temperaturLayout, snowHeightLayout);
        temperatureSnowHeightLayout.setWidth("100%");


        // Wind Speed
        Component windspeedLayout =
                VerticalDataView("Windgeschwindigkeit", VaadinIcon.LOCATION_ARROW, skiResort.get().getWeather_current_windspeed(), " m/s", "", "");
        // Fresh Snow
        Component freshSnowLayout =
                VerticalDataView("Neuschnee heute", VaadinIcon.TRENDING_UP, skiResort.get().getAmount_fresh_snow(), " cm", "", "");
        // Horizontal Layout windspeed & fresh snow
        HorizontalLayout windspeedFreshSnowLayout = new HorizontalLayout(windspeedLayout, freshSnowLayout);
        windspeedFreshSnowLayout.setWidth("100%");


        // Snowfall forecast
        Component forecastLayout =
                VerticalDataView("Vorhersage Neuschnee", VaadinIcon.TRENDING_UP, skiResort.get().getWeather_current_snowfall_forecast_percent(),
                        "% / ", skiResort.get().getWeather_current_snowfall_forecast_amount_mm(), " mm");
        // date Last snowfall
        Component lastSnowfallLayout =
                VerticalDataView("Datum letzer Schneefall", VaadinIcon.CALENDAR, skiResort.get().getDate_last_snowfall(), "", "", "");
        // Horizontal Layout forecast & snowfall
        HorizontalLayout forecastLastSnowfallLayout = new HorizontalLayout(forecastLayout, lastSnowfallLayout);
        forecastLastSnowfallLayout.setWidth("100%");


        VerticalLayout rightLayout = new VerticalLayout(
                slopeTitleRight,
                getAvalancheLevel(skiResort.get().getAvalanche_warning_level()),
                adressRegionLayout,
                seasonTimeLayout,
                lengthRopewaysLayout,

                informationTitleRight,
                temperatureSnowHeightLayout,
                windspeedFreshSnowLayout,
                forecastLastSnowfallLayout
        );
        rightLayout.setSpacing(false);

        // Horizontal Layout of Vertical Layouts
        HorizontalLayout layout = new HorizontalLayout(leftLayout, rightLayout);
        layout.setWidth("100%");
        layout.setSpacing(false);

        return layout;
    }

    private Component getAvalancheLevel(Integer level) {

        Label title = new Label("Lawinenwarnstufe");
        title.addClassNames("pr-m", "pl-m");

        HorizontalLayout avalancheLayout = new HorizontalLayout(title);
        avalancheLayout.setMaxWidth("100%");
        avalancheLayout.setHeight("30px");
        avalancheLayout.setSpacing(false);
        avalancheLayout.addClassNames("mb-l");
        avalancheLayout.setAlignItems(FlexComponent.Alignment.CENTER);

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

            avalancheLayout.add(lb);
        }

        Button ticket = new Button("Tickets");
        ticket.addClickListener(e -> UI.getCurrent().getPage().executeJs("window.open($0);", skiResort.get().getUrl_ticketpage()));
        ticket.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout layout = new HorizontalLayout(avalancheLayout, ticket);
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
        series.add(new DataSeriesItemBullet(0, skiResort.get().getCurrent_utilization_percent()));
        series.setPlotOptions(options);
        conf.addSeries(series);

// Configure the axes
        YAxis yAxis = conf.getyAxis();
        yAxis.setGridLineWidth(0);
        yAxis.setTitle("");
        yAxis.addPlotBand(new PlotBand(0, skiResort.get().getCurrent_utilization_percent(), new SolidColor("#af3d5e")));
        yAxis.addPlotBand(new PlotBand(skiResort.get().getCurrent_utilization_percent(), 100, new SolidColor("#ffffff ")));
        yAxis.setMax(100);
        conf.getxAxis().addCategory("");

        return chart;
    }

    private <T> Component IconText(VaadinIcon icon, T first_text, String suffix_first, T second_text, String suffix_second) {
        Icon ic = new Icon(icon);
        String convertFirst = first_text.toString();
        String convertSecond = second_text.toString();
        ic.setSize("19px");

        Label l1 = new Label(convertFirst + suffix_first + convertSecond + suffix_second);
        l1.getStyle().set("font-size", "19px");
        HorizontalLayout hl = new HorizontalLayout(ic, l1);
        hl.addClassNames("pt-0", "mt-0");
        hl.setAlignItems(FlexComponent.Alignment.CENTER);

        return hl;
    }

    private <T> Component VerticalDataView(String title, VaadinIcon icon, T first_text, String suffix_first, T second_text, String suffix_second) {

        Label layoutTitle = new Label(title);
        layoutTitle.addClassNames("pb-0", "mb-0");
        Component snowHeight = IconText(icon, first_text, suffix_first, second_text, suffix_second);
        VerticalLayout layout = new VerticalLayout(layoutTitle, snowHeight);
        layout.addClassNames("pb-s");
        layout.setSpacing(false);

        return layout;
    }


    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        UI.getCurrent().getPage().setTitle("Testtitle");

    }

}
