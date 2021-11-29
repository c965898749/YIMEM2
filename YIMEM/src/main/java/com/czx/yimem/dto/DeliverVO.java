package com.czx.yimem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class DeliverVO {
    private Integer devId;

    private String userName;

    private String phoneNum;

    private String jobName;

    private String jobCateName;

    private String companyName;
    private String reUserName;
    private String rePhone;

    private String state;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd"
    )
    private Date updateTime;
}
