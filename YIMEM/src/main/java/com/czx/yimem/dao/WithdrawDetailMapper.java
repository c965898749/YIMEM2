package com.czx.yimem.dao;

import com.czx.yimem.entity.WithdrawDetail;

public interface WithdrawDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WithdrawDetail record);

    int insertSelective(WithdrawDetail record);

    WithdrawDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WithdrawDetail record);

    int updateByPrimaryKey(WithdrawDetail record);
}