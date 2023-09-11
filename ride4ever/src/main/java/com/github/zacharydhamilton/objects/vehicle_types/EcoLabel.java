
package com.github.zacharydhamilton.objects.vehicle_types;

import java.util.HashMap;
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
    "country_code",
    "eco_sticker"
})
public class EcoLabel {

    /**
     *  Country code following the ISO 3166-1 alpha-2 notation. Added in v2.3.
     * 
     */
    @JsonProperty("country_code")
    @JsonPropertyDescription(" Country code following the ISO 3166-1 alpha-2 notation. Added in v2.3.")
    private String countryCode;
    /**
     *  Name of the eco label. Added in v2.3.
     * 
     */
    @JsonProperty("eco_sticker")
    @JsonPropertyDescription(" Name of the eco label. Added in v2.3.")
    private String ecoSticker;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *  Country code following the ISO 3166-1 alpha-2 notation. Added in v2.3.
     * 
     */
    @JsonProperty("country_code")
    public String getCountryCode() {
        return countryCode;
    }

    /**
     *  Country code following the ISO 3166-1 alpha-2 notation. Added in v2.3.
     * 
     */
    @JsonProperty("country_code")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     *  Name of the eco label. Added in v2.3.
     * 
     */
    @JsonProperty("eco_sticker")
    public String getEcoSticker() {
        return ecoSticker;
    }

    /**
     *  Name of the eco label. Added in v2.3.
     * 
     */
    @JsonProperty("eco_sticker")
    public void setEcoSticker(String ecoSticker) {
        this.ecoSticker = ecoSticker;
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
        sb.append(EcoLabel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("countryCode");
        sb.append('=');
        sb.append(((this.countryCode == null)?"<null>":this.countryCode));
        sb.append(',');
        sb.append("ecoSticker");
        sb.append('=');
        sb.append(((this.ecoSticker == null)?"<null>":this.ecoSticker));
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
        result = ((result* 31)+((this.ecoSticker == null)? 0 :this.ecoSticker.hashCode()));
        result = ((result* 31)+((this.countryCode == null)? 0 :this.countryCode.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EcoLabel) == false) {
            return false;
        }
        EcoLabel rhs = ((EcoLabel) other);
        return ((((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&((this.ecoSticker == rhs.ecoSticker)||((this.ecoSticker!= null)&&this.ecoSticker.equals(rhs.ecoSticker))))&&((this.countryCode == rhs.countryCode)||((this.countryCode!= null)&&this.countryCode.equals(rhs.countryCode))));
    }

}
