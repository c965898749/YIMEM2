package com.czx.yimem.service.financial.impl;

import com.czx.yimem.dao.*;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.*;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.DeliverDetailService;
import com.czx.yimem.service.JobService;
import com.czx.yimem.service.financial.CompanyBillDetailService;
import com.czx.yimem.service.financial.CompanyBillService;
import com.czx.yimem.service.financial.IncomeDetailService;
import com.czx.yimem.service.financial.IncomeService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.XMLUtil;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/13 0013 16:37
 */
@Slf4j
@Service
public class IncomeServiceImpl implements IncomeService {
    @Resource
    private IncomeMapper incomeMapper;
    @Resource
    private RecommendMapper recommendMapper;
    @Autowired
    private IncomeDetailService incomeDetailService;
    @Autowired
    private DeliverDetailService deliverDetailService;
    @Autowired
    private CompanyBillDetailService companyBillDetailService;
    @Autowired
    private CompanyBillService companyBillService;
    @Resource
    private NewsMapper newsMapper;
    @Resource
    private CompanyMapper companyMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Income record) {
        return 0;
    }

    @Override
    public int insertSelective(Income record) {
        return 0;
    }

    @Override
    public Income selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Income record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Income record) {
        return 0;
    }

    @Override
    public BaseResp vpnIncomeGetsvc(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            Income income = JSON.toJavaObject(vo.getVpnIncomeGet(), Income.class);
            List<Income> list = incomeMapper.findByincome(income);
            Page<Income> jobPage = (Page<Income>) list;
            Map map = new HashMap();
            map.put("vpnIncomeGetResp", list);
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
    public void updatefindTimeUp() {
        List<IncomeDetail> list = incomeDetailService.findIncomeDetail(Constants.ENTRY_TIME);
        if (Xtool.isNotNull(list)) {
            list.forEach(x -> {
//                    入账日志
                log.info("入账日志:" + x.toString());
//                    给分销者发放佣金
                incomeDetailService.insertSelective(x);
                News ws = new News();
                ws.setToUserId(x.getUserId());
                ws.setTitle("佣金通知");
                ws.setContent("您推荐的【"+x.getJobName()+"】岗位,"+x.getBrokerage()+"元佣金已到账");
                ws.setIdentity(2);
                ws.setNewType(5);//佣金通知
                newsMapper.insertSelective(ws);
                //更新分销推荐明细
                recommendMapper.updateiscount(x);
                //统计到分销佣金总表

                Income income = incomeMapper.selectByUserId(x.getUserId());
                if(income==null){
                    //需要新增
                    income = new Income();
                    income.setUserId(x.getUserId());
                    income.setUserName(x.getUserName());
                    income.setMoney(x.getBrokerage());
                    income.setPhone(x.getPhone());
                    income.setUpdateTime(new Date());
                    income.setrUserNum(1);
                    incomeMapper.insertSelective(income);
                }else{
                    income.setUpdateTime(new Date());
                    income.setrUserNum(income.getrUserNum()+1);
                    incomeMapper.updateByPrimaryKey(income);
                }
//                    给企业生成账单
                CompanyBillDetail companyBillDetail = new CompanyBillDetail();
                companyBillDetail.setUserId(x.getrUserId());
                companyBillDetail.setUserName(x.getrUserName());
                companyBillDetail.setUserPhone(x.getrPhone());
                companyBillDetail.setJobId(x.getJobId());
                companyBillDetail.setJobName(x.getJobName());
                companyBillDetail.setUserEntryTime(x.getEntryTime());
                companyBillDetail.setJobMoney(x.getMoney());
                companyBillDetail.setExpend(x.getMoney());
                companyBillDetail.setCompanyId(x.getCompanyId());
                companyBillDetailService.insertSelective(companyBillDetail);
//                    更新公司总账
                ws.setToUserId(companyMapper.finduserId(x.getCompanyId()));
                ws.setTitle("系统通知");
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
                ws.setContent("您发布的【"+x.getJobName()+"】岗位,"+x.getrUserName()+" 自 "+sdf.format(x.getEntryTime())+" 入职到今为止已满"+Constants.ENTRY_TIME+"天，按平台协议您需支付" +
                        x.getMoney()+" 元佣金");
                ws.setIdentity(3);
                ws.setNewType(1);//系统通知
                newsMapper.insertSelective(ws);
                CompanyBill companyBill = companyBillService.selectByPrimaryKey2(x.getCompanyId());
                if (companyBill != null) {
                    companyBill.setSum(companyBill.getSum().add(x.getMoney()));
                    companyBill.setMemberNum(companyBill.getMemberNum() + 1);
                    companyBill.setState("0");
                    companyBillService.updateByPrimaryKeySelective(companyBill);
                } else {
                    CompanyBill bill = new CompanyBill();
                    bill.setSum(x.getMoney());
                    bill.setMemberNum(1);
                    bill.setCompanyId(x.getCompanyId());
                    bill.setCompanyName(x.getCompanyName());
                    bill.setState("0");
                    companyBillService.insertSelective(bill);
                }
                deliverDetailService.updateisRuIncome(x.getDevId());
            });
        }


    }

    @Transactional
    @Override
    public void updatefindTimeUp2() {

        List<IncomeDetail> list = incomeDetailService.findIncomeDetail2(Constants.ENTRY_TIME);
        if (Xtool.isNotNull(list)) {
            list.forEach(x -> {
//                    给企业生成账单
                CompanyBillDetail companyBillDetail = new CompanyBillDetail();
                companyBillDetail.setUserId(x.getrUserId());
                companyBillDetail.setUserName(x.getrUserName());
                companyBillDetail.setUserPhone(x.getrPhone());
                companyBillDetail.setJobId(x.getJobId());
                companyBillDetail.setJobName(x.getJobName());
                companyBillDetail.setUserEntryTime(x.getEntryTime());
                companyBillDetail.setJobMoney(x.getMoney());
                companyBillDetail.setExpend(x.getMoney());
                companyBillDetail.setCompanyId(x.getCompanyId());
                companyBillDetailService.insertSelective(companyBillDetail);
                News ws = new News();
//                    更新公司总账
                ws.setToUserId(companyMapper.finduserId(x.getCompanyId()));
                ws.setTitle("系统通知");
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
                ws.setContent("您发布的【"+x.getJobName()+"】岗位,"+x.getrUserName()+" 自 "+sdf.format(x.getEntryTime())+" 入职到今为止已满"+Constants.ENTRY_TIME+"天，按平台协议您需支付" +
                        x.getMoney()+" 元佣金");
                ws.setIdentity(3);
                ws.setNewType(1);//系统通知
                newsMapper.insertSelective(ws);
                CompanyBill companyBill = companyBillService.selectByPrimaryKey2(x.getCompanyId());
                if (companyBill != null) {
                    companyBill.setSum(companyBill.getSum().add(x.getMoney()));
                    companyBill.setMemberNum(companyBill.getMemberNum() + 1);
                    companyBill.setState("0");
                    companyBillService.updateByPrimaryKeySelective(companyBill);
                } else {
                    CompanyBill bill = new CompanyBill();
                    bill.setSum(x.getMoney());
                    bill.setMemberNum(1);
                    bill.setCompanyId(x.getCompanyId());
                    bill.setCompanyName(x.getCompanyName());
                    bill.setState("0");
                    companyBillService.insertSelective(bill);
                }
                deliverDetailService.updateisRuIncome(x.getDevId());
            });
        }


    }
}
