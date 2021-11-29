package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.Language;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface LanguageService {
    int deleteByPrimaryKey(Integer id);

    int insert(Language record);

    int insertSelective(Language record);

    Language selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Language record);

    int updateByPrimaryKey(Language record);

    BaseResp findBylanguage(OssVO ossVO);

    BaseResp addBylanguage(OssVO ossVO);

    BaseResp updateBylanguage(OssVO ossVO);

    BaseResp delBylanguage(OssVO ossVO);
}
