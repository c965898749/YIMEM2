package com.czx.yimem.service.admin;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.Navigation;
import com.czx.yimem.entity.responsebean.BaseResp;

import javax.servlet.http.HttpServletRequest;

public interface NavigationService {
    int deleteByPrimaryKey(Integer naId);

    int insert(Navigation record);

    int insertSelective(Navigation record);

    Navigation selectByPrimaryKey(Integer naId);

    int updateByPrimaryKeySelective(Navigation record);

    int updateByPrimaryKey(Navigation record);

    BaseResp getNavigation(OssVO ossVO);

    BaseResp getNavigation2(OssVO ossVO);

    BaseResp delNavigation(OssVO ossVO);

    BaseResp addNavigation(OssVO ossVO);

    BaseResp updateNavigation(OssVO ossVO);
}
