package com.czx.yimem.service.admin.impl;

import com.czx.yimem.dao.GiftMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.Gift;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.admin.GiftService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenli
 * @createTime 2021/4/2 15:54
 */
@Service
public class GiftServiceImpl implements GiftService{
    @Autowired
    private GiftService giftService;
    @Autowired
    private GiftMapper giftMapper;

    @Override
    public BaseResp getList(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            List<Gift> list = giftMapper.getList();
            Page<Gift> jobPage = (Page<Gift>) list;
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
    @Transactional
    public BaseResp updateGift(OssVO vo){
        Gift gift = JSON.toJavaObject(vo.getVpnGiftAdd(), Gift.class);
        int count=0;
        if(Xtool.isNull(gift.getId())){//新增
            count=giftMapper.insertSelective(gift);
        }else{//编辑
            count=giftMapper.updateByPrimaryKeySelective(gift);
        }
        return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
    }

    @Override
    public BaseResp delGift(OssVO vo){
        Gift gift = JSON.toJavaObject(vo.getVpnGiftAdd(), Gift.class);
        if(Xtool.isNull(gift.getId())){
            return new BaseResp(Constants.FAIL_CODE,"礼物ID"+Constants.NO_NULL);
        }
        Gift info=new Gift();
        info.setId(gift.getId());
        info.setState(2);//礼物状态设为禁用
        int count=giftMapper.updateByPrimaryKeySelective(info);
        return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
    }

    @Override
    public BaseResp getGiftList(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            List<Gift> list = giftMapper.getGiftList();
            Page<Gift> jobPage = (Page<Gift>) list;
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
    
}
