
package com.github.zacharydhamilton.objects.geofencing;

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


/**
 * Each geofenced zone and its associated rules and attributes is described as an object within the array of features.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "features"
})
public class GeofencingZones__1 {

    /**
     * FeatureCollection as per IETF RFC 7946.
     * (Required)
     * 
     */
    @JsonProperty("type")
    @JsonPropertyDescription("FeatureCollection as per IETF RFC 7946.")
    private GeofencingZones__1 .Type type;
    /**
     * Array of objects.
     * (Required)
     * 
     */
    @JsonProperty("features")
    @JsonPropertyDescription("Array of objects.")
    private List<Feature> features = new ArrayList<Feature>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * FeatureCollection as per IETF RFC 7946.
     * (Required)
     * 
     */
    @JsonProperty("type")
    public GeofencingZones__1 .Type getType() {
        return type;
    }

    /**
     * FeatureCollection as per IETF RFC 7946.
     * (Required)
     * 
     */
    @JsonProperty("type")
    public void setType(GeofencingZones__1 .Type type) {
        this.type = type;
    }

    /**
     * Array of objects.
     * (Required)
     * 
     */
    @JsonProperty("features")
    public List<Feature> getFeatures() {
        return features;
    }

    /**
     * Array of objects.
     * (Required)
     * 
     */
    @JsonProperty("features")
    public void setFeatures(List<Feature> features) {
        this.features = features;
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
        sb.append(GeofencingZones__1 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("features");
        sb.append('=');
        sb.append(((this.features == null)?"<null>":this.features));
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
        result = ((result* 31)+((this.features == null)? 0 :this.features.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GeofencingZones__1) == false) {
            return false;
        }
        GeofencingZones__1 rhs = ((GeofencingZones__1) other);
        return ((((this.features == rhs.features)||((this.features!= null)&&this.features.equals(rhs.features)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))));
    }


    /**
     * FeatureCollection as per IETF RFC 7946.
     * 
     */
    public enum Type {

        FEATURE_COLLECTION("FeatureCollection");
        private final String value;
        private final static Map<String, GeofencingZones__1 .Type> CONSTANTS = new HashMap<String, GeofencingZones__1 .Type>();

        static {
            for (GeofencingZones__1 .Type c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Type(String value) {
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
        public static GeofencingZones__1 .Type fromValue(String value) {
            GeofencingZones__1 .Type constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
