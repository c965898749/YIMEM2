package com.czx.yimem.dao;

import com.czx.yimem.entity.Job;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface JobMapper {
    int deleteByPrimaryKey(Integer jobId);

    int findstate(List ids);

    int insert(Job record);

    int insertSelective(Job record);

    Job selectByPrimaryKey(Integer jobId);

    int selectjobisUse(Integer jobId);

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);

    int updateReadcount(Integer jobId);

    List<Job> selectByJob(Job job);

   String selectphone(Integer jobId);

    List<Job> selectByJob3(Job job);

    List<Job> selectByJob6(Job job);

    Job selectByJob2(Integer devId);

    List<Job> selectByweTOJob(Job job);

    List<Job> selectByzhJiJob(Job job);

    BigDecimal selectbrokerage(Integer jobId);

    Integer updateIsread(Integer jobId);

    Integer updateIsread2(Integer jobId);

    List<Integer> selectJobLightspotIds(Integer jobId);
}
