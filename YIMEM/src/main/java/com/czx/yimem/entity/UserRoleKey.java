package com.czx.yimem.entity;

public class UserRoleKey {
    private Integer memberId;

    private Integer roleId;

    public UserRoleKey(Integer memberId, Integer roleId) {
        this.memberId = memberId;
        this.roleId = roleId;
    }

    public UserRoleKey() {
        super();
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}