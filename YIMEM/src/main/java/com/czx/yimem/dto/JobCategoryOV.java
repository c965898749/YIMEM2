package com.czx.yimem.dto;

import com.czx.yimem.entity.JobCategory;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/19 0019 10:30
 */
@Data
public class JobCategoryOV {
    private Integer id;

    private Integer jobCateId;

    private BigDecimal money;

    private BigDecimal brokerage;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd"
    )
    private Date createTime;

    private String state;

    private String name;

    private short parentId;

    private Integer key;

    private List children;
}
