package com.czx.yimem.controller.admin;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.financial.WexinPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/17 0017 8:32
 */
@Slf4j
@RequestMapping("vpnservice")
@RestController
public class TX {
    @Autowired
    private WexinPayService wexinPayService;
    @RequestMapping("/kk")
    public Object vpncompanyGetsvc(){
        return wexinPayService.Presentation("o-v7l5FqltsbX0Hyq0ibzqimOfs0","1");
    }
}
