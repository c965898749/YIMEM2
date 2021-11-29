package com.czx.yimem.dao;

import com.czx.yimem.entity.Slideshow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SlideshowMapper {
    int deleteByPrimaryKey(Integer ssId);

    int insert(Slideshow record);

    int insertSelective(Slideshow record);

    Slideshow selectByPrimaryKey(Integer ssId);

    int updateByPrimaryKeySelective(Slideshow record);

    int updateByPrimaryKey(Slideshow record);

    List<Slideshow> findbytoUser(Slideshow slideshow);
}
