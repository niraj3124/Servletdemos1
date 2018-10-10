package com.igate.ch8.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/AddCookieServlet")
public class AddCookieServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String name = req.getParameter("name");
		String data = req.getParameter("data");
		String age = req.getParameter("age");

		Cookie cookie = new Cookie(name, data);

		if ((age == null) || (age.equals(""))) {
			res.addCookie(cookie);
			out.println("Set Cookie --- Name= " + name + " Value = " + data);
		} else {
			try {
				int intage = Integer.parseInt(age);
				cookie.setMaxAge(intage);
				res.addCookie(cookie);
				out.println("Set Cookie --- Name= " + name + " Value = " + data
						+ " age= " + age);
			} catch (Exception e) {
				out.println("Exception " + e);
			}
		}
		out.close();
	}
}
