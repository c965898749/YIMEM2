package com.czx.yimem.dto;

import com.czx.yimem.dto.PageVO;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Date;

/**
 * @Description :
 * @Author : gonghf
 * @Date : 2020-10-22
 **/
@Data
public class RoleDTO {
    private JSONObject svcCaller;
    private Date timestamp;
    private  String keyword;


}
