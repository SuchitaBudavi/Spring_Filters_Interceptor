package com.suchi.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ErrorHandlingFilter implements Filter{

	public void destroy() {
		System.out.println("ErrorHanlding Destroy");
		
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {				
				System.out.println("ErrorHandlingFilter: do filter");
				chain.doFilter(req, res);
			
	}

	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("Class: "+this.getClass()+" method "+this.getClass().getEnclosingMethod());		
		
	}

}
