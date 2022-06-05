package com.application.views.imagelist;

import com.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import javax.annotation.security.RolesAllowed;

@PageTitle("Skigebiete")
@Route(value = "skigebiete", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@RolesAllowed("USER")
public class ImageListView extends Main implements HasComponents, HasStyle {

    TextField filterText = new TextField();
    SkigebieteFilterForm filter;

    private OrderedList imageContainer;

    public ImageListView() {
        addClassNames("image-list-view", "mx-auto", "pb-l", "px-l", "max-w-screen-2xl");

        addClassName("skigebiete-image-list");
        setSizeFull();

        configureImageContainer();
        configureFilter();


        add(
                getToolbar(),
                getContent()
        );

        closeFilter();
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Skigebiete suchen ...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        // TODO: filterText.addValueChangeListener(e -> updateList());

        Button filter = new Button("Filter");
        filter.addClickListener(e -> setFilter());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, filter);
        toolbar.addClassName("toolbar");
        toolbar.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        toolbar.addClassNames("pl-xl", "pt-m", "pb-m");

        return toolbar;
    }

    private void setFilter() {
        filter.setVisible(true);
        addClassName("editing");
    }

    private void closeFilter() {
        filter.setVisible(false);
        removeClassName("editing");
    }

    private void configureFilter() {
        filter = new SkigebieteFilterForm();
        filter.setWidth("5em");
        filter.addClassNames("sticky");
    }


    private void configureImageContainer() {
        imageContainer = new OrderedList();
        imageContainer.addClassNames("gap-l", "grid", "list-none");


        for (int i = 0; i < 6; i++) {

            imageContainer.add(new ImageListViewCard(
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
                    98-i,
                    i));
        }
    }


    private Component getContent() {

        HorizontalLayout container = new HorizontalLayout(imageContainer, filter);
        container.setFlexGrow(2, imageContainer);
        container.setFlexGrow(1, filter);

        return container;

    }


}