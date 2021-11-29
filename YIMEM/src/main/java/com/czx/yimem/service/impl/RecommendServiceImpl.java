package com.czx.yimem.service.impl;

import com.czx.yimem.dao.DeliverDetailMapper;
import com.czx.yimem.dao.DistributionMapper;
import com.czx.yimem.dao.IncomeDetailMapper;
import com.czx.yimem.dao.RecommendMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.dto.RecommendVO;
import com.czx.yimem.entity.DeliverDetail;
import com.czx.yimem.entity.IncomeDetail;
import com.czx.yimem.entity.Recommend;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.RecommendService;
import com.czx.yimem.utils.Constants;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class RecommendServiceImpl implements RecommendService {
    @Resource
    private RecommendMapper recommendMapper;
    @Resource
    private DistributionMapper distributionMapper;
    @Resource
    private DeliverDetailMapper deliverDetailMapper;
    @Resource
    private IncomeDetailMapper incomeDetailMapper;
    @Override
    public int deleteByPrimaryKey(Integer reId) {
        return 0;
    }

    @Override
    public int insert(Recommend record) {
        return 0;
    }

    @Override
    public int insertSelective(Recommend record) {
        try {
            //验证是否分销者
            if (distributionMapper.isdistribution(record.getUserId())>0){
                recommendMapper.insertSelective(record);
            }
            return 1;
        } catch (Exception e) {
            log.error("分销明细异常",e.getMessage());
        }
        return 0;
    }

    @Override
    public Recommend selectByPrimaryKey(Integer reId) {
        return null;
    }

    @Override
    public Recommend selectByDelId(Recommend record) {
        return recommendMapper.selectByDelId(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Recommend record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Recommend record) {
        return 0;
    }

    @Override
    public int updateiscount(IncomeDetail incomeDetail) {

        return 0;
    }

    @Override
    public Integer selectrecommend(Recommend record) {
        return recommendMapper.selectrecommend(record);
    }

    @Override
    public BaseResp vpnrecommendGetsvc(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            RecommendVO recommend = JSON.toJavaObject(vo.getVpnrecommendGetResp(), RecommendVO.class);
            List<RecommendVO> list = recommendMapper.findByrecommend(recommend);
            list.forEach(x->{
                if ("7".equals(x.getState())){
                    DeliverDetail deliverDetail=deliverDetailMapper.selectByPrimaryKey11(x.getDelId());
                    x.setEntryTime(deliverDetail.getEntryTime());
                }
                if ("8".equals(x.getState())){
                    DeliverDetail deliverDetail=deliverDetailMapper.selectByPrimaryKey11(x.getDelId());
                    x.setLeveTime(deliverDetail.getLeaveTime());
                }
                if ("10".equals(x.getState())){
                    IncomeDetail incomeDetail=incomeDetailMapper.selectBydelid(x.getDelId());
                    x.setAccountTime(incomeDetail.getCreatedTime());
                }
            });
            Page<RecommendVO> jobPage = (Page<RecommendVO>) list;
            Map map = new HashMap();
            map.put("vpnrecommendGetResp", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
