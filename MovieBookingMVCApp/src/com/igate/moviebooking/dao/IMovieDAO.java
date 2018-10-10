package com.igate.moviebooking.dao;

import java.util.ArrayList;

import com.igate.moviebooking.dto.Booking;
import com.igate.moviebooking.dto.Theater;
import com.igate.moviebooking.exception.MovieException;

/*
 * DAO Interface , declaring method signatures to be implemented in DAO Impl class
 */
public interface IMovieDAO {
	public ArrayList<String> getMovieNames() throws MovieException;

	public ArrayList<Theater> searchMovie(String movieName)
			throws MovieException;

	public int makeBooking(Booking booking, int theaterId)
			throws MovieException;
}
