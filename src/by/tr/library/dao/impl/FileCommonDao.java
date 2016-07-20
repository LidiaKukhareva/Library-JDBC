package by.tr.library.dao.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tr.library.bean.User;
import by.tr.library.dao.CommonDao;
import by.tr.library.dao.exception.DAOException;

public class FileCommonDao implements CommonDao {

	private static final String filename = "users.txt";
	private final static Logger log = LogManager.getRootLogger();
	
	@Override
	public boolean logination(String login, String password) throws DAOException{
		ArrayList<User> users = makeUsers();	
		for (int i = 0; i < users.size(); i++){
			if (((users.get(i).getLogin()).equals(login))&&((users.get(i).getPassword()).equals(password))){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean registration(String login, String password) throws DAOException {
		ArrayList<User> users = makeUsers();
		for (int i = 0; i < users.size(); i++){
			if (((users.get(i).getLogin()).equals(login))&&((users.get(i).getPassword()).equals(password))){
				return false;
			}
		}
			
		User user = new User(login, password);
		FileAdminDao.writeToFile(user.toString(), filename, true);
		return true;
	}
	
	
	
	public static ArrayList<User> makeUsers() throws DAOException{	
		ArrayList<User> users = new ArrayList<User>();	
		try(BufferedReader br = new BufferedReader(new FileReader(filename))){
			String s;
			while ((s = br.readLine()) != null){
				StringTokenizer tokens = new StringTokenizer(s);
				String someLogin = tokens.nextToken();
				String somePassword = tokens.nextToken();
				users.add(new User(someLogin, somePassword));
			}
			
		}catch (IOException e){
			log.error("IOException: method makeUsers, class FileCommonDao");
			throw new DAOException("problems with file: " + filename);
		}catch (NoSuchElementException e){
			log.error("NoSuchElementException: method makeUsers, class FileCommonDao");
			throw new DAOException("problems with file: " + filename);
		}
		return users;
	}
	
	public static String getFilename(){
		return filename;
	}
}
