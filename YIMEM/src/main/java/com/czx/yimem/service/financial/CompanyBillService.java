package com.czx.yimem.service.financial;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.CompanyBill;
import com.czx.yimem.entity.responsebean.BaseResp;

import java.util.List;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/13 0013 10:06
 */
public interface CompanyBillService {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyBill record);

    int insertSelective(CompanyBill record);

    CompanyBill selectByPrimaryKey2(Integer id);

    int updateByPrimaryKeySelective(CompanyBill record);

    int updateByPrimaryKey(CompanyBill record);

    BaseResp vpnCompanyBillGetsvc(OssVO ossVO);

    BaseResp vpnCompanyBillUpdatesvc(OssVO ossVO);

    List<CompanyBill> findBycompanyBill(CompanyBill record);

    CompanyBill selectByPrimaryKey(Integer id);

}
