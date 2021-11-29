package com.czx.yimem.entity;

import java.math.BigDecimal;

public class Wallet {
    private Integer id;

    private Integer userId;

    private BigDecimal money;

    private String state;

    public Wallet(Integer userId) {
        this.userId = userId;
    }

    public Wallet(Integer userId, BigDecimal money) {
        this.userId = userId;
        this.money = money;
    }

    public Wallet(Integer id, Integer userId, BigDecimal money, String state) {
        this.id = id;
        this.userId = userId;
        this.money = money;
        this.state = state;
    }

    public Wallet() {
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
}
