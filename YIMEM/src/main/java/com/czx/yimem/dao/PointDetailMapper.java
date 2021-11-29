package com.czx.yimem.dao;

import com.czx.yimem.entity.PointDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PointDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PointDetail record);

    int insertSelective(PointDetail record);

    PointDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PointDetail record);

    int updateByPrimaryKey(PointDetail record);

    List<PointDetail> queryMonth(PointDetail record);

    List<PointDetail> queryYear(PointDetail record);


}