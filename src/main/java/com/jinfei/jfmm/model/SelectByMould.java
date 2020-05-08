package com.jinfei.jfmm.model;

public class SelectByMould extends SearchColum {
    private String mould_id;
    private String classify_no;
    private Boolean is_pop;
    public SelectByMould() {}
    public SelectByMould(String mould_id, String classify_no, Boolean is_pop) {
        this.mould_id = mould_id;
        this.classify_no = classify_no;
        this.is_pop = is_pop;
    }
}
