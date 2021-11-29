package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.JobCustomLightenEnum;
import com.czx.yimem.entity.responsebean.BaseResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobCustomLightenEnumService {
    int deleteByPrimaryKey(Integer jobLightenId);

    int insert(JobCustomLightenEnum record);

    int insertSelective(JobCustomLightenEnum record);

    JobCustomLightenEnum selectByPrimaryKey(Integer jobLightenId);

    int updateByPrimaryKeySelective(JobCustomLightenEnum record);

    int updateByPrimaryKey(JobCustomLightenEnum record);

    BaseResp selectByJobCustomLighten(OssVO vo);

    /**
     * 查询工作id
     * 自定义职业亮点
     */
    List<JobCustomLightenEnum> findJobCustomLightenEnumbyjobId(Integer jobId);

    /**
     * 批量插入自定职业亮点
     *
     */
    Integer saveJobCustomLightenEnumbyjobId(List<JobCustomLightenEnum> jobCustomLightenEnums);
    /**
     * 按职业id删除自定义亮点
     */
    Integer delByjobId(Integer jobId);
}
