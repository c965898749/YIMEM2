package com.czx.yimem.service.impl;

import com.czx.yimem.dao.LogRecordMapper;
import com.czx.yimem.entity.LogRecord;
import com.czx.yimem.service.LogRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Service
public class LogRecordServiceImpl implements LogRecordService {
    @Resource
    private LogRecordMapper logRecordMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(LogRecord record) {
        return 0;
    }

    @Override
    public int insertSelective(LogRecord record) {
        return logRecordMapper.insertSelective(record);
    }

    @Override
    public LogRecord selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(LogRecord record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(LogRecord record) {
        return 0;
    }
}
