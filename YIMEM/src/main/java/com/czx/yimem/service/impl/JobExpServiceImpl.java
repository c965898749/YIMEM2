package com.czx.yimem.service.impl;

import com.czx.yimem.dao.JobExpMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.EducationExp;
import com.czx.yimem.entity.JobExp;
import com.czx.yimem.entity.JobWanted;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.JobExpService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.TokenUtil;
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
public class JobExpServiceImpl implements JobExpService {
    @Resource
    private JobExpMapper jobExpMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(JobExp record) {
        return 0;
    }

    @Override
    public int insertSelective(JobExp record) {
        return 0;
    }

    @Override
    public JobExp selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(JobExp record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(JobExp record) {
        return 0;
    }

    @Override
    public BaseResp findByjobExp(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            JobExp jobExp = JSON.toJavaObject(vo.getVpnjobExpGet(), JobExp.class);
            if (jobExp==null||Xtool.isNull(jobExp.getUserId())|| TokenUtil.getTokenUserId().equals(jobExp.getUserId())){
                return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
            }
            List<JobExp> list = jobExpMapper.findByjobExp(jobExp);
            Page<JobExp> jobPage = (Page<JobExp>) list;
            Map map = new HashMap();
            map.put("vpnjobExpGetResp", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp addByjobExp(OssVO vo) {
        try {
            JobExp jobExp = JSON.toJavaObject(vo.getVpnjobExpAdd(), JobExp.class);
            Integer count = 0;
            if (Xtool.isNotNull(jobExp.getUserId())) {
                if (jobExp==null||Xtool.isNull(jobExp.getUserId())|| TokenUtil.getTokenUserId().equals(jobExp.getUserId())){
                    return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
                }
                count = jobExpMapper.insertSelective(jobExp);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp updateByjobExp(OssVO vo) {
        try {
            JobExp jobExp = JSON.toJavaObject(vo.getVpnjobExpUpdate(), JobExp.class);
            Integer count = 0;
            if (Xtool.isNotNull(jobExp.getId())) {
                if (jobExp==null||Xtool.isNull(jobExp.getUserId())|| TokenUtil.getTokenUserId().equals(jobExp.getUserId())){
                    return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
                }
                count = jobExpMapper.updateByPrimaryKeySelective(jobExp);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp delByjobExp(OssVO vo) {
        try {
            JobExp jobExp = JSON.toJavaObject(vo.getVpnjobExpDel(), JobExp.class);
            Integer count = 0;
            if (Xtool.isNotNull(jobExp.getId())) {
                if (jobExp==null||Xtool.isNull(jobExp.getUserId())|| TokenUtil.getTokenUserId().equals(jobExp.getUserId())){
                    return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
                }
                count = jobExpMapper.deleteByPrimaryKey(jobExp.getId());
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
