package com.application.views;

import com.application.data.entity.SkiResort;
import com.application.data.service.SkiResortService;
import com.application.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.component.button.Button;

@PageTitle("Debug")
@Route(value = "debug", layout = MainLayout.class)
@AnonymousAllowed
public class DebugView extends VerticalLayout {

    Grid<SkiResort> grid = new Grid<>(SkiResort.class);
    TextField filterText = new TextField();
    SkiResortService service;

    public DebugView(SkiResortService service) {
        this.service = service;
        addClassName("debug-view");
        setSizeFull();
        configureGrid();

        add(/*getToolbar(), */getContent());
        updateList();
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid);
        content.setFlexGrow(2, grid);
        //content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureGrid() {
        grid.addClassNames("skiresort-grid");
        grid.setSizeFull();
        grid.setColumns("id", "name", "operator", "address", "zip", "city");
        //grid.addColumn(skiresort -> skiresort.getName()).setHeader("Name");
        //grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addSkiResortButton = new Button("Ski-Resort hinzufügen");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addSkiResortButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList() {
        grid.setItems(service.getAllSkiResort());
    }
}
