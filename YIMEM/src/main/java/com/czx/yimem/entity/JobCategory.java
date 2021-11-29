package com.czx.yimem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class JobCategory {
    private Integer id;

    private Integer jobCateId;

    private BigDecimal money;

    private BigDecimal brokerage;
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

    private String name;

    private short parentId;

    private Integer key;



    private List<JobCategory> children;

    public JobCategory(Integer id, Integer jobCateId, BigDecimal money, BigDecimal brokerage, Date createTime, String state) {
        this.id = id;
        this.jobCateId = jobCateId;
        this.money = money;
        this.brokerage = brokerage;
        this.createTime = createTime;
        this.state = state;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public List<JobCategory> getChildren() {
        return children;
    }

    public void setChildren(List<JobCategory> children) {
        this.children = children;
    }

    public short getParentId() {
        return parentId;
    }

    public void setParentId(short parentId) {
        this.parentId = parentId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JobCategory() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJobCateId() {
        return jobCateId;
    }

    public void setJobCateId(Integer jobCateId) {
        this.jobCateId = jobCateId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(BigDecimal brokerage) {
        this.brokerage = brokerage;
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
