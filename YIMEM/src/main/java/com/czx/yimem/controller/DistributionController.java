package com.czx.yimem.controller;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.ParmVO;
import com.czx.yimem.dto.RecommendVO;
import com.czx.yimem.entity.Recommend;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.DeliverService;
import com.czx.yimem.service.DistributionService;
import com.czx.yimem.service.RecommendService;
import com.czx.yimem.service.financial.ApplicationForWithdrawalService;
import com.czx.yimem.service.financial.IncomeDetailService;
import com.czx.yimem.service.financial.WalletService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.TokenUtil;
import com.czx.yimem.utils.UserLoginToken;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 分销者
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Slf4j
@RequestMapping("vpnservice")
@RestController
public class DistributionController {
    @Autowired
    private DistributionService distributionService;
    @Autowired
    private RecommendService recommendService;
    @Autowired
    private ApplicationForWithdrawalService applicationForWithdrawalService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private IncomeDetailService incomeDetailService;
    @Autowired
    private DeliverService deliverService;


    /**
     * 4.73.	分销者新增接口
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpndistributionAddsvc")
    public BaseResp vpndistributionAddsvc(@RequestBody OssVO vo){
        BaseResp baseResp=distributionService.vpndistributionAddsvc(vo);
        return baseResp;
    }

    /**
     * 4.36.	分销者用户推荐查询接口
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnrecommendGetsvc")
    public BaseResp vpnrecommendGetsvc(@RequestBody OssVO vo){
        RecommendVO recommend = JSON.toJavaObject(vo.getVpnrecommendGetResp(), RecommendVO.class);
        if (recommend==null||Xtool.isNull(recommend.getUserId())){
            return new  BaseResp(Constants.FAIL_CODE,Constants.FAIL,Constants.NO_STIPULATE);
        }
        if (!TokenUtil.getTokenUserId().equals(recommend.getUserId()+"")){
            return new  BaseResp(Constants.FAIL_CODE,Constants.FAIL,Constants.FORBIDDEN);
        }
        BaseResp baseResp=recommendService.vpnrecommendGetsvc(vo);
        return baseResp;
    }

    /**
     * 4.36.	分销者用户推荐流程查询接口
     * @param
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnrecommendDetailGetsvc")
    public BaseResp vpnrecommendDetailGetsvc(@RequestBody ParmVO parmVO){
        if (parmVO==null|| Xtool.isNull(parmVO.getId())){
            return new BaseResp(Constants.FAIL_CODE,Constants.NO_STIPULATE);
        }
        String userId= TokenUtil.getTokenUserId();
        if (Xtool.isNull(userId)){
            return new BaseResp(Constants.FAIL_CODE,Constants.NO_AUTH);
        }
        Recommend recommend=new Recommend();
        recommend.setUserId(Integer.parseInt(userId));
        recommend.setDelId(parmVO.getId());
        Recommend commend=recommendService.selectByDelId(recommend);
        if (commend==null){
            return new BaseResp(Constants.FAIL_CODE,Constants.NO_AUTH);
        }
//        BaseResp baseResp = deliverService.vpndeliverGetByidsvc(parmVO.getId());
        BaseResp baseResp = deliverService.vpndeliverGetByidsvc2(commend);
        return baseResp;
    }
    /**
     * 4.36.	分销者推荐用户详细查询接口
     * @param
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnrecommendUserDetailGetsvc")
    public BaseResp vpnrecommendUserDetailGetsvc(@RequestBody ParmVO parmVO){
        if (parmVO==null|| Xtool.isNull(parmVO.getId())){
            return new BaseResp(Constants.FAIL_CODE,Constants.NO_STIPULATE);
        }
        String userId= TokenUtil.getTokenUserId();
        if (Xtool.isNull(userId)){
            return new BaseResp(Constants.FAIL_CODE,Constants.NO_AUTH);
        }
        BaseResp baseResp = deliverService.vpnrecommendUserDetailGetsvc(Integer.parseInt(userId),parmVO.getId());
        return baseResp;
    }

    /**
     * 4.210.	分销者提现申请查询接口
     * @param vo
     * @return
     */
    @UserLoginToken
    @PostMapping("/vpnApplicationForWithdrawalGetsvc")
    public BaseResp vpnApplicationForWithdrawalGetsvc(@RequestBody OssVO vo) {
        return applicationForWithdrawalService.vpnApplicationForWithdrawalGetsvc2(vo);
    }


    /**
     *4.34.	分销者收入明细查询接口
     * @param vo
     * @return
     */
    @UserLoginToken
    @PostMapping("/vpnincomeDetailGetsvc")
    public BaseResp vpnincomeDetailGetsvc(@RequestBody OssVO vo) {
        return incomeDetailService.vpnincomeDetailGetsvc2(vo);
    }

    /**
     * 4.213.	分销者提现新增接口
     * @param vo
     * @return
     */
    @UserLoginToken
    @PostMapping("/vpnApplicationForWithdrawalAddsvc")
    public BaseResp vpnApplicationForWithdrawalAddsvc(@RequestBody OssVO vo) {
        try {
            return applicationForWithdrawalService.vpnApplicationForWithdrawalAddsvc(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }


    /**
     * 4.33.	分销者钱包查询接口
     * @param vo
     * @return
     */
    @UserLoginToken
    @PostMapping("/vpnWalletGetsvc")
    public BaseResp vpnWalletGetsvc(@RequestBody OssVO vo) {
        try {
            return walletService.vpnWalletGetsvc(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    /**
     * 4.216.	分销者佣金排行榜
     * @param vo
     * @return
     */
    @UserLoginToken
    @PostMapping("/vpnbrokerageRankingListsvc")
    public BaseResp vpnbrokerageRankingListsvc(@RequestBody OssVO vo) {
            return incomeDetailService.vpnbrokerageRankingListsvc(vo);

    }

    /**
     * 4.216.	分销者到面排行榜
     * @param vo
     * @return
     */
    @UserLoginToken
    @PostMapping("/vpnInterviewRankingListsvc")
    public BaseResp vpnInterviewRankingListsvc(@RequestBody OssVO vo) {
        return incomeDetailService.vpnInterviewRankingListsvc(vo);

    }

    /**
     * 4.216.	分销者推荐排行榜
     * @param vo
     * @return
     */
    @UserLoginToken
    @PostMapping("/vpnRecommendRankingListsvc")
    public BaseResp vpnRecommendRankingListsvc(@RequestBody OssVO vo) {
        return incomeDetailService.vpnRecommendRankingListsvc(vo);

    }
}
