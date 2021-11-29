package com.czx.yimem.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Income {
    private Integer id;

    private Integer userId;

    private String phone;

    private BigDecimal money;

    private Integer rUserNum;

    private Date updateTime;

    private String userName;

    public Income(Integer id, Integer userId, String phone, BigDecimal money, Integer rUserNum, Date updateTime, String userName) {
        this.id = id;
        this.userId = userId;
        this.phone = phone;
        this.money = money;
        this.rUserNum = rUserNum;
        this.updateTime = updateTime;
        this.userName = userName;
    }

    public Income() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getrUserNum() {
        return rUserNum;
    }

    public void setrUserNum(Integer rUserNum) {
        this.rUserNum = rUserNum;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }
}