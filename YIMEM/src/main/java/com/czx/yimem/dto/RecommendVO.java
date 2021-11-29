package com.czx.yimem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/19 0019 20:42
 */
@Data
public class RecommendVO {
    private Integer userId;
    private Integer reUserId;
    private Integer reId;
    private String name;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd"
    )
    private Date createTime;
    private Date entryTime;
    private Date accountTime;
    private Date leveTime;
    private String jobName;
    private BigDecimal money;
    private String state;
    private Integer delId;
}
