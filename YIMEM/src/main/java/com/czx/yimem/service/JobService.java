package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.Job;
import com.czx.yimem.entity.responsebean.BaseResp;

import java.util.List;

public interface JobService {
    int deleteByPrimaryKey(Integer jobId);

    int insert(Job record);

    BaseResp insertSelective(OssVO vo);

    Job selectByPrimaryKey(Integer jobId);

    BaseResp updateByPrimaryKeySelective(OssVO vo);
    BaseResp updateByPrimaryKeySelective2(OssVO vo);
    BaseResp updateByPrimaryKeySelective3(OssVO vo);
    BaseResp updateByPrimaryKeySelective4(OssVO vo);

    BaseResp vpnjobUpdateStatesvc(OssVO vo);

    int updateByPrimaryKey(Job record);

    BaseResp selectByJob(OssVO vo);

    BaseResp selectByJob2(OssVO vo);

    BaseResp selectByJobId(Integer id);

    BaseResp selectByJobId2(Integer id);

    BaseResp delByjob(OssVO vo);

    Job selectById(Integer jobId);

    String selectphone(Integer jobId);
}
