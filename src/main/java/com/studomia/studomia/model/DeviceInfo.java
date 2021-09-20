package com.studomia.studomia.model;


import com.studomia.studomia.validation.annotation.NullOrNotBlank;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DeviceInfo {

    @NotBlank(message = "Device id cannot be blank")
    @ApiModelProperty(value = "Device Id", required = true, dataType = "string", allowableValues = "Non empty string")
    private String deviceId;

    @NotNull(message = "Device type cannot be null")
    @ApiModelProperty(value = "Device type Android/iOS", required = true, dataType = "string", allowableValues =" DESKTOP_OR_LAPTOP,PHONE,TABLET,UNKNOWN")
    private DeviceType deviceType;

    @NullOrNotBlank(message = "Device notification token can be null but not blank")
    @ApiModelProperty(value = "Device notification id", dataType = "string", allowableValues = "Non empty string")
    private String notificationToken;


    private String name;

    private OS os;


    public DeviceInfo() {
    }

    public DeviceInfo(String deviceId, DeviceType deviceTypem, String notificationToken) {
        this.deviceId = deviceId;
        deviceType = deviceType;
        notificationToken = notificationToken;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public String getNotificationToken() {
        return notificationToken;
    }

    public void setNotificationToken(String notificationToken) {
        this.notificationToken = notificationToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OS getOs() {
        return os;
    }

    public void setOs(OS os) {
        this.os = os;
    }
}
