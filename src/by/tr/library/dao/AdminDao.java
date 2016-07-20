package by.tr.library.dao;

import by.tr.library.bean.Book;
import by.tr.library.dao.exception.DAOException;

public interface AdminDao {
	boolean addNewBook(Book book)throws DAOException;
	boolean deleteUser(String login)throws DAOException;
	boolean deleteBook(String title, String author)throws DAOException;
}
