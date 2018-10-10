package com.igate.moviebooking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.igate.moviebooking.dto.Booking;
import com.igate.moviebooking.dto.Theater;
import com.igate.moviebooking.exception.MovieException;
import com.igate.moviebooking.util.DBUtil;

/*
 * DAO class for database interaction
 */
public class MovieDAOImpl implements IMovieDAO {
	Statement statement = null;
	ResultSet rsSet = null;
	PreparedStatement preparedStatement = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.igate.moviebooking.dao.IMovieDAO#getMovieNames() Method to fetch
	 * the movie names from database returning an arraylist of type String with
	 * movie names
	 */
	public ArrayList<String> getMovieNames() throws MovieException {
		ArrayList<String> movieNames = new ArrayList<String>();
		Connection connection = DBUtil.obtainConnection();
		String sql = "SELECT movie_name FROM movies";
		try {
			statement = connection.createStatement();
			rsSet = statement.executeQuery(sql);
			while (rsSet.next()) {
				movieNames.add(rsSet.getString("Movie_Name"));
			}
		} catch (SQLException e) {
			throw new MovieException("Error while database interaction:::"
					+ e.getMessage());
		}
		return movieNames;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.igate.moviebooking.dao.IMovieDAO#searchMovie(java.lang.String)
	 * Method to fetch the theater details playing a particular type of movie,
	 * returning an arraylist of type theaters with relevant theater details,
	 * ticket price and number of tickets available
	 */
	@Override
	public ArrayList<Theater> searchMovie(String movieName)
			throws MovieException {
		ArrayList<Theater> theaterList = new ArrayList<Theater>();
		Connection connection = DBUtil.obtainConnection();
		String sql = "SELECT theaterId,theaterName,AvailableTickets,moviePlayTime,price FROM movies m,theaters t WHERE m.movieId=t.movieId AND movie_Name=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, movieName);
			rsSet = preparedStatement.executeQuery();
			while (rsSet.next()) {
				Theater theater = new Theater();
				theater.setTheaterId(rsSet.getInt("THEATERID"));
				theater.setTheaterName(rsSet.getString("THEATERNAME"));
				theater.setAvailableTickets(rsSet.getInt("AVAILABLETICKETS"));
				theater.setMoviePlayTime(rsSet.getTimestamp("MOVIEPLAYTIME"));
				theater.setTicketPrice(rsSet.getDouble("PRICE"));

				theaterList.add(theater);
			}
		} catch (SQLException e) {
			throw new MovieException("Error while database interaction:::"
					+ e.getMessage());
		}

		return theaterList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.igate.moviebooking.dao.IMovieDAO#makeBooking(com.igate.moviebooking
	 * .dto.Booking, int) 
	 * Method to confirm movie booking. Inserting in ticket details in Booking table and 
	 * updating the number of tickets in the theater table. Returing int as Booking id
	 */
	public int makeBooking(Booking booking, int theaterId)
			throws MovieException {
		int bookingId = 0;
		Connection connection = DBUtil.obtainConnection();
		String sequenceSql = "SELECT bookSeq.nextval FROM DUAL";
		String insertSql = "INSERT INTO movieBooking VALUES(?,?,?,?,?,?)";
		String updateSql = "UPDATE theaters SET availableTickets=availableTickets-? WHERE theaterId=?";
		PreparedStatement pstmt = null;
		try {
			statement = connection.createStatement();
			rsSet = statement.executeQuery(sequenceSql);
			if (rsSet.next()) {
				bookingId = rsSet.getInt(1);
			}

			preparedStatement = connection.prepareStatement(insertSql);
			preparedStatement.setInt(1, bookingId);
			preparedStatement.setString(2, booking.getTheaterName());
			preparedStatement.setInt(3, booking.getNoOfTickets());
			preparedStatement.setFloat(4, booking.getTotalPrice());
			preparedStatement.setTimestamp(5, booking.getMoviePlayTime());
			preparedStatement.setDate(6, booking.getBookingDate());

			int insertRows = preparedStatement.executeUpdate();

			if (insertRows != 0) {
				pstmt = connection.prepareStatement(updateSql);
				pstmt.setInt(1, booking.getNoOfTickets());
				pstmt.setInt(2, theaterId);

				int updateRows = pstmt.executeUpdate();

				if (updateRows != 0) {
					return bookingId;
				}
			}

		} catch (SQLException e) {
			throw new MovieException("Error while database interaction:::"
					+ e.getMessage());
		}
		return 0;
	}
}
