package com.czx.yimem.service.impl;

import com.czx.yimem.dao.QsCategoryMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.JobExp;
import com.czx.yimem.entity.QsCategory;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.QsCategoryService;
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
public class QsCategoryServiceImpl implements QsCategoryService {
    @Resource
    private QsCategoryMapper qsCategoryMapper;

    @Override
    public int deleteByPrimaryKey(Integer cId) {
        return 0;
    }

    @Override
    public int insert(QsCategory record) {
        return 0;
    }

    @Override
    public int insertSelective(QsCategory record) {
        return 0;
    }

    @Override
    public QsCategory selectByPrimaryKey(Integer cId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(QsCategory record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(QsCategory record) {
        return 0;
    }

    @Override
    public BaseResp findByqsCategory(OssVO vo) {
        try {
            QsCategory qsCategory = JSON.toJavaObject(vo.getVpnqsCategoryGet(), QsCategory.class);
            List<QsCategory> list = qsCategoryMapper.findByqsCategory(qsCategory);
            Map map = new HashMap();
            map.put("vpnqsCategoryGetResp", list);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp updateByqsCategory(OssVO vo) {
        try {
            QsCategory qsCategory = JSON.toJavaObject(vo.getVpnqsCategoryUpdate(), QsCategory.class);
            Integer count = qsCategoryMapper.updateByPrimaryKeySelective(qsCategory);
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp addByqsCategory(OssVO vo) {
        try {
            QsCategory qsCategory = JSON.toJavaObject(vo.getVpnqsCategoryAdd(), QsCategory.class);
            Integer count = qsCategoryMapper.insertSelective(qsCategory);
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp delByqsCategory(OssVO vo) {
        try {
            QsCategory qsCategory = JSON.toJavaObject(vo.getVpnqsCategoryDel(), QsCategory.class);
            Integer count = 0;
            if (Xtool.isNotNull(qsCategory.getcId())) {
                count = qsCategoryMapper.deleteByPrimaryKey(qsCategory.getcId());
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
