package com.czx.yimem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Description :
 * @Author : gonghf
 * @Date : 2020-10-22
 **/
public class DepartmentVO {
    private Integer depId;

    private Integer id;

    private DepartmentVO fDep;

    private String name;

    private Integer fDepId;

    private String state;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd"
    )
    private Date createTime;

    private Integer mUserId;

    public Integer getmUserId() {
        return mUserId;
    }

    public void setmUserId(Integer mUserId) {
        this.mUserId = mUserId;
    }

    List<DepartmentVO> child;

    private MemberVO manager;

    public Integer getfDepId() {
        return fDepId;
    }

    public void setfDepId(Integer fDepId) {
        this.fDepId = fDepId;
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

    public DepartmentVO getfDep() {
        return fDep;
    }

    public void setfDep(DepartmentVO fDep) {
        this.fDep = fDep;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<DepartmentVO> getChild() {
        return child;
    }

    public void setChild(List<DepartmentVO> child) {
        this.child = child;
    }


    public MemberVO getManager() {
        return manager;
    }

    public void setManager(MemberVO manager) {
        this.manager = manager;
    }
}
