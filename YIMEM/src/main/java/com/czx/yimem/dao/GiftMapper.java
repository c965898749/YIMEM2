package com.czx.yimem.dao;

import com.czx.yimem.entity.Gift;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GiftMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Gift record);

    int insertSelective(Gift record);

    Gift selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Gift record);

    int updateByPrimaryKey(Gift record);

    List<Gift> getList();

    List<Gift> getGiftList();
}