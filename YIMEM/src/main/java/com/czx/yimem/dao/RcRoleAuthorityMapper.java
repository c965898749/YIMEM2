package com.czx.yimem.dao;

import com.czx.yimem.entity.RcRoleAuthority;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RcRoleAuthorityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RcRoleAuthority record);

    int insertSelective(RcRoleAuthority record);

    RcRoleAuthority selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RcRoleAuthority record);

    int updateByPrimaryKey(RcRoleAuthority record);

    List<RcRoleAuthority> selectByRoleId(Integer roleId);
}
