package com.czx.yimem.dao;

import com.czx.yimem.entity.CompanyBillDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyBillDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyBillDetail record);

    int insertSelective(CompanyBillDetail record);

    CompanyBillDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompanyBillDetail record);

    int updateByPrimaryKey(CompanyBillDetail record);

    List<CompanyBillDetail> findBycompanyBillDetail(CompanyBillDetail record);

    List<CompanyBillDetail> findBycompanyId(Integer  companyId);
}
