package com.jinfei.jfmm.model;

public class mMain extends SearchColum {
    private String fId;

    private String modelId;

    private String fNo;

    private String castTypeId;

    private String fName;

    private String fLable;

    private String prodClassifyName;

    private String specName;

    private String custName;

    private String comp_parts;

    private Boolean amortization;

    private mModel mModel;

    public String getComp_parts() {
        return comp_parts;
    }

    public void setComp_parts(String comp_parts) {
        this.comp_parts = comp_parts;
    }

    public String getProdClassifyName() {
        return prodClassifyName;
    }

    public void setProdClassifyName(String prodClassifyName) {
        this.prodClassifyName = prodClassifyName;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfLable() {
        return fLable;
    }

    public void setfLable(String fLable) {
        this.fLable = fLable;
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getfNo() {
        return fNo;
    }

    public void setfNo(String fNo) {
        this.fNo = fNo;
    }

    public String getCastTypeId() {
        return castTypeId;
    }

    public void setCastTypeId(String castTypeId) {
        this.castTypeId = castTypeId;
    }

    public Boolean getAmortization() {
        return amortization;
    }

    public void setAmortization(Boolean amortization) {
        this.amortization = amortization;
    }

    public com.jinfei.jfmm.model.mModel getmModel() {
        return mModel;
    }

    public void setmModel(com.jinfei.jfmm.model.mModel mModel) {
        this.mModel = mModel;
    }
}