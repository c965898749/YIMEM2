package com.czx.yimem.dao;

import com.czx.yimem.entity.JobCustomLightenEnum;
import com.czx.yimem.entity.JobLightenEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JobCustomLightenEnumMapper {
    int deleteByPrimaryKey(Integer jobLightenId);

    int insert(JobCustomLightenEnum record);

    int insertSelective(JobCustomLightenEnum record);

    JobCustomLightenEnum selectByPrimaryKey(Integer jobLightenId);

    int updateByPrimaryKeySelective(JobCustomLightenEnum record);

    int updateByPrimaryKey(JobCustomLightenEnum record);

    List<JobCustomLightenEnum> selectByJobCustomLighten(JobCustomLightenEnum record);

    List<JobCustomLightenEnum> findJobCustomLightenEnumbyjobId(@Param("jobId") Integer jobId);

    Integer delByjobId(@Param("jobId") Integer jobId);
}
