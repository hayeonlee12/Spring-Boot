package com.codingbox.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.PatternMatchUtils;

import com.codingbox.web.session.SessionConst;

public class LoginCheckFilter implements Filter{

	private static final String[] whitelist = { "/", "/members/add", "/login", "/logout", "/css/*" };
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest
			= (HttpServletRequest) request;
		String requestURI = httpRequest.getRequestURI();
		HttpServletResponse httpResponse
			= (HttpServletResponse) response;
		
		try {
			System.out.println("인증 필터 체크 시작 : " + requestURI);
			
			if(isLoginCheckPath(requestURI)) { // whitelist가 아닌 uri 만을 검증
				System.out.println("인증 체크 로직 실행 : " + requestURI);
				HttpSession session = httpRequest.getSession(false);
				
				if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
					System.out.println("미인증 사용자 요청 : " + requestURI);
					// 로그인으로 redirect
					// 예를 들어, 내가 로그인 하지 않은 상태로 items url을 접근 -> 로그인 페이지가 보여져야 한다.
					httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
					return; // 여기가 중요, 미인증 사용자는 다음으로 진행하지 않고 종료
				}
			}
			// 다음으로 진행
			chain.doFilter(request, response);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("인증 체크 필터 종료 : " + requestURI);
		}
	}
	
	// 화이트 리스트의 경우 인증 체크 하지 않음
	private boolean isLoginCheckPath(String requestURI) {
		/*
		 * PatternMatchUtils : 간단한 패턴 매치를 판별하기 위한 유틸
		 */
		return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
	}
	
}
