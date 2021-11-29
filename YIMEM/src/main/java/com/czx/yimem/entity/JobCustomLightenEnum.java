package com.czx.yimem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class JobCustomLightenEnum {
    private Integer jobLightenId;

    private Integer jobId;

    private String name;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createTime;

    private String state;

    public JobCustomLightenEnum(Integer jobLightenId, Integer jobId, String name, Date createTime, String state) {
        this.jobLightenId = jobLightenId;
        this.jobId = jobId;
        this.name = name;
        this.createTime = createTime;
        this.state = state;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public JobCustomLightenEnum() {
        super();
    }

    public Integer getJobLightenId() {
        return jobLightenId;
    }

    public void setJobLightenId(Integer jobLightenId) {
        this.jobLightenId = jobLightenId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}
