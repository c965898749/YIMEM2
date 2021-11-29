package com.czx.yimem.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author CZX
 * @version 1.0
 * @date 2021/1/4 0004 23:56
 */
@Data
public class UserParam {
    private String jobName;
    private BigDecimal brokerage;
    private String userName;
    private String phoneNum;
    private String state;
}
