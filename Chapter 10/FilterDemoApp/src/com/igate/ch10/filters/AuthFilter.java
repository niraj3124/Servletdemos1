package com.igate.ch10.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Servlet Filter implementation class AuthFilter Verifies the user credentials
 * passed via init-params
 */

@WebFilter(filterName = "AuthFilter", urlPatterns = { "/HelloServlet" }, initParams = {
		@WebInitParam(name = "username", value = "igate"),
		@WebInitParam(name = "password", value = "igate123") })
public class AuthFilter implements Filter {
	String username, password;

	/*
	 * Here the annotation @WebInitParam is used with name and value pair
	 * specifying username and password used for authentication. These
	 * parameters could be retrieved in init-method of filter. Observe that URL
	 * Pattern attribute is mapped to HelloServlet, that means before request
	 * goes to HelloServlet it would pass through AuthFilter
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		PrintWriter pw = response.getWriter();

		// These credentials obtained from login.html
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");

		if (name.equals(username) && pwd.equals(password)) {
			chain.doFilter(request, response);
		} else {
			pw.println("Invalid usser credentials, cannot enter inside::: Invalid user name given is:::"
					+ name);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fc) throws ServletException {
		username = fc.getInitParameter("username");
		password = fc.getInitParameter("password");

	}
}
