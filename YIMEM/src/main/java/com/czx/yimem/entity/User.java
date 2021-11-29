package com.czx.yimem.entity;

import com.czx.yimem.dto.ManshVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class User {
    private Integer userId;

    private String openid;

    private String userName;

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
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd HH:mm"
    )
    private Date createtime;
    private String presentAddress;

    private String census;

    private String headImg;

    private String phoneNum;

    private String emil;

    private String remark;

    private Integer cityId;

    private String cityName;

    private Integer countryId;

    private String countryName;

    private Integer provinceId;

    private String provinceName;

    private String identityNum;

    private String password;

    private Integer censusCityId;

    private String censusCityName;

    private Integer censusCountryId;

    private String censusCountryName;

    private Integer censusProvinceId;

    private String censusProvinceName;


    private String inviteCode;

    private String invitedCode;

    private String education;

    private Integer age;

    private Integer workage;

    private String school;

    private Integer devIsComplete;

    private Integer expectedSalaryId;

    private String  expectedSalaryName;

    private String  state;

    private Integer isdistribution;

    private String jobName;

    private ManshVO manshVO;

    private List<JobWanted> jobWanteds;

    private List<EducationExp> educationExps;

    private List<JobExp> jobExps;

    private List<Language> languages;

    private List<ProjectExp> projectExps;

    private List<Certificate> certificates;

    public User(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public ManshVO getManshVO() {
        return manshVO;
    }

    public void setManshVO(ManshVO manshVO) {
        this.manshVO = manshVO;
    }

    public String getExpectedSalaryName() {
        return expectedSalaryName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setExpectedSalaryName(String expectedSalaryName) {
        this.expectedSalaryName = expectedSalaryName;
    }

    public Integer getExpectedSalaryId() {
        return expectedSalaryId;
    }

    public void setExpectedSalaryId(Integer expectedSalaryId) {
        this.expectedSalaryId = expectedSalaryId;
    }

    public Integer getIsdistribution() {
        return isdistribution;
    }

    public void setIsdistribution(Integer isdistribution) {
        this.isdistribution = isdistribution;
    }

    public String getCensusCityName() {
        return censusCityName;
    }

    public void setCensusCityName(String censusCityName) {
        this.censusCityName = censusCityName;
    }

    public String getCensusCountryName() {
        return censusCountryName;
    }

    public void setCensusCountryName(String censusCountryName) {
        this.censusCountryName = censusCountryName;
    }

    public String getCensusProvinceName() {
        return censusProvinceName;
    }

    public void setCensusProvinceName(String censusProvinceName) {
        this.censusProvinceName = censusProvinceName;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public User(Integer userId, String openid, String userName, String userSex, Date birthday, Date jionJobTime, String presentAddress, String census, String headImg, String phoneNum, String emil, String remark, Integer cityId, Integer countryId, Integer provinceId, String identityNum, String password, Integer censusCityId, Integer censusCountryId, Integer censusProvinceId
        ,String inviteCode,String invitedCode,Date createtime) {
        this.userId = userId;
        this.openid = openid;
        this.userName = userName;
        this.userSex = userSex;
        this.birthday = birthday;
        this.jionJobTime = jionJobTime;
        this.presentAddress = presentAddress;
        this.census = census;
        this.headImg = headImg;
        this.phoneNum = phoneNum;
        this.emil = emil;
        this.remark = remark;
        this.cityId = cityId;
        this.countryId = countryId;
        this.provinceId = provinceId;
        this.identityNum = identityNum;
        this.password = password;
        this.censusCityId = censusCityId;
        this.censusCountryId = censusCountryId;
        this.censusProvinceId = censusProvinceId;
        this.inviteCode = inviteCode;
        this.invitedCode = invitedCode;
        this.createtime = createtime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getInvitedCode() {
        return invitedCode;
    }

    public void setInvitedCode(String invitedCode) {
        this.invitedCode = invitedCode;
    }

    public Integer getDevIsComplete() {
        return devIsComplete;
    }

    public void setDevIsComplete(Integer devIsComplete) {
        this.devIsComplete = devIsComplete;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWorkage() {
        return workage;
    }

    public void setWorkage(Integer workage) {
        this.workage = workage;
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

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Integer getCensusCityId() {
        return censusCityId;
    }

    public void setCensusCityId(Integer censusCityId) {
        this.censusCityId = censusCityId;
    }

    public Integer getCensusCountryId() {
        return censusCountryId;
    }

    public void setCensusCountryId(Integer censusCountryId) {
        this.censusCountryId = censusCountryId;
    }

    public Integer getCensusProvinceId() {
        return censusProvinceId;
    }

    public void setCensusProvinceId(Integer censusProvinceId) {
        this.censusProvinceId = censusProvinceId;
    }

    public List<JobWanted> getJobWanteds() {
        return jobWanteds;
    }

    public void setJobWanteds(List<JobWanted> jobWanteds) {
        this.jobWanteds = jobWanteds;
    }

    public List<EducationExp> getEducationExps() {
        return educationExps;
    }

    public void setEducationExps(List<EducationExp> educationExps) {
        this.educationExps = educationExps;
    }

    public List<JobExp> getJobExps() {
        return jobExps;
    }

    public void setJobExps(List<JobExp> jobExps) {
        this.jobExps = jobExps;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<ProjectExp> getProjectExps() {
        return projectExps;
    }

    public void setProjectExps(List<ProjectExp> projectExps) {
        this.projectExps = projectExps;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    public User() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
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

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress == null ? null : presentAddress.trim();
    }

    public String getCensus() {
        return census;
    }

    public void setCensus(String census) {
        this.census = census == null ? null : census.trim();
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getEmil() {
        return emil;
    }

    public void setEmil(String emil) {
        this.emil = emil == null ? null : emil.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public String getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum == null ? null : identityNum.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", openid='" + openid + '\'' +
                ", userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", birthday=" + birthday +
                ", jionJobTime=" + jionJobTime +
                ", presentAddress='" + presentAddress + '\'' +
                ", census='" + census + '\'' +
                ", headImg='" + headImg + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", emil='" + emil + '\'' +
                ", remark='" + remark + '\'' +
                ", cityId=" + cityId +
                ", countryId=" + countryId +
                ", provinceId=" + provinceId +
                ", identityNum='" + identityNum + '\'' +
                '}';
    }
}
