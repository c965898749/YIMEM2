package com.czx.yimem.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/23 0023 11:32
 */
@Data
public class BrokerageRankingList {
    private Integer id;
    private Integer userId;
    private String userName;
    private String headImg;
    private BigDecimal money;
    private Integer ranking;
    private List<BrokerageRankingList> rankingList;
}
