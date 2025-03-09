package com.vmeknowledge.config;

import com.vmeknowledge.interceptor.LoginCheckInterceptor;
import com.vmeknowledge.interceptor.RateLimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private LoginCheckInterceptor loginCheckinterceptor;
//
//    @Autowired
//    private RateLimitInterceptor rateLimitInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(rateLimitInterceptor)
//                .addPathPatterns("/**"); // 配置需要限流的接口路径
//        registry.addInterceptor(loginCheckinterceptor).addPathPatterns("/**")
//                .excludePathPatterns("/error");
//    }
}
