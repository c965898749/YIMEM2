package com.czx.yimem.service.admin.impl;

import com.czx.yimem.dao.ProtocolMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.dto.TouserVO;
import com.czx.yimem.entity.Beginner;
import com.czx.yimem.entity.Protocol;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.admin.ProtocolService;
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
public class ProtocolServiceImpl implements ProtocolService {
    @Resource
    private ProtocolMapper protocolMapper;

    @Override
    public int deleteByPrimaryKey(Integer prId) {
        return 0;
    }

    @Override
    public int insert(Protocol record) {
        return 0;
    }

    @Override
    public int insertSelective(Protocol record) {
        return 0;
    }

    @Override
    public Protocol selectByPrimaryKey(Integer prId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Protocol record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Protocol record) {
        return 0;
    }

    @Override
    public BaseResp protocolget(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            Protocol protocol = JSON.toJavaObject(vo.getVpnprotocolGet(), Protocol.class);
            List<Protocol> list = protocolMapper.findByprotocol(protocol);
            Page<Protocol> jobPage = (Page<Protocol>) list;
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
    public BaseResp vpnprotocolGet(TouserVO touser) {
        try {
            if (touser==null||Xtool.isNull(touser.getToUser())){
                return new BaseResp(Constants.FAIL_CODE, Constants.NO_STIPULATE);
            }
            Protocol protocol = protocolMapper.selectBytouser(touser.getToUser());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE,protocol);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp protocolgetByeId(Integer id) {
        try {
            Protocol protocol = protocolMapper.selectByPrimaryKey(id);
            Map map = new HashMap();
            map.put("vpnprotocolGetResp", protocol);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp protocoladd(OssVO vo) {
        try {
            Protocol protocol = JSON.toJavaObject(vo.getVpnprotocolAdd(), Protocol.class);
            Integer count = 0;
            if (Xtool.isNotNull(protocol.getToUser())&&Xtool.isNotNull(protocol.getTitle())&&Xtool.isNotNull(protocol.getContent())) {
                count = protocolMapper.insertSelective(protocol);
                if ("1".equals(protocol.getState())&&count>0){
                    protocolMapper.updateState(protocol);
                }
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, "必填项不能"+Constants.NO_NULL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp protocolupdate(OssVO vo) {
        try {
            Protocol protocol = JSON.toJavaObject(vo.getVpnprotocolUpdate(), Protocol.class);
            Integer count = 0;
            if (Xtool.isNotNull(protocol.getPrId())) {
                if ("1".equals(protocol.getState())){
                    protocolMapper.updateState(protocol);
                }
                count = protocolMapper.updateByPrimaryKeySelective(protocol);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp Protocoldel(OssVO vo) {
        try {
            Protocol protocol = JSON.toJavaObject(vo.getVpnprotocolDel(), Protocol.class);
            Integer count = 0;
            if (Xtool.isNotNull(protocol.getPrId())) {
                count = protocolMapper.deleteByPrimaryKey(protocol.getPrId());
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public void updatefindTimeUp() {
//        protocolMapper.updatefindTimeUp();
        List<Integer> list=protocolMapper.findTimeUpid();
        if (Xtool.isNotNull(list)){
            Protocol protocol=new Protocol();
            protocol.setPrId(list.get(0));
            protocolMapper.updateState(protocol);
            protocolMapper.updatefindTimeUp(list.get(0));
        }
    }
}
