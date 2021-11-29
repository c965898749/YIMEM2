package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.RmCity;
import com.czx.yimem.entity.responsebean.BaseResp;


public interface RmCityService {
    int deleteByPrimaryKey(String uuid);

    int insert(RmCity record);

    int insertSelective(RmCity record);

    RmCity selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(RmCity record);

    int updateByPrimaryKey(RmCity record);

    BaseResp findByrmCity(OssVO ossVO);

}
