
package com.github.zacharydhamilton.objects.station_status;

import java.util.HashMap;
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
    "vehicle_type_id",
    "count"
})
public class VehicleTypesAvailable {

    /**
     * The vehicle_type_id of vehicle at the station (added in v2.1-RC).
     * (Required)
     * 
     */
    @JsonProperty("vehicle_type_id")
    @JsonPropertyDescription("The vehicle_type_id of vehicle at the station (added in v2.1-RC).")
    private String vehicleTypeId;
    /**
     * A number representing the total amount of this vehicle type at the station (added in v2.1-RC).
     * (Required)
     * 
     */
    @JsonProperty("count")
    @JsonPropertyDescription("A number representing the total amount of this vehicle type at the station (added in v2.1-RC).")
    private Integer count;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * The vehicle_type_id of vehicle at the station (added in v2.1-RC).
     * (Required)
     * 
     */
    @JsonProperty("vehicle_type_id")
    public String getVehicleTypeId() {
        return vehicleTypeId;
    }

    /**
     * The vehicle_type_id of vehicle at the station (added in v2.1-RC).
     * (Required)
     * 
     */
    @JsonProperty("vehicle_type_id")
    public void setVehicleTypeId(String vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    /**
     * A number representing the total amount of this vehicle type at the station (added in v2.1-RC).
     * (Required)
     * 
     */
    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    /**
     * A number representing the total amount of this vehicle type at the station (added in v2.1-RC).
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
        sb.append(VehicleTypesAvailable.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("vehicleTypeId");
        sb.append('=');
        sb.append(((this.vehicleTypeId == null)?"<null>":this.vehicleTypeId));
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
        result = ((result* 31)+((this.vehicleTypeId == null)? 0 :this.vehicleTypeId.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof VehicleTypesAvailable) == false) {
            return false;
        }
        VehicleTypesAvailable rhs = ((VehicleTypesAvailable) other);
        return ((((this.count == rhs.count)||((this.count!= null)&&this.count.equals(rhs.count)))&&((this.vehicleTypeId == rhs.vehicleTypeId)||((this.vehicleTypeId!= null)&&this.vehicleTypeId.equals(rhs.vehicleTypeId))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
