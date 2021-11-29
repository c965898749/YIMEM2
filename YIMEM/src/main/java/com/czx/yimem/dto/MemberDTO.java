package com.czx.yimem.dto;

import com.czx.yimem.dto.PageVO;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * @Description :
 * @Author : gonghf
 * @Date : 2020-10-22
 **/
public class MemberDTO {
    private JSONObject svcCaller;
//    private PageVO svcCaller;
    private Date timestamp;
    private String keyword;

    private String depId;

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public JSONObject getSvcCaller() {
        return svcCaller;
    }

    public void setSvcCaller(JSONObject svcCaller) {
        this.svcCaller = svcCaller;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
