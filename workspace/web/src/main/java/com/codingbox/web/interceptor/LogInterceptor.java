package com.codingbox.web.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LogInterceptor implements HandlerInterceptor{
	
	public static final String LOG_ID = "logId";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String requestURI = request.getRequestURI();
		String uuid = UUID.randomUUID().toString();
		
		request.setAttribute(LOG_ID, uuid);
		
		System.out.println("[interceptor] uuid : " + uuid);
		System.out.println("[interceptor] requestURI : " + requestURI);
		
		return true;	// false 로 전환시 진행하지 않는다.
		
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("[interceptor] postHandle : " + modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String requestURI = request.getRequestURI();
		String logId = (String) request.getAttribute(LOG_ID);
		
		System.out.println("[interceptor] logId : " + logId);
		System.out.println("[interceptor] requestURI : " + requestURI);
	}
}
