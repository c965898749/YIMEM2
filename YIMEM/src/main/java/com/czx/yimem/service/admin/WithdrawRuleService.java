package com.czx.yimem.service.admin;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.WithdrawRule;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface WithdrawRuleService {
    int deleteByPrimaryKey(Integer wiId);

    int insert(WithdrawRule record);

    int insertSelective(WithdrawRule record);

    WithdrawRule selectByPrimaryKey(Integer wiId);

    BaseResp vpnwithdrawRuleGet();

    int updateByPrimaryKeySelective(WithdrawRule record);

    int updateByPrimaryKey(WithdrawRule record);

    BaseResp withdrawRuleget(OssVO vo);

    BaseResp withdrawRuleadd(OssVO vo);

    BaseResp withdrawRuleupdate(OssVO vo);

    BaseResp withdrawRuledel(OssVO vo);

    BaseResp withdrawRulegetById(Integer id);

    void updatefindTimeUp();
}
