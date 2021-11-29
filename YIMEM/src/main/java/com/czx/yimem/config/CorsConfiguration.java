package com.czx.yimem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 * @description 跨域配置
 */
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                //暴露header中的其他属性给客户端应用程序
//                //如果不设置这个属性前端无法通过response header获取到Authorization也就是token
//                .exposedHeaders("Authorization")
//                .allowCredentials(true)
//                .allowedMethods("GET", "POST", "DELETE", "PUT")
//                .maxAge(3600);
        registry.addMapping("/**")     //允许的路径
                .allowedMethods("*")     //允许的方法
                .allowedOrigins("*")       //允许的网站
                .allowedHeaders("*")     //允许的请求头
                .allowCredentials(true)
                .maxAge(3600);
    }
}
