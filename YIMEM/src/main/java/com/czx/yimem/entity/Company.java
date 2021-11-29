package com.czx.yimem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Company {
    private Integer companyId;

    private Integer id;

    private String name;

    private String businessId;

    private String scale;

    private String remark;

    private String userName;

    private Integer userId;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createdTime;

    private String job;

    private String licenseUrl;

    private String adress;

    private Integer cityId;
    private String cityName;

    private Integer countryId;

    private Integer provinceId;
    private Integer tag;
    private String provinceName;

    private String state;

    private Boolean isEntrust;

    private String resion;

    private Integer cusId;

    private String phone;

    private String gMphone;

    private String gmName;

    private String businessName;

    private String logourl;

    private String mapx;

    private String mapy;

    public String getgMphone() {
        return gMphone;
    }

    public void setgMphone(String gMphone) {
        this.gMphone = gMphone;
    }

    public Company(String phone) {
        this.phone = phone;
    }

    public Company(Integer companyId, Integer id, String name, String businessId, String scale, String remark, String userName, Integer userId, Date createdTime, String job, String licenseUrl, String adress, Integer cityId, Integer countryId, Integer provinceId, String state, Boolean isEntrust, String resion, Integer cusId, String phone,String logourl,String mapx,String mapy) {
        this.companyId = companyId;
        this.id = id;
        this.name = name;
        this.businessId = businessId;
        this.scale = scale;
        this.remark = remark;
        this.userName = userName;
        this.userId = userId;
        this.createdTime = createdTime;
        this.job = job;
        this.licenseUrl = licenseUrl;
        this.adress = adress;
        this.cityId = cityId;
        this.countryId = countryId;
        this.provinceId = provinceId;
        this.state = state;
        this.isEntrust = isEntrust;
        this.resion = resion;
        this.cusId = cusId;
        this.phone = phone;
        this.logourl = logourl;
        this.mapx = mapx;
        this.mapy = mapy;
    }

    public String getMapx() {
        return mapx;
    }

    public void setMapx(String mapx) {
        this.mapx = mapx;
    }

    public String getMapy() {
        return mapy;
    }

    public void setMapy(String mapy) {
        this.mapy = mapy;
    }

    public Boolean getEntrust() {
        return isEntrust;
    }

    public void setEntrust(Boolean entrust) {
        isEntrust = entrust;
    }

    public String getLogourl() {
        return logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getGmName() {
        return gmName;
    }

    public void setGmName(String gmName) {
        this.gmName = gmName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Company() {
        super();
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId == null ? null : businessId.trim();
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale == null ? null : scale.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl == null ? null : licenseUrl.trim();
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress == null ? null : adress.trim();
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Boolean getIsEntrust() {
        return isEntrust;
    }

    public void setIsEntrust(Boolean isEntrust) {
        this.isEntrust = isEntrust;
    }

    public String getResion() {
        return resion;
    }

    public void setResion(String resion) {
        this.resion = resion == null ? null : resion.trim();
    }

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }
}
