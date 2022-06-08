package com.application.views.imagelist;

import com.application.data.entity.SkiResort;
import com.application.data.service.SkiResortService;
import com.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;

import javax.annotation.security.RolesAllowed;

@PageTitle("Skigebiete")
@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@RolesAllowed("USER")
public class SkiResortListView extends Main implements HasComponents, HasStyle {

    TextField filterText = new TextField();
    SkiResortFilterForm filter;

    private OrderedList imageContainer;

    SkiResortService service;

    public SkiResortListView(SkiResortService service) {
        this.service = service;
        addClassNames("image-list-view", "mx-auto", "pb-l", "px-l", "max-w-screen-2xl");

        addClassName("ski-resort-list-view");
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
        filter = new SkiResortFilterForm();
        filter.setWidth("5em");
        filter.addClassNames("sticky");
    }

    private void configureImageContainer() {
        imageContainer = new OrderedList();
        imageContainer.addClassNames("gap-l", "grid", "list-none");

        for (SkiResort skiResort : service.getAllSkiResort()) {

            SkiResortListViewCard tempVar = new SkiResortListViewCard(skiResort);

            RouteConfiguration.forSessionScope().getUrl(SkiResortDetailView.class, new RouteParameters("id", String.valueOf(skiResort.getId())));

            tempVar.addClickListener(e -> viewDetails(skiResort));
            imageContainer.add(tempVar);
        }
    }


    private void viewDetails(SkiResort skiResort) {
        UI.getCurrent().navigate(SkiResortDetailView.class, new RouteParameters("id", String.valueOf(skiResort.getId())));
    }


    private Component getContent() {

        HorizontalLayout container = new HorizontalLayout(imageContainer, filter);
        container.setFlexGrow(2, imageContainer);
        container.setFlexGrow(1, filter);

        return container;

    }


}