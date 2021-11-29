package com.czx.yimem.dao;

import com.czx.yimem.entity.QsCategoryMajor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QsCategoryMajorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QsCategoryMajor record);

    int insertSelective(QsCategoryMajor record);

    QsCategoryMajor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QsCategoryMajor record);

    int updateByPrimaryKey(QsCategoryMajor record);

    List<QsCategoryMajor> findByqsCategoryMajor(QsCategoryMajor record);

    List<QsCategoryMajor> findAll();
}
