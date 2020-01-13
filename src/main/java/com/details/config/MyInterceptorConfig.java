package com.details.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/***
 * @author zlp
 * @date 16:40 2020/1/13
 */
@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {

    @Bean
    HandlerInterceptor getUserInterceptor() {
        return new MyHandlerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getUserInterceptor()).addPathPatterns().excludePathPatterns();
    }
}
