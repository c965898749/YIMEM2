package com.czx.yimem.dao;

import com.czx.yimem.entity.EducationExp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EducationExpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EducationExp record);

    int insertSelective(EducationExp record);

    EducationExp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EducationExp record);

    int updateByPrimaryKey(EducationExp record);

    int findByeducationExpCount(Integer userId);

    List<EducationExp> findByeducationExp(EducationExp record);
}
