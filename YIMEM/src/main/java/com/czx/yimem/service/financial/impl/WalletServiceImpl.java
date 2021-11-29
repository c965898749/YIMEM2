package com.czx.yimem.service.financial.impl;

import com.czx.yimem.dao.WalletMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.JobExp;
import com.czx.yimem.entity.Wallet;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.financial.WalletService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.TokenUtil;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/21 0021 16:27
 */
@Service
public class WalletServiceImpl implements WalletService {

    @Resource
    private WalletMapper walletMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Transactional
    @Override
    public int insert(Wallet record) {
        return walletMapper.insert(record);
    }

    @Transactional
    @Override
    public int insertSelective(Wallet record) {
        return walletMapper.insertSelective(record);
    }

    @Transactional
    @Override
    public Wallet selectByPrimaryKey(Integer id) {
        return walletMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public Wallet findByWallet(Wallet record) {
        return walletMapper.findByWallet(record);
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(Wallet record) {
        return walletMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective2(Wallet record) {
        return walletMapper.updateByPrimaryKeySelective2(record);
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(Wallet record) {
        return walletMapper.updateByPrimaryKey(record);
    }

    @Transactional
    @Override
    public BaseResp vpnWalletGetsvc(OssVO vo) {
        Wallet wallet = JSON.toJavaObject(vo.getVpnWalletGet(), Wallet.class);
        // TODO 先验证微信和请求用户是否一致
        String id = TokenUtil.getTokenUserId();
        if (Xtool.isNotNull(wallet.getUserId()) && Xtool.isNotNull(id) && id.equals(wallet.getUserId() + "")) {
            Wallet byWallet = walletMapper.findByWallet(wallet);
            Map map = new HashMap();
            map.put("vpnWalletGetResp", byWallet);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } else {
            return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
        }
    }
}
