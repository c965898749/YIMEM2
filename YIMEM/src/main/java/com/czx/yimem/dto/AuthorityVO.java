package com.czx.yimem.dto;

/**
 * @Description :
 * @Author : gonghf
 * @Date : 2020-10-23
 **/
public class AuthorityVO {


    private Integer id;

    private String authorityCode;

    private String authorityName;

    private String group;

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
        this.authorityCode = authorityCode;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
