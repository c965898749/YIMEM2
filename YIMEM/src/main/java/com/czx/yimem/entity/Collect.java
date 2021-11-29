package com.czx.yimem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Collect {
    private Integer id;

    private Integer userId;

    private Integer jobId;

    private String state;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createTime;

    private Integer companyId;

    private Integer devUserId;

    private Job job;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Collect(Integer id, Integer userId, Integer jobId, String state, Date createTime,Integer companyId,Integer devUserId) {
        this.id = id;
        this.userId = userId;
        this.jobId = jobId;
        this.state = state;
        this.createTime = createTime;
        this.companyId = companyId;
        this.devUserId = devUserId;
    }

    public Integer getDevUserId() {
        return devUserId;
    }

    public void setDevUserId(Integer devUserId) {
        this.devUserId = devUserId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Collect() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
