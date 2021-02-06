package com.gcase.feat.interceptors.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 * 拦截器注册
 * <p>
 *
 * @creator bicheng.deng
 * @createTime 2021/1/27
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public AuthInterceptor getAuthInterceptor(){
        return new AuthInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAuthInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login");
    }
}
