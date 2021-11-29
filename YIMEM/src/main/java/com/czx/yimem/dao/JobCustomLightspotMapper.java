package com.czx.yimem.dao;

import com.czx.yimem.entity.JobCustomLightspot;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JobCustomLightspotMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobCustomLightspot record);

    int insertSelective(JobCustomLightspot record);

    JobCustomLightspot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobCustomLightspot record);

    int updateByPrimaryKey(JobCustomLightspot record);
}
