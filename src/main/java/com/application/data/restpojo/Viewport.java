
package com.application.data.restpojo;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "northeast",
    "southwest"
})
@Generated("jsonschema2pojo")
public class Viewport {

    @JsonProperty("northeast")
    private Northeast__1 northeast;
    @JsonProperty("southwest")
    private Southwest__1 southwest;

    @JsonProperty("northeast")
    public Northeast__1 getNortheast() {
        return northeast;
    }

    @JsonProperty("northeast")
    public void setNortheast(Northeast__1 northeast) {
        this.northeast = northeast;
    }

    @JsonProperty("southwest")
    public Southwest__1 getSouthwest() {
        return southwest;
    }

    @JsonProperty("southwest")
    public void setSouthwest(Southwest__1 southwest) {
        this.southwest = southwest;
    }

}
