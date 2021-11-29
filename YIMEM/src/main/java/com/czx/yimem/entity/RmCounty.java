package com.czx.yimem.entity;

import java.util.Date;

public class RmCounty {
    private Integer uuid;

    private String zhLabel;

    private Integer provinceId;

    private Integer cityId;

    private String creator;

    private Date createTime;

    private String modifier;

    private Date modifyTime;

    private Date timeStamp;

    private Integer stateflag;

    public RmCounty(Integer uuid, String zhLabel, Integer provinceId, Integer cityId, String creator, Date createTime, String modifier, Date modifyTime, Date timeStamp, Integer stateflag) {
        this.uuid = uuid;
        this.zhLabel = zhLabel;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.creator = creator;
        this.createTime = createTime;
        this.modifier = modifier;
        this.modifyTime = modifyTime;
        this.timeStamp = timeStamp;
        this.stateflag = stateflag;
    }

    public RmCounty() {
        super();
    }



    public String getZhLabel() {
        return zhLabel;
    }

    public void setZhLabel(String zhLabel) {
        this.zhLabel = zhLabel == null ? null : zhLabel.trim();
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getStateflag() {
        return stateflag;
    }

    public void setStateflag(Integer stateflag) {
        this.stateflag = stateflag;
    }
}
