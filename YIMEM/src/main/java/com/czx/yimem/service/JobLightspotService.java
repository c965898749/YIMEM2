package com.czx.yimem.service;

import com.czx.yimem.entity.JobLightenEnum;
import com.czx.yimem.entity.JobLightspot;

import java.util.List;

public interface JobLightspotService {
    int deleteByPrimaryKey(Integer id);

    int insert(JobLightspot record);

    int insertSelective(JobLightspot record);

    JobLightspot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobLightspot record);

    int updateByPrimaryKey(JobLightspot record);

    /**
     * 批量插入通用职位亮点关联
     */
    int saveJobLightspots(List<JobLightspot> jobLightspots);

    /**
     * 根据职业id删除职业亮点关联
     */
    int delByJob(Integer job);

}
