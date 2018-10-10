package com.igate.moviebooking.filters;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.igate.moviebooking.dto.Booking;

/*
 * Creating a filter to log the details of a particular movie booking on logout action. 
 */
@WebFilter(filterName = "MovieLogFilter", urlPatterns = { "/*" }, initParams = { @WebInitParam(name = "path", value = "/WEB-INF/log4j.properties") }, dispatcherTypes = { DispatcherType.REQUEST })
public class MovieLogFilter implements Filter {

	// Obtaining a logger
	Logger logger = Logger.getLogger(MovieLogFilter.class);

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		String action = httpRequest.getParameter("action");
		RequestDispatcher view = null;
		if (action != null && action.equalsIgnoreCase("Logout")) {
			Booking book = (Booking) session.getAttribute("bookingDetails");
			if (book != null) {
				logger.info("Movie booking Id:::::"
						+ session.getAttribute("bookingId"));
				logger.info("Booking Details:::::" + "Theater Name:::"
						+ book.getTheaterName() + "Total Price:::"
						+ book.getTotalPrice() + "No. of Tickets::"
						+ book.getNoOfTickets() + "Movie Play Time:::"
						+ book.getMoviePlayTime() + " Booking Date::::"
						+ book.getBookingDate());
				chain.doFilter(request, response);
			} else {
				request.setAttribute("errMsg", "Invalid Request");
				view = httpRequest.getRequestDispatcher("pages/error.jsp");
				view.forward(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		/*
		 * Reading the properties file (log4j.properties) located in  WEB-INF folder 
		 */
		String contextPath = fConfig.getServletContext().getRealPath("/");
		String logFilePath = contextPath + fConfig.getInitParameter("path");
		PropertyConfigurator.configure(logFilePath);
	}

}
