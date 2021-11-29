package com.czx.yimem.service;

import com.czx.yimem.entity.JobCustomLightspot;
import com.czx.yimem.entity.JobCustomLightenEnum;
import java.util.List;

public interface JobCustomLightspotService {
    int deleteByPrimaryKey(Integer id);

    int insert(JobCustomLightspot record);

    int insertSelective(JobCustomLightspot record);

    JobCustomLightspot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobCustomLightspot record);

    int updateByPrimaryKey(JobCustomLightspot record);


}
