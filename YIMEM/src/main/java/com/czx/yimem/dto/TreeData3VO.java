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
public class TreeData3VO {
    private String label;
    private Integer id;
    private List<TreeData3VO> children;
}
