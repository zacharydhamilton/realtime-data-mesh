
package com.github.zacharydhamilton.objects.geofencing;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Array that contains geofencing information for the system.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "geofencing_zones"
})
public class Data {

    /**
     * Each geofenced zone and its associated rules and attributes is described as an object within the array of features.
     * (Required)
     * 
     */
    @JsonProperty("geofencing_zones")
    @JsonPropertyDescription("Each geofenced zone and its associated rules and attributes is described as an object within the array of features.")
    private GeofencingZones__1 geofencingZones;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Each geofenced zone and its associated rules and attributes is described as an object within the array of features.
     * (Required)
     * 
     */
    @JsonProperty("geofencing_zones")
    public GeofencingZones__1 getGeofencingZones() {
        return geofencingZones;
    }

    /**
     * Each geofenced zone and its associated rules and attributes is described as an object within the array of features.
     * (Required)
     * 
     */
    @JsonProperty("geofencing_zones")
    public void setGeofencingZones(GeofencingZones__1 geofencingZones) {
        this.geofencingZones = geofencingZones;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Data.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("geofencingZones");
        sb.append('=');
        sb.append(((this.geofencingZones == null)?"<null>":this.geofencingZones));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.geofencingZones == null)? 0 :this.geofencingZones.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Data) == false) {
            return false;
        }
        Data rhs = ((Data) other);
        return (((this.geofencingZones == rhs.geofencingZones)||((this.geofencingZones!= null)&&this.geofencingZones.equals(rhs.geofencingZones)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
