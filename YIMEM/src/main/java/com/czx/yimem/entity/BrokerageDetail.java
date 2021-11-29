package com.czx.yimem.entity;

import java.util.Date;

public class BrokerageDetail {
    private Integer id;

    private Integer broId;

    private String referrerId;

    private String userId;

    private Integer jobId;

    private Date createTime;

    public BrokerageDetail(Integer id, Integer broId, String referrerId, String userId, Integer jobId, Date createTime) {
        this.id = id;
        this.broId = broId;
        this.referrerId = referrerId;
        this.userId = userId;
        this.jobId = jobId;
        this.createTime = createTime;
    }

    public BrokerageDetail() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBroId() {
        return broId;
    }

    public void setBroId(Integer broId) {
        this.broId = broId;
    }

    public String getReferrerId() {
        return referrerId;
    }

    public void setReferrerId(String referrerId) {
        this.referrerId = referrerId == null ? null : referrerId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}