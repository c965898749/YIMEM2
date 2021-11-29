package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.QsCategoryJobs;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface QsCategoryJobsService {
    int deleteByPrimaryKey(Integer id);

    int insert(QsCategoryJobs record);

    int insertSelective(QsCategoryJobs record);

    QsCategoryJobs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QsCategoryJobs record);

    int updateByPrimaryKeyWithBLOBs(QsCategoryJobs record);

    int updateByPrimaryKey(QsCategoryJobs record);

    /**
     * 查询职业
     * @param ossVO
     * @return
     */
    BaseResp findByqsCategoryJobs(OssVO ossVO);
    /**
     * pc树形查询职业
     * @param
     * @return
     */
    BaseResp findByqsCategoryJobs2();

    BaseResp findByqsCategoryJobs3();

    /**
     * 新增职业
     * @param ossVO
     * @return
     */
    BaseResp addByqsCategoryJobs(OssVO ossVO);

    /**
     * 删除职业
     * @param ossVO
     * @return
     */
    BaseResp delByqsCategoryJobs(OssVO ossVO);

    /**
     * 更新职业
     * @param ossVO
     * @return
     */
    BaseResp updateByqsCategoryJobs(OssVO ossVO);


}
