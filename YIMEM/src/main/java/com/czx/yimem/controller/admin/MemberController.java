package com.czx.yimem.controller.admin;

import com.czx.yimem.Annotation.SysLog;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.CompanyService;
import com.czx.yimem.service.DistributionService;
import com.czx.yimem.service.admin.JobhunterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 会员管理
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class MemberController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private JobhunterService jobhunterService;
    @Autowired
    private DistributionService distributionService;
    /**
     * 4.17.	企业查询列表接口
     * @param vo
     * @return
     */
    @SysLog("企业查询列表接口")
    @PreAuthorize("hasAnyRole('ROLE_MM_QY_CX','ROLE_ADMIN')")
    @RequestMapping("/vpncompanyGetsvc")
    public BaseResp vpncompanyGetsvc(@RequestBody OssVO vo){
        BaseResp baseResp=companyService.findBycompany(vo);
        return baseResp;
    }



    /**
     * 4.61.	企业新增接口
     * @param vo
     * @return
     */
    @SysLog("企业新增接口")
    @PreAuthorize("hasAnyRole('ROLE_MM_QY_CX_BUTTON','ROLE_ADMIN')")
    @RequestMapping("/vpncompanyAddsvc")
    public BaseResp vpncompanyAddsvc(@RequestBody OssVO vo){
        BaseResp baseResp=companyService.addBycompany(vo);
        return baseResp;
    }


    /**
     * 4.105.	企业修改接口
     * @param vo
     * @return
     */
    @SysLog("企业修改接口")
    @PreAuthorize("hasAnyRole('ROLE_MM_QY_EXAMINE','ROLE_MM_QY_DISTRIBUTION','ROLE_ADMIN')")
    @RequestMapping("/vpncompanyUpdatesvc")
    public BaseResp vpncompanyUpdatesvc(@RequestBody OssVO vo){
        BaseResp baseResp=companyService.updateBycompany(vo);
        return baseResp;
    }

    /**
     * 4.23.	求职者列表查询接口
     * @param vo
     * @return
     */
    @SysLog("求职者列表查询接口")
    @PreAuthorize("hasAnyRole('ROLE_MM_GR_EXAMINE','ROLE_MM_GR','ROLE_ADMIN')")
    @RequestMapping("/vpnjobhunterGetsvc")
    public BaseResp vpnjobhunterGetsvc(@RequestBody OssVO vo){
        BaseResp baseResp=jobhunterService.vpnjobhunterGetsvc(vo);
        return baseResp;
    }


    /**
     * 4.24.	分销者查询列表接口
     * @param vo
     * @return
     */
    @SysLog("分销者查询列表接口")
    @PreAuthorize("hasAnyRole('ROLE_MM_GR_CK','ROLE_ADMIN')")
    @RequestMapping("/vpndistributionGetsvc")
    public BaseResp vpndistributionGetsvc(@RequestBody OssVO vo){
        BaseResp baseResp=distributionService.vpndistributionGetsvc(vo);
        return baseResp;
    }

    /**
     * 4.116.	分销者修改接口
     * @param vo
     * @return
     */
    @SysLog("分销者修改接口")
    @PreAuthorize("hasAnyRole('ROLE_MM_GR_EXAMINE','ROLE_ADMIN')")
    @RequestMapping("/vpndistributionUpdatesvc")
    public BaseResp vpndistributionUpdatesvc(@RequestBody OssVO vo){
        BaseResp baseResp=distributionService.vpndistributionUpdatesvc(vo);
        return baseResp;
    }


    /**
     * 4.17.	企业查询接口
     * @param
     * @return
     */
    @SysLog("企业查询接口")
    @PreAuthorize("hasAnyRole('ROLE_MM_QY_CX','ROLE_ADMIN')")
    @RequestMapping("/vpncompanyGetsvc/{id}")
    public BaseResp vpncompanyGetsvcid(@PathVariable int id){
        BaseResp baseResp=companyService.findBycompanyid(id);
        return baseResp;
    }
}
