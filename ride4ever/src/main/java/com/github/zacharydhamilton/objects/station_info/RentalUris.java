
package com.github.zacharydhamilton.objects.station_info;

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
 * Contains rental uris for Android, iOS, and web in the android, ios, and web fields (added in v1.1).
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "android",
    "ios",
    "web"
})
public class RentalUris {

    /**
     * URI that can be passed to an Android app with an intent (added in v1.1).
     * 
     */
    @JsonProperty("android")
    @JsonPropertyDescription("URI that can be passed to an Android app with an intent (added in v1.1).")
    private URI android;
    /**
     * URI that can be used on iOS to launch the rental app for this station (added in v1.1).
     * 
     */
    @JsonProperty("ios")
    @JsonPropertyDescription("URI that can be used on iOS to launch the rental app for this station (added in v1.1).")
    private URI ios;
    /**
     * URL that can be used by a web browser to show more information about renting a vehicle at this station (added in v1.1).
     * 
     */
    @JsonProperty("web")
    @JsonPropertyDescription("URL that can be used by a web browser to show more information about renting a vehicle at this station (added in v1.1).")
    private URI web;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * URI that can be passed to an Android app with an intent (added in v1.1).
     * 
     */
    @JsonProperty("android")
    public URI getAndroid() {
        return android;
    }

    /**
     * URI that can be passed to an Android app with an intent (added in v1.1).
     * 
     */
    @JsonProperty("android")
    public void setAndroid(URI android) {
        this.android = android;
    }

    /**
     * URI that can be used on iOS to launch the rental app for this station (added in v1.1).
     * 
     */
    @JsonProperty("ios")
    public URI getIos() {
        return ios;
    }

    /**
     * URI that can be used on iOS to launch the rental app for this station (added in v1.1).
     * 
     */
    @JsonProperty("ios")
    public void setIos(URI ios) {
        this.ios = ios;
    }

    /**
     * URL that can be used by a web browser to show more information about renting a vehicle at this station (added in v1.1).
     * 
     */
    @JsonProperty("web")
    public URI getWeb() {
        return web;
    }

    /**
     * URL that can be used by a web browser to show more information about renting a vehicle at this station (added in v1.1).
     * 
     */
    @JsonProperty("web")
    public void setWeb(URI web) {
        this.web = web;
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
        sb.append(RentalUris.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("android");
        sb.append('=');
        sb.append(((this.android == null)?"<null>":this.android));
        sb.append(',');
        sb.append("ios");
        sb.append('=');
        sb.append(((this.ios == null)?"<null>":this.ios));
        sb.append(',');
        sb.append("web");
        sb.append('=');
        sb.append(((this.web == null)?"<null>":this.web));
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
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.ios == null)? 0 :this.ios.hashCode()));
        result = ((result* 31)+((this.web == null)? 0 :this.web.hashCode()));
        result = ((result* 31)+((this.android == null)? 0 :this.android.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RentalUris) == false) {
            return false;
        }
        RentalUris rhs = ((RentalUris) other);
        return (((((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&((this.ios == rhs.ios)||((this.ios!= null)&&this.ios.equals(rhs.ios))))&&((this.web == rhs.web)||((this.web!= null)&&this.web.equals(rhs.web))))&&((this.android == rhs.android)||((this.android!= null)&&this.android.equals(rhs.android))));
    }

}
