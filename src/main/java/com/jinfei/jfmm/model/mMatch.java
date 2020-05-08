package com.jinfei.jfmm.model;

import java.util.Date;

public class mMatch extends SearchColum {
    private String fId;

    private String mMainId;

    private String bMatchNo;

    private String editUser;

    private Date editTime;

    private String mMainName;

    private String partsCate;

    private String mModelName;

    private String compParts;

    private String prodCate;

    private String castType;

    public String getCastType() {
        return castType;
    }

    public void setCastType(String castType) {
        this.castType = castType;
    }

    public String getMatchNo() {
        return matchNo;
    }

    public void setMatchNo(String matchNo) {
        this.matchNo = matchNo;
    }

    private String matchNo;

    public String getCompParts() {
        return compParts;
    }

    public void setCompParts(String compParts) {
        this.compParts = compParts;
    }

    public String getmMainName() {
        return mMainName;
    }

    public void setmMainName(String mMainName) {
        this.mMainName = mMainName;
    }

    public String getPartsCate() {
        return partsCate;
    }

    public void setPartsCate(String partsCate) {
        this.partsCate = partsCate;
    }

    public String getmModelName() {
        return mModelName;
    }

    public void setmModelName(String mModelName) {
        this.mModelName = mModelName;
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getmMainId() {
        return mMainId;
    }

    public void setmMainId(String mMainId) {
        this.mMainId = mMainId;
    }

    public String getbMatchNo() {
        return bMatchNo;
    }

    public void setbMatchNo(String bMatchNo) {
        this.bMatchNo = bMatchNo;
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

    public String getProdCate() {
        return prodCate;
    }

    public void setProdCate(String prodCate) {
        this.prodCate = prodCate;
    }

    @Override
    public String toString() {
        return "mMatch{" +
                "fId='" + fId + '\'' +
                ", mMainId='" + mMainId + '\'' +
                ", bMatchNo='" + bMatchNo + '\'' +
                ", editUser='" + editUser + '\'' +
                ", editTime=" + editTime +
                ", mMainName='" + mMainName + '\'' +
                ", partsCate='" + partsCate + '\'' +
                ", mModelName='" + mModelName + '\'' +
                ", compParts='" + compParts + '\'' +
                ", matchNo='" + matchNo + '\'' +
                '}';
    }
}