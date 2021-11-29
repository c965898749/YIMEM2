package com.czx.yimem.service;

import com.czx.yimem.dto.DeliverDetailVO;
import com.czx.yimem.dto.ManshVO;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.DeliverDetail;
import com.czx.yimem.entity.responsebean.BaseResp;

import java.util.List;

public interface DeliverDetailService {
    int deleteByPrimaryKey(Integer id);

    int insert(DeliverDetail record);

    int insertSelective(DeliverDetail record);

    DeliverDetail selectByPrimaryKey(Integer id);

    ManshVO findmanshVO(Integer id);

    int updateByPrimaryKeySelective(DeliverDetail record);

    int updateByPrimaryKey(DeliverDetail record);

    Integer updateisRuIncome(Integer id);

    /**
     * 根据简历标识查询所有简历状态
     * @return
     */
    List<DeliverDetail> findBydevId(Integer devId);

    Integer findcompanyuserid(Integer devId);

    Integer findUserid(Integer devId);

    BaseResp findBydeliverDetail(OssVO ossVO);

    BaseResp addBydeliverDetail(DeliverDetail deliverDetail) throws Exception;

    BaseResp leaveAudit(DeliverDetail deliverDetail);


//    BaseResp interview(DeliverDetailVO detailVO);

    BaseResp inappropriate(Integer id);

    BaseResp updateBydeliverDetail(OssVO ossVO);

    BaseResp delBydeliverDetail(OssVO ossVO);

    BaseResp vpninviteUpdatesvc(OssVO ossVO) throws Exception;

    BaseResp vpninviteGetsvc(Integer id);
}
