package com.codingbox.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.codingbox.web.session.SessionConst;

public class LoginCheckInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		
		request.setAttribute(requestURI, response);
		HttpSession session = request.getSession();
		
		System.out.println("[interceptor] 인증 체크 로직 실행 : " + requestURI);
		
		if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
			System.out.println("미인증 사용자 요청 : " + requestURI);
			response.sendRedirect("/login?redirectURL=" + requestURI);
			
			return false;
		}
		
		return true;
	}
}
