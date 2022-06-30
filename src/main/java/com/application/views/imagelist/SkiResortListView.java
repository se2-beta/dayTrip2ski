package com.application.views.imagelist;

import com.application.data.entity.SkiResort;
import com.application.data.service.RatingService;
import com.application.data.service.SkiResortService;
import com.application.data.service.UserService;
import com.application.security.AuthenticatedUser;
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
import java.util.Optional;

@PageTitle("Skigebiete")
@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@RolesAllowed("USER")
public class SkiResortListView extends Main implements HasComponents, HasStyle {

    TextField filterText = new TextField();
    CustomDialog filterDialog;
    CustomDialog.Position dialogPosition = new CustomDialog.Position("120px", "50px");
    private final OrderedList imageContainer = new OrderedList();
    SkiResortFilterForm filterForm;
    SkiResortService skiResortService;
    UserService userService;
    RatingService ratingService;
    private final AuthenticatedUser authenticatedUser;
    Optional<UI> ui = getUI();

    public SkiResortListView(SkiResortService service, AuthenticatedUser authenticatedUser, UserService userService, RatingService ratingService) {
        this.skiResortService = service;
        this.userService = userService;
        this.authenticatedUser = authenticatedUser;
        this.ratingService = ratingService;

        addClassNames("image-list-view", "mx-auto", "pb-l", "pr-l");
        addClassName("ski-resort-list-view");

        setHeightFull();
        setWidth("90%");

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
        filterText.addValueChangeListener(e -> setImageList());

        Button filterButton = new Button("Präferenzen");
        filterButton.addClickListener(e -> {
            if (filterDialog.isOpened()) {
                closeFilter();
                filterForm.setWeightValues();
            } else {
                openFilter();
            }
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText, filterButton);
        toolbar.addClassName("toolbar");
        toolbar.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        toolbar.addClassNames("pl-xl", "pt-m", "pb-m");

        return toolbar;
    }

    public void setImageList() {

        imageContainer.removeAll();

        for (SkiResort skiResort : skiResortService.findAllSkiResort(filterText.getValue(), authenticatedUser.get().get())) {
            SkiResortListViewCard tempVar = new SkiResortListViewCard(skiResort, authenticatedUser, ratingService);
            RouteConfiguration.forSessionScope().getUrl(SkiResortDetailView.class, new RouteParameters("id", String.valueOf(skiResort.getId())));
            tempVar.addClickListener(e -> navigateToDetailView(skiResort));
            imageContainer.add(tempVar);
        }
    }

    private void openFilter() {
        filterDialog.setPosition(dialogPosition);
        filterDialog.open();
    }

    private void closeFilter() {
        filterDialog.close();
    }

    private void configureFilterDialog() {
        filterDialog = new CustomDialog();

        VerticalLayout dialogLayout = createDialogLayout(filterDialog);
        filterDialog.add(dialogLayout);
        filterDialog.setModal(false);
        filterDialog.setCloseOnEsc(true);
        filterDialog.setPosition(dialogPosition);
    }

    private VerticalLayout createDialogLayout(CustomDialog dialog) {
        H2 headline = new H2("Ihre Präferenzen");
        headline.getStyle().set("margin", "var(--lumo-space-m) 0")
                .set("font-size", "1.5em").set("font-weight", "bold");
        headline.addClassNames("text-center", "my-0", "py-0");

        filterForm = new SkiResortFilterForm(dialog, authenticatedUser, userService, ratingService, this);

        VerticalLayout dialogLayout = new VerticalLayout(headline, filterForm);
        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "300px").set("max-width", "100%");

        return dialogLayout;
    }

    private void configureImageContainer() {
        imageContainer.addClassNames("gap-l", "grid", "list-none");
        setImageList();
    }

    private void navigateToDetailView(SkiResort skiResort) {
        UI.getCurrent().navigate(SkiResortDetailView.class, new RouteParameters("id", String.valueOf(skiResort.getId())));
    }

}