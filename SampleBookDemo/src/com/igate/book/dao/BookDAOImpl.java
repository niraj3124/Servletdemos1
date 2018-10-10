package com.igate.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.igate.book.dto.Book;
import com.igate.book.exception.BookException;
import com.igate.book.util.DBUtil;

/*
 * Database class - with all the database queries
 */
public class BookDAOImpl implements IBookDAO {

	Connection connection = null;
	Statement statement = null;
	ResultSet rsSet = null;
	PreparedStatement preparedStatement = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.igate.book.dao.IBookDAO#insertBook(com.igate.book.dto.Book) Code
	 * to insert book record in table, returning int- specifying number of
	 * records that are inserted successfully
	 */
	@Override
	public int insertBook(Book book) throws BookException {
		connection = DBUtil.obtainConnection();
		String sql = "INSERT INTO BOOK VALUES(bookISBN.nextval,?,?,?,?)";
		int recordInserted = 0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, book.getBookName());
			preparedStatement.setDate(2,
					java.sql.Date.valueOf(book.getPublishDate()));
			preparedStatement.setFloat(3, book.getBookPrice());
			preparedStatement.setInt(4, book.getAvailableQty());

			recordInserted = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new BookException("Error while inserting values::"
					+ e.getMessage());
		}
		return recordInserted;
	}

	/*
	 * (non-Javadoc)
	 * @see com.igate.book.dao.IBookDAO#showBooks()
	 * Fetching book records from table. Storing in ArrayList and returning arraylist of type Book 
	 */
	@Override
	public ArrayList<Book> showBooks() throws BookException {
		ArrayList<Book> bookList = new ArrayList<Book>();
		connection = DBUtil.obtainConnection();
		String sql = "SELECT ISBN,BOOK_NAME,PUBLISH_DATE,PRICE,AVAILABLEQTY FROM BOOK";
		try {
			statement = connection.createStatement();
			rsSet = statement.executeQuery(sql);
			while (rsSet.next()) {
				Book book = new Book();
				book.setIsbn(rsSet.getInt("ISBN"));
				book.setBookName(rsSet.getString("BOOK_NAME"));
				book.setPublishDate(rsSet.getDate("PUBLISH_DATE").toLocalDate());
				book.setBookPrice(rsSet.getFloat("PRICE"));
				book.setAvailableQty(rsSet.getInt("AVAILABLEQTY"));

				bookList.add(book);
			}
		} catch (SQLException e) {
			throw new BookException("Error while fetching values::"
					+ e.getMessage());
		}
		return bookList;

	}
}
