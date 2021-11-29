package com.czx.yimem.dao;

import com.czx.yimem.entity.Collect;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CollectMapper {
    int deleteByPrimaryKey(Integer id);

    Integer selectByuseridandjobid(Collect record);

    Integer selectBygetCompanyIdandDevId(Collect record);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    List<Collect> findBycollect(Collect record);

    Integer vpniscollectJob(Collect collect);

    Integer vpniscollectdev(Collect collect);
}
