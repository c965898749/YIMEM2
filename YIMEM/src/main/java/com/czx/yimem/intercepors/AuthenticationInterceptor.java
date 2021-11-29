package com.czx.yimem.intercepors;

import com.czx.yimem.entity.User;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.UserService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.PassToken;
import com.czx.yimem.utils.UserLoginToken;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.HandlerMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 拦截器
 * @author qiaoyn
 * @date 2019/06/14
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (Xtool.isNull(token)) {
                    httpServletResponse.setContentType(Constants.CONTENT_TYPE);
                    httpServletResponse.getWriter().append(JSON.toJSONString(new BaseResp(Constants.OVER_CODE,Constants.OVERDUE_TOKEN))).flush();
                    return false;
                }
                // 获取 token 中的 user id
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                    System.out.println(userId);
                } catch (JWTDecodeException j) {
                    httpServletResponse.setContentType(Constants.CONTENT_TYPE);
                    httpServletResponse.getWriter().append(JSON.toJSONString(new BaseResp(Constants.FAIL_CODE,Constants.OVERDUE_TOKEN))).flush();
                    return false;
                }
                Integer id=Integer.parseInt(userId);
                User user = userService.selectByPrimaryKey(id);
                if (user == null) {
                    httpServletResponse.setContentType(Constants.CONTENT_TYPE);
                    httpServletResponse.getWriter().append(JSON.toJSONString(new BaseResp(Constants.FAIL_CODE,Constants.NO_FOUND_USER))).flush();
                    return false;
                }
                // 验证 token,只需验证合法性所以不取值
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getOpenid())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    httpServletResponse.setContentType(Constants.CONTENT_TYPE);
                    httpServletResponse.getWriter().append(JSON.toJSONString(new BaseResp(Constants.OVER_CODE, Constants.OVERDUE_TOKEN))).flush();
                    return false;
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}


