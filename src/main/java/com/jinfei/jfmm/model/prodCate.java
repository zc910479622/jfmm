package com.jinfei.jfmm.model;

public class prodCate {
    private String prod_classify_id;

    private String f_name;

    private String classify_no;

    private String NUMS;

    private String assets_state;

    private String location;

    private String cost;

    public String getProd_classify_id() {
        return prod_classify_id;
    }

    public void setProd_classify_id(String prod_classify_id) {
        this.prod_classify_id = prod_classify_id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getClassify_no() {
        return classify_no;
    }

    public void setClassify_no(String classify_no) {
        this.classify_no = classify_no;
    }

    public String getNUMS() {
        return NUMS;
    }

    public void setNUMS(String NUMS) {
        this.NUMS = NUMS;
    }

    public String getAssets_state() {
        return assets_state;
    }

    public void setAssets_state(String assets_state) {
        this.assets_state = assets_state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "prodCate{" +
                "prod_classify_id='" + prod_classify_id + '\'' +
                ", f_name='" + f_name + '\'' +
                ", classify_no='" + classify_no + '\'' +
                ", NUMS='" + NUMS + '\'' +
                ", assets_state='" + assets_state + '\'' +
                ", location='" + location + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
