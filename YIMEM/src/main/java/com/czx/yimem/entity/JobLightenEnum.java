package com.czx.yimem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class JobLightenEnum {
    private Integer jobLightenId;

    private Integer id;

    private String name;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd"
    )
    private Date createTime;

    private String startTime;

    private String endTime;

    private String state;

    public JobLightenEnum(Integer jobLightenId, Integer id, String name, Date createTime, String state) {
        this.jobLightenId = jobLightenId;
        this.id = id;
        this.name = name;
        this.createTime = createTime;
        this.state = state;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public JobLightenEnum() {
        super();
    }

    public Integer getJobLightenId() {
        return jobLightenId;
    }

    public void setJobLightenId(Integer jobLightenId) {
        this.jobLightenId = jobLightenId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
