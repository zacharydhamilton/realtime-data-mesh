
package com.github.zacharydhamilton.objects.vehicle_types;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "vehicle_type_id",
    "form_factor",
    "rider_capacity",
    "cargo_volume_capacity",
    "cargo_load_capacity",
    "propulsion_type",
    "eco_label",
    "max_range_meters",
    "name",
    "vehicle_accessories",
    "g_CO2_km",
    "vehicle_image",
    "make",
    "model",
    "color",
    "wheel_count",
    "max_permitted_speed",
    "rated_power",
    "default_reserve_time",
    "return_constraint",
    "vehicle_assets",
    "default_pricing_plan_id",
    "pricing_plan_ids"
})
public class VehicleType {

    /**
     * Unique identifier of a vehicle type.
     * (Required)
     * 
     */
    @JsonProperty("vehicle_type_id")
    @JsonPropertyDescription("Unique identifier of a vehicle type.")
    private String vehicleTypeId;
    /**
     * The vehicle's general form factor.
     * (Required)
     * 
     */
    @JsonProperty("form_factor")
    @JsonPropertyDescription("The vehicle's general form factor.")
    private VehicleType.FormFactor formFactor;
    /**
     * The number of riders (driver included) the vehicle can legally accommodate
     * 
     */
    @JsonProperty("rider_capacity")
    @JsonPropertyDescription("The number of riders (driver included) the vehicle can legally accommodate")
    private Integer riderCapacity;
    /**
     * Cargo volume available in the vehicle, expressed in liters.
     * 
     */
    @JsonProperty("cargo_volume_capacity")
    @JsonPropertyDescription("Cargo volume available in the vehicle, expressed in liters.")
    private Integer cargoVolumeCapacity;
    /**
     * The capacity of the vehicle cargo space (excluding passengers), expressed in kilograms.
     * 
     */
    @JsonProperty("cargo_load_capacity")
    @JsonPropertyDescription("The capacity of the vehicle cargo space (excluding passengers), expressed in kilograms.")
    private Integer cargoLoadCapacity;
    /**
     * The primary propulsion type of the vehicle. Updated in v2.3 to represent car-sharing
     * (Required)
     * 
     */
    @JsonProperty("propulsion_type")
    @JsonPropertyDescription("The primary propulsion type of the vehicle. Updated in v2.3 to represent car-sharing")
    private VehicleType.PropulsionType propulsionType;
    /**
     * Vehicle air quality certificate. added in v2.3.
     * 
     */
    @JsonProperty("eco_label")
    @JsonPropertyDescription("Vehicle air quality certificate. added in v2.3.")
    private List<EcoLabel> ecoLabel = new ArrayList<EcoLabel>();
    /**
     * The furthest distance in meters that the vehicle can travel without recharging or refueling when it has the maximum amount of energy potential.
     * 
     */
    @JsonProperty("max_range_meters")
    @JsonPropertyDescription("The furthest distance in meters that the vehicle can travel without recharging or refueling when it has the maximum amount of energy potential.")
    private Double maxRangeMeters;
    /**
     * The public name of this vehicle type.
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("The public name of this vehicle type.")
    private String name;
    /**
     * Description of accessories available in the vehicle.
     * 
     */
    @JsonProperty("vehicle_accessories")
    @JsonPropertyDescription("Description of accessories available in the vehicle.")
    private List<VehicleAccessory> vehicleAccessories = new ArrayList<VehicleAccessory>();
    /**
     * Maximum quantity of CO2, in grams, emitted per kilometer, according to the WLTP. Added in v2.3
     * 
     */
    @JsonProperty("g_CO2_km")
    @JsonPropertyDescription("Maximum quantity of CO2, in grams, emitted per kilometer, according to the WLTP. Added in v2.3")
    private Integer gCO2Km;
    /**
     * URL to an image that would assist the user in identifying the vehicle. JPEG or PNG. Added in v2.3
     * 
     */
    @JsonProperty("vehicle_image")
    @JsonPropertyDescription("URL to an image that would assist the user in identifying the vehicle. JPEG or PNG. Added in v2.3")
    private URI vehicleImage;
    /**
     * The name of the vehicle manufacturer. Added in v2.3
     * 
     */
    @JsonProperty("make")
    @JsonPropertyDescription("The name of the vehicle manufacturer. Added in v2.3")
    private String make;
    /**
     * The name of the vehicle model. Added in v2.3
     * 
     */
    @JsonProperty("model")
    @JsonPropertyDescription("The name of the vehicle model. Added in v2.3")
    private String model;
    /**
     * The color of the vehicle. Added in v2.3
     * 
     */
    @JsonProperty("color")
    @JsonPropertyDescription("The color of the vehicle. Added in v2.3")
    private String color;
    /**
     * Number of wheels this vehicle type has. Added in v2.3
     * 
     */
    @JsonProperty("wheel_count")
    @JsonPropertyDescription("Number of wheels this vehicle type has. Added in v2.3")
    private Integer wheelCount;
    /**
     * The maximum speed in kilometers per hour this vehicle is permitted to reach in accordance with local permit and regulations. Added in v2.3
     * 
     */
    @JsonProperty("max_permitted_speed")
    @JsonPropertyDescription("The maximum speed in kilometers per hour this vehicle is permitted to reach in accordance with local permit and regulations. Added in v2.3")
    private Integer maxPermittedSpeed;
    /**
     * The rated power of the motor for this vehicle type in watts. Added in v2.3
     * 
     */
    @JsonProperty("rated_power")
    @JsonPropertyDescription("The rated power of the motor for this vehicle type in watts. Added in v2.3")
    private Integer ratedPower;
    /**
     * Maximum time in minutes that a vehicle can be reserved before a rental begins added in v2.3-RC.
     * 
     */
    @JsonProperty("default_reserve_time")
    @JsonPropertyDescription("Maximum time in minutes that a vehicle can be reserved before a rental begins added in v2.3-RC.")
    private Integer defaultReserveTime;
    /**
     * The conditions for returning the vehicle at the end of the trip. Added in v2.3-RC as return_type, and updated to return_constraint in v2.3.
     * 
     */
    @JsonProperty("return_constraint")
    @JsonPropertyDescription("The conditions for returning the vehicle at the end of the trip. Added in v2.3-RC as return_type, and updated to return_constraint in v2.3.")
    private VehicleType.ReturnConstraint returnConstraint;
    /**
     * An object where each key defines one of the items listed below added in v2.3-RC.
     * 
     */
    @JsonProperty("vehicle_assets")
    @JsonPropertyDescription("An object where each key defines one of the items listed below added in v2.3-RC.")
    private VehicleAssets vehicleAssets;
    /**
     * A plan_id as defined in system_pricing_plans.json added in v2.3-RC.
     * 
     */
    @JsonProperty("default_pricing_plan_id")
    @JsonPropertyDescription("A plan_id as defined in system_pricing_plans.json added in v2.3-RC.")
    private String defaultPricingPlanId;
    /**
     * Array of all pricing plan IDs as defined in system_pricing_plans.json added in v2.3-RC.
     * 
     */
    @JsonProperty("pricing_plan_ids")
    @JsonPropertyDescription("Array of all pricing plan IDs as defined in system_pricing_plans.json added in v2.3-RC.")
    private List<String> pricingPlanIds = new ArrayList<String>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Unique identifier of a vehicle type.
     * (Required)
     * 
     */
    @JsonProperty("vehicle_type_id")
    public String getVehicleTypeId() {
        return vehicleTypeId;
    }

    /**
     * Unique identifier of a vehicle type.
     * (Required)
     * 
     */
    @JsonProperty("vehicle_type_id")
    public void setVehicleTypeId(String vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    /**
     * The vehicle's general form factor.
     * (Required)
     * 
     */
    @JsonProperty("form_factor")
    public VehicleType.FormFactor getFormFactor() {
        return formFactor;
    }

    /**
     * The vehicle's general form factor.
     * (Required)
     * 
     */
    @JsonProperty("form_factor")
    public void setFormFactor(VehicleType.FormFactor formFactor) {
        this.formFactor = formFactor;
    }

    /**
     * The number of riders (driver included) the vehicle can legally accommodate
     * 
     */
    @JsonProperty("rider_capacity")
    public Integer getRiderCapacity() {
        return riderCapacity;
    }

    /**
     * The number of riders (driver included) the vehicle can legally accommodate
     * 
     */
    @JsonProperty("rider_capacity")
    public void setRiderCapacity(Integer riderCapacity) {
        this.riderCapacity = riderCapacity;
    }

    /**
     * Cargo volume available in the vehicle, expressed in liters.
     * 
     */
    @JsonProperty("cargo_volume_capacity")
    public Integer getCargoVolumeCapacity() {
        return cargoVolumeCapacity;
    }

    /**
     * Cargo volume available in the vehicle, expressed in liters.
     * 
     */
    @JsonProperty("cargo_volume_capacity")
    public void setCargoVolumeCapacity(Integer cargoVolumeCapacity) {
        this.cargoVolumeCapacity = cargoVolumeCapacity;
    }

    /**
     * The capacity of the vehicle cargo space (excluding passengers), expressed in kilograms.
     * 
     */
    @JsonProperty("cargo_load_capacity")
    public Integer getCargoLoadCapacity() {
        return cargoLoadCapacity;
    }

    /**
     * The capacity of the vehicle cargo space (excluding passengers), expressed in kilograms.
     * 
     */
    @JsonProperty("cargo_load_capacity")
    public void setCargoLoadCapacity(Integer cargoLoadCapacity) {
        this.cargoLoadCapacity = cargoLoadCapacity;
    }

    /**
     * The primary propulsion type of the vehicle. Updated in v2.3 to represent car-sharing
     * (Required)
     * 
     */
    @JsonProperty("propulsion_type")
    public VehicleType.PropulsionType getPropulsionType() {
        return propulsionType;
    }

    /**
     * The primary propulsion type of the vehicle. Updated in v2.3 to represent car-sharing
     * (Required)
     * 
     */
    @JsonProperty("propulsion_type")
    public void setPropulsionType(VehicleType.PropulsionType propulsionType) {
        this.propulsionType = propulsionType;
    }

    /**
     * Vehicle air quality certificate. added in v2.3.
     * 
     */
    @JsonProperty("eco_label")
    public List<EcoLabel> getEcoLabel() {
        return ecoLabel;
    }

    /**
     * Vehicle air quality certificate. added in v2.3.
     * 
     */
    @JsonProperty("eco_label")
    public void setEcoLabel(List<EcoLabel> ecoLabel) {
        this.ecoLabel = ecoLabel;
    }

    /**
     * The furthest distance in meters that the vehicle can travel without recharging or refueling when it has the maximum amount of energy potential.
     * 
     */
    @JsonProperty("max_range_meters")
    public Double getMaxRangeMeters() {
        return maxRangeMeters;
    }

    /**
     * The furthest distance in meters that the vehicle can travel without recharging or refueling when it has the maximum amount of energy potential.
     * 
     */
    @JsonProperty("max_range_meters")
    public void setMaxRangeMeters(Double maxRangeMeters) {
        this.maxRangeMeters = maxRangeMeters;
    }

    /**
     * The public name of this vehicle type.
     * 
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * The public name of this vehicle type.
     * 
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Description of accessories available in the vehicle.
     * 
     */
    @JsonProperty("vehicle_accessories")
    public List<VehicleAccessory> getVehicleAccessories() {
        return vehicleAccessories;
    }

    /**
     * Description of accessories available in the vehicle.
     * 
     */
    @JsonProperty("vehicle_accessories")
    public void setVehicleAccessories(List<VehicleAccessory> vehicleAccessories) {
        this.vehicleAccessories = vehicleAccessories;
    }

    /**
     * Maximum quantity of CO2, in grams, emitted per kilometer, according to the WLTP. Added in v2.3
     * 
     */
    @JsonProperty("g_CO2_km")
    public Integer getgCO2Km() {
        return gCO2Km;
    }

    /**
     * Maximum quantity of CO2, in grams, emitted per kilometer, according to the WLTP. Added in v2.3
     * 
     */
    @JsonProperty("g_CO2_km")
    public void setgCO2Km(Integer gCO2Km) {
        this.gCO2Km = gCO2Km;
    }

    /**
     * URL to an image that would assist the user in identifying the vehicle. JPEG or PNG. Added in v2.3
     * 
     */
    @JsonProperty("vehicle_image")
    public URI getVehicleImage() {
        return vehicleImage;
    }

    /**
     * URL to an image that would assist the user in identifying the vehicle. JPEG or PNG. Added in v2.3
     * 
     */
    @JsonProperty("vehicle_image")
    public void setVehicleImage(URI vehicleImage) {
        this.vehicleImage = vehicleImage;
    }

    /**
     * The name of the vehicle manufacturer. Added in v2.3
     * 
     */
    @JsonProperty("make")
    public String getMake() {
        return make;
    }

    /**
     * The name of the vehicle manufacturer. Added in v2.3
     * 
     */
    @JsonProperty("make")
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * The name of the vehicle model. Added in v2.3
     * 
     */
    @JsonProperty("model")
    public String getModel() {
        return model;
    }

    /**
     * The name of the vehicle model. Added in v2.3
     * 
     */
    @JsonProperty("model")
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * The color of the vehicle. Added in v2.3
     * 
     */
    @JsonProperty("color")
    public String getColor() {
        return color;
    }

    /**
     * The color of the vehicle. Added in v2.3
     * 
     */
    @JsonProperty("color")
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Number of wheels this vehicle type has. Added in v2.3
     * 
     */
    @JsonProperty("wheel_count")
    public Integer getWheelCount() {
        return wheelCount;
    }

    /**
     * Number of wheels this vehicle type has. Added in v2.3
     * 
     */
    @JsonProperty("wheel_count")
    public void setWheelCount(Integer wheelCount) {
        this.wheelCount = wheelCount;
    }

    /**
     * The maximum speed in kilometers per hour this vehicle is permitted to reach in accordance with local permit and regulations. Added in v2.3
     * 
     */
    @JsonProperty("max_permitted_speed")
    public Integer getMaxPermittedSpeed() {
        return maxPermittedSpeed;
    }

    /**
     * The maximum speed in kilometers per hour this vehicle is permitted to reach in accordance with local permit and regulations. Added in v2.3
     * 
     */
    @JsonProperty("max_permitted_speed")
    public void setMaxPermittedSpeed(Integer maxPermittedSpeed) {
        this.maxPermittedSpeed = maxPermittedSpeed;
    }

    /**
     * The rated power of the motor for this vehicle type in watts. Added in v2.3
     * 
     */
    @JsonProperty("rated_power")
    public Integer getRatedPower() {
        return ratedPower;
    }

    /**
     * The rated power of the motor for this vehicle type in watts. Added in v2.3
     * 
     */
    @JsonProperty("rated_power")
    public void setRatedPower(Integer ratedPower) {
        this.ratedPower = ratedPower;
    }

    /**
     * Maximum time in minutes that a vehicle can be reserved before a rental begins added in v2.3-RC.
     * 
     */
    @JsonProperty("default_reserve_time")
    public Integer getDefaultReserveTime() {
        return defaultReserveTime;
    }

    /**
     * Maximum time in minutes that a vehicle can be reserved before a rental begins added in v2.3-RC.
     * 
     */
    @JsonProperty("default_reserve_time")
    public void setDefaultReserveTime(Integer defaultReserveTime) {
        this.defaultReserveTime = defaultReserveTime;
    }

    /**
     * The conditions for returning the vehicle at the end of the trip. Added in v2.3-RC as return_type, and updated to return_constraint in v2.3.
     * 
     */
    @JsonProperty("return_constraint")
    public VehicleType.ReturnConstraint getReturnConstraint() {
        return returnConstraint;
    }

    /**
     * The conditions for returning the vehicle at the end of the trip. Added in v2.3-RC as return_type, and updated to return_constraint in v2.3.
     * 
     */
    @JsonProperty("return_constraint")
    public void setReturnConstraint(VehicleType.ReturnConstraint returnConstraint) {
        this.returnConstraint = returnConstraint;
    }

    /**
     * An object where each key defines one of the items listed below added in v2.3-RC.
     * 
     */
    @JsonProperty("vehicle_assets")
    public VehicleAssets getVehicleAssets() {
        return vehicleAssets;
    }

    /**
     * An object where each key defines one of the items listed below added in v2.3-RC.
     * 
     */
    @JsonProperty("vehicle_assets")
    public void setVehicleAssets(VehicleAssets vehicleAssets) {
        this.vehicleAssets = vehicleAssets;
    }

    /**
     * A plan_id as defined in system_pricing_plans.json added in v2.3-RC.
     * 
     */
    @JsonProperty("default_pricing_plan_id")
    public String getDefaultPricingPlanId() {
        return defaultPricingPlanId;
    }

    /**
     * A plan_id as defined in system_pricing_plans.json added in v2.3-RC.
     * 
     */
    @JsonProperty("default_pricing_plan_id")
    public void setDefaultPricingPlanId(String defaultPricingPlanId) {
        this.defaultPricingPlanId = defaultPricingPlanId;
    }

    /**
     * Array of all pricing plan IDs as defined in system_pricing_plans.json added in v2.3-RC.
     * 
     */
    @JsonProperty("pricing_plan_ids")
    public List<String> getPricingPlanIds() {
        return pricingPlanIds;
    }

    /**
     * Array of all pricing plan IDs as defined in system_pricing_plans.json added in v2.3-RC.
     * 
     */
    @JsonProperty("pricing_plan_ids")
    public void setPricingPlanIds(List<String> pricingPlanIds) {
        this.pricingPlanIds = pricingPlanIds;
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
        sb.append(VehicleType.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("vehicleTypeId");
        sb.append('=');
        sb.append(((this.vehicleTypeId == null)?"<null>":this.vehicleTypeId));
        sb.append(',');
        sb.append("formFactor");
        sb.append('=');
        sb.append(((this.formFactor == null)?"<null>":this.formFactor));
        sb.append(',');
        sb.append("riderCapacity");
        sb.append('=');
        sb.append(((this.riderCapacity == null)?"<null>":this.riderCapacity));
        sb.append(',');
        sb.append("cargoVolumeCapacity");
        sb.append('=');
        sb.append(((this.cargoVolumeCapacity == null)?"<null>":this.cargoVolumeCapacity));
        sb.append(',');
        sb.append("cargoLoadCapacity");
        sb.append('=');
        sb.append(((this.cargoLoadCapacity == null)?"<null>":this.cargoLoadCapacity));
        sb.append(',');
        sb.append("propulsionType");
        sb.append('=');
        sb.append(((this.propulsionType == null)?"<null>":this.propulsionType));
        sb.append(',');
        sb.append("ecoLabel");
        sb.append('=');
        sb.append(((this.ecoLabel == null)?"<null>":this.ecoLabel));
        sb.append(',');
        sb.append("maxRangeMeters");
        sb.append('=');
        sb.append(((this.maxRangeMeters == null)?"<null>":this.maxRangeMeters));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("vehicleAccessories");
        sb.append('=');
        sb.append(((this.vehicleAccessories == null)?"<null>":this.vehicleAccessories));
        sb.append(',');
        sb.append("gCO2Km");
        sb.append('=');
        sb.append(((this.gCO2Km == null)?"<null>":this.gCO2Km));
        sb.append(',');
        sb.append("vehicleImage");
        sb.append('=');
        sb.append(((this.vehicleImage == null)?"<null>":this.vehicleImage));
        sb.append(',');
        sb.append("make");
        sb.append('=');
        sb.append(((this.make == null)?"<null>":this.make));
        sb.append(',');
        sb.append("model");
        sb.append('=');
        sb.append(((this.model == null)?"<null>":this.model));
        sb.append(',');
        sb.append("color");
        sb.append('=');
        sb.append(((this.color == null)?"<null>":this.color));
        sb.append(',');
        sb.append("wheelCount");
        sb.append('=');
        sb.append(((this.wheelCount == null)?"<null>":this.wheelCount));
        sb.append(',');
        sb.append("maxPermittedSpeed");
        sb.append('=');
        sb.append(((this.maxPermittedSpeed == null)?"<null>":this.maxPermittedSpeed));
        sb.append(',');
        sb.append("ratedPower");
        sb.append('=');
        sb.append(((this.ratedPower == null)?"<null>":this.ratedPower));
        sb.append(',');
        sb.append("defaultReserveTime");
        sb.append('=');
        sb.append(((this.defaultReserveTime == null)?"<null>":this.defaultReserveTime));
        sb.append(',');
        sb.append("returnConstraint");
        sb.append('=');
        sb.append(((this.returnConstraint == null)?"<null>":this.returnConstraint));
        sb.append(',');
        sb.append("vehicleAssets");
        sb.append('=');
        sb.append(((this.vehicleAssets == null)?"<null>":this.vehicleAssets));
        sb.append(',');
        sb.append("defaultPricingPlanId");
        sb.append('=');
        sb.append(((this.defaultPricingPlanId == null)?"<null>":this.defaultPricingPlanId));
        sb.append(',');
        sb.append("pricingPlanIds");
        sb.append('=');
        sb.append(((this.pricingPlanIds == null)?"<null>":this.pricingPlanIds));
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
        result = ((result* 31)+((this.vehicleAccessories == null)? 0 :this.vehicleAccessories.hashCode()));
        result = ((result* 31)+((this.color == null)? 0 :this.color.hashCode()));
        result = ((result* 31)+((this.ecoLabel == null)? 0 :this.ecoLabel.hashCode()));
        result = ((result* 31)+((this.vehicleImage == null)? 0 :this.vehicleImage.hashCode()));
        result = ((result* 31)+((this.formFactor == null)? 0 :this.formFactor.hashCode()));
        result = ((result* 31)+((this.defaultPricingPlanId == null)? 0 :this.defaultPricingPlanId.hashCode()));
        result = ((result* 31)+((this.maxPermittedSpeed == null)? 0 :this.maxPermittedSpeed.hashCode()));
        result = ((result* 31)+((this.maxRangeMeters == null)? 0 :this.maxRangeMeters.hashCode()));
        result = ((result* 31)+((this.vehicleAssets == null)? 0 :this.vehicleAssets.hashCode()));
        result = ((result* 31)+((this.cargoVolumeCapacity == null)? 0 :this.cargoVolumeCapacity.hashCode()));
        result = ((result* 31)+((this.propulsionType == null)? 0 :this.propulsionType.hashCode()));
        result = ((result* 31)+((this.gCO2Km == null)? 0 :this.gCO2Km.hashCode()));
        result = ((result* 31)+((this.riderCapacity == null)? 0 :this.riderCapacity.hashCode()));
        result = ((result* 31)+((this.model == null)? 0 :this.model.hashCode()));
        result = ((result* 31)+((this.make == null)? 0 :this.make.hashCode()));
        result = ((result* 31)+((this.cargoLoadCapacity == null)? 0 :this.cargoLoadCapacity.hashCode()));
        result = ((result* 31)+((this.ratedPower == null)? 0 :this.ratedPower.hashCode()));
        result = ((result* 31)+((this.defaultReserveTime == null)? 0 :this.defaultReserveTime.hashCode()));
        result = ((result* 31)+((this.returnConstraint == null)? 0 :this.returnConstraint.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.wheelCount == null)? 0 :this.wheelCount.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.pricingPlanIds == null)? 0 :this.pricingPlanIds.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof VehicleType) == false) {
            return false;
        }
        VehicleType rhs = ((VehicleType) other);
        return (((((((((((((((((((((((((this.vehicleTypeId == rhs.vehicleTypeId)||((this.vehicleTypeId!= null)&&this.vehicleTypeId.equals(rhs.vehicleTypeId)))&&((this.vehicleAccessories == rhs.vehicleAccessories)||((this.vehicleAccessories!= null)&&this.vehicleAccessories.equals(rhs.vehicleAccessories))))&&((this.color == rhs.color)||((this.color!= null)&&this.color.equals(rhs.color))))&&((this.ecoLabel == rhs.ecoLabel)||((this.ecoLabel!= null)&&this.ecoLabel.equals(rhs.ecoLabel))))&&((this.vehicleImage == rhs.vehicleImage)||((this.vehicleImage!= null)&&this.vehicleImage.equals(rhs.vehicleImage))))&&((this.formFactor == rhs.formFactor)||((this.formFactor!= null)&&this.formFactor.equals(rhs.formFactor))))&&((this.defaultPricingPlanId == rhs.defaultPricingPlanId)||((this.defaultPricingPlanId!= null)&&this.defaultPricingPlanId.equals(rhs.defaultPricingPlanId))))&&((this.maxPermittedSpeed == rhs.maxPermittedSpeed)||((this.maxPermittedSpeed!= null)&&this.maxPermittedSpeed.equals(rhs.maxPermittedSpeed))))&&((this.maxRangeMeters == rhs.maxRangeMeters)||((this.maxRangeMeters!= null)&&this.maxRangeMeters.equals(rhs.maxRangeMeters))))&&((this.vehicleAssets == rhs.vehicleAssets)||((this.vehicleAssets!= null)&&this.vehicleAssets.equals(rhs.vehicleAssets))))&&((this.cargoVolumeCapacity == rhs.cargoVolumeCapacity)||((this.cargoVolumeCapacity!= null)&&this.cargoVolumeCapacity.equals(rhs.cargoVolumeCapacity))))&&((this.propulsionType == rhs.propulsionType)||((this.propulsionType!= null)&&this.propulsionType.equals(rhs.propulsionType))))&&((this.gCO2Km == rhs.gCO2Km)||((this.gCO2Km!= null)&&this.gCO2Km.equals(rhs.gCO2Km))))&&((this.riderCapacity == rhs.riderCapacity)||((this.riderCapacity!= null)&&this.riderCapacity.equals(rhs.riderCapacity))))&&((this.model == rhs.model)||((this.model!= null)&&this.model.equals(rhs.model))))&&((this.make == rhs.make)||((this.make!= null)&&this.make.equals(rhs.make))))&&((this.cargoLoadCapacity == rhs.cargoLoadCapacity)||((this.cargoLoadCapacity!= null)&&this.cargoLoadCapacity.equals(rhs.cargoLoadCapacity))))&&((this.ratedPower == rhs.ratedPower)||((this.ratedPower!= null)&&this.ratedPower.equals(rhs.ratedPower))))&&((this.defaultReserveTime == rhs.defaultReserveTime)||((this.defaultReserveTime!= null)&&this.defaultReserveTime.equals(rhs.defaultReserveTime))))&&((this.returnConstraint == rhs.returnConstraint)||((this.returnConstraint!= null)&&this.returnConstraint.equals(rhs.returnConstraint))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.wheelCount == rhs.wheelCount)||((this.wheelCount!= null)&&this.wheelCount.equals(rhs.wheelCount))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.pricingPlanIds == rhs.pricingPlanIds)||((this.pricingPlanIds!= null)&&this.pricingPlanIds.equals(rhs.pricingPlanIds))));
    }


    /**
     * The vehicle's general form factor.
     * 
     */
    public enum FormFactor {

        BICYCLE("bicycle"),
        CARGO_BICYCLE("cargo_bicycle"),
        CAR("car"),
        MOPED("moped"),
        SCOOTER_STANDING("scooter_standing"),
        SCOOTER_SEATED("scooter_seated"),
        OTHER("other"),
        SCOOTER("scooter");
        private final String value;
        private final static Map<String, VehicleType.FormFactor> CONSTANTS = new HashMap<String, VehicleType.FormFactor>();

        static {
            for (VehicleType.FormFactor c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private FormFactor(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static VehicleType.FormFactor fromValue(String value) {
            VehicleType.FormFactor constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }


    /**
     * The primary propulsion type of the vehicle. Updated in v2.3 to represent car-sharing
     * 
     */
    public enum PropulsionType {

        HUMAN("human"),
        ELECTRIC_ASSIST("electric_assist"),
        ELECTRIC("electric"),
        COMBUSTION("combustion"),
        COMBUSTION_DIESEL("combustion_diesel"),
        HYBRID("hybrid"),
        PLUG_IN_HYBRID("plug_in_hybrid"),
        HYDROGEN_FUEL_CELL("hydrogen_fuel_cell");
        private final String value;
        private final static Map<String, VehicleType.PropulsionType> CONSTANTS = new HashMap<String, VehicleType.PropulsionType>();

        static {
            for (VehicleType.PropulsionType c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private PropulsionType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static VehicleType.PropulsionType fromValue(String value) {
            VehicleType.PropulsionType constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }


    /**
     * The conditions for returning the vehicle at the end of the trip. Added in v2.3-RC as return_type, and updated to return_constraint in v2.3.
     * 
     */
    public enum ReturnConstraint {

        FREE_FLOATING("free_floating"),
        ROUNDTRIP_STATION("roundtrip_station"),
        ANY_STATION("any_station"),
        HYBRID("hybrid");
        private final String value;
        private final static Map<String, VehicleType.ReturnConstraint> CONSTANTS = new HashMap<String, VehicleType.ReturnConstraint>();

        static {
            for (VehicleType.ReturnConstraint c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private ReturnConstraint(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static VehicleType.ReturnConstraint fromValue(String value) {
            VehicleType.ReturnConstraint constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
