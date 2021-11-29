package com.czx.yimem.dao;

import com.czx.yimem.entity.RevenueManagementDetail;

public interface RevenueManagementDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RevenueManagementDetail record);

    int insertSelective(RevenueManagementDetail record);

    RevenueManagementDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RevenueManagementDetail record);

    int updateByPrimaryKey(RevenueManagementDetail record);
}