package com.application.views.masterdetail;

import com.application.data.entity.SkiResort;
import com.application.views.MainLayout;
import com.application.views.list.SkigebietForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@PageTitle("Master-Detail")
@Route(value = "master-detail/:sampleBookID?/:action?(edit)", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class MasterDetailView extends VerticalLayout {
    Grid<SkiResort> grid = new Grid<>(SkiResort.class);
    TextField filterText = new TextField();
    SkigebietForm form;





    public MasterDetailView() {
        addClassNames("list-view");
        setSizeFull();
        configureGrid();
        //configureForm();
        add(getToolbar(), grid);

    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("name", "region");
        //grid.addColumn(contact -> contact.get);
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addSkiButton = new Button("add Contact");
        HorizontalLayout toolbar = new HorizontalLayout(filterText, addSkiButton);


        toolbar.addClassName("toolbar");
        return toolbar;

    }
}
