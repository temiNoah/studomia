package com.studomia.studomia.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Login {

    private String username;
    private String password;


}
