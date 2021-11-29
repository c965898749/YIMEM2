package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.Message;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface MessageService {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    /**
     * 条件查询消息
     * @param ossVO
     * @return
     */
    BaseResp findBymessage(OssVO ossVO);

    BaseResp addBymessage(OssVO ossVO);

    BaseResp delBymessage(OssVO ossVO);

    BaseResp udateBymessage(OssVO ossVO);
}
