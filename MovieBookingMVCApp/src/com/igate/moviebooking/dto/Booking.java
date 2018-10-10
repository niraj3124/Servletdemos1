package com.igate.moviebooking.dto;

import java.sql.Date;
import java.sql.Timestamp;

/*
 * Booking DTO with booking detail attributes and respective getters and setters 
 */
public class Booking {
	private String theaterName;
	private int noOfTickets;
	private float totalPrice;
	private Timestamp moviePlayTime;
	private Date bookingDate;

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public int getNoOfTickets() {
		return noOfTickets;
	}

	public void setNoOfTickets(int noOfTickets) {
		this.noOfTickets = noOfTickets;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Timestamp getMoviePlayTime() {
		return moviePlayTime;
	}

	public void setMoviePlayTime(Timestamp moviePlayTime) {
		this.moviePlayTime = moviePlayTime;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

}
