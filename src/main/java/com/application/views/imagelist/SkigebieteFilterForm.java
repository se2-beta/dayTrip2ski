package com.application.views.imagelist;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.shared.Registration;

public class SkigebieteFilterForm extends FormLayout {

    TextField region = new TextField("Region"); // Durch ComboBox<Region> region = new ComboBox<>("Region"); ersetzen?
    IntegerField fresh_snow = new IntegerField("Neuschnee (cm)");
    IntegerField total_length = new IntegerField("Pistenlänge (km)");
    NumberField travel_time = new NumberField("Anfahrtszeit (Stunden)");
    IntegerField current_utilization_percent = new IntegerField("Auslastung (%)");

    Button save = new Button("Speichern");
    Button cancel = new Button("Abbrechen");

    public SkigebieteFilterForm() {        // TODO: Als Argument dann eine Liste an Regionen hinzufügen

        addClassName("skigebieteFilter");
        addClassName("px-xl");

        configureFields();

        add(
                region,
                fresh_snow,
                total_length,
                travel_time,
                current_utilization_percent,
                createButtonLayout()
        );

    }

    private void configureFields() {
        fresh_snow.setMin(0);
        fresh_snow.setHasControls(true);
        fresh_snow.setValue(0);

        total_length.setMin(0);
        total_length.setHasControls(true);
        total_length.setValue(0);

        travel_time.setMin(0);
        travel_time.setHasControls(true);
        travel_time.setStep(0.5);
        travel_time.setValue(0.0);

        current_utilization_percent.setMin(0);
        current_utilization_percent.setMax(100);
        current_utilization_percent.setHasControls(true);
        current_utilization_percent.setStep(10);
        current_utilization_percent.setValue(0);

    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        //TODO cancel.addClickListener(event -> {fireEvent(new CloseEvent(this));

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, cancel);
    }

    public static abstract class SkigebieteFilterEvent extends ComponentEvent<SkigebieteFilterForm> {

        public SkigebieteFilterEvent(SkigebieteFilterForm source, boolean fromClient) {
            super(source, false);
        }

        public static class CloseEvent extends SkigebieteFilterEvent {
            public CloseEvent(SkigebieteFilterForm source) {
                super(source, true); // true => ändern wenn Klasse Skigebiet existiert
            }
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
