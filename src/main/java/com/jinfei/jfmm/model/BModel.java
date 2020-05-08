package com.jinfei.jfmm.model;

public class BModel extends SearchColum {
    private String f_id;
    private String f_name;
    private String prod_classify_id;
    private String prod_spec_id;
    private String classify_no;
    private String mould_spec_id;
    private String cust_id;

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getProd_classify_id() {
        return prod_classify_id;
    }

    public void setProd_classify_id(String prod_classify_id) {
        this.prod_classify_id = prod_classify_id;
    }

    public String getProd_spec_id() {
        return prod_spec_id;
    }

    public void setProd_spec_id(String prod_spec_id) {
        this.prod_spec_id = prod_spec_id;
    }

    public String getClassify_no() {
        return classify_no;
    }

    public void setClassify_no(String classify_no) {
        this.classify_no = classify_no;
    }

    public String getMould_spec_id() {
        return mould_spec_id;
    }

    public void setMould_spec_id(String mould_spec_id) {
        this.mould_spec_id = mould_spec_id;
    }

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    @Override
    public String toString() {
        return "BModel{" +
                "f_id='" + f_id + '\'' +
                ", f_name='" + f_name + '\'' +
                ", prod_classify_id='" + prod_classify_id + '\'' +
                ", prod_spec_id='" + prod_spec_id + '\'' +
                ", classify_no='" + classify_no + '\'' +
                ", mould_spec_id='" + mould_spec_id + '\'' +
                '}';
    }
}
