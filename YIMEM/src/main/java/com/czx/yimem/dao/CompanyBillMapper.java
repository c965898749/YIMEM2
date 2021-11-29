package com.czx.yimem.dao;

import com.czx.yimem.entity.CompanyBill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyBillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyBill record);

    int insertSelective(CompanyBill record);

    CompanyBill selectByPrimaryKey(Integer id);

    CompanyBill selectByPrimaryKey2(Integer id);

    int updateByPrimaryKeySelective(CompanyBill record);

    int updateByPrimaryKey(CompanyBill record);

    List<CompanyBill> findBycompanyBill(CompanyBill record);
 }
