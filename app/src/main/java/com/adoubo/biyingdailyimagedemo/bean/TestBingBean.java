package com.adoubo.biyingdailyimagedemo.bean;

/**
 * @author: adoubo
 * @date: 2019/1/27
 * Description:
 */
public class TestBingBean {
    private String startdate;
    private String url;
    private String copyright;
    private String copyrightlink;

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getCopyrightlink() {
        return copyrightlink;
    }

    public void setCopyrightlink(String copyrightlink) {
        this.copyrightlink = copyrightlink;
    }

    public String getImageUrl() {
        return OpenApiBean.API_BING_HEADER + getUrl();
    }
}
