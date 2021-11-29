package com.czx.yimem.config;

import com.czx.yimem.Filter.JwtAuthenticationFilter;
import com.czx.yimem.Filter.JwtAuthorizationFilter;
import com.czx.yimem.exception.JwtAccessDeniedHandler;
import com.czx.yimem.exception.JwtAuthenticationEntryPoint;
import com.czx.yimem.service.admin.MembserService;
import com.czx.yimem.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.sql.DataSource;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 * @description Spring Security配置类
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    MembserService membserService;
    /**
     * 密码编码器
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService createUserDetailsService() {
        return userDetailsServiceImpl;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 设置自定义的userDetailsService以及密码编码器
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                // 禁用 CSRF
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/mobile_sing").permitAll()
                // 指定路径下的资源需要验证了的用户才能访问
                .antMatchers("/admin/**").authenticated()
//                .antMatchers(HttpMethod.DELETE, "/admin/**").hasRole("ADMIN")//采用注解
                // 其他都放行了
                .anyRequest().permitAll()
                .and()
                //添加自定义Filter
                .addFilter(new JwtAuthenticationFilter(authenticationManager(),membserService))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userDetailsServiceImpl))
                // 不需要session（不创建会话）
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 授权异常处理
                .exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                .accessDeniedHandler(new JwtAccessDeniedHandler());
        // 防止H2 web 页面的Frame 被拦截
        http.headers().frameOptions().disable();
    }
    public static void main(String[] strs){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456789"));
    }
}
