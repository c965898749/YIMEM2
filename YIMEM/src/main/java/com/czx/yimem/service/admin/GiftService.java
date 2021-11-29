package com.czx.yimem.service.admin;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.responsebean.BaseResp;

/**
 * @author chenli
 * @createTime 2021/4/2 15:54
 */
public interface GiftService {
    BaseResp getList(OssVO vo);

    BaseResp updateGift(OssVO vo);

    BaseResp delGift(OssVO vo);

    BaseResp getGiftList(OssVO vo);

}
