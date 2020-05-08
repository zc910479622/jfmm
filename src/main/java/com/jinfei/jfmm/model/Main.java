package com.jinfei.jfmm.model;

public class Main extends SearchColum {
    private String f_id;
    private String f_name;
    private String model_id;
    private String cast_type_id;
    private String make_fty_id;
    private String heat_fty_id;
    private String mould_mater_id;
    private Integer usage_max;
    private Double make_cost;
    private String d1;
    private String d2;
    private String d3;

    public Main() {
    }

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

    public String getModel_id() {
        return model_id;
    }

    public void setModel_id(String model_id) {
        this.model_id = model_id;
    }

    public String getCast_type_id() {
        return cast_type_id;
    }

    public void setCast_type_id(String cast_type_id) {
        this.cast_type_id = cast_type_id;
    }

    public String getMake_fty_id() {
        return make_fty_id;
    }

    public void setMake_fty_id(String make_fty_id) {
        this.make_fty_id = make_fty_id;
    }

    public String getHeat_fty_id() {
        return heat_fty_id;
    }

    public void setHeat_fty_id(String heat_fty_id) {
        this.heat_fty_id = heat_fty_id;
    }

    public String getMould_mater_id() {
        return mould_mater_id;
    }

    public void setMould_mater_id(String mould_mater_id) {
        this.mould_mater_id = mould_mater_id;
    }

    public Integer getUsage_max() {
        return usage_max;
    }

    public void setUsage_max(Integer usage_max) {
        this.usage_max = usage_max;
    }

    public Double getMake_cost() {
        return make_cost;
    }

    public void setMake_cost(Double make_cost) {
        this.make_cost = make_cost;
    }

    public String getD1() {
        return d1;
    }

    public void setD1(String d1) {
        this.d1 = d1;
    }

    public String getD2() {
        return d2;
    }

    public void setD2(String d2) {
        this.d2 = d2;
    }

    public String getD3() {
        return d3;
    }

    public void setD3(String d3) {
        this.d3 = d3;
    }

    @Override
    public String toString() {
        return "Main{" +
                "f_id='" + f_id + '\'' +
                ", f_name='" + f_name + '\'' +
                ", model_id='" + model_id + '\'' +
                ", cast_type_id='" + cast_type_id + '\'' +
                ", make_fty_id='" + make_fty_id + '\'' +
                ", heat_fty_id='" + heat_fty_id + '\'' +
                ", mould_mater_id='" + mould_mater_id + '\'' +
                ", usage_max=" + usage_max +
                ", make_cost=" + make_cost +
                ", d1=" + d1 +
                ", d2=" + d2 +
                ", d3=" + d3 +
                '}';
    }
}
