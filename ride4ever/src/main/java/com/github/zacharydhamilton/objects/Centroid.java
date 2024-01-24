
package com.github.zacharydhamilton.objects;

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
 * The geometric center of the neighborhood.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lat",
    "lon"
})
public class Centroid {

    /**
     * The latitude of the centroid.
     * (Required)
     * 
     */
    @JsonProperty("lat")
    @JsonPropertyDescription("The latitude of the centroid.")
    private String lat;
    /**
     * The longitude of the centroid.
     * (Required)
     * 
     */
    @JsonProperty("lon")
    @JsonPropertyDescription("The longitude of the centroid.")
    private String lon;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * The latitude of the centroid.
     * (Required)
     * 
     */
    @JsonProperty("lat")
    public String getLat() {
        return lat;
    }

    /**
     * The latitude of the centroid.
     * (Required)
     * 
     */
    @JsonProperty("lat")
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * The longitude of the centroid.
     * (Required)
     * 
     */
    @JsonProperty("lon")
    public String getLon() {
        return lon;
    }

    /**
     * The longitude of the centroid.
     * (Required)
     * 
     */
    @JsonProperty("lon")
    public void setLon(String lon) {
        this.lon = lon;
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
        sb.append(Centroid.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("lat");
        sb.append('=');
        sb.append(((this.lat == null)?"<null>":this.lat));
        sb.append(',');
        sb.append("lon");
        sb.append('=');
        sb.append(((this.lon == null)?"<null>":this.lon));
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
        result = ((result* 31)+((this.lon == null)? 0 :this.lon.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.lat == null)? 0 :this.lat.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Centroid) == false) {
            return false;
        }
        Centroid rhs = ((Centroid) other);
        return ((((this.lon == rhs.lon)||((this.lon!= null)&&this.lon.equals(rhs.lon)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.lat == rhs.lat)||((this.lat!= null)&&this.lat.equals(rhs.lat))));
    }

}
