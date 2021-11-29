package com.czx.yimem.service.financial.impl;

import com.czx.yimem.dao.CompanyBillMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.CompanyBill;
import com.czx.yimem.entity.CompanyBillDetail;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.financial.CompanyBillDetailService;
import com.czx.yimem.service.financial.CompanyBillService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/13 0013 10:06
 */
@Service
public class CompanyBillServiceImpl implements CompanyBillService {
    @Resource
    private CompanyBillMapper companyBillMapper;
    @Autowired
    private CompanyBillService companyBillService;
    @Autowired
    private CompanyBillDetailService companyBillDetailService;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(CompanyBill record) {
        return 0;
    }

    @Transactional
    @Override
    public int insertSelective(CompanyBill record) {
        return companyBillMapper.insertSelective(record);
    }


    @Override
    public CompanyBill selectByPrimaryKey2(Integer id) {
        return companyBillMapper.selectByPrimaryKey2(id);
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(CompanyBill record) {
        return companyBillMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CompanyBill record) {
        return 0;
    }

    @Override
    public BaseResp vpnCompanyBillGetsvc(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            CompanyBill companyBill = JSON.toJavaObject(vo.getVpnCompanyBillGet(), CompanyBill.class);
            List<CompanyBill> list = companyBillMapper.findBycompanyBill(companyBill);
            Page<CompanyBill> jobPage = (Page<CompanyBill>) list;
            Map map = new HashMap();
            map.put("vpnCompanyBillGetResp", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp vpnCompanyBillUpdatesvc(OssVO vo) {
        try {
            CompanyBill companyBill = JSON.toJavaObject(vo.getVpnCompanyBillUpdate(), CompanyBill.class);
            Integer count = 0;
            if (Xtool.isNotNull(companyBill.getId())) {

               //查看是否已收完
                CompanyBill bill=companyBillService.selectByPrimaryKey(companyBill.getId());
                if (bill==null){
                  return   new BaseResp(Constants.FAIL_CODE, Constants.FAIL,"该公司无账单");
                }
                if (bill.getSum().compareTo(companyBill.getPaid())<0){
                    return new BaseResp(Constants.FAIL_CODE,"金额"+Constants.NO_STIPULATE);
                }
                //TODO 进行流水核销
                List<CompanyBillDetail> list=companyBillDetailService.findBycompanyId(bill.getCompanyId());
                BigDecimal k=companyBill.getPaid();
                if (Xtool.isNotNull(list)){
                    for (CompanyBillDetail x: list) {
                        if (k.compareTo(x.getExpend())>0){
                            k=k.subtract(x.getExpend());
                            x.setExpend(new BigDecimal(0));
                            companyBillDetailService.updateByPrimaryKeySelective(x);
                        }else {
                            x.setExpend(x.getExpend().subtract(k));
                            companyBillDetailService.updateByPrimaryKeySelective(x);
                            break;
                        }
                    }
                    bill.setSum(bill.getSum().subtract(companyBill.getPaid()));
                    bill.setPaid(bill.getPaid().add(companyBill.getPaid()));
                    count = companyBillService.updateByPrimaryKeySelective(bill);
                }
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public List<CompanyBill> findBycompanyBill(CompanyBill record) {
        return companyBillMapper.findBycompanyBill(record);
    }

    @Override
    public CompanyBill selectByPrimaryKey(Integer id) {
        return companyBillMapper.selectByPrimaryKey(id);
    }
}
