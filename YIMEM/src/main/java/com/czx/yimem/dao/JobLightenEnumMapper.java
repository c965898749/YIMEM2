package com.czx.yimem.dao;

import com.czx.yimem.entity.JobCustomLightenEnum;
import com.czx.yimem.entity.JobLightenEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JobLightenEnumMapper {
    int deleteByPrimaryKey(Integer jobLightenId);

    int insert(JobLightenEnum record);

    int insertSelective(JobLightenEnum record);

    JobLightenEnum selectByPrimaryKey(Integer jobLightenId);

    int updateByPrimaryKeySelective(JobLightenEnum record);

    int updateByPrimaryKey(JobLightenEnum record);
    List<JobLightenEnum> selectByJobLighten(JobLightenEnum record);

    List<JobLightenEnum> findJobLightenEnumbyjobId(@Param("jobId") Integer jobId);

}
