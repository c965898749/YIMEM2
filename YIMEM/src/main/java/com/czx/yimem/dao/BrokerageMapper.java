package com.czx.yimem.dao;

import com.czx.yimem.entity.Brokerage;

public interface BrokerageMapper {
    int deleteByPrimaryKey(Integer broId);

    int insert(Brokerage record);

    int insertSelective(Brokerage record);

    Brokerage selectByPrimaryKey(Integer broId);

    int updateByPrimaryKeySelective(Brokerage record);

    int updateByPrimaryKey(Brokerage record);
}