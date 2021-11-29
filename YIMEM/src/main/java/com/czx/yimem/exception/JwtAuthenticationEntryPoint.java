package com.czx.yimem.exception;

import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.utils.Constants;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shuang.kou
 * @description AuthenticationEntryPoint 用来解决匿名用户访问需要权限才能访问的资源时的异常
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * 当用户尝试访问需要权限才能的REST资源而不提供Token或者Token错误或者过期时，
     * 将调用此方法发送401响应以及错误信息
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setContentType(Constants.CONTENT_TYPE);
        response.getWriter().append(JSON.toJSONString(new BaseResp(Constants.FAIL_CODE,Constants.UNAUTHORIZED))).flush();
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}
