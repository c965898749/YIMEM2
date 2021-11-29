package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.Distribution;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface DistributionService {
    int deleteByPrimaryKey(Integer id);

    int insert(Distribution record);

    int insertSelective(Distribution record);

    Distribution selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Distribution record);

    int updateByPrimaryKey(Distribution record);

    BaseResp vpndistributionGetCardsvc(OssVO vo);

    BaseResp vpndistributionGetsvc(OssVO vo);

    BaseResp vpndistributionAddsvc(OssVO vo);

    BaseResp vpndistributionUpdatesvc(OssVO vo);
}
