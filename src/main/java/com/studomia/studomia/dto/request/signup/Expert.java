package com.studomia.studomia.dto.request.signup;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.studomia.studomia.dto.request.Course;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

/*** Signup request model **/
//@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Expert {

    @ApiModelProperty(hidden = true)
    private Long expertId=0l;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phoneNumber")
    @NotNull(message = "phone  cannot be null")
    @Min(value = 11, message = "phone should not be less than 15")
    @Max(value = 11, message = "phone should not be greater than 11")
    private String phoneNumber ;

    @JsonProperty("age")
    @NotNull(message = "age  cannot be null")
    @Min(value = 15, message = "Age should not be less than 15")
    @Max(value = 65, message = "Age should not be greater than 65")
    private String age;

    @Column(name="username")
    protected String username;

    @Column(name="password")
    protected String password;


    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getUsername()
    {
        return this.username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getPassword()
    {
        return this.password;
    }

    public Long getExpertId() {
        return expertId;
    }

    public void setExpertId(Long expertId) {
        this.expertId = expertId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
