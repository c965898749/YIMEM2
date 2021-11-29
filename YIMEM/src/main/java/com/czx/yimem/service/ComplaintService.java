package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.Complaint;
import com.czx.yimem.entity.responsebean.BaseResp;

public interface ComplaintService {
    int deleteByPrimaryKey(Integer comId);

    int insert(Complaint record);

    int insertSelective(Complaint record);

    Complaint selectByPrimaryKey(Integer comId);

    int updateByPrimaryKeySelective(Complaint record);

    int updateByPrimaryKey(Complaint record);

    BaseResp complaintService(OssVO vo);

    BaseResp vpncomplaintGetsvcid(Integer id);

    BaseResp vpncomplaintAddsvc(OssVO vo);

    BaseResp vpncomplaintDelsvc(OssVO vo);
}
