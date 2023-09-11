
package com.github.zacharydhamilton.objects.station_info;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RentalMethod {

    KEY("KEY"),
    CREDITCARD("CREDITCARD"),
    PAYPASS("PAYPASS"),
    APPLEPAY("APPLEPAY"),
    ANDROIDPAY("ANDROIDPAY"),
    TRANSITCARD("TRANSITCARD"),
    ACCOUNTNUMBER("ACCOUNTNUMBER"),
    PHONE("PHONE");
    private final String value;
    private final static Map<String, RentalMethod> CONSTANTS = new HashMap<String, RentalMethod>();

    static {
        for (RentalMethod c: values()) {
            CONSTANTS.put(c.value, c);
        }
    }

    private RentalMethod(String value) {
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
    public static RentalMethod fromValue(String value) {
        RentalMethod constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }

}
