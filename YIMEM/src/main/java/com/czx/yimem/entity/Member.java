package com.czx.yimem.entity;

import com.czx.yimem.utils.Xtool;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Member {
    private Integer id;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createTime;

    private String headImgUrl;

    private String name;

    private String phone;

    private String account;

    private String password;

    private String state;

    private List<Authority> authorityList;


    public void setAuthorityList(List<Authority> authorityList) {
        this.authorityList = authorityList;
    }

    public List<SimpleGrantedAuthority> getAuthorityList() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorityList.forEach(x->{
            authorities.add(new SimpleGrantedAuthority("ROLE_" + x.getAuthorityCode()));
        });
        return authorities;
    }


    public Member(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public Member(Integer id, Date createTime, String headImgUrl, String name, String phone, String account, String password, String state) {
        this.id = id;
        this.createTime = createTime;
        this.headImgUrl = headImgUrl;
        this.name = name;
        this.phone = phone;
        this.account = account;
        this.password = password;
        this.state = state;
    }

    public Member() {
        super();
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
        this.headImgUrl = headImgUrl == null ? null : headImgUrl.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}
