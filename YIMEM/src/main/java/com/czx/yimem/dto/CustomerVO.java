package com.czx.yimem.dto;


import lombok.Data;

@Data
public class CustomerVO {
    private Integer cusId;

    private Integer id;

    private Integer mUserId;

    private String toUser;

    private String startTime;

    private String endTime;

    private String remark;

    private String phone;

    private String state;

}
