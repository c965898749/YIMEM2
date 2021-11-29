package com.czx.yimem.dao;

import com.czx.yimem.entity.RmCounty;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RmCountyMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(RmCounty record);

    int insertSelective(RmCounty record);

    RmCounty selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(RmCounty record);

    int updateByPrimaryKey(RmCounty record);

    List<RmCounty> findByCounty(RmCounty rmCounty);
}
