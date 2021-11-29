package com.czx.yimem.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class MenuVO {
    private Integer id;
    private String router;
    private List<MenuVO> children;
    private List<String> button;
}
