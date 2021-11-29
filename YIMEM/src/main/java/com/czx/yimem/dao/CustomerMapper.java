package com.czx.yimem.dao;

import com.czx.yimem.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {
    int deleteByPrimaryKey(Integer cusId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer cusId);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    List<Customer> findBycustomer(Customer record);
}
