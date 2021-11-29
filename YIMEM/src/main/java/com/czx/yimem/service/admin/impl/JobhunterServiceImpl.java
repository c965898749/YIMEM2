package com.czx.yimem.service.admin.impl;

import com.czx.yimem.dao.JobhunterMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.Jobhunter;
import com.czx.yimem.entity.Protocol;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.admin.JobhunterService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
public class JobhunterServiceImpl implements JobhunterService {
    @Resource
    private JobhunterMapper jobhunterMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Jobhunter record) {
        return 0;
    }

    @Override
    public int insertSelective(Jobhunter record) {
        return 0;
    }

    @Override
    public Jobhunter selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Jobhunter record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Jobhunter record) {
        return 0;
    }

    @Override
    public BaseResp vpnjobhunterGetsvc(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            Jobhunter jobhunter = JSON.toJavaObject(vo.getVpnjobhunterGet(), Jobhunter.class);
            List<Jobhunter> list = jobhunterMapper.findByjobhunter(jobhunter);
            Page<Jobhunter> jobPage = (Page<Jobhunter>) list;
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
