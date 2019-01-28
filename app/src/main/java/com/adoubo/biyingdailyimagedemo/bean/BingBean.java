package com.adoubo.biyingdailyimagedemo.bean;

import java.util.List;

public class BingBean {
    private List<BingImageBean> bingImageBeans;
    private String tooltips;

    public List<BingImageBean> getBingImageBeans() {
        return bingImageBeans;
    }

    public void setBingImageBeans(List<BingImageBean> bingImageBeans) {
        this.bingImageBeans = bingImageBeans;
    }

    public String getTooltips() {
        return tooltips;
    }

    public void setTooltips(String tooltips) {
        this.tooltips = tooltips;
    }
}
