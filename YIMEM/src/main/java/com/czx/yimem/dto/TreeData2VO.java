package com.czx.yimem.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description :
 * @Author : gonghf
 * @Date : 2020-10-22
 **/
@Data
public class TreeData2VO {
    private String label;
    private Integer id;
    private BigDecimal money;
    private BigDecimal brokerage;
    private List<TreeData2VO> children;
}
