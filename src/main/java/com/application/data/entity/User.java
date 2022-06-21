package com.application.data.entity;

import com.application.data.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "application_user")
public class User extends AbstractEntity {

    @NotNull
    @Column(unique=true)
    private String username;

    @NotNull
    private String name;

    @JsonIgnore
    private String hashedPassword;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @NotNull
    private String profilePictureUrl;

    @NotNull
    private Double homeLat;

    @NotNull
    private Double homeLon;

    @NotNull
    private Integer weightFreshSnow = 0;

    @NotNull
    private Integer weightSlopeLength = 0;

    @NotNull
    private Integer weightTravelTime = 0;

    @NotNull
    private Integer weightOccupancy = 0;

    @OneToMany(mappedBy="user")
    private List<Rating> ratings = new ArrayList<>();

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHashedPassword() {
        return hashedPassword;
    }
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }
    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public Double getHomeLat() {
        return homeLat;
    }

    public void setHomeLat(Double homeLat) {
        this.homeLat = homeLat;
    }

    public Double getHomeLon() {
        return homeLon;
    }

    public void setHomeLon(Double homeLon) {
        this.homeLon = homeLon;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public Integer getWeightFreshSnow() {
        return weightFreshSnow;
    }

    public void setWeightFreshSnow(Integer weightFreshSnow) {
        this.weightFreshSnow = weightFreshSnow;
    }

    public Integer getWeightSlopeLength() {
        return weightSlopeLength;
    }

    public void setWeightSlopeLength(Integer weightSlopeLength) {
        this.weightSlopeLength = weightSlopeLength;
    }

    public Integer getWeightTravelTime() {
        return weightTravelTime;
    }

    public void setWeightTravelTime(Integer weightTravelTime) {
        this.weightTravelTime = weightTravelTime;
    }

    public Integer getWeightOccupancy() {
        return weightOccupancy;
    }

    public void setWeightOccupancy(Integer weightOccupancy) {
        this.weightOccupancy = weightOccupancy;
    }
}
