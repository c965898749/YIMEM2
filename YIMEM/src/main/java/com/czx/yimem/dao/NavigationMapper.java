package com.czx.yimem.dao;

import com.czx.yimem.entity.Navigation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NavigationMapper {
    int deleteByPrimaryKey(Integer naId);

    int insert(Navigation record);

    int insertSelective(Navigation record);

    int selectcount();

    Navigation selectByPrimaryKey(Integer naId);

    int updateByPrimaryKeySelective(Navigation record);

    Integer updateotherstate(Integer naId);

    int updateByPrimaryKey(Navigation record);

    List<Navigation> findBynavigation(Navigation record);
}
