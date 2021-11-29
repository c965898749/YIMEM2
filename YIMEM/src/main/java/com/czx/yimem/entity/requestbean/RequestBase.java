package com.czx.yimem.entity.requestbean;

import java.util.Date;

/**
 * @Description :
 * @Author : gonghf
 * @Date : 2020-10-22
 **/
public class RequestBase {
    private Object svcCaller;
    private Date timestamp;
    private String reserved;
    private Object request;

    public Object getSvcCaller() {
        return svcCaller;
    }

    public void setSvcCaller(Object svcCaller) {
        this.svcCaller = svcCaller;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }
}
