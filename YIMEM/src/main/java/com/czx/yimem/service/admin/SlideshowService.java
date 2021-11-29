package com.czx.yimem.service.admin;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.Slideshow;
import com.czx.yimem.entity.responsebean.BaseResp;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface SlideshowService {
    int deleteByPrimaryKey(Integer ssId);

    int insert(Slideshow record);

    int insertSelective(Slideshow record);

    Slideshow selectByPrimaryKey(Integer ssId);

    int updateByPrimaryKeySelective(Slideshow record);

    int updateByPrimaryKey(Slideshow record);

    BaseResp create(OssVO ossVO);

    BaseResp del(OssVO ossVO);

    BaseResp get(OssVO ossVO);
}
