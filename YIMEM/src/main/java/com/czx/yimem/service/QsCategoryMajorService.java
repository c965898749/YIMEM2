package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.QsCategoryMajor;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface QsCategoryMajorService {
    int deleteByPrimaryKey(Integer id);

    int insert(QsCategoryMajor record);

    int insertSelective(QsCategoryMajor record);

    QsCategoryMajor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QsCategoryMajor record);

    int updateByPrimaryKey(QsCategoryMajor record);

    BaseResp findByqsCategoryMajor(OssVO ossVO);

    BaseResp findByqsCategoryMajor2();
}
