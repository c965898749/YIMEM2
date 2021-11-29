package com.czx.yimem.dao;

import com.czx.yimem.entity.QsCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QsCategoryMapper {
    int deleteByPrimaryKey(@Param("cId") Integer cId);

    int insert(QsCategory record);

    int insertSelective(QsCategory record);

    QsCategory selectByPrimaryKey(Integer cId);

    int updateByPrimaryKeySelective(QsCategory record);

    int updateByPrimaryKey(QsCategory record);

    List<QsCategory> findByqsCategory(QsCategory record);
}
