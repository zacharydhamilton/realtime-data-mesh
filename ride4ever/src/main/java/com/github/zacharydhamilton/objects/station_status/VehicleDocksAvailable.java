
package com.github.zacharydhamilton.objects.station_status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "vehicle_type_ids",
    "count"
})
public class VehicleDocksAvailable {

    /**
     * An array of strings where each string represents a vehicle_type_id that is able to use a particular type of dock at the station (added in v2.1-RC).
     * (Required)
     * 
     */
    @JsonProperty("vehicle_type_ids")
    @JsonPropertyDescription("An array of strings where each string represents a vehicle_type_id that is able to use a particular type of dock at the station (added in v2.1-RC).")
    private List<String> vehicleTypeIds = new ArrayList<String>();
    /**
     * A number representing the total number of available docks for the defined vehicle type (added in v2.1-RC).
     * (Required)
     * 
     */
    @JsonProperty("count")
    @JsonPropertyDescription("A number representing the total number of available docks for the defined vehicle type (added in v2.1-RC).")
    private Integer count;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * An array of strings where each string represents a vehicle_type_id that is able to use a particular type of dock at the station (added in v2.1-RC).
     * (Required)
     * 
     */
    @JsonProperty("vehicle_type_ids")
    public List<String> getVehicleTypeIds() {
        return vehicleTypeIds;
    }

    /**
     * An array of strings where each string represents a vehicle_type_id that is able to use a particular type of dock at the station (added in v2.1-RC).
     * (Required)
     * 
     */
    @JsonProperty("vehicle_type_ids")
    public void setVehicleTypeIds(List<String> vehicleTypeIds) {
        this.vehicleTypeIds = vehicleTypeIds;
    }

    /**
     * A number representing the total number of available docks for the defined vehicle type (added in v2.1-RC).
     * (Required)
     * 
     */
    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    /**
     * A number representing the total number of available docks for the defined vehicle type (added in v2.1-RC).
     * (Required)
     * 
     */
    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
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
        sb.append(VehicleDocksAvailable.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("vehicleTypeIds");
        sb.append('=');
        sb.append(((this.vehicleTypeIds == null)?"<null>":this.vehicleTypeIds));
        sb.append(',');
        sb.append("count");
        sb.append('=');
        sb.append(((this.count == null)?"<null>":this.count));
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
        result = ((result* 31)+((this.count == null)? 0 :this.count.hashCode()));
        result = ((result* 31)+((this.vehicleTypeIds == null)? 0 :this.vehicleTypeIds.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof VehicleDocksAvailable) == false) {
            return false;
        }
        VehicleDocksAvailable rhs = ((VehicleDocksAvailable) other);
        return ((((this.count == rhs.count)||((this.count!= null)&&this.count.equals(rhs.count)))&&((this.vehicleTypeIds == rhs.vehicleTypeIds)||((this.vehicleTypeIds!= null)&&this.vehicleTypeIds.equals(rhs.vehicleTypeIds))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
