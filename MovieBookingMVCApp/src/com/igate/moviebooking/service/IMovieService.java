package com.igate.moviebooking.service;

import java.util.ArrayList;

import com.igate.moviebooking.dto.Booking;
import com.igate.moviebooking.dto.Theater;
import com.igate.moviebooking.exception.MovieException;

/*
 * Service Interface , declaring method signatures to be implemented in Service Impl class
 */
public interface IMovieService {
	public ArrayList<String> getMovieNames() throws MovieException;

	public ArrayList<Theater> searchMovie(String movieName)
			throws MovieException;

	public int makeBooking(Booking booking, int theaterId)
			throws MovieException;
}
