package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.IncomeDetail;
import com.czx.yimem.entity.Recommend;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface RecommendService {
    int deleteByPrimaryKey(Integer reId);

    int insert(Recommend record);

    int insertSelective(Recommend record);

    Recommend selectByPrimaryKey(Integer reId);

    Recommend selectByDelId(Recommend record);

    int updateByPrimaryKeySelective(Recommend record);

    int updateByPrimaryKey(Recommend record);

    int updateiscount(IncomeDetail incomeDetail);

    Integer selectrecommend(Recommend record);

    BaseResp vpnrecommendGetsvc(OssVO ossVO);
}
