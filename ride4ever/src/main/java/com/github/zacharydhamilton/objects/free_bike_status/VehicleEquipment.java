
package com.github.zacharydhamilton.objects.free_bike_status;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum VehicleEquipment {

    CHILD_SEAT_A("child_seat_a"),
    CHILD_SEAT_B("child_seat_b"),
    CHILD_SEAT_C("child_seat_c"),
    WINTER_TIRES("winter_tires"),
    SNOW_CHAINS("snow_chains");
    private final String value;
    private final static Map<String, VehicleEquipment> CONSTANTS = new HashMap<String, VehicleEquipment>();

    static {
        for (VehicleEquipment c: values()) {
            CONSTANTS.put(c.value, c);
        }
    }

    private VehicleEquipment(String value) {
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
    public static VehicleEquipment fromValue(String value) {
        VehicleEquipment constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }

}
