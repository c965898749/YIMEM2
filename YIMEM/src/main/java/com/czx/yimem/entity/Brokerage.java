package com.czx.yimem.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Brokerage {
    private Integer broId;

    private Integer id;

    private String referrerId;

    private BigDecimal totalMoney;

    private Date referrerNum;

    private String state;

    public Brokerage(Integer broId, Integer id, String referrerId, BigDecimal totalMoney, Date referrerNum, String state) {
        this.broId = broId;
        this.id = id;
        this.referrerId = referrerId;
        this.totalMoney = totalMoney;
        this.referrerNum = referrerNum;
        this.state = state;
    }

    public Brokerage() {
        super();
    }

    public Integer getBroId() {
        return broId;
    }

    public void setBroId(Integer broId) {
        this.broId = broId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReferrerId() {
        return referrerId;
    }

    public void setReferrerId(String referrerId) {
        this.referrerId = referrerId == null ? null : referrerId.trim();
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Date getReferrerNum() {
        return referrerNum;
    }

    public void setReferrerNum(Date referrerNum) {
        this.referrerNum = referrerNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}