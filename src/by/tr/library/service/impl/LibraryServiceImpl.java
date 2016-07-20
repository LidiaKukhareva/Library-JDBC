package by.tr.library.service.impl;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tr.library.bean.Book;
import by.tr.library.dao.AdminDao;
import by.tr.library.dao.DAOFactory;
import by.tr.library.dao.UserDao;
import by.tr.library.dao.exception.DAOException;
import by.tr.library.service.LibraryService;
import by.tr.library.service.exception.ServiceException;

public class LibraryServiceImpl implements LibraryService{

	private final static Logger log = LogManager.getRootLogger();
	
	@Override
	public Book findBookByAuthor(String author) throws ServiceException {
		
		if (author == null || author.isEmpty()){
			return null;
		}
		
		DAOFactory factory = DAOFactory.getInstance();
		UserDao userDao = factory.getUserDao();
		
		Book book;
		try {
			book = userDao.getBookByAuthor(author);
		} catch (DAOException e) {
			log.error("DAOException: method findByAuthor, class LibraryServiceImpl");
			throw new ServiceException("service message", e);
		}	
		return book;
	}

	@Override
	public Book findBookByTitle(String title) throws ServiceException {
		
		if (title == null || title.isEmpty()){
			return null;
		}
		
		DAOFactory factory = DAOFactory.getInstance();
		UserDao userDao = factory.getUserDao();
		
		Book book;
		try {
			book = userDao.getBookByTitle(title);
		} catch (DAOException e) {
			log.error("DAOException: method findByTitle, class LibraryServiceImpl");
			throw new ServiceException("service message", e);
		}	
		return book;
	}

	@Override
	public boolean addBook(String title, String author, String price) throws ServiceException{
		
		if ((title == null) || (author == null) || (price == null)){
			return false;
		}
		
		if ((title.isEmpty()) || (author.isEmpty()) || (price.isEmpty())){
			return false;
		}
		
		Scanner sc = new Scanner(price);
		if (!sc.hasNextInt()){
			return false;
		}
		
		Book book = new Book(title, author, Integer.parseInt(price));
		
		DAOFactory factory = DAOFactory.getInstance();
		AdminDao adminDAo = factory.getAdminDao();
		
		boolean result;
		try {
			 result = adminDAo.addNewBook(book);
		} catch (DAOException e) {
			log.error("DAOException: method addBook, class LibraryServiceImpl");
			throw new ServiceException("service message", e);
		}
		return result;
	}

	@Override
	public ArrayList<Book> getCatalog() throws ServiceException{
		
		DAOFactory factory = DAOFactory.getInstance();
		UserDao userDao = factory.getUserDao();
		
		ArrayList<Book> books;
		try {
			books = userDao.getCatalog();
		} catch (DAOException e) {
			log.error("DAOException: method getCatalog, class LibraryServiceImpl");
			throw new ServiceException("service message", e);
		}
		
		return books;
	}

	@Override
	public Book findBookByPrice(String price) throws ServiceException {
		
		Scanner sc = new Scanner(price);
		if (!sc.hasNextInt()){
			return null;
		}
		
		DAOFactory factory = DAOFactory.getInstance();
		UserDao userDao = factory.getUserDao();
		
		Book book;
		try {
			book = userDao.getBookByPrice(Integer.parseInt(price));
		} catch (DAOException e) {
			log.error("DAOException: method findByPrice, class LibraryServiceImpl");
			throw new ServiceException("service message", e);
		}	
		return book;
	}

	@Override
	public boolean deleteBook(String title, String author)throws ServiceException {
		
		if (title == null || title.isEmpty()){
			return false;
		}
		
		if (author == null || author.isEmpty()){
			return false;
		}
		
		DAOFactory factory = DAOFactory.getInstance();
		AdminDao aDao = factory.getAdminDao();
		
		boolean result;
		try {
			result = aDao.deleteBook(title, author);
		} catch (DAOException e) {
			log.error("DAOException: method deleteBook, class LibraryServiceImpl");
			throw new ServiceException("service message", e);
		}
		
		return result;
	}

}

















