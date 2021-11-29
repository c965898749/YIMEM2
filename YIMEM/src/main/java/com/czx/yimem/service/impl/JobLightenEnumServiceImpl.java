package com.czx.yimem.service.impl;

import com.czx.yimem.dao.JobLightenEnumMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.Job;
import com.czx.yimem.entity.JobExp;
import com.czx.yimem.entity.JobLightenEnum;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.JobLightenEnumService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Service
public class JobLightenEnumServiceImpl implements JobLightenEnumService {
    @Resource
    private JobLightenEnumMapper jobLightenEnumMapper;

    @Override
    public int deleteByPrimaryKey(Integer jobLightenId) {
        return 0;
    }

    @Override
    public int insert(JobLightenEnum record) {
        return 0;
    }

    @Override
    public int insertSelective(JobLightenEnum record) {
        return 0;
    }

    @Override
    public JobLightenEnum selectByPrimaryKey(Integer jobLightenId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(JobLightenEnum record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(JobLightenEnum record) {
        return 0;
    }

    @Override
    public BaseResp selectByJobLighten(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            JobLightenEnum job = JSON.toJavaObject(vo.getVpnjobLightenEnumGet(), JobLightenEnum.class);
            List<JobLightenEnum> list = jobLightenEnumMapper.selectByJobLighten(job);
            Page<JobLightenEnum> jobPage = (Page<JobLightenEnum>) list;
            Map map = new HashMap();
            map.put("vpnjobLightenEnumGetResp", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp selectByJobLighten2() {
        try {
            List<JobLightenEnum> list = jobLightenEnumMapper.selectByJobLighten(new JobLightenEnum());
            Map map = new HashMap();
            map.put("vpnjobLightenEnumGetResp", list);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp vpnjobLightenEnumAddsvc(OssVO vo) {
        try {
            JobLightenEnum jobLightenEnum = JSON.toJavaObject(vo.getVpnjobLightenEnumAdd(), JobLightenEnum.class);
            Integer count = 0;
            if (Xtool.isNotNull(jobLightenEnum.getName())) {
                count = jobLightenEnumMapper.insertSelective(jobLightenEnum);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp vpnjobLightenEnumUpdatesvc(OssVO vo) {
        try {
            JobLightenEnum jobLightenEnum = JSON.toJavaObject(vo.getVpnjobLightenEnumUpdate(), JobLightenEnum.class);
            Integer count = 0;
            if (Xtool.isNotNull(jobLightenEnum.getJobLightenId())) {
                count = jobLightenEnumMapper.updateByPrimaryKeySelective(jobLightenEnum);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp vpnjobLightenEnumDelsvc(OssVO vo) {
        try {
            JobLightenEnum jobLightenEnum = JSON.toJavaObject(vo.getVpnjobLightenEnumDel(), JobLightenEnum.class);
            Integer count = 0;
            if (Xtool.isNotNull(jobLightenEnum.getJobLightenId())) {
                count = jobLightenEnumMapper.deleteByPrimaryKey(jobLightenEnum.getJobLightenId());
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public List<JobLightenEnum> findJobLightenEnumbyjobId(Integer jobId) {
        return jobLightenEnumMapper.findJobLightenEnumbyjobId(jobId);
    }
}
