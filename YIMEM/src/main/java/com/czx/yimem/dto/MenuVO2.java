package com.czx.yimem.dto;

import lombok.Data;

import java.util.List;

@Data
public class MenuVO2 {
    private Integer id;
    private String router;
    private List children;
}
