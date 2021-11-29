package com.czx.yimem.service.impl;

import com.czx.yimem.dao.*;
import com.czx.yimem.dto.*;
import com.czx.yimem.entity.*;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.*;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.TokenUtil;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Service
public class DeliverDetailServiceImpl implements DeliverDetailService {
    @Resource
    private DeliverDetailMapper deliverDetailMapper;
    @Autowired
    private JobCustomLightenEnumService jobCustomLightenEnumService;
    @Autowired
    private JobLightenEnumService jobLightenEnumService;
    @Resource
    private DeliverMapper deliverMapper;
    @Resource
    private RecommendMapper recommendMapper;
    @Resource
    private JobMapper jobMapper;
    @Resource
    private NewsMapper newsMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(DeliverDetail record) {
        return 0;
    }

    @Override
    public int insertSelective(DeliverDetail record) {
        return 0;
    }

    @Override
    public DeliverDetail selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public ManshVO findmanshVO(Integer id) {
        return deliverDetailMapper.findmanshVO(id);
    }

    @Override
    public int updateByPrimaryKeySelective(DeliverDetail record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(DeliverDetail record) {
        return 0;
    }

    @Transactional
    @Override
    public Integer updateisRuIncome(Integer id) {
        return deliverDetailMapper.updateisRuIncome(id);
    }

    @Override
    public List<DeliverDetail> findBydevId(Integer devId) {
        return deliverDetailMapper.findBydevId(devId);
    }

    @Override
    public Integer findcompanyuserid(Integer devId) {
        return deliverDetailMapper.findcompanyuserid(devId);
    }

    @Override
    public Integer findUserid(Integer devId){
        return deliverDetailMapper.findUserid(devId);
    }

    @Override
    public BaseResp findBydeliverDetail(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            DeliverDetail deliverDetail = JSON.toJavaObject(vo.getVpnDeliverDetailGet(), DeliverDetail.class);
            List<DeliverDetail> list = deliverDetailMapper.findBydeliverDetail(deliverDetail);
            Page<DeliverDetail> jobPage = (Page<DeliverDetail>) list;
            Map map = new HashMap();
            map.put("vpnDeliverDetailGetResp", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    /**
     * pc简历操作
     *
     * @param
     * @return
     */
    @Override
    @Transactional
    public BaseResp addBydeliverDetail(DeliverDetail deliverDetail) throws Exception {
            //TODO 只要每次新增都会产生消息
//            DeliverDetail deliverDetail = JSON.toJavaObject(vo.getVpnDeliverDetailAdd(), DeliverDetail.class);
            Integer count = 0;
            if (Xtool.isNotNull(deliverDetail.getDevId())) {
                //再判断简历是否存在

                Deliver deliver = deliverMapper.selectByPrimaryKey(deliverDetail.getDevId());
                if (deliver != null) {
                    String jobName = deliverMapper.getjobName(deliver.getDevId());

                    //0不合适 1已投递 2已邀约3待面试4推送企业 5企业面试6已面试7已入职8已离职
                    //当不合适、推送企业、企业面试、已面试、已入职、已离职时，如果带面试3存在，删除待面试3环节
                    //验证流程跳跃
                    //当不合适时
                    String state=deliver.getState();
                    if ("0".equals(state)){
                        return new BaseResp(Constants.FAIL_CODE, "该简历已被企业拒绝，无法继续操作");
                    }
                    if ("9".equals(state)){
                        return new BaseResp(Constants.FAIL_CODE,"离职申请中请勿重复操作");
                    }
                    //当已邀约
                    if ("2".equals(state)){
                        String k1[] = { "4", "5", "6", "7", "8"};
                        List<String> c1 = new ArrayList<>();
                        c1.addAll(Arrays.asList(k1));
                        if (c1.contains(deliverDetail.getState())) {
                            return new BaseResp(Constants.FAIL_CODE, "用户还未同意面试无法进行其他操作");
                        }
                    }
                    String kk[] = {"0", "4", "5", "6", "7", "8"};
                    List<String> cc = new ArrayList<>();
                    cc.addAll(Arrays.asList(kk));
                    if (cc.contains(deliverDetail.getState())) {
                        deliverDetailMapper.deleteBystate3(deliverDetail.getDevId());
                    }
                    if ("0".equals(deliverDetail.getState())){
                        News ws = new News();
                        ws.setToUserId(Integer.parseInt(deliver.getUserId()));
                        ws.setTitle("您收到来自");
                        ws.setContent("您未通过【"+jobName+"】该岗位面试要求");
                        ws.setIdentity(1);
                        ws.setNewType(3);//面试通知
                        newsMapper.insertSelective(ws);
                        ws.setDevid(deliver.getDevId());
                        newsMapper.updatesiagree(ws);
                    }
                    if ("4".equals(deliverDetail.getState())){
                        //推送企业并把待面试变成已面试
                        DeliverDetail detail=new DeliverDetail();
                        BeanUtils.copyProperties(deliverDetail,detail);
                        detail.setState("6");
                        deliverDetailMapper.insertSelective(detail);
                    }
                    if ("7".equals(deliverDetail.getState())) {
                        if ("7".equals(deliverDetailMapper.findstate(deliverDetail.getDevId()))) {
                            return new BaseResp(Constants.FAIL_CODE,"该求职者已入职，请勿重复操作" );
                        }
                        if ("8".equals(deliverDetailMapper.findstate(deliverDetail.getDevId()))) {
                            return new BaseResp(Constants.FAIL_CODE, "该求职者已离职，请勿再次入职");
                        }
                        if (deliverDetail.getEntryTime() == null) {
                            return new BaseResp(Constants.FAIL_CODE, "入职时间" + Constants.NO_NULL);
                        }
                        News news = new News();
                        news.setToUserId(Integer.parseInt(deliver.getUserId()));
                        news.setTitle("您收到来自");
                        news.setDevid(deliverDetail.getId());
                        deliverDetailMapper.updatestate3for6(deliverDetail.getDevId());
                        deliverDetailMapper.updateisRuIncome2(deliverDetail.getDevId());
                        news.setContent("恭喜你已成功入职【" + jobName + "】该岗位");
                        news.setIdentity(1);
                        news.setNewType(4);//入职通知
                        newsMapper.insertSelective(news);
                        //其他业务逻辑
                    }
                    if ("8".equals(deliverDetail.getState())) {
                        if ("8".equals(deliverDetailMapper.findstate(deliverDetail.getDevId()))) {
                            return new BaseResp(Constants.FAIL_CODE,"该求职者已离职，请勿重复操作");
                        }
                        if (deliverDetail.getLeaveTime() == null) {
                            return new BaseResp(Constants.FAIL_CODE,"离职时间" + Constants.NO_NULL);
                        }
                        //更新不能发放佣金减少定时压力
                        deliverDetailMapper.updateisRuIncome(deliverDetail.getDevId());
                    }
                    if ("9".equals(deliverDetail.getState())) {
                        if ("7".equals(deliverDetailMapper.findstate(deliverDetail.getDevId()))) {
                            if (deliverDetail.getLeaveTime() == null || deliverDetail.getResion()==null) {
                                return new BaseResp(Constants.FAIL_CODE,"离职时间及原因" + Constants.NO_NULL);
                            }
                        }else{
                            return new BaseResp(Constants.FAIL_CODE,"非入职状态，无法离职");
                        }
                    }
                    User user = userMapper.selectByPrimaryKey(Integer.parseInt(deliver.getUserId()));
                    //判断是否携带分销参数,插入分销数据
                    if (Xtool.isNotNull(deliver.getReUserId()) && Xtool.isNotNull(deliver.getIsRecommend())&&deliver.getIsRecommend()==1) {
                        //判断分销主表存在
                        Recommend commend=new Recommend();
                        commend.setDelId(deliver.getDevId());
                        commend.setUserId(deliver.getReUserId());
                        Recommend recommend = recommendMapper.selectByDelId(commend);
                        if (recommend != null) {
                            //0不合适 1已投递 2已邀约3待面试4推送企业 5企业面试6已面试7已入职8已离职
                            // TODO 分销只取01678
                            String arr[] = {"0", "1", "6", "7", "8"};
                            List<String> list = new ArrayList<>();
                            list.addAll(Arrays.asList(arr));
                            // enter array into the list
                            if (list.contains(deliverDetail.getState())) {
                                if ("7".equals(deliverDetail.getState())){
                                    News ws = new News();
                                    ws.setToUserId(deliver.getReUserId());
                                    ws.setTitle("恭喜你推荐的求职者已成功入职");
                                    ws.setContent(user.getUserName()+" 该求职者成功入职你分享的【"+jobName+"】");
                                    ws.setIdentity(2);
                                    ws.setNewType(4);//入职通知
                                    newsMapper.insertSelective(ws);
                                }
                                if ("0".equals(deliverDetail.getState())){
                                    News ws = new News();
                                    ws.setToUserId(deliver.getReUserId());
                                    ws.setTitle("很遗憾你推荐的求职者已被拒");
                                    ws.setContent(user.getUserName()+" 该求职者未通过你分享的【"+jobName+"】岗位面试要求");
                                    ws.setIdentity(2);
                                    ws.setNewType(1);//系统通知
                                    newsMapper.insertSelective(ws);
                                }
                                recommend.setState(deliverDetail.getState());
                                recommendMapper.updateByPrimaryKeySelective(recommend);
                            }
                        }
                    }
                    //如果4推送企业消息
                    if ("4".equals(state)){
                        News wss = new News();
                        Integer userid =deliverDetailMapper.findcompanyuserid(deliver.getDevId());
                        if (Xtool.isNotNull(userid)){
                            wss.setToUserId(userid);
                            wss.setTitle("【一梦】平台推送了一份简历");
                            wss.setContent(user.getUserName()+" 该求职者通过【"+jobName+"】岗位的平台筛选");
                            wss.setNewType(1);//系统通知
                            newsMapper.insertSelective(wss);
                        }
                    }
                    count = deliverDetailMapper.insertSelective(deliverDetail);
                    if (count>0){
                        //当2已邀约，发送邀请通知
                        if ("2".equals(deliverDetail.getState())) {
                            News news = new News();
                            news.setToUserId(Integer.parseInt(deliver.getUserId()));
                            news.setTitle("面试邀约");
                            news.setDevid(deliverDetail.getId());
                            news.setContent("【" + jobName + "】招聘信息的面试邀请");
                            news.setIdentity(1);
                            news.setIsagree(0);
                            news.setDevid(deliver.getDevId());
                            news.setJobId(deliver.getJobId());
                            news.setDevdeilId(deliverDetail.getId());
                            news.setNewType(3);
                            newsMapper.insertSelective(news);
                        }
                    }
                    deliver.setState(deliverDetail.getState());
                    deliverMapper.updateByPrimaryKeySelective(deliver);
                }
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
    }

    @Override
    @Transactional
    public BaseResp leaveAudit(DeliverDetail deliverDetail){
        try {
            Integer count = 0;
            if (Xtool.isNotNull(deliverDetail.getDevId())) {
                //再判断简历是否存在
                Deliver deliver = deliverMapper.selectByPrimaryKey(deliverDetail.getDevId());
                if (deliver != null) {
                    String jobName = deliverMapper.getjobName(deliver.getDevId());
                    //7已入职8已离职
                    String state=deliver.getState();
                    if ("9".equals(state)){//离职待审核状态
                        if ("8".equals(deliverDetail.getState())) {
                            //更新不能发放佣金减少定时压力
                            deliverDetailMapper.updateisRuIncome(deliverDetail.getDevId());
                        }
                    }else{
                        return new BaseResp(Constants.FAIL_CODE, "该简历非离职待审核状态，无法操作");
                    }

                    User user = userMapper.selectByPrimaryKey(Integer.parseInt(deliver.getUserId()));
                    //判断是否携带分销参数,插入分销数据
                    if (Xtool.isNotNull(deliver.getReUserId()) && Xtool.isNotNull(deliver.getIsRecommend())&&deliver.getIsRecommend()==1) {
                        //判断分销主表存在
                        Recommend commend=new Recommend();
                        commend.setDelId(deliver.getDevId());
                        commend.setUserId(deliver.getReUserId());
                        Recommend recommend = recommendMapper.selectByDelId(commend);
                        if (recommend != null) {
                            //0不合适 1已投递 2已邀约3待面试4推送企业 5企业面试6已面试7已入职8已离职
                            // TODO 分销只取01678
                            String arr[] = {"0", "1", "6", "7", "8"};
                            List<String> list = new ArrayList<>();
                            list.addAll(Arrays.asList(arr));
                            // enter array into the list
                            if (list.contains(deliverDetail.getState())) {
                                if ("8".equals(deliverDetail.getState())){
                                    News ws = new News();
                                    ws.setToUserId(deliver.getReUserId());
                                    ws.setTitle("很遗憾你推荐的求职者已离职");
                                    ws.setContent(user.getUserName()+" 该求职者已从你分享的【"+jobName+"】岗位离职");
                                    ws.setIdentity(2);
                                    ws.setNewType(1);//系统通知
                                    newsMapper.insertSelective(ws);
                                }
                                recommend.setState(deliverDetail.getState());
                                recommendMapper.updateByPrimaryKeySelective(recommend);
                            }
                        }
                    }
                    deliverDetailMapper.insertSelective(deliverDetail);
                    deliver.setState(deliverDetail.getState());
                    deliverMapper.updateByPrimaryKeySelective(deliver);
                }
            }
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }



    @Override
    public BaseResp inappropriate(Integer id) {
        return null;
    }


    @Override
    public BaseResp updateBydeliverDetail(OssVO vo) {
        try {
            DeliverDetail deliverDetail = JSON.toJavaObject(vo.getVpnDeliverDetailUpdate(), DeliverDetail.class);
            Integer count = 0;
            if (Xtool.isNotNull(deliverDetail.getId())) {
                count = deliverDetailMapper.updateByPrimaryKeySelective(deliverDetail);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp delBydeliverDetail(OssVO vo) {
        try {
            DeliverDetail deliverDetail = JSON.toJavaObject(vo.getVpnDeliverDetailDel(), DeliverDetail.class);
            Integer count = 0;
            if (Xtool.isNotNull(deliverDetail.getId())) {
                count = deliverDetailMapper.deleteByPrimaryKey(deliverDetail.getId());
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    @Transactional
    public BaseResp vpninviteUpdatesvc(OssVO vo) throws Exception{
            SateVO sateVO = JSON.toJavaObject(vo.getVpninviteUpdate(), SateVO.class);
            Integer count = 0;
            if (Xtool.isNotNull(sateVO.getId()) && Xtool.isNotNull(sateVO.getState())) {
                News news = newsMapper.selectByPrimaryKey2(sateVO.getId());
                News ws=new News();
                if (news != null) {
                    if (Xtool.isNull(TokenUtil.getTokenUserId())||!TokenUtil.getTokenUserId().equals(news.getToUserId()+"")){
                        return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
                    }
                    Deliver deliver = deliverMapper.selectByPrimaryKey(news.getDevid());
//                    DeliverDetail detail = deliverDetailMapper.selectByPrimaryKey11(news.getDevid());
                    DeliverDetail detail = deliverDetailMapper.selectByPrimaryKey2(news.getDevdeilId());
                    User user=userMapper.selectByPrimaryKey(Integer.parseInt(TokenUtil.getTokenUserId()));
                    Job job=jobMapper.selectByJob2(detail.getDevId());
                    Integer userid =deliverDetailMapper.findcompanyuserid(detail.getDevId());
//                新增流程
                    if ("1".equals(sateVO.getState())) {
                        if (detail.getFlag() != 0) {
                            return new BaseResp(Constants.FAIL_CODE, "你已操作，无法再继续操作");
                        }
//                    先更新
                        detail.setFlag(1);
                        deliverDetailMapper.updateByPrimaryKeySelective(detail);
                        news.setIsagree(1);
                        newsMapper.updateByPrimaryKeySelective(news);
                        if ("2".equals(deliver.getState())){
                            //                    最后新增
                            DeliverDetail deliverDetail = new DeliverDetail();
                            deliverDetail.setState("3");
                            deliverDetail.setDevId(detail.getDevId());
                            deliverDetail.setPhone(detail.getPhone());
                            deliverDetail.setRemark(detail.getRemark());
                            deliverDetail.setTime(detail.getTime());
                            deliverDetail.setAdress(detail.getAdress());
                            addBydeliverDetail(deliverDetail);
                        }
                        //发消息给企业端
                        if (Xtool.isNotNull(userid)){
                            ws.setIdentity(3);
                            ws.setToUserId(userid);
                            ws.setTitle("有位求职者同意你的面试邀请");
                            ws.setContent(user.getUserName()+" 该求职者同意了【"+job.getName()+"】岗位的面试邀请");
                            ws.setNewType(3);
                            newsMapper.insertSelective(ws);
                        }
                        return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);
                    } else if ("2".equals(sateVO.getState())) {
                        //先更新
                        if (detail.getFlag() != 0) {
                            return new BaseResp(Constants.FAIL_CODE, "你已操作，无法再继续操作");
                        }
                        detail.setFlag(2);
                        deliverDetailMapper.updateByPrimaryKeySelective(detail);
                        news.setIsagree(2);
                        newsMapper.updateByPrimaryKeySelective(news);
                        if (Xtool.isNotNull(userid)){
                            ws.setIdentity(3);
                            ws.setToUserId(userid);
                            ws.setTitle("有位求职者拒绝你的面试邀请");
                            ws.setContent(user.getUserName()+" 该求职者拒绝了【"+job.getName()+"】岗位的面试邀请");
                            ws.setNewType(3);
                            newsMapper.insertSelective(ws);
                        }
                        return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);
                    }
                }
            }
            return new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
    }

    @Override
    public BaseResp vpninviteGetsvc(Integer id) {
        try {
            InviteVO inviteVO = new InviteVO();
            String userId = TokenUtil.getTokenUserId();
            News news = newsMapper.selectByPrimaryKey2(id);
//            更新已读
            if (news != null) {
                if (Xtool.isNull(userId) ||!userId.equals(news.getToUserId())) {
                    return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
                }
                if ("1".equals(news.getState())) {
                    news.setState("0");
                    newsMapper.updateByPrimaryKeySelective(news);
                }
                DeliverDetail detail = deliverDetailMapper.selectByPrimaryKey2(news.getDevid());
                Job job = jobMapper.selectByJob2(detail.getDevId());
                if (job != null) {
                    List<JobCustomLightenEnum> customLightenEnums = jobCustomLightenEnumService.findJobCustomLightenEnumbyjobId(job.getJobId());
                    job.setJobCustomLightspot(customLightenEnums);
                    List<JobLightenEnum> jobLightenEnums = jobLightenEnumService.findJobLightenEnumbyjobId(job.getJobId());
                    job.setJobLightspot(jobLightenEnums);
                }
                inviteVO.setIsagress(detail.getFlag());
                inviteVO.setDetail(detail);
                inviteVO.setJob(job);
            }
            Map map = new HashMap();
            map.put("vpninviteGetResp", inviteVO);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
