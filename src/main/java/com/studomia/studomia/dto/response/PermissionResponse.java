package com.studomia.studomia.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.studomia.studomia.dao.entities.Student;
import com.studomia.studomia.dto.request.Role;

import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PermissionResponse extends Response{

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    private Set<Role> enrolledRoles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Role> getEnrolledRoles() {
        return enrolledRoles;
    }

    public void setEnrolledRoles(Set<Role> enrolledRoles) {
        this.enrolledRoles = enrolledRoles;
    }
}
