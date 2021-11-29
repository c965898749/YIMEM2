package com.czx.yimem.service.impl;

import com.czx.yimem.dao.QsCategoryGroupMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.QsCategory;
import com.czx.yimem.entity.QsCategoryGroup;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.QsCategoryGroupService;
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
public class QsCategoryGroupServiceImpl implements QsCategoryGroupService {
    @Resource
    private QsCategoryGroupMapper qsCategoryGroupMapper;

    @Override
    public int deleteByPrimaryKey(Integer gId) {
        return 0;
    }

    @Override
    public int insert(QsCategoryGroup record) {
        return 0;
    }

    @Override
    public int insertSelective(QsCategoryGroup record) {
        return 0;
    }

    @Override
    public QsCategoryGroup selectByPrimaryKey(Integer gId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(QsCategoryGroup record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(QsCategoryGroup record) {
        return 0;
    }

    @Override
    public BaseResp findByqsCategoryGroup(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            QsCategoryGroup qsCategoryGroup = JSON.toJavaObject(vo.getVpnqsCategoryGroupGet(), QsCategoryGroup.class);
            List<QsCategoryGroup> list = qsCategoryGroupMapper.findByqsCategoryGroup(qsCategoryGroup);
            Page<QsCategoryGroup> jobPage = (Page<QsCategoryGroup>) list;
            Map map = new HashMap();
            map.put("vpnqsCategoryGroupGetResp", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
