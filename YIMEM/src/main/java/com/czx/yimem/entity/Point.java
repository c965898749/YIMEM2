package com.czx.yimem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class Point {
    private Integer id;

    private Integer userId;

    private Integer value;

    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd"
    )
    private Date lastSign;

    private Integer contdays;

    public Point(Integer id, Integer userId, Integer value, Date lastSign, Integer contdays) {
        this.id = id;
        this.userId = userId;
        this.value = value;
        this.lastSign = lastSign;
        this.contdays = contdays;
    }

    public Point() {
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Date getLastSign() {
        return lastSign;
    }

    public void setLastSign(Date lastSign) {
        this.lastSign = lastSign;
    }

    public Integer getContdays() {
        return contdays;
    }

    public void setContdays(Integer contdays) {
        this.contdays = contdays;
    }
}