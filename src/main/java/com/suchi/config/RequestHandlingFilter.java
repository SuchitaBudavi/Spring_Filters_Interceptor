package com.suchi.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RequestHandlingFilter implements Filter{

	public void destroy() {
		System.out.println("RequestHandling Destroy");
		
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("RequestHandling doFilter");
		chain.doFilter(req, res);
	}

	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("RequestHandling Init");
		
	}

	
}
