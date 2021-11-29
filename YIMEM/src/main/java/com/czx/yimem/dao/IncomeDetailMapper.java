package com.czx.yimem.dao;

import com.czx.yimem.dto.*;
import com.czx.yimem.entity.IncomeDetail;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface IncomeDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IncomeDetail record);

    int insertSelective(IncomeDetail record);

    IncomeDetail selectByPrimaryKey(Integer id);

    IncomeDetail selectBydelid(Integer id);

    int updateByPrimaryKeySelective(IncomeDetail record);

    int updateByPrimaryKey(IncomeDetail record);

    List<IncomeDetail> findByIncomeDetail(IncomeDetail record);

    List<IncomeDetailVOs> getnewincomeDetails();

    List<IncomeDetail> findByIncomeDetail2(Integer userId);

    BigDecimal selectSumincomeDetail(Integer userId);

    List<IncomeDetailVO> findByTime(IncomeDetailVO incomeDetailVO);

    List<IncomeDetail> selectincomeDetailsBytime(IncomeDetailVO incomeDetailVO);

    List<IncomeDetail> findIncomeDetail(Integer day);
    List<IncomeDetail> findIncomeDetail2(Integer day);

    List<BrokerageRankingList> findbrokerageRankingList(BrokerageRankingList brokerageRankingList);

    List<InterviewRankingList>  findInterviewRankingList(InterviewRankingList brokerageRankingList);

    List<RecommendRankingList>  findRecommendRankingList(RecommendRankingList brokerageRankingList);

    BrokerageRankingList findbrokerageRankingListbyuserId(BrokerageRankingList brokerageRankingList);

    InterviewRankingList findInterviewRankingListbyuserId(InterviewRankingList brokerageRankingList);

    RecommendRankingList findRecommendRankingListbyuserId(RecommendRankingList brokerageRankingList);
}
