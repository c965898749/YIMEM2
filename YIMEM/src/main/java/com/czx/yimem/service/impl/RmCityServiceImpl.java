package com.czx.yimem.service.impl;

import com.czx.yimem.dao.RmCityMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.RmCity;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.RmCityService;
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
public class RmCityServiceImpl implements RmCityService {
    @Resource
    private RmCityMapper rmCityMapper;

    @Override
    public int deleteByPrimaryKey(String uuid) {
        return 0;
    }

    @Override
    public int insert(RmCity record) {
        return 0;
    }

    @Override
    public int insertSelective(RmCity record) {
        return 0;
    }

    @Override
    public RmCity selectByPrimaryKey(String uuid) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(RmCity record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(RmCity record) {
        return 0;
    }

    @Override
    public BaseResp findByrmCity(OssVO vo) {
        try {
            RmCity rmCity = JSON.toJavaObject(vo.getVpnRmCityGet(), RmCity.class);
            List<RmCity> list = rmCityMapper.findByCity(rmCity);
            Map map = new HashMap();
            map.put("vpnRmCityGetResp", list);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
