package com.jinfei.jfmm.model;

import java.util.Date;

public class sRecord {
    private String fId;

    private String bMainId;

    private String matchNo;

    private String deptId;

    private String outSeatId;

    private String inSeatId;

    private String editUser;

    private Date editTime;

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getbMainId() {
        return bMainId;
    }

    public void setbMainId(String bMainId) {
        this.bMainId = bMainId;
    }

    public String getMatchNo() {
        return matchNo;
    }

    public void setMatchNo(String matchNo) {
        this.matchNo = matchNo;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getOutSeatId() {
        return outSeatId;
    }

    public void setOutSeatId(String outSeatId) {
        this.outSeatId = outSeatId;
    }

    public String getInSeatId() {
        return inSeatId;
    }

    public void setInSeatId(String inSeatId) {
        this.inSeatId = inSeatId;
    }

    public String getEditUser() {
        return editUser;
    }

    public void setEditUser(String editUser) {
        this.editUser = editUser;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
}