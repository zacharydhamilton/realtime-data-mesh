
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
    "station_id",
    "num_bikes_available",
    "vehicle_types_available",
    "num_bikes_disabled",
    "num_docks_available",
    "num_docks_disabled",
    "is_installed",
    "is_renting",
    "is_returning",
    "last_reported",
    "vehicle_docks_available"
})
public class Station {

    /**
     * Identifier of a station.
     * (Required)
     * 
     */
    @JsonProperty("station_id")
    @JsonPropertyDescription("Identifier of a station.")
    private String stationId;
    /**
     * Number of vehicles of any type physically available for rental at the station.
     * (Required)
     * 
     */
    @JsonProperty("num_bikes_available")
    @JsonPropertyDescription("Number of vehicles of any type physically available for rental at the station.")
    private Integer numBikesAvailable;
    /**
     * Array of objects displaying the total number of each vehicle type at the station (added in v2.1-RC).
     * 
     */
    @JsonProperty("vehicle_types_available")
    @JsonPropertyDescription("Array of objects displaying the total number of each vehicle type at the station (added in v2.1-RC).")
    private List<VehicleTypesAvailable> vehicleTypesAvailable = new ArrayList<VehicleTypesAvailable>();
    /**
     * Number of disabled vehicles of any type at the station.
     * 
     */
    @JsonProperty("num_bikes_disabled")
    @JsonPropertyDescription("Number of disabled vehicles of any type at the station.")
    private Integer numBikesDisabled;
    /**
     * Number of functional docks physically at the station.
     * 
     */
    @JsonProperty("num_docks_available")
    @JsonPropertyDescription("Number of functional docks physically at the station.")
    private Integer numDocksAvailable;
    /**
     * Number of empty but disabled docks at the station.
     * 
     */
    @JsonProperty("num_docks_disabled")
    @JsonPropertyDescription("Number of empty but disabled docks at the station.")
    private Integer numDocksDisabled;
    /**
     * Is the station currently on the street?
     * (Required)
     * 
     */
    @JsonProperty("is_installed")
    @JsonPropertyDescription("Is the station currently on the street?")
    private Boolean isInstalled;
    /**
     * Is the station currently renting vehicles?
     * (Required)
     * 
     */
    @JsonProperty("is_renting")
    @JsonPropertyDescription("Is the station currently renting vehicles?")
    private Boolean isRenting;
    /**
     * Is the station accepting vehicle returns?
     * (Required)
     * 
     */
    @JsonProperty("is_returning")
    @JsonPropertyDescription("Is the station accepting vehicle returns?")
    private Boolean isReturning;
    /**
     * The last time this station reported its status to the operator's backend in POSIX time.
     * (Required)
     * 
     */
    @JsonProperty("last_reported")
    @JsonPropertyDescription("The last time this station reported its status to the operator's backend in POSIX time.")
    private Integer lastReported;
    /**
     * Object displaying available docks by vehicle type (added in v2.1-RC).
     * 
     */
    @JsonProperty("vehicle_docks_available")
    @JsonPropertyDescription("Object displaying available docks by vehicle type (added in v2.1-RC).")
    private List<VehicleDocksAvailable> vehicleDocksAvailable = new ArrayList<VehicleDocksAvailable>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Identifier of a station.
     * (Required)
     * 
     */
    @JsonProperty("station_id")
    public String getStationId() {
        return stationId;
    }

    /**
     * Identifier of a station.
     * (Required)
     * 
     */
    @JsonProperty("station_id")
    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    /**
     * Number of vehicles of any type physically available for rental at the station.
     * (Required)
     * 
     */
    @JsonProperty("num_bikes_available")
    public Integer getNumBikesAvailable() {
        return numBikesAvailable;
    }

    /**
     * Number of vehicles of any type physically available for rental at the station.
     * (Required)
     * 
     */
    @JsonProperty("num_bikes_available")
    public void setNumBikesAvailable(Integer numBikesAvailable) {
        this.numBikesAvailable = numBikesAvailable;
    }

    /**
     * Array of objects displaying the total number of each vehicle type at the station (added in v2.1-RC).
     * 
     */
    @JsonProperty("vehicle_types_available")
    public List<VehicleTypesAvailable> getVehicleTypesAvailable() {
        return vehicleTypesAvailable;
    }

    /**
     * Array of objects displaying the total number of each vehicle type at the station (added in v2.1-RC).
     * 
     */
    @JsonProperty("vehicle_types_available")
    public void setVehicleTypesAvailable(List<VehicleTypesAvailable> vehicleTypesAvailable) {
        this.vehicleTypesAvailable = vehicleTypesAvailable;
    }

    /**
     * Number of disabled vehicles of any type at the station.
     * 
     */
    @JsonProperty("num_bikes_disabled")
    public Integer getNumBikesDisabled() {
        return numBikesDisabled;
    }

    /**
     * Number of disabled vehicles of any type at the station.
     * 
     */
    @JsonProperty("num_bikes_disabled")
    public void setNumBikesDisabled(Integer numBikesDisabled) {
        this.numBikesDisabled = numBikesDisabled;
    }

    /**
     * Number of functional docks physically at the station.
     * 
     */
    @JsonProperty("num_docks_available")
    public Integer getNumDocksAvailable() {
        return numDocksAvailable;
    }

    /**
     * Number of functional docks physically at the station.
     * 
     */
    @JsonProperty("num_docks_available")
    public void setNumDocksAvailable(Integer numDocksAvailable) {
        this.numDocksAvailable = numDocksAvailable;
    }

    /**
     * Number of empty but disabled docks at the station.
     * 
     */
    @JsonProperty("num_docks_disabled")
    public Integer getNumDocksDisabled() {
        return numDocksDisabled;
    }

    /**
     * Number of empty but disabled docks at the station.
     * 
     */
    @JsonProperty("num_docks_disabled")
    public void setNumDocksDisabled(Integer numDocksDisabled) {
        this.numDocksDisabled = numDocksDisabled;
    }

    /**
     * Is the station currently on the street?
     * (Required)
     * 
     */
    @JsonProperty("is_installed")
    public Boolean getIsInstalled() {
        return isInstalled;
    }

    /**
     * Is the station currently on the street?
     * (Required)
     * 
     */
    @JsonProperty("is_installed")
    public void setIsInstalled(Boolean isInstalled) {
        this.isInstalled = isInstalled;
    }

    /**
     * Is the station currently renting vehicles?
     * (Required)
     * 
     */
    @JsonProperty("is_renting")
    public Boolean getIsRenting() {
        return isRenting;
    }

    /**
     * Is the station currently renting vehicles?
     * (Required)
     * 
     */
    @JsonProperty("is_renting")
    public void setIsRenting(Boolean isRenting) {
        this.isRenting = isRenting;
    }

    /**
     * Is the station accepting vehicle returns?
     * (Required)
     * 
     */
    @JsonProperty("is_returning")
    public Boolean getIsReturning() {
        return isReturning;
    }

    /**
     * Is the station accepting vehicle returns?
     * (Required)
     * 
     */
    @JsonProperty("is_returning")
    public void setIsReturning(Boolean isReturning) {
        this.isReturning = isReturning;
    }

    /**
     * The last time this station reported its status to the operator's backend in POSIX time.
     * (Required)
     * 
     */
    @JsonProperty("last_reported")
    public Integer getLastReported() {
        return lastReported;
    }

    /**
     * The last time this station reported its status to the operator's backend in POSIX time.
     * (Required)
     * 
     */
    @JsonProperty("last_reported")
    public void setLastReported(Integer lastReported) {
        this.lastReported = lastReported;
    }

    /**
     * Object displaying available docks by vehicle type (added in v2.1-RC).
     * 
     */
    @JsonProperty("vehicle_docks_available")
    public List<VehicleDocksAvailable> getVehicleDocksAvailable() {
        return vehicleDocksAvailable;
    }

    /**
     * Object displaying available docks by vehicle type (added in v2.1-RC).
     * 
     */
    @JsonProperty("vehicle_docks_available")
    public void setVehicleDocksAvailable(List<VehicleDocksAvailable> vehicleDocksAvailable) {
        this.vehicleDocksAvailable = vehicleDocksAvailable;
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
        sb.append(Station.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("stationId");
        sb.append('=');
        sb.append(((this.stationId == null)?"<null>":this.stationId));
        sb.append(',');
        sb.append("numBikesAvailable");
        sb.append('=');
        sb.append(((this.numBikesAvailable == null)?"<null>":this.numBikesAvailable));
        sb.append(',');
        sb.append("vehicleTypesAvailable");
        sb.append('=');
        sb.append(((this.vehicleTypesAvailable == null)?"<null>":this.vehicleTypesAvailable));
        sb.append(',');
        sb.append("numBikesDisabled");
        sb.append('=');
        sb.append(((this.numBikesDisabled == null)?"<null>":this.numBikesDisabled));
        sb.append(',');
        sb.append("numDocksAvailable");
        sb.append('=');
        sb.append(((this.numDocksAvailable == null)?"<null>":this.numDocksAvailable));
        sb.append(',');
        sb.append("numDocksDisabled");
        sb.append('=');
        sb.append(((this.numDocksDisabled == null)?"<null>":this.numDocksDisabled));
        sb.append(',');
        sb.append("isInstalled");
        sb.append('=');
        sb.append(((this.isInstalled == null)?"<null>":this.isInstalled));
        sb.append(',');
        sb.append("isRenting");
        sb.append('=');
        sb.append(((this.isRenting == null)?"<null>":this.isRenting));
        sb.append(',');
        sb.append("isReturning");
        sb.append('=');
        sb.append(((this.isReturning == null)?"<null>":this.isReturning));
        sb.append(',');
        sb.append("lastReported");
        sb.append('=');
        sb.append(((this.lastReported == null)?"<null>":this.lastReported));
        sb.append(',');
        sb.append("vehicleDocksAvailable");
        sb.append('=');
        sb.append(((this.vehicleDocksAvailable == null)?"<null>":this.vehicleDocksAvailable));
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
        result = ((result* 31)+((this.numDocksAvailable == null)? 0 :this.numDocksAvailable.hashCode()));
        result = ((result* 31)+((this.isInstalled == null)? 0 :this.isInstalled.hashCode()));
        result = ((result* 31)+((this.isReturning == null)? 0 :this.isReturning.hashCode()));
        result = ((result* 31)+((this.isRenting == null)? 0 :this.isRenting.hashCode()));
        result = ((result* 31)+((this.numDocksDisabled == null)? 0 :this.numDocksDisabled.hashCode()));
        result = ((result* 31)+((this.vehicleTypesAvailable == null)? 0 :this.vehicleTypesAvailable.hashCode()));
        result = ((result* 31)+((this.numBikesAvailable == null)? 0 :this.numBikesAvailable.hashCode()));
        result = ((result* 31)+((this.lastReported == null)? 0 :this.lastReported.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.vehicleDocksAvailable == null)? 0 :this.vehicleDocksAvailable.hashCode()));
        result = ((result* 31)+((this.stationId == null)? 0 :this.stationId.hashCode()));
        result = ((result* 31)+((this.numBikesDisabled == null)? 0 :this.numBikesDisabled.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Station) == false) {
            return false;
        }
        Station rhs = ((Station) other);
        return (((((((((((((this.numDocksAvailable == rhs.numDocksAvailable)||((this.numDocksAvailable!= null)&&this.numDocksAvailable.equals(rhs.numDocksAvailable)))&&((this.isInstalled == rhs.isInstalled)||((this.isInstalled!= null)&&this.isInstalled.equals(rhs.isInstalled))))&&((this.isReturning == rhs.isReturning)||((this.isReturning!= null)&&this.isReturning.equals(rhs.isReturning))))&&((this.isRenting == rhs.isRenting)||((this.isRenting!= null)&&this.isRenting.equals(rhs.isRenting))))&&((this.numDocksDisabled == rhs.numDocksDisabled)||((this.numDocksDisabled!= null)&&this.numDocksDisabled.equals(rhs.numDocksDisabled))))&&((this.vehicleTypesAvailable == rhs.vehicleTypesAvailable)||((this.vehicleTypesAvailable!= null)&&this.vehicleTypesAvailable.equals(rhs.vehicleTypesAvailable))))&&((this.numBikesAvailable == rhs.numBikesAvailable)||((this.numBikesAvailable!= null)&&this.numBikesAvailable.equals(rhs.numBikesAvailable))))&&((this.lastReported == rhs.lastReported)||((this.lastReported!= null)&&this.lastReported.equals(rhs.lastReported))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.vehicleDocksAvailable == rhs.vehicleDocksAvailable)||((this.vehicleDocksAvailable!= null)&&this.vehicleDocksAvailable.equals(rhs.vehicleDocksAvailable))))&&((this.stationId == rhs.stationId)||((this.stationId!= null)&&this.stationId.equals(rhs.stationId))))&&((this.numBikesDisabled == rhs.numBikesDisabled)||((this.numBikesDisabled!= null)&&this.numBikesDisabled.equals(rhs.numBikesDisabled))));
    }

}
