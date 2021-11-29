package com.czx.yimem.service.impl;

import com.czx.yimem.dao.MessageMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.JobExp;
import com.czx.yimem.entity.Message;
import com.czx.yimem.entity.ProjectExp;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.MessageService;
import com.czx.yimem.utils.Constants;
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
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageMapper messageMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Message record) {
        return 0;
    }

    @Override
    public int insertSelective(Message record) {
        return 0;
    }

    @Override
    public Message selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Message record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Message record) {
        return 0;
    }

    @Override
    public BaseResp findBymessage(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            Message message = JSON.toJavaObject(vo.getVpnmessageGet(), Message.class);
            List<Message> list = messageMapper.findBymessage(message);
            Page<Message> jobPage = (Page<Message>) list;
            Map map = new HashMap();
            map.put("vpnmessageGetResp", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp addBymessage(OssVO vo) {
        try {
            Message message = JSON.toJavaObject(vo.getVpnmessageAdd(), Message.class);
            Integer count = 0;
            if (Xtool.isNotNull(message.getUserId()) && Xtool.isNotNull(message.getToUserId())) {
                if (message.getToUserId() != message.getUserId()) {
                    count = messageMapper.insertSelective(message);
                } else {
                    return new BaseResp(Constants.FAIL_CODE, Constants.U_to_U);
                }
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp delBymessage(OssVO vo) {
        try {
            Message message = JSON.toJavaObject(vo.getVpnmessageDel(), Message.class);
            Integer count = 0;
            if (Xtool.isNotNull(message.getId())) {
                count = messageMapper.deleteByPrimaryKey(message.getId());
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp udateBymessage(OssVO vo) {
        try {
            Message message = JSON.toJavaObject(vo.getVpnmessageUpdate(), Message.class);
            Integer count = 0;
            if (Xtool.isNotNull(message.getId()) && Xtool.isNotNull(message.getToUserId())) {
                count = messageMapper.updateByPrimaryKeySelective(message);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
