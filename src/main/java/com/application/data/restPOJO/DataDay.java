
package com.application.data.restPOJO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "time",
    "pictocode",
    "uvindex",
    "temperature_max",
    "temperature_min",
    "temperature_mean",
    "felttemperature_max",
    "felttemperature_min",
    "winddirection",
    "precipitation_probability",
    "rainspot",
    "predictability_class",
    "predictability",
    "precipitation",
    "snowfraction",
    "sealevelpressure_max",
    "sealevelpressure_min",
    "sealevelpressure_mean",
    "windspeed_max",
    "windspeed_mean",
    "windspeed_min",
    "relativehumidity_max",
    "relativehumidity_min",
    "relativehumidity_mean",
    "convective_precipitation",
    "precipitation_hours",
    "humiditygreater90_hours"
})
@Generated("jsonschema2pojo")
public class DataDay {

    @JsonProperty("time")
    private List<String> time = null;
    @JsonProperty("pictocode")
    private List<Integer> pictocode = null;
    @JsonProperty("uvindex")
    private List<Integer> uvindex = null;
    @JsonProperty("temperature_max")
    private List<Double> temperatureMax = null;
    @JsonProperty("temperature_min")
    private List<Double> temperatureMin = null;
    @JsonProperty("temperature_mean")
    private List<Double> temperatureMean = null;
    @JsonProperty("felttemperature_max")
    private List<Double> felttemperatureMax = null;
    @JsonProperty("felttemperature_min")
    private List<Double> felttemperatureMin = null;
    @JsonProperty("winddirection")
    private List<Integer> winddirection = null;
    @JsonProperty("precipitation_probability")
    private List<Integer> precipitationProbability = null;
    @JsonProperty("rainspot")
    private List<String> rainspot = null;
    @JsonProperty("predictability_class")
    private List<Integer> predictabilityClass = null;
    @JsonProperty("predictability")
    private List<Integer> predictability = null;
    @JsonProperty("precipitation")
    private List<Double> precipitation = null;
    @JsonProperty("snowfraction")
    private List<Double> snowfraction = null;
    @JsonProperty("sealevelpressure_max")
    private List<Integer> sealevelpressureMax = null;
    @JsonProperty("sealevelpressure_min")
    private List<Integer> sealevelpressureMin = null;
    @JsonProperty("sealevelpressure_mean")
    private List<Integer> sealevelpressureMean = null;
    @JsonProperty("windspeed_max")
    private List<Double> windspeedMax = null;
    @JsonProperty("windspeed_mean")
    private List<Double> windspeedMean = null;
    @JsonProperty("windspeed_min")
    private List<Double> windspeedMin = null;
    @JsonProperty("relativehumidity_max")
    private List<Integer> relativehumidityMax = null;
    @JsonProperty("relativehumidity_min")
    private List<Integer> relativehumidityMin = null;
    @JsonProperty("relativehumidity_mean")
    private List<Integer> relativehumidityMean = null;
    @JsonProperty("convective_precipitation")
    private List<Double> convectivePrecipitation = null;
    @JsonProperty("precipitation_hours")
    private List<Double> precipitationHours = null;
    @JsonProperty("humiditygreater90_hours")
    private List<Double> humiditygreater90Hours = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("time")
    public List<String> getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(List<String> time) {
        this.time = time;
    }

    @JsonProperty("pictocode")
    public List<Integer> getPictocode() {
        return pictocode;
    }

    @JsonProperty("pictocode")
    public void setPictocode(List<Integer> pictocode) {
        this.pictocode = pictocode;
    }

    @JsonProperty("uvindex")
    public List<Integer> getUvindex() {
        return uvindex;
    }

    @JsonProperty("uvindex")
    public void setUvindex(List<Integer> uvindex) {
        this.uvindex = uvindex;
    }

    @JsonProperty("temperature_max")
    public List<Double> getTemperatureMax() {
        return temperatureMax;
    }

    @JsonProperty("temperature_max")
    public void setTemperatureMax(List<Double> temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    @JsonProperty("temperature_min")
    public List<Double> getTemperatureMin() {
        return temperatureMin;
    }

    @JsonProperty("temperature_min")
    public void setTemperatureMin(List<Double> temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    @JsonProperty("temperature_mean")
    public List<Double> getTemperatureMean() {
        return temperatureMean;
    }

    @JsonProperty("temperature_mean")
    public void setTemperatureMean(List<Double> temperatureMean) {
        this.temperatureMean = temperatureMean;
    }

    @JsonProperty("felttemperature_max")
    public List<Double> getFelttemperatureMax() {
        return felttemperatureMax;
    }

    @JsonProperty("felttemperature_max")
    public void setFelttemperatureMax(List<Double> felttemperatureMax) {
        this.felttemperatureMax = felttemperatureMax;
    }

    @JsonProperty("felttemperature_min")
    public List<Double> getFelttemperatureMin() {
        return felttemperatureMin;
    }

    @JsonProperty("felttemperature_min")
    public void setFelttemperatureMin(List<Double> felttemperatureMin) {
        this.felttemperatureMin = felttemperatureMin;
    }

    @JsonProperty("winddirection")
    public List<Integer> getWinddirection() {
        return winddirection;
    }

    @JsonProperty("winddirection")
    public void setWinddirection(List<Integer> winddirection) {
        this.winddirection = winddirection;
    }

    @JsonProperty("precipitation_probability")
    public List<Integer> getPrecipitationProbability() {
        return precipitationProbability;
    }

    @JsonProperty("precipitation_probability")
    public void setPrecipitationProbability(List<Integer> precipitationProbability) {
        this.precipitationProbability = precipitationProbability;
    }

    @JsonProperty("rainspot")
    public List<String> getRainspot() {
        return rainspot;
    }

    @JsonProperty("rainspot")
    public void setRainspot(List<String> rainspot) {
        this.rainspot = rainspot;
    }

    @JsonProperty("predictability_class")
    public List<Integer> getPredictabilityClass() {
        return predictabilityClass;
    }

    @JsonProperty("predictability_class")
    public void setPredictabilityClass(List<Integer> predictabilityClass) {
        this.predictabilityClass = predictabilityClass;
    }

    @JsonProperty("predictability")
    public List<Integer> getPredictability() {
        return predictability;
    }

    @JsonProperty("predictability")
    public void setPredictability(List<Integer> predictability) {
        this.predictability = predictability;
    }

    @JsonProperty("precipitation")
    public List<Double> getPrecipitation() {
        return precipitation;
    }

    @JsonProperty("precipitation")
    public void setPrecipitation(List<Double> precipitation) {
        this.precipitation = precipitation;
    }

    @JsonProperty("snowfraction")
    public List<Double> getSnowfraction() {
        return snowfraction;
    }

    @JsonProperty("snowfraction")
    public void setSnowfraction(List<Double> snowfraction) {
        this.snowfraction = snowfraction;
    }

    @JsonProperty("sealevelpressure_max")
    public List<Integer> getSealevelpressureMax() {
        return sealevelpressureMax;
    }

    @JsonProperty("sealevelpressure_max")
    public void setSealevelpressureMax(List<Integer> sealevelpressureMax) {
        this.sealevelpressureMax = sealevelpressureMax;
    }

    @JsonProperty("sealevelpressure_min")
    public List<Integer> getSealevelpressureMin() {
        return sealevelpressureMin;
    }

    @JsonProperty("sealevelpressure_min")
    public void setSealevelpressureMin(List<Integer> sealevelpressureMin) {
        this.sealevelpressureMin = sealevelpressureMin;
    }

    @JsonProperty("sealevelpressure_mean")
    public List<Integer> getSealevelpressureMean() {
        return sealevelpressureMean;
    }

    @JsonProperty("sealevelpressure_mean")
    public void setSealevelpressureMean(List<Integer> sealevelpressureMean) {
        this.sealevelpressureMean = sealevelpressureMean;
    }

    @JsonProperty("windspeed_max")
    public List<Double> getWindspeedMax() {
        return windspeedMax;
    }

    @JsonProperty("windspeed_max")
    public void setWindspeedMax(List<Double> windspeedMax) {
        this.windspeedMax = windspeedMax;
    }

    @JsonProperty("windspeed_mean")
    public List<Double> getWindspeedMean() {
        return windspeedMean;
    }

    @JsonProperty("windspeed_mean")
    public void setWindspeedMean(List<Double> windspeedMean) {
        this.windspeedMean = windspeedMean;
    }

    @JsonProperty("windspeed_min")
    public List<Double> getWindspeedMin() {
        return windspeedMin;
    }

    @JsonProperty("windspeed_min")
    public void setWindspeedMin(List<Double> windspeedMin) {
        this.windspeedMin = windspeedMin;
    }

    @JsonProperty("relativehumidity_max")
    public List<Integer> getRelativehumidityMax() {
        return relativehumidityMax;
    }

    @JsonProperty("relativehumidity_max")
    public void setRelativehumidityMax(List<Integer> relativehumidityMax) {
        this.relativehumidityMax = relativehumidityMax;
    }

    @JsonProperty("relativehumidity_min")
    public List<Integer> getRelativehumidityMin() {
        return relativehumidityMin;
    }

    @JsonProperty("relativehumidity_min")
    public void setRelativehumidityMin(List<Integer> relativehumidityMin) {
        this.relativehumidityMin = relativehumidityMin;
    }

    @JsonProperty("relativehumidity_mean")
    public List<Integer> getRelativehumidityMean() {
        return relativehumidityMean;
    }

    @JsonProperty("relativehumidity_mean")
    public void setRelativehumidityMean(List<Integer> relativehumidityMean) {
        this.relativehumidityMean = relativehumidityMean;
    }

    @JsonProperty("convective_precipitation")
    public List<Double> getConvectivePrecipitation() {
        return convectivePrecipitation;
    }

    @JsonProperty("convective_precipitation")
    public void setConvectivePrecipitation(List<Double> convectivePrecipitation) {
        this.convectivePrecipitation = convectivePrecipitation;
    }

    @JsonProperty("precipitation_hours")
    public List<Double> getPrecipitationHours() {
        return precipitationHours;
    }

    @JsonProperty("precipitation_hours")
    public void setPrecipitationHours(List<Double> precipitationHours) {
        this.precipitationHours = precipitationHours;
    }

    @JsonProperty("humiditygreater90_hours")
    public List<Double> getHumiditygreater90Hours() {
        return humiditygreater90Hours;
    }

    @JsonProperty("humiditygreater90_hours")
    public void setHumiditygreater90Hours(List<Double> humiditygreater90Hours) {
        this.humiditygreater90Hours = humiditygreater90Hours;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
