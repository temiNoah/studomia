package com.studomia.studomia.dao.entities;


import com.studomia.studomia.model.audit.DateAudit;
import com.studomia.studomia.security.socialLogin.AuthProvider;
import com.studomia.studomia.utils.UserType;
import com.studomia.studomia.validation.annotation.NullOrNotBlank;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "SS0_USER")
@AllArgsConstructor
public class SSOuser extends DateAudit
{

    @Id
    @Column(name = "SSO_USER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", allocationSize = 1)
    private Long id;

    //@NaturalId
    @Column(name = "EMAIL", unique = true)
    @NotBlank(message = "User email cannot be null")
    private String email;

    @Column(name = "FIRST_NAME")
    @NullOrNotBlank(message = "First name can not be blank")
    private String firstName;

    @Column(name = "LAST_NAME")
    @NullOrNotBlank(message = "Last name can not be blank")
    private String lastName;

    private String imageUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    private UserType userType;

    public SSOuser(SSOuser user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();

    }

    public SSOuser() {

    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "SSOuser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", provider=" + provider +
                ", providerId='" + providerId + '\'' +
                ", userType=" + userType +
                '}';
    }
}
