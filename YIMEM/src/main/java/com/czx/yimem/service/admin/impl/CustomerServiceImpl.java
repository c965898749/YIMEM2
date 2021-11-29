package com.czx.yimem.service.admin.impl;

import com.czx.yimem.dao.CustomerMapper;
import com.czx.yimem.dto.CustomerVO;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.Beginner;
import com.czx.yimem.entity.Customer;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.admin.CustomerService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerMapper customerMapper;

    @Override
    public int deleteByPrimaryKey(Integer cusId) {
        return 0;
    }

    @Override
    public int insert(Customer record) {
        return 0;
    }

    @Override
    public int insertSelective(Customer record) {
        return 0;
    }

    @Override
    public Customer selectByPrimaryKey(Integer cusId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Customer record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Customer record) {
        return 0;
    }

    @Override
    public BaseResp customerget(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            Customer customer = JSON.toJavaObject(vo.getVpncustomerGet(), Customer.class);
            List<Customer> list = customerMapper.findBycustomer(customer);
            Page<Customer> jobPage = (Page<Customer>) list;
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
    public BaseResp customeradd(OssVO vo) {
        try {
            CustomerVO customerVO = JSON.toJavaObject(vo.getVpncustomerAdd(), CustomerVO.class);
            Customer customer=new Customer();
            BeanUtils.copyProperties(customerVO,customer);
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            customer.setStartTime(df.parse(customerVO.getStartTime()));
            customer.setEndTime(df.parse(customerVO.getEndTime()));
            Integer count = 0;
            if (Xtool.isNotNull(customer.getToUser())||Xtool.isNotNull(customer.getPhone())) {
                count = customerMapper.insertSelective(customer);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, "必填项不能"+Constants.NO_NULL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp customerdel(OssVO vo) {
        try {
            Customer customer = JSON.toJavaObject(vo.getVpncustomerDel(), Customer.class);
            Integer count = 0;
            if (Xtool.isNotNull(customer.getCusId())) {
                count = customerMapper.deleteByPrimaryKey(customer.getCusId());
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp customerupdate(OssVO vo) {
        try {
            CustomerVO customerVO = JSON.toJavaObject(vo.getVpncustomerUpdate(), CustomerVO.class);
            Integer count = 0;
            if (Xtool.isNotNull(customerVO.getCusId())) {
                Customer customer=new Customer();
                BeanUtils.copyProperties(customerVO,customer);
                SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                if (Xtool.isNotNull(customerVO.getStartTime())){
                    customer.setStartTime(df.parse(customerVO.getStartTime()));
                }
                if (Xtool.isNotNull(customerVO.getEndTime())){
                    customer.setEndTime(df.parse(customerVO.getEndTime()));
                }
                count = customerMapper.updateByPrimaryKeySelective(customer);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
