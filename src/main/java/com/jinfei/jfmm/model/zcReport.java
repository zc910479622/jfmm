package com.jinfei.jfmm.model;

import java.util.Date;

public class zcReport extends SearchColum{
    private String mMainName;

    private String mModelName;

    private Integer mMainNums;

    private String partName;

    private String seatName;

    private String partNums;

    private String location;

    private String rackPosition;

    private String requestOrg;

    private String assetsState;

    private Integer isComplete;

    private Boolean amortization;

    private String castTypeId;

    private String prodSpecId;

    private Double partPrices;

    private Integer wCost;

    private Date amortizationTime;

    public String getmMainName() {
        return mMainName;
    }

    public void setmMainName(String mMainName) {
        this.mMainName = mMainName;
    }

    public String getmModelName() {
        return mModelName;
    }

    public void setmModelName(String mModelName) {
        this.mModelName = mModelName;
    }

    public Integer getmMainNums() {
        return mMainNums;
    }

    public void setmMainNums(Integer mMainNums) {
        this.mMainNums = mMainNums;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public String getPartNums() {
        return partNums;
    }

    public void setPartNums(String partNums) {
        this.partNums = partNums;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRackPosition() {
        return rackPosition;
    }

    public void setRackPosition(String rackPosition) {
        this.rackPosition = rackPosition;
    }

    public String getRequestOrg() {
        return requestOrg;
    }

    public void setRequestOrg(String requestOrg) {
        this.requestOrg = requestOrg;
    }

    public String getAssetsState() {
        return assetsState;
    }

    public void setAssetsState(String assetsState) {
        this.assetsState = assetsState;
    }

    public Integer getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }

    public Boolean getAmortization() {
        return amortization;
    }

    public void setAmortization(Boolean amortization) {
        this.amortization = amortization;
    }

    public String getCastTypeId() {
        return castTypeId;
    }

    public void setCastTypeId(String castTypeId) {
        this.castTypeId = castTypeId;
    }

    public String getProdSpecId() {
        return prodSpecId;
    }

    public void setProdSpecId(String prodSpecId) {
        this.prodSpecId = prodSpecId;
    }

    public Double getPartPrice() {
        return partPrices;
    }

    public void setPartPrice(Double partPrices) {
        this.partPrices = partPrices;
    }

    public Double getPartPrices() {
        return partPrices;
    }

    public void setPartPrices(Double partPrices) {
        this.partPrices = partPrices;
    }

    public Integer getwCost() {
        return wCost;
    }

    public void setwCost(Integer wCost) {
        this.wCost = wCost;
    }

    public Date getAmortizationTime() {
        return amortizationTime;
    }

    public void setAmortizationTime(Date amortizationTime) {
        this.amortizationTime = amortizationTime;
    }
}