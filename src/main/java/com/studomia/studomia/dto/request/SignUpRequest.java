package com.studomia.studomia.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;



public class SignUpRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String firstName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
