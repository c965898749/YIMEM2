package com.czx.yimem.entity;


import java.util.List;
import java.util.Set;

public class Authority {
    private Integer id;

    private String authorityCode;

    private String authorityName;

    private List<String> button;

    private String group;

    private Integer fId;

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    private List<Authority> authorities;

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Authority(Integer id, String authorityCode, String authorityName, String group, Integer fId) {
        this.id = id;
        this.authorityCode = authorityCode;
        this.authorityName = authorityName;
        this.group = group;
        this.fId = fId;
    }

    public List<String> getButton() {
        return button;
    }

    public void setButton(List<String> button) {
        this.button = button;
    }

    public Authority() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthorityCode() {
        return authorityCode;
    }

    public void setAuthorityCode(String authorityCode) {
        this.authorityCode = authorityCode == null ? null : authorityCode.trim();
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName == null ? null : authorityName.trim();
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group == null ? null : group.trim();
    }
}
