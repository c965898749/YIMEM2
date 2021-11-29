package com.czx.yimem.service.impl;

import com.czx.yimem.dao.JobLightspotMapper;
import com.czx.yimem.entity.JobLightenEnum;
import com.czx.yimem.entity.JobLightspot;
import com.czx.yimem.service.JobLightspotService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.Xtool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Service
public class JobLightspotServiceImpl implements JobLightspotService {

    @Resource
    private JobLightspotMapper jobLightspotMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(JobLightspot record) {
        return 0;
    }

    @Override
    public int insertSelective(JobLightspot record) {
        return 0;
    }

    @Override
    public JobLightspot selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(JobLightspot record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(JobLightspot record) {
        return 0;
    }

    @Override
    public int saveJobLightspots(List<JobLightspot> jobLightspots) {
        if (Xtool.isNotNull(jobLightspots)){
            jobLightspots.forEach(x->{
                jobLightspotMapper.insertSelective(x);
            });
        }
        return Constants.SUCCEE_CODE;
    }

    @Override
    public int delByJob(Integer job) {
        if (Xtool.isNull(job)){
            return Constants.FAIL_CODE;
        }
        return jobLightspotMapper.delByJob(job);
    }


}
