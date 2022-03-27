package com.ezlo.ezlotestapp.utils.mapper;

import com.ezlo.ezlotestapp.data.model.api.DeviceNetworkEntity;
import com.ezlo.ezlotestapp.data.model.view.Device;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//converting network and view objects to each other
public class DeviceMapper implements ModelMapper<DeviceNetworkEntity, Device> {

    private int row;
    public DeviceMapper(){
        row = 1;
    }
    @Override
    public DeviceNetworkEntity mapFromView(Device device) {
        return new DeviceNetworkEntity();
    }

    @Override
    public Device mapToView(DeviceNetworkEntity deviceNetworkEntity) {
        Device device = new Device();
        device.setFirmWare(deviceNetworkEntity.getFirmWare());
        device.setMacAddress(deviceNetworkEntity.getMacAddress());
        device.setModel(deviceNetworkEntity.getPlatform());
        device.setSn(String.valueOf(deviceNetworkEntity.getPkDevice()));
        device.setPk(deviceNetworkEntity.getPkDevice());
        device.setName("Home Number " + row);
        row++;
        return device;
    }

    public List<Device> mapToViewList(List<DeviceNetworkEntity> deviceNetworkEntityList) {
        Collections.sort(deviceNetworkEntityList);
        return deviceNetworkEntityList.stream().map(this::mapToView).collect(Collectors.toList());
    }
}
