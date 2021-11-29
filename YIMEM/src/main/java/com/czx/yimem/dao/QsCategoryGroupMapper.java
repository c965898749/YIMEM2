package com.czx.yimem.dao;

import com.czx.yimem.entity.QsCategoryGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QsCategoryGroupMapper {
    int deleteByPrimaryKey(Integer gId);

    int insert(QsCategoryGroup record);

    int insertSelective(QsCategoryGroup record);

    QsCategoryGroup selectByPrimaryKey(Integer gId);

    int updateByPrimaryKeySelective(QsCategoryGroup record);

    int updateByPrimaryKey(QsCategoryGroup record);

    List<QsCategoryGroup> findByqsCategoryGroup(QsCategoryGroup record);
}
