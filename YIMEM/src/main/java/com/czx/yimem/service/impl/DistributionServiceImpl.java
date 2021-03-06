package com.czx.yimem.service.impl;

import com.czx.yimem.dao.DistributionMapper;
import com.czx.yimem.dao.NewsMapper;
import com.czx.yimem.dao.UserMapper;
import com.czx.yimem.dao.WalletMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.*;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.DistributionService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.TokenUtil;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Service
public class DistributionServiceImpl implements DistributionService {
    @Resource
    private DistributionMapper distributionMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private WalletMapper walletMapper;
    @Resource
    private NewsMapper newsMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Distribution record) {
        return 0;
    }

    @Override
    public int insertSelective(Distribution record) {
        return 0;
    }

    @Override
    public Distribution selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Distribution record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Distribution record) {
        return 0;
    }

    @Override
    public BaseResp vpndistributionGetCardsvc(OssVO vo) {
        return null;
    }

    @Override
    public BaseResp vpndistributionGetsvc(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            Distribution distribution = JSON.toJavaObject(vo.getVpndistributionGet(), Distribution.class);
            List<Distribution> list = distributionMapper.findBydistribution(distribution);
            Page<Distribution> jobPage = (Page<Distribution>) list;
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
    public BaseResp vpndistributionAddsvc(OssVO vo) {
        try {
            Distribution distribution = JSON.toJavaObject(vo.getVpndistributionAdd(), Distribution.class);
            Integer count = 0;
            if (Xtool.isNotNull(distribution.getUserId())&&Xtool.isNotNull(distribution.getCardUrlA())&&
            Xtool.isNotNull(distribution.getCardUrlB())) {
                // TODO ??????????????????????????????????????????
                String id= TokenUtil.getTokenUserId();
                if (Xtool.isNotNull(id)&&id.equals(distribution.getUserId()+"")){
                    //                ?????????????????????
                    if (Xtool.isNotNull(distributionMapper.findBydistribution(new Distribution(distribution.getUserId(),"1")))||Xtool.isNotNull(distributionMapper.findBydistribution(new Distribution(distribution.getUserId(),"2")))){
                        return new BaseResp(Constants.FAIL_CODE,"????????????????????????");
                    }
                    User user=userMapper.selectByPrimaryKey(distribution.getUserId());
                    if (user!=null){
                        distribution.setName(user.getUserName());
                        distribution.setPhone(user.getPhoneNum());
                        count = distributionMapper.insertSelective(distribution);
                    }
                }
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp vpndistributionUpdatesvc(OssVO vo) {
        try {
            Distribution distribution = JSON.toJavaObject(vo.getVpndistributionUpdate(), Distribution.class);
            Integer count = 0;
            Distribution d=distributionMapper.selectByPrimaryKey(distribution.getId());
            if (d!=null){
                News news=new News();
                if (Xtool.isNotNull(distribution.getId())) {
                    if ("2".equals(distribution.getState())){
                        /*User user=userMapper.selectByPrimaryKey(d.getUserId());
                        user.setInviteCode(getInvitCode());//?????????????????????
                        userMapper.updateByPrimaryKeySelective(user);//???????????????*/
                        walletMapper.insertSelective(new Wallet(d.getUserId()));
                        news.setToUserId(d.getUserId());
                        news.setTitle("???????????????????????????");
                        news.setIdentity(1);
                        news.setContent("???????????????????????????????????????????????????????????????????????????");
                        news.setNewType(1);
                        newsMapper.insertSelective(news);
                    }else {
                        news.setToUserId(d.getUserId());
                        news.setTitle("?????????????????????????????????????????????");
                        news.setIdentity(1);
                        news.setContent("?????????????????????????????????????????????????????????");
                        news.setNewType(1);
                        newsMapper.insertSelective(news);
                    }
                    count = distributionMapper.updateByPrimaryKeySelective(distribution);
                }
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

}
