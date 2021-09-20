package com.studomia.studomia.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.studomia.studomia.dto.request.Permission;
import com.studomia.studomia.dto.request.signup.Admin;
import com.studomia.studomia.dto.request.signup.Expert;
import com.studomia.studomia.dto.request.signup.Student;

import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleResponse extends Response
{

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    Set<Permission> permissions ;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Permission> getPermissions()
    {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions)
    {
        this.permissions = permissions;
    }

}
