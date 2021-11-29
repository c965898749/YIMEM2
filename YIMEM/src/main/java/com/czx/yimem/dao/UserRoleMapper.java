package com.czx.yimem.dao;

import com.czx.yimem.entity.UserRole;
import com.czx.yimem.entity.UserRoleKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper {
    int deleteByPrimaryKey(UserRoleKey key);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(UserRoleKey key);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    void deleteByMember(Integer mid);
}