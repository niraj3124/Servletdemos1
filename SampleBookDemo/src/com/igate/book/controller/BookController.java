package com.igate.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.igate.book.dto.Book;
import com.igate.book.exception.BookException;
import com.igate.book.service.BookServiceImpl;
import com.igate.book.service.IBookService;

/**
 * Servlet implementation class BookController
 * Acting as front controller. Where all requests are dispatched from this controller
 */
@WebServlet("/BookController")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("action");
		PrintWriter pw = response.getWriter();
		RequestDispatcher view = null;
		IBookService service = new BookServiceImpl();
		if (operation != null && "add".equals(operation)) {
			view = request.getRequestDispatcher("addBook.html");
			view.forward(request, response);
		}
		if (operation != null && "Append Book".equals(operation)) {
			String bookName = request.getParameter("bookName");
			LocalDate publishDate = LocalDate.parse(request
					.getParameter("publishDate"));
			float bookPrice = Float.parseFloat(request
					.getParameter("bookPrice"));
			int bookQty = Integer.parseInt(request.getParameter("bookQty"));
			Book book = new Book();
			book.setBookName(bookName);
			book.setBookPrice(bookPrice);
			book.setPublishDate(publishDate);
			book.setAvailableQty(bookQty);
			try {
				int records = service.insertBook(book);
				if (records != 0) {
					view = request.getRequestDispatcher("success.html");
					view.forward(request, response);
				} else {
					pw.println("Inserting Book details failed");
					view = request.getRequestDispatcher("error.html");
					view.include(request, response);
				}
			} catch (BookException e) {
				pw.println("Error while inserting book details");
				view = request.getRequestDispatcher("error.html");
				view.include(request, response);
			}
		} if(operation != null && "view".equals(operation)) {
			try {
				ArrayList<Book> bookList = service.showBooks();
				if (bookList != null) {
					Iterator<Book> bookIterator = bookList.iterator();
					pw.println("<body>");
					pw.println("<table border='1'>");
					pw.println("<tr>");
					pw.println("<th>Book ISBN</th>");
					pw.println("<th>Book Name</th>");
					pw.println("<th>Book Publish Date</th>");
					pw.println("<th>Book Price</th>");
					pw.println("<th>Book Quantity</th>");
					pw.println("</tr>");
					while(bookIterator.hasNext()) {
						Book book = bookIterator.next();
						pw.println("<tr>");
						pw.println("<td>"+book.getIsbn()+"</td>");
						pw.println("<td>"+book.getBookName()+"</td>");
						pw.println("<td>"+book.getPublishDate()+"</td>");
						pw.println("<td>"+book.getBookPrice()+"</td>");
						pw.println("<td>"+book.getAvailableQty()+"</td>");
						pw.println("</tr>");
					}
					pw.println("</table>");
					pw.println("<a href='index.html'>Go Back to Home</a>");
					pw.println("</body>");
				} else {
					pw.println("Fetching Book details failed");
					view = request.getRequestDispatcher("error.html");
					view.include(request, response);
				}
			} catch (BookException e) {
				pw.println("Error while fetching book details");
				view = request.getRequestDispatcher("error.html");
				view.include(request, response);
			}
		}
	}

}
