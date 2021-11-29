package com.czx.yimem.config;

import com.czx.yimem.service.LsImgService;
import com.czx.yimem.service.StrategyService;
import com.czx.yimem.service.admin.BeginnerService;
import com.czx.yimem.service.admin.ProtocolService;
import com.czx.yimem.service.admin.WithdrawRuleService;
import com.czx.yimem.service.financial.IncomeService;
import com.czx.yimem.utils.RedisAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Component
public class Scheduler {
    @Autowired
    private BeginnerService beginnerService;
    @Autowired
    private ProtocolService protocolService;
    @Autowired
    private WithdrawRuleService withdrawRuleService;
    @Autowired
    private StrategyService strategyService;
    @Autowired
    private IncomeService incomeService;
    @Autowired
    private LsImgService lsImgService;
    @Autowired
    private RedisAPI redisAPI;

    /**
     * 定时器
     */
    @Scheduled(fixedRate = 5000)
    public void testTasks() {
        beginnerService.updatefindTimeUp();
        protocolService.updatefindTimeUp();
        withdrawRuleService.updatefindTimeUp();
        strategyService.updatefindTimeUp();
        incomeService.updatefindTimeUp();
        incomeService.updatefindTimeUp2();
    }

    //6点清除缓存数据
    @Scheduled(cron = "0 0 6 ? * *")
    public void testTasks2() {
        lsImgService.updatefindTimeUp();
        redisAPI.del("enMoneyCount_*");
    }
//    //3点入账处理
//    @Scheduled(cron = "0 0 3 ? * *")
//    public void testTasks3() {
//        incomeService.updatefindTimeUp();
//        incomeService.updatefindTimeUp2();
//    }
}
