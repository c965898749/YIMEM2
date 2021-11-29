package com.czx.yimem.dao;

import com.czx.yimem.entity.Point;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PointMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Point record);

    int insertSelective(Point record);

    Point selectByPrimaryKey(Integer id);
    Point selectByUserKey(Integer userId);

    int updateByPrimaryKeySelective(Point record);

    int updateByPrimaryKey(Point record);
}