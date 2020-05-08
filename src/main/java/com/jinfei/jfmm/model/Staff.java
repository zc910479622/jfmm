package com.jinfei.jfmm.model;

public class Staff extends SearchColum{
    private String f_id;
    private String f_name;
    private String f_no;
    private String id_no;
    private String f_post;

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

    public String getF_no() {
        return f_no;
    }

    public void setF_no(String f_no) {
        this.f_no = f_no;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    public String getF_post() {
        return f_post;
    }

    public void setF_post(String f_post) {
        this.f_post = f_post;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "f_id='" + f_id + '\'' +
                ", f_name='" + f_name + '\'' +
                ", f_no='" + f_no + '\'' +
                ", id_no='" + id_no + '\'' +
                ", f_post='" + f_post + '\'' +
                '}';
    }
}
