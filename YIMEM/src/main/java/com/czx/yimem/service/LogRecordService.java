package com.czx.yimem.service;

import com.czx.yimem.entity.LogRecord;

public interface LogRecordService {
    int deleteByPrimaryKey(Integer id);

    int insert(LogRecord record);

    int insertSelective(LogRecord record);

    LogRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogRecord record);

    int updateByPrimaryKey(LogRecord record);
}
