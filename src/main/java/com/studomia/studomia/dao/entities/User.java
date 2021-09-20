package com.studomia.studomia.dao.entities;

import com.studomia.studomia.model.audit.DateAudit;

import javax.persistence.*;

//import org.springframework.data.annotation.Id;
import javax.validation.constraints.*;

@MappedSuperclass
public  class User extends DateAudit {

//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    @Column(name="ID")
//    private long id;

    @NotNull
    @NotBlank
    @Email
   // @Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
    @Column(name="EMAIL")
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 20)
    @Column(name="FIRST_NAME")
    private String firstName;

    @Size(min = 1, max = 20)
    @Column(name="LAST_NAME")
    private String lastName;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]{3}", message = "length must be greater than 3")
    @Column(name="PASSWORD")
    private String password;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[0-9]{11}", message = "length must be greater than 11")
    @Column(name="PHONE_NUMBER")
    private String phoneNumber;


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name="EMAIL_VERIFIED" ,nullable = false)
    private Boolean emailVerified = false;

    @Column(name="IS_ACTIVE", nullable = false)
    private Boolean isActive = false;


    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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




}
