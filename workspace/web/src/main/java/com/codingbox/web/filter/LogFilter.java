package com.codingbox.web.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LogFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestURI = httpRequest.getRequestURI();
		String uuid = UUID.randomUUID().toString();
		
		try {
			System.out.println("uuid : " + uuid);
			System.out.println("requestURI : " + requestURI);
			chain.doFilter(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("uuid : " + uuid);
			System.out.println("requestURI : " + requestURI);
		}
	}
	
}
