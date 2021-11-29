package com.czx.yimem.dao;

import com.czx.yimem.entity.CollectRec;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CollectRecMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CollectRec record);

    int insertSelective(CollectRec record);

    CollectRec selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CollectRec record);

    int updateByPrimaryKey(CollectRec record);
}