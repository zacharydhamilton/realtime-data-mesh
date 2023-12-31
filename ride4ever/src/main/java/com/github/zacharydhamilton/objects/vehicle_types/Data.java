
package com.github.zacharydhamilton.objects.vehicle_types;

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


/**
 * Response data in the form of name:value pairs.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "vehicle_types"
})
public class Data {

    /**
     * Array that contains one object per vehicle type in the system as defined below.
     * (Required)
     * 
     */
    @JsonProperty("vehicle_types")
    @JsonPropertyDescription("Array that contains one object per vehicle type in the system as defined below.")
    private List<VehicleType> vehicleTypes = new ArrayList<VehicleType>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Array that contains one object per vehicle type in the system as defined below.
     * (Required)
     * 
     */
    @JsonProperty("vehicle_types")
    public List<VehicleType> getVehicleTypes() {
        return vehicleTypes;
    }

    /**
     * Array that contains one object per vehicle type in the system as defined below.
     * (Required)
     * 
     */
    @JsonProperty("vehicle_types")
    public void setVehicleTypes(List<VehicleType> vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
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
        sb.append("vehicleTypes");
        sb.append('=');
        sb.append(((this.vehicleTypes == null)?"<null>":this.vehicleTypes));
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
        result = ((result* 31)+((this.vehicleTypes == null)? 0 :this.vehicleTypes.hashCode()));
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
        return (((this.vehicleTypes == rhs.vehicleTypes)||((this.vehicleTypes!= null)&&this.vehicleTypes.equals(rhs.vehicleTypes)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
