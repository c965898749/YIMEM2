package com.czx.yimem.dao;

import com.czx.yimem.dto.IdentityVO;
import com.czx.yimem.entity.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    News selectByPrimaryKey2(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    int updatesiread(News record);

    int updatesiagree(News record);

    Integer isnewmsg(IdentityVO identityVO);

    List<News> findBynews(News record);
}
