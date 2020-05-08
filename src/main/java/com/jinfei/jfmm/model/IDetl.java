package com.jinfei.jfmm.model;

public class IDetl extends SearchColum {
    private String f_id;
    private String main_id;
    private String f_name;
    private String match_no;
    private String part_id;
    private Integer order_no;
    private String rece_time;
    private String seat_id;
    private String rece_edit_time;
    private String img_path;
    private Integer isHG;

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getMatch_no() {
        return match_no;
    }

    public void setMatch_no(String match_no) {
        this.match_no = match_no;
    }

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public String getMain_id() {
        return main_id;
    }

    public void setMain_id(String main_id) {
        this.main_id = main_id;
    }

    public String getPart_id() {
        return part_id;
    }

    public void setPart_id(String part_id) {
        this.part_id = part_id;
    }

    public Integer getOrder_no() {
        return order_no;
    }

    public void setOrder_no(Integer order_no) {
        this.order_no = order_no;
    }

    public String getRece_time() {
        return rece_time;
    }

    public void setRece_time(String rece_time) {
        this.rece_time = rece_time;
    }

    public String getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(String seat_id) {
        this.seat_id = seat_id;
    }

    public String getRece_edit_time() {
        return rece_edit_time;
    }

    public void setRece_edit_time(String rece_edit_time) {
        this.rece_edit_time = rece_edit_time;
    }


    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public Integer getIsHG() {
        return isHG;
    }

    public void setIsHG(Integer isHG) {
        this.isHG = isHG;
    }

    @Override
    public String toString() {
        return "IDetl{" +
                "f_id='" + f_id + '\'' +
                ", main_id='" + main_id + '\'' +
                ", f_name='" + f_name + '\'' +
                ", match_no='" + match_no + '\'' +
                ", part_id='" + part_id + '\'' +
                ", order_no=" + order_no +
                ", rece_time='" + rece_time + '\'' +
                ", seat_id='" + seat_id + '\'' +
                ", rece_edit_time='" + rece_edit_time + '\'' +
                ", img_path='" + img_path + '\'' +
                ", isHG='" + isHG + '\'' +
                '}';
    }
}
