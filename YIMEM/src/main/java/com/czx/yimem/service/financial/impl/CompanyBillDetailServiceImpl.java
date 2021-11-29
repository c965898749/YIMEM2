package com.czx.yimem.service.financial.impl;

import com.czx.yimem.dao.CompanyBillDetailMapper;
import com.czx.yimem.dao.CompanyBillMapper;
import com.czx.yimem.dao.UserMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.CompanyBill;
import com.czx.yimem.entity.CompanyBillDetail;
import com.czx.yimem.entity.JobExp;
import com.czx.yimem.entity.User;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.financial.CompanyBillDetailService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/13 0013 10:09
 */
@Service
public class CompanyBillDetailServiceImpl implements CompanyBillDetailService {
    @Resource
    private CompanyBillDetailMapper companyBillDetailMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private CompanyBillMapper companyBillMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(CompanyBillDetail record) {
        return 0;
    }

    @Transactional
    @Override
    public int insertSelective(CompanyBillDetail record) {
        return companyBillDetailMapper.insertSelective(record);
    }

    @Override
    public CompanyBillDetail selectByPrimaryKey(Integer id) {
        return null;
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(CompanyBillDetail record) {
        return companyBillDetailMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CompanyBillDetail record) {
        return 0;
    }

    @Override
    public BaseResp vpnCompanyBillDetailGetsvc(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            CompanyBillDetail companyBillDetail = JSON.toJavaObject(vo.getVpnCompanyBillDetailGet(), CompanyBillDetail.class);
            if (Xtool.isNull(companyBillDetail.getId())){
                return new BaseResp(Constants.FAIL_CODE,"所传参数"+Constants.NO_STIPULATE);
            }
            CompanyBill bill=companyBillMapper.selectByPrimaryKey(companyBillDetail.getId());
            List<CompanyBillDetail> list=new ArrayList<>();
            if (Xtool.isNotNull(companyBillDetail.getId())){
                companyBillDetail.setCompanyId(bill.getCompanyId());
                PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
                list = companyBillDetailMapper.findBycompanyBillDetail(companyBillDetail);
                list.forEach(x->{
                    User user=userMapper.findByuserId(x.getUserId());
                    x.setUser(user);
                });
            }
            Page<CompanyBillDetail> jobPage = (Page<CompanyBillDetail>) list;
            Map map = new HashMap();
            map.put("vpnCompanyBillDetailGetResp", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Transactional
    @Override
    public List<CompanyBillDetail> findBycompanyId(Integer companyId) {
        return companyBillDetailMapper.findBycompanyId(companyId);
    }
}
