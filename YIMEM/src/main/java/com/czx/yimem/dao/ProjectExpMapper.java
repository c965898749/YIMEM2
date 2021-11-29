package com.czx.yimem.dao;

import com.czx.yimem.entity.ProjectExp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectExpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectExp record);

    int insertSelective(ProjectExp record);

    ProjectExp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectExp record);

    int updateByPrimaryKey(ProjectExp record);

    List<ProjectExp> findByprojectExp(ProjectExp record);
}
