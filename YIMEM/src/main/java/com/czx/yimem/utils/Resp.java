package com.czx.yimem.utils;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.ParmVO;
import com.czx.yimem.entity.User;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.CompanyService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/12/12 0012 10:14
 */
@Component
public class Resp {
    @Autowired
    private CompanyService companyService;

    /**
     * 企业用户认证
     * @param
     * @param
     * @return
     */
    public BaseResp comParmResp(Integer id, BaseResp resp) {
        String userId = TokenUtil.getTokenUserId();
        if (Xtool.isNull(id)) {
            return new BaseResp(Constants.FAIL_CODE, "用户标识" + Constants.NO_NULL);
        }
        //根据用户id判断是否有权查询（企业）
        if (userId.equals(companyService.finduserId(id)+"")) {
            return resp;
        }
        return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
    }

    /**
     * 用户认证
     * @param vo
     * @param baseResp
     * @return
     */
    public BaseResp ownerResp(OssVO vo, BaseResp baseResp) {
        String userId = TokenUtil.getTokenUserId();
        User user = JSON.toJavaObject(vo.getVpnuserUpdate(), User.class);
        if (Xtool.isNull(user.getUserId())) {
            return new BaseResp(Constants.FAIL_CODE, "用户标识" + Constants.NO_NULL);
        }
        //根据用户id判断是否有权查询（本人）
        if (userId.equals(user.getUserId() + "")) {
            return baseResp;
        }
        return new BaseResp(Constants.FAIL_CODE, Constants.NO_HANDLE);
    }

    /**
     * 用户认证和授权企业认证
     * @param parmVO
     * @param object
     * @return
     */
    public BaseResp ownerParmResp(ParmVO parmVO, Object object) {
        String userId = TokenUtil.getTokenUserId();
        if (parmVO == null && Xtool.isNull(parmVO.getId())) {
            return new BaseResp(Constants.FAIL_CODE, "用户标识" + Constants.NO_NULL);
        }
        //根据用户id判断是否有权查询（递的企业，本人）
        if (userId.equals(parmVO.getId() + "") || companyService.findByUtoU(Integer.parseInt(userId), parmVO.getId()) > 0) {
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, object);
        }
        return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
    }

    /**
     * 用户认证和授权企业认证
     * @param vo
     * @param baseResp
     * @return
     */
    public BaseResp ownAndcomResp(OssVO vo, BaseResp baseResp) {
        String userId = TokenUtil.getTokenUserId();
        User user = JSON.toJavaObject(vo.getVpnuserGet(), User.class);
        if (Xtool.isNull(user.getUserId())) {
            return new BaseResp(Constants.FAIL_CODE, "用户标识" + Constants.NO_NULL);
        }
        //根据用户id判断是否有权查询（递的企业，本人）
        if (userId.equals(user.getUserId() + "") || companyService.findByUtoU(user.getUserId(), Integer.parseInt(userId)) > 0) {
            return baseResp;
        }
        return new BaseResp(Constants.FAIL_CODE, Constants.NO_HANDLE);
    }


    /**
     * 用户认证和授权企业认证
     * @param
     * @param baseResp
     * @return
     */
    public BaseResp chekidResp(Integer id, BaseResp baseResp) {
        String userId = TokenUtil.getTokenUserId();
        if (Xtool.isNull(id)){
            return new BaseResp(Constants.FAIL_CODE, "用户标识" + Constants.NO_NULL);
        }
        //根据用户id判断是否有权查询（递的企业，本人）
        if (userId.equals(id + "") || companyService.findByUtoU(id, Integer.parseInt(userId)) > 0) {
            return baseResp;
        }
        return new BaseResp(Constants.FAIL_CODE, Constants.NO_HANDLE);
    }
}
