package com.czx.yimem.service.admin;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.TouserVO;
import com.czx.yimem.entity.Protocol;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface ProtocolService {
    int deleteByPrimaryKey(Integer prId);

    int insert(Protocol record);

    int insertSelective(Protocol record);

    Protocol selectByPrimaryKey(Integer prId);

    int updateByPrimaryKeySelective(Protocol record);

    int updateByPrimaryKey(Protocol record);

    BaseResp protocolget(OssVO vo);

    BaseResp vpnprotocolGet(TouserVO touser);

    BaseResp protocolgetByeId(Integer id);

    BaseResp protocoladd(OssVO vo);

    BaseResp protocolupdate(OssVO vo);

    BaseResp Protocoldel(OssVO vo);

    void updatefindTimeUp();
}
