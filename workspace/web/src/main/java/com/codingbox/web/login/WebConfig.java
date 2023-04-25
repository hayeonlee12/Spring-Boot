package com.codingbox.web.login;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codingbox.web.filter.LogFilter;

@Configuration
public class WebConfig {
	
	@Bean
	public FilterRegistrationBean logFilter() {
		FilterRegistrationBean<Filter> filterRegistrationBean
			= new FilterRegistrationBean<>();
		
		filterRegistrationBean.setFilter(new LogFilter());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.addUrlPatterns("/*");
		
		return filterRegistrationBean;
	}
	
	@Bean
	public FilterRegistrationBean loginCheckFilter() {
		FilterRegistrationBean<Filter> filterRegistrationBean
			= new FilterRegistrationBean<>();
		
		filterRegistrationBean.setFilter(new LogFilter());
		filterRegistrationBean.setOrder(2);
		filterRegistrationBean.addUrlPatterns("/*");
		
		return filterRegistrationBean;
	}
}
