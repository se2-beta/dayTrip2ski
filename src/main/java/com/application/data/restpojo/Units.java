
package com.application.data.restpojo;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "time",
        "predictability",
        "precipitation_probability",
        "pressure",
        "relativehumidity",
        "co",
        "temperature",
        "winddirection",
        "precipitation",
        "windspeed"
})
@Generated("jsonschema2pojo")
public class Units {

    @JsonProperty("time")
    private String time;
    @JsonProperty("predictability")
    private String predictability;
    @JsonProperty("precipitation_probability")
    private String precipitationProbability;
    @JsonProperty("pressure")
    private String pressure;
    @JsonProperty("relativehumidity")
    private String relativehumidity;
    @JsonProperty("co")
    private String co;
    @JsonProperty("temperature")
    private String temperature;
    @JsonProperty("winddirection")
    private String winddirection;
    @JsonProperty("precipitation")
    private String precipitation;
    @JsonProperty("windspeed")
    private String windspeed;

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(String time) {
        this.time = time;
    }

    @JsonProperty("predictability")
    public String getPredictability() {
        return predictability;
    }

    @JsonProperty("predictability")
    public void setPredictability(String predictability) {
        this.predictability = predictability;
    }

    @JsonProperty("precipitation_probability")
    public String getPrecipitationProbability() {
        return precipitationProbability;
    }

    @JsonProperty("precipitation_probability")
    public void setPrecipitationProbability(String precipitationProbability) {
        this.precipitationProbability = precipitationProbability;
    }

    @JsonProperty("pressure")
    public String getPressure() {
        return pressure;
    }

    @JsonProperty("pressure")
    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    @JsonProperty("relativehumidity")
    public String getRelativehumidity() {
        return relativehumidity;
    }

    @JsonProperty("relativehumidity")
    public void setRelativehumidity(String relativehumidity) {
        this.relativehumidity = relativehumidity;
    }

    @JsonProperty("co")
    public String getCo() {
        return co;
    }

    @JsonProperty("co")
    public void setCo(String co) {
        this.co = co;
    }

    @JsonProperty("temperature")
    public String getTemperature() {
        return temperature;
    }

    @JsonProperty("temperature")
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @JsonProperty("winddirection")
    public String getWinddirection() {
        return winddirection;
    }

    @JsonProperty("winddirection")
    public void setWinddirection(String winddirection) {
        this.winddirection = winddirection;
    }

    @JsonProperty("precipitation")
    public String getPrecipitation() {
        return precipitation;
    }

    @JsonProperty("precipitation")
    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    @JsonProperty("windspeed")
    public String getWindspeed() {
        return windspeed;
    }

    @JsonProperty("windspeed")
    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

}
