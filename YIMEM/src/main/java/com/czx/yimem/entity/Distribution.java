package com.czx.yimem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Distribution {
    private Integer id;

    private Integer userId;

    private String cardUrlA;

    private String cardUrlB;

    private String state;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd"
    )
    private Date applyTime;

    private String resion;

    private String name;

    private String phone;

    public Distribution(Integer id, Integer userId, String cardUrlA, String cardUrlB, String state, Date applyTime, String resion, String name, String phone) {
        this.id = id;
        this.userId = userId;
        this.cardUrlA = cardUrlA;
        this.cardUrlB = cardUrlB;
        this.state = state;
        this.applyTime = applyTime;
        this.resion = resion;
        this.name = name;
        this.phone = phone;
    }

    public Distribution() {
        super();
    }

    public Distribution(Integer userId, String state) {
        this.userId = userId;
        this.state = state;
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

    public String getCardUrlA() {
        return cardUrlA;
    }

    public void setCardUrlA(String cardUrlA) {
        this.cardUrlA = cardUrlA == null ? null : cardUrlA.trim();
    }

    public String getCardUrlB() {
        return cardUrlB;
    }

    public void setCardUrlB(String cardUrlB) {
        this.cardUrlB = cardUrlB == null ? null : cardUrlB.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getResion() {
        return resion;
    }

    public void setResion(String resion) {
        this.resion = resion == null ? null : resion.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
}
