package com.czx.yimem.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Customer {
    private Integer cusId;

    private Integer id;

    private Integer mUserId;

    private String toUser;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd"
    )
    private Date createTime;
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "HH:mm:ss"
    )
    @JSONField(format="HH:mm:ss")
    private Date startTime;
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "HH:mm:ss"
    )
    @JSONField(format="HH:mm:ss")
    private Date endTime;

    private String remark;

    private String phone;

    private String state;

    public Customer(Integer cusId, Integer id, Integer mUserId, String toUser, Date createTime, Date startTime, Date endTime, String remark, String phone, String state) {
        this.cusId = cusId;
        this.id = id;
        this.mUserId = mUserId;
        this.toUser = toUser;
        this.createTime = createTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.remark = remark;
        this.phone = phone;
        this.state = state;
    }

    public Customer() {
        super();
    }

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getmUserId() {
        return mUserId;
    }

    public void setmUserId(Integer mUserId) {
        this.mUserId = mUserId;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser == null ? null : toUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}
