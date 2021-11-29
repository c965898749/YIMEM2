package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.Deliver;
import com.czx.yimem.entity.Recommend;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface DeliverService {
    int deleteByPrimaryKey(Integer devId);

    int insert(Deliver record);

    int insertSelective(Deliver record);

    Deliver selectByPrimaryKey(Integer devId);

    int updateByPrimaryKeySelective(Deliver record);

    int updateByPrimaryKey(Deliver record);

    /**
     * 按条件查询简历
     * @param ossVO
     * @return
     */
    BaseResp findBydeliver(OssVO ossVO);

    BaseResp findBydeliver5(OssVO ossVO);

    BaseResp findBydeliver4(OssVO ossVO);

    BaseResp findBydeliver2(Deliver deliver, PageVO pageVO);

    BaseResp findBydeliver3(Deliver deliver, PageVO pageVO);

    BaseResp vpndeliverGetByidsvc(Integer id);

    BaseResp vpndeliverGetByidsvc2(Recommend recommend);

    BaseResp addBydeliver(OssVO ossVO) throws Exception;

    BaseResp updateBydeliver(OssVO ossVO);

    BaseResp delBydeliver(OssVO ossVO);

    BaseResp vpnrecommendUserDetailGetsvc(Integer userId,Integer id);

    String selectState(Integer id);

}
