package com.czx.yimem.service.financial;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.IncomeDetail;
import com.czx.yimem.entity.responsebean.BaseResp;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 22:14
 */
public interface IncomeDetailService {

    List<IncomeDetail> findByIncomeDetail(IncomeDetail record);

    List<IncomeDetail> findByIncomeDetail2(Integer userId);

    List<IncomeDetail> findIncomeDetail(Integer day);
    List<IncomeDetail> findIncomeDetail2(Integer day);

    BigDecimal selectSumincomeDetail(Integer userId);

    int deleteByPrimaryKey(Integer id);

    int insert(IncomeDetail record);

    int insertSelective(IncomeDetail record);

    IncomeDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IncomeDetail record);

    int updateByPrimaryKey(IncomeDetail record);

    BaseResp getnewincomeDetails();

    BaseResp vpnincomeDetailGetsvc(OssVO vo);

    BaseResp vpnincomeDetailGetsvc2(OssVO vo);

    BaseResp vpnbrokerageRankingListsvc(OssVO vo);

    BaseResp vpnInterviewRankingListsvc(OssVO vo);

    BaseResp vpnRecommendRankingListsvc(OssVO vo);
}
