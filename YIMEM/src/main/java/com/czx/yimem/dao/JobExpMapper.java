package com.czx.yimem.dao;

import com.czx.yimem.entity.JobExp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JobExpMapper {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(JobExp record);

    int insertSelective(JobExp record);

    JobExp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobExp record);

    int updateByPrimaryKey(JobExp record);

    int findByjobExpCount(Integer userId);


    List<JobExp> findByjobExp(JobExp jobExp);
}
