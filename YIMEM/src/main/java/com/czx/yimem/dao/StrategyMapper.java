package com.czx.yimem.dao;

import com.czx.yimem.entity.Strategy;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StrategyMapper {
    int deleteByPrimaryKey(Integer stId);

    int insert(Strategy record);

    int insertSelective(Strategy record);

    Strategy selectByPrimaryKey(Integer stId);

    int updateByPrimaryKeySelective(Strategy record);

    int updateByPrimaryKey(Strategy record);

    List<Strategy> findBycomplain(Strategy record);

    Integer updateReadcount(Integer stId);

    void updatefindTimeUp();
}
