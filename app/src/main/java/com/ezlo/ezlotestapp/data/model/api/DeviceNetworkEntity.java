package com.ezlo.ezlotestapp.data.model.api;

import com.ezlo.ezlotestapp.data.model.view.Device;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class DeviceNetworkEntity implements Comparable<DeviceNetworkEntity>{

    @SerializedName("PK_Device")
    private long pkDevice;

    @SerializedName("MacAddress")
    private String macAddress;

    @SerializedName("PK_DeviceType")
    private int deviceType;

    @SerializedName("PK_DeviceSubType")
    private int deviceSubType;

    @SerializedName("Firmware")
    private String firmWare;

    @SerializedName("Server_Device")
    private String serverDevice;

    @SerializedName("Server_Event")
    private String serverEvent;

    @SerializedName("Server_Account")
    private String serverAccount;

    @SerializedName("InternalIP")
    private String internalIp;

    @SerializedName("LastAliveReported")
    private String lastAliveReported;

    @SerializedName("Platform")
    private String platform;

    public long getPkDevice() {
        return pkDevice;
    }

    public void setPkDevice(long pkDevice) {
        this.pkDevice = pkDevice;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public int getDeviceSubType() {
        return deviceSubType;
    }

    public void setDeviceSubType(int deviceSubType) {
        this.deviceSubType = deviceSubType;
    }

    public String getFirmWare() {
        return firmWare;
    }

    public void setFirmWare(String firmWare) {
        this.firmWare = firmWare;
    }

    public String getServerDevice() {
        return serverDevice;
    }

    public void setServerDevice(String serverDevice) {
        this.serverDevice = serverDevice;
    }

    public String getServerEvent() {
        return serverEvent;
    }

    public void setServerEvent(String serverEvent) {
        this.serverEvent = serverEvent;
    }

    public String getServerAccount() {
        return serverAccount;
    }

    public void setServerAccount(String serverAccount) {
        this.serverAccount = serverAccount;
    }

    public String getInternalIp() {
        return internalIp;
    }

    public void setInternalIp(String internalIp) {
        this.internalIp = internalIp;
    }

    public String getLastAliveReported() {
        return lastAliveReported;
    }

    public void setLastAliveReported(String lastAliveReported) {
        this.lastAliveReported = lastAliveReported;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public int compareTo(DeviceNetworkEntity device) {
        if(device.getPkDevice() >= this.getPkDevice()){
            return -1;
        }
        return 1;
    }
}
