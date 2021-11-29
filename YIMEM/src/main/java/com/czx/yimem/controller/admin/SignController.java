package com.czx.yimem.controller.admin;

import com.czx.yimem.Annotation.SysLog;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.dto.RoleDTO;
import com.czx.yimem.dto.RoleVO;
import com.czx.yimem.entity.Deliver;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.DeliverService;
import com.czx.yimem.utils.Constants;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * 报名管理
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class SignController {
    @Autowired
    private DeliverService deliverService;

    /**
     * 直接企业报名
     * @param vo
     * @return
     */
    @SysLog("直接企业报名")
    @PreAuthorize("hasAnyRole('ROLE_RM_ZJ_CK','ROLE_ADMIN')")
    @RequestMapping("/ZJQYdeliverGetsvc")
    public BaseResp ZJQYdeliverGetsvc(@RequestBody OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            Deliver deliver = JSON.toJavaObject(vo.getVpndeliverGet(), Deliver.class);
            deliver.setIsRecommend(0);
            deliver.setIsEntrust(0);
            BaseResp baseResp = deliverService.findBydeliver2(deliver,pageVO);
            return baseResp;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }

    }

    /**
     * 直接委托报名
     * @param vo
     * @return
     */
    @SysLog("直接委托报名")
    @PreAuthorize("hasAnyRole('ROLE_RM_ZJ_CK','ROLE_ADMIN')")
    @RequestMapping("/ZJWTdeliverGetsvc")
    public BaseResp ZJWTdeliverGetsvc(@RequestBody OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            Deliver deliver = JSON.toJavaObject(vo.getVpndeliverGet(), Deliver.class);
            deliver.setIsRecommend(0);
            deliver.setIsEntrust(1);
            BaseResp baseResp = deliverService.findBydeliver2(deliver,pageVO);
            return baseResp;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    /**
     * 推荐委托报名
     * @param vo
     * @return
     */
    @SysLog("推荐委托报名")
    @PreAuthorize("hasAnyRole('ROLE_RM_TJ_CK','ROLE_ADMIN')")
    @RequestMapping("/TJWTdeliverGetsvc")
    public BaseResp TJWTdeliverGetsvc(@RequestBody OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            Deliver deliver = JSON.toJavaObject(vo.getVpndeliverGet(), Deliver.class);
            deliver.setIsEntrust(1);
            deliver.setIsRecommend(1);
            BaseResp baseResp = deliverService.findBydeliver3(deliver,pageVO);
            return baseResp;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    /**
     * 推荐企业面试
     * @param vo
     * @return
     */
    @SysLog("推荐企业面试")
    @PreAuthorize("hasAnyRole('ROLE_RM_TJ_CK','ROLE_ADMIN')")
    @RequestMapping("/TJQYdeliverGetsvc")
    public BaseResp TJQYdeliverGetsvc(@RequestBody OssVO vo) {
        try {
            Deliver deliver = JSON.toJavaObject(vo.getVpndeliverGet(), Deliver.class);
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            deliver.setIsEntrust(0);
            deliver.setIsRecommend(1);
            BaseResp baseResp = deliverService.findBydeliver3(deliver,pageVO);
            return baseResp;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
