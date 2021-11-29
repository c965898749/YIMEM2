package com.czx.yimem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class IncomeDetail {
    private Integer id;

    private Integer userId;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd"
    )
    private Date createdTime;

    private BigDecimal money;

    private Integer jobId;

    private BigDecimal brokerage;

    private Integer rUserId;

    private String userName;

    private String rUserName;

    private String phone;

    private String rPhone;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd"
    )
    private Date entryTime;

    private Integer companyId;

    private String jobName;

    private String companyName;

    private Date startTime;

    private Date endTime;

    private BigDecimal expend;

    private Integer devId;

    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public IncomeDetail(Integer id, Integer userId, Date createdTime, BigDecimal money, Integer jobId, BigDecimal brokerage, Integer rUserId, String userName, String rUserName, String phone, String rPhone, Date entryTime, Integer companyId, String jobName, String companyName,BigDecimal expend,Integer devId) {
        this.id = id;
        this.userId = userId;
        this.createdTime = createdTime;
        this.money = money;
        this.jobId = jobId;
        this.brokerage = brokerage;
        this.rUserId = rUserId;
        this.userName = userName;
        this.rUserName = rUserName;
        this.phone = phone;
        this.rPhone = rPhone;
        this.entryTime = entryTime;
        this.companyId = companyId;
        this.jobName = jobName;
        this.companyName = companyName;
        this.expend = expend;
        this.devId = devId;
    }


    public BigDecimal getExpend() {
        return expend;
    }

    public void setExpend(BigDecimal expend) {
        this.expend = expend;
    }

    public IncomeDetail() {
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

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public BigDecimal getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(BigDecimal brokerage) {
        this.brokerage = brokerage;
    }

    public Integer getrUserId() {
        return rUserId;
    }

    public void setrUserId(Integer rUserId) {
        this.rUserId = rUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getrUserName() {
        return rUserName;
    }

    public void setrUserName(String rUserName) {
        this.rUserName = rUserName == null ? null : rUserName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getrPhone() {
        return rPhone;
    }

    public void setrPhone(String rPhone) {
        this.rPhone = rPhone == null ? null : rPhone.trim();
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }
}
