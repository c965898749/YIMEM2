package com.czx.yimem.dao;

import com.czx.yimem.entity.Jobhunter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JobhunterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Jobhunter record);

    int insertSelective(Jobhunter record);

    Jobhunter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Jobhunter record);

    int updateByPrimaryKey(Jobhunter record);

    int updateByUserId(Jobhunter record);

    List<Jobhunter> findByjobhunter(Jobhunter record);
}
