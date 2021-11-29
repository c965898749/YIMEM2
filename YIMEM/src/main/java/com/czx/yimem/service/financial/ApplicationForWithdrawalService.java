package com.czx.yimem.service.financial;

import com.czx.yimem.dto.ApplicationVO;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.ApplicationForWithdrawal;
import com.czx.yimem.entity.responsebean.BaseResp;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/13 0013 17:19
 */
public interface ApplicationForWithdrawalService {

    Integer enMoneyCount(Integer userId);

    List<ApplicationForWithdrawal> findByapplication(ApplicationForWithdrawal record);

    List<ApplicationVO> findByTime(ApplicationVO applicationVO);

    List<ApplicationForWithdrawal> selectapplicationForWithdrawalsBytime(ApplicationVO applicationVO);

    BigDecimal sumEnMoney(Integer userId);

    String getstate(Integer id);


    int deleteByPrimaryKey(Integer id);

    int insert(ApplicationForWithdrawal record);

    int insertSelective(ApplicationForWithdrawal record);

    ApplicationForWithdrawal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApplicationForWithdrawal record);

    int updateByPrimaryKey(ApplicationForWithdrawal record);

    BaseResp vpnApplicationForWithdrawalGetsvc(OssVO ov);

    BaseResp vpnApplicationForWithdrawalGetsvc2(OssVO ov);

    BaseResp vpnApplicationForWithdrawalUpdatesvc(OssVO ov);

    BaseResp vpnApplicationForWithdrawalAddsvc(OssVO ov);
}
