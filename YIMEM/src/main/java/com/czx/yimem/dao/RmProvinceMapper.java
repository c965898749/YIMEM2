package com.czx.yimem.dao;

import com.czx.yimem.entity.RmProvince;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RmProvinceMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(RmProvince record);

    int insertSelective(RmProvince record);

    RmProvince selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(RmProvince record);

    int updateByPrimaryKey(RmProvince record);

    List<RmProvince> findByProvince(RmProvince record);
}
