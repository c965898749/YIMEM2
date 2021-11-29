package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.QsCategoryGroup;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface QsCategoryGroupService {
    int deleteByPrimaryKey(Integer gId);

    int insert(QsCategoryGroup record);

    int insertSelective(QsCategoryGroup record);

    QsCategoryGroup selectByPrimaryKey(Integer gId);

    int updateByPrimaryKeySelective(QsCategoryGroup record);

    int updateByPrimaryKey(QsCategoryGroup record);

    BaseResp findByqsCategoryGroup(OssVO ossVO);
}
