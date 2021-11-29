package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.Strategy;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface StrategyService {
    int deleteByPrimaryKey(Integer stId);

    int insert(Strategy record);

    int insertSelective(Strategy record);

    Strategy selectByPrimaryKey(Integer stId);

    int updateByPrimaryKeySelective(Strategy record);

    int updateByPrimaryKey(Strategy record);

    BaseResp vpnstrategyGetsvc(OssVO vo);

    BaseResp vpnstrategyGetsvcid(Integer id);

    BaseResp vpnstrategyAddsvc(OssVO vo);

    BaseResp vpnstrategyUpdatesvc(OssVO vo);

    BaseResp vpnstrategyDelsvc(OssVO vo);

    BaseResp vpnstrategyState(OssVO vo);

    void updatefindTimeUp();
}
