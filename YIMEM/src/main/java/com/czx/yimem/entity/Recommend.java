package com.czx.yimem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Recommend {
    private Integer reId;

    private Integer id;

    private Integer userId;

    private Integer jobId;

    private Integer reUserId;

    private BigDecimal money;

    private String state;

    private Integer delId;

    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd"
    )
    private Date createTime;


    public Recommend(Integer reId, Integer id, Integer userId, Integer jobId, Integer reUserId, BigDecimal money, String state, Date createTime,Integer delId) {
        this.reId = reId;
        this.id = id;
        this.userId = userId;
        this.jobId = jobId;
        this.reUserId = reUserId;
        this.money = money;
        this.state = state;
        this.createTime = createTime;
        this.delId = delId;
    }


    public Integer getDelId() {
        return delId;
    }

    public void setDelId(Integer delId) {
        this.delId = delId;
    }

    public Recommend() {
        super();
    }

    public Integer getReId() {
        return reId;
    }

    public void setReId(Integer reId) {
        this.reId = reId;
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

    public Integer getReUserId() {
        return reUserId;
    }

    public void setReUserId(Integer reUserId) {
        this.reUserId = reUserId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
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
