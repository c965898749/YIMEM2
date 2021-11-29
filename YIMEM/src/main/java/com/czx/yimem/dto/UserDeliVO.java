package com.czx.yimem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserDeliVO {

    private Integer userId;

    private String userName;

    private String userSex;

    private String phoneNum;

    private String cityName;

    private String education;

    private String companyName;

    private String jobName;

    private Integer age;

    private Integer workage;

    private BigDecimal startMoney;

    private BigDecimal endMoney;

    private String expectedSalaryName;

    private Integer weTo;

    private String state;
}
