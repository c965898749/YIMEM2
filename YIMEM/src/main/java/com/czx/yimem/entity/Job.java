package com.czx.yimem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Job {
    private Integer jobId;

    private Integer id;

    private Integer companyId;

    private String companyName;

    private String isAllDay;

    private String name;

    private Integer jobCateId;

    private String jobCateName;

    private BigDecimal salary;

    private Integer jobExp;

    private String jobExpName;

    private Integer educationExp;

    private String educationExpName;

    private String remark;

    private String adress;

    private String state;

    private Integer cityId;

    private String cityName;

    private Integer countryId;

    private String countryName;

    private Integer provinceId;

    private String provinceName;

    private Integer peopleNum;

    private String timeliness;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd"
    )
    private Date startTime;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd"
    )
    private Date endTime;

    private BigDecimal partTimeSalary;

    private String payments;

    private String jobContent;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd"
    )
    private Date createTime;

    private String jobSex;

    private Integer startAge;

    private Integer endAge;

    private BigDecimal money;

    private BigDecimal brokerage;

    private String rule;

    /**
     * 发布导航栏（热门）
     */
    private String priority;

    private String mapX;

    private String mapY;

    private String mapZoom;

    private Integer salaryId;

    private BigDecimal lowSalary;

    private BigDecimal higSalary;

    private String wage;

    private Integer deliverMun;

    private String companyAdress;

    private Boolean isgraduate;

    private Boolean showcompany;

    private Long readCount;

    private Integer weTO;

    private Integer isread;

    private Company company;

    private String phone;

    private String logoUrl;

    private String scale;

    private String businessname;


    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getIsread() {
        return isread;
    }

    public void setIsread(Integer isread) {
        this.isread = isread;
    }

    public Integer getWeTO() {
        return weTO;
    }

    public void setWeTO(Integer weTO) {
        this.weTO = weTO;
    }

    public Boolean getShowcompany() {
        return showcompany;
    }

    public void setShowcompany(Boolean showcompany) {
        this.showcompany = showcompany;
    }

    public Boolean getIsgraduate() {
        return isgraduate;
    }

    public void setIsgraduate(Boolean isgraduate) {
        this.isgraduate = isgraduate;
    }

    public String getCompanyAdress() {
        return companyAdress;
    }

    public void setCompanyAdress(String companyAdress) {
        this.companyAdress = companyAdress;
    }

    public Integer getDeliverMun() {
        return deliverMun;
    }

    public void setDeliverMun(Integer deliverMun) {
        this.deliverMun = deliverMun;
    }

    public BigDecimal getLowSalary() {
        return lowSalary;
    }

    public void setLowSalary(BigDecimal lowSalary) {
        this.lowSalary = lowSalary;
    }

    public BigDecimal getHigSalary() {
        return higSalary;
    }

    public void setHigSalary(BigDecimal higSalary) {
        this.higSalary = higSalary;
    }

    public String getEducationExpName() {
        return educationExpName;
    }

    public void setEducationExpName(String educationExpName) {
        this.educationExpName = educationExpName;
    }

    public String getWage() {
        return wage;
    }

    public void setWage(String wage) {
        this.wage = wage;
    }

    public String getJobExpName() {
        return jobExpName;
    }

    public void setJobExpName(String jobExpName) {
        this.jobExpName = jobExpName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    private List<JobCustomLightenEnum>  jobCustomLightspot;

    private List<JobLightenEnum> jobLightspot;

    private List<Integer> jobLightspotIds;

    public List<Integer> getJobLightspotIds() {
        return jobLightspotIds;
    }

    public void setJobLightspotIds(List<Integer> jobLightspotIds) {
        this.jobLightspotIds = jobLightspotIds;
    }

    public Job(Integer jobId, Integer id, Integer companyId, String isAllDay, String name, Integer jobCateId, BigDecimal salary, Integer jobExp, Integer educationExp, String remark, String adress, String state, Integer cityId, Integer countryId, Integer provinceId, Integer peopleNum, String timeliness, Date startTime, Date endTime, BigDecimal partTimeSalary, String payments, String jobContent, Date createTime, String jobSex, Integer startAge, Integer endAge, BigDecimal money, BigDecimal brokerage, String rule, String priority, String mapX, String mapY, String mapZoom, Integer salaryId
    , BigDecimal lowSalary, BigDecimal higSalary,Boolean isgraduate,Boolean showcompany,Long readCount,Integer isread) {
        this.jobId = jobId;
        this.id = id;
        this.companyId = companyId;
        this.isAllDay = isAllDay;
        this.name = name;
        this.jobCateId = jobCateId;
        this.salary = salary;
        this.jobExp = jobExp;
        this.educationExp = educationExp;
        this.remark = remark;
        this.adress = adress;
        this.state = state;
        this.cityId = cityId;
        this.countryId = countryId;
        this.provinceId = provinceId;
        this.peopleNum = peopleNum;
        this.timeliness = timeliness;
        this.startTime = startTime;
        this.endTime = endTime;
        this.partTimeSalary = partTimeSalary;
        this.payments = payments;
        this.jobContent = jobContent;
        this.createTime = createTime;
        this.jobSex = jobSex;
        this.startAge = startAge;
        this.endAge = endAge;
        this.money = money;
        this.brokerage = brokerage;
        this.rule = rule;
        this.priority = priority;
        this.mapX = mapX;
        this.mapY = mapY;
        this.mapZoom = mapZoom;
        this.salaryId = salaryId;
        this.lowSalary = lowSalary;
        this.higSalary = higSalary;
        this.isgraduate = isgraduate;
        this.showcompany = showcompany;
        this.readCount = readCount;
        this.isread = isread;
    }

    public Job(Integer jobId, String state,String priority) {
        this.jobId = jobId;
        this.state = state;
        this.priority = priority;
    }

    public Long getReadCount() {
        return readCount;
    }

    public void setReadCount(Long readCount) {
        this.readCount = readCount;
    }

    public Job(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobCateName() {
        return jobCateName;
    }

    public void setJobCateName(String jobCateName) {
        this.jobCateName = jobCateName;
    }

    public Integer getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Integer salaryId) {
        this.salaryId = salaryId;
    }

    public String getMapX() {
        return mapX;
    }

    public void setMapX(String mapX) {
        this.mapX = mapX;
    }

    public String getMapY() {
        return mapY;
    }

    public void setMapY(String mapY) {
        this.mapY = mapY;
    }

    public String getMapZoom() {
        return mapZoom;
    }

    public void setMapZoom(String mapZoom) {
        this.mapZoom = mapZoom;
    }

    public Job() {
        super();
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getIsAllDay() {
        return isAllDay;
    }

    public void setIsAllDay(String isAllDay) {
        this.isAllDay = isAllDay == null ? null : isAllDay.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getJobCateId() {
        return jobCateId;
    }

    public void setJobCateId(Integer jobCateId) {
        this.jobCateId = jobCateId;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Integer getJobExp() {
        return jobExp;
    }

    public void setJobExp(Integer jobExp) {
        this.jobExp = jobExp;
    }

    public Integer getEducationExp() {
        return educationExp;
    }

    public void setEducationExp(Integer educationExp) {
        this.educationExp = educationExp;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress == null ? null : adress.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
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

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public String getTimeliness() {
        return timeliness;
    }

    public void setTimeliness(String timeliness) {
        this.timeliness = timeliness == null ? null : timeliness.trim();
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

    public BigDecimal getPartTimeSalary() {
        return partTimeSalary;
    }

    public void setPartTimeSalary(BigDecimal partTimeSalary) {
        this.partTimeSalary = partTimeSalary;
    }

    public String getPayments() {
        return payments;
    }

    public void setPayments(String payments) {
        this.payments = payments == null ? null : payments.trim();
    }

    public String getJobContent() {
        return jobContent;
    }

    public void setJobContent(String jobContent) {
        this.jobContent = jobContent == null ? null : jobContent.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getJobSex() {
        return jobSex;
    }

    public void setJobSex(String jobSex) {
        this.jobSex = jobSex;
    }

    public Integer getStartAge() {
        return startAge;
    }

    public void setStartAge(Integer startAge) {
        this.startAge = startAge;
    }

    public Integer getEndAge() {
        return endAge;
    }

    public void setEndAge(Integer endAge) {
        this.endAge = endAge;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(BigDecimal brokerage) {
        this.brokerage = brokerage;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule == null ? null : rule.trim();
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority == null ? null : priority.trim();
    }

    public List<JobCustomLightenEnum> getJobCustomLightspot() {
        return jobCustomLightspot;
    }

    public void setJobCustomLightspot(List<JobCustomLightenEnum> jobCustomLightspot) {
        this.jobCustomLightspot = jobCustomLightspot;
    }

    public List<JobLightenEnum> getJobLightspot() {
        return jobLightspot;
    }

    public void setJobLightspot(List<JobLightenEnum> jobLightspot) {
        this.jobLightspot = jobLightspot;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
