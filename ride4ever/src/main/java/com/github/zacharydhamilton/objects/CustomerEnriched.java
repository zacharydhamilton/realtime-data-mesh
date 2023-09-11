
package com.github.zacharydhamilton.objects;

import java.util.HashMap;
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
 * customer_enriched
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "customer_id",
    "first_name",
    "last_name",
    "email",
    "phone_number",
    "age",
    "gender",
    "income",
    "education_level",
    "marital_status",
    "ethnicity"
})
public class CustomerEnriched {

    /**
     * Unique identifier for the customer
     * (Required)
     * 
     */
    @JsonProperty("customer_id")
    @JsonPropertyDescription("Unique identifier for the customer")
    private String customerId;
    /**
     * First name of the customer
     * (Required)
     * 
     */
    @JsonProperty("first_name")
    @JsonPropertyDescription("First name of the customer")
    private String firstName;
    /**
     * Last name of the customer
     * (Required)
     * 
     */
    @JsonProperty("last_name")
    @JsonPropertyDescription("Last name of the customer")
    private String lastName;
    /**
     * Email address of the customer
     * (Required)
     * 
     */
    @JsonProperty("email")
    @JsonPropertyDescription("Email address of the customer")
    private String email;
    /**
     * Phone number of the customer
     * 
     */
    @JsonProperty("phone_number")
    @JsonPropertyDescription("Phone number of the customer")
    private String phoneNumber;
    /**
     * Age of the customer
     * (Required)
     * 
     */
    @JsonProperty("age")
    @JsonPropertyDescription("Age of the customer")
    private Integer age;
    /**
     * Gender of the customer
     * (Required)
     * 
     */
    @JsonProperty("gender")
    @JsonPropertyDescription("Gender of the customer")
    private CustomerEnriched.Gender gender;
    /**
     * Annual income of the customer
     * 
     */
    @JsonProperty("income")
    @JsonPropertyDescription("Annual income of the customer")
    private Integer income;
    /**
     * Highest level of education
     * 
     */
    @JsonProperty("education_level")
    @JsonPropertyDescription("Highest level of education")
    private CustomerEnriched.EducationLevel educationLevel;
    /**
     * Marital status of the customer
     * 
     */
    @JsonProperty("marital_status")
    @JsonPropertyDescription("Marital status of the customer")
    private CustomerEnriched.MaritalStatus maritalStatus;
    /**
     * Ethnic background of the customer
     * 
     */
    @JsonProperty("ethnicity")
    @JsonPropertyDescription("Ethnic background of the customer")
    private String ethnicity;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Unique identifier for the customer
     * (Required)
     * 
     */
    @JsonProperty("customer_id")
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Unique identifier for the customer
     * (Required)
     * 
     */
    @JsonProperty("customer_id")
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * First name of the customer
     * (Required)
     * 
     */
    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    /**
     * First name of the customer
     * (Required)
     * 
     */
    @JsonProperty("first_name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Last name of the customer
     * (Required)
     * 
     */
    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    /**
     * Last name of the customer
     * (Required)
     * 
     */
    @JsonProperty("last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Email address of the customer
     * (Required)
     * 
     */
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    /**
     * Email address of the customer
     * (Required)
     * 
     */
    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Phone number of the customer
     * 
     */
    @JsonProperty("phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Phone number of the customer
     * 
     */
    @JsonProperty("phone_number")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Age of the customer
     * (Required)
     * 
     */
    @JsonProperty("age")
    public Integer getAge() {
        return age;
    }

    /**
     * Age of the customer
     * (Required)
     * 
     */
    @JsonProperty("age")
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Gender of the customer
     * (Required)
     * 
     */
    @JsonProperty("gender")
    public CustomerEnriched.Gender getGender() {
        return gender;
    }

    /**
     * Gender of the customer
     * (Required)
     * 
     */
    @JsonProperty("gender")
    public void setGender(CustomerEnriched.Gender gender) {
        this.gender = gender;
    }

    /**
     * Annual income of the customer
     * 
     */
    @JsonProperty("income")
    public Integer getIncome() {
        return income;
    }

    /**
     * Annual income of the customer
     * 
     */
    @JsonProperty("income")
    public void setIncome(Integer income) {
        this.income = income;
    }

    /**
     * Highest level of education
     * 
     */
    @JsonProperty("education_level")
    public CustomerEnriched.EducationLevel getEducationLevel() {
        return educationLevel;
    }

    /**
     * Highest level of education
     * 
     */
    @JsonProperty("education_level")
    public void setEducationLevel(CustomerEnriched.EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    /**
     * Marital status of the customer
     * 
     */
    @JsonProperty("marital_status")
    public CustomerEnriched.MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Marital status of the customer
     * 
     */
    @JsonProperty("marital_status")
    public void setMaritalStatus(CustomerEnriched.MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * Ethnic background of the customer
     * 
     */
    @JsonProperty("ethnicity")
    public String getEthnicity() {
        return ethnicity;
    }

    /**
     * Ethnic background of the customer
     * 
     */
    @JsonProperty("ethnicity")
    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
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
        sb.append(CustomerEnriched.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("customerId");
        sb.append('=');
        sb.append(((this.customerId == null)?"<null>":this.customerId));
        sb.append(',');
        sb.append("firstName");
        sb.append('=');
        sb.append(((this.firstName == null)?"<null>":this.firstName));
        sb.append(',');
        sb.append("lastName");
        sb.append('=');
        sb.append(((this.lastName == null)?"<null>":this.lastName));
        sb.append(',');
        sb.append("email");
        sb.append('=');
        sb.append(((this.email == null)?"<null>":this.email));
        sb.append(',');
        sb.append("phoneNumber");
        sb.append('=');
        sb.append(((this.phoneNumber == null)?"<null>":this.phoneNumber));
        sb.append(',');
        sb.append("age");
        sb.append('=');
        sb.append(((this.age == null)?"<null>":this.age));
        sb.append(',');
        sb.append("gender");
        sb.append('=');
        sb.append(((this.gender == null)?"<null>":this.gender));
        sb.append(',');
        sb.append("income");
        sb.append('=');
        sb.append(((this.income == null)?"<null>":this.income));
        sb.append(',');
        sb.append("educationLevel");
        sb.append('=');
        sb.append(((this.educationLevel == null)?"<null>":this.educationLevel));
        sb.append(',');
        sb.append("maritalStatus");
        sb.append('=');
        sb.append(((this.maritalStatus == null)?"<null>":this.maritalStatus));
        sb.append(',');
        sb.append("ethnicity");
        sb.append('=');
        sb.append(((this.ethnicity == null)?"<null>":this.ethnicity));
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
        result = ((result* 31)+((this.income == null)? 0 :this.income.hashCode()));
        result = ((result* 31)+((this.lastName == null)? 0 :this.lastName.hashCode()));
        result = ((result* 31)+((this.gender == null)? 0 :this.gender.hashCode()));
        result = ((result* 31)+((this.ethnicity == null)? 0 :this.ethnicity.hashCode()));
        result = ((result* 31)+((this.firstName == null)? 0 :this.firstName.hashCode()));
        result = ((result* 31)+((this.phoneNumber == null)? 0 :this.phoneNumber.hashCode()));
        result = ((result* 31)+((this.educationLevel == null)? 0 :this.educationLevel.hashCode()));
        result = ((result* 31)+((this.customerId == null)? 0 :this.customerId.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.email == null)? 0 :this.email.hashCode()));
        result = ((result* 31)+((this.age == null)? 0 :this.age.hashCode()));
        result = ((result* 31)+((this.maritalStatus == null)? 0 :this.maritalStatus.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CustomerEnriched) == false) {
            return false;
        }
        CustomerEnriched rhs = ((CustomerEnriched) other);
        return (((((((((((((this.income == rhs.income)||((this.income!= null)&&this.income.equals(rhs.income)))&&((this.lastName == rhs.lastName)||((this.lastName!= null)&&this.lastName.equals(rhs.lastName))))&&((this.gender == rhs.gender)||((this.gender!= null)&&this.gender.equals(rhs.gender))))&&((this.ethnicity == rhs.ethnicity)||((this.ethnicity!= null)&&this.ethnicity.equals(rhs.ethnicity))))&&((this.firstName == rhs.firstName)||((this.firstName!= null)&&this.firstName.equals(rhs.firstName))))&&((this.phoneNumber == rhs.phoneNumber)||((this.phoneNumber!= null)&&this.phoneNumber.equals(rhs.phoneNumber))))&&((this.educationLevel == rhs.educationLevel)||((this.educationLevel!= null)&&this.educationLevel.equals(rhs.educationLevel))))&&((this.customerId == rhs.customerId)||((this.customerId!= null)&&this.customerId.equals(rhs.customerId))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.email == rhs.email)||((this.email!= null)&&this.email.equals(rhs.email))))&&((this.age == rhs.age)||((this.age!= null)&&this.age.equals(rhs.age))))&&((this.maritalStatus == rhs.maritalStatus)||((this.maritalStatus!= null)&&this.maritalStatus.equals(rhs.maritalStatus))));
    }


    /**
     * Highest level of education
     * 
     */
    public enum EducationLevel {

        HIGH_SCHOOL("High School"),
        SOME_COLLEGE("Some College"),
        BACHELOR_S("Bachelor's"),
        MASTER_S("Master's"),
        DOCTORATE("Doctorate");
        private final String value;
        private final static Map<String, CustomerEnriched.EducationLevel> CONSTANTS = new HashMap<String, CustomerEnriched.EducationLevel>();

        static {
            for (CustomerEnriched.EducationLevel c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private EducationLevel(String value) {
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
        public static CustomerEnriched.EducationLevel fromValue(String value) {
            CustomerEnriched.EducationLevel constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }


    /**
     * Gender of the customer
     * 
     */
    public enum Gender {

        MALE("Male"),
        FEMALE("Female"),
        OTHER("Other"),
        PREFER_NOT_TO_SAY("Prefer not to say");
        private final String value;
        private final static Map<String, CustomerEnriched.Gender> CONSTANTS = new HashMap<String, CustomerEnriched.Gender>();

        static {
            for (CustomerEnriched.Gender c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Gender(String value) {
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
        public static CustomerEnriched.Gender fromValue(String value) {
            CustomerEnriched.Gender constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }


    /**
     * Marital status of the customer
     * 
     */
    public enum MaritalStatus {

        SINGLE("Single"),
        MARRIED("Married"),
        DIVORCED("Divorced"),
        WIDOWED("Widowed");
        private final String value;
        private final static Map<String, CustomerEnriched.MaritalStatus> CONSTANTS = new HashMap<String, CustomerEnriched.MaritalStatus>();

        static {
            for (CustomerEnriched.MaritalStatus c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private MaritalStatus(String value) {
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
        public static CustomerEnriched.MaritalStatus fromValue(String value) {
            CustomerEnriched.MaritalStatus constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
