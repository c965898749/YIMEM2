package com.czx.yimem.entity;

import java.util.Date;

public class RmProvince {
    private String uuid;

    private String zhLabel;

    private Integer provinceCode;

    private String creator;

    private Date createTime;

    private String modifier;

    private Date modifyTime;

    private Date timeStamp;

    private Integer stateflag;

    public RmProvince(String uuid, String zhLabel, Integer provinceCode, String creator, Date createTime, String modifier, Date modifyTime, Date timeStamp, Integer stateflag) {
        this.uuid = uuid;
        this.zhLabel = zhLabel;
        this.provinceCode = provinceCode;
        this.creator = creator;
        this.createTime = createTime;
        this.modifier = modifier;
        this.modifyTime = modifyTime;
        this.timeStamp = timeStamp;
        this.stateflag = stateflag;
    }

    public RmProvince() {
        super();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getZhLabel() {
        return zhLabel;
    }

    public void setZhLabel(String zhLabel) {
        this.zhLabel = zhLabel == null ? null : zhLabel.trim();
    }

    public Integer getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Integer provinceCode) {
        this.provinceCode = provinceCode;
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
