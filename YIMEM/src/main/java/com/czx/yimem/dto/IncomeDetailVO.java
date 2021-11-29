package com.czx.yimem.dto;

import com.czx.yimem.entity.ApplicationForWithdrawal;
import com.czx.yimem.entity.IncomeDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/23 0023 9:39
 */
@Data
public class IncomeDetailVO {
    private Integer id;
    private Integer userId;
    private String time;
    private BigDecimal totalmoney;
    List<IncomeDetail> incomeDetails;
}
