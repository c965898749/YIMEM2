package com.czx.yimem.dao;

import com.czx.yimem.entity.WithdrawRule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WithdrawRuleMapper {
    int deleteByPrimaryKey(Integer wiId);

    int insert(WithdrawRule record);

    int insertSelective(WithdrawRule record);

    WithdrawRule selectByPrimaryKey(Integer wiId);

    WithdrawRule selectBytouser();

    int updateByPrimaryKeySelective(WithdrawRule record);

    int updateByPrimaryKey(WithdrawRule record);

    List<Integer> findTimeUpid();

    int updateState();

    List<WithdrawRule> findByWithdrawRule(WithdrawRule record);

    void updatefindTimeUp(Integer id);
}
