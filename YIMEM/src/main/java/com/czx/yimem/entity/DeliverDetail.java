package com.czx.yimem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DeliverDetail {
    private Integer id;

    private Integer devId;

    private String state;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createTime;

    private String remark;

    private String resion;

    private String adress;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date time;

    private String phone;

    private Integer flag;

    private Date entryTime;

    private Date leaveTime;

    private Integer isRuIncome;

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public DeliverDetail(Integer devId) {
        this.devId = devId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public DeliverDetail(Integer id, Integer devId, String state, Date createTime, String remark, String resion, String adress, Date time,String phone,Integer flag,Date entryTime,Date leaveTime,Integer isRuIncome) {
        this.id = id;
        this.devId = devId;
        this.state = state;
        this.createTime = createTime;
        this.remark = remark;
        this.resion = resion;
        this.adress = adress;
        this.time = time;
        this.phone = phone;
        this.entryTime = entryTime;
        this.leaveTime = leaveTime;
        this.isRuIncome = isRuIncome;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Integer getIsRuIncome() {
        return isRuIncome;
    }

    public void setIsRuIncome(Integer isRuIncome) {
        this.isRuIncome = isRuIncome;
    }

    public DeliverDetail() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getResion() {
        return resion;
    }

    public void setResion(String resion) {
        this.resion = resion == null ? null : resion.trim();
    }
}
