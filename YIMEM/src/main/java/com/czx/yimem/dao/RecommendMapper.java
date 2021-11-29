package com.czx.yimem.dao;

import com.czx.yimem.dto.RecommendVO;
import com.czx.yimem.entity.IncomeDetail;
import com.czx.yimem.entity.Recommend;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecommendMapper {
    int deleteByPrimaryKey(Integer reId);

    int insert(Recommend record);

    int insertSelective(Recommend record);

    Recommend selectByPrimaryKey(Integer reId);

    int updateByPrimaryKeySelective(Recommend record);

    int updateByPrimaryKey(Recommend record);

    int updateiscount(IncomeDetail incomeDetail);

    Integer selectrecommend(Recommend record);

    Recommend selectByDelId(Recommend record);

    List<RecommendVO> findByrecommend(RecommendVO recommend);
}
