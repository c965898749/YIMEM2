package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.Collect;
import com.czx.yimem.entity.CollectRec;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface CollectService {
    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    BaseResp findBycollect(OssVO ossVO);

    BaseResp vpniscollectJob(Collect collect);

    BaseResp addBycollect(OssVO ossVO);

    BaseResp addBycollect2(OssVO ossVO);

    BaseResp vpncompanycollectdev(Collect collect);

    BaseResp updateBycollect(OssVO ossVO);

    BaseResp delBycollect(OssVO ossVO);

    BaseResp insertRecSelective(CollectRec record);

    BaseResp delBycollectrec(CollectRec record);
}
