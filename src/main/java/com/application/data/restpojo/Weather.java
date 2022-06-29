package com.application.data.restpojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "metadata",
        "units",
        "data_day"
})
@Generated("jsonschema2pojo")
public class Weather {

    @JsonProperty("metadata")
    private Metadata metadata;
    @JsonProperty("units")
    private Units units;
    @JsonProperty("data_day")
    private DataDay dataDay;

    @JsonProperty("metadata")
    public Metadata getMetadata() {
        return metadata;
    }

    @JsonProperty("metadata")
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    @JsonProperty("units")
    public Units getUnits() {
        return units;
    }

    @JsonProperty("units")
    public void setUnits(Units units) {
        this.units = units;
    }

    @JsonProperty("data_day")
    public DataDay getDataDay() {
        return dataDay;
    }

    @JsonProperty("data_day")
    public void setDataDay(DataDay dataDay) {
        this.dataDay = dataDay;
    }

}
