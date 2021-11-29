package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.JobExp;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface JobExpService {
    int deleteByPrimaryKey(Integer id);

    int insert(JobExp record);

    int insertSelective(JobExp record);

    JobExp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobExp record);

    int updateByPrimaryKey(JobExp record);

    BaseResp findByjobExp(OssVO ossVO);

    BaseResp addByjobExp(OssVO ossVO);

    BaseResp updateByjobExp(OssVO ossVO);

    BaseResp delByjobExp(OssVO ossVO);
}
