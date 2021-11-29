package com.czx.yimem.entity;

import java.math.BigDecimal;

public class JobWanted {

    private Integer id;

    private Integer userId;

    private String jobCateId;

    private BigDecimal startMoney;

    private BigDecimal endMoney;

    private Integer provinceId;

    private String provinceName;

    private Integer cityId;

    private Integer countryId;

    private String countryName;

    private String property;

    private String state;

    private String stateName;

    private Integer businessId;

    private String businessName;

    private Integer expectedSalaryId;

    private String expectedSalaryName;

    private String jobCateName;

    private String cityName;

    private String propertyName;

    private String isAllDay;

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }



    public String getIsAllDay() {
        return isAllDay;
    }

    public void setIsAllDay(String isAllDay) {
        this.isAllDay = isAllDay;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getJobCateName() {
        return jobCateName;
    }

    public void setJobCateName(String jobCateName) {
        this.jobCateName = jobCateName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public JobWanted(Integer userId) {
        this.userId = userId;
    }



    public JobWanted(Integer id, Integer userId, String jobCateId, BigDecimal startMoney, BigDecimal endMoney, Integer provinceId, Integer cityId, Integer countryId, String property, String state, Integer businessId, Integer expectedSalaryId,String isAllDay) {
        this.id = id;
        this.userId = userId;
        this.jobCateId = jobCateId;
        this.startMoney = startMoney;
        this.endMoney = endMoney;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.countryId = countryId;
        this.property = property;
        this.state = state;
        this.businessId = businessId;
        this.expectedSalaryId = expectedSalaryId;
        this.isAllDay = isAllDay;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getExpectedSalaryName() {
        return expectedSalaryName;
    }

    public void setExpectedSalaryName(String expectedSalaryName) {
        this.expectedSalaryName = expectedSalaryName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getExpectedSalaryId() {
        return expectedSalaryId;
    }

    public void setExpectedSalaryId(Integer expectedSalaryId) {
        this.expectedSalaryId = expectedSalaryId;
    }

    public JobWanted() {
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

    public String getJobCateId() {
        return jobCateId;
    }

    public void setJobCateId(String jobCateId) {
        this.jobCateId = jobCateId == null ? null : jobCateId.trim();
    }

    public BigDecimal getStartMoney() {
        return startMoney;
    }

    public void setStartMoney(BigDecimal startMoney) {
        this.startMoney = startMoney;
    }

    public BigDecimal getEndMoney() {
        return endMoney;
    }

    public void setEndMoney(BigDecimal endMoney) {
        this.endMoney = endMoney;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
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

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property == null ? null : property.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}
