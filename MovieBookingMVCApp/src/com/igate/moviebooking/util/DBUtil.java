package com.igate.moviebooking.util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.igate.moviebooking.exception.MovieException;
/*
 * DBUtil class to access a connection pool 
 */
public class DBUtil {
	static Connection connection;

	public static Connection obtainConnection() throws MovieException {
		try {
			InitialContext context = new InitialContext();
			DataSource source = (DataSource) context
					.lookup("java:/jdbc/ConPool");
			connection = source.getConnection();
		} catch (NamingException e) {
			throw new MovieException("Error while creating datascource::"
					+ e.getMessage());
		} catch (SQLException e) {
			throw new MovieException("Error while obtaining connection::"
					+ e.getMessage());
		}
		return connection;
	}
}
