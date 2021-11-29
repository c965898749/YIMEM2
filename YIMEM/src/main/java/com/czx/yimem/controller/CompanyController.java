package com.czx.yimem.controller;

import com.czx.yimem.dto.ManshVO;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.ParmVO;
import com.czx.yimem.entity.*;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.*;
import com.czx.yimem.utils.*;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 公司管理
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Slf4j
@RequestMapping("vpnservice")
@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private DeliverService deliverService;
    @Autowired
    private DeliverDetailService deliverDetailService;
    @Autowired
    private Resp resp;
    @Autowired
    private UserService userService;
    @Autowired
    private JobService jobService;
    @Autowired
    private CollectService collectService;
    /**
     * 4.17.	企业查询接口
     * @param vo
     * @return
     */
    @RequestMapping("/vpncompanyGetsvc")
    public BaseResp vpncompanyGetsvc(@RequestBody OssVO vo){
        BaseResp baseResp=companyService.findBycompany(vo);
        return baseResp;
    }
    /**
     * 4.17.	企业查询接口
     * @param
     * @return
     */
    @RequestMapping("/vpncompanyGetsvc/{id}")
    public BaseResp vpncompanyGetsvcid(@PathVariable int id){
        BaseResp baseResp=companyService.findBycompanyid(id);
        return baseResp;
    }

    /**
     * 4.17.	企业查询接口
     * @param
     * @return
     */
    @RequestMapping("/vpncompanyGetbyidsvc")
    public BaseResp vpncompanyGetsvcids(@RequestBody ParmVO parmVO){
        if (Xtool.isNotNull(parmVO.getId())){
            return companyService.findBycompanyid(parmVO.getId());
        }else {
            return new BaseResp(Constants.FAIL_CODE,Constants.FAIL,"参数"+Constants.NO_STIPULATE);
        }
    }

    /**
     * 4.17.	手机号企业查询接口
     * @param
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpncompanyGetbyphonesvc")
    public BaseResp vpncompanyGetsvcids(@RequestBody Company  company){
        if (Xtool.isNotNull(company.getPhone())){
            return companyService.findBycompanyPhone(company.getPhone());
        }else {
            return new BaseResp(Constants.FAIL_CODE,Constants.FAIL,"参数"+Constants.NO_STIPULATE);
        }
    }
    /**
     * 4.61.	企业新增接口
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpncompanyAddsvc")
    public BaseResp vpncompanyAddsvc(@RequestBody OssVO vo){
        Company company = JSON.toJavaObject(vo.getVpncompanyAdd(), Company.class);
        //验证手机是否合规
        if (!PhoneUtil.regular(company.getPhone())){
            return new BaseResp(Constants.FAIL_CODE,"手机号"+Constants.NO_STIPULATE);
        }
        User user=userService.findphone2(company.getPhone());
        if (user==null||!TokenUtil.getTokenUserId().equals(user.getUserId()+"")){
            return new BaseResp(Constants.FAIL_CODE,Constants.NO_HANDLE);
        }
        return companyService.addBycompany(vo);
    }


    /**
     * 4.105.	企业修改接口
     * @param vo
     * @return
     */
    @RequestMapping("/vpncompanyUpdatesvc")
    public BaseResp vpncompanyUpdatesvc(@RequestBody OssVO vo){
        try {
            Company company = JSON.toJavaObject(vo.getVpncompanyUpdate(), Company.class);
            if (Xtool.isNull(company.getCompanyId())) {
                return new BaseResp(Constants.FAIL_CODE,Constants.FAIL,"参数"+Constants.NO_STIPULATE);
            }
            return resp.comParmResp(company.getCompanyId(),companyService.updateBycompany(vo));
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE,Constants.EXCEPTION);
        }
    }


    /**
     * 4.15.	用户简历投递列表查询接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpncompanydeliverGetsvc")
    public BaseResp vpndeliverGetsvc(@RequestBody OssVO vo) {
        try {
            Deliver deliver = JSON.toJavaObject(vo.getVpndeliverGet(), Deliver.class);
            if (Xtool.isNull(deliver.getCompanyId())) {
                return new BaseResp(Constants.FAIL_CODE,Constants.FAIL,"参数"+Constants.NO_STIPULATE);
            }
            return resp.comParmResp(deliver.getCompanyId(),deliverService.findBydeliver(vo));
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE,Constants.EXCEPTION);
        }
    }

    /**
     * 4.15.	用户收藏简历列表查询接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpncompanycollectdevGetsvc")
    public BaseResp vpncompanycollectdevGetsvc(@RequestBody OssVO vo) {
        try {
            Deliver deliver = JSON.toJavaObject(vo.getVpndeliverGet(), Deliver.class);
            if (Xtool.isNull(deliver.getCompanyId())) {
                return new BaseResp(Constants.FAIL_CODE,Constants.FAIL,"参数"+Constants.NO_STIPULATE);
            }
            return resp.comParmResp(deliver.getCompanyId(),deliverService.findBydeliver5(vo));
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE,Constants.EXCEPTION);
        }
    }


    /**
     * 4.15.	用户收藏简历接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpncompanycollectdevAddsvc")
    public BaseResp vpncompanycollectdevAddsvc(@RequestBody OssVO vo) {
        try {
            Collect collect = JSON.toJavaObject(vo.getVpncollectAdd(), Collect.class);
            if (Xtool.isNull(collect.getCompanyId())) {
                return new BaseResp(Constants.FAIL_CODE,Constants.FAIL,"参数"+Constants.NO_STIPULATE);
            }
            return resp.comParmResp(collect.getCompanyId(),collectService.addBycollect2(vo));
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE,Constants.EXCEPTION);
        }
    }


    /**
     * 4.15.	用户是否收藏简历接口
     *
     * @param
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpncompanycollectdevsvc")
    public BaseResp vpncompanycollectdevsvc(@RequestBody Collect collect) {
        return collectService.vpncompanycollectdev(collect);
    }


    /**
     * 4.36.	企业面试用户推荐流程查询接口
     * @param
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpncompanydeliverUserDetailGetsvc")
    public BaseResp vpncompanydeliverDetailGetsvc(@RequestBody ParmVO parmVO){
        if (parmVO==null|| Xtool.isNull(parmVO.getId())){
            return new BaseResp(Constants.FAIL_CODE,Constants.NO_STIPULATE);
        }
        String userId= TokenUtil.getTokenUserId();
        if (Xtool.isNull(userId)){
            return new BaseResp(Constants.FAIL_CODE,Constants.NO_AUTH);
        }
        if (companyService.companydeliver(Integer.parseInt(userId),parmVO.getId())<=0){
            return new BaseResp(Constants.FAIL_CODE,Constants.NO_AUTH);
        }
        Deliver deliver=deliverService.selectByPrimaryKey(parmVO.getId());
        if (deliver==null){
            return new BaseResp(Constants.FAIL_CODE,Constants.FAIL,"该简历不存在");
        }
        User user=userService.findByuserId(Integer.parseInt(deliver.getUserId()));
        if (user!=null){
            user.setState(deliver.getState());
        }
        Job job=jobService.selectById(deliver.getJobId());
        if (job!=null){
            user.setJobName(job.getName());
        }
        ManshVO manshVO=deliverDetailService.findmanshVO(parmVO.getId());
        if (manshVO!=null){
            user.setManshVO(manshVO);
        }
        return new BaseResp(Constants.SUCCEE_CODE,Constants.SUCCEE,user);
    }
    /**
     * 4.36.	企业面试用户详细查询接口
     * @param
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpncompanydeliverDetailGetsvc")
    public BaseResp vpncompanydeliverUserDetailGetsvc(@RequestBody ParmVO parmVO){
        if (parmVO==null|| Xtool.isNull(parmVO.getId())){
            return new BaseResp(Constants.FAIL_CODE,Constants.NO_STIPULATE);
        }
        String userId= TokenUtil.getTokenUserId();
        if (Xtool.isNull(userId)){
            return new BaseResp(Constants.FAIL_CODE,Constants.NO_AUTH);
        }
        if (companyService.companydeliver(Integer.parseInt(userId),parmVO.getId())<=0){
            return new BaseResp(Constants.FAIL_CODE,Constants.NO_AUTH);
        }
        BaseResp baseResp = deliverService.vpndeliverGetByidsvc(parmVO.getId());
        return baseResp;
    }
//    /**
//     * 4.149.	企业删除接口
//     * @param vo
//     * @return
//     */
//    @RequestMapping("/vpncompanyDelsvc")
//    public BaseResp vpncompanyDelsvc(@RequestBody OssVO vo){
//        BaseResp baseResp=companyService.delBycompany(vo);
//        return baseResp;
//    }

    /**
     * 4.149.	企业岗位已读接口
     * @param
     * @return
     */
    @RequestMapping("/updateIsread")
    public BaseResp vpncompanyDelsvc(@RequestBody ParmVO parmVO){
        if (Xtool.isNotNull(parmVO.getId())){
            return companyService.updateIsread(parmVO.getId());
        }else {
            return new BaseResp(Constants.FAIL_CODE,Constants.FAIL,"参数"+Constants.NO_STIPULATE);
        }
    }



//    /**
//     * 邀请面试
//     *
//     * @param vo
//     * @return
//     */
//    @RequestMapping("/vpnuserbasicDeli/interview/{id}")
//    public BaseResp interview2(@PathVariable int id, @RequestBody DeliverDetailVO vo) {
//        // 0不合适 1已投递 2已邀约3待面试4推送企业 5企业面试6已面试7已入职8已离职
//        //                        只取123可以不合适
//        String arr[] = {"1"};
//        List<String> list = new ArrayList<>();
//        list.addAll(Arrays.asList(arr));
//        // enter array into the list
//        if (list.contains(deliverService.selectState(id))) {
//            vo.setDevId(id);
//            vo.setState("2");
//            return deliverDetailService.interview(vo);
//        }
//        return new BaseResp(Constants.FAIL_CODE,"当前环节无法 邀请面试");
//    }
//
//    /**
//     * 不合适
//     *
//     * @param
//     * @return
//     */
//    @RequestMapping("/vpnuserbasicDeli/inappropriate/{id}")
//    public BaseResp inappropriate(@PathVariable int id, @RequestBody DeliverDetailVO vo) {
//        // 0不合适 1已投递 2已邀约3待面试4推送企业 5企业面试6已面试7已入职8已离职
//        //                        只取123可以不合适
//        String arr[] = {"1", "2", "3"};
//        List<String> list = new ArrayList<>();
//        list.addAll(Arrays.asList(arr));
//        // enter array into the list
//        if (list.contains(deliverService.selectState(id))) {
//            vo.setDevId(id);
//            vo.setState("0");
//            BaseResp baseResp = deliverDetailService.interview(vo);
//            return baseResp;
//        }
//        return new BaseResp(Constants.FAIL_CODE,"当前环节无法 不合适");
//    }
//
//    /**
//     * 推送企业
//     *
//     * @param
//     * @return
//     */
//    @RequestMapping("/vpnuserbasicDeli/push/{id}")
//    public BaseResp push(@PathVariable int id) {
//        // 0不合适 1已投递 2已邀约3待面试4推送企业 5企业面试6已面试7已入职8已离职
//        //                        只取123可以不合适
//        String arr[] = {"3"};
//        List<String> list = new ArrayList<>();
//        list.addAll(Arrays.asList(arr));
//        // enter array into the list
//        if (list.contains(deliverService.selectState(id))) {
//            DeliverDetailVO vo = new DeliverDetailVO();
//            vo.setDevId(id);
//            vo.setState("4");
//            BaseResp baseResp = deliverDetailService.interview(vo);
//            return baseResp;
//        }
//        return new BaseResp(Constants.FAIL_CODE,"当前环节无法 推送企业");
//
//    }
}
