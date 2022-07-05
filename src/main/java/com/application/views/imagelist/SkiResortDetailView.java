package com.application.views.imagelist;

import com.application.data.entity.SkiResort;
import com.application.data.service.SkiResortService;
import com.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.charts.model.style.SolidColor;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@PageTitle("Skigebiet Details")
@Route(value = "skigebietdetail/:id", layout = MainLayout.class)
@RolesAllowed("USER")
public class SkiResortDetailView extends Main implements HasComponents, HasStyle, BeforeEnterObserver {

    private final SkiResortService service;

    private Optional<SkiResort> skiResort;

    @Override
    public void beforeEnter(BeforeEnterEvent event) {

        Integer resortId = Integer.valueOf(event.getRouteParameters().get("id").get());
        skiResort = service.get(resortId);

        Div skiresortDiv = new Div();
        skiresortDiv.addClassNames("bg-contrast", "flex items-center", "mb-m", "overflow-hidden", "rounded-m w-full");
        skiresortDiv.setMaxHeight("200px");
        Image skiresortImage = new Image();
        skiresortImage.setWidth("100%");
        skiresortImage.setSrc(skiResort.isPresent()
                ?
                skiResort.get().getUrlImageFront()
                :
                "https://user-images.githubusercontent.com/24848110/33519396-7e56363c-d79d-11e7-969b-09782f5ccbab.png");
        skiresortImage.setAlt("Skigebiete-Titelbild");

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

        H4 slopeTitle = new H4("" + (skiResort.isPresent() ? skiResort.get().getName() : "Invalid")
                + " (Talstation: " + skiResort.get().getHeightMin() + "m - Bergstation: " + skiResort.get().getHeightMax() + "m)");
        Image slopeImage = new Image();
        slopeImage.setMaxWidth("100%");
        slopeImage.setSrc(skiResort.get().getUrlImageSlope());

        Component chart = configureUtilizationChart();

        HorizontalLayout utilizationLayout = new HorizontalLayout(new Label("Auslastung"), chart);
        utilizationLayout.setMaxWidth("100%");

        VerticalLayout leftLayout = new VerticalLayout(
                slopeTitle,
                utilizationLayout,
                slopeImage
        );

        H4 slopeTitleRight = new H4("Skigebietdetails");
        slopeTitleRight.addClassNames("pl-m", "pb-m");

        Component adressRegionLayout = horizontalDataView(
                VerticalDataView("Adresse", VaadinIcon.ROAD, skiResort.get().getZip(), " - ", skiResort.get().getAddress(), ""),
                VerticalDataView("Region", VaadinIcon.HOME, skiResort.get().getCity(), ", ", skiResort.get().getRegion(), "")
        );

        Component seasonTimeLayout = horizontalDataView(
                VerticalDataView("Saison", VaadinIcon.CALENDAR, skiResort.get().getDateSeasonStart(), " - ", skiResort.get().getDateSeasonEnd(), ""),
                VerticalDataView("Öffnungszeiten", VaadinIcon.CLOCK, skiResort.get().getTimeServiceStart(), " - ", skiResort.get().getTimeServiceEnd(), "")
        );

        Component lengthRopewaysLayout = horizontalDataView(
                VerticalDataView("Gesamte Pistenkilometer", VaadinIcon.FORWARD, skiResort.get().getTotalLength(), " km", "", ""),
                VerticalDataView("Gesamte Seilbahnkilometer", VaadinIcon.CARET_RIGHT, skiResort.get().getRopeWays(), " km", "", "")
        );


        H4 informationTitleRight = new H4("Aktuelle Informationen");
        informationTitleRight.addClassNames("pl-m", "pb-s", "pt-m");

        Component temperatureSnowHeightLayout = horizontalDataView(
                VerticalDataView("Aktuelle Temperatur", VaadinIcon.CLOUD, skiResort.get().getWeatherCurrentTemperature(), " °C", "", ""),
                VerticalDataView("Aktuelle Schneehöhe", VaadinIcon.ASTERISK, skiResort.get().getSnowDepthMin(), " - ", skiResort.get().getSnowDepthMax(), " cm")
        );

        Component windspeedFreshSnowLayout = horizontalDataView(
                VerticalDataView("Windgeschwindigkeit", VaadinIcon.LOCATION_ARROW, skiResort.get().getWeatherCurrentWindSpeed(), " m/s", "", ""),
                VerticalDataView("Neuschnee heute", VaadinIcon.TRENDING_UP, skiResort.get().getAmountFreshSnow(), " cm", "", "")
        );

        Component forecastLastSnowfallLayout = horizontalDataView(
                VerticalDataView("Vorhersage Neuschnee", VaadinIcon.TRENDING_UP, skiResort.get().getWeatherCurrentSnowfallForecastPercent(),
                        "% / ", skiResort.get().getWeatherCurrentSnowfallForecastAmountMM(), " mm"),
                VerticalDataView("Datum letzter Schneefall", VaadinIcon.CALENDAR, skiResort.get().getDateLastSnowfall(), "", "", "")
        );

        VerticalLayout rightLayout = new VerticalLayout(
                slopeTitleRight,
                getAvalancheLevel(skiResort.get().getAvalancheWarningLevel()),
                adressRegionLayout,
                seasonTimeLayout,
                lengthRopewaysLayout,

                informationTitleRight,
                temperatureSnowHeightLayout,
                windspeedFreshSnowLayout,
                forecastLastSnowfallLayout
        );
        rightLayout.setSpacing(false);

        FormLayout layout = new FormLayout(leftLayout, rightLayout);
        layout.setWidthFull();
        layout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("1000px", 2)

        );

        return layout;
    }

    private Component horizontalDataView(Component left, Component right) {

        HorizontalLayout layout = new HorizontalLayout(left, right);
        layout.setWidth("100%");

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
        ticket.addClickListener(e -> UI.getCurrent().getPage().executeJs("window.open($0);",
                skiResort.isPresent()
                        ?
                        skiResort.get().getUrlTicketPage()
                        :
                        Notification.show("Webseite nicht verfügbar!")));
        ticket.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout layout = new HorizontalLayout(avalancheLayout, ticket);
        layout.setWidth("90%");
        layout.setAlignItems(FlexComponent.Alignment.BASELINE);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        return layout;
    }

    private Component configureUtilizationChart() {

        Chart chart = new Chart(ChartType.BULLET);
        chart.setHeight("60px");

        Configuration conf = chart.getConfiguration();
        conf.getChart().setInverted(true);
        conf.getLegend().setEnabled(false);
        conf.getChart().setBackgroundColor(new SolidColor(255, 255, 255, 0));

        PlotOptionsBullet options = new PlotOptionsBullet();
        options.setBorderWidth(0);
        options.setColor(SolidColor.BLACK);
        options.getTargetOptions().setWidth("200%");
        DataSeries series = new DataSeries();
        series.add(new DataSeriesItemBullet(0, skiResort.isPresent() ? skiResort.get().getCurrentUtilizationPercent() : 0));
        series.setPlotOptions(options);
        conf.addSeries(series);

        YAxis yAxis = conf.getyAxis();
        yAxis.setGridLineWidth(0);
        yAxis.setTitle("");
        yAxis.addPlotBand(new PlotBand(0, skiResort.get().getCurrentUtilizationPercent(), new SolidColor("#af3d5e")));
        yAxis.addPlotBand(new PlotBand(skiResort.get().getCurrentUtilizationPercent(), 100, new SolidColor("#ffffff ")));
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


}
