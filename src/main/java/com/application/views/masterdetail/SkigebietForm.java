package com.application.views.masterdetail;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.awt.*;

public class SkigebietForm extends FormLayout {

    TextField name =new TextField("Name");
    TextField region = new TextField("Region");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");


    public SkigebietForm() {
        addClassName("Skigebiet-Form");

        add(
                name,
                region,
                createButtonsLayout()
        );
    }
        private HorizontalLayout  createButtonsLayout(){
            save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
            close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            save.addClickShortcut(Key.ENTER);
            close.addClickShortcut(Key.ESCAPE);

            return new HorizontalLayout(save, delete, close);
        }
    }

