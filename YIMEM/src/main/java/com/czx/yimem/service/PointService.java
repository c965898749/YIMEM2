package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface PointService {

    public BaseResp pointSign(OssVO vo);


    BaseResp getPointByUserId(int userId);

    BaseResp getPointdetail(OssVO vo);

    BaseResp pointToGift(OssVO vo);
}
