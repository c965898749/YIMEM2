package com.czx.yimem.service.impl;

import com.czx.yimem.dao.CompanyMapper;
import com.czx.yimem.dao.JobMapper;
import com.czx.yimem.dao.NewsMapper;
import com.czx.yimem.dao.UserMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.Company;
import com.czx.yimem.entity.News;
import com.czx.yimem.entity.User;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.CompanyService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.PhoneUtil;
import com.czx.yimem.utils.TokenUtil;
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
public class CompanyServiceImpl implements CompanyService {
    @Resource
    private CompanyMapper companyMapper;
    @Resource
    private JobMapper jobMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private NewsMapper newsMapper;

    @Override
    public int deleteByPrimaryKey(Integer companyId) {
        return 0;
    }

    @Override
    public int insert(Company record) {
        return 0;
    }

    @Override
    public int insertSelective(Company record) {
        return 0;
    }

    @Override
    public int selectisAgree(Integer companyId) {
        return companyMapper.selectisAgree(companyId);
    }

    @Override
    public Company selectByPrimaryKey(Integer companyId) {
        return companyMapper.selectByPrimaryKey(companyId);
    }

    @Override
    public int updateByPrimaryKeySelective(Company record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Company record) {
        return 0;
    }

    @Override
    public BaseResp findBycompany(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            Integer page=1;
            Integer pageSize=100;
            if (pageVO!=null){
                page=pageVO.getPage();
                pageSize=pageVO.getPageSize();
            }
            PageHelper.startPage(page,pageSize );
            Company company = JSON.toJavaObject(vo.getVpncompanyGet(), Company.class);
            company.setState("2");
            if (Xtool.isNotNull(company.getTag())){
                if (company.getTag()==1){
                    company.setState("");
                }
                if (company.getTag()==2){
                  company.setState("1");
                }
                if (company.getTag()==3){
                    company.setState("2");
                }

            }
            List<Company> list = companyMapper.findBycompany(company);
            Page<Company> jobPage = (Page<Company>) list;
            Map map = new HashMap();
            map.put("vpncompanyGetResp", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp addBycompany(OssVO vo) {
        try {
            Company company = JSON.toJavaObject(vo.getVpncompanyAdd(), Company.class);
            //验证手机是否合规
            if (!PhoneUtil.regular(company.getPhone())){
                return new BaseResp(Constants.FAIL_CODE,"手机号"+Constants.NO_STIPULATE);
            }
            Integer count = 0;
            if (Xtool.isNotNull(company.getPhone()) && Xtool.isNotNull(company.getName())) {
//                手机号是公司和小程的唯一绑定
                if (Xtool.isNull(companyMapper.findBycompany(new Company(company.getPhone())))) {
                    count = companyMapper.insertSelective(company);
                } else {
                    return new BaseResp(Constants.FAIL_CODE, Constants.YE_FOUND_USER);
                }
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public Integer companydeliver(Integer userId,Integer id) {
        return companyMapper.companydeliver(userId,id);
    }

    @Override
    public BaseResp updateBycompany(OssVO vo) {
        try {
            Company company = JSON.toJavaObject(vo.getVpncompanyUpdate(), Company.class);
            Integer count = 0;
            if (Xtool.isNotNull(company.getCompanyId())) {
                Integer userid=companyMapper.finduserId(company.getCompanyId());
                if (company.getIsEntrust()!=null&&Xtool.isNotNull(userid)){
                    News news = new News();
                    news.setToUserId(userid);
                    if(company.getIsEntrust()){
                        news.setTitle("您的委托面试申请已通过");
                        news.setContent("平台将会筛选优质求职者资源，请关注后续推送消息");
                    }else {
                        news.setTitle("平台已取消您的委托面试服务");
                        news.setContent("感谢合作");
                    }
                    news.setIdentity(3);
                    news.setNewType(1);//系统通知
                    newsMapper.insertSelective(news);
                }
//                验证微信只能绑一家公司
                count = companyMapper.updateByPrimaryKeySelective(company);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp delBycompany(OssVO vo) {
        try {
            Company company = JSON.toJavaObject(vo.getVpncompanyDel(), Company.class);
            Integer count = 0;
            if (Xtool.isNotNull(company.getCompanyId())) {
//                验证微信只能绑一家公司
                count = companyMapper.deleteByPrimaryKey(company.getCompanyId());
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp findBycompanyid(Integer id) {
        try {
            Company list = companyMapper.selectByPrimaryKey(id);
            Map map = new HashMap();
            map.put("vpncompanyGetResp", list);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp updateIsread(Integer id) {
        jobMapper.updateIsread(id);
        return new BaseResp(Constants.SUCCEE_CODE,Constants.SUCCEE);
    }

    @Override
    public BaseResp findBycompanyPhone(String phone) {
        try {
            if (Xtool.isNotNull(phone)){
                User user= userMapper.findphone2(phone);
                if (user!=null&&TokenUtil.getTokenUserId().equals(user.getUserId()+"")){
                    List<Company> list = companyMapper.selectByPrimaryKey2(phone);
                    Map map = new HashMap();
                    if (Xtool.isNotNull(list)){
                        map.put("vpncompanyGetResp", list.get(0));
                    }else {
                        map.put("vpncompanyGetResp", "");
                    }
                    return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
                }
            }
            return new BaseResp(Constants.FAIL_CODE,Constants.NO_AUTH);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public Integer findByUtoU(Integer userId, Integer comuserId) {
        return companyMapper.findByUtoU(userId,comuserId);
    }

    @Override
    public Integer finduserId(Integer companyId) {
        return companyMapper.finduserId(companyId);
    }
}
