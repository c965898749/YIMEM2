package com.czx.yimem.dao;

import com.czx.yimem.dto.BrokerageRankingList;
import com.czx.yimem.dto.InterviewRankingList;
import com.czx.yimem.entity.Income;
import com.czx.yimem.entity.IncomeDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IncomeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Income record);

    int insertSelective(Income record);

    Income selectByPrimaryKey(Integer id);

    Income selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(Income record);

    int updateByPrimaryKey(Income record);

    List<Income> findByincome(Income record);

    List<BrokerageRankingList> findbrokerageRankingList(Integer num);


}
