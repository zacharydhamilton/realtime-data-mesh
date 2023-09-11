
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


/**
 * Describing travel allowances and limitations.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "start",
    "end",
    "rules"
})
public class Properties {

    /**
     * Public name of the geofencing zone.
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("Public name of the geofencing zone.")
    private String name;
    /**
     * Start time of the geofencing zone in POSIX time.
     * 
     */
    @JsonProperty("start")
    @JsonPropertyDescription("Start time of the geofencing zone in POSIX time.")
    private Integer start;
    /**
     * End time of the geofencing zone in POSIX time.
     * 
     */
    @JsonProperty("end")
    @JsonPropertyDescription("End time of the geofencing zone in POSIX time.")
    private Integer end;
    /**
     * Array that contains one object per rule.
     * 
     */
    @JsonProperty("rules")
    @JsonPropertyDescription("Array that contains one object per rule.")
    private List<Rule> rules = new ArrayList<Rule>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Public name of the geofencing zone.
     * 
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * Public name of the geofencing zone.
     * 
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Start time of the geofencing zone in POSIX time.
     * 
     */
    @JsonProperty("start")
    public Integer getStart() {
        return start;
    }

    /**
     * Start time of the geofencing zone in POSIX time.
     * 
     */
    @JsonProperty("start")
    public void setStart(Integer start) {
        this.start = start;
    }

    /**
     * End time of the geofencing zone in POSIX time.
     * 
     */
    @JsonProperty("end")
    public Integer getEnd() {
        return end;
    }

    /**
     * End time of the geofencing zone in POSIX time.
     * 
     */
    @JsonProperty("end")
    public void setEnd(Integer end) {
        this.end = end;
    }

    /**
     * Array that contains one object per rule.
     * 
     */
    @JsonProperty("rules")
    public List<Rule> getRules() {
        return rules;
    }

    /**
     * Array that contains one object per rule.
     * 
     */
    @JsonProperty("rules")
    public void setRules(List<Rule> rules) {
        this.rules = rules;
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
        sb.append(Properties.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("start");
        sb.append('=');
        sb.append(((this.start == null)?"<null>":this.start));
        sb.append(',');
        sb.append("end");
        sb.append('=');
        sb.append(((this.end == null)?"<null>":this.end));
        sb.append(',');
        sb.append("rules");
        sb.append('=');
        sb.append(((this.rules == null)?"<null>":this.rules));
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
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.start == null)? 0 :this.start.hashCode()));
        result = ((result* 31)+((this.end == null)? 0 :this.end.hashCode()));
        result = ((result* 31)+((this.rules == null)? 0 :this.rules.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Properties) == false) {
            return false;
        }
        Properties rhs = ((Properties) other);
        return ((((((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name)))&&((this.start == rhs.start)||((this.start!= null)&&this.start.equals(rhs.start))))&&((this.end == rhs.end)||((this.end!= null)&&this.end.equals(rhs.end))))&&((this.rules == rhs.rules)||((this.rules!= null)&&this.rules.equals(rhs.rules))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
