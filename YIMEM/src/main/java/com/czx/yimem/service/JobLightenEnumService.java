package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.JobCustomLightenEnum;
import com.czx.yimem.entity.JobLightenEnum;
import com.czx.yimem.entity.responsebean.BaseResp;

import java.util.List;

public interface JobLightenEnumService {
    int deleteByPrimaryKey(Integer jobLightenId);

    int insert(JobLightenEnum record);

    int insertSelective(JobLightenEnum record);

    JobLightenEnum selectByPrimaryKey(Integer jobLightenId);

    int updateByPrimaryKeySelective(JobLightenEnum record);

    int updateByPrimaryKey(JobLightenEnum record);

    BaseResp selectByJobLighten(OssVO vo);
    BaseResp selectByJobLighten2();

    BaseResp vpnjobLightenEnumAddsvc(OssVO vo);

    BaseResp vpnjobLightenEnumUpdatesvc(OssVO vo);

    BaseResp vpnjobLightenEnumDelsvc(OssVO vo);

    /**
     * 查询工作id
     * 自定义职业亮点
     */
    List<JobLightenEnum> findJobLightenEnumbyjobId(Integer jobId);
}
