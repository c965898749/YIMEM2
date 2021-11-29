package com.czx.yimem.dao;

import com.czx.yimem.entity.QsCategoryJobs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QsCategoryJobsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QsCategoryJobs record);

    int insertSelective(QsCategoryJobs record);

    QsCategoryJobs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QsCategoryJobs record);

    int updateByPrimaryKeyWithBLOBs(QsCategoryJobs record);

    int updateByPrimaryKey(QsCategoryJobs record);

    List<QsCategoryJobs> findByqsCategoryJobs(QsCategoryJobs record);

    List<QsCategoryJobs> selecAll();

}
