package com.igate.ch10.filters;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class LogFilter
 */

/*
 * This filter will now execute for all the URL patterns coming from web
 * resources As we are now implementing Filter <I> all methods of interface need
 * to be implemented
 */
@WebFilter(filterName = "TimeFilter", urlPatterns = { "/*" })
public class LogFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String ipAddress = request.getRemoteAddr(); 
        //Log the IP address and current timestamp. 
		System.out.println("IP "+ipAddress + ", Time "+ new 
                                                                      Date().toString()); 
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
