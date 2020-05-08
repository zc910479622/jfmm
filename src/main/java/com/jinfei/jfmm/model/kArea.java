package com.jinfei.jfmm.model;

public class kArea {
    private String fId;

    private String houseId;

    private String fName;

    private Boolean is_state;

    public kArea() {
    }

    public kArea(String fId, String fName, String fPid, Boolean status) {
        this.fId = fId;
        this.fName = fName;
        this.houseId = fPid;
        this.is_state = status;
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public Boolean getIs_state() {
        return is_state;
    }

    public void setIs_state(Boolean is_state) {
        this.is_state = is_state;
    }
}