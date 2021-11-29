package com.czx.yimem.service.admin;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.Customer;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface CustomerService {
    int deleteByPrimaryKey(Integer cusId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer cusId);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    BaseResp customerget(OssVO vo);

    BaseResp customeradd(OssVO vo);

    BaseResp customerdel(OssVO vo);

    BaseResp customerupdate(OssVO vo);
}
