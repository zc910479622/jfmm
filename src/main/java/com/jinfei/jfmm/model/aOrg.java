package com.jinfei.jfmm.model;

import java.util.List;

public class aOrg {
    private String fId;

    private String fName;

    private String fPid;

    private byte isWh;

    private String fAddr;

    private Double fLng;

    private Double fLat;

    private byte fUsable;

    private List<aOrg> node;

    public List<aOrg> getNode() {
        return node;
    }

    public void setNode(List<aOrg> node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return "{" +
                "\"fId\":\"" + fId + '\"' +
                ", \"fName\":\"" + fName + '\"' +
                ", \"fPid\":\"" + fPid + '\"' +
                ", \"isWh\":\"" + isWh + '\"' +
                ", \"fAddr\":\"" + fAddr + '\"' +
                ", \"fLng\":" + fLng +
                ", \"fLat\":" + fLat +
                ", \"fUsable\":" + fUsable +
                '}';
    }

    public aOrg() {
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfPid() {
        return fPid;
    }

    public void setfPid(String fPid) {
        this.fPid = fPid;
    }

    public byte getIsWh() {
        return isWh;
    }

    public void setIsWh(byte isWh) {
        this.isWh = isWh;
    }

    public String getfAddr() {
        return fAddr;
    }

    public void setfAddr(String fAddr) {
        this.fAddr = fAddr;
    }

    public Double getfLng() {
        return fLng;
    }

    public void setfLng(Double fLng) {
        this.fLng = fLng;
    }

    public Double getfLat() {
        return fLat;
    }

    public void setfLat(Double fLat) {
        this.fLat = fLat;
    }

    public byte getfUsable() {
        return fUsable;
    }

    public void setfUsable(byte fUsable) {
        this.fUsable = fUsable;
    }
}