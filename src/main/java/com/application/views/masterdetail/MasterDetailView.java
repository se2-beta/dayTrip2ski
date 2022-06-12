package com.application.views.masterdetail;

import com.application.data.entity.SampleBook;
import com.application.data.entity.SkiResort;
import com.application.data.service.SampleBookService;
import com.application.data.service.SkiResortService;
import com.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import elemental.json.Json;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.util.UriUtils;

@PageTitle("Master-Detail")
@Route(value = "master-detail/:sampleBookID?/:action?(edit)", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class MasterDetailView extends VerticalLayout {

    Grid<SkiResort> grid = new Grid<>(SkiResort.class);
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
        Notification.show("Skigebiet gesichert");
        updateList();
        closeEditor();
    }

    private void deleteSkiresort(SkigebietForm.DeleteEvent event){
        service.delete(event.getContact().getId());
        Notification.show("Skigebiet gelöscht");
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("name", "region", "operator", "address", "zip", "city", "heightMin", "heightMax", "totalLength", "ropeways", "posLon", "posLat", "dateSeasonStart", "dateSeasonEnd", "timeServiceStart", "timeServiceEnd", "URLTicketpage", "URLImageFront", "URLImageSlope");
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

        Button addSkiButton = new Button("add Contact");
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

