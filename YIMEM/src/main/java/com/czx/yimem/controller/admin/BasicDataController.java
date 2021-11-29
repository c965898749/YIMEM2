package com.czx.yimem.controller.admin;

import com.czx.yimem.Annotation.SysLog;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.requestbean.ResultBeanObj;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.ComplaintService;
import com.czx.yimem.service.QsCategoryJobsService;
import com.czx.yimem.service.StrategyService;
import com.czx.yimem.service.admin.*;
import com.czx.yimem.utils.UploadUtil;
import com.czx.yimem.utils.XMLUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 基础数据
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Slf4j
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class BasicDataController {
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
    private QsCategoryJobsService qsCategoryJobsService;
    @Autowired
    private ComplaintService complaintService;
    @Autowired
    private StrategyService strategyService;
    /**
     * 轮播上传
     */
    @SysLog("轮播上传")
    @PreAuthorize("hasAnyRole('ROLE_BD_LB_SCTP','ROLE_ADMIN')")
    @PostMapping("/Slideshow/upload")
    public BaseResp uploadHead(@RequestBody OssVO vo){
        return slideshowService.create(vo);

    }

    /**
     * 查询轮播图
     * @param vo
     * @param
     * @return
     * @throws IOException
     */
    @SysLog("查询轮播图")
    @PreAuthorize("hasAnyRole('ROLE_BD_LB_LB','ROLE_ADMIN')")
    @PostMapping("/Slideshow/get")
    public BaseResp get(@RequestBody OssVO vo){
        return slideshowService.get(vo);

    }

    /**
     * 删除轮播图
     * @param vo
     * @param
     * @return
     * @throws IOException
     */
    @SysLog("删除轮播图")
    @PreAuthorize("hasAnyRole('ROLE_BD_LB_LBSH','ROLE_ADMIN')")
    @PostMapping("/Slideshow/del")
    public BaseResp del(@RequestBody OssVO vo){
        return slideshowService.del(vo);

    }

    /**
     *4.73.	导航栏新增接口
     * @param vo
     * @return
     */
    @SysLog("导航栏新增接口")
    @PreAuthorize("hasAnyRole('ROLE_BD_NV_XZNV','ROLE_ADMIN')")
    @PostMapping("/Navigation/add")
    public BaseResp addNavigation(@RequestBody OssVO vo){
        return navigationService.addNavigation(vo);
    }

    /**
     *.73.	导航栏查询接口
     * @param vo
     * @return
     */
    @SysLog("导航栏查询接口")
    @PreAuthorize("hasAnyRole('ROLE_BD_NV_CXNV','ROLE_ADMIN')")
    @PostMapping("/Navigation/get")
    public BaseResp getNavigation(@RequestBody OssVO vo){
        return navigationService.getNavigation(vo);
    }

    /**
     * 4.117.	导航栏修改接口
     * @param vo
     * @return
     */
    @SysLog("导航栏修改接口")
    @PreAuthorize("hasAnyRole('ROLE_BD_NV_GXNV','ROLE_ADMIN')")
    @PostMapping("/Navigation/update")
    public BaseResp updateNavigation(@RequestBody OssVO vo){
        return navigationService.updateNavigation(vo);
    }

    /**
     * 4.161.	导航栏删除接口
     * @param vo
     * @param
     * @return
     */
    @SysLog("导航栏删除接口")
    @PreAuthorize("hasAnyRole('ROLE_BD_NV_SCNV','ROLE_ADMIN')")
    @PostMapping("/Navigation/del")
    public BaseResp delNavigation(@RequestBody OssVO vo){
        return navigationService.delNavigation(vo);
    }

    /**
     * 4.30.	新手指导查询列表接口
     * @param vo
     * @return
     */
    @SysLog("新手指导查询列表接口")
    @PreAuthorize("hasAnyRole('ROLE_BD_NU_CX','ROLE_ADMIN')")
    @PostMapping("/Beginner/get")
    public BaseResp beginnerGet(@RequestBody OssVO vo){
        return beginnerService.beginnerGet(vo);
    }

    /**
     * 4.30.	新手指导新增接口
     * @param vo
     * @return
     */
    @SysLog("新手指导新增接口")
    @PreAuthorize("hasAnyRole('ROLE_BD_NU_XZ_BUTTON','ROLE_ADMIN')")
    @PostMapping("/Beginner/add")
    public BaseResp beginnerAdd(@RequestBody OssVO vo){
        return beginnerService.beginnerAdd(vo);
    }

    /**
     * 4.118.	新手指导修改接口
     * @param vo
     * @return
     */
    @SysLog("新手指导修改接口")
    @PreAuthorize("hasAnyRole('ROLE_BD_NU_XG_BUTTON','ROLE_ADMIN')")
    @PostMapping("/Beginner/update")
    public BaseResp beginnerupdate(@RequestBody OssVO vo){
        return beginnerService.beginnerupdate(vo);
    }

    /**
     * 4.162.	新手指导删除接口
     * @param vo
     * @return
     */
    @SysLog("新手指导删除接口")
    @PreAuthorize("hasAnyRole('ROLE_BD_NU_SC','ROLE_ADMIN')")
    @PostMapping("/Beginner/del")
    public BaseResp beginnerdel(@RequestBody OssVO vo){
        return beginnerService.beginnerdel(vo);
    }

    /**
     * 4.18.	客服查询接口（属于pc端）
     * @param vo
     * @return
     */
    @SysLog("客服查询接口")
    @PreAuthorize("hasAnyRole('ROLE_BD_GM_CX','ROLE_ADMIN')")
    @PostMapping("/customer/get")
    public BaseResp customerget(@RequestBody OssVO vo){
        return customerService.customerget(vo);
    }

    /**
     * 4.62.	客服新增接口
     * @param vo
     * @return
     */
    @SysLog("客服新增接口")
    @PreAuthorize("hasAnyRole('ROLE_BD_GM_XZ','ROLE_ADMIN')")
    @PostMapping("/customer/add")
    public BaseResp customeradd(@RequestBody OssVO vo){
        return customerService.customeradd(vo);
    }

    /**
     * 4.150.	客服删除接口
     * @param vo
     * @return
     */
    @SysLog("客服删除接口")
    @PreAuthorize("hasAnyRole('ROLE_BD_GM_SC','ROLE_ADMIN')")
    @PostMapping("/customer/del")
    public BaseResp customerdel(@RequestBody OssVO vo){
        return customerService.customerdel(vo);
    }

    /**
     * 4.106.	客服修改接口
     * @param vo
     * @return
     */
    @SysLog("客服修改接口")
    @PreAuthorize("hasAnyRole('ROLE_BD_GM_XG','ROLE_ADMIN')")
    @PostMapping("/customer/update")
    public BaseResp customerupdate(@RequestBody OssVO vo){
        return customerService.customerupdate(vo);
    }

    /**
     * 4.32.	协议查询接口
     * @param vo
     * @return
     */
    @SysLog("协议查询接口")
    @PreAuthorize("hasAnyRole('ROLE_BD_XY_CX','ROLE_ADMIN')")
    @PostMapping("/protocol/get")
    public BaseResp Protocolget(@RequestBody OssVO vo){
        return protocolService.protocolget(vo);
    }

    /**
     * 4.76.	协议新增接口
     * @param vo
     * @return
     */
    @SysLog("协议新增接口")
    @PreAuthorize("hasAnyRole('ROLE_BD_XY_XZ_BUTTON','ROLE_ADMIN')")
    @PostMapping("/protocol/add")
    public BaseResp Protocoladd(@RequestBody OssVO vo){
        return protocolService.protocoladd(vo);
    }

    /**
     * 4.120.	协议修改接口
     * @param vo
     * @return
     */
    @SysLog("协议修改接口")
    @PreAuthorize("hasAnyRole('ROLE_BD_XY_XG_BUTTON','ROLE_ADMIN')")
    @PostMapping("/protocol/update")
    public BaseResp Protocolupdate(@RequestBody OssVO vo){
        return protocolService.protocolupdate(vo);
    }

    /**
     * 4.164.	协议删除接口
     * @param vo
     * @return
     */
    @SysLog("协议删除接口")
    @PreAuthorize("hasAnyRole('ROLE_BD_XY_SC','ROLE_ADMIN')")
    @PostMapping("/protocol/del")
    public BaseResp Protocoldel(@RequestBody OssVO vo){
        return protocolService.Protocoldel(vo);
    }

    /**
     * 4.33.	提现规则查询接口
     * @param vo
     * @return
     */
    @SysLog("提现规则查询接口")
    @PreAuthorize("hasAnyRole('ROLE_BD_TX_CX','ROLE_ADMIN')")
    @PostMapping("/withdrawRule/get")
    public BaseResp withdrawRuleget(@RequestBody OssVO vo){
        return withdrawRuleService.withdrawRuleget(vo);
    }

    /**
     * 4.77.	提现规则新增接口
     * @param vo
     * @return
     */
    @SysLog("提现规则新增接口")
    @PreAuthorize("hasAnyRole('ROLE_BD_TX_XZ_BUTTON','ROLE_ADMIN')")
    @PostMapping("/withdrawRule/add")
    public BaseResp withdrawRuleadd(@RequestBody OssVO vo) {
        return withdrawRuleService.withdrawRuleadd(vo);
    }

    /**
     * 4.121.	提现规则修改接口
     * @param vo
     * @return
     */
    @SysLog("提现规则修改接口")
    @PreAuthorize("hasAnyRole('ROLE_BD_TX_XG_BUTTON','ROLE_ADMIN')")
    @PostMapping("/withdrawRule/update")
    public BaseResp withdrawRuleupdate(@RequestBody OssVO vo) {
        return withdrawRuleService.withdrawRuleupdate(vo);
    }

    /**
     * 4.121.	提现规则删除接口
     * @param vo
     * @return
     */
    @SysLog("提现规则删除接口")
    @PreAuthorize("hasAnyRole('ROLE_BD_TX_SC','ROLE_ADMIN')")
    @PostMapping("/withdrawRule/del")
    public BaseResp withdrawRuledel(@RequestBody OssVO vo) {
        return withdrawRuleService.withdrawRuledel(vo);
    }


    /**
     * 4.35.	投诉建议列表查询接口
     * @param
     * @return
     */
    @SysLog("投诉建议列表查询接口")
    @PreAuthorize("hasAnyRole('ROLE_AM_LB_CK','ROLE_ADMIN')")
    @PostMapping("/vpncomplaintGetsvc")
    public BaseResp vpncomplaintGetsvc(@RequestBody OssVO vo){
        return complaintService.complaintService(vo);
    }


    @PreAuthorize("hasAnyRole('ROLE_AM_LB_CK','ROLE_ADMIN')")
    @PostMapping("/vpncomplaintGetsvc/{id}")
    public BaseResp vpncomplaintGetsvcid(@PathVariable int id){
        return complaintService.vpncomplaintGetsvcid(id);
    }

    /**
     * 4.169.	投诉建议删除接口
     * @param vo
     * @return
     */
    @SysLog("投诉建议删除接口")
    @PreAuthorize("hasAnyRole('ROLE_AM_LB_DELL','ROLE_ADMIN')")
    @PostMapping("/vpncomplaintDelsvc")
    public BaseResp vpncomplaintDelsvc(@RequestBody OssVO vo){
        return complaintService.vpncomplaintDelsvc(vo);
    }

    /**
     * 4.85.	攻略新增接口
     * @param vo
     * @return
     */
    @SysLog("攻略新增接口")
    @PreAuthorize("hasAnyRole('ROLE_IM_LB_XZ','ROLE_ADMIN')")
    @PostMapping("/vpnstrategyAddsvc")
    public BaseResp vpnstrategyAddsvc(@RequestBody OssVO vo){
        return strategyService.vpnstrategyAddsvc(vo);
    }


    /**
     * 4.129.	攻略修改接口
     * @param vo
     * @return
     */
    @SysLog("攻略修改接口")
    @PreAuthorize("hasAnyRole('ROLE_IM_LB_XG','ROLE_ADMIN')")
    @PostMapping("/vpnstrategyUpdatesvc")
    public BaseResp vpnstrategyUpdatesvc(@RequestBody OssVO vo){
        return strategyService.vpnstrategyUpdatesvc(vo);
    }

    /**
     * 4.173.	攻略删除接口
     * @param vo
     * @return
     */
    @SysLog("攻略删除接口")
    @PreAuthorize("hasAnyRole('ROLE_IM_LB_DELL','ROLE_ADMIN')")
    @PostMapping("/vpnstrategyDelsvc")
    public BaseResp vpnstrategyDelsvc(@RequestBody OssVO vo){
        return strategyService.vpnstrategyDelsvc(vo);
    }


    /**
     * 攻略状态变化
     * @param vo
     * @return
     */
    @SysLog("攻略状态变化")
    @PreAuthorize("hasAnyRole('ROLE_IM_LB_XG','ROLE_ADMIN')")
    @PostMapping("/vpnstrategyState")
    public BaseResp vpnstrategyState(@RequestBody OssVO vo){
        return strategyService.vpnstrategyState(vo);
    }

    /**
     * 4.186.	职业类型查询接口
     * @param
     * @return
     */
    @SysLog("职业类型查询接口")
    @RequestMapping("/vpnqsCategoryJobsGetsvc")
    public BaseResp vpnqsCategoryJobsGetsvc(){
        BaseResp baseResp=qsCategoryJobsService.findByqsCategoryJobs2();
        return baseResp;
    }

    /**
     * 富文本
     * @return
     * @throws IOException
     */
    @PreAuthorize("hasAnyRole('ROLE_BD_NU_XG_BUTTON','ROLE_BD_XY_XG_BUTTON','ROLE_BD_TX_XG_BUTTON','ROLE_IM_LB_XG','ROLE_ADMIN')")
    @RequestMapping("/imgUpload")
    public Map imgUpload(HttpServletRequest request) throws IOException {
        log.info("进入接口");
        return UploadUtil.uploadImage2(request,"1","ax-jhrc");
    }

}
