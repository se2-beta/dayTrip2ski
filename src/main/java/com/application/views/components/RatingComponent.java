package com.application.views.components;

import com.vaadin.flow.component.AbstractSinglePropertyField;
import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.PropertyDescriptors;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;

@Tag("icons-rating")
@JsModule(value = "./iconrating.js")
public class RatingComponent extends AbstractSinglePropertyField<RatingComponent, Integer> {

    private static final long serialVersionUID = 1L;
    private static final PropertyDescriptor<Integer, Integer> ratingProperty = PropertyDescriptors.propertyWithDefault("rating", 0);
    private static final PropertyDescriptor<Integer, Integer> numProperty = PropertyDescriptors.propertyWithDefault("numicons", 0);
    private static final PropertyDescriptor<Boolean, Boolean> manualProperty = PropertyDescriptors.propertyWithDefault("manual", false);

    public RatingComponent() {
        this(1, 5, true);
    }

    public RatingComponent(Integer rating) {
        this(rating, 5, true);
    }

    public RatingComponent(Integer rating, Integer numicons) {
        this(rating, numicons, true);
    }

    public RatingComponent(Integer rating, Integer numicons, boolean manual) {
        super("rating", 0, false);
        this.setRating(rating);
        this.setNumicons(numicons);
        this.setManual(manual);
    }

    public Integer getRating() {
        return (Integer) ratingProperty.get(this);
    }

    public void setRating(Integer rating) {
        ratingProperty.set(this, rating);
    }

    public Integer getNumicons() {
        return (Integer) numProperty.get(this);
    }

    public void setNumicons(Integer numicons) {
        numProperty.set(this, numicons);
    }

    public boolean isManual() {
        return (Boolean) manualProperty.get(this);
    }

    public void setManual(boolean manual) {
        manualProperty.set(this, manual);
    }
}


