package com.czx.yimem.service.impl;

import com.czx.yimem.dao.RmProvinceMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.QsCategory;
import com.czx.yimem.entity.RmProvince;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.RmProvinceService;
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
public class RmProvinceServiceImpl implements RmProvinceService {
    @Resource
    private RmProvinceMapper rmProvinceMapper;

    @Override
    public int deleteByPrimaryKey(String uuid) {
        return 0;
    }

    @Override
    public int insert(RmProvince record) {
        return 0;
    }

    @Override
    public int insertSelective(RmProvince record) {
        return 0;
    }

    @Override
    public RmProvince selectByPrimaryKey(String uuid) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(RmProvince record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(RmProvince record) {
        return 0;
    }

    @Override
    public BaseResp findByrmProvince(OssVO vo) {
        try {
            RmProvince rmProvince = JSON.toJavaObject(vo.getVpnRmProvinceGet(), RmProvince.class);
            List<RmProvince> list = rmProvinceMapper.findByProvince(rmProvince);
            Map map = new HashMap();
            map.put("vpnRmProvinceGetResp", list);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
