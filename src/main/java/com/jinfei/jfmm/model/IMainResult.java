package com.jinfei.jfmm.model;

public class IMainResult {
    private String imain;
    private String detls;

    public String getImain() {
        return imain;
    }

    public void setImain(String imain) {
        this.imain = imain;
    }

    public String getDetls() {
        return detls;
    }

    public void setDetls(String detls) {
        this.detls = detls;
    }

    @Override
    public String toString() {
        return "IMainResult{" +
                "imain='" + imain + '\'' +
                ", detls='" + detls + '\'' +
                '}';
    }
}
