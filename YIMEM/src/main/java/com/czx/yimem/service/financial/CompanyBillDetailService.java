package com.czx.yimem.service.financial;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.CompanyBillDetail;
import com.czx.yimem.entity.responsebean.BaseResp;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/13 0013 10:08
 */
public interface CompanyBillDetailService {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyBillDetail record);

    int insertSelective(CompanyBillDetail record);

    CompanyBillDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompanyBillDetail record);

    int updateByPrimaryKey(CompanyBillDetail record);

    BaseResp vpnCompanyBillDetailGetsvc(OssVO vo);

    List<CompanyBillDetail> findBycompanyId(Integer  companyId);
}
