package com.czx.yimem.dao;

import com.czx.yimem.entity.BrokerageDetail;

public interface BrokerageDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrokerageDetail record);

    int insertSelective(BrokerageDetail record);

    BrokerageDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrokerageDetail record);

    int updateByPrimaryKey(BrokerageDetail record);
}