package com.czx.yimem.entity;

import com.czx.yimem.dto.MemberOV;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Department {
    private Integer depId;

    private Integer id;

    private Integer fDepId;

    private String name;

    private Integer mUserId;

    private String state;

    private MemberOV manager;

    public MemberOV getManager() {
        return manager;
    }

    public void setManager(MemberOV manager) {
        this.manager = manager;
    }

    private Department fdep;

    public Department getFdep() {
        return fdep;
    }

    public void setFdep(Department fdep) {
        this.fdep = fdep;
    }

    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createTime;

    public Department(Integer depId, Integer id, Integer fDepId, String name, Integer mUserId, String state, Date createTime) {
        this.depId = depId;
        this.id = id;
        this.fDepId = fDepId;
        this.name = name;
        this.mUserId = mUserId;
        this.state = state;
        this.createTime = createTime;
    }

    public Department() {
        super();
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getfDepId() {
        return fDepId;
    }

    public void setfDepId(Integer fDepId) {
        this.fDepId = fDepId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getmUserId() {
        return mUserId;
    }

    public void setmUserId(Integer mUserId) {
        this.mUserId = mUserId;
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
}
