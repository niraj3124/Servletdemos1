package com.igate.moviebooking.exception;

/*
 * User exception class to define custom exceptions wrapped around pre-defined exceptions like SQLException, Naming Exception
 */
public class MovieException extends Exception {
	public MovieException(String message) {
		super(message);
	}
}
