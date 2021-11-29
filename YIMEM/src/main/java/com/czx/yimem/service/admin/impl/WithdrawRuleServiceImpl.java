package com.czx.yimem.service.admin.impl;

import com.czx.yimem.dao.WithdrawRuleMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.Beginner;
import com.czx.yimem.entity.Protocol;
import com.czx.yimem.entity.WithdrawRule;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.admin.WithdrawRuleService;
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
public class WithdrawRuleServiceImpl implements WithdrawRuleService {
    @Resource
    private WithdrawRuleMapper withdrawRuleMapper;
    @Override
    public int deleteByPrimaryKey(Integer wiId) {
        return 0;
    }

    @Override
    public int insert(WithdrawRule record) {
        return 0;
    }

    @Override
    public int insertSelective(WithdrawRule record) {
        return 0;
    }

    @Override
    public WithdrawRule selectByPrimaryKey(Integer wiId) {
        return null;
    }

    @Override
    public BaseResp vpnwithdrawRuleGet() {
        try {
            WithdrawRule protocol = withdrawRuleMapper.selectBytouser();
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE,protocol);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(WithdrawRule record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(WithdrawRule record) {
        return 0;
    }

    @Override
    public BaseResp withdrawRuleget(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            WithdrawRule beginner = JSON.toJavaObject(vo.getVpnwithdrawRuleGet(), WithdrawRule.class);
            List<WithdrawRule> list = withdrawRuleMapper.findByWithdrawRule(beginner);
            Page<WithdrawRule> jobPage = (Page<WithdrawRule>) list;
            // 标签过滤String content = "待处理富文本内容"; Document document = Jsoup.parse(content);
            // 标签过滤
            if (Xtool.isNotNull(list)){
                list.forEach(x->{
                    String content = x.getContent();
                    Document document = Jsoup.parse(content);
                    content = document.text();
                    // 截取前100位
                    Integer contentLength = content.length();
                    content = content.substring(0, contentLength >= 100 ? 100 : contentLength);
                    x.setContent(content);
                });
            }
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
    public BaseResp withdrawRuleadd(OssVO vo) {
        try {
            WithdrawRule withdrawRule = JSON.toJavaObject(vo.getVpnwithdrawRuleAdd(), WithdrawRule.class);
            Integer count = 0;
            if (Xtool.isNotNull(withdrawRule.getToUser())&&Xtool.isNotNull(withdrawRule.getTitle())&&Xtool.isNotNull(withdrawRule.getContent())) {
                if ("1".equals(withdrawRule.getState())){
                    withdrawRuleMapper.updateState();
                }
                count = withdrawRuleMapper.insertSelective(withdrawRule);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, "必填项不能"+Constants.NO_NULL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp withdrawRuleupdate(OssVO vo) {
        try {
            WithdrawRule withdrawRule = JSON.toJavaObject(vo.getVpnwithdrawRuleUpdate(), WithdrawRule.class);
            Integer count = 0;
            if (Xtool.isNotNull(withdrawRule.getWiId())) {
                if ("1".equals(withdrawRule.getState())){
                    withdrawRuleMapper.updateState();
                }
                count = withdrawRuleMapper.updateByPrimaryKeySelective(withdrawRule);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp withdrawRuledel(OssVO vo) {
        try {
            WithdrawRule withdrawRule = JSON.toJavaObject(vo.getVpnwithdrawRuleDel(), WithdrawRule.class);
            Integer count = 0;
            if (Xtool.isNotNull(withdrawRule.getWiId())) {
                count = withdrawRuleMapper.deleteByPrimaryKey(withdrawRule.getWiId());
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp withdrawRulegetById(Integer id) {
        try {
            WithdrawRule list = withdrawRuleMapper.selectByPrimaryKey(id);
            Map map = new HashMap();
            map.put("vpnwithdrawRuleGetResp", list);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public void updatefindTimeUp() {
//        withdrawRuleMapper.updatefindTimeUp();
        List<Integer> list=withdrawRuleMapper.findTimeUpid();
        if (Xtool.isNotNull(list)){
            WithdrawRule withdrawRule=new WithdrawRule();
            withdrawRule.setWiId(list.get(0));
            withdrawRuleMapper.updateState();
            withdrawRuleMapper.updatefindTimeUp(list.get(0));
        }
    }


}
