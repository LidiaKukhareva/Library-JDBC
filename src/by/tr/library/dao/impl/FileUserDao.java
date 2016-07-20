package by.tr.library.dao.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tr.library.bean.Book;
import by.tr.library.dao.UserDao;
import by.tr.library.dao.exception.DAOException;

public class FileUserDao implements UserDao{
	
	private static final String filename = "catalog.txt";
	private final static Logger log = LogManager.getRootLogger();
	
	@Override
	public ArrayList<Book> getCatalog() throws DAOException{
		ArrayList<Book> catalog = makeCatalog();
		return catalog;
	}
	
	@Override
	public Book getBookByTitle(String title) throws DAOException {
		ArrayList<Book> catalog = makeCatalog();
		for (int i = 0; i < catalog.size(); i++){
			if ((catalog.get(i).getTitle()).equals(title)){
				return catalog.get(i);		
			}
		}
		return null;
	}
	
	@Override
	public Book getBookByAuthor(String author) throws DAOException {
		ArrayList<Book> catalog = makeCatalog();
		for (int i = 0; i < catalog.size(); i++){
			if ((catalog.get(i).getAuthor()).equals(author)){
				return catalog.get(i);		
			}
		}
		return null;
	}
	
	
	@Override
	public Book getBookByPrice(int price) throws DAOException {
		ArrayList<Book> catalog = makeCatalog();
		for (int i = 0; i < catalog.size(); i++){
			if (catalog.get(i).getPrice() == price){
				return catalog.get(i);		
			}
		}
		return null;
	}
	
	public static String getFilename(){
		return filename;
	}
	
	public static ArrayList<Book> makeCatalog() throws DAOException{
		
		ArrayList<Book> catalog = new ArrayList<Book>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(filename))){
			String s;
			while ((s = br.readLine()) != null){
				StringTokenizer tokens = new StringTokenizer(s);
				String title = tokens.nextToken();
				String author = tokens.nextToken();
				String price = tokens.nextToken();
				catalog.add(new Book(title, author, Integer.parseInt(price)));
			}
			
		}catch (IOException e){
			log.error("IOException: method makeCatalog, class FileUserDao");
			throw new DAOException("problems with file: " + filename);
		}catch (NoSuchElementException e){
			log.error("NoSuchElementException: method makeCatalog, class FileUserDao");
			throw new DAOException("problems with file: " + filename);
		}
		return catalog;
	}

	
}
