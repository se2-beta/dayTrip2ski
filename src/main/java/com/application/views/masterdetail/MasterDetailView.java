package com.application.views.masterdetail;

import com.application.data.entity.SkiResort;
import com.application.data.service.SkiResortService;
import com.application.views.MainLayout;
import com.vaadin.flow.component.Component;
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
    private SkiResortService service;




    public MasterDetailView(SkiResortService service) {
        this.service = service;
        addClassNames("list-view");
        setSizeFull();
        configureGrid();
        configureForm();
        add(getToolbar(), getContent());

        updateList();

        closeEditor();

    }

    private void closeEditor(){
        form.setSkiResort(null);
        form.setVisible(false);
        removeClassName("editing");

    }

    private void updateList(){
        grid.setItems(service.getAllSkiResort());
    }

    private Component getContent(){
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    public void configureForm(){
        form = new SkigebietForm();
        form.setWidth("25em");

        form.addListener(SkigebietForm.SaveEvent.class, this::saveSkiresort);
        form.addListener(SkigebietForm.DeleteEvent.class, this::deleteSkiresort);
        form.addListener(SkigebietForm.CloseEvent.class, e->closeEditor());
    }

    private void saveSkiresort(SkigebietForm.SaveEvent event){
        service.update(event.getContact());
        updateList();
        closeEditor();
    }

    private void deleteSkiresort(SkigebietForm.DeleteEvent event){
        service.delete(event.getContact().getId());
        updateList();
        closeEditor();


    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("name", "region", "operator", "address", "zip", "city", "height_min", "height_max", "total_length", "ropeways", "pos_lon", "pos_lat", "date_season_start", "date_season_end", "time_service_start", "time_service_end", "url_ticketpage", "image_front_url", "image_slope_url");
        //grid.addColumn(contact -> contact.getName());
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> editContact(e.getValue()));
    }

    private void editContact(SkiResort skiResort){
        if(skiResort == null){
            closeEditor();
        }else {
            form.setSkiResort(skiResort);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addSkiButton = new Button("add Contact");
        addSkiButton.addClickListener(e->addSkiResort());
        HorizontalLayout toolbar = new HorizontalLayout(filterText, addSkiButton);


        toolbar.addClassName("toolbar");
        return toolbar;

    }
    private void addSkiResort(){
        grid.asSingleSelect().clear();
        editContact(new SkiResort());
    }
}
