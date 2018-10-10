package com.igate.ch10.filters;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Servlet Filter implementation class LocaleFilter
 */

/**
 * 
 * @author yv824177
 *Only 3 bundles are added , if more are required then add properties file.
 */
@WebFilter(filterName = "LocaleFilter", urlPatterns = { "/LocaleServlet" })
public class LocaleFilter implements Filter {

	ServletContext ctx;

	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		FileInputStream fis = null;
		Properties properties = new Properties();
		Locale locale = request.getLocale();
		String language = locale.getLanguage();
		String message;
		PrintWriter out = response.getWriter();

		String filename = "message_" + language + ".properties";
		String path = ctx.getRealPath("/WEB-INF/" + filename);
		try {
			fis = new FileInputStream(path);
			properties.load(fis);
			message = properties.getProperty("welcome.message");
			out.println(message);
			chain.doFilter(request, response);
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fg) throws ServletException {
		ctx = fg.getServletContext();
	}

}
