
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
 * station_neighborhood
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "region_id",
    "station_id",
    "zone",
    "neighborhood",
    "area_sq_ft",
    "centroid"
})
public class StationNeighborhood {

    /**
     * Identifier of the region where the station is located.
     * 
     */
    @JsonProperty("region_id")
    @JsonPropertyDescription("Identifier of the region where the station is located.")
    private String regionId;
    /**
     * Unique identifier for the station.
     * (Required)
     * 
     */
    @JsonProperty("station_id")
    @JsonPropertyDescription("Unique identifier for the station.")
    private String stationId;
    /**
     * The larger area within a city that encompasses a collection of neighborhoods.
     * (Required)
     * 
     */
    @JsonProperty("zone")
    @JsonPropertyDescription("The larger area within a city that encompasses a collection of neighborhoods.")
    private String zone;
    /**
     * The specific neighborhood within the zone.
     * (Required)
     * 
     */
    @JsonProperty("neighborhood")
    @JsonPropertyDescription("The specific neighborhood within the zone.")
    private String neighborhood;
    /**
     * The area of the neighborhood in square feet.
     * (Required)
     * 
     */
    @JsonProperty("area_sq_ft")
    @JsonPropertyDescription("The area of the neighborhood in square feet.")
    private String areaSqFt;
    /**
     * The geometric center of the neighborhood.
     * (Required)
     * 
     */
    @JsonProperty("centroid")
    @JsonPropertyDescription("The geometric center of the neighborhood.")
    private Centroid centroid;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Identifier of the region where the station is located.
     * 
     */
    @JsonProperty("region_id")
    public String getRegionId() {
        return regionId;
    }

    /**
     * Identifier of the region where the station is located.
     * 
     */
    @JsonProperty("region_id")
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    /**
     * Unique identifier for the station.
     * (Required)
     * 
     */
    @JsonProperty("station_id")
    public String getStationId() {
        return stationId;
    }

    /**
     * Unique identifier for the station.
     * (Required)
     * 
     */
    @JsonProperty("station_id")
    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    /**
     * The larger area within a city that encompasses a collection of neighborhoods.
     * (Required)
     * 
     */
    @JsonProperty("zone")
    public String getZone() {
        return zone;
    }

    /**
     * The larger area within a city that encompasses a collection of neighborhoods.
     * (Required)
     * 
     */
    @JsonProperty("zone")
    public void setZone(String zone) {
        this.zone = zone;
    }

    /**
     * The specific neighborhood within the zone.
     * (Required)
     * 
     */
    @JsonProperty("neighborhood")
    public String getNeighborhood() {
        return neighborhood;
    }

    /**
     * The specific neighborhood within the zone.
     * (Required)
     * 
     */
    @JsonProperty("neighborhood")
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    /**
     * The area of the neighborhood in square feet.
     * (Required)
     * 
     */
    @JsonProperty("area_sq_ft")
    public String getAreaSqFt() {
        return areaSqFt;
    }

    /**
     * The area of the neighborhood in square feet.
     * (Required)
     * 
     */
    @JsonProperty("area_sq_ft")
    public void setAreaSqFt(String areaSqFt) {
        this.areaSqFt = areaSqFt;
    }

    /**
     * The geometric center of the neighborhood.
     * (Required)
     * 
     */
    @JsonProperty("centroid")
    public Centroid getCentroid() {
        return centroid;
    }

    /**
     * The geometric center of the neighborhood.
     * (Required)
     * 
     */
    @JsonProperty("centroid")
    public void setCentroid(Centroid centroid) {
        this.centroid = centroid;
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
        sb.append(StationNeighborhood.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("regionId");
        sb.append('=');
        sb.append(((this.regionId == null)?"<null>":this.regionId));
        sb.append(',');
        sb.append("stationId");
        sb.append('=');
        sb.append(((this.stationId == null)?"<null>":this.stationId));
        sb.append(',');
        sb.append("zone");
        sb.append('=');
        sb.append(((this.zone == null)?"<null>":this.zone));
        sb.append(',');
        sb.append("neighborhood");
        sb.append('=');
        sb.append(((this.neighborhood == null)?"<null>":this.neighborhood));
        sb.append(',');
        sb.append("areaSqFt");
        sb.append('=');
        sb.append(((this.areaSqFt == null)?"<null>":this.areaSqFt));
        sb.append(',');
        sb.append("centroid");
        sb.append('=');
        sb.append(((this.centroid == null)?"<null>":this.centroid));
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
        result = ((result* 31)+((this.regionId == null)? 0 :this.regionId.hashCode()));
        result = ((result* 31)+((this.zone == null)? 0 :this.zone.hashCode()));
        result = ((result* 31)+((this.centroid == null)? 0 :this.centroid.hashCode()));
        result = ((result* 31)+((this.areaSqFt == null)? 0 :this.areaSqFt.hashCode()));
        result = ((result* 31)+((this.neighborhood == null)? 0 :this.neighborhood.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.stationId == null)? 0 :this.stationId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof StationNeighborhood) == false) {
            return false;
        }
        StationNeighborhood rhs = ((StationNeighborhood) other);
        return ((((((((this.regionId == rhs.regionId)||((this.regionId!= null)&&this.regionId.equals(rhs.regionId)))&&((this.zone == rhs.zone)||((this.zone!= null)&&this.zone.equals(rhs.zone))))&&((this.centroid == rhs.centroid)||((this.centroid!= null)&&this.centroid.equals(rhs.centroid))))&&((this.areaSqFt == rhs.areaSqFt)||((this.areaSqFt!= null)&&this.areaSqFt.equals(rhs.areaSqFt))))&&((this.neighborhood == rhs.neighborhood)||((this.neighborhood!= null)&&this.neighborhood.equals(rhs.neighborhood))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.stationId == rhs.stationId)||((this.stationId!= null)&&this.stationId.equals(rhs.stationId))));
    }

}
