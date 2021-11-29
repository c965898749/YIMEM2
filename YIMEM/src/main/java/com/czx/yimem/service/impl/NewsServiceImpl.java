package com.czx.yimem.service.impl;

import com.czx.yimem.dao.DeliverDetailMapper;
import com.czx.yimem.dao.NewsMapper;
import com.czx.yimem.dto.IdentityVO;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.DeliverDetail;
import com.czx.yimem.entity.Message;
import com.czx.yimem.entity.News;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.NewsService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.TokenUtil;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Resource
    private NewsMapper newsMapper;
    @Resource
    private DeliverDetailMapper deliverDetailMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(News record) {
        return 0;
    }

    @Override
    public int insertSelective(News record) {
        return 0;
    }

    @Override
    public News selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(News record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(News record) {
        return 0;
    }

    @Override
    public BaseResp updatesiread(OssVO vo) {
        try {
            News news = JSON.toJavaObject(vo.getVpnnewsGet(), News.class);
            newsMapper.updatesiread(news);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);
        }catch(Exception e){
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp findBynews(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            News news = JSON.toJavaObject(vo.getVpnnewsGet(), News.class);
            String userid = TokenUtil.getTokenUserId();
            if (Xtool.isNull(userid)){
                return new BaseResp(Constants.FAIL_CODE,Constants.NO_AUTH);
            }
            news.setToUserId(Integer.parseInt(userid));
            List<News> list = newsMapper.findBynews(news);

//            遍历查询需要优化
            list.forEach(x -> {
                DeliverDetail deliverDetail = deliverDetailMapper.selectByPrimaryKey2(x.getDevid());
                newsMapper.updatesiread(x);
                if (deliverDetail != null) {
                    //            判断时间是否过期,不使用定时器
                    //            isagree	String(255)	是否同意		0未选择1同意2不同意3已失效
                    if (deliverDetail.getTime() != null) {
                        if (deliverDetail.getFlag() == 0) {
                            Date now = new Date();
                            if (now.after(deliverDetail.getTime())) {
                                //                        已过期
                                deliverDetail.setFlag(3);
                                deliverDetailMapper.updateByPrimaryKeySelective(deliverDetail);
                                x.setIsagree(3);
                                newsMapper.updateByPrimaryKeySelective(x);
                            }
                        }
                    }
                }
            });
            //查询未读消息数
            Integer count=0;
            IdentityVO identityVO = new IdentityVO();

            identityVO.setUserId(news.getToUserId());
            identityVO.setIdentity(news.getIdentity());
            count=newsMapper.isnewmsg(identityVO);

            Page<News> jobPage = (Page<News>) list;
            Map map = new HashMap();
            map.put("vpnnewsGetResp", list);
            map.put("noreadcount",count);

            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp isnewmsg(IdentityVO ossVO) {
        try {
            String userId= TokenUtil.getTokenUserId();
            if (Xtool.isNull(userId)){
                return new BaseResp(Constants.FAIL_CODE,Constants.NO_AUTH);
            }
            Integer count=0;
            ossVO.setUserId(Integer.parseInt(userId));
            count=newsMapper.isnewmsg(ossVO);
            Map map=new HashMap();
            map.put("state",count);
            return new BaseResp(Constants.SUCCEE_CODE,Constants.SUCCEE,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }


    @Override
    public BaseResp addBynews(OssVO vo) {
        try {
            News news = JSON.toJavaObject(vo.getVpnnewsAdd(), News.class);
            Integer count = 0;
            if (Xtool.isNotNull(news.getUserId()) && Xtool.isNotNull(news.getToUserId())) {
                if (news.getToUserId() != news.getUserId()) {
                    count = newsMapper.insertSelective(news);
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
    public BaseResp updateBynews(OssVO vo) {
        try {
            News news = JSON.toJavaObject(vo.getVpnnewsUpdate(), News.class);
            Integer count = 0;
            if (Xtool.isNotNull(news.getId()) && Xtool.isNotNull(news.getToUserId())) {
                count = newsMapper.updateByPrimaryKeySelective(news);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp delBynews(OssVO vo) {
        try {
            News news = JSON.toJavaObject(vo.getVpnnewsDel(), News.class);
            Integer count = 0;
            if (Xtool.isNotNull(news.getId())) {
                count = newsMapper.deleteByPrimaryKey(news.getId());
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
