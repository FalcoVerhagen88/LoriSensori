package com.lorisensori.application.domain.payload;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Logout request", description = "The logout request payload")
public class LogOutRequest {

	@Valid
	@NotNull(message = "Device info cannot be null")
	@ApiModelProperty(value = "Device info", required = true, dataType = "object", allowableValues = "A valid " +
			"deviceInfo object")
	private DeviceInfo deviceInfo;

	public LogOutRequest() {
	}

	public LogOutRequest(DeviceInfo deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public DeviceInfo getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(DeviceInfo deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
}
