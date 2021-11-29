package com.czx.yimem.dao;

import com.czx.yimem.entity.JobWanted;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JobWantedMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobWanted record);

    int insertSelective(JobWanted record);

    JobWanted selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobWanted record);

    int updateByPrimaryKey(JobWanted record);

    List<JobWanted> findByjobWanted(JobWanted jobWanted);

    int updateByuserId(JobWanted jobWanted);

    int delByuserId(JobWanted jobWanted);

    int findBywantedCount(Integer userId);
}
