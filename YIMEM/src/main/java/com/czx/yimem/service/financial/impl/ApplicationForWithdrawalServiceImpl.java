package com.czx.yimem.service.financial.impl;

import com.czx.yimem.dao.ApplicationForWithdrawalMapper;
import com.czx.yimem.dao.DistributionMapper;
import com.czx.yimem.dao.NewsMapper;
import com.czx.yimem.dto.ApplicationVO;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.*;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.UserService;
import com.czx.yimem.service.financial.ApplicationForWithdrawalService;
import com.czx.yimem.service.financial.IncomeDetailService;
import com.czx.yimem.service.financial.WalletService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.RedisAPI;
import com.czx.yimem.utils.TokenUtil;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/13 0013 17:19
 */
@Service
public class ApplicationForWithdrawalServiceImpl implements ApplicationForWithdrawalService {
    @Resource
    private ApplicationForWithdrawalMapper applicationForWithdrawalMapper;
    @Autowired
    private ApplicationForWithdrawalService applicationForWithdrawalService;
    @Resource
    private NewsMapper newsMapper;
    @Autowired
    private WalletService walletService;
    @Autowired
    private IncomeDetailService incomeDetailService;
    @Resource
    private DistributionMapper distributionMapper;
    @Autowired
    private RedisAPI redisAPI;

    @Transactional
    @Override
    public Integer enMoneyCount(Integer userId) {
        return applicationForWithdrawalMapper.enMoneyCount(userId);
    }

    @Override
    public List<ApplicationForWithdrawal> findByapplication(ApplicationForWithdrawal record) {
        return applicationForWithdrawalMapper.findByapplication(record);
    }

    @Override
    public List<ApplicationVO> findByTime(ApplicationVO applicationVO) {
        return applicationForWithdrawalMapper.findByTime(applicationVO);
    }

    @Override
    public List<ApplicationForWithdrawal> selectapplicationForWithdrawalsBytime(ApplicationVO applicationVO) {
        return applicationForWithdrawalMapper.selectapplicationForWithdrawalsBytime(applicationVO);
    }

    @Transactional
    @Override
    public BigDecimal sumEnMoney(Integer userId) {
        return applicationForWithdrawalMapper.sumEnMoney(userId);
    }

    @Transactional
    @Override
    public String getstate(Integer id) {
        return applicationForWithdrawalMapper.getstate(id);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(ApplicationForWithdrawal record) {
        return 0;
    }

    @Transactional
    @Override
    public int insertSelective(ApplicationForWithdrawal record) {
        return applicationForWithdrawalMapper.insertSelective(record);
    }

    @Transactional
    @Override
    public ApplicationForWithdrawal selectByPrimaryKey(Integer id) {
        return applicationForWithdrawalMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(ApplicationForWithdrawal record) {
        return applicationForWithdrawalMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ApplicationForWithdrawal record) {
        return applicationForWithdrawalMapper.updateByPrimaryKey(record);
    }

    @Override
    public BaseResp vpnApplicationForWithdrawalGetsvc(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            ApplicationForWithdrawal application = JSON.toJavaObject(vo.getVpnApplicationForWithdrawalGet(), ApplicationForWithdrawal.class);
            List<ApplicationForWithdrawal> list = applicationForWithdrawalMapper.findByapplication(application);
            Page<ApplicationForWithdrawal> jobPage = (Page<ApplicationForWithdrawal>) list;
            Map map = new HashMap();
            map.put("vpnApplicationForWithdrawalGet", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp vpnApplicationForWithdrawalGetsvc2(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            ApplicationVO application = JSON.toJavaObject(vo.getVpnApplicationForWithdrawalGet(), ApplicationVO.class);
            List<ApplicationVO> list = applicationForWithdrawalMapper.findByTime(application);
            list.forEach(x -> {
                List<ApplicationForWithdrawal> applicationForWithdrawals = applicationForWithdrawalMapper.selectapplicationForWithdrawalsBytime(x);
                x.setApplicationForWithdrawals(applicationForWithdrawals);
            });
            Page<ApplicationVO> jobPage = (Page<ApplicationVO>) list;
            Map map = new HashMap();
            map.put("vpnApplicationForWithdrawalGet", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Transactional
    @Override
    public BaseResp vpnApplicationForWithdrawalUpdatesvc(OssVO vo) {
        ApplicationForWithdrawal application = JSON.toJavaObject(vo.getVpnApplicationForWithdrawalUpdate(), ApplicationForWithdrawal.class);
        Integer count = 0;
        if (Xtool.isNotNull(application.getId()) && Xtool.isNotNull(application.getState())) {
            if ("2".equals(application.getState())) {
                // TODO ??????????????????
                if ("2".equals(applicationForWithdrawalService.getstate(application.getId()))) {
                    return new BaseResp(Constants.FAIL_CODE, "???????????????????????????????????????");
                }
                if ("0".equals(applicationForWithdrawalService.getstate(application.getId()))) {
                    return new BaseResp(Constants.FAIL_CODE, "???????????????????????????????????????");
                }
                //??????????????????
                ApplicationForWithdrawal forWithdrawal = applicationForWithdrawalService.selectByPrimaryKey(application.getId());
                BigDecimal enMoney = new BigDecimal(forWithdrawal.getMoney());
                BigDecimal oldMoney = walletService.findByWallet(new Wallet(forWithdrawal.getUserId())).getMoney();
                //TODO ??????????????????
                if (enMoney.compareTo(oldMoney) > 0 || enMoney.compareTo(incomeDetailService.selectSumincomeDetail(forWithdrawal.getUserId())) > 0) {
                    return new BaseResp(Constants.FAIL_CODE, Constants.NO_MONEY);
                }
                //TODO ??????????????????
                List<IncomeDetail> incomeDetails = incomeDetailService.findByIncomeDetail2(forWithdrawal.getUserId());
                BigDecimal k = enMoney;
                for (IncomeDetail x : incomeDetails) {
                    if (k.compareTo(x.getExpend()) > 0) {
                        k = k.subtract(x.getExpend());
                        x.setExpend(new BigDecimal(0));
                        incomeDetailService.updateByPrimaryKeySelective(x);
                    } else {
                        x.setExpend(x.getExpend().subtract(k));
                        incomeDetailService.updateByPrimaryKeySelective(x);
                        break;
                    }
                }
                count = applicationForWithdrawalService.updateByPrimaryKeySelective(application);
                if (count>0){
                    News ws = new News();
                    ws.setToUserId(forWithdrawal.getUserId());
                    ws.setTitle("????????????");
                    ws.setContent("???????????? "+enMoney+" ??????????????????????????????");
                    ws.setIdentity(2);
                    ws.setNewType(6);//????????????
                    newsMapper.insertSelective(ws);
                }
            } else if ("0".equals(application.getState())) {
                if ("2".equals(applicationForWithdrawalService.getstate(application.getId()))) {
                    return new BaseResp(Constants.FAIL_CODE, "???????????????????????????????????????");
                }
                if ("0".equals(applicationForWithdrawalService.getstate(application.getId()))) {
                    return new BaseResp(Constants.FAIL_CODE, "???????????????????????????????????????");
                }
                ApplicationForWithdrawal forWithdrawal = applicationForWithdrawalService.selectByPrimaryKey(application.getId());
                BigDecimal enMoney = new BigDecimal(forWithdrawal.getMoney());
                BigDecimal oldMoney = walletService.findByWallet(new Wallet(forWithdrawal.getUserId())).getMoney();
                count = applicationForWithdrawalService.updateByPrimaryKeySelective(application);
                if (count>0){
                    Wallet wallet = new Wallet();
                    wallet.setUserId(forWithdrawal.getUserId());
                    wallet.setMoney(oldMoney.add(enMoney));
                    walletService.updateByPrimaryKeySelective2(wallet);
                    News ws = new News();
                    ws.setToUserId(forWithdrawal.getUserId());
                    ws.setTitle("????????????");
                    ws.setContent("???????????? "+enMoney+" ??????????????????????????????????????????"+application.getReason());
                    ws.setIdentity(2);
                    ws.setNewType(6);//????????????
                    newsMapper.insertSelective(ws);
                }
            } else {
                return new BaseResp(Constants.FAIL_CODE, "??????" + Constants.NO_STIPULATE);
            }
        }
        return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
    }

    @Transactional
    @Override
    public BaseResp vpnApplicationForWithdrawalAddsvc(OssVO vo) {
        ApplicationForWithdrawal applicationForWithdrawal = JSON.toJavaObject(vo.getVpnApplicationForWithdrawalAdd(), ApplicationForWithdrawal.class);
        Integer count = 0;
//        if (Xtool.isNotNull(applicationForWithdrawal.getUserId()) &&
//                Xtool.isNotNull(applicationForWithdrawal.getMoney()) &&
//                Xtool.isNotNull(applicationForWithdrawal.getPassword())) {
        if (Xtool.isNull(applicationForWithdrawal.getUserId())) {
            return new BaseResp(Constants.FAIL_CODE, "???????????????");
        }
        if (Xtool.isNull(applicationForWithdrawal.getMoney())) {
            return new BaseResp(Constants.FAIL_CODE, "??????????????????0???");
        }
//                Xtool.isNotNull(applicationForWithdrawal.getMoney())) {
        // TODO ??????????????????????????????????????????
            String id = TokenUtil.getTokenUserId();
        if (Xtool.isNotNull(id) && id.equals(applicationForWithdrawal.getUserId() + "")) {
            // TODO ???????????????????????????

//                BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
//                User user = userService.selectByPrimaryKey(applicationForWithdrawal.getUserId());
//                if (encode.matches(applicationForWithdrawal.getPassword(), user.getPassword())) {
//                    // TODO ?????????????????????
//                    BigDecimal enMoney = new BigDecimal(applicationForWithdrawal.getMoney());
//                    BigDecimal oldMoney=walletService.findByWallet(new Wallet(applicationForWithdrawal.getUserId())).getMoney();
//                    if (enMoney.compareTo(oldMoney) > 0||
//                            new BigDecimal(applicationForWithdrawal.getMoney()).compareTo(incomeDetailService.selectSumincomeDetail(user.getUserId()))>0) {
//                        return new BaseResp(Constants.FAIL_CODE,Constants.NO_MONEY);
//                    }
//                    BigDecimal sumEnMoney=applicationForWithdrawalService.sumEnMoney(applicationForWithdrawal.getUserId());
//                    //TODO ????????????????????????????????????????????????????????????
//                    if (applicationForWithdrawalService.enMoneyCount(applicationForWithdrawal.getUserId())>Constants.EN_MONEY_COUNT) {
//                        return new BaseResp(Constants.FAIL_CODE, "??????????????????????????????????????????");
//                    }
//                    if (sumEnMoney.add(enMoney).compareTo(oldMoney) > 0) {
//                        return new BaseResp(Constants.FAIL_CODE, "??????????????????????????????");
//                    }
//                    applicationForWithdrawal.setPhone(user.getPhoneNum());
//                    applicationForWithdrawal.setUserName(user.getUserName());
//                    count = applicationForWithdrawalService.insertSelective(applicationForWithdrawal);
//
//                }
            //???????????????
            Distribution distribution = distributionMapper.selectByuserid(applicationForWithdrawal.getUserId());
            if (distribution == null) {
                return new BaseResp(Constants.FAIL_CODE, "??????????????????????????????");
            }
            // TODO ?????????????????????
            BigDecimal enMoney = new BigDecimal(applicationForWithdrawal.getMoney());
            BigDecimal oldMoney = walletService.findByWallet(new Wallet(applicationForWithdrawal.getUserId())).getMoney();
            //TODO ??????????????????
            if (enMoney.compareTo(oldMoney) > 0 || enMoney.compareTo(incomeDetailService.selectSumincomeDetail(Integer.parseInt(id)))> 0) {
                return new BaseResp(Constants.FAIL_CODE, Constants.NO_MONEY);
            }
//            BigDecimal sumEnMoney = applicationForWithdrawalService.sumEnMoney(applicationForWithdrawal.getUserId());
//            if (sumEnMoney.add(enMoney).compareTo(oldMoney) > 0) {
//                return new BaseResp(Constants.FAIL_CODE, Constants.NO_MONEY);
//            }
            //TODO ????????????????????????????????????????????????????????????
//            if (applicationForWithdrawalService.enMoneyCount(applicationForWithdrawal.getUserId()) > Constants.EN_MONEY_COUNT) {
//                return new BaseResp(Constants.FAIL_CODE, "??????????????????????????????????????????");
//            }
            Integer enMoneyCount=0;
            if (!redisAPI.exist("enMoneyCount_"+id) || redisAPI.get("enMoneyCount_"+id) == null) {
                redisAPI.set("enMoneyCount_"+id, enMoneyCount+"", 24*60 * 60);
            } else {
                enMoneyCount = Integer.parseInt(redisAPI.get("enMoneyCount_"+id).toString());
            }
            if (enMoneyCount >= Constants.EN_MONEY_COUNT) {
                return new BaseResp(Constants.FAIL_CODE, "???????????????????????????????????????"+Constants.EN_MONEY_COUNT+"???");
            }
            applicationForWithdrawal.setPhone(distribution.getPhone());
            applicationForWithdrawal.setUserName(distribution.getName());
            count = applicationForWithdrawalService.insertSelective(applicationForWithdrawal);
            if (count>0){
                enMoneyCount=enMoneyCount+1;
                Wallet wallet=new Wallet();
                wallet.setMoney(oldMoney.subtract(enMoney));
                wallet.setUserId(Integer.parseInt(id));
                walletService.updateByPrimaryKeySelective2(wallet);
                redisAPI.set("enMoneyCount_"+id, enMoneyCount+"", 24*60 * 60);
            }
        }
        return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
    }
}
