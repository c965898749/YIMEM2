package com.czx.yimem.entity;

import java.math.BigDecimal;

public class RevenueManagement {
    private Integer revId;

    private Integer id;

    private Integer companyId;

    private BigDecimal predictMoney;

    private BigDecimal realityMoney;

    private Integer entryNum;

    private String state;

    public RevenueManagement(Integer revId, Integer id, Integer companyId, BigDecimal predictMoney, BigDecimal realityMoney, Integer entryNum, String state) {
        this.revId = revId;
        this.id = id;
        this.companyId = companyId;
        this.predictMoney = predictMoney;
        this.realityMoney = realityMoney;
        this.entryNum = entryNum;
        this.state = state;
    }

    public RevenueManagement() {
        super();
    }

    public Integer getRevId() {
        return revId;
    }

    public void setRevId(Integer revId) {
        this.revId = revId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public BigDecimal getPredictMoney() {
        return predictMoney;
    }

    public void setPredictMoney(BigDecimal predictMoney) {
        this.predictMoney = predictMoney;
    }

    public BigDecimal getRealityMoney() {
        return realityMoney;
    }

    public void setRealityMoney(BigDecimal realityMoney) {
        this.realityMoney = realityMoney;
    }

    public Integer getEntryNum() {
        return entryNum;
    }

    public void setEntryNum(Integer entryNum) {
        this.entryNum = entryNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}