package com.codingbox.web.login;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.codingbox.web.interceptor.LogInterceptor;
import com.codingbox.web.interceptor.LoginCheckInterceptor;

@Configuration
public class WebConfig2 implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LogInterceptor())
				.order(1)
				.addPathPatterns("/**")		// 모든 경로 전체 가능
				.excludePathPatterns("/css/**", "/*.ico", "/error"); // 특정 경로
		
		registry.addInterceptor(new LoginCheckInterceptor())
		.order(2)
		.addPathPatterns("/**")		// 모든 경로 전체 가능
		.excludePathPatterns("/css/**", "/*.ico", "/error", 
				"/members/add", "/login", "/logout", "/css/*" ); // 특정 경로
	}
	
	
}
