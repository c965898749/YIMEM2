package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.Certificate;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface CertificateService {
    int deleteByPrimaryKey(Integer id);

    int insert(Certificate record);

    int insertSelective(Certificate record);

    Certificate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Certificate record);

    int updateByPrimaryKey(Certificate record);

    /**
     * 条件查询学历
     * @param ossVO
     * @return
     */
    BaseResp findBycertificate(OssVO ossVO);

    BaseResp addBycertificate(OssVO ossVO);

    BaseResp updateBycertificate(OssVO ossVO);

    BaseResp delBycertificate(OssVO ossVO);
}
