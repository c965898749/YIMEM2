package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.ProjectExp;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface ProjectExpService {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectExp record);

    int insertSelective(ProjectExp record);

    ProjectExp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectExp record);

    int updateByPrimaryKey(ProjectExp record);

    BaseResp findByprojectExp(OssVO ossVO);

    BaseResp addByprojectExp(OssVO ossVO);

    BaseResp updateByprojectExp(OssVO ossVO);

    BaseResp delByprojectExp(OssVO ossVO);
}
