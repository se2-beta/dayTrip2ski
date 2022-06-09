package com.application.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "Rating")
public class Rating extends AbstractEntity {
    @Lob
    double rating;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "skiResort_id", nullable=false)
    private SkiResort skiResort;

    public Rating() {
        this.rating = 0.0;
    }

    public Rating(double rating) {
        this.rating = rating;
    }

    public Rating(User user, SkiResort skiResort, double rating) {
        this.rating = rating;
        this.user = user;
        this.skiResort = skiResort;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
