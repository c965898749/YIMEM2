package com.czx.yimem.dao;

import com.czx.yimem.entity.Beginner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BeginnerMapper {
    int deleteByPrimaryKey(Integer beId);

    int insert(Beginner record);

    int insertSelective(Beginner record);

    Beginner selectByPrimaryKey(Integer beId);

    Beginner selectBytouser(String touser);

    int updateByPrimaryKeySelective(Beginner record);

    int updateByPrimaryKey(Beginner record);

    int updateState(Beginner record);

    List<Beginner> findBybeginner(Beginner record);

    Integer updatefindTimeUp(Integer id);

    List<Integer> findTimeUpid();

}
