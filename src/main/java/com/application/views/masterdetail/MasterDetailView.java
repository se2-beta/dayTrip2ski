package com.application.views.masterdetail;

import com.application.data.entity.SkiResort;
import com.application.data.service.SkiResortService;
import com.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import javax.annotation.security.RolesAllowed;


@PageTitle("Admin-Bereich")
@Route(value = "master-detail/:sampleBookID?/:action?(edit)", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class MasterDetailView extends VerticalLayout {

    Grid<SkiResort> grid = new Grid<>(SkiResort.class);
    SkiResortForm form;
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
        form = new SkiResortForm();
        form.setWidth("30em");

        form.addListener(SkiResortForm.SaveEvent.class, this::saveSkiresort);
        form.addListener(SkiResortForm.DeleteEvent.class, this::deleteSkiresort);
        form.addListener(SkiResortForm.CloseEvent.class, e->closeEditor());
    }

    private void saveSkiresort(SkiResortForm.SaveEvent event){
        service.update(event.getContact());
        Notification.show("Skigebiet gesichert");
        updateList();
        closeEditor();
    }

    private void deleteSkiresort(SkiResortForm.DeleteEvent event){
        service.delete(event.getContact().getId());
        Notification.show("Skigebiet gelöscht");
        updateList();
        closeEditor();
    }

    private void configureGrid() {

        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns();
        grid.addColumn(SkiResort::getName).setHeader("Name");
        grid.addColumn(SkiResort::getRegion).setHeader("Region");
        grid.addColumn(SkiResort::getOperator).setHeader("Betreiber");
        grid.addColumn(SkiResort::getAddress).setHeader("Addresse");
        grid.addColumn(SkiResort::getZip).setHeader("Postleitzahl");
        grid.addColumn(SkiResort::getCity).setHeader("Stadt");
        grid.addColumn(SkiResort::getHeightMin).setHeader("Schneehöhe min");
        grid.addColumn(SkiResort::getHeightMax).setHeader("Schneehöhe max");
        grid.addColumn(SkiResort::getTotalLength).setHeader("Streckenlänge");
        grid.addColumn(SkiResort::getRopeways).setHeader("Pistenanzahl");
        grid.addColumn(SkiResort::getPosLon).setHeader("Breitengrad");
        grid.addColumn(SkiResort::getPosLat).setHeader("Längengrad");
        grid.addColumn(SkiResort::getDateSeasonStart).setHeader("Start-Saison");
        grid.addColumn(SkiResort::getDateSeasonEnd).setHeader("Ende-Saison");
        grid.addColumn(SkiResort::getTimeServiceStart).setHeader("Öffnungszeiten");
        grid.addColumn(SkiResort::getTimeServiceEnd).setHeader("Schließzeiten");
        grid.addColumn(SkiResort::getURLImageSlope).setHeader("Pistenbild");
        grid.addColumn(SkiResort::getURLImageFront).setHeader("Titelbild");
        grid.addColumn(SkiResort::getURLTicketpage).setHeader("Ticketseite");
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

        Button addSkiButton = new Button("Skigebiet hinzufügen");
        addSkiButton.addClickListener(e->addSkiResort());
        HorizontalLayout toolbar = new HorizontalLayout(addSkiButton);

        toolbar.addClassName("toolbar");
        return toolbar;

    }
    private void addSkiResort(){
        grid.asSingleSelect().clear();
        editContact(new SkiResort());
    }


}

