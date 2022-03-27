package com.ezlo.ezlotestapp.data.model.view;

import com.ezlo.ezlotestapp.R;

public class Device implements Comparable<Device> {

    private String name;

    private long pk;

    private String sn;

    private String macAddress;

    private String firmWare;

    private String model;

    private int resource;

    private boolean editable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getFirmWare() {
        return firmWare;
    }

    public void setFirmWare(String firmWare) {
        this.firmWare = firmWare;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public int compareTo(Device device) {
        if(device.getPk() >= this.getPk()){
            return -1;
        }
        return 1;
    }

    public int getResource() {
        if(model.equals("Sercomm G450")){
            return R.drawable.vera_plus_big;
        }else if(model.equals("Sercomm G550")){
            return R.drawable.vera_secure_big;
        }
        return R.drawable.vera_edge_big;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public long getPk() {
        return pk;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
}
