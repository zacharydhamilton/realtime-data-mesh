
package com.github.zacharydhamilton.objects.station_info;

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
    "station_id",
    "name",
    "short_name",
    "lat",
    "lon",
    "address",
    "cross_street",
    "region_id",
    "post_code",
    "rental_methods",
    "is_virtual_station",
    "station_area",
    "parking_type",
    "parking_hoop",
    "contact_phone",
    "capacity",
    "vehicle_capacity",
    "is_valet_station",
    "is_charging_station",
    "rental_uris",
    "vehicle_type_capacity"
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
     * Public name of the station.
     * (Required)
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("Public name of the station.")
    private String name;
    /**
     * Short name or other type of identifier.
     * 
     */
    @JsonProperty("short_name")
    @JsonPropertyDescription("Short name or other type of identifier.")
    private String shortName;
    /**
     * The latitude of the station.
     * (Required)
     * 
     */
    @JsonProperty("lat")
    @JsonPropertyDescription("The latitude of the station.")
    private Double lat;
    /**
     * The longitude fo the station.
     * (Required)
     * 
     */
    @JsonProperty("lon")
    @JsonPropertyDescription("The longitude fo the station.")
    private Double lon;
    /**
     * Address where station is located.
     * 
     */
    @JsonProperty("address")
    @JsonPropertyDescription("Address where station is located.")
    private String address;
    /**
     * Cross street or landmark where the station is located.
     * 
     */
    @JsonProperty("cross_street")
    @JsonPropertyDescription("Cross street or landmark where the station is located.")
    private String crossStreet;
    /**
     * Identifier of the region where the station is located.
     * 
     */
    @JsonProperty("region_id")
    @JsonPropertyDescription("Identifier of the region where the station is located.")
    private String regionId;
    /**
     * Postal code where station is located.
     * 
     */
    @JsonProperty("post_code")
    @JsonPropertyDescription("Postal code where station is located.")
    private String postCode;
    /**
     * Payment methods accepted at this station.
     * 
     */
    @JsonProperty("rental_methods")
    @JsonPropertyDescription("Payment methods accepted at this station.")
    private List<RentalMethod> rentalMethods = new ArrayList<RentalMethod>();
    /**
     * Is this station a location with or without physical infrastructure? (added in v2.1-RC)
     * 
     */
    @JsonProperty("is_virtual_station")
    @JsonPropertyDescription("Is this station a location with or without physical infrastructure? (added in v2.1-RC)")
    private Boolean isVirtualStation;
    /**
     * A multipolygon that describes the area of a virtual station (added in v2.1-RC).
     * 
     */
    @JsonProperty("station_area")
    @JsonPropertyDescription("A multipolygon that describes the area of a virtual station (added in v2.1-RC).")
    private StationArea stationArea;
    /**
     * Type of parking station. Added in v2.3
     * 
     */
    @JsonProperty("parking_type")
    @JsonPropertyDescription("Type of parking station. Added in v2.3")
    private Station.ParkingType parkingType;
    /**
     * Are parking hoops present at this station? Added in v2.3
     * 
     */
    @JsonProperty("parking_hoop")
    @JsonPropertyDescription("Are parking hoops present at this station? Added in v2.3")
    private Boolean parkingHoop;
    /**
     * Contact phone of the station. Added in v2.3
     * 
     */
    @JsonProperty("contact_phone")
    @JsonPropertyDescription("Contact phone of the station. Added in v2.3")
    private String contactPhone;
    /**
     * Number of total docking points installed at this station, both available and unavailable.
     * 
     */
    @JsonProperty("capacity")
    @JsonPropertyDescription("Number of total docking points installed at this station, both available and unavailable.")
    private Integer capacity;
    /**
     * An object where each key is a vehicle_type_id and the value is a number presenting the total number of vehicles of this type that can park within the station_area (added in v2.1-RC).
     * 
     */
    @JsonProperty("vehicle_capacity")
    @JsonPropertyDescription("An object where each key is a vehicle_type_id and the value is a number presenting the total number of vehicles of this type that can park within the station_area (added in v2.1-RC).")
    private VehicleCapacity vehicleCapacity;
    /**
     * Are valet services provided at this station? (added in v2.1-RC)
     * 
     */
    @JsonProperty("is_valet_station")
    @JsonPropertyDescription("Are valet services provided at this station? (added in v2.1-RC)")
    private Boolean isValetStation;
    /**
     * Does the station support charging of electric vehicles? (added in v2.3-RC)
     * 
     */
    @JsonProperty("is_charging_station")
    @JsonPropertyDescription("Does the station support charging of electric vehicles? (added in v2.3-RC)")
    private Boolean isChargingStation;
    /**
     * Contains rental uris for Android, iOS, and web in the android, ios, and web fields (added in v1.1).
     * 
     */
    @JsonProperty("rental_uris")
    @JsonPropertyDescription("Contains rental uris for Android, iOS, and web in the android, ios, and web fields (added in v1.1).")
    private RentalUris rentalUris;
    /**
     * An object where each key is a vehicle_type_id and the value is a number representing the total docking points installed at this station for each vehicle type (added in v2.1-RC).
     * 
     */
    @JsonProperty("vehicle_type_capacity")
    @JsonPropertyDescription("An object where each key is a vehicle_type_id and the value is a number representing the total docking points installed at this station for each vehicle type (added in v2.1-RC).")
    private VehicleTypeCapacity vehicleTypeCapacity;
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
     * Public name of the station.
     * (Required)
     * 
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * Public name of the station.
     * (Required)
     * 
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Short name or other type of identifier.
     * 
     */
    @JsonProperty("short_name")
    public String getShortName() {
        return shortName;
    }

    /**
     * Short name or other type of identifier.
     * 
     */
    @JsonProperty("short_name")
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * The latitude of the station.
     * (Required)
     * 
     */
    @JsonProperty("lat")
    public Double getLat() {
        return lat;
    }

    /**
     * The latitude of the station.
     * (Required)
     * 
     */
    @JsonProperty("lat")
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * The longitude fo the station.
     * (Required)
     * 
     */
    @JsonProperty("lon")
    public Double getLon() {
        return lon;
    }

    /**
     * The longitude fo the station.
     * (Required)
     * 
     */
    @JsonProperty("lon")
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * Address where station is located.
     * 
     */
    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    /**
     * Address where station is located.
     * 
     */
    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Cross street or landmark where the station is located.
     * 
     */
    @JsonProperty("cross_street")
    public String getCrossStreet() {
        return crossStreet;
    }

    /**
     * Cross street or landmark where the station is located.
     * 
     */
    @JsonProperty("cross_street")
    public void setCrossStreet(String crossStreet) {
        this.crossStreet = crossStreet;
    }

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
     * Postal code where station is located.
     * 
     */
    @JsonProperty("post_code")
    public String getPostCode() {
        return postCode;
    }

    /**
     * Postal code where station is located.
     * 
     */
    @JsonProperty("post_code")
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * Payment methods accepted at this station.
     * 
     */
    @JsonProperty("rental_methods")
    public List<RentalMethod> getRentalMethods() {
        return rentalMethods;
    }

    /**
     * Payment methods accepted at this station.
     * 
     */
    @JsonProperty("rental_methods")
    public void setRentalMethods(List<RentalMethod> rentalMethods) {
        this.rentalMethods = rentalMethods;
    }

    /**
     * Is this station a location with or without physical infrastructure? (added in v2.1-RC)
     * 
     */
    @JsonProperty("is_virtual_station")
    public Boolean getIsVirtualStation() {
        return isVirtualStation;
    }

    /**
     * Is this station a location with or without physical infrastructure? (added in v2.1-RC)
     * 
     */
    @JsonProperty("is_virtual_station")
    public void setIsVirtualStation(Boolean isVirtualStation) {
        this.isVirtualStation = isVirtualStation;
    }

    /**
     * A multipolygon that describes the area of a virtual station (added in v2.1-RC).
     * 
     */
    @JsonProperty("station_area")
    public StationArea getStationArea() {
        return stationArea;
    }

    /**
     * A multipolygon that describes the area of a virtual station (added in v2.1-RC).
     * 
     */
    @JsonProperty("station_area")
    public void setStationArea(StationArea stationArea) {
        this.stationArea = stationArea;
    }

    /**
     * Type of parking station. Added in v2.3
     * 
     */
    @JsonProperty("parking_type")
    public Station.ParkingType getParkingType() {
        return parkingType;
    }

    /**
     * Type of parking station. Added in v2.3
     * 
     */
    @JsonProperty("parking_type")
    public void setParkingType(Station.ParkingType parkingType) {
        this.parkingType = parkingType;
    }

    /**
     * Are parking hoops present at this station? Added in v2.3
     * 
     */
    @JsonProperty("parking_hoop")
    public Boolean getParkingHoop() {
        return parkingHoop;
    }

    /**
     * Are parking hoops present at this station? Added in v2.3
     * 
     */
    @JsonProperty("parking_hoop")
    public void setParkingHoop(Boolean parkingHoop) {
        this.parkingHoop = parkingHoop;
    }

    /**
     * Contact phone of the station. Added in v2.3
     * 
     */
    @JsonProperty("contact_phone")
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * Contact phone of the station. Added in v2.3
     * 
     */
    @JsonProperty("contact_phone")
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * Number of total docking points installed at this station, both available and unavailable.
     * 
     */
    @JsonProperty("capacity")
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * Number of total docking points installed at this station, both available and unavailable.
     * 
     */
    @JsonProperty("capacity")
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * An object where each key is a vehicle_type_id and the value is a number presenting the total number of vehicles of this type that can park within the station_area (added in v2.1-RC).
     * 
     */
    @JsonProperty("vehicle_capacity")
    public VehicleCapacity getVehicleCapacity() {
        return vehicleCapacity;
    }

    /**
     * An object where each key is a vehicle_type_id and the value is a number presenting the total number of vehicles of this type that can park within the station_area (added in v2.1-RC).
     * 
     */
    @JsonProperty("vehicle_capacity")
    public void setVehicleCapacity(VehicleCapacity vehicleCapacity) {
        this.vehicleCapacity = vehicleCapacity;
    }

    /**
     * Are valet services provided at this station? (added in v2.1-RC)
     * 
     */
    @JsonProperty("is_valet_station")
    public Boolean getIsValetStation() {
        return isValetStation;
    }

    /**
     * Are valet services provided at this station? (added in v2.1-RC)
     * 
     */
    @JsonProperty("is_valet_station")
    public void setIsValetStation(Boolean isValetStation) {
        this.isValetStation = isValetStation;
    }

    /**
     * Does the station support charging of electric vehicles? (added in v2.3-RC)
     * 
     */
    @JsonProperty("is_charging_station")
    public Boolean getIsChargingStation() {
        return isChargingStation;
    }

    /**
     * Does the station support charging of electric vehicles? (added in v2.3-RC)
     * 
     */
    @JsonProperty("is_charging_station")
    public void setIsChargingStation(Boolean isChargingStation) {
        this.isChargingStation = isChargingStation;
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
     * An object where each key is a vehicle_type_id and the value is a number representing the total docking points installed at this station for each vehicle type (added in v2.1-RC).
     * 
     */
    @JsonProperty("vehicle_type_capacity")
    public VehicleTypeCapacity getVehicleTypeCapacity() {
        return vehicleTypeCapacity;
    }

    /**
     * An object where each key is a vehicle_type_id and the value is a number representing the total docking points installed at this station for each vehicle type (added in v2.1-RC).
     * 
     */
    @JsonProperty("vehicle_type_capacity")
    public void setVehicleTypeCapacity(VehicleTypeCapacity vehicleTypeCapacity) {
        this.vehicleTypeCapacity = vehicleTypeCapacity;
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
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("shortName");
        sb.append('=');
        sb.append(((this.shortName == null)?"<null>":this.shortName));
        sb.append(',');
        sb.append("lat");
        sb.append('=');
        sb.append(((this.lat == null)?"<null>":this.lat));
        sb.append(',');
        sb.append("lon");
        sb.append('=');
        sb.append(((this.lon == null)?"<null>":this.lon));
        sb.append(',');
        sb.append("address");
        sb.append('=');
        sb.append(((this.address == null)?"<null>":this.address));
        sb.append(',');
        sb.append("crossStreet");
        sb.append('=');
        sb.append(((this.crossStreet == null)?"<null>":this.crossStreet));
        sb.append(',');
        sb.append("regionId");
        sb.append('=');
        sb.append(((this.regionId == null)?"<null>":this.regionId));
        sb.append(',');
        sb.append("postCode");
        sb.append('=');
        sb.append(((this.postCode == null)?"<null>":this.postCode));
        sb.append(',');
        sb.append("rentalMethods");
        sb.append('=');
        sb.append(((this.rentalMethods == null)?"<null>":this.rentalMethods));
        sb.append(',');
        sb.append("isVirtualStation");
        sb.append('=');
        sb.append(((this.isVirtualStation == null)?"<null>":this.isVirtualStation));
        sb.append(',');
        sb.append("stationArea");
        sb.append('=');
        sb.append(((this.stationArea == null)?"<null>":this.stationArea));
        sb.append(',');
        sb.append("parkingType");
        sb.append('=');
        sb.append(((this.parkingType == null)?"<null>":this.parkingType));
        sb.append(',');
        sb.append("parkingHoop");
        sb.append('=');
        sb.append(((this.parkingHoop == null)?"<null>":this.parkingHoop));
        sb.append(',');
        sb.append("contactPhone");
        sb.append('=');
        sb.append(((this.contactPhone == null)?"<null>":this.contactPhone));
        sb.append(',');
        sb.append("capacity");
        sb.append('=');
        sb.append(((this.capacity == null)?"<null>":this.capacity));
        sb.append(',');
        sb.append("vehicleCapacity");
        sb.append('=');
        sb.append(((this.vehicleCapacity == null)?"<null>":this.vehicleCapacity));
        sb.append(',');
        sb.append("isValetStation");
        sb.append('=');
        sb.append(((this.isValetStation == null)?"<null>":this.isValetStation));
        sb.append(',');
        sb.append("isChargingStation");
        sb.append('=');
        sb.append(((this.isChargingStation == null)?"<null>":this.isChargingStation));
        sb.append(',');
        sb.append("rentalUris");
        sb.append('=');
        sb.append(((this.rentalUris == null)?"<null>":this.rentalUris));
        sb.append(',');
        sb.append("vehicleTypeCapacity");
        sb.append('=');
        sb.append(((this.vehicleTypeCapacity == null)?"<null>":this.vehicleTypeCapacity));
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
        result = ((result* 31)+((this.parkingType == null)? 0 :this.parkingType.hashCode()));
        result = ((result* 31)+((this.address == null)? 0 :this.address.hashCode()));
        result = ((result* 31)+((this.rentalUris == null)? 0 :this.rentalUris.hashCode()));
        result = ((result* 31)+((this.lon == null)? 0 :this.lon.hashCode()));
        result = ((result* 31)+((this.parkingHoop == null)? 0 :this.parkingHoop.hashCode()));
        result = ((result* 31)+((this.capacity == null)? 0 :this.capacity.hashCode()));
        result = ((result* 31)+((this.isVirtualStation == null)? 0 :this.isVirtualStation.hashCode()));
        result = ((result* 31)+((this.vehicleCapacity == null)? 0 :this.vehicleCapacity.hashCode()));
        result = ((result* 31)+((this.regionId == null)? 0 :this.regionId.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.postCode == null)? 0 :this.postCode.hashCode()));
        result = ((result* 31)+((this.isChargingStation == null)? 0 :this.isChargingStation.hashCode()));
        result = ((result* 31)+((this.rentalMethods == null)? 0 :this.rentalMethods.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.shortName == null)? 0 :this.shortName.hashCode()));
        result = ((result* 31)+((this.crossStreet == null)? 0 :this.crossStreet.hashCode()));
        result = ((result* 31)+((this.contactPhone == null)? 0 :this.contactPhone.hashCode()));
        result = ((result* 31)+((this.vehicleTypeCapacity == null)? 0 :this.vehicleTypeCapacity.hashCode()));
        result = ((result* 31)+((this.lat == null)? 0 :this.lat.hashCode()));
        result = ((result* 31)+((this.stationArea == null)? 0 :this.stationArea.hashCode()));
        result = ((result* 31)+((this.stationId == null)? 0 :this.stationId.hashCode()));
        result = ((result* 31)+((this.isValetStation == null)? 0 :this.isValetStation.hashCode()));
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
        return (((((((((((((((((((((((this.parkingType == rhs.parkingType)||((this.parkingType!= null)&&this.parkingType.equals(rhs.parkingType)))&&((this.address == rhs.address)||((this.address!= null)&&this.address.equals(rhs.address))))&&((this.rentalUris == rhs.rentalUris)||((this.rentalUris!= null)&&this.rentalUris.equals(rhs.rentalUris))))&&((this.lon == rhs.lon)||((this.lon!= null)&&this.lon.equals(rhs.lon))))&&((this.parkingHoop == rhs.parkingHoop)||((this.parkingHoop!= null)&&this.parkingHoop.equals(rhs.parkingHoop))))&&((this.capacity == rhs.capacity)||((this.capacity!= null)&&this.capacity.equals(rhs.capacity))))&&((this.isVirtualStation == rhs.isVirtualStation)||((this.isVirtualStation!= null)&&this.isVirtualStation.equals(rhs.isVirtualStation))))&&((this.vehicleCapacity == rhs.vehicleCapacity)||((this.vehicleCapacity!= null)&&this.vehicleCapacity.equals(rhs.vehicleCapacity))))&&((this.regionId == rhs.regionId)||((this.regionId!= null)&&this.regionId.equals(rhs.regionId))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.postCode == rhs.postCode)||((this.postCode!= null)&&this.postCode.equals(rhs.postCode))))&&((this.isChargingStation == rhs.isChargingStation)||((this.isChargingStation!= null)&&this.isChargingStation.equals(rhs.isChargingStation))))&&((this.rentalMethods == rhs.rentalMethods)||((this.rentalMethods!= null)&&this.rentalMethods.equals(rhs.rentalMethods))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.shortName == rhs.shortName)||((this.shortName!= null)&&this.shortName.equals(rhs.shortName))))&&((this.crossStreet == rhs.crossStreet)||((this.crossStreet!= null)&&this.crossStreet.equals(rhs.crossStreet))))&&((this.contactPhone == rhs.contactPhone)||((this.contactPhone!= null)&&this.contactPhone.equals(rhs.contactPhone))))&&((this.vehicleTypeCapacity == rhs.vehicleTypeCapacity)||((this.vehicleTypeCapacity!= null)&&this.vehicleTypeCapacity.equals(rhs.vehicleTypeCapacity))))&&((this.lat == rhs.lat)||((this.lat!= null)&&this.lat.equals(rhs.lat))))&&((this.stationArea == rhs.stationArea)||((this.stationArea!= null)&&this.stationArea.equals(rhs.stationArea))))&&((this.stationId == rhs.stationId)||((this.stationId!= null)&&this.stationId.equals(rhs.stationId))))&&((this.isValetStation == rhs.isValetStation)||((this.isValetStation!= null)&&this.isValetStation.equals(rhs.isValetStation))));
    }


    /**
     * Type of parking station. Added in v2.3
     * 
     */
    public enum ParkingType {

        PARKING_LOT("parking_lot"),
        STREET_PARKING("street_parking"),
        UNDERGROUND_PARKING("underground_parking"),
        SIDEWALK_PARKING("sidewalk_parking"),
        OTHER("other");
        private final String value;
        private final static Map<String, Station.ParkingType> CONSTANTS = new HashMap<String, Station.ParkingType>();

        static {
            for (Station.ParkingType c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private ParkingType(String value) {
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
        public static Station.ParkingType fromValue(String value) {
            Station.ParkingType constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
