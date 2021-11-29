package com.czx.yimem.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description :
 * @Author : gonghf
 * @Date : 2020-10-22
 **/
@Data
public class TreeDataVO {
    private String title;
    private Integer key;
    private Integer value;
    private BigDecimal money;
    private BigDecimal brokerage;
    private List<TreeDataVO> children;
}
