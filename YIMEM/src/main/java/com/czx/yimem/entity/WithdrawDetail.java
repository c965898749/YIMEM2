package com.czx.yimem.entity;

import java.math.BigDecimal;
import java.util.Date;

public class WithdrawDetail {
    private Integer id;

    private Integer userId;

    private Date createdTime;

    private BigDecimal money;

    private String state;

    private String resion;

    public WithdrawDetail(Integer id, Integer userId, Date createdTime, BigDecimal money, String state, String resion) {
        this.id = id;
        this.userId = userId;
        this.createdTime = createdTime;
        this.money = money;
        this.state = state;
        this.resion = resion;
    }

    public WithdrawDetail() {
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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
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

    public String getResion() {
        return resion;
    }

    public void setResion(String resion) {
        this.resion = resion == null ? null : resion.trim();
    }
}