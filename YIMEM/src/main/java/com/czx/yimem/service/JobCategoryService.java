package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.JobCategory;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface JobCategoryService {
    int deleteByPrimaryKey(Integer jobCateId);

    int insert(JobCategory record);

    int insertSelective(JobCategory record);

    JobCategory selectByPrimaryKey(Integer jobCateId);

    int updateByPrimaryKeySelective(JobCategory record);

    int updateByPrimaryKey(JobCategory record);

    BaseResp selectByjobcategory(OssVO ossVO);

    BaseResp selectByjobcategory3(OssVO ossVO);

    BaseResp selectByjobcategory2(OssVO ossVO);

    BaseResp addByjobcategory(OssVO ossVO);

    BaseResp updateByjobcategory(OssVO ossVO);

    BaseResp delByjobcategory(OssVO ossVO);
}
