package com.igate.book.service;

import java.util.ArrayList;

import com.igate.book.dto.Book;
import com.igate.book.exception.BookException;

public interface IBookService {
	public int insertBook(Book book) throws BookException;
	public ArrayList<Book> showBooks() throws BookException;
}
