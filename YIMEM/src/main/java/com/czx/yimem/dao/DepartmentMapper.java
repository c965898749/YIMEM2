package com.czx.yimem.dao;

import com.czx.yimem.entity.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer depId);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer depId);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> selectAllByState(String state);

    List<Department> selectByMember(Integer mid);

    List<Integer> getAllBYid(Integer id);

    Integer selectchild(Integer id);

    Department selectByfdep(Integer id);
}
