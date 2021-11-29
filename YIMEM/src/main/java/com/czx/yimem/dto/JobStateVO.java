package com.czx.yimem.dto;

import lombok.Data;

import java.util.List;

@Data
public class JobStateVO {
    private List<Integer> jobId;
    private String state;
    private String priority;
}
