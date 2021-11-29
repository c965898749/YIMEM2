package com.czx.yimem.service.impl;

import com.czx.yimem.dao.CollectMapper;
import com.czx.yimem.dao.CollectRecMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.*;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.CollectService;
import com.czx.yimem.service.JobService;
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
public class CollectServiceImpl implements CollectService {
    @Resource
    private CollectMapper collectMapper;
    @Resource
    private JobService jobService;
    @Resource
    private CollectRecMapper collectRecMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Collect record) {
        return 0;
    }

    @Override
    public int insertSelective(Collect record) {
        return 0;
    }

    @Override
    public Collect selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Collect record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Collect record) {
        return 0;
    }

    @Override
    public BaseResp findBycollect(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            Collect collect = JSON.toJavaObject(vo.getVpncollectGet(), Collect.class);
            if (collect == null || Xtool.isNull(collect.getUserId()) || TokenUtil.getTokenUserId().equals(collect.getUserId())) {
                return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
            }
            List<Collect> list = collectMapper.findBycollect(collect);
            if (Xtool.isNotNull(list)) {
                list.forEach(x -> {
                    Job job = jobService.selectById(x.getJobId());
                    if (job != null) {
                        job.setPhone(jobService.selectphone(x.getJobId()));
                    }
                    x.setJob(job);
                });
            }
            Page<Collect> jobPage = (Page<Collect>) list;
            Map map = new HashMap();
            map.put("vpncollectGetResp", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp vpniscollectJob(Collect collect) {
        if (collect != null && Xtool.isNotNull(collect.getUserId()) && Xtool.isNotNull(collect.getJobId())) {
            Integer vpniscollectJob = collectMapper.vpniscollectJob(collect);
            Map map = new HashMap();
            if (vpniscollectJob > 0) {
                map.put("iscollect", 1);
            } else {
                map.put("iscollect", 0);
            }
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        }
        return new BaseResp(Constants.FAIL_CODE, "参数" + Constants.NO_STIPULATE);
    }

    @Override
    public BaseResp addBycollect(OssVO vo) {
        try {
            Collect collect = JSON.toJavaObject(vo.getVpncollectAdd(), Collect.class);
            Integer count = 0;
            if (Xtool.isNotNull(collect.getUserId())) {
                if (collect == null || Xtool.isNull(collect.getUserId()) || TokenUtil.getTokenUserId().equals(collect.getUserId())) {
                    return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
                }
                if (Xtool.isNull(collect.getJobId())) {
                    return new BaseResp(Constants.FAIL_CODE, "请选择岗位收藏");
                }
                Integer id = collectMapper.selectByuseridandjobid(collect);
                if (Xtool.isNotNull(id)) {
                    count = collectMapper.deleteByPrimaryKey(id);
                } else {
                    count = collectMapper.insertSelective(collect);
                }
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp addBycollect2(OssVO vo) {
        try {
            Collect collect = JSON.toJavaObject(vo.getVpncollectAdd(), Collect.class);
            Integer count = 0;
            if (collect == null || Xtool.isNull(collect.getCompanyId()) || Xtool.isNull(collect.getDevUserId())) {
                return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
            }
            Integer id = collectMapper.selectBygetCompanyIdandDevId(collect);
            if (Xtool.isNotNull(id)) {
                count = collectMapper.deleteByPrimaryKey(id);
            } else {
                count = collectMapper.insertSelective(collect);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp vpncompanycollectdev(Collect collect) {

        if (collect != null && Xtool.isNotNull(collect.getCompanyId()) && Xtool.isNotNull(collect.getDevUserId())) {
            Integer vpniscollectJob = collectMapper.vpniscollectdev(collect);
            Map map = new HashMap();
            if (vpniscollectJob > 0) {
                map.put("iscollect", 1);
            } else {
                map.put("iscollect", 0);
            }
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        }
        return new BaseResp(Constants.FAIL_CODE, "参数" + Constants.NO_STIPULATE);
    }

    @Override
    public BaseResp updateBycollect(OssVO vo) {
        try {
            Collect collect = JSON.toJavaObject(vo.getVpncollectUpdate(), Collect.class);
            Integer count = 0;
            if (Xtool.isNotNull(collect.getId()) && Xtool.isNotNull(collect.getUserId())) {
                if (collect == null || Xtool.isNull(collect.getUserId()) || TokenUtil.getTokenUserId().equals(collect.getUserId())) {
                    return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
                }
                count = collectMapper.updateByPrimaryKeySelective(collect);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp delBycollect(OssVO vo) {
        try {
            Collect collect = JSON.toJavaObject(vo.getVpncollectDel(), Collect.class);
            Integer count = 0;
            if (Xtool.isNotNull(collect.getId())) {
                if (collect == null || Xtool.isNull(collect.getUserId()) || TokenUtil.getTokenUserId().equals(collect.getUserId())) {
                    return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
                }
                count = collectMapper.deleteByPrimaryKey(collect.getId());
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp insertRecSelective(CollectRec record) {
        try {

            collectRecMapper.insertSelective(record);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp delBycollectrec(CollectRec record) {
        try {

            collectRecMapper.deleteByPrimaryKey(record.getId());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
