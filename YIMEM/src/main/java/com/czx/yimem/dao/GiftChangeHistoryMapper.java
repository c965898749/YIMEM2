package com.czx.yimem.dao;

import com.czx.yimem.entity.GiftChangeHistory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GiftChangeHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GiftChangeHistory record);

    int insertSelective(GiftChangeHistory record);

    GiftChangeHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GiftChangeHistory record);

    int updateByPrimaryKey(GiftChangeHistory record);
}