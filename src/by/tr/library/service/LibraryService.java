package by.tr.library.service;

import java.util.ArrayList;

import by.tr.library.bean.Book;
import by.tr.library.service.exception.ServiceException;

public interface LibraryService {
	Book findBookByAuthor(String author)throws ServiceException;
	Book findBookByTitle(String title)throws ServiceException;
	Book findBookByPrice(String price)throws ServiceException;
	boolean addBook(String title, String author, String price)throws ServiceException;
	boolean deleteBook(String title, String author)throws ServiceException;
	ArrayList<Book> getCatalog()throws ServiceException;
}
