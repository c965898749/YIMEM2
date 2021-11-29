package com.czx.yimem.service.impl;

import com.czx.yimem.dao.GiftChangeHistoryMapper;
import com.czx.yimem.dao.GiftMapper;
import com.czx.yimem.dao.PointDetailMapper;
import com.czx.yimem.dao.PointMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.Gift;
import com.czx.yimem.entity.GiftChangeHistory;
import com.czx.yimem.entity.Point;
import com.czx.yimem.entity.PointDetail;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.PointService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.DateUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PointServiceImpl implements PointService {

    @Resource
    private PointMapper pointMapper;
    @Resource
    private PointDetailMapper pointDetailMapper;
    @Autowired
    private GiftMapper giftMapper;
    @Autowired
    private GiftChangeHistoryMapper giftChangeHistoryMapper;

    @Transactional
    @Override
    public BaseResp pointSign(OssVO vo) {
        try{
            //查询 积分表
            Point reqPoint = JSON.toJavaObject(vo.getVpnPointAdd(),Point.class);
            Point point = pointMapper.selectByUserKey(reqPoint.getUserId());
            //登记积分明细
            PointDetail pointDetail = new PointDetail();
            pointDetail.setUserId(reqPoint.getUserId());
            pointDetail.setContent("签到");
            pointDetail.setPoint(reqPoint.getValue());
            pointDetail.setCreatetime(new Date());
            pointDetail.setFlag(1);
            pointDetailMapper.insertSelective(pointDetail);
            Map<String,Object> map = new HashMap<>();
            //第一次签到
            if(point==null){
                reqPoint.setContdays(1);
                pointMapper.insertSelective(reqPoint);
                map.put("contDays",1);
            }else{
                //更新上次
                long btwDays = DateUtil.getDaysBetween(point.getLastSign(),reqPoint.getLastSign());
                //已签到
                if(btwDays==0){
                    return new BaseResp(Constants.FAIL_CODE, Constants.SIGNED);
                }else if(btwDays==1){
                    //连续签到
                    point.setContdays(point.getContdays()+1);
                }else{
                    //签到中断
                    point.setContdays(1);
                }
                point.setLastSign(reqPoint.getLastSign());
                point.setValue(point.getValue()+reqPoint.getValue());
                pointMapper.updateByPrimaryKey(point);
                map.put("contDays",point.getContdays());
            }
            map.put("value",reqPoint.getValue());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        }catch(Exception e){
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp getPointByUserId(int userId) {

        try{
            Point point = pointMapper.selectByUserKey(userId);
            if (point == null) {
                point=new Point();
                point.setContdays(0);
                point.setValue(0);
            }
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE,point);
        }catch(Exception e){
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }

    }

    @Override
    public BaseResp getPointdetail(OssVO vo) {

        try{
            //分页
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            String mark = vo.getVpnPointAdd().getString("mark");
            PointDetail reqPoint = JSON.toJavaObject(vo.getVpnPointAdd(),PointDetail.class);

            Integer page=1;
            Integer pageSize=100;
            if (pageVO!=null){
                page=pageVO.getPage();
                pageSize=pageVO.getPageSize();
            }
            PageHelper.startPage(page,pageSize );
            List<PointDetail> points;

            if("month".equals(mark))
                points = pointDetailMapper.queryMonth(reqPoint);
            else{
                points = pointDetailMapper.queryYear(reqPoint);

            }
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE,points);
        }catch(Exception e){
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Transactional
    @Override
    public BaseResp pointToGift(OssVO vo) {
        GiftChangeHistory changeHistory = JSON.toJavaObject(vo.getVpnGiftChange(), GiftChangeHistory.class);
        Point point = pointMapper.selectByUserKey(changeHistory.getUserId());
        Gift gift = giftMapper.selectByPrimaryKey(changeHistory.getGiftId());
        if (gift == null) {
            return new BaseResp(Constants.FAIL_CODE, Constants.NO_GIFT);
        }
        if (point == null || point.getValue() < gift.getPoint()) {
            return new BaseResp(Constants.FAIL_CODE, Constants.NO_POINTS);
        } else {
            //登记积分兑换明细
            GiftChangeHistory giftChangeHistory = new GiftChangeHistory();
            giftChangeHistory.setUserId(changeHistory.getUserId());
            giftChangeHistory.setGiftId(changeHistory.getGiftId());
            giftChangeHistory.setPoints(gift.getPoint());
            giftChangeHistory.setCreatetime(new Date());
            giftChangeHistoryMapper.insertSelective(giftChangeHistory);

            //登记积分明细
            PointDetail pointDetail = new PointDetail();
            pointDetail.setUserId(changeHistory.getUserId());
            pointDetail.setContent("兑换礼物 —— "+gift.getName());
            pointDetail.setPoint(gift.getPoint());
            pointDetail.setCreatetime(new Date());
            pointDetail.setFlag(0);
            pointDetailMapper.insertSelective(pointDetail);

            //更新用户积分
            point.setValue(point.getValue() - gift.getPoint());
            pointMapper.updateByPrimaryKey(point);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);
        }
    }



}
