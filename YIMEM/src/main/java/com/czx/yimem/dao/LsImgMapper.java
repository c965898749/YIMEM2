package com.czx.yimem.dao;

import com.czx.yimem.entity.LsImg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LsImgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LsImg record);

    int insertSelective(LsImg record);

    LsImg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LsImg record);

    int updateByPrimaryKey(LsImg record);

    Integer selectByurl(String shortUrl);

    List<LsImg> updatefindTimeUp();
}
