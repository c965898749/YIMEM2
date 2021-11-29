package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.QsCategory;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface QsCategoryService {
    int deleteByPrimaryKey(Integer cId);

    int insert(QsCategory record);

    int insertSelective(QsCategory record);

    QsCategory selectByPrimaryKey(Integer cId);

    int updateByPrimaryKeySelective(QsCategory record);

    int updateByPrimaryKey(QsCategory record);

    /**
     * 分类查询
     * @param ossVO
     * @return
     */
    BaseResp findByqsCategory(OssVO ossVO);

    /**
     * 分类更新
     * @param ossVO
     * @return
     */
    BaseResp updateByqsCategory(OssVO ossVO);

    /**
     * 分类新增
     * @param ossVO
     * @return
     */
    BaseResp addByqsCategory(OssVO ossVO);

    /**
     * 分类删除
     * @param ossVO
     * @return
     */
    BaseResp delByqsCategory(OssVO ossVO);
}
