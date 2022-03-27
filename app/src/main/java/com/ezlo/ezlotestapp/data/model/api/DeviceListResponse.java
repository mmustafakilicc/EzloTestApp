package com.ezlo.ezlotestapp.data.model.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeviceListResponse {

    @SerializedName("Devices")
    private List<DeviceNetworkEntity> devices;

    public List<DeviceNetworkEntity> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceNetworkEntity> devices) {
        this.devices = devices;
    }
}
