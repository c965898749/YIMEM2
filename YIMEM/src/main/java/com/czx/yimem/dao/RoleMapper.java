package com.czx.yimem.dao;

import com.czx.yimem.dto.RoleDTO;
import com.czx.yimem.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectByMemberId(Integer mid);

    List<Role> search(RoleDTO record);

    Integer deleteRoleUser(Integer roleId);
}
