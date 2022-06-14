package com.application.views.masterdetail;

import com.application.data.entity.SkiResort;
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
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;


public class SkiResortForm extends FormLayout{

    Binder<SkiResort> binder = new BeanValidationBinder<>(SkiResort.class);
    TextField name =new TextField("Name");
    TextField region = new TextField("Region");
    TextField operator = new TextField("Betreiber");
    TextField address = new TextField("Addresse");
    IntegerField zip = new IntegerField("Postleitzahl");
    TextField city = new TextField("Stadt");
    IntegerField heightMin = new IntegerField("min Höhenlage");
    IntegerField heightMax = new IntegerField("max Höhenlage");
    IntegerField totalLenght = new IntegerField("Pistenlänge");
    IntegerField ropeways = new IntegerField("Anzahl Pisten");
    NumberField pos_lon = new NumberField("Laengengrad");
    NumberField pos_lat = new NumberField("Breitengrad");
    TextField dateSeasonStart = new TextField("Saisonstart");
    TextField dateSeasonEnd = new TextField("Saisonende");
    TextField timeServiceStart = new TextField("Öffnungszeiten");
    TextField timeServiceEnd = new TextField("Schließzeiten");
    IntegerField currentUtilizationPercent = new IntegerField("Auslastung");
    IntegerField userRating = new IntegerField("User Bewertungen");
    NumberField weatherCurrentWindspeed = new NumberField("aktuelle Windgeschwindigkeit");
    NumberField weatherCurrentTemperature = new NumberField("aktuelle Temperatur");
    IntegerField weatherCurrentSnowfallForecastPercent = new IntegerField("Schneefall Forecast in %");
    IntegerField weatherCurrentSnowfallForecastAmountMM = new IntegerField("Schneefall Forecast in mm");
    TextField weatherDatetimeLastRead = new TextField("Letzte Eingabe Wetter");
    IntegerField snowDepthMin = new IntegerField("Schneehöhe min ");
    IntegerField snowDepthMax = new IntegerField("Schneehöhe max");
    IntegerField amountFreshSnow = new IntegerField("Neuschneehöhe");
    TextField dateLastSnowfall = new TextField("Datum letzter Schneefall");
    TextField URLTicketpage = new TextField("Ticketpreise");
    IntegerField avalancheWarningLevel = new IntegerField("aktuelle Warnsufe");
    TextField URLImageFront = new TextField("Titelbild");
    TextField URLImageSlope = new TextField("Pistenbild");

    Button save = new Button("Speichern");
    Button delete = new Button("Löschen");
    Button close = new Button("Abbrechen");
    private SkiResort skiResort;


    public SkiResortForm() {
        addClassName("SkiResort-Form");
        binder.bindInstanceFields(this);

        add(
                name,
                region,
                operator,
                address,
                zip,
                city,
                heightMin,
                heightMax,
                totalLenght,
                ropeways,
                pos_lon,
                pos_lat,
                dateSeasonStart,
                dateSeasonEnd,
                timeServiceStart,
                timeServiceEnd,
                currentUtilizationPercent,
                userRating,
                weatherCurrentWindspeed,
                weatherCurrentTemperature,
                weatherCurrentSnowfallForecastPercent,
                weatherCurrentSnowfallForecastAmountMM,
                weatherDatetimeLastRead,
                snowDepthMin,
                snowDepthMax,
                amountFreshSnow,
                dateLastSnowfall,
                URLTicketpage,
                avalancheWarningLevel,
                URLImageFront,
                URLImageSlope,
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

    public static abstract class SkiResortEvent extends ComponentEvent<SkiResortForm>{
        private SkiResort skiResort;
        protected SkiResortEvent(SkiResortForm source, SkiResort contact){
            super(source, false);
            this.skiResort = contact;
        }
        public SkiResort getContact(){
            return skiResort;
        }
    }

    public static class SaveEvent extends SkiResortEvent {
        SaveEvent(SkiResortForm source, SkiResort skiResort) {
            super(source, skiResort);
        }
    }

    public static class DeleteEvent extends SkiResortEvent{
        DeleteEvent(SkiResortForm source, SkiResort skiResort) {
            super(source, skiResort);
        }

    }

    public static class CloseEvent extends SkiResortEvent {
        CloseEvent(SkiResortForm source) {
            super(source, null);
        }
    }
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {

        return getEventBus().addListener(eventType, listener);
    }


}
