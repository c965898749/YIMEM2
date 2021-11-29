package com.czx.yimem.dao;

import com.czx.yimem.entity.Distribution;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DistributionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Distribution record);

    int insertSelective(Distribution record);

    Distribution selectByPrimaryKey(Integer id);
    Distribution selectByuserid(Integer id);

    int updateByPrimaryKeySelective(Distribution record);

    int updateByPrimaryKey(Distribution record);

    List<Distribution> findBydistribution(Distribution record);

    Integer IsExist(Integer userId);
    Integer isdistribution(Integer userId);
}
