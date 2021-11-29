package com.czx.yimem.entity;

import java.util.Date;

public class LogRecord {
    private Integer id;

    private String account;

    private Date createTime;

    private String remark;

    private String ip;

    private String name;

    private String method;

    private String params;

    public LogRecord(Integer id, String account, Date createTime, String remark, String ip, String name, String method, String params) {
        this.id = id;
        this.account = account;
        this.createTime = createTime;
        this.remark = remark;
        this.ip = ip;
        this.name = name;
        this.method = method;
        this.params = params;
    }

    public LogRecord() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }
}