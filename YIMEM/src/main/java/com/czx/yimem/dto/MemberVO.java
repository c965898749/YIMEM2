package com.czx.yimem.dto;

import com.czx.yimem.entity.Department;
import com.czx.yimem.entity.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Description :
 * @Author : gonghf
 * @Date : 2020-10-22
 **/
public class MemberVO {
    private Integer id;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd"
    )
    private Date createTime;

    private String headImgUrl;

    private String name;

    private String phone;

    private String account;

    private String password;

    private String state;

//    private List<DepartmentVO> deps;
    private Department deps;

//    private List<RoleVO> roles;
    private Role roles;

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

    public Department getDeps() {
        return deps;
    }

    public void setDeps(Department deps) {
        this.deps = deps;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


}
