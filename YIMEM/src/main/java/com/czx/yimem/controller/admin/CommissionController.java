package com.czx.yimem.controller.admin;

import com.czx.yimem.Annotation.SysLog;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.financial.*;
import com.czx.yimem.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin/financial")
public class CommissionController {
    @Autowired
    private IncomeDetailService incomeDetailService;
    @Autowired
    private CompanyBillService companyBillService;
    @Autowired
    private CompanyBillDetailService companyBillDetailService;
    @Autowired
    private IncomeService incomeService;
    @Autowired
    private ApplicationForWithdrawalService applicationForWithdrawalService;

    /**
     *4.26.	分销者收入明细查询接口
     * @param vo
     * @return
     */
    @SysLog("分销者收入明细查询接口")
    @PreAuthorize("hasAnyRole('ROLE_CM_YFLB_CK','ROLE_ADMIN')")
    @PostMapping("/vpnincomeDetailGetsvc")
    public BaseResp vpnincomeDetailGetsvc(@RequestBody OssVO vo) {
        return incomeDetailService.vpnincomeDetailGetsvc(vo);
    }

    /**
     *4.206.	企业账单列表查询接口
     * @param vo
     * @return
     */
    @SysLog("企业账单列表查询接口")
    @PreAuthorize("hasAnyRole('ROLE_CM_SR_LB_CK','ROLE_ADMIN')")
    @PostMapping("/vpnCompanyBillGetsvc")
    public BaseResp vpnCompanyBillGetsvc(@RequestBody OssVO vo) {
        return companyBillService.vpnCompanyBillGetsvc(vo);
    }

    /**
     *4.208.	企业账单详细清单查询接口
     * @param vo
     * @return
     */
    @SysLog("企业账单详细清单查询接口")
    @PreAuthorize("hasAnyRole('ROLE_CM_YFLB_CK','ROLE_ADMIN')")
    @PostMapping("/vpnCompanyBillDetailGetsvc")
    public BaseResp vpnCompanyBillDetailGetsvc(@RequestBody OssVO vo) {
        return companyBillDetailService.vpnCompanyBillDetailGetsvc(vo);
    }

    /**
     *4.208.	企业账单列表更新接口
     * @param vo
     * @return
     */
    @SysLog("企业账单列表更新接口")
    @PreAuthorize("hasAnyRole('ROLE_CM_SR_COLLECTION','ROLE_ADMIN')")
    @PostMapping("/vpnCompanyBillUpdatesvc")
    public BaseResp vpnCompanyBillUpdatesvc(@RequestBody OssVO vo) {
        return companyBillService.vpnCompanyBillUpdatesvc(vo);
    }

    /**
     *4.209.	分销者收入主表查询接口
     * @param vo
     * @return
     */
    @SysLog("分销者收入主表查询接口")
    @PreAuthorize("hasAnyRole('ROLE_CM_YFLB_CK','ROLE_CM_YF_LB','ROLE_ADMIN')")
    @PostMapping("/vpnIncomeGetsvc")
    public BaseResp vpnIncomeGetsvc(@RequestBody OssVO vo) {
        return incomeService.vpnIncomeGetsvc(vo);
    }

    /**
     * 4.210.	分销者提现申请查询接口
     * @param vo
     * @return
     */
    @SysLog("分销者提现申请查询接口")
    @PreAuthorize("hasAnyRole('ROLE_CM_TX','ROLE_CM_TX_CK','ROLE_ADMIN')")
    @PostMapping("/vpnApplicationForWithdrawalGetsvc")
    public BaseResp vpnApplicationForWithdrawalGetsvc(@RequestBody OssVO vo) {
        return applicationForWithdrawalService.vpnApplicationForWithdrawalGetsvc(vo);
    }

    /**
     * 4.211.	分销者提现更新查询接口
     * @param vo
     * @return
     */
    @SysLog("分销者提现更新查询接口")
    @PreAuthorize("hasAnyRole('ROLE_CM_TX_EXAMINE','ROLE_ADMIN')")
    @PostMapping("/vpnApplicationForWithdrawalUpdatesvc")
    public BaseResp vpnApplicationForWithdrawalUpdatesvc(@RequestBody OssVO vo) {
        try {
            return applicationForWithdrawalService.vpnApplicationForWithdrawalUpdatesvc(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
