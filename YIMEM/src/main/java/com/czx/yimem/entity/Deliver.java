package com.czx.yimem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Deliver {

    private Integer devId;

    private Integer id;

    private Integer jobId;

    private String userId;

    private String state;

    private String reUserName;

    private Integer isEntrust;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd"
    )
    private Date createTime;

    public Deliver(Integer jobId, String userId) {
        this.jobId = jobId;
        this.userId = userId;
    }

    private String userName;
    private String education;
    private String phoneNum;
    private String userSex;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd"
    )
    private Date birthday;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd"
    )
    private Date jionJobTime;
    private String cityName;
    private String countryName;
    private String emil;

    private Integer isRecommend;


    private Integer reUserId;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd"
    )
    private Date updateTime;

    private String jobName;

    private DeliverDetail deliverDetail;

    private Integer day;

    private Job job;

    private Integer companyId;

    private String headImg;

    private User user;

    private String key;

    private Integer flag;

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Job getJob() {
        return job;
    }

    public DeliverDetail getDeliverDetail() {
        return deliverDetail;
    }

    public void setDeliverDetail(DeliverDetail deliverDetail) {
        this.deliverDetail = deliverDetail;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Integer getIsEntrust() {
        return isEntrust;
    }

    public String getReUserName() {
        return reUserName;
    }

    public void setReUserName(String reUserName) {
        this.reUserName = reUserName;
    }

    public void setIsEntrust(Integer isEntrust) {
        this.isEntrust = isEntrust;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getReUserId() {
        return reUserId;
    }

    public void setReUserId(Integer reUserId) {
        this.reUserId = reUserId;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Deliver(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getJionJobTime() {
        return jionJobTime;
    }

    public void setJionJobTime(Date jionJobTime) {
        this.jionJobTime = jionJobTime;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getEmil() {
        return emil;
    }

    public void setEmil(String emil) {
        this.emil = emil;
    }

//    List<DeliverDetail> deliverDetail;


    public Deliver(Integer devId, Integer id, Integer jobId, String userId, String state, Date createTime,Integer isRecommend,Integer reUserId,Date updateTime) {
        this.devId = devId;
        this.id = id;
        this.jobId = jobId;
        this.userId = userId;
        this.state = state;
        this.createTime = createTime;
        this.isRecommend = isRecommend;
        this.reUserId = reUserId;
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
//
//    public List<DeliverDetail> getDeliverDetail() {
//        return deliverDetail;
//    }
//
//    public void setDeliverDetail(List<DeliverDetail> deliverDetail) {
//        this.deliverDetail = deliverDetail;
//    }

    public Deliver() {
        super();
    }

    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}
