package com.czx.yimem.controller;

import com.czx.yimem.dto.ErwmVO;
import com.czx.yimem.dto.IdentityVO;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.ParmVO;
import com.czx.yimem.entity.*;
import com.czx.yimem.entity.requestbean.ResultBeanObj;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.*;
import com.czx.yimem.service.admin.BeginnerService;
import com.czx.yimem.service.admin.ProtocolService;
import com.czx.yimem.service.admin.WithdrawRuleService;
import com.czx.yimem.service.financial.IncomeService;
import com.czx.yimem.utils.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户
 *
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Slf4j
@RequestMapping("vpnservice")
@RestController
public class UserController {

    @Autowired
    private JobWantedService jobWantedService;
    @Autowired
    private UserService userService;
    @Autowired
    private JobExpService jobExpService;
    @Autowired
    private EducationExpService educationExpService;
    @Autowired
    private LanguageService languageService;
    @Autowired
    private ProjectExpService projectExpservice;
    @Autowired
    private CertificateService certificateService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private WeChatUtil weChatUtil;
    @Autowired
    private LsImgService imgService;
    @Autowired
    private CvFileService cvFileService;
    @Autowired
    private IncomeService incomeService;
    @Autowired
    private PointService pointService;
    @Autowired
    private Resp resp;


    /**
     * 用户基本信息查询
     *
     * @param
     * @return
     */
//    @UserLoginToken
//    @RequestMapping("/vpnuserbasic/{id}")
//    public BaseResp vpnuserGetsvc(@PathVariable()int id){
//        User user=userService.findByuserId(id);
//        return new BaseResp(Constants.SUCCEE_CODE,Constants.SUCCEE,user);
//    }
    @UserLoginToken
    @RequestMapping("/vpnuserbasic")
    public BaseResp vpnuserGetsvc(@RequestBody ParmVO parmVO) {
        if (parmVO == null || Xtool.isNull(parmVO.getId())) {
            return new BaseResp(Constants.FAIL_CODE, Constants.NO_STIPULATE);
        }
        return resp.ownerParmResp(parmVO, userService.findByuserId(parmVO.getId()));
    }


    /**
     * 用户工作(pc端用)
     *
     * @param
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_RM_ZJ_CZ','ROLE_RM_ZJ_CZ','ROLE_ADMIN')")
    @RequestMapping("/vpnuserbasicDeli/{id}")
    public BaseResp vpnuserbasicDeli(@PathVariable() int id) {
        return userService.vpnuserbasicDeli(id);
    }

    /**
     * 4.5.    简历查询接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnuserGetsvc")
    public BaseResp vpnuserGetsvc(@RequestBody OssVO vo) {
        return resp.ownAndcomResp(vo, userService.findByuser(vo));
    }

    /**
     * 4.49.    用户新增接口
     * @param vo
     * @return
     */
    //TODO 手机新增
//    @RequestMapping("/vpnuserAddsvc")
//    public BaseResp vpnuserAddsvc(@RequestBody OssVO vo){
//        BaseResp baseResp=userService.addByuser(vo);
//        return baseResp;
//    }

    /**
     * 4.93.    用户修改接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnuserUpdatesvc")
    public BaseResp vpnuserUpdatesvc(@RequestBody OssVO vo) {
        return resp.ownerResp(vo, userService.udateByuser(vo));
    }

//    /**
//     * 4.137.    用户删除接口
//     * @param vo
//     * @return
//     */
//    @UserLoginToken
//    @RequestMapping("/vpnuserDelsvc")
//    public BaseResp vpnuserDelsvc(@RequestBody OssVO vo){
//        BaseResp baseResp=userService.delByuser(vo);
//        return baseResp;
//    }


    /**
     * 3.12   用户签到接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnPointSigntsvc")
    public BaseResp vpnPointSigntsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = pointService.pointSign(vo);
        return baseResp;
    }

    /**
     * 3.12   用户签到接口
     *
     * @param userId
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnGetPointsvc/{userId}")
    public BaseResp vpnGetPointsvc(@PathVariable() int userId) {
        BaseResp baseResp = pointService.getPointByUserId(userId);
        return baseResp;
    }

    /**
     * 3.12   用户签到接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnGetPointdetailsvc")
    public BaseResp vpnGetPointdetailsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = pointService.getPointdetail(vo);
        return baseResp;
    }


    /**
     * 4.6.    用户简历附件查询接口
     *
     * @param
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpncvFileServiceGetsvc")
    public BaseResp vpncvFileServiceGetsvc(@RequestBody ParmVO parmVO) {
        if (parmVO == null || Xtool.isNull(parmVO.getId())) {
            return new BaseResp(Constants.FAIL_CODE, Constants.NO_STIPULATE);
        }
        BaseResp baseResp = cvFileService.vpncvFileServiceGet(parmVO.getId());
        return resp.chekidResp(parmVO.getId(), baseResp);
    }

    /**
     * 4.50.    用户简历附件新增接口
     *
     * @param
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpncvFileServiceAddsvc")
    public BaseResp vpncvFileServiceAddsvc(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> er = request.getParameterNames();
        while (er.hasMoreElements()) {
            String name = (String) er.nextElement();
            String value = request.getParameter(name);
            map.put(name, value);
        }
        if (map.isEmpty() || Xtool.isNull(map.get("userId")) || Xtool.isNull(map.get("filename"))) {
            return new BaseResp(Constants.FAIL_CODE, Constants.NO_STIPULATE);
        }
        if (!TokenUtil.getTokenUserId().equals(map.get("userId"))) {
            return new BaseResp(Constants.FAIL_CODE, "你无权更改他人简历附件");
        }
        CvFile cvFile = new CvFile();
        ResultBeanObj resultBeanObj = UploadUtil.uploadFile(file, request, "1", "ax-jhrc");
        if (resultBeanObj.getSuccess() != 0) {
            Map<String, String> stringMap = (Map<String, String>) resultBeanObj.getData();
            if (!stringMap.isEmpty()) {
                cvFile.setUserId(Integer.parseInt(map.get("userId")));
                cvFile.setFilename(map.get("filename"));
                cvFile.setUrl(stringMap.get("shortUrl"));
                cvFile.setFilesize(String.valueOf(stringMap.get("filesize")));
                BaseResp baseResp = cvFileService.vpncvFileServiceAdd(cvFile);
                return baseResp;
            }
        }
        return new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
    }


//    /**
//     * 4.94.    用户简历附件修改接口
//     * @param vo
//     * @return
//     */
//    @UserLoginToken
//    @RequestMapping("/vpncvFileServiceUpdatesvc")
//    public BaseResp vpncvFileServiceUpdatesvc(@RequestBody OssVO vo){
//        BaseResp baseResp=cvFileService.vpncvFileServiceUpdate(vo);
//        return baseResp;
//    }

    /**
     * 4.138.    用户简历附件删除接口
     *
     * @param
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpncvFileServiceDelsvc")
    public BaseResp vpncvFileServiceDelsvc(@RequestBody ParmVO parmVO) {
        if (parmVO == null || Xtool.isNull(parmVO.getId())) {
            return new BaseResp(Constants.FAIL_CODE, Constants.NO_STIPULATE);
        }
        String userId = TokenUtil.getTokenUserId();
        CvFile cvFile = new CvFile();
        cvFile.setUserId(Integer.parseInt(userId));
        cvFile.setId(parmVO.getId());
        BaseResp baseResp = cvFileService.vpncvFileServiceDel(cvFile);
        return baseResp;
    }

    /**
     * 4.6.    用户求职意向查询接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnjobWantedGetsvc")
    public BaseResp vpnjobWantedGetsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = jobWantedService.findByjobWanted(vo);
        return baseResp;
    }

    /**
     * 4.50.    用户求职意向新增接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnjobWantedAddsvc")
    public BaseResp vpnjobWantedAddsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = jobWantedService.addByjobWanted(vo);
        return baseResp;
    }


    /**
     * 4.94.    用户求职意向修改接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnjobWantedUpdatesvc")
    public BaseResp vpnjobWantedUpdatesvc(@RequestBody OssVO vo) {
        BaseResp baseResp = jobWantedService.updateByjobWanted(vo);
        return baseResp;
    }

    /**
     * 4.138.    用户求职意向删除接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnjobWantedDelsvc")
    public BaseResp vpnjobWantedDelsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = jobWantedService.delByjobWanted(vo);
        return baseResp;
    }

    /**
     * 4.7.    用户工作经验查询接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnjobExpGetsvc")
    public BaseResp vpnjobExpGetsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = jobExpService.findByjobExp(vo);
        return baseResp;
    }

    /**
     * 4.51.    用户工作经验新增接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnjobExpAddsvc")
    public BaseResp vpnjobExpAddsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = jobExpService.addByjobExp(vo);
        return baseResp;
    }

    /**
     * 4.95.    用户工作经验修改接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnjobExpUpdatesvc")
    public BaseResp vpnjobExpUpdatesvc(@RequestBody OssVO vo) {
        BaseResp baseResp = jobExpService.updateByjobExp(vo);
        return baseResp;
    }

    /**
     * 4.139.    用户工作经验删除接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnjobExpDelsvc")
    public BaseResp vpnjobExpDelsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = jobExpService.delByjobExp(vo);
        return baseResp;
    }

    /**
     * 4.8.    用户教育经历查询接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpneducationExpGetsvc")
    public BaseResp vpneducationExpGetsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = educationExpService.findByeducationExp(vo);
        return baseResp;
    }


    /**
     * 4.52.    用户教育经历新增接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpneducationExpAddsvc")
    public BaseResp vpneducationExpAddsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = educationExpService.addByeducationExp(vo);
        return baseResp;
    }

    /**
     * 4.96.    用户教育经历修改接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpneducationExpUpdatesvc")
    public BaseResp vpneducationExpUpdatesvc(@RequestBody OssVO vo) {
        BaseResp baseResp = educationExpService.updateByeducationExp(vo);
        return baseResp;
    }

    /**
     * 4.140.    用户教育经历删除接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpneducationExpDelsvc")
    public BaseResp vpneducationExpDelsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = educationExpService.delByeducationExp(vo);
        return baseResp;
    }

    /**
     * 4.9.    用户语言能力查询接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnlanguageGetsvc")
    public BaseResp vpnlanguageGetsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = languageService.findBylanguage(vo);
        return baseResp;
    }

    /**
     * 4.53.    用户语言能力新增接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnlanguageAddsvc")
    public BaseResp vpnlanguageAddsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = languageService.addBylanguage(vo);
        return baseResp;
    }

    /**
     * 4.97.    用户语言能力修改接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnlanguageUpdatesvc")
    public BaseResp vpnlanguageUpdatesvc(@RequestBody OssVO vo) {
        BaseResp baseResp = languageService.updateBylanguage(vo);
        return baseResp;
    }

    /**
     * 4.141.    用户语言能力删除接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnlanguageDelsvc")
    public BaseResp vpnlanguageDelsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = languageService.delBylanguage(vo);
        return baseResp;
    }

    /**
     * 4.54.    用户项目经验新增接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnprojectExpAddsvc")
    public BaseResp vpnprojectExpAddsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = projectExpservice.addByprojectExp(vo);
        return baseResp;
    }

    /**
     * 4.98.    用户项目经验修改接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnprojectExpUpdatesvc")
    public BaseResp vpnprojectExpUpdatesvc(@RequestBody OssVO vo) {
        BaseResp baseResp = projectExpservice.updateByprojectExp(vo);
        return baseResp;
    }

    /**
     * 4.10.    用户项目经验查询接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnprojectExpGetsvc")
    public BaseResp vpnprojectExpGetsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = projectExpservice.findByprojectExp(vo);
        return baseResp;
    }

    /**
     * 4.142.    用户项目经验删除接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnprojectExpDelsvc")
    public BaseResp vpnprojectExpDelsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = projectExpservice.delByprojectExp(vo);
        return baseResp;
    }

    /**
     * 4.11.    用户学历证书查询接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpncertificateGetsvc")
    public BaseResp vpncertificateGetsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = certificateService.findBycertificate(vo);
        return baseResp;
    }


    /**
     * 4.99.    用户学历证书修改接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpncertificateUpdatesvc")
    public BaseResp vpncertificateUpdatesvc(@RequestBody OssVO vo) {
        BaseResp baseResp = certificateService.updateBycertificate(vo);
        return baseResp;
    }

    /**
     * 4.55.    用户学历证书新增接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpncertificateAddsvc")
    public BaseResp vpncertificateAddsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = certificateService.addBycertificate(vo);
        return baseResp;
    }

    /**
     * 4.143.    用户学历证书删除接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpncertificateDelsvc")
    public BaseResp vpncertificateDelsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = certificateService.delBycertificate(vo);
        return baseResp;
    }

    /**
     * 4.12.    用户消息查询接口
     *
     * @param vo
     * @return
     */
    @RequestMapping("/vpnmessageGetsvc")
    public BaseResp vpnmessageGetsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = messageService.findBymessage(vo);
        return baseResp;
    }

    @UserLoginToken
    @RequestMapping("/isnewmsg")
    public BaseResp isnewmsg(@RequestBody IdentityVO vo) {
        BaseResp baseResp = newsService.isnewmsg(vo);
        return baseResp;
    }

    /**
     * 4.56.    用户消息新增接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnmessageAddsvc")
    public BaseResp vpnmessageAddsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = messageService.addBymessage(vo);
        return baseResp;
    }

    /**
     * 4.144.    用户消息删除接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnmessageDelsvc")
    public BaseResp vpnmessageDelsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = messageService.delBymessage(vo);
        return baseResp;
    }

    @UserLoginToken
    @RequestMapping("/vpnmessageUpdatesvc")
    public BaseResp vpnmessageUpdatesvc(@RequestBody OssVO vo) {
        BaseResp baseResp = messageService.udateBymessage(vo);
        return baseResp;
    }

    /**
     * 4.13.    用户通知查询接口
     *
     * @param vo
     * @return
     */
    @RequestMapping("/vpnnewsGetsvc")
    public BaseResp vpnnewsGetsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = newsService.findBynews(vo);
        return baseResp;
    }

    /**
     * 3.12    全部通知已读接口
     *
     * @param vo
     * @return
     */
    @RequestMapping("/vpnnewsReadsvc")
    public BaseResp vpnnewsReadsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = newsService.updatesiread(vo);
        return baseResp;
    }

    /**
     * 4.57.    用户通知新增接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnnewsAddsvc")
    public BaseResp vpnnewsAddsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = newsService.addBynews(vo);
        return baseResp;
    }

    /**
     * 4.101.    用户通知修改接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnnewsUpdatesvc")
    public BaseResp vpnnewsUpdatesvc(@RequestBody OssVO vo) {
        BaseResp baseResp = newsService.updateBynews(vo);
        return baseResp;
    }

    /**
     * 4.145.    用户通知删除接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnnewsDelsvc")
    public BaseResp vpnnewsDelsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = newsService.delBynews(vo);
        return baseResp;
    }

    /**
     * 4.14.    用户收藏查询接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpncollectGetsvc")
    public BaseResp vpncollectGetsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = collectService.findBycollect(vo);
        return baseResp;
    }

    /**
     * 4.14.    用户收藏职位是否收藏
     *
     * @param
     * @return
     */
    @RequestMapping("/vpniscollectJob")
    public BaseResp vpniscollectJob(@RequestBody Collect collect) {
        return collectService.vpniscollectJob(collect);

    }

    /**
     * 4.58.    用户收藏新增接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpncollectAddsvc")
    public BaseResp vpncollectAddsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = collectService.addBycollect(vo);
        return baseResp;
    }

    /**
     * 4.102.    用户收藏修改接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpncollectUpdatesvc")
    public BaseResp vpncollectUpdatesvc(@RequestBody OssVO vo) {
        BaseResp baseResp = collectService.updateBycollect(vo);
        return baseResp;
    }

    /**
     * 4.146.    用户收藏删除接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpncollectDelsvc")
    public BaseResp vpncollectDelsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = collectService.delBycollect(vo);
        return baseResp;
    }

    /**
     * 4.146.    用户收藏删除接口
     *
     * @param record
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpncollectRecSavesvc")
    public BaseResp vpncollectRecSavesvc(@RequestBody CollectRec record) {
        BaseResp baseResp = collectService.insertRecSelective(record);
        return baseResp;
    }


    /**
     * 获取AccessToken
     *
     * @return
     */
    @RequestMapping("/getAccessToken")
    public BaseResp getAccessToken() {
        return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, weChatUtil.getAccessToken());
    }

    @RequestMapping("/inviteList/{inviteCode}")
    public BaseResp inviteList(@PathVariable() String inviteCode) {
        return userService.inviteList(inviteCode);
    }

//    /**
//     *生成二位码
//     * @return
//     */
//    @RequestMapping("/getWechatCode")
//    public BaseResp getWechatCode(@RequestBody ErwmVO erwmVO) throws IOException {
//        System.out.println(erwmVO.getPath()+"----------------"+erwmVO.getParam());
//        return new BaseResp(Constants.SUCCEE_CODE,Constants.SUCCEE,weChatUtil.getWechatCode(erwmVO.getPath(),erwmVO.getParam()));
//    }

    /**
     * 头像上传
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("/upload")
    public ResultBeanObj uploadHead(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        ResultBeanObj resultBeanObj = UploadUtil.uploadImage(file, request, "1", "ax-jhrc");
        if (resultBeanObj.getSuccess() != 0) {
            Map<String, String> map = (Map<String, String>) resultBeanObj.getData();
            if (!map.isEmpty()) {
                LsImg img = new LsImg();
                img.setImageUrl(map.get("imageUrl"));
                img.setName(map.get("name"));
                img.setShortUrl(map.get("shortUrl"));
                img.setRealPath(map.get("realPath"));
                imgService.insertSelective(img);
            }
        }
        return resultBeanObj;
    }

//    /**
//     * 头像上传
//     * @return
//     * @throws IOException
//     */
//    @RequestMapping("/uploadfile")
//    public ResultBeanObj uploadfile(@RequestParam MultipartFile file,HttpServletRequest request) throws IOException {
//        Map<String, String> map =new HashMap<>();
//        Enumeration<String> er = request.getParameterNames();
//        while (er.hasMoreElements()) {
//            String name = (String) er.nextElement();
//            String value = request.getParameter(name);
//            map.put(name, value);
//        }
//        return ResultBeanObj.success().setData(map);
////        ResultBeanObj resultBeanObj= UploadUtil.uploadFile(file,request,"1","ax-jhrc");
////        return resultBeanObj;
//    }
//
////    @RequestMapping("/cc")
////    public void cc(){
////        incomeService.updatefindTimeUp();
////    }


    @RequestMapping("/qrCode")
    @ResponseBody
    public BaseResp cenerateTheQrCode(@RequestBody ErwmVO erwmVO) {
        if (erwmVO == null || Xtool.isNull(erwmVO.getScene()) || Xtool.isNull(erwmVO.getPath())) {
            return new BaseResp(Constants.FAIL_CODE, Constants.NO_STIPULATE);
        }
        //调用上面方法，获取accessToken，建议对accessToken进行缓存，
        //appid：小程序appid，appsecret：小程序密钥
        String token = weChatUtil.getAccessToken();
        log.info("token:" + token);
        Map<String, Object> params = new HashMap<>();
        //参数,一个可以直接放入参数例如:1 多个例如：id=1&name=2&...
        Map<String, Object> color = new HashMap<String, Object>();
        color.put("r", 0);
        color.put("g", 0);
        color.put("b", 0);
        params.put("width", 430);
        params.put("auto_color", false);
        params.put("line_color", color);
        params.put("is_hyaline", false);
        params.put("scene", erwmVO.getScene());
        params.put("path", erwmVO.getPath()); //扫码后进入小程序的页面位置
//        params.put("width", Number类型)//不是必须，需要的宽度，默认430x430，最小280最大1280
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 接口
        HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + token);
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
        String body = JSON.toJSONString(params);//必须是json模式的 post
        StringEntity entity = null;
        Map map = new HashMap();
        try {
            entity = new StringEntity(body);
            entity.setContentType("image/png");//也可以是image/jpg
            httpPost.setEntity(entity);
            HttpResponse response;
            response = httpClient.execute(httpPost);
            InputStream inputStream = response.getEntity().getContent();
            String filePath = UploadUtil.uploadInputStream(inputStream);
            map.put("filePath", Constants.OSS_URL + filePath);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
        return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
    }

    /**
     * 4.146.    用户积分兑换礼物接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/pointToGift")
    public BaseResp pointToGift(@RequestBody OssVO vo) {
        BaseResp baseResp = pointService.pointToGift(vo);
        return baseResp;
    }

//
//    public static void main(String[] args) {
//
//
//        String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=40_Gj1sG2YKbgSdw4usWceBwIZHAXRP-k5k0wntzgz-mfeqYn3WBhETGj1eA84pl6H1Oy9HcNRx46L7eh6ru-q7mUh8H58ON9O2aY_u5jv6iLwIvaSPpR47YTtAxUH3YCzxk1oCI_uwxQXJS0UkAMPdAHARDG";
//        JSONObject postmap = new JSONObject();
//        Map<String, Object> color = new HashMap<String, Object>();
//        color.put("r", 0);
//        color.put("g", 0);
//        color.put("b", 0);
//        postmap.put("scene", "111");
//        postmap.put("page", "pages/index/index");
//        postmap.put("width", 430);
//        postmap.put("auto_color", false);
//        postmap.put("line_color", color);
//        postmap.put("is_hyaline", false);
//        OutputStreamWriter out = null;
//        BufferedReader reader = null;
//        String response = null;
//        try {
//            URL httpUrl = null; //HTTP URL类 用这个类来创建连接
//            //创建URL
//            httpUrl = new URL(url);
//            //建立连接
//            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setRequestProperty("connection", "keep-alive");
//            conn.setRequestProperty("opensessionid", "opensessionidtest");
//            conn.setUseCaches(false);//设置不要缓存
//            conn.setInstanceFollowRedirects(true);
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            conn.connect();
//            //POST请求
//            out = new OutputStreamWriter(conn.getOutputStream());
//            out.write(JSONObject.toJSONString(postmap));
//            System.out.print("返回图片buffer信息：" + JSONObject.toJSONString(postmap));
//            out.flush();
//            //读取响应
//            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            InputStream iss = conn.getInputStream();
//            InputStream inputStream=conn.getInputStream();
//            byte[] data=new byte[1024];
//            StringBuffer sb1=new StringBuffer();
//            int length=0;
//            while ((length=inputStream.read(data))!=-1){
//                String s=new String(data, Charset.forName("utf-8"));
//                sb1.append(s);
//            }
//            System.out.println(sb1.toString());
//            inputStream.close();
//            conn.disconnect();
////            response = UploadUtil.uploadInputStream(iss);
////            System.out.println(response);//UploadCtyunUtil.uploadInputStream(iss);
////            reader.close();
////            // 断开连接
////            conn.disconnect();
//        } catch (Exception e) {
//            System.out.println("发送 POST 请求出现异常！" + e);
//            e.printStackTrace();
//        }
//        //使用final块来关闭输出流、输入流
//        finally {
//            try {
//                if (out != null) {
//                    out.close();
//                }
//                if (reader != null) {
//                    reader.close();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
}
