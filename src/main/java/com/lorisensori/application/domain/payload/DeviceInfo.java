package com.lorisensori.application.domain.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.lorisensori.application.enums.DeviceType;
import com.lorisensori.application.validator.NullOrNotBlank;

import io.swagger.annotations.ApiModelProperty;

public class DeviceInfo {

	@NotBlank(message = "Device id cannot be blank")
	@ApiModelProperty(value = "Device Id", required = true, dataType = "string", allowableValues = "Non empty string")
	private String deviceId;

	@NotNull(message = "Device type cannot be null")
	@ApiModelProperty(value = "Device type Android/iOS", required = true, dataType = "string", allowableValues =
			"DEVICE_TYPE_ANDROID, DEVICE_TYPE_IOS")
	private DeviceType deviceType;

	@NullOrNotBlank(message = "Device notification token can be null but not blank")
	@ApiModelProperty(value = "Device notification id", dataType = "string", allowableValues = "Non empty string")
	private String notificationToken;

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
}