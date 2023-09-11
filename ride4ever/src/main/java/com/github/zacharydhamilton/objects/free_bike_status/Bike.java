
package com.github.zacharydhamilton.objects.free_bike_status;

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
    "bike_id",
    "lat",
    "lon",
    "is_reserved",
    "is_disabled",
    "rental_uris",
    "vehicle_type_id",
    "last_reported",
    "current_range_meters",
    "current_fuel_percent",
    "station_id",
    "home_station_id",
    "pricing_plan_id",
    "vehicle_equipment",
    "available_until"
})
public class Bike {

    /**
     * Rotating (as of v2.0) identifier of a vehicle.
     * (Required)
     * 
     */
    @JsonProperty("bike_id")
    @JsonPropertyDescription("Rotating (as of v2.0) identifier of a vehicle.")
    private String bikeId;
    /**
     * The latitude of the vehicle.
     * 
     */
    @JsonProperty("lat")
    @JsonPropertyDescription("The latitude of the vehicle.")
    private Double lat;
    /**
     * The longitude of the vehicle.
     * 
     */
    @JsonProperty("lon")
    @JsonPropertyDescription("The longitude of the vehicle.")
    private Double lon;
    /**
     * Is the vehicle currently reserved?
     * (Required)
     * 
     */
    @JsonProperty("is_reserved")
    @JsonPropertyDescription("Is the vehicle currently reserved?")
    private Boolean isReserved;
    /**
     * Is the vehicle currently disabled (broken)?
     * (Required)
     * 
     */
    @JsonProperty("is_disabled")
    @JsonPropertyDescription("Is the vehicle currently disabled (broken)?")
    private Boolean isDisabled;
    /**
     * Contains rental uris for Android, iOS, and web in the android, ios, and web fields (added in v1.1).
     * 
     */
    @JsonProperty("rental_uris")
    @JsonPropertyDescription("Contains rental uris for Android, iOS, and web in the android, ios, and web fields (added in v1.1).")
    private RentalUris rentalUris;
    /**
     * The vehicle_type_id of this vehicle (added in v2.1-RC).
     * 
     */
    @JsonProperty("vehicle_type_id")
    @JsonPropertyDescription("The vehicle_type_id of this vehicle (added in v2.1-RC).")
    private String vehicleTypeId;
    /**
     * The last time this vehicle reported its status to the operator's backend in POSIX time (added in v2.1-RC).
     * 
     */
    @JsonProperty("last_reported")
    @JsonPropertyDescription("The last time this vehicle reported its status to the operator's backend in POSIX time (added in v2.1-RC).")
    private Double lastReported;
    /**
     * The furthest distance in meters that the vehicle can travel without recharging or refueling with the vehicle's current charge or fuel (added in v2.1-RC).
     * 
     */
    @JsonProperty("current_range_meters")
    @JsonPropertyDescription("The furthest distance in meters that the vehicle can travel without recharging or refueling with the vehicle's current charge or fuel (added in v2.1-RC).")
    private Double currentRangeMeters;
    /**
     * This value represents the current percentage, expressed from 0 to 1, of fuel or battery power remaining in the vehicle. Added in v2.3-RC.
     * 
     */
    @JsonProperty("current_fuel_percent")
    @JsonPropertyDescription("This value represents the current percentage, expressed from 0 to 1, of fuel or battery power remaining in the vehicle. Added in v2.3-RC.")
    private Double currentFuelPercent;
    /**
     * Identifier referencing the station_id if the vehicle is currently at a station (added in v2.1-RC2).
     * 
     */
    @JsonProperty("station_id")
    @JsonPropertyDescription("Identifier referencing the station_id if the vehicle is currently at a station (added in v2.1-RC2).")
    private String stationId;
    /**
     * The station_id of the station this vehicle must be returned to (added in v2.3-RC).
     * 
     */
    @JsonProperty("home_station_id")
    @JsonPropertyDescription("The station_id of the station this vehicle must be returned to (added in v2.3-RC).")
    private String homeStationId;
    /**
     * The plan_id of the pricing plan this vehicle is eligible for (added in v2.2).
     * 
     */
    @JsonProperty("pricing_plan_id")
    @JsonPropertyDescription("The plan_id of the pricing plan this vehicle is eligible for (added in v2.2).")
    private String pricingPlanId;
    /**
     * List of vehicle equipment provided by the operator in addition to the accessories already provided in the vehicle. Added in v2.3.
     * 
     */
    @JsonProperty("vehicle_equipment")
    @JsonPropertyDescription("List of vehicle equipment provided by the operator in addition to the accessories already provided in the vehicle. Added in v2.3.")
    private List<VehicleEquipment> vehicleEquipment = new ArrayList<VehicleEquipment>();
    /**
     * The date and time when any rental of the vehicle must be completed. Added in v2.3.
     * 
     */
    @JsonProperty("available_until")
    @JsonPropertyDescription("The date and time when any rental of the vehicle must be completed. Added in v2.3.")
    private String availableUntil;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Rotating (as of v2.0) identifier of a vehicle.
     * (Required)
     * 
     */
    @JsonProperty("bike_id")
    public String getBikeId() {
        return bikeId;
    }

    /**
     * Rotating (as of v2.0) identifier of a vehicle.
     * (Required)
     * 
     */
    @JsonProperty("bike_id")
    public void setBikeId(String bikeId) {
        this.bikeId = bikeId;
    }

    /**
     * The latitude of the vehicle.
     * 
     */
    @JsonProperty("lat")
    public Double getLat() {
        return lat;
    }

    /**
     * The latitude of the vehicle.
     * 
     */
    @JsonProperty("lat")
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * The longitude of the vehicle.
     * 
     */
    @JsonProperty("lon")
    public Double getLon() {
        return lon;
    }

    /**
     * The longitude of the vehicle.
     * 
     */
    @JsonProperty("lon")
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * Is the vehicle currently reserved?
     * (Required)
     * 
     */
    @JsonProperty("is_reserved")
    public Boolean getIsReserved() {
        return isReserved;
    }

    /**
     * Is the vehicle currently reserved?
     * (Required)
     * 
     */
    @JsonProperty("is_reserved")
    public void setIsReserved(Boolean isReserved) {
        this.isReserved = isReserved;
    }

    /**
     * Is the vehicle currently disabled (broken)?
     * (Required)
     * 
     */
    @JsonProperty("is_disabled")
    public Boolean getIsDisabled() {
        return isDisabled;
    }

    /**
     * Is the vehicle currently disabled (broken)?
     * (Required)
     * 
     */
    @JsonProperty("is_disabled")
    public void setIsDisabled(Boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    /**
     * Contains rental uris for Android, iOS, and web in the android, ios, and web fields (added in v1.1).
     * 
     */
    @JsonProperty("rental_uris")
    public RentalUris getRentalUris() {
        return rentalUris;
    }

    /**
     * Contains rental uris for Android, iOS, and web in the android, ios, and web fields (added in v1.1).
     * 
     */
    @JsonProperty("rental_uris")
    public void setRentalUris(RentalUris rentalUris) {
        this.rentalUris = rentalUris;
    }

    /**
     * The vehicle_type_id of this vehicle (added in v2.1-RC).
     * 
     */
    @JsonProperty("vehicle_type_id")
    public String getVehicleTypeId() {
        return vehicleTypeId;
    }

    /**
     * The vehicle_type_id of this vehicle (added in v2.1-RC).
     * 
     */
    @JsonProperty("vehicle_type_id")
    public void setVehicleTypeId(String vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    /**
     * The last time this vehicle reported its status to the operator's backend in POSIX time (added in v2.1-RC).
     * 
     */
    @JsonProperty("last_reported")
    public Double getLastReported() {
        return lastReported;
    }

    /**
     * The last time this vehicle reported its status to the operator's backend in POSIX time (added in v2.1-RC).
     * 
     */
    @JsonProperty("last_reported")
    public void setLastReported(Double lastReported) {
        this.lastReported = lastReported;
    }

    /**
     * The furthest distance in meters that the vehicle can travel without recharging or refueling with the vehicle's current charge or fuel (added in v2.1-RC).
     * 
     */
    @JsonProperty("current_range_meters")
    public Double getCurrentRangeMeters() {
        return currentRangeMeters;
    }

    /**
     * The furthest distance in meters that the vehicle can travel without recharging or refueling with the vehicle's current charge or fuel (added in v2.1-RC).
     * 
     */
    @JsonProperty("current_range_meters")
    public void setCurrentRangeMeters(Double currentRangeMeters) {
        this.currentRangeMeters = currentRangeMeters;
    }

    /**
     * This value represents the current percentage, expressed from 0 to 1, of fuel or battery power remaining in the vehicle. Added in v2.3-RC.
     * 
     */
    @JsonProperty("current_fuel_percent")
    public Double getCurrentFuelPercent() {
        return currentFuelPercent;
    }

    /**
     * This value represents the current percentage, expressed from 0 to 1, of fuel or battery power remaining in the vehicle. Added in v2.3-RC.
     * 
     */
    @JsonProperty("current_fuel_percent")
    public void setCurrentFuelPercent(Double currentFuelPercent) {
        this.currentFuelPercent = currentFuelPercent;
    }

    /**
     * Identifier referencing the station_id if the vehicle is currently at a station (added in v2.1-RC2).
     * 
     */
    @JsonProperty("station_id")
    public String getStationId() {
        return stationId;
    }

    /**
     * Identifier referencing the station_id if the vehicle is currently at a station (added in v2.1-RC2).
     * 
     */
    @JsonProperty("station_id")
    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    /**
     * The station_id of the station this vehicle must be returned to (added in v2.3-RC).
     * 
     */
    @JsonProperty("home_station_id")
    public String getHomeStationId() {
        return homeStationId;
    }

    /**
     * The station_id of the station this vehicle must be returned to (added in v2.3-RC).
     * 
     */
    @JsonProperty("home_station_id")
    public void setHomeStationId(String homeStationId) {
        this.homeStationId = homeStationId;
    }

    /**
     * The plan_id of the pricing plan this vehicle is eligible for (added in v2.2).
     * 
     */
    @JsonProperty("pricing_plan_id")
    public String getPricingPlanId() {
        return pricingPlanId;
    }

    /**
     * The plan_id of the pricing plan this vehicle is eligible for (added in v2.2).
     * 
     */
    @JsonProperty("pricing_plan_id")
    public void setPricingPlanId(String pricingPlanId) {
        this.pricingPlanId = pricingPlanId;
    }

    /**
     * List of vehicle equipment provided by the operator in addition to the accessories already provided in the vehicle. Added in v2.3.
     * 
     */
    @JsonProperty("vehicle_equipment")
    public List<VehicleEquipment> getVehicleEquipment() {
        return vehicleEquipment;
    }

    /**
     * List of vehicle equipment provided by the operator in addition to the accessories already provided in the vehicle. Added in v2.3.
     * 
     */
    @JsonProperty("vehicle_equipment")
    public void setVehicleEquipment(List<VehicleEquipment> vehicleEquipment) {
        this.vehicleEquipment = vehicleEquipment;
    }

    /**
     * The date and time when any rental of the vehicle must be completed. Added in v2.3.
     * 
     */
    @JsonProperty("available_until")
    public String getAvailableUntil() {
        return availableUntil;
    }

    /**
     * The date and time when any rental of the vehicle must be completed. Added in v2.3.
     * 
     */
    @JsonProperty("available_until")
    public void setAvailableUntil(String availableUntil) {
        this.availableUntil = availableUntil;
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
        sb.append(Bike.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("bikeId");
        sb.append('=');
        sb.append(((this.bikeId == null)?"<null>":this.bikeId));
        sb.append(',');
        sb.append("lat");
        sb.append('=');
        sb.append(((this.lat == null)?"<null>":this.lat));
        sb.append(',');
        sb.append("lon");
        sb.append('=');
        sb.append(((this.lon == null)?"<null>":this.lon));
        sb.append(',');
        sb.append("isReserved");
        sb.append('=');
        sb.append(((this.isReserved == null)?"<null>":this.isReserved));
        sb.append(',');
        sb.append("isDisabled");
        sb.append('=');
        sb.append(((this.isDisabled == null)?"<null>":this.isDisabled));
        sb.append(',');
        sb.append("rentalUris");
        sb.append('=');
        sb.append(((this.rentalUris == null)?"<null>":this.rentalUris));
        sb.append(',');
        sb.append("vehicleTypeId");
        sb.append('=');
        sb.append(((this.vehicleTypeId == null)?"<null>":this.vehicleTypeId));
        sb.append(',');
        sb.append("lastReported");
        sb.append('=');
        sb.append(((this.lastReported == null)?"<null>":this.lastReported));
        sb.append(',');
        sb.append("currentRangeMeters");
        sb.append('=');
        sb.append(((this.currentRangeMeters == null)?"<null>":this.currentRangeMeters));
        sb.append(',');
        sb.append("currentFuelPercent");
        sb.append('=');
        sb.append(((this.currentFuelPercent == null)?"<null>":this.currentFuelPercent));
        sb.append(',');
        sb.append("stationId");
        sb.append('=');
        sb.append(((this.stationId == null)?"<null>":this.stationId));
        sb.append(',');
        sb.append("homeStationId");
        sb.append('=');
        sb.append(((this.homeStationId == null)?"<null>":this.homeStationId));
        sb.append(',');
        sb.append("pricingPlanId");
        sb.append('=');
        sb.append(((this.pricingPlanId == null)?"<null>":this.pricingPlanId));
        sb.append(',');
        sb.append("vehicleEquipment");
        sb.append('=');
        sb.append(((this.vehicleEquipment == null)?"<null>":this.vehicleEquipment));
        sb.append(',');
        sb.append("availableUntil");
        sb.append('=');
        sb.append(((this.availableUntil == null)?"<null>":this.availableUntil));
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
        result = ((result* 31)+((this.rentalUris == null)? 0 :this.rentalUris.hashCode()));
        result = ((result* 31)+((this.vehicleEquipment == null)? 0 :this.vehicleEquipment.hashCode()));
        result = ((result* 31)+((this.isReserved == null)? 0 :this.isReserved.hashCode()));
        result = ((result* 31)+((this.bikeId == null)? 0 :this.bikeId.hashCode()));
        result = ((result* 31)+((this.lon == null)? 0 :this.lon.hashCode()));
        result = ((result* 31)+((this.currentFuelPercent == null)? 0 :this.currentFuelPercent.hashCode()));
        result = ((result* 31)+((this.homeStationId == null)? 0 :this.homeStationId.hashCode()));
        result = ((result* 31)+((this.pricingPlanId == null)? 0 :this.pricingPlanId.hashCode()));
        result = ((result* 31)+((this.availableUntil == null)? 0 :this.availableUntil.hashCode()));
        result = ((result* 31)+((this.lastReported == null)? 0 :this.lastReported.hashCode()));
        result = ((result* 31)+((this.currentRangeMeters == null)? 0 :this.currentRangeMeters.hashCode()));
        result = ((result* 31)+((this.isDisabled == null)? 0 :this.isDisabled.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.lat == null)? 0 :this.lat.hashCode()));
        result = ((result* 31)+((this.stationId == null)? 0 :this.stationId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Bike) == false) {
            return false;
        }
        Bike rhs = ((Bike) other);
        return (((((((((((((((((this.vehicleTypeId == rhs.vehicleTypeId)||((this.vehicleTypeId!= null)&&this.vehicleTypeId.equals(rhs.vehicleTypeId)))&&((this.rentalUris == rhs.rentalUris)||((this.rentalUris!= null)&&this.rentalUris.equals(rhs.rentalUris))))&&((this.vehicleEquipment == rhs.vehicleEquipment)||((this.vehicleEquipment!= null)&&this.vehicleEquipment.equals(rhs.vehicleEquipment))))&&((this.isReserved == rhs.isReserved)||((this.isReserved!= null)&&this.isReserved.equals(rhs.isReserved))))&&((this.bikeId == rhs.bikeId)||((this.bikeId!= null)&&this.bikeId.equals(rhs.bikeId))))&&((this.lon == rhs.lon)||((this.lon!= null)&&this.lon.equals(rhs.lon))))&&((this.currentFuelPercent == rhs.currentFuelPercent)||((this.currentFuelPercent!= null)&&this.currentFuelPercent.equals(rhs.currentFuelPercent))))&&((this.homeStationId == rhs.homeStationId)||((this.homeStationId!= null)&&this.homeStationId.equals(rhs.homeStationId))))&&((this.pricingPlanId == rhs.pricingPlanId)||((this.pricingPlanId!= null)&&this.pricingPlanId.equals(rhs.pricingPlanId))))&&((this.availableUntil == rhs.availableUntil)||((this.availableUntil!= null)&&this.availableUntil.equals(rhs.availableUntil))))&&((this.lastReported == rhs.lastReported)||((this.lastReported!= null)&&this.lastReported.equals(rhs.lastReported))))&&((this.currentRangeMeters == rhs.currentRangeMeters)||((this.currentRangeMeters!= null)&&this.currentRangeMeters.equals(rhs.currentRangeMeters))))&&((this.isDisabled == rhs.isDisabled)||((this.isDisabled!= null)&&this.isDisabled.equals(rhs.isDisabled))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.lat == rhs.lat)||((this.lat!= null)&&this.lat.equals(rhs.lat))))&&((this.stationId == rhs.stationId)||((this.stationId!= null)&&this.stationId.equals(rhs.stationId))));
    }

}
