package com.jinfei.jfmm.model;

import java.util.Date;

public class aDict {
    private String fId;

    private String fLable;

    private String fValue;

    private String fPid;

    private Boolean status;

    private String editUser;

    private Date editTime;

    @Override
    public String toString() {
        return "aDict{" +
                "fId='" + fId + '\'' +
                ", fLable='" + fLable + '\'' +
                ", fValue='" + fValue + '\'' +
                ", fPid='" + fPid + '\'' +
                ", status=" + status +
                ", editUser='" + editUser + '\'' +
                ", editTime=" + editTime +
                '}';
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getfLable() {
        return fLable;
    }

    public void setfLable(String fLable) {
        this.fLable = fLable;
    }

    public String getfValue() {
        return fValue;
    }

    public void setfValue(String fValue) {
        this.fValue = fValue;
    }

    public String getfPid() {
        return fPid;
    }

    public void setfPid(String fPid) {
        this.fPid = fPid;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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