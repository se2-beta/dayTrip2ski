package com.application.data.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Rating", uniqueConstraints = {
        @UniqueConstraint(name = "UniqueUserAndSkiResort", columnNames = {"user_id", "skiResort_id"})})
public class Rating extends AbstractEntity {

    @NotNull
    Double rating;

    @NotNull
    Double distanceVal;

    @NotNull
    Double durationVal;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "skiResort_id", nullable = false)
    private SkiResort skiResort;

    public Rating() {
        this.rating = 0.0;
    }

    public Rating(User user, SkiResort skiResort) {
        this.user = user;
        this.skiResort = skiResort;
        this.rating = 0d;
        this.distanceVal = 0d;
        this.durationVal = 0d;
    }

    public Rating(User user, SkiResort skiResort, Double rating, Double distanceVal, Double durationVal) {
        this.rating = rating;
        this.distanceVal = distanceVal;
        this.durationVal = durationVal;
        this.user = user;
        this.skiResort = skiResort;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDistanceStr() {
        String retval = "";
        if (distanceVal > 1000) {
            retval += Math.floor(distanceVal / 1000) + "km";
        } else {
            retval += (distanceVal) + "m";
        }
        return retval;
    }

    public Double getDistanceVal() {
        return distanceVal;
    }

    public void setDistanceVal(Double distanceVal) {
        this.distanceVal = distanceVal;
    }

    public String getDurationStr() {
        int hours = (int) Math.floor((durationVal / 60) / 60);
        String retval = "";
        if (hours > 0) {
            retval += hours + "h ";
        }
        retval += String.valueOf((int) (durationVal / 60) % 60);
        return retval;
    }


    public Double getDurationVal() {
        return durationVal;
    }

    public void setDurationVal(Double durationVal) {
        this.durationVal = durationVal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SkiResort getSkiResort() {
        return skiResort;
    }

    public void setSkiResort(SkiResort skiResort) {
        this.skiResort = skiResort;
    }

}
