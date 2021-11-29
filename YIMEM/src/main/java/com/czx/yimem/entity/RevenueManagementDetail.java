package com.czx.yimem.entity;

import java.util.Date;

public class RevenueManagementDetail {
    private Integer id;

    private Integer revId;

    private Integer companyId;

    private String userId;

    private Integer jobId;

    private Date entryTime;

    private String state;

    public RevenueManagementDetail(Integer id, Integer revId, Integer companyId, String userId, Integer jobId, Date entryTime, String state) {
        this.id = id;
        this.revId = revId;
        this.companyId = companyId;
        this.userId = userId;
        this.jobId = jobId;
        this.entryTime = entryTime;
        this.state = state;
    }

    public RevenueManagementDetail() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRevId() {
        return revId;
    }

    public void setRevId(Integer revId) {
        this.revId = revId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}