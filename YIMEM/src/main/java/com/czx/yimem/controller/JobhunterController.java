package com.czx.yimem.controller;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.ParmVO;
import com.czx.yimem.entity.Deliver;
import com.czx.yimem.entity.DeliverDetail;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.DeliverDetailService;
import com.czx.yimem.service.DeliverService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.TokenUtil;
import com.czx.yimem.utils.UserLoginToken;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 求职者
 *
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Slf4j
@RequestMapping("vpnservice")
@RestController
public class JobhunterController {

    @Autowired
    private DeliverService deliverService;
    @Autowired
    private DeliverDetailService deliverDetailService;

    /**
     * 4.15.	用户简历投递列表查询接口(非企业端)
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpndeliverGetsvc")
    public BaseResp vpndeliverGetsvc(@RequestBody OssVO vo) {
        try {
            Deliver deliver = JSON.toJavaObject(vo.getVpndeliverGet(), Deliver.class);
            if (deliver == null || Xtool.isNull(deliver.getUserId()) || !TokenUtil.getTokenUserId().equals(deliver.getUserId())) {
                return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
            }
            BaseResp baseResp = deliverService.findBydeliver4(vo);
            return baseResp;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    /**
     * 4.15.	用户简历投递查询接口
     *
     * @param
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpndeliverGetsvc/{id}")
    public BaseResp vpndeliverGetByidsvc(@PathVariable Integer id) {
        BaseResp baseResp = deliverService.vpndeliverGetByidsvc(id);
        return baseResp;
    }

    @UserLoginToken
    @RequestMapping("/vpndeliverGetidsvc")
    public BaseResp vpndeliverGetidsvc(@RequestBody ParmVO parmVO) {
        if (parmVO==null||Xtool.isNull(parmVO.getId())){
            return new BaseResp(Constants.FAIL_CODE,Constants.NO_STIPULATE);
        }
        BaseResp baseResp = deliverService.vpndeliverGetByidsvc(parmVO.getId());
        return baseResp;
    }

    /**
     * 4.59.	用户简历投递新增接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpndeliverAddsvc")
    public BaseResp vpndeliverAddsvc(@RequestBody OssVO vo) {
        try {
            BaseResp baseResp = deliverService.addBydeliver(vo);
            return baseResp;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    /**
     * 4.103.	用户简历投递修改接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpndeliverUpdatesvc")
    public BaseResp vpndeliverUpdatesvc(@RequestBody OssVO vo) {
        BaseResp baseResp = deliverService.updateBydeliver(vo);
        return baseResp;
    }

    /**
     * 4.147.	用户简历投递删除接口
     *
     * @param vo
     * @return
     */
//    @UserLoginToken
//    @RequestMapping("/vpndeliverDelsvc")
//    public BaseResp vpndeliverDelsvc(@RequestBody OssVO vo) {
//        BaseResp baseResp = deliverService.delBydeliver(vo);
//        return baseResp;
//    }

    /**
     * 4.194.	简历流程查询接口
     *
     * @param vo
     * @return
     */
    @RequestMapping("/vpnDeliverDetailGetsvc")
    public BaseResp vpnDeliverDetailGetsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = deliverDetailService.findBydeliverDetail(vo);
        return baseResp;
    }

    /**
     * 4.195.	简历流程新增接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnDeliverDetailAddsvc")
    public BaseResp vpnDeliverDetailAddsvc(@RequestBody OssVO vo) {
        try {
            String userId= TokenUtil.getTokenUserId();
            if (Xtool.isNull(userId)){
                return new  BaseResp(Constants.FAIL_CODE,Constants.NO_HANDLE);
            }
            DeliverDetail deliverDetail = JSON.toJavaObject(vo.getVpnDeliverDetailAdd(), DeliverDetail.class);
            if (deliverDetail==null||Xtool.isNull(deliverDetail.getDevId())||Xtool.isNull(deliverDetail.getState())){
                return new  BaseResp(Constants.FAIL_CODE,Constants.NO_STIPULATE);
            }
//        进行权限验证
            if (!userId.equals(deliverDetailService.findcompanyuserid(deliverDetail.getDevId())+"")){
                return new  BaseResp(Constants.FAIL_CODE,Constants.NO_AUTH);
            }
            BaseResp baseResp = deliverDetailService.addBydeliverDetail(deliverDetail);
            return baseResp;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    /**
     * 4.195.	确认离职/入职接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnUserDeliverDetailAddsvc")
    public BaseResp vpnUserDeliverDetailAddsvc(@RequestBody OssVO vo) {
        try {
            String userId= TokenUtil.getTokenUserId();
            if (Xtool.isNull(userId)){
                return new  BaseResp(Constants.FAIL_CODE,Constants.NO_HANDLE);
            }
            DeliverDetail deliverDetail = JSON.toJavaObject(vo.getVpnDeliverDetailAdd(), DeliverDetail.class);
            if (deliverDetail==null||Xtool.isNull(deliverDetail.getDevId())||Xtool.isNull(deliverDetail.getState())){
                return new  BaseResp(Constants.FAIL_CODE,Constants.NO_STIPULATE);
            }
//        进行权限验证 企业及本人
            if (!userId.equals(deliverDetailService.findcompanyuserid(deliverDetail.getDevId())+"") && !userId.equals(deliverDetailService.findUserid(deliverDetail.getDevId())+"")){
                return new  BaseResp(Constants.FAIL_CODE,Constants.NO_AUTH);
            }
            BaseResp baseResp = deliverDetailService.addBydeliverDetail(deliverDetail);
            return baseResp;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    /**
     * 4.196.	简历流程更新接口
     *
     * @param vo
     * @return
     */
//    @RequestMapping("/vpnDeliverDetailUpdatesvc")
//    public BaseResp vpnDeliverDetailUpdatesvc(@RequestBody OssVO vo) {
//        BaseResp baseResp = deliverDetailService.updateBydeliverDetail(vo);
//        return baseResp;
//    }

    /**
     * 4.197.	简历流程删除接口
     *
     * @param vo
     * @return
     */
//    @RequestMapping("/vpnDeliverDetailDelsvc")
//    public BaseResp vpnDeliverDetailDelsvc(@RequestBody OssVO vo) {
//        BaseResp baseResp = deliverDetailService.delBydeliverDetail(vo);
//        return baseResp;
//    }

    /**
     * 4.19.	用户面试邀约接口
     *
     * @param
     * @return
     */
//    @RequestMapping("/vpninviteGetsvc/{id}")
//    public BaseResp vpninviteGetsvc(@PathVariable int id) {
//        BaseResp baseResp = deliverDetailService.vpninviteGetsvc(id);
//        return baseResp;
//    }
    @UserLoginToken
    @RequestMapping("/vpninviteGetidsvc")
    public BaseResp vpninviteGetidsvc(@RequestBody ParmVO parmVO) {
        if (parmVO==null||Xtool.isNull(parmVO.getId())){
            return new BaseResp(Constants.FAIL_CODE,Constants.NO_STIPULATE);
        }
        BaseResp baseResp = deliverDetailService.vpninviteGetsvc(parmVO.getId());
        return baseResp;
    }
    /**
     * 4.20.	面试邀约同意或不同意接口
     *
     * @param
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpninviteUpdatesvc")
    public BaseResp vpninviteUpdatesvc(@RequestBody OssVO vo) {
        try {
            BaseResp baseResp = deliverDetailService.vpninviteUpdatesvc(vo);
            return baseResp;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

}
