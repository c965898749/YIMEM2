package com.czx.yimem.entity;

import java.util.Date;

public class RcRoleAuthority {
    private Integer id;

    private Integer roleId;

    private Integer authorityId;

    private Date createTime;

    private Date updateTime;

    private Integer flag;

    public RcRoleAuthority(Integer id, Integer roleId, Integer authorityId, Date createTime, Date updateTime, Integer flag) {
        this.id = id;
        this.roleId = roleId;
        this.authorityId = authorityId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.flag = flag;
    }

    public RcRoleAuthority() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}