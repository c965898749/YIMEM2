package com.czx.yimem.dao;

import com.czx.yimem.entity.GroupMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupMember record);

    int insertSelective(GroupMember record);

    GroupMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupMember record);

    int updateByPrimaryKey(GroupMember record);

    void deleteByMember(Integer mid);

    List<GroupMember> selectByMember(Integer mid);

    Integer selectBychild(Integer id);
}
