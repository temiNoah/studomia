package com.studomia.studomia.dto.request.signup;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.studomia.studomia.dao.entities.Role;
import com.studomia.studomia.dao.entities.User;
import com.studomia.studomia.dto.request.Course;
import com.studomia.studomia.dto.request.Request;
import com.studomia.studomia.security.socialLogin.AuthProvider;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/*** Signup request model **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Student extends User
{
    @ApiModelProperty(hidden = true)
    private Long studentId=0l;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phoneNumber")
    @NotNull(message = "phone  cannot be null")
    private String phoneNumber ;


    @Column(name="username")
    protected String username;

    @Column(name="password")
    protected String password;

    @JsonProperty("age")
    @NotNull(message = "age  cannot be null")
    @Min(value = 15, message = "Age should not be less than 15")
    @Max(value = 65, message = "Age should not be greater than 65")
    private String age;


    @ManyToMany(targetEntity = Role.class, cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH} )
    @JoinTable(
            name = "student_role",
            joinColumns = @JoinColumn(name = "student_id", updatable=true ,referencedColumnName = "student_id",nullable = true),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "role_id",updatable = true,nullable = true )
    )
    private Set<Role> roles;


    @Column(name = "emailVerified", nullable = false)
    private Boolean emailVerified = false;

    @Column(name = "is_active", nullable = false)
    private Boolean active;


    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    @Override
    public Boolean getActive() {
        return active;
    }

    @Override
    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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

    @Override
    public String toString(){
        return String.format("Student[ first name='%s' , last name='%s' , age='%s']",firstName,lastName,age);
    }
}
