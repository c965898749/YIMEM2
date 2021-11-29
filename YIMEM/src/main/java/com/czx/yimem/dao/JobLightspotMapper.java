package com.czx.yimem.dao;

import com.czx.yimem.entity.JobLightenEnum;
import com.czx.yimem.entity.JobLightspot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface JobLightspotMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobLightspot record);

    int insertSelective(JobLightspot record);

    JobLightspot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobLightspot record);

    int updateByPrimaryKey(JobLightspot record);

    List<JobLightspot> selectByjobId(@Param("jobId")Integer jobId);

    int delByJob(@Param("jobId")Integer jobId);
}
