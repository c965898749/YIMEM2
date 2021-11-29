package com.czx.yimem.dao;

import com.czx.yimem.entity.RevenueManagement;

public interface RevenueManagementMapper {
    int deleteByPrimaryKey(Integer revId);

    int insert(RevenueManagement record);

    int insertSelective(RevenueManagement record);

    RevenueManagement selectByPrimaryKey(Integer revId);

    int updateByPrimaryKeySelective(RevenueManagement record);

    int updateByPrimaryKey(RevenueManagement record);
}