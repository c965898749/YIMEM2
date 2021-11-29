package com.czx.yimem.Filter;

import com.czx.yimem.dto.LoginRequest;
import com.czx.yimem.entity.JwtUser;
import com.czx.yimem.entity.Member;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.exception.LoginFailedException;
import com.czx.yimem.service.admin.MembserService;
import com.czx.yimem.service.impl.UserDetailsServiceImpl;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.JwtTokenUtils;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author shuang.kou
 * @description 如果用户名和密码正确，那么过滤器将创建一个JWT Token 并在HTTP Response 的header中返回它，格式：token: "Bearer +具体token值"
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ThreadLocal<Boolean> rememberMe = new ThreadLocal<>();
    private final AuthenticationManager authenticationManager;
    private final MembserService membserService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, MembserService membserService) {
        this.authenticationManager = authenticationManager;
        // 设置URL，以确定是否需要身份验证
        this.membserService = membserService;
        super.setFilterProcessesUrl(Constants.AUTH_LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // 获取登录的信息
            LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
            rememberMe.set(loginRequest.getRememberMe());
            // 这部分和attemptAuthentication方法中的源码是一样的，
            // 只不过由于这个方法源码的是把用户名和密码这些参数的名字是死的，所以我们重写了一下
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(), loginRequest.getPassword());
            return authenticationManager.authenticate(authentication);
        } catch (IOException | AuthenticationException e) {
            response.setContentType(Constants.CONTENT_TYPE);
            if (e instanceof AuthenticationException) {
                try {
                    response.getWriter().append(JSON.toJSONString(new BaseResp(Constants.FAIL_CODE, Constants.LOGIN_FALL))).flush();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                throw new LoginFailedException(Constants.LOGIN_FALL);
            }
            try {
                response.getWriter().append(JSON.toJSONString(new BaseResp(Constants.FAIL_CODE,Constants.EXCEPTION))).flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            throw new LoginFailedException(e.getMessage());
        }
    }

    /**
     * 如果验证成功，就生成token并返回
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authentication) throws IOException {

        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        List<String> authorities = jwtUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        // 创建 Token
        String token = JwtTokenUtils.createToken(jwtUser.getUsername(), authorities, rememberMe.get());
        long expiration = rememberMe.get() ? Constants.EXPIRATION_REMEMBER : Constants.EXPIRATION;
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);
        rememberMe.remove();
        // Http Response Header 中返回 Token
//        response.setHeader(Constants.TOKEN_HEADER, token);
        response.setContentType(Constants.CONTENT_TYPE);
        Map map = new HashMap();
        map.put("token", token);
        Member member = membserService.findByMember(jwtUser.getUsername());
        member.setPassword("");
        map.put("user", member);
        map.put("overTime", expirationDate);
        response.getWriter().append(JSON.toJSONString(new BaseResp(Constants.SUCCEE_CODE, Constants.VERIFY_SUCCEE, map))).flush();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException {
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getMessage());
    }
}
