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
     * pc????????????
     *
     * @param
     * @return
     */
    @Override
    @Transactional
    public BaseResp addBydeliverDetail(DeliverDetail deliverDetail) throws Exception {
            //TODO ????????????????????????????????????
//            DeliverDetail deliverDetail = JSON.toJavaObject(vo.getVpnDeliverDetailAdd(), DeliverDetail.class);
            Integer count = 0;
            if (Xtool.isNotNull(deliverDetail.getDevId())) {
                //???????????????????????????

                Deliver deliver = deliverMapper.selectByPrimaryKey(deliverDetail.getDevId());
                if (deliver != null) {
                    String jobName = deliverMapper.getjobName(deliver.getDevId());

                    //0????????? 1????????? 2?????????3?????????4???????????? 5????????????6?????????7?????????8?????????
                    //???????????????????????????????????????????????????????????????????????????????????????????????????3????????????????????????3??????
                    //??????????????????
                    //???????????????
                    String state=deliver.getState();
                    if ("0".equals(state)){
                        return new BaseResp(Constants.FAIL_CODE, "????????????????????????????????????????????????");
                    }
                    if ("9".equals(state)){
                        return new BaseResp(Constants.FAIL_CODE,"?????????????????????????????????");
                    }
                    //????????????
                    if ("2".equals(state)){
                        String k1[] = { "4", "5", "6", "7", "8"};
                        List<String> c1 = new ArrayList<>();
                        c1.addAll(Arrays.asList(k1));
                        if (c1.contains(deliverDetail.getState())) {
                            return new BaseResp(Constants.FAIL_CODE, "????????????????????????????????????????????????");
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
                        ws.setTitle("???????????????");
                        ws.setContent("???????????????"+jobName+"????????????????????????");
                        ws.setIdentity(1);
                        ws.setNewType(3);//????????????
                        newsMapper.insertSelective(ws);
                        ws.setDevid(deliver.getDevId());
                        newsMapper.updatesiagree(ws);
                    }
                    if ("4".equals(deliverDetail.getState())){
                        //??????????????????????????????????????????
                        DeliverDetail detail=new DeliverDetail();
                        BeanUtils.copyProperties(deliverDetail,detail);
                        detail.setState("6");
                        deliverDetailMapper.insertSelective(detail);
                    }
                    if ("7".equals(deliverDetail.getState())) {
                        if ("7".equals(deliverDetailMapper.findstate(deliverDetail.getDevId()))) {
                            return new BaseResp(Constants.FAIL_CODE,"??????????????????????????????????????????" );
                        }
                        if ("8".equals(deliverDetailMapper.findstate(deliverDetail.getDevId()))) {
                            return new BaseResp(Constants.FAIL_CODE, "??????????????????????????????????????????");
                        }
                        if (deliverDetail.getEntryTime() == null) {
                            return new BaseResp(Constants.FAIL_CODE, "????????????" + Constants.NO_NULL);
                        }
                        News news = new News();
                        news.setToUserId(Integer.parseInt(deliver.getUserId()));
                        news.setTitle("???????????????");
                        news.setDevid(deliverDetail.getId());
                        deliverDetailMapper.updatestate3for6(deliverDetail.getDevId());
                        deliverDetailMapper.updateisRuIncome2(deliverDetail.getDevId());
                        news.setContent("???????????????????????????" + jobName + "????????????");
                        news.setIdentity(1);
                        news.setNewType(4);//????????????
                        newsMapper.insertSelective(news);
                        //??????????????????
                    }
                    if ("8".equals(deliverDetail.getState())) {
                        if ("8".equals(deliverDetailMapper.findstate(deliverDetail.getDevId()))) {
                            return new BaseResp(Constants.FAIL_CODE,"??????????????????????????????????????????");
                        }
                        if (deliverDetail.getLeaveTime() == null) {
                            return new BaseResp(Constants.FAIL_CODE,"????????????" + Constants.NO_NULL);
                        }
                        //??????????????????????????????????????????
                        deliverDetailMapper.updateisRuIncome(deliverDetail.getDevId());
                    }
                    if ("9".equals(deliverDetail.getState())) {
                        if ("7".equals(deliverDetailMapper.findstate(deliverDetail.getDevId()))) {
                            if (deliverDetail.getLeaveTime() == null || deliverDetail.getResion()==null) {
                                return new BaseResp(Constants.FAIL_CODE,"?????????????????????" + Constants.NO_NULL);
                            }
                        }else{
                            return new BaseResp(Constants.FAIL_CODE,"??????????????????????????????");
                        }
                    }
                    User user = userMapper.selectByPrimaryKey(Integer.parseInt(deliver.getUserId()));
                    //??????????????????????????????,??????????????????
                    if (Xtool.isNotNull(deliver.getReUserId()) && Xtool.isNotNull(deliver.getIsRecommend())&&deliver.getIsRecommend()==1) {
                        //????????????????????????
                        Recommend commend=new Recommend();
                        commend.setDelId(deliver.getDevId());
                        commend.setUserId(deliver.getReUserId());
                        Recommend recommend = recommendMapper.selectByDelId(commend);
                        if (recommend != null) {
                            //0????????? 1????????? 2?????????3?????????4???????????? 5????????????6?????????7?????????8?????????
                            // TODO ????????????01678
                            String arr[] = {"0", "1", "6", "7", "8"};
                            List<String> list = new ArrayList<>();
                            list.addAll(Arrays.asList(arr));
                            // enter array into the list
                            if (list.contains(deliverDetail.getState())) {
                                if ("7".equals(deliverDetail.getState())){
                                    News ws = new News();
                                    ws.setToUserId(deliver.getReUserId());
                                    ws.setTitle("??????????????????????????????????????????");
                                    ws.setContent(user.getUserName()+" ???????????????????????????????????????"+jobName+"???");
                                    ws.setIdentity(2);
                                    ws.setNewType(4);//????????????
                                    newsMapper.insertSelective(ws);
                                }
                                if ("0".equals(deliverDetail.getState())){
                                    News ws = new News();
                                    ws.setToUserId(deliver.getReUserId());
                                    ws.setTitle("???????????????????????????????????????");
                                    ws.setContent(user.getUserName()+" ????????????????????????????????????"+jobName+"?????????????????????");
                                    ws.setIdentity(2);
                                    ws.setNewType(1);//????????????
                                    newsMapper.insertSelective(ws);
                                }
                                recommend.setState(deliverDetail.getState());
                                recommendMapper.updateByPrimaryKeySelective(recommend);
                            }
                        }
                    }
                    //??????4??????????????????
                    if ("4".equals(state)){
                        News wss = new News();
                        Integer userid =deliverDetailMapper.findcompanyuserid(deliver.getDevId());
                        if (Xtool.isNotNull(userid)){
                            wss.setToUserId(userid);
                            wss.setTitle("???????????????????????????????????????");
                            wss.setContent(user.getUserName()+" ?????????????????????"+jobName+"????????????????????????");
                            wss.setNewType(1);//????????????
                            newsMapper.insertSelective(wss);
                        }
                    }
                    count = deliverDetailMapper.insertSelective(deliverDetail);
                    if (count>0){
                        //???2??????????????????????????????
                        if ("2".equals(deliverDetail.getState())) {
                            News news = new News();
                            news.setToUserId(Integer.parseInt(deliver.getUserId()));
                            news.setTitle("????????????");
                            news.setDevid(deliverDetail.getId());
                            news.setContent("???" + jobName + "??????????????????????????????");
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
                //???????????????????????????
                Deliver deliver = deliverMapper.selectByPrimaryKey(deliverDetail.getDevId());
                if (deliver != null) {
                    String jobName = deliverMapper.getjobName(deliver.getDevId());
                    //7?????????8?????????
                    String state=deliver.getState();
                    if ("9".equals(state)){//?????????????????????
                        if ("8".equals(deliverDetail.getState())) {
                            //??????????????????????????????????????????
                            deliverDetailMapper.updateisRuIncome(deliverDetail.getDevId());
                        }
                    }else{
                        return new BaseResp(Constants.FAIL_CODE, "????????????????????????????????????????????????");
                    }

                    User user = userMapper.selectByPrimaryKey(Integer.parseInt(deliver.getUserId()));
                    //??????????????????????????????,??????????????????
                    if (Xtool.isNotNull(deliver.getReUserId()) && Xtool.isNotNull(deliver.getIsRecommend())&&deliver.getIsRecommend()==1) {
                        //????????????????????????
                        Recommend commend=new Recommend();
                        commend.setDelId(deliver.getDevId());
                        commend.setUserId(deliver.getReUserId());
                        Recommend recommend = recommendMapper.selectByDelId(commend);
                        if (recommend != null) {
                            //0????????? 1????????? 2?????????3?????????4???????????? 5????????????6?????????7?????????8?????????
                            // TODO ????????????01678
                            String arr[] = {"0", "1", "6", "7", "8"};
                            List<String> list = new ArrayList<>();
                            list.addAll(Arrays.asList(arr));
                            // enter array into the list
                            if (list.contains(deliverDetail.getState())) {
                                if ("8".equals(deliverDetail.getState())){
                                    News ws = new News();
                                    ws.setToUserId(deliver.getReUserId());
                                    ws.setTitle("???????????????????????????????????????");
                                    ws.setContent(user.getUserName()+" ?????????????????????????????????"+jobName+"???????????????");
                                    ws.setIdentity(2);
                                    ws.setNewType(1);//????????????
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
//                ????????????
                    if ("1".equals(sateVO.getState())) {
                        if (detail.getFlag() != 0) {
                            return new BaseResp(Constants.FAIL_CODE, "????????????????????????????????????");
                        }
//                    ?????????
                        detail.setFlag(1);
                        deliverDetailMapper.updateByPrimaryKeySelective(detail);
                        news.setIsagree(1);
                        newsMapper.updateByPrimaryKeySelective(news);
                        if ("2".equals(deliver.getState())){
                            //                    ????????????
                            DeliverDetail deliverDetail = new DeliverDetail();
                            deliverDetail.setState("3");
                            deliverDetail.setDevId(detail.getDevId());
                            deliverDetail.setPhone(detail.getPhone());
                            deliverDetail.setRemark(detail.getRemark());
                            deliverDetail.setTime(detail.getTime());
                            deliverDetail.setAdress(detail.getAdress());
                            addBydeliverDetail(deliverDetail);
                        }
                        //?????????????????????
                        if (Xtool.isNotNull(userid)){
                            ws.setIdentity(3);
                            ws.setToUserId(userid);
                            ws.setTitle("???????????????????????????????????????");
                            ws.setContent(user.getUserName()+" ????????????????????????"+job.getName()+"????????????????????????");
                            ws.setNewType(3);
                            newsMapper.insertSelective(ws);
                        }
                        return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);
                    } else if ("2".equals(sateVO.getState())) {
                        //?????????
                        if (detail.getFlag() != 0) {
                            return new BaseResp(Constants.FAIL_CODE, "????????????????????????????????????");
                        }
                        detail.setFlag(2);
                        deliverDetailMapper.updateByPrimaryKeySelective(detail);
                        news.setIsagree(2);
                        newsMapper.updateByPrimaryKeySelective(news);
                        if (Xtool.isNotNull(userid)){
                            ws.setIdentity(3);
                            ws.setToUserId(userid);
                            ws.setTitle("???????????????????????????????????????");
                            ws.setContent(user.getUserName()+" ????????????????????????"+job.getName()+"????????????????????????");
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
//            ????????????
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
