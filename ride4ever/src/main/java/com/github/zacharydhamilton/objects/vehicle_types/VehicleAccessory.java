
package com.github.zacharydhamilton.objects.vehicle_types;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum VehicleAccessory {

    AIR_CONDITIONING("air_conditioning"),
    AUTOMATIC("automatic"),
    MANUAL("manual"),
    CONVERTIBLE("convertible"),
    CRUISE_CONTROL("cruise_control"),
    DOORS_2("doors_2"),
    DOORS_3("doors_3"),
    DOORS_4("doors_4"),
    DOORS_5("doors_5"),
    NAVIGATION("navigation");
    private final String value;
    private final static Map<String, VehicleAccessory> CONSTANTS = new HashMap<String, VehicleAccessory>();

    static {
        for (VehicleAccessory c: values()) {
            CONSTANTS.put(c.value, c);
        }
    }

    private VehicleAccessory(String value) {
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
    public static VehicleAccessory fromValue(String value) {
        VehicleAccessory constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }

}
