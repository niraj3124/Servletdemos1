package com.igate.moviebooking.controller;

/*
 * MovieController servlet will dispatch control to different pages based upon the action parameter 
 */
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.igate.moviebooking.dto.Booking;
import com.igate.moviebooking.dto.Theater;
import com.igate.moviebooking.exception.MovieException;
import com.igate.moviebooking.service.IMovieService;
import com.igate.moviebooking.service.MovieServiceImpl;

/**
 * Servlet implementation class MovieController
 */
@WebServlet("/MovieController")
public class MovieController extends HttpServlet {

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
		String action = request.getParameter("action");
		RequestDispatcher view = null;
		IMovieService service = new MovieServiceImpl();
		// Initial setting the session parameter to false
		HttpSession session = request.getSession(false);
		// action to fetch movie names available
		if (action != null && action.equalsIgnoreCase("fetchName")) {
			try {
				ArrayList<String> movieNames = service.getMovieNames();
				request.setAttribute("movieNames", movieNames);
				view = getServletContext().getRequestDispatcher(
						"/pages/movieList.jsp");
				view.forward(request, response);
			} catch (MovieException e) {
				// dispatching control to error page
				request.setAttribute("errMsg", e.getMessage());
				view = getServletContext().getRequestDispatcher(
						"/pages/error.jsp");
				view.forward(request, response);
			}
		}

		// action to search theaters which play selected movie
		if (action != null && action.equalsIgnoreCase("Search")) {
			String movieName = request.getParameter("movieName");
			// if an existing session is not there, then creating a new one.
			if (session == null) {
				session = request.getSession(true);
			}
			session.setAttribute("movieName", movieName);
			try {
				ArrayList<Theater> theaterList = service.searchMovie(movieName);
				session.setAttribute("theaterList", theaterList);
				view = getServletContext().getRequestDispatcher(
						"/pages/showTheaters.jsp");
				view.forward(request, response);
			} catch (MovieException e) {
				request.setAttribute("errMsg", e.getMessage());
				view = getServletContext().getRequestDispatcher(
						"/pages/error.jsp");
				view.forward(request, response);
			}
		}
		
		// action to allow the user to avail booking for a particular movie 
		if (action != null && action.equalsIgnoreCase("Book")) {
			if (session != null) {
				int theaterId = Integer.parseInt(request
						.getParameter("theaterId"));
				ArrayList<Theater> theaterList = (ArrayList<Theater>) session
						.getAttribute("theaterList");
				for (Theater theaterDetails : theaterList) {
					if (theaterId == theaterDetails.getTheaterId()) {
						session.setAttribute("theaterDetails", theaterDetails);
					}
				}
				view = getServletContext().getRequestDispatcher(
						"/pages/bookNow.jsp");
				view.forward(request, response);
			} else {
				// dispatching control to error page if session is null
				request.setAttribute("errMsg", "Invalid Request");
				view = getServletContext().getRequestDispatcher(
						"/pages/error.jsp");
				view.forward(request, response);
			}
		}
		// action to confirm movie booking
		if (action != null && action.equalsIgnoreCase("ConfirmBook")) {
			if (session != null) {
				int quantity = Integer.parseInt(request
						.getParameter("quantity"));
				Theater theater = (Theater) session
						.getAttribute("theaterDetails");
				Booking booking = new Booking();
				booking.setTheaterName(theater.getTheaterName());
				booking.setNoOfTickets(quantity);
				float cost = (float) (theater.getTicketPrice() * quantity);
				float taxFees = (float) (cost * 10 / 100);
				float finalPrice = cost + taxFees;
				booking.setTotalPrice(finalPrice);
				booking.setMoviePlayTime(theater.getMoviePlayTime());
				LocalDate date = LocalDate.now();
				booking.setBookingDate(Date.valueOf(date));
				session.setAttribute("bookingDetails", booking);
				try {
					int bookingId = service.makeBooking(booking,
							theater.getTheaterId());
					if (bookingId != 0) {
						session.setAttribute("bookingId", bookingId);
						view = getServletContext().getRequestDispatcher(
								"/pages/success.jsp");
						view.forward(request, response);
					} else {
						request.setAttribute("errMsg",
								"Movie Booking not done.");
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				} catch (MovieException e) {
					request.setAttribute("errMsg", e.getMessage());
					view = getServletContext().getRequestDispatcher(
							"/pages/error.jsp");
					view.forward(request, response);
				}
			} else {
				request.setAttribute("errMsg", "Invalid Request");
				view = getServletContext().getRequestDispatcher(
						"/pages/error.jsp");
				view.forward(request, response);
			}
		}
		// action to logout and invalidate the session
		if (action != null && action.equalsIgnoreCase("Logout")) {
			if (session != null) {
				session.invalidate();
				view = getServletContext().getRequestDispatcher("/index.jsp");
				view.forward(request, response);
			} else {
				request.setAttribute("errMsg", "Invalid Request");
				view = getServletContext().getRequestDispatcher(
						"/pages/error.jsp");
				view.forward(request, response);
			}
		}
	}

}
