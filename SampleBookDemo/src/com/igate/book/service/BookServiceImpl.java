package com.igate.book.service;

import java.util.ArrayList;

import com.igate.book.dao.BookDAOImpl;
import com.igate.book.dao.IBookDAO;
import com.igate.book.dto.Book;
import com.igate.book.exception.BookException;
/*
 * Service layer - dispatching all calls to database layer
 */
public class BookServiceImpl implements IBookService {

	IBookDAO bookDAO;

	public BookServiceImpl() {
		bookDAO = new BookDAOImpl();
	}

	@Override
	public int insertBook(Book book) throws BookException {
		return bookDAO.insertBook(book);
	}

	@Override
	public ArrayList<Book> showBooks() throws BookException {
		return bookDAO.showBooks();
	}

}
