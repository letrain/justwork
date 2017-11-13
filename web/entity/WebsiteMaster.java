package com.cp.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class WebsiteMaster implements Serializable{
    private static final long serialVersionUID = -6772714209501238118L;

    /**
     * 访问者的Ip
     */
    @Id
    private String ip;

    /**
     * 访问网址的当前时间
     */
    @Column(name = "visit_time")
    private Date visitTime;

    /**
     * ip对应的城市
     */
    private String city;

    /**
     * 停留时间
     */
    @Column(name = "stay_time")
    private String stayTime;

    /**
     * 同一个ip地址访问网页内容
     */
    private String visitContent;

    //访问量？


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStayTime() {
        return stayTime;
    }

    public void setStayTime(String stayTime) {
        this.stayTime = stayTime;
    }

    public String getVisitContent() {
        return visitContent;
    }

    public void setVisitContent(String visitContent) {
        this.visitContent = visitContent;
    }
}
