package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.Company;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface CompanyService {
    int deleteByPrimaryKey(Integer companyId);

    int insert(Company record);

    int insertSelective(Company record);

    int selectisAgree(Integer companyId);

    Company selectByPrimaryKey(Integer companyId);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

    BaseResp findBycompany(OssVO ossVO);

    BaseResp addBycompany(OssVO ossVO);

    Integer companydeliver(Integer userId,Integer id);

    BaseResp updateBycompany(OssVO ossVO);

    BaseResp delBycompany(OssVO ossVO);

    BaseResp findBycompanyid(Integer id);

    BaseResp updateIsread(Integer id);

    BaseResp findBycompanyPhone(String phone);

    Integer findByUtoU(Integer userId,Integer comuserId);

    Integer finduserId(Integer companyId);
}
