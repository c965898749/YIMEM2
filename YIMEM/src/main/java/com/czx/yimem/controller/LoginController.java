package com.czx.yimem.controller;

import com.czx.yimem.dto.WexinCode;
import com.czx.yimem.entity.User;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.UserService;
import com.czx.yimem.service.admin.MembserService;
import com.czx.yimem.service.impl.TokenService;
import com.czx.yimem.utils.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Slf4j
@RequestMapping("vpnservice")
@RestController
public class LoginController {



    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private MembserService membserService;

    // 登录
    @RequestMapping(value = "/login")
//    public BaseResp login(@RequestParam(value = "code", required = false) String code, HttpServletResponse response) {
    public BaseResp login(@RequestBody WexinCode wexinCode, HttpServletResponse response) {
        String code=null;
        if (wexinCode!=null){
            code=wexinCode.getCode();
        }
        JSONObject SessionKeyOpenId = OpenidUtill.getSessionKeyOrOpenId(code);
        log.info("post请求获取的SessionAndopenId=" + SessionKeyOpenId);
        String openid = SessionKeyOpenId.getString("openid");
        if (Xtool.isNotNull(openid)){
            User user = userService.selectByopenid(openid);
            if(user!=null){
                String sessionKey = SessionKeyOpenId.getString("session_key");
                log.info("openid=" + openid + ",session_key=" + sessionKey);
                String token = tokenService.getToken(user);
                log.info("用户"+user.toString());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("token", token);
                log.info("token:"+token);
                jsonObject.put("userId",user.getUserId());
                Cookie cookie = new Cookie("token", token);
                cookie.setPath("/");
                response.addCookie(cookie);
                return new BaseResp(Constants.SUCCEE_CODE,Constants.VERIFY_SUCCEE,jsonObject);
            }else{
                return new BaseResp(Constants.OVER_CODE,Constants.YE_FOUND_OPENID);
            }
        }else {
            return new BaseResp(Constants.FAIL_CODE,Constants.NO_FOUND_OPENID);
        }

    }

    /***
     * 这个请求需要验证token才能访问
     *
     * @author: qiaoyn
     * @date 2019/06/14
     * @return String 返回类型
     */
    @UserLoginToken
    @RequestMapping(value = "/getMessage")
    public String getMessage() {

        // 取出token中带的用户id 进行操作
        System.out.println(TokenUtil.getTokenUserId());

        return "您已通过验证";
    }


    @RequestMapping(value = "/account")
    public BaseResp getMessage(@RequestParam("account") String account) {
        return new BaseResp(Constants.SUCCEE_CODE,Constants.SUCCEE,membserService.findByMember(account));

    }

    //获取openid
    @RequestMapping(value = "/openid")
//    public BaseResp openid(@RequestParam(value = "code", required = false) String code, HttpServletResponse response) {
    public BaseResp openid(@RequestBody WexinCode wexinCode, HttpServletRequest request) {
        String code=null;
        if (wexinCode!=null){
            code=wexinCode.getCode();
        }
        JSONObject SessionKeyOpenId = OpenidUtill.getSessionKeyOrOpenId(code);
        log.info("post请求获取的SessionAndopenId=" + SessionKeyOpenId);
        String openid = SessionKeyOpenId.getString("openid");
        Map map=new HashMap();
        map.put("openid",openid);
        return new BaseResp(Constants.SUCCEE_CODE,Constants.SUCCEE,map);
    }

    /**
     * token刷新
     * @return
     */
    @UserLoginToken
    @RequestMapping(value = "/refresh")
    public BaseResp refresh() {
        // 取出token中带的用户id 进行操作
        Integer userId=Integer.parseInt(TokenUtil.getTokenUserId());
        try {
            User user=userService.selectByPrimaryKey(userId);
            if (user!=null){
                String newtoken=tokenService.getToken(user);
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("token",newtoken);
                jsonObject.put("userId",userId);
                return new BaseResp(Constants.SUCCEE_CODE,Constants.SUCCEE,jsonObject);
            }else {
                return new BaseResp(Constants.OVER_CODE,Constants.OVERDUE_TOKEN);
            }
        } catch (Exception e) {
            log.error("userId:"+userId+","+e.getMessage());
            return new BaseResp(Constants.FAIL_CODE,Constants.EXCEPTION);

        }

    }

}
