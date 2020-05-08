package com.jinfei.jfmm.model;

public class IMain extends SearchColum {
    private String f_id;
    private String f_type;
    private String bill_no;
    private String init_man_id;
    private String init_time;
    private String plan_out_time;
    private String plan_in_time;
    private String out_org_id;
    private String out_org_name;
    private String out_man_id;
    private String out_time;
    private String in_org_id;
    private String in_org_name;
    private String in_man_id;
    private String in_time;
    private Integer idetl_count;
    private String trans_man;
    private String trans_man_phone;
    private String car_plate;
    private String part_no;
    private Integer is_process;

    public Integer getIdetl_count() {
        return idetl_count;
    }

    public void setIdetl_count(Integer idetl_count) {
        this.idetl_count = idetl_count;
    }

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public String getF_type() {
        return f_type;
    }

    public void setF_type(String f_type) {
        this.f_type = f_type;
    }

    public String getBill_no() {
        return bill_no;
    }

    public void setBill_no(String bill_no) {
        this.bill_no = bill_no;
    }

    public String getInit_man_id() {
        return init_man_id;
    }

    public void setInit_man_id(String init_man_id) {
        this.init_man_id = init_man_id;
    }

    public String getInit_time() {
        return init_time;
    }

    public void setInit_time(String init_time) {
        this.init_time = init_time;
    }

    public String getPlan_out_time() {
        return plan_out_time;
    }

    public void setPlan_out_time(String plan_out_time) {
        this.plan_out_time = plan_out_time;
    }

    public String getPlan_in_time() {
        return plan_in_time;
    }

    public void setPlan_in_time(String plan_in_time) {
        this.plan_in_time = plan_in_time;
    }

    public String getOut_org_id() {
        return out_org_id;
    }

    public void setOut_org_id(String out_org_id) {
        this.out_org_id = out_org_id;
    }

    public String getOut_man_id() {
        return out_man_id;
    }

    public void setOut_man_id(String out_man_id) {
        this.out_man_id = out_man_id;
    }

    public String getOut_time() {
        return out_time;
    }

    public void setOut_time(String out_time) {
        this.out_time = out_time;
    }

    public String getIn_org_id() {
        return in_org_id;
    }

    public void setIn_org_id(String in_org_id) {
        this.in_org_id = in_org_id;
    }

    public String getIn_man_id() {
        return in_man_id;
    }

    public void setIn_man_id(String in_man_id) {
        this.in_man_id = in_man_id;
    }

    public String getIn_time() {
        return in_time;
    }

    public void setIn_time(String in_time) {
        this.in_time = in_time;
    }

    public String getOut_org_name() {
        return out_org_name;
    }

    public void setOut_org_name(String out_org_name) {
        this.out_org_name = out_org_name;
    }

    public String getIn_org_name() {
        return in_org_name;
    }

    public void setIn_org_name(String in_org_name) {
        this.in_org_name = in_org_name;
    }

    public String getTrans_man() {
        return trans_man;
    }

    public void setTrans_man(String trans_man) {
        this.trans_man = trans_man;
    }

    public String getTrans_man_phone() {
        return trans_man_phone;
    }

    public void setTrans_man_phone(String trans_man_phone) {
        this.trans_man_phone = trans_man_phone;
    }

    public String getCar_plate() {
        return car_plate;
    }

    public void setCar_plate(String car_plate) {
        this.car_plate = car_plate;
    }

    public String getPart_no() {
        return part_no;
    }

    public void setPart_no(String part_no) {
        this.part_no = part_no;
    }

    public Integer getIs_process() {
        return is_process;
    }

    public void setIs_process(Integer is_process) {
        this.is_process = is_process;
    }

    @Override
    public String toString() {
        return "IMain{" +
                "f_id='" + f_id + '\'' +
                ", f_type='" + f_type + '\'' +
                ", bill_no='" + bill_no + '\'' +
                ", init_man_id='" + init_man_id + '\'' +
                ", init_time='" + init_time + '\'' +
                ", plan_out_time='" + plan_out_time + '\'' +
                ", plan_in_time='" + plan_in_time + '\'' +
                ", out_org_id='" + out_org_id + '\'' +
                ", out_org_name='" + out_org_name + '\'' +
                ", out_man_id='" + out_man_id + '\'' +
                ", out_time='" + out_time + '\'' +
                ", in_org_id='" + in_org_id + '\'' +
                ", in_org_name='" + in_org_name + '\'' +
                ", in_man_id='" + in_man_id + '\'' +
                ", in_time='" + in_time + '\'' +
                ", idetl_count=" + idetl_count +
                ", trans_man='" + trans_man + '\'' +
                ", trans_man_phone='" + trans_man_phone + '\'' +
                ", car_plate='" + car_plate + '\'' +
                ", part_no='" + part_no + '\'' +
                ", is_process=" + is_process +
                '}';
    }
}

