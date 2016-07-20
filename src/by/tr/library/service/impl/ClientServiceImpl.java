package by.tr.library.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tr.library.dao.AdminDao;
import by.tr.library.dao.CommonDao;
import by.tr.library.dao.DAOFactory;
import by.tr.library.dao.exception.DAOException;
import by.tr.library.service.ClientService;
import by.tr.library.service.exception.ServiceException;

public class ClientServiceImpl implements ClientService{

	private final static Logger log = LogManager.getRootLogger();
	
	@Override
	public boolean logination(String login, String password) throws ServiceException{

		if (login == null || login.isEmpty()){
			return false;
		}
		
		if (password == null || password.isEmpty()){
			return false;
		}
				
		DAOFactory factory = DAOFactory.getInstance();
		CommonDao commonDao = factory.getCommonDao();
		
		boolean result;
		try {
			result = commonDao.logination(login, password);
		} catch (DAOException e) {
			log.error("DAOException: method logination, class ClientServiceImpl");
			throw new ServiceException("service message", e);
		}
		
		return result;
	}

	@Override
	public boolean registration(String login, String password) throws ServiceException {
		
		if (login == null || login.isEmpty()){
			return false;
		}
		
		if (password == null || password.isEmpty()){
			return false;
		}
		
		DAOFactory factory = DAOFactory.getInstance();
		CommonDao commonDao = factory.getCommonDao();
		
		boolean result;
		try {
			result = commonDao.registration(login, password);
		} catch (DAOException e) {
			log.error("DAOException: method registration, class ClientServiceImpl");
			throw new ServiceException("service message", e);
		}
		
		return result;
	}

	@Override
	public boolean deleteUser(String login) throws ServiceException {
		
		if (login == null || login.isEmpty()){
			return false;
		}
		
		DAOFactory factory = DAOFactory.getInstance();
		AdminDao aDao = factory.getAdminDao();
		
		boolean result;
		try {
			result = aDao.deleteUser(login);
		} catch (DAOException e) {
			log.error("DAOException: method deleteUser, class ClientServiceImpl");
			throw new ServiceException("service message", e);
		}
		
		return result;
	}

}
