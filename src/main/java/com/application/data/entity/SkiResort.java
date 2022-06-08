package com.application.data.entity;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "SkiResort")
public class SkiResort extends AbstractEntity {
    @Lob
    private String name;
    private String region;
    private String operator;
    private String address;
    private Integer zip;
    private String city;
    private Integer heightMin;
    private Integer heightMax;
    private Double totalLength;
    private Integer ropeways;
    private Double posLon;
    private Double posLat;
    private String dateSeasonStart;
    private String dateSeasonEnd;
    private String timeServiceStart;
    private String timeServiceEnd;
    private Integer currentUtilizationPercent;
    private Integer userRating;
    private Double weatherCurrentWindspeed;
    private Double weatherCurrentTemperature;
    private Integer weatherCurrentSnowfallForecastPercent;
    private Integer weatherCurrentSnowfallForecastAmountMM;
    private String weatherDatetimeLastRead;
    private Integer snowDepthMin;
    private Integer snowDepthMax;
    private Integer amountFreshSnow;
    private String dateLastSnowfall;
    private String URLTicketpage;
    private Integer avalancheWarningLevel;
    private String URLImageFront;
    private String URLImageSlope;

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
