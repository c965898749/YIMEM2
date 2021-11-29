package com.czx.yimem.dto;

import lombok.Data;

import java.util.List;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/12/9 0009 10:11
 */
@Data
public class ButtonVO {
    private Integer fId;
    private String account;
    private List<String> button;
}
