package com.czx.yimem.service.admin;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.TouserVO;
import com.czx.yimem.entity.Beginner;
import com.czx.yimem.entity.responsebean.BaseResp;

import java.util.List;

public interface BeginnerService {
    int deleteByPrimaryKey(Integer beId);

    int insert(Beginner record);

    int insertSelective(Beginner record);

    Beginner selectByPrimaryKey(Integer beId);

    int updateByPrimaryKeySelective(Beginner record);

    int updateByPrimaryKey(Beginner record);

    BaseResp beginnerGet(OssVO vo);

    BaseResp vpnbeginnerGet(TouserVO touser);

    BaseResp beginnerGetbyid(Integer id);

    BaseResp beginnerAdd(OssVO vo);

    BaseResp beginnerupdate(OssVO vo);

    void updatefindTimeUp();

    BaseResp beginnerdel(OssVO vo);
}
