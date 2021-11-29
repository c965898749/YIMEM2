package com.czx.yimem.dao;

import com.czx.yimem.entity.LogRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogRecord record);

    int insertSelective(LogRecord record);

    LogRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogRecord record);

    int updateByPrimaryKeyWithBLOBs(LogRecord record);

    int updateByPrimaryKey(LogRecord record);
}
