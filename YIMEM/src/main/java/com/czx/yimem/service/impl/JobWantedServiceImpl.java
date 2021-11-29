package com.czx.yimem.service.impl;

import com.czx.yimem.dao.JobWantedMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.*;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.JobWantedService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.czx.yimem.utils.TokenUtil;
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
public class JobWantedServiceImpl implements JobWantedService {
    @Resource
    private JobWantedMapper jobWantedMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(com.czx.yimem.entity.JobWanted record) {
        return 0;
    }

    @Override
    public int insertSelective(com.czx.yimem.entity.JobWanted record) {
        return 0;
    }

    @Override
    public com.czx.yimem.entity.JobWanted selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(com.czx.yimem.entity.JobWanted record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(com.czx.yimem.entity.JobWanted record) {
        return 0;
    }

    @Override
    public BaseResp findByjobWanted(OssVO vo) {
        try {
            JobWanted jobWanted = JSON.toJavaObject(vo.getVpnjobWantedGet(), JobWanted.class);
            if (jobWanted==null||Xtool.isNull(jobWanted.getUserId())||TokenUtil.getTokenUserId().equals(jobWanted.getUserId())){
                return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
            }
            List<JobWanted> list = jobWantedMapper.findByjobWanted(jobWanted);
            Map map = new HashMap();
            map.put("vpnjobWantedGetResp", list);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp addByjobWanted(OssVO vo) {
        try {
            JobWanted jobWanted = JSON.toJavaObject(vo.getVpnjobWantedAdd(), JobWanted.class);
            Integer count = 0;
            if (Xtool.isNotNull(jobWanted.getUserId())) {
                if (jobWanted==null||Xtool.isNull(jobWanted.getUserId())||!TokenUtil.getTokenUserId().equals(jobWanted.getUserId()+"")){
                    return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
                }
                if (jobWantedMapper.findBywantedCount(jobWanted.getUserId())>0) {
                    count = jobWantedMapper.updateByuserId(jobWanted);
                } else {
                    count = jobWantedMapper.insertSelective(jobWanted);
                }
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp updateByjobWanted(OssVO vo) {
        try {
            JobWanted jobWanted = JSON.toJavaObject(vo.getVpnjobWantedUpdate(), JobWanted.class);
            Integer count = 0;
            if (Xtool.isNotNull(jobWanted.getUserId())) {
                if (jobWanted==null||Xtool.isNull(jobWanted.getUserId())||TokenUtil.getTokenUserId().equals(jobWanted.getUserId())){
                    return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
                }
                count = jobWantedMapper.updateByuserId(jobWanted);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp delByjobWanted(OssVO vo) {
        try {
            JobWanted jobWanted = JSON.toJavaObject(vo.getVpnjobWantedDel(), JobWanted.class);
            Integer count = 0;
            if (Xtool.isNotNull(jobWanted.getUserId())) {
                if (jobWanted==null||Xtool.isNull(jobWanted.getUserId())||TokenUtil.getTokenUserId().equals(jobWanted.getUserId())){
                    return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
                }
                count = jobWantedMapper.delByuserId(jobWanted);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }


}
