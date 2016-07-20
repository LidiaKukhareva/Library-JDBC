package by.tr.library.dao;

import java.util.ArrayList;

import by.tr.library.bean.Book;
import by.tr.library.dao.exception.DAOException;

public interface UserDao {
	ArrayList<Book> getCatalog() throws DAOException;
	Book getBookByTitle(String title) throws DAOException;
	Book getBookByAuthor(String author) throws DAOException;
	Book getBookByPrice(int price) throws DAOException;
}
