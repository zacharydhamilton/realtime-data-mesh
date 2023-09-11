
package com.github.zacharydhamilton.objects.vehicle_types;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * An object where each key defines one of the items listed below added in v2.3-RC.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "icon_url",
    "icon_url_dark",
    "icon_last_modified"
})
public class VehicleAssets {

    /**
     * A fully qualified URL pointing to the location of a graphic icon file that MAY be used to represent this vehicle type on maps and in other applications added in v2.3-RC.
     * (Required)
     * 
     */
    @JsonProperty("icon_url")
    @JsonPropertyDescription("A fully qualified URL pointing to the location of a graphic icon file that MAY be used to represent this vehicle type on maps and in other applications added in v2.3-RC.")
    private URI iconUrl;
    /**
     * A fully qualified URL pointing to the location of a graphic icon file to be used to represent this vehicle type when in dark mode added in v2.3-RC.
     * 
     */
    @JsonProperty("icon_url_dark")
    @JsonPropertyDescription("A fully qualified URL pointing to the location of a graphic icon file to be used to represent this vehicle type when in dark mode added in v2.3-RC.")
    private URI iconUrlDark;
    /**
     * Date that indicates the last time any included vehicle icon images were modified or updated added in v2.3-RC.
     * (Required)
     * 
     */
    @JsonProperty("icon_last_modified")
    @JsonPropertyDescription("Date that indicates the last time any included vehicle icon images were modified or updated added in v2.3-RC.")
    private String iconLastModified;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * A fully qualified URL pointing to the location of a graphic icon file that MAY be used to represent this vehicle type on maps and in other applications added in v2.3-RC.
     * (Required)
     * 
     */
    @JsonProperty("icon_url")
    public URI getIconUrl() {
        return iconUrl;
    }

    /**
     * A fully qualified URL pointing to the location of a graphic icon file that MAY be used to represent this vehicle type on maps and in other applications added in v2.3-RC.
     * (Required)
     * 
     */
    @JsonProperty("icon_url")
    public void setIconUrl(URI iconUrl) {
        this.iconUrl = iconUrl;
    }

    /**
     * A fully qualified URL pointing to the location of a graphic icon file to be used to represent this vehicle type when in dark mode added in v2.3-RC.
     * 
     */
    @JsonProperty("icon_url_dark")
    public URI getIconUrlDark() {
        return iconUrlDark;
    }

    /**
     * A fully qualified URL pointing to the location of a graphic icon file to be used to represent this vehicle type when in dark mode added in v2.3-RC.
     * 
     */
    @JsonProperty("icon_url_dark")
    public void setIconUrlDark(URI iconUrlDark) {
        this.iconUrlDark = iconUrlDark;
    }

    /**
     * Date that indicates the last time any included vehicle icon images were modified or updated added in v2.3-RC.
     * (Required)
     * 
     */
    @JsonProperty("icon_last_modified")
    public String getIconLastModified() {
        return iconLastModified;
    }

    /**
     * Date that indicates the last time any included vehicle icon images were modified or updated added in v2.3-RC.
     * (Required)
     * 
     */
    @JsonProperty("icon_last_modified")
    public void setIconLastModified(String iconLastModified) {
        this.iconLastModified = iconLastModified;
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
        sb.append(VehicleAssets.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("iconUrl");
        sb.append('=');
        sb.append(((this.iconUrl == null)?"<null>":this.iconUrl));
        sb.append(',');
        sb.append("iconUrlDark");
        sb.append('=');
        sb.append(((this.iconUrlDark == null)?"<null>":this.iconUrlDark));
        sb.append(',');
        sb.append("iconLastModified");
        sb.append('=');
        sb.append(((this.iconLastModified == null)?"<null>":this.iconLastModified));
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
        result = ((result* 31)+((this.iconUrlDark == null)? 0 :this.iconUrlDark.hashCode()));
        result = ((result* 31)+((this.iconUrl == null)? 0 :this.iconUrl.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.iconLastModified == null)? 0 :this.iconLastModified.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof VehicleAssets) == false) {
            return false;
        }
        VehicleAssets rhs = ((VehicleAssets) other);
        return (((((this.iconUrlDark == rhs.iconUrlDark)||((this.iconUrlDark!= null)&&this.iconUrlDark.equals(rhs.iconUrlDark)))&&((this.iconUrl == rhs.iconUrl)||((this.iconUrl!= null)&&this.iconUrl.equals(rhs.iconUrl))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.iconLastModified == rhs.iconLastModified)||((this.iconLastModified!= null)&&this.iconLastModified.equals(rhs.iconLastModified))));
    }

}
