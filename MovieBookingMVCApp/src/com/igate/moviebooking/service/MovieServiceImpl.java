package com.igate.moviebooking.service;

import java.util.ArrayList;

import com.igate.moviebooking.dao.IMovieDAO;
import com.igate.moviebooking.dao.MovieDAOImpl;
import com.igate.moviebooking.dto.Booking;
import com.igate.moviebooking.dto.Theater;
import com.igate.moviebooking.exception.MovieException;
/*
 *  MovieServiceImpl class dispatching calls to the Database layer (DAOImpl)
 */
public class MovieServiceImpl implements IMovieService {
	IMovieDAO movieDAO;

	public MovieServiceImpl() {
		movieDAO = new MovieDAOImpl();
	}

	public ArrayList<String> getMovieNames() throws MovieException {
		return movieDAO.getMovieNames();
	}

	@Override
	public ArrayList<Theater> searchMovie(String movieName)
			throws MovieException {
		return movieDAO.searchMovie(movieName);
	}
	
	public int makeBooking(Booking booking, int theaterId)
			throws MovieException {
		return movieDAO.makeBooking(booking, theaterId);
	}
}
