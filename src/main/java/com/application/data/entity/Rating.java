package com.application.data.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
//@Table()
@Table(name = "Rating", uniqueConstraints = {
        @UniqueConstraint(name = "UniqueUserAndSkiResort", columnNames = {"user_id", "skiResort_id"})})
public class Rating extends AbstractEntity {
    @NotNull
    Double rating;

    @NotBlank
    String distanceStr;

    @NotNull
    Double distanceVal;

    @NotBlank
    String durationStr;

    @NotNull
    Double durationVal;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "skiResort_id", nullable=false)
    private SkiResort skiResort;

    public Rating() {
        this.rating = 0.0;
    }

    public Rating(User user, SkiResort skiResort) {
        this.user = user;
        this.skiResort = skiResort;
        this.rating = 0d;
        this.distanceStr = "0";
        this.distanceVal = 0d;
        this.durationStr = "0";
        this.durationVal = 0d;
    }

    public Rating(User user, SkiResort skiResort, Double rating, String distanceStr, Double distanceVal, String durationStr, Double durationVal) {
        this.rating = rating;
        this.distanceStr = distanceStr;
        this.distanceVal = distanceVal;
        this.durationStr = durationStr;
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
        return distanceStr;
    }

    public void setDistanceStr(String distanceStr) {
        this.distanceStr = distanceStr;
    }

    public Double getDistanceVal() {
        return distanceVal;
    }

    public void setDistanceVal(Double distanceVal) {
        this.distanceVal = distanceVal;
    }

    public String getDurationStr() {
        return durationStr;
    }

    public void setDurationStr(String durationStr) {
        this.durationStr = durationStr;
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
