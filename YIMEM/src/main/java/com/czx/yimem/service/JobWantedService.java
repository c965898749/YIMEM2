package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.JobWanted;
import com.czx.yimem.entity.responsebean.BaseResp;

import java.util.List;

public interface JobWantedService {
    int deleteByPrimaryKey(Integer id);

    int insert(com.czx.yimem.entity.JobWanted record);

    int insertSelective(com.czx.yimem.entity.JobWanted record);

    com.czx.yimem.entity.JobWanted selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(com.czx.yimem.entity.JobWanted record);

    int updateByPrimaryKey(com.czx.yimem.entity.JobWanted record);

    /**
     * 条件查询求职意向
     * @param ossVO
     * @return
     */
    BaseResp findByjobWanted(OssVO ossVO);

    /**
     * 新增求职意向
     * @param ossVO
     * @return
     */
    BaseResp addByjobWanted(OssVO ossVO);

    /**
     * 修改求职意向
     * @param ossVO
     * @return
     */
    BaseResp updateByjobWanted(OssVO ossVO);

    /**
     * 删除求职意向
     * @param ossVO
     * @return
     */
    BaseResp delByjobWanted(OssVO ossVO);


}
