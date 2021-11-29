package com.czx.yimem.dto;

import com.czx.yimem.entity.ApplicationForWithdrawal;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/20 0020 15:48
 */
@Data
public class ApplicationVO {
    private Integer id;
    private Integer userId;
    private String time;
    private BigDecimal totalmoney;
    List<ApplicationForWithdrawal> applicationForWithdrawals;
}
