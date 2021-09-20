package com.studomia.studomia.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.studomia.studomia.model.DeviceInfo;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Login {

    private String username;

    @NotNull(message = "Login password cannot be blank")
    @ApiModelProperty(value = "Valid user password", required = true, allowableValues = "NonEmpty String")
    private String password;

    @Valid
    @NotNull(message = "Device info cannot be null")
    @ApiModelProperty(value = "Device info", required = true, dataType = "object", allowableValues = "A valid " +
            "deviceInfo object")
    private DeviceInfo deviceInfo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
}
