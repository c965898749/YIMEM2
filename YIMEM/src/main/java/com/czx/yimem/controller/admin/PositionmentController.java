package com.czx.yimem.controller.admin;

import com.czx.yimem.Annotation.SysLog;
import com.czx.yimem.dao.MemberMapper;
import com.czx.yimem.dto.DeliverDetailVO;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.DeliverDetail;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.*;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 职业管理
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class PositionmentController {

    @Autowired
    private JobCategoryService jobCategoryService;
    @Autowired
    private JobLightenEnumService jobLightenEnumService;
    @Autowired
    private JobService jobService;
    @Autowired
    private DeliverDetailService deliverDetailService;
    @Autowired
    private DeliverService deliverService;
    @Autowired
    private UserService userService;
    @Resource
    private MemberMapper memberMapper;

    /**
     * 4.16.	职位类别企佣收费查询接口
     *
     * @param vo
     * @return
     */
    @SysLog("职位类别企佣收费查询接口")
    @PreAuthorize("hasAnyRole('ROLE_PM_LX_CX','ROLE_ADMIN')")
    @RequestMapping("/vpnjobcategoryGetsvc")
    public BaseResp vpnjobcategoryGetsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = jobCategoryService.selectByjobcategory2(vo);
        return baseResp;
    }

    /**
     * 4.60.	职位类别企佣收费新增接口
     *
     * @param vo
     * @return
     */
    @SysLog("职位类别企佣收费新增接口")
    @PreAuthorize("hasAnyRole('ROLE_PM_LX_XZ','ROLE_ADMIN')")
    @RequestMapping("/vpnjobcategoryAddsvc")
    public BaseResp vpnjobcategoryAddsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = jobCategoryService.addByjobcategory(vo);
        return baseResp;
    }

    /**
     * 4.104.	职位类别企佣收费修改接口
     *
     * @param vo
     * @return
     */
    @SysLog("职位类别企佣收费修改接口")
    @PreAuthorize("hasAnyRole('ROLE_PM_LX_XG','ROLE_ADMIN')")
    @RequestMapping("/vpnjobcategoryUpdatesvc")
    public BaseResp updateByjobcategory(@RequestBody OssVO vo) {
        BaseResp baseResp = jobCategoryService.updateByjobcategory(vo);
        return baseResp;
    }

    /**
     * 4.148.	职位类别企佣收费删除接口
     *
     * @param vo
     * @return
     */
    @SysLog("职位类别企佣收费删除接口")
    @PreAuthorize("hasAnyRole('ROLE_PM_LX_SC','ROLE_ADMIN')")
    @RequestMapping("/vpnjobcategoryDelsvc")
    public BaseResp vpnjobcategoryDelsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = jobCategoryService.delByjobcategory(vo);
        return baseResp;
    }

    /**
     * 4.26.	通用职位亮点查询接口
     *
     * @param vo
     * @return
     */
    @SysLog("通用职位亮点查询接口")
    @PreAuthorize("hasAnyRole('ROLE_PM_LD_CX','ROLE_ADMIN')")
    @RequestMapping("/vpnjobLightenEnumGetsvc")
    public BaseResp vpnjobLightenEnumGetsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = jobLightenEnumService.selectByJobLighten(vo);
        return baseResp;


    }

    /**
     * 4.70.	通用职位亮点新增接口
     *
     * @param vo
     * @return
     */
    @SysLog("通用职位亮点新增接口")
    @PreAuthorize("hasAnyRole('ROLE_PM_LD_XZ','ROLE_ADMIN')")
    @RequestMapping("/vpnjobLightenEnumAddsvc")
    public BaseResp vpnjobLightenEnumAddsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = jobLightenEnumService.vpnjobLightenEnumAddsvc(vo);
        return baseResp;
    }

    /**
     * 4.114.	通用职位亮点修改接口
     *
     * @param vo
     * @return
     */
    @SysLog("通用职位亮点修改接口")
    @PreAuthorize("hasAnyRole('ROLE_PM_LD_GX','ROLE_ADMIN')")
    @RequestMapping("/vpnjobLightenEnumUpdatesvc")
    public BaseResp vpnjobLightenEnumUpdatesvc(@RequestBody OssVO vo) {
        BaseResp baseResp = jobLightenEnumService.vpnjobLightenEnumUpdatesvc(vo);
        return baseResp;
    }

    /**
     * 4.158.	通用职位亮点删除接口
     *
     * @param vo
     * @return
     */
    @SysLog("通用职位亮点删除接口")
    @PreAuthorize("hasAnyRole('ROLE_PM_LD_SC','ROLE_ADMIN')")
    @RequestMapping("/vpnjobLightenEnumDelsvc")
    public BaseResp vpnjobLightenEnumDelsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = jobLightenEnumService.vpnjobLightenEnumDelsvc(vo);
        return baseResp;
    }

    /**
     * 4.2.	岗位新增接口
     *
     * @param vo
     * @return
     */
    @SysLog("岗位新增接口")
    @PreAuthorize("hasAnyRole('ROLE_PM_LB_XZ_BUTTON','ROLE_ADMIN')")
    @RequestMapping("/vpnjobAddsvc")
    public BaseResp vpnjobAddsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = jobService.insertSelective(vo);
        return baseResp;
    }

    /**
     * 4.3.	岗位修改接口
     *
     * @param vo
     * @return
     */
    @SysLog("岗位修改接口")
    @PreAuthorize("hasAnyRole('ROLE_PM_LB_GX_BUTTON','ROLE_ADMIN')")
    @RequestMapping("/vpnjobUpdatesvc")
    public BaseResp vpnjobUpdatesvc(@RequestBody OssVO vo) {
        BaseResp baseResp = jobService.updateByPrimaryKeySelective(vo);
        return baseResp;
    }

    /**
     * 4.3.	岗位状态更新接口
     *
     * @param vo
     * @return
     */
    @SysLog("岗位状态更新接口")
    @PreAuthorize("hasAnyRole('ROLE_PM_LB_EXAMINE','ROLE_ADMIN')")
    @RequestMapping("/vpnjobUpdateStatesvc")
    public BaseResp vpnjobUpdateStatesvc(@RequestBody OssVO vo) {
        BaseResp baseResp = jobService.vpnjobUpdateStatesvc(vo);
        return baseResp;
    }

    /**
     * 4.4.	岗位删除接口
     *
     * @param vo
     * @return
     */
    @SysLog("岗位删除接口")
    @PreAuthorize("hasAnyRole('ROLE_PM_LB_SC','ROLE_ADMIN')")
    @RequestMapping("/vpnjobDelsvc")
    public BaseResp vpnjobDelsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = jobService.delByjob(vo);
        return baseResp;
    }

    /**
     * 4.15.	用户简历投递列表查询接口
     *
     * @param vo
     * @return
     */
    @SysLog("用户简历投递列表查询接口")
    @PreAuthorize("hasAnyRole('ROLE_PM_LB_JLCX','ROLE_ADMIN')")
    @RequestMapping("/vpndeliverGetsvc")
    public BaseResp vpndeliverGetsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = deliverService.findBydeliver(vo);
        return baseResp;
    }

    /**
     * 4.15.	用户简历投递详情查询接口
     *
     * @param
     * @return
     */
    @SysLog("用户简历投递详情查询接口")
    @PreAuthorize("hasAnyRole('ROLE_PM_LB_JLCX','ROLE_ADMIN')")
    @RequestMapping("/vpndeliverGetsvc/{id}")
    public BaseResp vpndeliverGetByidsvc(@PathVariable Integer id) {
        BaseResp baseResp = deliverService.vpndeliverGetByidsvc(id);
        return baseResp;
    }


    /**
     * 4.1.	岗位查询接口
     *
     * @param vo
     * @return
     */
    @SysLog("岗位查询接口")
    @PreAuthorize("hasAnyRole('ROLE_PM_LB_CX','ROLE_ADMIN')")
    @RequestMapping("/vpnjobGetsvc")
    public BaseResp vpnjobGetsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = jobService.selectByJob2(vo);
        return baseResp;
    }



    /**
     * 4.1.	岗位详细查询接口
     * @param
     * @return
     */
    @SysLog("岗位详细查询接口")
    @PreAuthorize("hasAnyRole('ROLE_PM_LB_CX','ROLE_ADMIN')")
    @RequestMapping("/vpnjobGetsvc/{id}")
    public BaseResp vpnjobGetsvc(@PathVariable int id){
        BaseResp baseResp=jobService.selectByJobId(id);
        return baseResp;
    }
    /**
     * 4.5.	简历查询接口
     *
     * @param vo
     * @return
     */
    @SysLog("简历查询接口")
    @PreAuthorize("hasAnyRole('ROLE_PM_LB_JLCX','ROLE_ADMIN')")
    @RequestMapping("/vpnuserGetsvc")
    public BaseResp vpnuserGetsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = userService.findByuser(vo);
        return baseResp;
    }

    /**
     * 4.5.	用户简历流程新增
     *
     * @param
     * @return
     */
    @SysLog("用户简历流程新增")
    @PreAuthorize("hasAnyRole('ROLE_RM_TJ_CZ','ROLE_RM_ZJ_CZ','ROLE_PM_LB_JLLBCX','ROLE_ADMIN')")
    @RequestMapping("/vpnDeliverDetailAddsvc")
    public BaseResp interview(@RequestBody OssVO vo) {
        try {
            DeliverDetail deliverDetail = JSON.toJavaObject(vo.getVpnDeliverDetailAdd(), DeliverDetail.class);
            //委托客服电话
            if (Xtool.isNotNull(deliverDetail.getDevId())){
                String phone =memberMapper.selectphoneBydevId(deliverDetail.getDevId());
                deliverDetail.setPhone(phone);
            }
            BaseResp baseResp = deliverDetailService.addBydeliverDetail(deliverDetail);
            return baseResp;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);

        }
    }

    /**
     * 4.5.	用户简历流程新增
     *
     * @param
     * @return
     */
    @SysLog("离职审核")
    @PreAuthorize("hasAnyRole('ROLE_RM_TJ_CZ','ROLE_RM_ZJ_CZ','ROLE_PM_LB_JLLBCX','ROLE_ADMIN')")
    @RequestMapping("/vpnDeliverLeaveAudit")
    public BaseResp leaveAudit(@RequestBody OssVO vo) {
        DeliverDetail deliverDetail = JSON.toJavaObject(vo.getVpnDeliverDetailAdd(), DeliverDetail.class);

        BaseResp baseResp = deliverDetailService.leaveAudit(deliverDetail);
        return baseResp;
    }

}
