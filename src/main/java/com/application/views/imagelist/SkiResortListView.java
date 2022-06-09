package com.application.views.imagelist;

import com.application.data.entity.SkiResort;
import com.application.data.service.SkiResortService;
import com.application.views.MainLayout;
import com.application.views.components.CustomDialog;
import com.application.views.components.SkiResortFilterForm;
import com.application.views.components.SkiResortListViewCard;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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
    SkiResortFilterForm filterForm;

    CustomDialog filterDialog;
    CustomDialog.Position dialogPosition = new CustomDialog.Position("120px", "50px");

    private OrderedList imageContainer;

    SkiResortService service;

    Button save = new Button("Speichern");
    Button cancel = new Button("Abbrechen");

    public SkiResortListView(SkiResortService service) {
        this.service = service;
        addClassNames("image-list-view", "mx-auto", "pb-l", "px-l", "max-w-screen-2xl");

        addClassName("ski-resort-list-view");
        setSizeFull();

        configureImageContainer();
        configureFilterDialog();

        add(
                getToolbar(),
                imageContainer
        );

    }

    private Component getToolbar() {
        filterText.setPlaceholder("Skigebiete suchen ...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        // TODO: filterText.addValueChangeListener(e -> updateList());

        Button filterButton = new Button("Filter");
        filterButton.addClickListener(e -> openFilter());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, filterButton);
        toolbar.addClassName("toolbar");
        toolbar.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        toolbar.addClassNames("pl-xl", "pt-m", "pb-m");

        return toolbar;
    }

    private void openFilter() {
        filterDialog.setPosition(dialogPosition);
        filterDialog.open();
    }

    private void configureFilterDialog() {
        filterDialog = new CustomDialog();
        filterDialog.getElement()
                .setAttribute("aria-label", "System maintenance hint");

        VerticalLayout dialogLayout = createDialogLayout(filterDialog);
        filterDialog.add(dialogLayout);
        filterDialog.setModal(false);
        filterDialog.setCloseOnEsc(true);
        filterDialog.setPosition(dialogPosition);
    }

    private VerticalLayout createDialogLayout(CustomDialog dialog) {
        H2 headline = new H2("Finden sie ein Skigebiet nach ihrem Geschmack!");
        headline.getStyle().set("margin", "var(--lumo-space-m) 0")
                .set("font-size", "1.5em").set("font-weight", "bold");
        headline.addClassNames("text-center");

        SkiResortFilterForm filterForm = new SkiResortFilterForm(dialog);

        VerticalLayout dialogLayout = new VerticalLayout(headline, filterForm);
        dialogLayout.setPadding(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "300px").set("max-width", "100%");

        return dialogLayout;
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

}