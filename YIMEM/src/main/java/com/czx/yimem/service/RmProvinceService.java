package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.RmProvince;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface RmProvinceService {
    int deleteByPrimaryKey(String uuid);

    int insert(RmProvince record);

    int insertSelective(RmProvince record);

    RmProvince selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(RmProvince record);

    int updateByPrimaryKey(RmProvince record);

    /**
     * 省份查询
     * @param ossVO
     * @return
     */
    BaseResp findByrmProvince(OssVO ossVO);
}
