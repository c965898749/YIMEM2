package com.czx.yimem.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author CZX
 * @version 1.0
 * @date 2021/1/5 0005 16:06
 */
@Data
public class ManshVO {
    private String adress;
    private Date time;
    private String phone;
    private Date entryTime;
    private Date leaveTime;
}
