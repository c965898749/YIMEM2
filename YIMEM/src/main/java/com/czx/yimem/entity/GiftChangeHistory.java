package com.czx.yimem.entity;

import java.util.Date;

public class GiftChangeHistory {
    private Integer id;

    private Integer userId;

    private Integer giftId;

    private Integer points;

    private Date createtime;

    public GiftChangeHistory(Integer id, Integer userId, Integer giftId, Integer points, Date createtime) {
        this.id = id;
        this.userId = userId;
        this.giftId = giftId;
        this.points = points;
        this.createtime = createtime;
    }

    public GiftChangeHistory() {
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

    public Integer getGiftId() {
        return giftId;
    }

    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}