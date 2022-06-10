package com.application.views.masterdetail;

import com.application.data.entity.SkiResort;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class SkigebietForm extends FormLayout {
    Binder<SkiResort> binder = new BeanValidationBinder<>(SkiResort.class);
    TextField name =new TextField("Name");
    TextField region = new TextField("Region");
    TextField operator = new TextField("Betreiber");
    TextField address = new TextField("Addresse");
    IntegerField zip = new IntegerField("Postleitzahl");
    TextField city = new TextField("Stadt");
    IntegerField height_min = new IntegerField("min Höhenlage");
    IntegerField height_max = new IntegerField("max Höhenlage");
    IntegerField total_lenght = new IntegerField("Pistenlänge");
    IntegerField ropeways = new IntegerField("Anzahl Pisten");
   // BigDecimalField pos_lon = new BigDecimalField("Laengengrad");
   // BigDecimalField pos_lat = new BigDecimalField("Breitengrad");
    TextField date_season_start = new TextField("Saisonstart");
    TextField date_season_end = new TextField("Saisonende");
    TextField time_service_start = new TextField("Öffnungszeiten");
    TextField url_ticketpage = new TextField("Ticketpreise");
    TextField image_front_url = new TextField("Titelbild");
    TextField image_slope_url = new TextField("Pistenbild");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    private SkiResort skiResort;


    public SkigebietForm() {
        addClassName("Skigebiet-Form");
        binder.bindInstanceFields(this);

        add(
                name,
                region,
                operator,
                address,
                zip,
                city,
                height_min,
                height_max,
                total_lenght,
                ropeways,
                total_lenght,
     //           pos_lon,
   //             pos_lat,
                date_season_start,
                date_season_end,
                time_service_start,
                url_ticketpage,
                image_front_url,
                image_slope_url,
                createButtonsLayout()
        );
    }

    public void setSkiResort(SkiResort skiResort){
        this.skiResort = skiResort;
        binder.readBean(skiResort);
    }


        private HorizontalLayout  createButtonsLayout(){
            save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
            close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);


            save.addClickListener(event -> validateAndSave());
            delete.addClickListener(event -> fireEvent(new DeleteEvent(this, skiResort)));
            close.addClickListener(event -> fireEvent(new CloseEvent(this)));

            save.addClickShortcut(Key.ENTER);
            close.addClickShortcut(Key.ESCAPE);

            return new HorizontalLayout(save, delete, close);
        }

        private void validateAndSave(){
            try{
                binder.writeBean(skiResort);
                fireEvent(new SaveEvent(this, skiResort));
            } catch (ValidationException e){
                e.printStackTrace();
            }
        }


        public static abstract class SkigebietFormEvent extends ComponentEvent<SkigebietForm>{
            private SkiResort skiResort;
            protected SkigebietFormEvent(SkigebietForm source, SkiResort contact){
                super(source, false);
                this.skiResort = contact;
            }
            public SkiResort getContact(){
                return skiResort;
            }
        }

    public static class SaveEvent extends SkigebietFormEvent {
        SaveEvent(SkigebietForm source, SkiResort skiResort) {
            super(source, skiResort);
        }
    }

    public static class DeleteEvent extends SkigebietFormEvent{
        DeleteEvent(SkigebietForm source, SkiResort skiResort) {
            super(source, skiResort);
        }

    }

    public static class CloseEvent extends SkigebietFormEvent {
        CloseEvent(SkigebietForm source) {
            super(source, null);
        }
    }
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {


        return getEventBus().addListener(eventType, listener);
    }
    }

