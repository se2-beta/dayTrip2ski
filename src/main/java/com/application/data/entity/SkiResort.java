package com.application.data.entity;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Date;

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
    private Integer height_min;
    private Integer height_max;
    private Double total_length;
    private Integer ropeways;
    private Double pos_lon;
    private Double pos_lat;
    private String date_season_start;
    private String date_season_end;
    private String time_service_start;
    private String time_service_end;
    private Integer current_utilization_percent;
    private Integer user_rating;
    private Double weather_current_windspeed;
    private Double weather_current_temperature;
    private Integer weather_current_snowfall_forecast_percent;
    private Integer weather_current_snowfall_forecast_amount_mm;
    private String weather_datetime_lastread;
    private Integer snow_depth_min;
    private Integer snow_depth_max;
    private Integer amount_fresh_snow;
    private String date_last_snowfall;
    private String url_ticketpage;
    private Integer avalanche_warning_level;
    private String image_front_url;
    private String image_slope_url;

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

    public Integer getHeight_min() {
        return height_min;
    }

    public void setHeight_min(Integer height_min) {
        this.height_min = height_min;
    }

    public Integer getHeight_max() {
        return height_max;
    }

    public void setHeight_max(Integer height_max) {
        this.height_max = height_max;
    }

    public Double getTotal_length() {
        return total_length;
    }

    public void setTotal_length(Double total_length) {
        this.total_length = total_length;
    }

    public Integer getRopeways() {
        return ropeways;
    }

    public void setRopeways(Integer ropeways) {
        this.ropeways = ropeways;
    }

    public Double getPos_lon() {
        return pos_lon;
    }

    public void setPos_lon(Double pos_lon) {
        this.pos_lon = pos_lon;
    }

    public Double getPos_lat() {
        return pos_lat;
    }

    public void setPos_lat(Double pos_lat) {
        this.pos_lat = pos_lat;
    }

    public String getDate_season_start() {
        return date_season_start;
    }

    public void setDate_season_start(String date_season_start) {
        this.date_season_start = date_season_start;
    }

    public String getDate_season_end() {
        return date_season_end;
    }

    public void setDate_season_end(String date_season_end) {
        this.date_season_end = date_season_end;
    }

    public String getTime_service_start() {
        return time_service_start;
    }

    public void setTime_service_start(String time_service_start) {
        this.time_service_start = time_service_start;
    }

    public String getTime_service_end() {
        return time_service_end;
    }

    public void setTime_service_end(String time_service_end) {
        this.time_service_end = time_service_end;
    }

    public Integer getCurrent_utilization_percent() {
        return current_utilization_percent;
    }

    public void setCurrent_utilization_percent(Integer current_utilization_percent) {
        this.current_utilization_percent = current_utilization_percent;
    }

    public Integer getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(Integer user_rating) {
        this.user_rating = user_rating;
    }

    public Double getWeather_current_windspeed() {
        return weather_current_windspeed;
    }

    public void setWeather_current_windspeed(Double weather_current_windspeed) {
        this.weather_current_windspeed = weather_current_windspeed;
    }

    public Double getWeather_current_temperature() {
        return weather_current_temperature;
    }

    public void setWeather_current_temperature(Double weather_current_temperature) {
        this.weather_current_temperature = weather_current_temperature;
    }

    public Integer getWeather_current_snowfall_forecast_percent() {
        return weather_current_snowfall_forecast_percent;
    }

    public void setWeather_current_snowfall_forecast_percent(Integer weather_current_snowfall_forecast_percent) {
        this.weather_current_snowfall_forecast_percent = weather_current_snowfall_forecast_percent;
    }

    public Integer getWeather_current_snowfall_forecast_amount_mm() {
        return weather_current_snowfall_forecast_amount_mm;
    }

    public void setWeather_current_snowfall_forecast_amount_mm(Integer weather_current_snowfall_forecast_amount_mm) {
        this.weather_current_snowfall_forecast_amount_mm = weather_current_snowfall_forecast_amount_mm;
    }

    public String getWeather_datetime_lastread() {
        return weather_datetime_lastread;
    }

    public void setWeather_datetime_lastread(String weather_datetime_lastread) {
        this.weather_datetime_lastread = weather_datetime_lastread;
    }

    public Integer getSnow_depth_min() {
        return snow_depth_min;
    }

    public void setSnow_depth_min(Integer snow_depth_min) {
        this.snow_depth_min = snow_depth_min;
    }

    public Integer getSnow_depth_max() {
        return snow_depth_max;
    }

    public void setSnow_depth_max(Integer snow_depth_max) {
        this.snow_depth_max = snow_depth_max;
    }

    public Integer getAmount_fresh_snow() {
        return amount_fresh_snow;
    }

    public void setAmount_fresh_snow(Integer amount_fresh_snow) {
        this.amount_fresh_snow = amount_fresh_snow;
    }

    public String getDate_last_snowfall() {
        return date_last_snowfall;
    }

    public void setDate_last_snowfall(String date_last_snowfall) {
        this.date_last_snowfall = date_last_snowfall;
    }

    public String getUrl_ticketpage() {
        return url_ticketpage;
    }

    public void setUrl_ticketpage(String url_ticketpage) {
        this.url_ticketpage = url_ticketpage;
    }

    public Integer getAvalanche_warning_level() {
        return avalanche_warning_level;
    }

    public void setAvalanche_warning_level(Integer avalanche_warning_level) {
        this.avalanche_warning_level = avalanche_warning_level;
    }

    public String getImage_front_url() {
        return image_front_url;
    }

    public void setImage_front_url(String image_front_url) {
        this.image_front_url = image_front_url;
    }

    public String getImage_slope_url() {
        return image_slope_url;
    }

    public void setImage_slope_url(String image_slope_url) {
        this.image_slope_url = image_slope_url;
    }
}
