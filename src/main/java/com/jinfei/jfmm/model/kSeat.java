package com.jinfei.jfmm.model;

public class kSeat {
    private String fId;

    private String shelfId;

    private String fName;

    private Boolean is_state;

    public kSeat() {
    }

    public kSeat(String fId, String shelfId, String fName, Boolean is_state) {
        this.fId = fId;
        this.shelfId = shelfId;
        this.fName = fName;
        this.is_state = is_state;
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getShelfId() {
        return shelfId;
    }

    public void setShelfId(String shelfId) {
        this.shelfId = shelfId;
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

    @Override
    public String toString() {
        return "{" +
                "\"fId\":\"" + fId + '\"' +
                ", \"shelfId\":\"" + shelfId + '\"' +
                ", \"fName\":\"" + fName + '\"' +
                ", \"is_state\":" + is_state +
                '}';
    }


}