package com.yzpc.yzpc_weixinapp.config;

import com.yzpc.yzpc_weixinapp.interceptor.LoginCheckInterceptor;
import com.yzpc.yzpc_weixinapp.interceptor.RoleCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author wq
 * @description 注册拦截器
 * @date 2024/12/20 13:31:12
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    LoginCheckInterceptor loginCheckInterceptor;
    @Resource
    RoleCheckInterceptor roleCheckInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/api/**").excludePathPatterns("/api/**/login","/*/doc.html");
        registry.addInterceptor(roleCheckInterceptor).addPathPatterns("/api/class/**");
    }
}
