package com.czx.yimem.controller;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.ParmVO;
import com.czx.yimem.dto.TouserVO;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.ComplaintService;
import com.czx.yimem.service.StrategyService;
import com.czx.yimem.service.admin.*;
import com.czx.yimem.service.financial.IncomeDetailService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.Xtool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
/**
 * 基础数据
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Slf4j
@RequestMapping("vpnservice")
@RestController
public class ToolController {
    @Autowired
    private SlideshowService slideshowService;
    @Autowired
    private NavigationService navigationService;
    @Autowired
    private BeginnerService beginnerService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProtocolService protocolService;
    @Autowired
    private WithdrawRuleService withdrawRuleService;
    @Autowired
    private ComplaintService complaintService;
    @Autowired
    private StrategyService strategyService;
    @Autowired
    private GiftService giftService;
    @Autowired
    private IncomeDetailService incomeDetailService;


    /**
     * 公告查询接口
     * @param
     * @return
     */
    @PostMapping("/vpnannouncementGetsvc")
    public BaseResp getannouncement() {
        return incomeDetailService.getnewincomeDetails();

    }

    /**
     * 4.30.	首页轮播图查询接口
     *
     * @param vo
     * @param
     * @return
     * @throws IOException
     */
    @PostMapping("/vpnslideshowGetsvc")
    public BaseResp get(@RequestBody OssVO vo) {
        return slideshowService.get(vo);

    }

    /**
     * .4.31.	导航栏查询接口
     *
     * @param vo
     * @return
     */
    @PostMapping("/vpnnavigationGetsvc")
    public BaseResp getNavigation(@RequestBody OssVO vo) {
        return navigationService.getNavigation2(vo);
    }

    /**
     * 4.30.	新手指导查询列表接口
     *
     * @param vo
     * @return
     */
    @PostMapping("/vpnbeginnerGetsvc")
    public BaseResp beginnerGet(@RequestBody OssVO vo) {
        return beginnerService.beginnerGet(vo);
    }

    @PostMapping("/vpnbeginnerGet")
    public BaseResp vpnbeginnerGet(@RequestBody TouserVO touser) {
        return beginnerService.vpnbeginnerGet(touser);
    }

    /**
     * 4.30.	新手指导查询详情
     *
     * @param
     * @return
     */
    @PostMapping("/Beginner/get/{id}")
    public BaseResp beginnerGetc(@PathVariable Integer id) {
        return beginnerService.beginnerGetbyid(id);
    }



    /**
     * 4.32.	协议列表查询接口
     *
     * @param vo
     * @return
     */
    @PostMapping("/vpnprotocolGetsvc")
    public BaseResp Protocolget(@RequestBody OssVO vo) {
        return protocolService.protocolget(vo);
    }


    @PostMapping("/vpnprotocolGet")
    public BaseResp vpnprotocolGet(@RequestBody TouserVO touser) {
        return protocolService.vpnprotocolGet(touser);
    }
    /**
     * .32.	协议详情查询接口
     *
     * @param
     * @return
     */
    @PostMapping("/protocol/get/{id}")
    public BaseResp Protocolgetc(@PathVariable int id) {
        return protocolService.protocolgetByeId(id);
    }

    /**
     * 4.33.	提现规则查列表询接口
     *
     * @param vo
     * @return
     */
    @PostMapping("/vpnwithdrawRuleGetsvc")
    public BaseResp withdrawRuleget(@RequestBody OssVO vo) {
        return withdrawRuleService.withdrawRuleget(vo);
    }


    @PostMapping("/vpnwithdrawRuleGet")
    public BaseResp vpnwithdrawRuleGet() {
        return withdrawRuleService.vpnwithdrawRuleGet();
    }
    /**
     * 4.33.	提现规则查详情询接口
     *
     * @param
     * @return
     */
    @PostMapping("/withdrawRule/get/{id}")
    public BaseResp withdrawRulegetc(@PathVariable int id) {
        return withdrawRuleService.withdrawRulegetById(id);
    }

    /**
     * 4.81.	投诉建议新增接口
     *
     * @param
     * @return
     */
    @PostMapping("/vpncomplaintAddsvc")
    public BaseResp vpncomplaintAddsvc(@RequestBody OssVO vo) {
        return complaintService.vpncomplaintAddsvc(vo);
    }

    /**
     * 4.40.	攻略查询列表接口
     * @param vo
     * @return
     */
    @PostMapping("/vpnstrategyGetsvc")
    public BaseResp vpnstrategyGetsvc(@RequestBody OssVO vo) {
        return strategyService.vpnstrategyGetsvc(vo);
    }

    /**
     * 4.40.	攻略查询详细接口
     * @param id
     * @return
     */
    @PostMapping("/vpnstrategyGetsvc/{id}")
    public BaseResp vpnstrategyGetsvcid(@PathVariable Integer id) {
        return strategyService.vpnstrategyGetsvcid(id);
    }

    /**
     * 4.40.	攻略查询详细接口
     * @param
     * @return
     */
    @PostMapping("/vpnstrategyGetbyidsvc")
    public BaseResp vpnstrategyGetsvcid(@RequestBody ParmVO parmVO) {
        if (Xtool.isNotNull(parmVO.getId())){
            return strategyService.vpnstrategyGetsvcid(parmVO.getId());
        }else {
            return new BaseResp(Constants.FAIL_CODE,Constants.FAIL,"参数"+Constants.NO_STIPULATE);
        }
    }

    /**
     * 4.40.	礼物查询列表接口
     * @param vo
     * @return
     */
    @PostMapping("/getGiftList")
    public BaseResp getGiftList(@RequestBody OssVO vo) {
        return giftService.getGiftList(vo);
    }


}
