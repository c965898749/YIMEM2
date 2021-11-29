package com.czx.yimem.service.impl;

import com.czx.yimem.dao.RmCountyMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.RmCounty;
import com.czx.yimem.entity.RmProvince;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.RmCountyService;
import com.czx.yimem.utils.Constants;
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
public class RmCountyServiceImpl implements RmCountyService {
    @Resource
    private RmCountyMapper rmCountyMapper;

    @Override
    public int deleteByPrimaryKey(String uuid) {
        return 0;
    }

    @Override
    public int insert(RmCounty record) {
        return 0;
    }

    @Override
    public int insertSelective(RmCounty record) {
        return 0;
    }

    @Override
    public RmCounty selectByPrimaryKey(String uuid) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(RmCounty record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(RmCounty record) {
        return 0;
    }

    @Override
    public BaseResp findByrmCounty(OssVO vo) {
        try {
            RmCounty rmCounty = JSON.toJavaObject(vo.getVpnRmCountyGet(), RmCounty.class);
            List<RmCounty> list = rmCountyMapper.findByCounty(rmCounty);
            Map map = new HashMap();
            map.put("vpnRmCountyGetResp", list);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
