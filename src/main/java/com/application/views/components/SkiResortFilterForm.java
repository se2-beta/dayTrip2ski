package com.application.views.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.shared.Registration;

public class SkiResortFilterForm extends FormLayout {

    IntegerField fresh_snow = new IntegerField("Neuschnee");
    IntegerField total_length = new IntegerField("Pistenlänge");
    IntegerField travel_time = new IntegerField("Anfahrtszeit");
    IntegerField current_utilization_percent = new IntegerField("Auslastung");

    Button save = new Button("Speichern");
    Button cancel = new Button("Abbrechen");

    Dialog dialog;

    public SkiResortFilterForm(CustomDialog dialog) {        // TODO: Als Argument dann eine Liste an Regionen hinzufügen
        this.dialog = dialog;
        addClassName("skigebieteFilter");
        addClassNames("px-0");

        configureFields();

        add(

                fresh_snow,
                total_length,
                travel_time,
                current_utilization_percent,
                createButtonLayout()
        );
    }

    private void configureFields() {
        fresh_snow.setMin(0);
        fresh_snow.setMax(5);
        fresh_snow.setHasControls(true);
        fresh_snow.setValue(0);

        total_length.setMin(0);
        total_length.setMax(5);
        total_length.setHasControls(true);
        total_length.setValue(0);

        travel_time.setMin(0);
        travel_time.setMax(5);
        travel_time.setHasControls(true);
        travel_time.setValue(0);

        current_utilization_percent.setMin(0);
        current_utilization_percent.setMax(5);
        current_utilization_percent.setHasControls(true);
        current_utilization_percent.setValue(0);


    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        cancel.addClickListener(e -> dialog.close());
        //TODO save.addClickListener(event -> {fireEvent(new saveEvent(this));

        HorizontalLayout layout = new HorizontalLayout(save, cancel);
        layout.addClassNames("pt-m");
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        return layout;
    }

    public static abstract class SkigebieteFilterEvent extends ComponentEvent<SkiResortFilterForm> {

        public SkigebieteFilterEvent(SkiResortFilterForm source, boolean fromClient) {
            super(source, false);
        }

        public static class CloseEvent extends SkigebieteFilterEvent {
            public CloseEvent(SkiResortFilterForm source) {
                super(source, true); // true => ändern wenn Klasse Skigebiet existiert
            }
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
