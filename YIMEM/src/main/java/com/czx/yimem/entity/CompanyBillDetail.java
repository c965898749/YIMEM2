package com.czx.yimem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class CompanyBillDetail {
    private Integer id;

    private Integer userId;

    private String userName;

    private String userPhone;

    private Integer jobId;

    private String jobName;

    private Integer jabCateId;

    private String jabCateName;

    private BigDecimal jobMoney;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd"
    )
    private Date userEntryTime;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd"
    )
    private Date createTime;

    private String state;

    private User user;

    private BigDecimal expend;

    private Integer companyId;

    public CompanyBillDetail(Integer id, Integer userId, String userName, String userPhone, Integer jobId, String jobName, Integer jabCateId, String jabCateName, BigDecimal jobMoney, Date userEntryTime, Date createTime, String state,Integer companyId) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.jobId = jobId;
        this.jobName = jobName;
        this.jabCateId = jabCateId;
        this.jabCateName = jabCateName;
        this.jobMoney = jobMoney;
        this.userEntryTime = userEntryTime;
        this.createTime = createTime;
        this.state = state;
        this.expend = expend;
        this.companyId = companyId;
    }


    public BigDecimal getExpend() {
        return expend;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public void setExpend(BigDecimal expend) {
        this.expend = expend;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CompanyBillDetail() {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public Integer getJabCateId() {
        return jabCateId;
    }

    public void setJabCateId(Integer jabCateId) {
        this.jabCateId = jabCateId;
    }

    public String getJabCateName() {
        return jabCateName;
    }

    public void setJabCateName(String jabCateName) {
        this.jabCateName = jabCateName == null ? null : jabCateName.trim();
    }

    public BigDecimal getJobMoney() {
        return jobMoney;
    }

    public void setJobMoney(BigDecimal jobMoney) {
        this.jobMoney = jobMoney;
    }

    public Date getUserEntryTime() {
        return userEntryTime;
    }

    public void setUserEntryTime(Date userEntryTime) {
        this.userEntryTime = userEntryTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}
