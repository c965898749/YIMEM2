package com.czx.yimem.service.impl;

import com.czx.yimem.dao.JobCustomLightenEnumMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.JobCustomLightenEnum;
import com.czx.yimem.entity.JobLightenEnum;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.JobCustomLightenEnumService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Service
public class JobCustomLightenEnumServiceImpl implements JobCustomLightenEnumService {
    @Resource
    private JobCustomLightenEnumMapper jobCustomLightenEnumMapper;

    @Override
    public int deleteByPrimaryKey(Integer jobLightenId) {
        return 0;
    }

    @Override
    public int insert(JobCustomLightenEnum record) {
        return 0;
    }

    @Override
    public int insertSelective(JobCustomLightenEnum record) {
        return 0;
    }

    @Override
    public JobCustomLightenEnum selectByPrimaryKey(Integer jobLightenId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(JobCustomLightenEnum record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(JobCustomLightenEnum record) {
        return 0;
    }

    @Override
    public BaseResp selectByJobCustomLighten(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            JobCustomLightenEnum job = JSON.toJavaObject(vo.getVpnjobCustomLightenEnumGet(), JobCustomLightenEnum.class);
            List<JobCustomLightenEnum> list = jobCustomLightenEnumMapper.selectByJobCustomLighten(job);
            Page<JobCustomLightenEnum> jobPage = (Page<JobCustomLightenEnum>) list;
            Map map = new HashMap();
            map.put("vpnjobCustomLightenEnumGetResp", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public List<JobCustomLightenEnum> findJobCustomLightenEnumbyjobId(Integer jobId) {
        return jobCustomLightenEnumMapper.findJobCustomLightenEnumbyjobId(jobId);
    }

    @Override
    public Integer saveJobCustomLightenEnumbyjobId(List<JobCustomLightenEnum> jobCustomLightenEnums) {
        if (Xtool.isNotNull(jobCustomLightenEnums)) {
            jobCustomLightenEnums.forEach(x -> {
                jobCustomLightenEnumMapper.insertSelective(x);
            });
        }
        return Constants.SUCCEE_CODE;
    }

    @Override
    public Integer delByjobId(Integer jobId) {
        if (Xtool.isNull(jobId)) {
            return Constants.FAIL_CODE;
        }
        return jobCustomLightenEnumMapper.delByjobId(jobId);
    }
}
