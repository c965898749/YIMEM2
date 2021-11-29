package com.czx.yimem.service.impl;

import com.czx.yimem.dao.ComplaintMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.Complaint;
import com.czx.yimem.entity.JobExp;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.ComplaintService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Service
public class ComplaintServiceImpl implements ComplaintService {
    @Resource
    private ComplaintMapper complaintMapper;
    @Override
    public int deleteByPrimaryKey(Integer comId) {
        return 0;
    }

    @Override
    public int insert(Complaint record) {
        return 0;
    }

    @Override
    public int insertSelective(Complaint record) {
        return 0;
    }

    @Override
    public Complaint selectByPrimaryKey(Integer comId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Complaint record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Complaint record) {
        return 0;
    }

    @Override
    public BaseResp complaintService(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            Complaint complaint = JSON.toJavaObject(vo.getVpncomplaintGet(), Complaint.class);
            List<Complaint> list = complaintMapper.findBycomplain(complaint);
            Page<Complaint> jobPage = (Page<Complaint>) list;
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
    public BaseResp vpncomplaintGetsvcid(Integer id) {
        try {
            Complaint list = complaintMapper.selectByPrimaryKey(id);
            Map map = new HashMap();
            map.put("vpncomplaintGet", list);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp vpncomplaintAddsvc(OssVO vo) {
        try {
            Complaint complaint = JSON.toJavaObject(vo.getVpncomplaintAdd(), Complaint.class);
            Integer count = 0;
            if (Xtool.isNotNull(complaint.getUserId())&&Xtool.isNotNull(complaint.getContent())&&Xtool.isNotNull(complaint.getPhone())) {
                count = complaintMapper.insertSelective(complaint);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, "必填项不能"+Constants.NO_NULL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp vpncomplaintDelsvc(OssVO vo) {
        try {
            Complaint complaint = JSON.toJavaObject(vo.getVpncomplaintDel(), Complaint.class);
            Integer count = 0;
            if (Xtool.isNotNull(complaint.getComId())) {
                count = complaintMapper.deleteByPrimaryKey(complaint.getComId());
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
