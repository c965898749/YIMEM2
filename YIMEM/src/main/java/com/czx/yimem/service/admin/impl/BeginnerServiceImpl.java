package com.czx.yimem.service.admin.impl;

import com.czx.yimem.dao.BeginnerMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.dto.TouserVO;
import com.czx.yimem.entity.Beginner;
import com.czx.yimem.entity.JobExp;
import com.czx.yimem.entity.Navigation;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.admin.BeginnerService;
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
public class BeginnerServiceImpl implements BeginnerService {

    @Resource
    private BeginnerMapper beginnerMapper;
    @Override
    public int deleteByPrimaryKey(Integer beId) {
        return 0;
    }

    @Override
    public int insert(Beginner record) {
        return 0;
    }

    @Override
    public int insertSelective(Beginner record) {
        return 0;
    }

    @Override
    public Beginner selectByPrimaryKey(Integer beId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Beginner record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Beginner record) {
        return 0;
    }

    @Override
    public BaseResp beginnerGet(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            Beginner beginner = JSON.toJavaObject(vo.getVpnbeginnerGet(), Beginner.class);
            List<Beginner> list = beginnerMapper.findBybeginner(beginner);
            Page<Beginner> jobPage = (Page<Beginner>) list;
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
    public BaseResp vpnbeginnerGet(TouserVO touser) {
        try {
            if (touser==null||Xtool.isNull(touser.getToUser())){
                return new BaseResp(Constants.FAIL_CODE, Constants.NO_STIPULATE);
            }
            Beginner beginner = beginnerMapper.selectBytouser(touser.getToUser());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE,beginner);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp beginnerGetbyid(Integer id) {
        try {
            Beginner beginner = beginnerMapper.selectByPrimaryKey(id);
            // 标签过滤String content = "待处理富文本内容"; Document document = Jsoup.parse(content);
            // 标签过滤
            Map map = new HashMap();
            map.put("vpnbeginnerGetResp", beginner);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp beginnerAdd(OssVO vo) {
        try {
            Beginner beginner = JSON.toJavaObject(vo.getVpnbeginnerAdd(), Beginner.class);
            Integer count = 0;
            if (Xtool.isNotNull(beginner.getToUser())&&Xtool.isNotNull(beginner.getTitle())&&Xtool.isNotNull(beginner.getContent())) {
                count = beginnerMapper.insertSelective(beginner);
                if ("1".equals(beginner.getState())){
                    beginnerMapper.updateState(beginner);
                }
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, "必填项不能"+Constants.NO_NULL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp beginnerupdate(OssVO vo) {
        try {
            Beginner beginner = JSON.toJavaObject(vo.getVpnbeginnerUpdate(), Beginner.class);
            Integer count = 0;
            if (Xtool.isNotNull(beginner.getBeId())) {
                if ("1".equals(beginner.getState())){
                    beginnerMapper.updateState(beginner);
                }
                count = beginnerMapper.updateByPrimaryKeySelective(beginner);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public void updatefindTimeUp() {
//         beginnerMapper.updatefindTimeUp();
        List<Integer> list=beginnerMapper.findTimeUpid();
        if (Xtool.isNotNull(list)){
            Beginner beginner=new Beginner();
            beginner.setBeId(list.get(0));
            beginnerMapper.updateState(beginner);
            beginnerMapper.updatefindTimeUp(list.get(0));
        }
    }

    @Override
    public BaseResp beginnerdel(OssVO vo) {
        try {
            Beginner beginner = JSON.toJavaObject(vo.getVpnbeginnerDel(), Beginner.class);
            Integer count = 0;
            if (Xtool.isNotNull(beginner.getBeId())) {
                count = beginnerMapper.deleteByPrimaryKey(beginner.getBeId());
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
