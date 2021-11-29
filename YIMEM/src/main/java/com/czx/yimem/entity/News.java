package com.czx.yimem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class News {
    private Integer id;

    private Integer userId;

    private String title;

    private String content;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createTime;

    private Integer toUserId;

    private Integer jobId;

    private Integer isagree;

    private Integer devid;

    private String state;

    private Integer identity;

    private Integer devdeilId;

    private Integer isread;
    //数据状态，1-系统消息，2-活动通知，3-面试通知，4-入职通知，5-奖励到账通知，6-提现到账通知
    private Integer newType;

    public Integer getDevdeilId() {
        return devdeilId;
    }

    public void setDevdeilId(Integer devdeilId) {
        this.devdeilId = devdeilId;
    }

    public News(Integer id, Integer userId, String title, String content, Date createTime, Integer toUserId, Integer jobId, String state, Integer isagree, Integer devid, Integer identity, Integer isread,Integer newType) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.toUserId = toUserId;
        this.jobId = jobId;
        this.state = state;
        this.isagree = isagree;
        this.devid = devid;
        this.identity = identity;
        this.isread = isread;
        //数据状态，1-系统消息，2-活动通知，3-面试通知，4-入职通知，5-奖励到账通知，6-提现到账通知
        this.newType = newType;
    }


    public Integer getNewType() {
        return newType;
    }

    public void setNewType(Integer newType) {
        this.newType = newType;
    }

    public Integer getIsread() {
        return isread;
    }

    public void setIsread(Integer isread) {
        this.isread = isread;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public Integer getIsagree() {
        return isagree;
    }

    public void setIsagree(Integer isagree) {
        this.isagree = isagree;
    }

    public Integer getDevid() {
        return devid;
    }

    public void setDevid(Integer devid) {
        this.devid = devid;
    }

    public News() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}
