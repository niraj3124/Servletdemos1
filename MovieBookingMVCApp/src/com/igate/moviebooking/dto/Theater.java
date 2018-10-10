package com.igate.moviebooking.dto;

import java.sql.Timestamp;

/*
 * Theater DTO with theater attributes and respective getters and setters
 */
public class Theater {
	private int theaterId;
	private String theaterName;
	private Timestamp moviePlayTime;
	private int availableTickets;
	private double ticketPrice;

	public int getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(int theaterId) {
		this.theaterId = theaterId;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public Timestamp getMoviePlayTime() {
		return moviePlayTime;
	}

	public void setMoviePlayTime(Timestamp moviePlayTime) {
		this.moviePlayTime = moviePlayTime;
	}

	public int getAvailableTickets() {
		return availableTickets;
	}

	public void setAvailableTickets(int availableTickets) {
		this.availableTickets = availableTickets;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

}
