
package com.application.data.restpojo;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "latitude",
        "longitude",
        "height",
        "timezone_abbrevation",
        "utc_timeoffset",
        "modelrun_utc",
        "modelrun_updatetime_utc"
})
@Generated("jsonschema2pojo")
public class Metadata {

    @JsonProperty("name")
    private String name;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("timezone_abbrevation")
    private String timezoneAbbrevation;
    @JsonProperty("utc_timeoffset")
    private Double utcTimeoffset;
    @JsonProperty("modelrun_utc")
    private String modelrunUtc;
    @JsonProperty("modelrun_updatetime_utc")
    private String modelrunUpdatetimeUtc;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("latitude")
    public Double getLatitude() {
        return latitude;
    }

    @JsonProperty("latitude")
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @JsonProperty("longitude")
    public Double getLongitude() {
        return longitude;
    }

    @JsonProperty("longitude")
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @JsonProperty("height")
    public Integer getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(Integer height) {
        this.height = height;
    }

    @JsonProperty("timezone_abbrevation")
    public String getTimezoneAbbrevation() {
        return timezoneAbbrevation;
    }

    @JsonProperty("timezone_abbrevation")
    public void setTimezoneAbbrevation(String timezoneAbbrevation) {
        this.timezoneAbbrevation = timezoneAbbrevation;
    }

    @JsonProperty("utc_timeoffset")
    public Double getUtcTimeoffset() {
        return utcTimeoffset;
    }

    @JsonProperty("utc_timeoffset")
    public void setUtcTimeoffset(Double utcTimeoffset) {
        this.utcTimeoffset = utcTimeoffset;
    }

    @JsonProperty("modelrun_utc")
    public String getModelrunUtc() {
        return modelrunUtc;
    }

    @JsonProperty("modelrun_utc")
    public void setModelrunUtc(String modelrunUtc) {
        this.modelrunUtc = modelrunUtc;
    }

    @JsonProperty("modelrun_updatetime_utc")
    public String getModelrunUpdatetimeUtc() {
        return modelrunUpdatetimeUtc;
    }

    @JsonProperty("modelrun_updatetime_utc")
    public void setModelrunUpdatetimeUtc(String modelrunUpdatetimeUtc) {
        this.modelrunUpdatetimeUtc = modelrunUpdatetimeUtc;
    }

}
