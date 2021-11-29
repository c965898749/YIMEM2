package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.EducationExp;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface EducationExpService {
    int deleteByPrimaryKey(Integer id);

    int insert(EducationExp record);

    int insertSelective(EducationExp record);

    EducationExp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EducationExp record);

    int updateByPrimaryKey(EducationExp record);

    /**
     * 条件查询学历
     * @param ossVO
     * @return
     */
    BaseResp findByeducationExp(OssVO ossVO);

    /**
     * 条件新增学历
     * @param ossVO
     * @return
     */
    BaseResp addByeducationExp(OssVO ossVO);

    /**
     * 更新学历
     * @param ossVO
     * @return
     */
    BaseResp updateByeducationExp(OssVO ossVO);

    /**
     * 删除学历
     * @param ossVO
     * @return
     */
    BaseResp delByeducationExp(OssVO ossVO);
}
