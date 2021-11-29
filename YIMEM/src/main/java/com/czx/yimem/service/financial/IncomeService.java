package com.czx.yimem.service.financial;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.Income;
import com.czx.yimem.entity.responsebean.BaseResp;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/13 0013 16:36
 */
public interface IncomeService {
    int deleteByPrimaryKey(Integer id);

    int insert(Income record);

    int insertSelective(Income record);

    Income selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Income record);

    int updateByPrimaryKey(Income record);

    BaseResp vpnIncomeGetsvc(OssVO ossVO);

    void  updatefindTimeUp();
    void  updatefindTimeUp2();

}
