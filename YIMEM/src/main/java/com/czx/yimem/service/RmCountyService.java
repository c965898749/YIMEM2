package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.RmCounty;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface RmCountyService {
    int deleteByPrimaryKey(String uuid);

    int insert(RmCounty record);

    int insertSelective(RmCounty record);

    RmCounty selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(RmCounty record);

    int updateByPrimaryKey(RmCounty record);

    BaseResp findByrmCounty(OssVO ossVO);
}
