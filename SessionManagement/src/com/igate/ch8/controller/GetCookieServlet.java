package com.igate.ch8.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/GetCookieServlet")
public class GetCookieServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<B>MyCookie are  : ");
		for (int i = 0; i < cookies.length; i++) {
			String name = cookies[i].getName();
			String value = cookies[i].getValue();
			out.println("name =  " + name + "; value = " + value + "<BR>");
		}
		out.close();
	}
}
