package com.czx.yimem.dao;

import com.czx.yimem.entity.Language;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LanguageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Language record);

    int insertSelective(Language record);

    Language selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Language record);

    int updateByPrimaryKey(Language record);

    List<Language> findBylanguage(Language record);
}
