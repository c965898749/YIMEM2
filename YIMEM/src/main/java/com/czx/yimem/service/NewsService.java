package com.czx.yimem.service;

import com.czx.yimem.dto.IdentityVO;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.News;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface NewsService {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    public BaseResp updatesiread(OssVO vo);

    BaseResp findBynews(OssVO ossVO);

    BaseResp isnewmsg(IdentityVO ossVO);

    BaseResp addBynews(OssVO ossVO);

    BaseResp updateBynews(OssVO ossVO);

    BaseResp delBynews(OssVO ossVO);
}
