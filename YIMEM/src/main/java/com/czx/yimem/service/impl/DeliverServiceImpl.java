package com.czx.yimem.service.impl;

import com.czx.yimem.dao.*;
import com.czx.yimem.dto.DeliverVO;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.dto.UserParam;
import com.czx.yimem.entity.*;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.DeliverDetailService;
import com.czx.yimem.service.DeliverService;
import com.czx.yimem.service.JobService;
import com.czx.yimem.service.RecommendService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.TokenUtil;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
public class DeliverServiceImpl implements DeliverService {
    @Resource
    private DeliverMapper deliverMapper;
    @Autowired
    private DeliverDetailService deliverDetailService;
    @Resource
    private DeliverDetailMapper deliverDetailMapper;
    @Resource
    private RecommendService recommendService;
    @Resource
    private JobMapper jobMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private JobWantedMapper jobWantedMapper;
    @Resource
    private JobExpMapper jobExpMapper;
    @Resource
    private EducationExpMapper educationExpMapper;
    @Autowired
    private JobService jobService;
    @Resource
    private NewsMapper newsMapper;
    @Resource
    private IncomeDetailMapper incomeDetailMapper;

    @Override
    public int deleteByPrimaryKey(Integer devId) {
        return 0;
    }

    @Override
    public int insert(Deliver record) {
        return 0;
    }

    @Override
    public int insertSelective(Deliver record) {
        return 0;
    }

    @Override
    public Deliver selectByPrimaryKey(Integer devId) {
        return deliverMapper.selectByPrimaryKey(devId);
    }

    @Override
    public int updateByPrimaryKeySelective(Deliver record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Deliver record) {
        return 0;
    }

    @Override
    public BaseResp findBydeliver(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            Deliver deliver = JSON.toJavaObject(vo.getVpndeliverGet(), Deliver.class);
            List<Deliver> list = deliverMapper.findBydeliver(deliver);
            Page<Deliver> jobPage = (Page<Deliver>) list;
            list.forEach(x -> {
                if ("3".equals(x.getState())) {
                    DeliverDetail deliverDetail = deliverDetailMapper.selectBydevId(x.getDevId());
                    x.setDeliverDetail(deliverDetail);
                }
                if (Xtool.isNotNull(x.getUserId())) {
                    User user = userMapper.findByuserId(Integer.parseInt(x.getUserId()));
                    x.setUser(user);
                }
                Job job = jobService.selectById(x.getJobId());
                if (job != null) {
                    job.setPhone(jobService.selectphone(x.getJobId()));
                }
                x.setJob(job);
            });
            Map map = new HashMap();
            map.put("vpndeliverGetResp", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp findBydeliver5(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            Deliver deliver = JSON.toJavaObject(vo.getVpndeliverGet(), Deliver.class);
            List<Deliver> list = deliverMapper.findBydeliver6(deliver);
            Page<Deliver> jobPage = (Page<Deliver>) list;
            list.forEach(x -> {
                if ("3".equals(x.getState())) {
                    DeliverDetail deliverDetail = deliverDetailMapper.selectBydevId(x.getDevId());
                    x.setDeliverDetail(deliverDetail);
                }
                if (Xtool.isNotNull(x.getUserId())) {
                    User user = userMapper.findByuserId(Integer.parseInt(x.getUserId()));
                    x.setUser(user);
                }
                Job job = jobService.selectById(x.getJobId());
                if (job != null) {
                    job.setPhone(jobService.selectphone(x.getJobId()));
                }
                x.setJob(job);
            });
            Map map = new HashMap();
            map.put("vpndeliverGetResp", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp findBydeliver4(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            Deliver deliver = JSON.toJavaObject(vo.getVpndeliverGet(), Deliver.class);
            List<Deliver> list = new ArrayList<>();
            if ("1".equals(deliver.getState())) {
                list = deliverMapper.findBydeliver4(deliver);

            } else {
                list = deliverMapper.findBydeliver5(deliver);
            }
            Page<Deliver> jobPage = (Page<Deliver>) list;
            list.forEach(x -> {
                if ("2".equals(x.getState())) {
                    DeliverDetail detail = deliverDetailMapper.selectByPrimaryKey11(x.getDevId());
                    if (detail!=null){
                        x.setFlag(detail.getFlag());
                    }
                }
                if ("3".equals(x.getState())) {
                    DeliverDetail detail = deliverDetailMapper.selectByPrimaryKey11(x.getDevId());
                    if (detail!=null){
                        x.setDeliverDetail(detail);
                    }
                }
                if (Xtool.isNotNull(x.getUserId())) {
                    User user = userMapper.findByuserId(Integer.parseInt(x.getUserId()));
                    x.setUser(user);
                }
                Job job = jobService.selectById(x.getJobId());
                if (job != null) {
                    job.setPhone(jobService.selectphone(x.getJobId()));
                }
                x.setJob(job);
            });
            Map map = new HashMap();
            map.put("vpndeliverGetResp", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp findBydeliver2(Deliver deliver, PageVO pageVO) {
        PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
        List<DeliverVO> list = deliverMapper.findBydeliver2(deliver);
        Page<DeliverVO> jobPage = (Page<DeliverVO>) list;
        Map map = new HashMap();
        map.put("vpndeliverGetResp", list);
        map.put("totalElements", jobPage.getTotal());
        map.put("totalPages", jobPage.getPages());
        return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
    }

    @Override
    public BaseResp findBydeliver3(Deliver deliver, PageVO pageVO) {
        PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
        List<DeliverVO> list = deliverMapper.findBydeliver3(deliver);
        Page<DeliverVO> jobPage = (Page<DeliverVO>) list;
        Map map = new HashMap();
        map.put("vpndeliverGetResp", list);
        map.put("totalElements", jobPage.getTotal());
        map.put("totalPages", jobPage.getPages());
        return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
    }


    @Override
    public BaseResp vpndeliverGetByidsvc(Integer id) {
        try {
            List<DeliverDetail> x = deliverDetailService.findBydevId(id);
            Map map = new HashMap();
            map.put("vpndeliverGetResp", x);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp vpndeliverGetByidsvc2(Recommend recommend) {
        try {
            List<DeliverDetail> x = deliverDetailService.findBydevId(recommend.getDelId());
            if ("10".equals(recommend.getState())){
                Integer count=0;
                IncomeDetail incomeDetail=incomeDetailMapper.selectBydelid(recommend.getDelId());
                for (int i=0;i<x.size();i++){
                    count=i;
                    if ("7".equals(x.get(i).getState())){
                      break;
                    }
                }
                DeliverDetail deliverDetail=new DeliverDetail();
                deliverDetail.setCreateTime(incomeDetail.getCreatedTime());
                deliverDetail.setState("10");
                x.add(count,deliverDetail);
            }
            Map map = new HashMap();
            map.put("vpndeliverGetResp", x);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }


    /**
     * 投递简历
     *
     * @param vo
     * @return
     */
    @Override
    @Transactional
    public BaseResp addBydeliver(OssVO vo) throws Exception{
            Deliver deliver = JSON.toJavaObject(vo.getVpndeliverAdd(), Deliver.class);
            Integer count = 0;
            if (Xtool.isNotNull(deliver.getUserId()) && Xtool.isNotNull(deliver.getJobId())) {
                //判断简历是否完善
//                //基本信息、求职意向、工作经验、教育经历必填
//                if (userMapper.findByuserCount(Integer.parseInt(deliver.getUserId())) <= 0 ||
//                        jobWantedMapper.findBywantedCount(Integer.parseInt(deliver.getUserId())) <= 0 ||
//                        jobExpMapper.findByjobExpCount(Integer.parseInt(deliver.getUserId())) <= 0 ||
//                        educationExpMapper.findByeducationExpCount(Integer.parseInt(deliver.getUserId())) <= 0) {
//                    return new BaseResp(Constants.FAIL_CODE, Constants.NO_RESUME);
//                }
                //判断简历是否是否是自己投递
                if (!TokenUtil.getTokenUserId().equals(deliver.getUserId())) {
                    return new BaseResp(Constants.FAIL_CODE, "你无权为该用户投递简历");
                }
                //判断岗位是否正在招聘
                if (jobMapper.selectjobisUse(deliver.getJobId()) <= 0) {
                    return new BaseResp(Constants.FAIL_CODE, "该岗位已停止招聘");
                }
//                判断简历重复投递
//                if (Xtool.isNotNull(deliverMapper.findBydeliver(new Deliver(deliver.getJobId(), deliver.getUserId())))) {
//                    return new BaseResp(Constants.FAIL_CODE, Constants.YE_SEND);
//                }
                if (deliverMapper.finduseidandjobid(deliver) > 0) {
                    return new BaseResp(Constants.FAIL_CODE, Constants.YE_SEND);
                }
                count = deliverMapper.insertSelective(deliver);
                if (count > 0) {
                    String jobName = deliverMapper.getjobName(deliver.getDevId());
                    News wss = new News();
                    User user = userMapper.selectByPrimaryKey(Integer.parseInt(deliver.getUserId()));
                    Integer userid = deliverDetailMapper.findcompanyuserid(deliver.getDevId());
                    if (Xtool.isNotNull(userid)) {
                        wss.setTitle("你发布的岗位已有人投递");
                        wss.setToUserId(userid);
                        wss.setContent(user.getUserName() + " 该求职者投递了你发布的【" + jobName + "】");
                        wss.setIdentity(3);
                        wss.setNewType(1);//系统消息
                        newsMapper.insertSelective(wss);
                    }
                    deliverDetailMapper.insertSelective(new DeliverDetail(deliver.getDevId()));
//                    判断是否携带分销参数,插入分销数据
                    if (Xtool.isNotNull(deliver.getIsRecommend()) && Xtool.isNotNull(deliver.getReUserId())) {
                        Recommend recommend = new Recommend();
                        recommend.setJobId(deliver.getJobId());
                        recommend.setReUserId(Integer.parseInt(deliver.getUserId()));
                        recommend.setMoney(jobMapper.selectbrokerage(deliver.getJobId()));
                        recommend.setUserId(deliver.getReUserId());
                        recommend.setDelId(deliver.getDevId());
                        Integer c = recommendService.insertSelective(recommend);
                        if (c == 1) {
                            News news = new News();
                            news.setToUserId(deliver.getReUserId());
                            news.setTitle("你分享的岗位已有人投递");
                            news.setContent(user.getUserName() + " 该求职者投递了你分享的【" + jobName + "】");
                            news.setIdentity(2);
                            news.setNewType(1);//系统消息
                            newsMapper.insertSelective(news);
                        }
                    }
                    jobMapper.updateIsread2(deliver.getJobId());
                }
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);

    }

    @Override
    public BaseResp updateBydeliver(OssVO vo) {
        try {
            Deliver deliver = JSON.toJavaObject(vo.getVpndeliverUpdate(), Deliver.class);
            Integer count = 0;
            if (Xtool.isNotNull(deliver.getDevId())) {
                count = deliverMapper.updateByPrimaryKeySelective(deliver);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp delBydeliver(OssVO vo) {
        try {
            Deliver deliver = JSON.toJavaObject(vo.getVpndeliverDel(), Deliver.class);
            Integer count = 0;
            if (Xtool.isNotNull(deliver.getDevId())) {
                count = deliverMapper.deleteByPrimaryKey(deliver.getDevId());
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp vpnrecommendUserDetailGetsvc(Integer userId, Integer id) {
        UserParam param = deliverMapper.vpnrecommendUserDetailGetsvc(userId, id);
        return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, param);
    }

    @Override
    public String selectState(Integer id) {
        return deliverMapper.selectState(id);
    }


}
