package com.czx.yimem.service.admin;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.Jobhunter;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface JobhunterService {
    int deleteByPrimaryKey(Integer id);

    int insert(Jobhunter record);

    int insertSelective(Jobhunter record);

    Jobhunter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Jobhunter record);

    int updateByPrimaryKey(Jobhunter record);

    BaseResp vpnjobhunterGetsvc(OssVO vo);
}
