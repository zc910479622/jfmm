package com.jinfei.jfmm.model;

public class kShelf {
    private String fId;

    private String areaId;

    private String fName;

    private Boolean is_state;

    public kShelf() {
    }

    public kShelf(String fId, String fName, String fPid, Boolean status) {
        this.fId = fId;
        this.fName = fName;
        this.areaId = fPid;
        this.is_state = status;
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
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