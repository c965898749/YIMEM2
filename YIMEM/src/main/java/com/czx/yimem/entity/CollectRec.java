package com.czx.yimem.entity;

import java.util.Date;

public class CollectRec {
    private Integer id;

    private Integer devId;

    private Integer userId;

    private Date createtime;

    private String reser;

    public CollectRec(Integer id, Integer devId, Integer userId, Date createtime, String reser) {
        this.id = id;
        this.devId = devId;
        this.userId = userId;
        this.createtime = createtime;
        this.reser = reser;
    }

    public CollectRec() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getReser() {
        return reser;
    }

    public void setReser(String reser) {
        this.reser = reser == null ? null : reser.trim();
    }
}