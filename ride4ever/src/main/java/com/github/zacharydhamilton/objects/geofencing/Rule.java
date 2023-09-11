
package com.github.zacharydhamilton.objects.geofencing;

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
    "vehicle_type_id",
    "ride_allowed",
    "ride_through_allowed",
    "maximum_speed_kph",
    "station_parking"
})
public class Rule {

    /**
     * Array of vehicle type IDs for which these restrictions apply.
     * 
     */
    @JsonProperty("vehicle_type_id")
    @JsonPropertyDescription("Array of vehicle type IDs for which these restrictions apply.")
    private List<String> vehicleTypeId = new ArrayList<String>();
    /**
     * Is the undocked ride allowed to start and end in this zone?
     * (Required)
     * 
     */
    @JsonProperty("ride_allowed")
    @JsonPropertyDescription("Is the undocked ride allowed to start and end in this zone?")
    private Boolean rideAllowed;
    /**
     * Is the ride allowed to travel through this zone?
     * (Required)
     * 
     */
    @JsonProperty("ride_through_allowed")
    @JsonPropertyDescription("Is the ride allowed to travel through this zone?")
    private Boolean rideThroughAllowed;
    /**
     * What is the maximum speed allowed, in kilometers per hour?
     * 
     */
    @JsonProperty("maximum_speed_kph")
    @JsonPropertyDescription("What is the maximum speed allowed, in kilometers per hour?")
    private Integer maximumSpeedKph;
    /**
     * Vehicle MUST be parked at stations defined in station_information.json within this geofence zone
     * 
     */
    @JsonProperty("station_parking")
    @JsonPropertyDescription("Vehicle MUST be parked at stations defined in station_information.json within this geofence zone")
    private Boolean stationParking;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Array of vehicle type IDs for which these restrictions apply.
     * 
     */
    @JsonProperty("vehicle_type_id")
    public List<String> getVehicleTypeId() {
        return vehicleTypeId;
    }

    /**
     * Array of vehicle type IDs for which these restrictions apply.
     * 
     */
    @JsonProperty("vehicle_type_id")
    public void setVehicleTypeId(List<String> vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    /**
     * Is the undocked ride allowed to start and end in this zone?
     * (Required)
     * 
     */
    @JsonProperty("ride_allowed")
    public Boolean getRideAllowed() {
        return rideAllowed;
    }

    /**
     * Is the undocked ride allowed to start and end in this zone?
     * (Required)
     * 
     */
    @JsonProperty("ride_allowed")
    public void setRideAllowed(Boolean rideAllowed) {
        this.rideAllowed = rideAllowed;
    }

    /**
     * Is the ride allowed to travel through this zone?
     * (Required)
     * 
     */
    @JsonProperty("ride_through_allowed")
    public Boolean getRideThroughAllowed() {
        return rideThroughAllowed;
    }

    /**
     * Is the ride allowed to travel through this zone?
     * (Required)
     * 
     */
    @JsonProperty("ride_through_allowed")
    public void setRideThroughAllowed(Boolean rideThroughAllowed) {
        this.rideThroughAllowed = rideThroughAllowed;
    }

    /**
     * What is the maximum speed allowed, in kilometers per hour?
     * 
     */
    @JsonProperty("maximum_speed_kph")
    public Integer getMaximumSpeedKph() {
        return maximumSpeedKph;
    }

    /**
     * What is the maximum speed allowed, in kilometers per hour?
     * 
     */
    @JsonProperty("maximum_speed_kph")
    public void setMaximumSpeedKph(Integer maximumSpeedKph) {
        this.maximumSpeedKph = maximumSpeedKph;
    }

    /**
     * Vehicle MUST be parked at stations defined in station_information.json within this geofence zone
     * 
     */
    @JsonProperty("station_parking")
    public Boolean getStationParking() {
        return stationParking;
    }

    /**
     * Vehicle MUST be parked at stations defined in station_information.json within this geofence zone
     * 
     */
    @JsonProperty("station_parking")
    public void setStationParking(Boolean stationParking) {
        this.stationParking = stationParking;
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
        sb.append(Rule.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("vehicleTypeId");
        sb.append('=');
        sb.append(((this.vehicleTypeId == null)?"<null>":this.vehicleTypeId));
        sb.append(',');
        sb.append("rideAllowed");
        sb.append('=');
        sb.append(((this.rideAllowed == null)?"<null>":this.rideAllowed));
        sb.append(',');
        sb.append("rideThroughAllowed");
        sb.append('=');
        sb.append(((this.rideThroughAllowed == null)?"<null>":this.rideThroughAllowed));
        sb.append(',');
        sb.append("maximumSpeedKph");
        sb.append('=');
        sb.append(((this.maximumSpeedKph == null)?"<null>":this.maximumSpeedKph));
        sb.append(',');
        sb.append("stationParking");
        sb.append('=');
        sb.append(((this.stationParking == null)?"<null>":this.stationParking));
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
        result = ((result* 31)+((this.vehicleTypeId == null)? 0 :this.vehicleTypeId.hashCode()));
        result = ((result* 31)+((this.rideThroughAllowed == null)? 0 :this.rideThroughAllowed.hashCode()));
        result = ((result* 31)+((this.maximumSpeedKph == null)? 0 :this.maximumSpeedKph.hashCode()));
        result = ((result* 31)+((this.stationParking == null)? 0 :this.stationParking.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.rideAllowed == null)? 0 :this.rideAllowed.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Rule) == false) {
            return false;
        }
        Rule rhs = ((Rule) other);
        return (((((((this.vehicleTypeId == rhs.vehicleTypeId)||((this.vehicleTypeId!= null)&&this.vehicleTypeId.equals(rhs.vehicleTypeId)))&&((this.rideThroughAllowed == rhs.rideThroughAllowed)||((this.rideThroughAllowed!= null)&&this.rideThroughAllowed.equals(rhs.rideThroughAllowed))))&&((this.maximumSpeedKph == rhs.maximumSpeedKph)||((this.maximumSpeedKph!= null)&&this.maximumSpeedKph.equals(rhs.maximumSpeedKph))))&&((this.stationParking == rhs.stationParking)||((this.stationParking!= null)&&this.stationParking.equals(rhs.stationParking))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.rideAllowed == rhs.rideAllowed)||((this.rideAllowed!= null)&&this.rideAllowed.equals(rhs.rideAllowed))));
    }

}
