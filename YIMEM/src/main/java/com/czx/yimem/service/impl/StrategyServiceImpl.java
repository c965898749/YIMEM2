package com.czx.yimem.service.impl;

import com.czx.yimem.dao.StrategyMapper;
import com.czx.yimem.dto.JobStateVO;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.dto.StrategyStateVO;
import com.czx.yimem.entity.Complaint;
import com.czx.yimem.entity.EducationExp;
import com.czx.yimem.entity.Job;
import com.czx.yimem.entity.Strategy;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.StrategyService;
import com.czx.yimem.utils.*;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Slf4j
@Service
public class StrategyServiceImpl implements StrategyService {

    @Resource
    private StrategyMapper strategyMapper;
    @Autowired
    private RedisAPI redisAPI;
    @Override
    public int deleteByPrimaryKey(Integer stId) {
        return 0;
    }

    @Override
    public int insert(Strategy record) {
        return 0;
    }

    @Override
    public int insertSelective(Strategy record) {
        return 0;
    }

    @Override
    public Strategy selectByPrimaryKey(Integer stId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Strategy record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Strategy record) {
        return 0;
    }

    @Override
    public BaseResp vpnstrategyGetsvc(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            Strategy strategy = JSON.toJavaObject(vo.getVpnstrategyGet(), Strategy.class);
            List<Strategy> list = strategyMapper.findBycomplain(strategy);
            Page<Strategy> jobPage = (Page<Strategy>) list;
            Map map = new HashMap();
            map.put("content", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp vpnstrategyGetsvcid(Integer id) {
        try {
            Strategy strategy = strategyMapper.selectByPrimaryKey(id);
            //增加浏览量
            String userId= TokenUtil.getTokenUserId();
            log.info("userId:"+userId);
            if (Xtool.isNotNull(userId)){
                Object vcode= redisAPI.get(CacheKeyManage.getVcode(userId+"strategy"+id));
                if(StringUtil.isNullObject(vcode)){
                    //如果没有阅读+1
                    strategyMapper.updateReadcount(id);
                    //设置24后过期
                    redisAPI.set(CacheKeyManage.getVcode(userId+"strategy"+id),Constants.SUCCEE_CODE+"",24*60*60);
                }
            }
            Map map = new HashMap();
            map.put("vpnstrategyGet", strategy);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp vpnstrategyAddsvc(OssVO vo) {

        try {
            Strategy strategy = JSON.toJavaObject(vo.getVpnstrategyAdd(), Strategy.class);
            Integer count = 0;
            if (Xtool.isNotNull(strategy.getToUser())&&Xtool.isNotNull(strategy.getContent())&&Xtool.isNotNull(strategy.getTitle())
            &&Xtool.isNotNull(strategy.getImage())&&Xtool.isNotNull(strategy.getIntroduction())) {
                count = strategyMapper.insertSelective(strategy);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, "必填项不能"+Constants.NO_NULL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp vpnstrategyUpdatesvc(OssVO vo) {
        try {
            Strategy strategy = JSON.toJavaObject(vo.getVpnstrategyUpdate(), Strategy.class);
            Integer count = 0;
            if (Xtool.isNotNull(strategy.getStId())) {
                count = strategyMapper.updateByPrimaryKeySelective(strategy);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp vpnstrategyDelsvc(OssVO vo) {
        try {
            Strategy strategy = JSON.toJavaObject(vo.getVpnstrategyDel(), Strategy.class);
            Integer count = 0;
            if (Xtool.isNotNull(strategy.getStId())) {
                count = strategyMapper.deleteByPrimaryKey(strategy.getStId());
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp vpnstrategyState(OssVO vo) {
        try {
            StrategyStateVO stateVO = JSON.toJavaObject(vo.getVpnstrategyUpdate(), StrategyStateVO.class);
            if (stateVO != null) {
                if (Xtool.isNull(stateVO.getStId()) || Xtool.isNull(stateVO.getState())) {
                    return new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
                }
                stateVO.getStId().forEach(x -> {
                    strategyMapper.updateByPrimaryKeySelective(new Strategy(x, stateVO.getState()));
                });
                return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);
            }
            return new BaseResp(Constants.FAIL_CODE, Constants.NO_NULL);
        } catch (Exception e) {
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public void updatefindTimeUp() {
        strategyMapper.updatefindTimeUp();
    }
}
