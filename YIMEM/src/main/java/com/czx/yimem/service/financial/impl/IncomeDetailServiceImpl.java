package com.czx.yimem.service.financial.impl;

import com.czx.yimem.dao.DistributionMapper;
import com.czx.yimem.dao.IncomeDetailMapper;
import com.czx.yimem.dao.IncomeMapper;
import com.czx.yimem.dao.UserMapper;
import com.czx.yimem.dto.*;
import com.czx.yimem.entity.*;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.financial.IncomeDetailService;
import com.czx.yimem.service.financial.WalletService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 22:16
 */
@Slf4j
@Service
public class IncomeDetailServiceImpl implements IncomeDetailService {
    @Resource
    private IncomeDetailMapper incomeDetailMapper;
    @Resource
    private IncomeMapper incomeMapper;
    @Autowired
    private WalletService walletService;
    @Resource
    private DistributionMapper distributionMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public List<IncomeDetail> findByIncomeDetail(IncomeDetail record) {
        return incomeDetailMapper.findByIncomeDetail(record);
    }

    @Transactional
    @Override
    public List<IncomeDetail> findByIncomeDetail2(Integer userId) {
        return incomeDetailMapper.findByIncomeDetail2(userId);
    }

    @Transactional
    @Override
    public List<IncomeDetail> findIncomeDetail(Integer day) {
        return incomeDetailMapper.findIncomeDetail(day);
    }

    @Transactional
    @Override
    public List<IncomeDetail> findIncomeDetail2(Integer day) {
        return incomeDetailMapper.findIncomeDetail2(day);
    }

    @Transactional
    @Override
    public BigDecimal selectSumincomeDetail(Integer userId) {
        return incomeDetailMapper.selectSumincomeDetail(userId);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(IncomeDetail record) {
        return 0;
    }

    @Transactional
    @Override
    public int insertSelective(IncomeDetail record) {

        if (record == null || Xtool.isNull(record.getUserId())) {
            return 0;
        }
        Distribution distribution = distributionMapper.selectByuserid(record.getUserId());
        if (distribution == null) {
            log.info("异常账单数据：" + record.toString());
            return 0;
        }
        BigDecimal oldMoney = walletService.findByWallet(new Wallet(record.getUserId())).getMoney();
        BigDecimal enMoney = record.getBrokerage();
        Wallet wallet = new Wallet();
        wallet.setUserId(record.getUserId());
        wallet.setMoney(oldMoney.add(enMoney));
        walletService.updateByPrimaryKeySelective2(wallet);
        return incomeDetailMapper.insertSelective(record);

    }

    @Override
    public IncomeDetail selectByPrimaryKey(Integer id) {
        return incomeDetailMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(IncomeDetail record) {
        return incomeDetailMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(IncomeDetail record) {
        return incomeDetailMapper.updateByPrimaryKey(record);
    }

    @Override
    public BaseResp getnewincomeDetails() {
        List<IncomeDetailVOs> list = incomeDetailMapper.getnewincomeDetails();
        Map map = new HashMap();
        map.put("list", list);
        return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
    }

    @Override
    public BaseResp vpnincomeDetailGetsvc(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            IncomeDetail incomeDetail = JSON.toJavaObject(vo.getVpnincomeDetailGet(), IncomeDetail.class);
            List<IncomeDetail> list = incomeDetailMapper.findByIncomeDetail(incomeDetail);
            Page<IncomeDetail> jobPage = (Page<IncomeDetail>) list;
            Map map = new HashMap();
            map.put("vpnincomeDetailGetResp", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp vpnincomeDetailGetsvc2(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            IncomeDetailVO incomeDetailVO = JSON.toJavaObject(vo.getVpnincomeDetailGet(), IncomeDetailVO.class);
            List<IncomeDetailVO> list = incomeDetailMapper.findByTime(incomeDetailVO);
            list.forEach(x -> {
                List<IncomeDetail> incomeDetails = incomeDetailMapper.selectincomeDetailsBytime(x);
                x.setIncomeDetails(incomeDetails);
            });
            Page<IncomeDetailVO> jobPage = (Page<IncomeDetailVO>) list;
            Map map = new HashMap();
            map.put("vpnincomeDetailGetResp", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp vpnbrokerageRankingListsvc(OssVO vo) {
        try {
            BrokerageRankingList brokerageRankingList = JSON.toJavaObject(vo.getVpnbrokerageRankingList(), BrokerageRankingList.class);
            if (Xtool.isNotNull(brokerageRankingList.getRanking()) || Xtool.isNotNull(brokerageRankingList.getUserId())) {
                BrokerageRankingList ranking = incomeDetailMapper.findbrokerageRankingListbyuserId(brokerageRankingList);
                brokerageRankingList.setMoney(new BigDecimal(0));
                brokerageRankingList.setId(0);
                if (ranking != null) {
                    ranking.setRanking(brokerageRankingList.getRanking());
                    BeanUtils.copyProperties(ranking, brokerageRankingList);
                }
                List<BrokerageRankingList> list;
                if (brokerageRankingList.getRanking() == 1 || brokerageRankingList.getRanking() == 2)
                    list = incomeDetailMapper.findbrokerageRankingList(brokerageRankingList);
                else
                    list = incomeMapper.findbrokerageRankingList(10);
                if (Xtool.isNotNull(list)) {
                    list.forEach(x -> {
                        User user = userMapper.selectByPrimaryKey(x.getUserId());
                        if (user != null) {
                            x.setHeadImg(user.getHeadImg());
                        }
                    });
                }
                brokerageRankingList.setRankingList(list);
            }
            Map map = new HashMap();
            map.put("vpnincomeDetailGetResp", brokerageRankingList);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp vpnInterviewRankingListsvc(OssVO vo) {
        try {
            InterviewRankingList brokerageRankingList = JSON.toJavaObject(vo.getVpnInterviewRankingList(), InterviewRankingList.class);
            if (Xtool.isNotNull(brokerageRankingList.getRanking()) || Xtool.isNotNull(brokerageRankingList.getUserId())) {
                InterviewRankingList ranking = incomeDetailMapper.findInterviewRankingListbyuserId(brokerageRankingList);
                brokerageRankingList.setPeoplenum(0);
                brokerageRankingList.setId(0);
                if (ranking != null) {
                    ranking.setRanking(brokerageRankingList.getRanking());
                    BeanUtils.copyProperties(ranking, brokerageRankingList);
                }
                List<InterviewRankingList>   list = incomeDetailMapper.findInterviewRankingList(brokerageRankingList);
                if (Xtool.isNotNull(list)) {
                    list.forEach(x -> {
                        User user = userMapper.selectByPrimaryKey(x.getUserId());
                        if (user != null) {
                            x.setHeadImg(user.getHeadImg());
                        }
                    });
                }
                brokerageRankingList.setRankingList(list);
            }
            Map map = new HashMap();
            map.put("vpnincomeDetailGetResp", brokerageRankingList);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp vpnRecommendRankingListsvc(OssVO vo) {
        try {
            RecommendRankingList brokerageRankingList = JSON.toJavaObject(vo.getVpnRecommendRankingList(), RecommendRankingList.class);
            if (Xtool.isNotNull(brokerageRankingList.getRanking()) || Xtool.isNotNull(brokerageRankingList.getUserId())) {
                RecommendRankingList ranking = incomeDetailMapper.findRecommendRankingListbyuserId(brokerageRankingList);
                brokerageRankingList.setPeoplenum(0);
                brokerageRankingList.setId(0);
                if (ranking != null) {
                    ranking.setRanking(brokerageRankingList.getRanking());
                    BeanUtils.copyProperties(ranking, brokerageRankingList);
                }
                List<RecommendRankingList> list= incomeDetailMapper.findRecommendRankingList(brokerageRankingList);
                if (Xtool.isNotNull(list)) {
                    list.forEach(x -> {
                        User user = userMapper.selectByPrimaryKey(x.getUserId());
                        if (user != null) {
                            x.setHeadImg(user.getHeadImg());
                        }
                    });
                }
                brokerageRankingList.setRankingList(list);
            }
            Map map = new HashMap();
            map.put("vpnincomeDetailGetResp", brokerageRankingList);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
