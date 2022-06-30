package com.application.data.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SkiResort")
public class SkiResort extends AbstractEntity {

    @Column(unique = true)
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

    @NotNull
    private Double totalLength;

    @NotNull
    private Integer ropeWays;

    private Double posLon;

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

    private Integer userRating;

    private Double weatherCurrentWindSpeed;

    private Double weatherCurrentTemperature;

    private Integer weatherCurrentSnowfallForecastPercent;

    private Integer weatherCurrentSnowfallForecastAmountMM;

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
    private String urlTicketPage;

    @NotNull
    private Integer avalancheWarningLevel;

    @NotBlank
    private String urlImageFront;

    @NotBlank
    private String urlImageSlope;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "skiResort", cascade = CascadeType.ALL, orphanRemoval = true)
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

    public Integer getRopeWays() {
        return ropeWays;
    }

    public void setRopeWays(Integer ropeWays) {
        this.ropeWays = ropeWays;
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

    public Double getWeatherCurrentWindSpeed() {
        return weatherCurrentWindSpeed;
    }

    public void setWeatherCurrentWindSpeed(Double weatherCurrentWindSpeed) {
        this.weatherCurrentWindSpeed = weatherCurrentWindSpeed;
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

    public String getUrlTicketPage() {
        return urlTicketPage;
    }

    public void setUrlTicketPage(String urlTicketpage) {
        this.urlTicketPage = urlTicketpage;
    }

    public Integer getAvalancheWarningLevel() {
        return avalancheWarningLevel;
    }

    public void setAvalancheWarningLevel(Integer avalancheWarningLevel) {
        this.avalancheWarningLevel = avalancheWarningLevel;
    }

    public String getUrlImageFront() {
        return urlImageFront;
    }

    public void setUrlImageFront(String urlImageFront) {
        this.urlImageFront = urlImageFront;
    }

    public String getUrlImageSlope() {
        return urlImageSlope;
    }

    public void setUrlImageSlope(String urlImageSlope) {
        this.urlImageSlope = urlImageSlope;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public int getNumberRatings() {
        return ratings.size();
    }

    public Double getRatingByUser(User user) {
        for (Rating r : ratings) {
            if (r.getUser().equals(user)) {
                return r.getRating();
            }
        }
        return 0d;
    }

}
