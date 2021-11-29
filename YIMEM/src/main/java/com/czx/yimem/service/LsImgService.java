package com.czx.yimem.service;

import com.czx.yimem.entity.LsImg;

public interface LsImgService {
    int deleteByPrimaryKey(Integer id);

    int insert(LsImg record);

    int insertSelective(LsImg record);

    LsImg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LsImg record);

    int updateByPrimaryKey(LsImg record);

    void updatefindTimeUp();
}
