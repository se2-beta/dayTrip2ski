package com.application.data.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SkiResort")
public class SkiResort extends AbstractEntity {
    @Column(unique=true)
    @NotBlank
    private String name;

    @NotBlank
    private String region;

    @NotBlank
    private String operator;

    @NotBlank
    private String address;

    @NotNull
    private Integer zip;

    @NotBlank
    private String city;

    @NotNull
    private Integer heightMin;

    @NotNull
    private Integer heightMax;

    //@NotNull
    private Double totalLength;

    @NotNull
    private Integer ropeways;

    //@NotNull
    private Double posLon;

    //@NotNull
    private Double posLat;

    @NotBlank
    private String dateSeasonStart;

    @NotBlank
    private String dateSeasonEnd;

    @NotBlank
    private String timeServiceStart;

    @NotBlank
    private String timeServiceEnd;

    @NotNull
    private Integer currentUtilizationPercent;

    //@NotNull
    private Integer userRating;

    //@NotNull
    private Double weatherCurrentWindspeed;

    //@NotNull
    private Double weatherCurrentTemperature;

    //@NotNull
    private Integer weatherCurrentSnowfallForecastPercent;

    //@NotNull
    private Integer weatherCurrentSnowfallForecastAmountMM;

    //@NotBlank
    private String weatherDatetimeLastRead;

    @NotNull
    private Integer snowDepthMin;

    @NotNull
    private Integer snowDepthMax;

    @NotNull
    private Integer amountFreshSnow;

    @NotBlank
    private String dateLastSnowfall;

    @NotBlank
    private String URLTicketpage;

    @NotNull
    private Integer avalancheWarningLevel;

    @NotBlank
    private String URLImageFront;

    @NotBlank
    private String URLImageSlope;

    @OneToMany(mappedBy="skiResort")
    private List<Rating> ratings = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getHeightMin() {
        return heightMin;
    }

    public void setHeightMin(Integer heightMin) {
        this.heightMin = heightMin;
    }

    public Integer getHeightMax() {
        return heightMax;
    }

    public void setHeightMax(Integer heightMax) {
        this.heightMax = heightMax;
    }

    public Double getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(Double totalLength) {
        this.totalLength = totalLength;
    }

    public Integer getRopeways() {
        return ropeways;
    }

    public void setRopeways(Integer ropeways) {
        this.ropeways = ropeways;
    }

    public Double getPosLon() {
        return posLon;
    }

    public void setPosLon(Double posLon) {
        this.posLon = posLon;
    }

    public Double getPosLat() {
        return posLat;
    }

    public void setPosLat(Double posLat) {
        this.posLat = posLat;
    }

    public String getDateSeasonStart() {
        return dateSeasonStart;
    }

    public void setDateSeasonStart(String dateSeasonStart) {
        this.dateSeasonStart = dateSeasonStart;
    }

    public String getDateSeasonEnd() {
        return dateSeasonEnd;
    }

    public void setDateSeasonEnd(String dateSeasonEnd) {
        this.dateSeasonEnd = dateSeasonEnd;
    }

    public String getTimeServiceStart() {
        return timeServiceStart;
    }

    public void setTimeServiceStart(String timeServiceStart) {
        this.timeServiceStart = timeServiceStart;
    }

    public String getTimeServiceEnd() {
        return timeServiceEnd;
    }

    public void setTimeServiceEnd(String timeServiceEnd) {
        this.timeServiceEnd = timeServiceEnd;
    }

    public Integer getCurrentUtilizationPercent() {
        return currentUtilizationPercent;
    }

    public void setCurrentUtilizationPercent(Integer currentUtilizationPercent) {
        this.currentUtilizationPercent = currentUtilizationPercent;
    }

    public Integer getUserRating() {
        return userRating;
    }

    public void setUserRating(Integer userRating) {
        this.userRating = userRating;
    }

    public Double getWeatherCurrentWindspeed() {
        return weatherCurrentWindspeed;
    }

    public void setWeatherCurrentWindspeed(Double weatherCurrentWindspeed) {
        this.weatherCurrentWindspeed = weatherCurrentWindspeed;
    }

    public Double getWeatherCurrentTemperature() {
        return weatherCurrentTemperature;
    }

    public void setWeatherCurrentTemperature(Double weatherCurrentTemperature) {
        this.weatherCurrentTemperature = weatherCurrentTemperature;
    }

    public Integer getWeatherCurrentSnowfallForecastPercent() {
        return weatherCurrentSnowfallForecastPercent;
    }

    public void setWeatherCurrentSnowfallForecastPercent(Integer weatherCurrentSnowfallForecastPercent) {
        this.weatherCurrentSnowfallForecastPercent = weatherCurrentSnowfallForecastPercent;
    }

    public Integer getWeatherCurrentSnowfallForecastAmountMM() {
        return weatherCurrentSnowfallForecastAmountMM;
    }

    public void setWeatherCurrentSnowfallForecastAmountMM(Integer weatherCurrentSnowfallForecastAmountMM) {
        this.weatherCurrentSnowfallForecastAmountMM = weatherCurrentSnowfallForecastAmountMM;
    }

    public String getWeatherDatetimeLastRead() {
        return weatherDatetimeLastRead;
    }

    public void setWeatherDatetimeLastRead(String weatherDatetimeLastRead) {
        this.weatherDatetimeLastRead = weatherDatetimeLastRead;
    }

    public Integer getSnowDepthMin() {
        return snowDepthMin;
    }

    public void setSnowDepthMin(Integer snowDepthMin) {
        this.snowDepthMin = snowDepthMin;
    }

    public Integer getSnowDepthMax() {
        return snowDepthMax;
    }

    public void setSnowDepthMax(Integer snowDepthMax) {
        this.snowDepthMax = snowDepthMax;
    }

    public Integer getAmountFreshSnow() {
        return amountFreshSnow;
    }

    public void setAmountFreshSnow(Integer amountFreshSnow) {
        this.amountFreshSnow = amountFreshSnow;
    }

    public String getDateLastSnowfall() {
        return dateLastSnowfall;
    }

    public void setDateLastSnowfall(String dateLastSnowfall) {
        this.dateLastSnowfall = dateLastSnowfall;
    }

    public String getURLTicketpage() {
        return URLTicketpage;
    }

    public void setURLTicketpage(String URLTicketpage) {
        this.URLTicketpage = URLTicketpage;
    }

    public Integer getAvalancheWarningLevel() {
        return avalancheWarningLevel;
    }

    public void setAvalancheWarningLevel(Integer avalancheWarningLevel) {
        this.avalancheWarningLevel = avalancheWarningLevel;
    }

    public String getURLImageFront() {
        return URLImageFront;
    }

    public void setURLImageFront(String URLImageFront) {
        this.URLImageFront = URLImageFront;
    }

    public String getURLImageSlope() {
        return URLImageSlope;
    }

    public void setURLImageSlope(String URLImageSlope) {
        this.URLImageSlope = URLImageSlope;
    }
}
