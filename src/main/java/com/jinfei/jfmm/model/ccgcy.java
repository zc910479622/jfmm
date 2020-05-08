package com.jinfei.jfmm.model;

public class ccgcy {
    private String fid;

    private String mjlx;

    private String cw;

    private String mjzs;

    private String bjlb;

    private String zczt;

    private String cfd;

    private String other1;

    private Boolean type;

    private String nums;

    private String KMSQSYB;

    private mMain mMain;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getMjlx() {
        return mjlx;
    }

    public void setMjlx(String mjlx) {
        this.mjlx = mjlx;
    }

    public String getCw() {
        return cw;
    }

    public void setCw(String cw) {
        this.cw = cw;
    }

    public String getMjzs() {
        return mjzs;
    }

    public void setMjzs(String mjzs) {
        this.mjzs = mjzs;
    }

    public String getBjlb() {
        return bjlb;
    }

    public void setBjlb(String bjlb) {
        this.bjlb = bjlb;
    }

    public String getZczt() {
        return zczt;
    }

    public void setZczt(String zczt) {
        this.zczt = zczt;
    }

    public String getCfd() {
        return cfd;
    }

    public void setCfd(String cfd) {
        this.cfd = cfd;
    }

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public String getKMSQSYB() {
        return KMSQSYB;
    }

    public void setKMSQSYB(String KMSQSYB) {
        this.KMSQSYB = KMSQSYB;
    }

    public com.jinfei.jfmm.model.mMain getmMain() {
        return mMain;
    }

    public void setmMain(com.jinfei.jfmm.model.mMain mMain) {
        this.mMain = mMain;
    }

    @Override
    public String toString() {
        return "ccgcy{" +
                "fid='" + fid + '\'' +
                ", mjlx='" + mjlx + '\'' +
                ", cw='" + cw + '\'' +
                ", mjzs='" + mjzs + '\'' +
                ", bjlb='" + bjlb + '\'' +
                ", zczt='" + zczt + '\'' +
                ", cfd='" + cfd + '\'' +
                ", other1='" + other1 + '\'' +
                ", type=" + type +
                ", nums='" + nums + '\'' +
                ", KMSQSYB='" + KMSQSYB + '\'' +
                '}';
    }
}