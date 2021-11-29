package com.czx.yimem.dao;

import com.czx.yimem.entity.RmCity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RmCityMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(RmCity record);

    int insertSelective(RmCity record);

    RmCity selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(RmCity record);

    int updateByPrimaryKey(RmCity record);

    List<RmCity> findByCity(RmCity record);
}
