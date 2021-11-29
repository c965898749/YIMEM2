package com.czx.yimem.service.admin.impl;

import com.czx.yimem.dao.LsImgMapper;
import com.czx.yimem.dao.SlideshowMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.JobExp;
import com.czx.yimem.entity.Slideshow;
import com.czx.yimem.entity.requestbean.ResultBeanObj;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.admin.SlideshowService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.UploadUtil;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Service
public class SlideshowServiceImpl implements SlideshowService {
    @Resource
    private SlideshowMapper slideshowMapper;
    @Resource
    private LsImgMapper lsImgMapper;

    @Override
    public int deleteByPrimaryKey(Integer ssId) {
        return 0;
    }

    @Override
    public int insert(Slideshow record) {
        return 0;
    }

    @Override
    public int insertSelective(Slideshow record) {
        return 0;
    }

    @Override
    public Slideshow selectByPrimaryKey(Integer ssId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Slideshow record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Slideshow record) {
        return 0;
    }

    @Override
    public BaseResp create(OssVO vo) {
        try {
            Slideshow slideshow = JSON.toJavaObject(vo.getVpnslideshowGet(), Slideshow.class);
            if (Xtool.isNull((slideshow.getToUser()))) {
                return new BaseResp(Constants.FAIL_CODE, "toUser:" + Constants.NO_NULL);
            }
            if (Xtool.isNull(slideshow.getPic())) {
                return new BaseResp(Constants.FAIL_CODE, "图片" + Constants.NO_NULL);
            }
            slideshow.getPic().forEach(x -> {
                Integer id = lsImgMapper.selectByurl(x.getShortUrl());
                if (Xtool.isNull(id)){
                    Slideshow sl = new Slideshow();
                    sl.setUrl(x.getShortUrl());
                    sl.setShortUrl(x.getShortUrl());
                    sl.setToUser(slideshow.getToUser());
                    lsImgMapper.deleteByPrimaryKey(id);
                    slideshowMapper.insertSelective(sl);
                }
            });
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp del(OssVO vo) {
        try {
            Slideshow slideshow = JSON.toJavaObject(vo.getVpnslideshowGet(), Slideshow.class);
            Integer count = 0;
            if (Xtool.isNotNull(slideshow.getSsId())) {
                count = slideshowMapper.deleteByPrimaryKey(slideshow.getSsId());
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp get(OssVO vo) {
        try {
//            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(1, 9999);
            Slideshow slideshow = JSON.toJavaObject(vo.getVpnslideshowGet(), Slideshow.class);
            List<Slideshow> list = slideshowMapper.findbytoUser(slideshow);
            Page<Slideshow> jobPage = (Page<Slideshow>) list;
            Map map = new HashMap();
            map.put("vpnslideshowGetResp", list);
            map.put("totalCount", jobPage.getTotal());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
