package by.tr.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tr.library.dao.CommonDao;
import by.tr.library.dao.connection.ConnectionPool;
import by.tr.library.dao.connection.ConnectionPoolException;
import by.tr.library.dao.exception.DAOException;

public class SQLCommonDao implements CommonDao{

	private final static Logger log = LogManager.getRootLogger();
	
	@Override
	public boolean logination(String login, String password)
			throws DAOException {
		
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		boolean res = false;
		
		try{
			con = ConnectionPool.getInstance().takeConnection();
			String sql = "SELECT login, password FROM users WHERE login=? AND password=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, login);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()){
				res = true;
			}
		}
		catch(ConnectionPoolException e){
			log.error("ConnectionPoolException: method logination, class SQLCommonDao");
			throw new DAOException("Logination fail", e);
		}
		catch(SQLException e){
			log.error("SQLException: method logination, class SQLCommonDao");
			e.printStackTrace();
			throw new DAOException("Logination fail", e);
		}
		finally{
			if (ps != null){
				ConnectionPool.close(ps);
			}
			if (rs != null){
				ConnectionPool.close(rs);
			}
			if (con != null){
				ConnectionPool.getInstance().closeConnection(con);
			}
		}
		return res;
	}

	@Override
	public boolean registration(String login, String password)
			throws DAOException {
		
		Connection con = null;
		PreparedStatement ps = null;
		boolean res = false;
		
		try{
			con = ConnectionPool.getInstance().takeConnection();
			String sql = "INSERT INTO users (login, password) VALUES(?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, login);
			ps.setString(2, password);
			ps.executeUpdate();
			res = true;
		}
		catch(ConnectionPoolException e){
			log.error("ConnectionPoolException: method registration, class SQLCommonDao");
			throw new DAOException("Registration fail", e);
		}
		catch(SQLException e){
			log.error("SQLException: method registration, class SQLCommonDao");
			throw new DAOException("Registration fail", e);
		}
		finally{
			if (ps != null){
				ConnectionPool.close(ps);
			}
			if (con != null){
				ConnectionPool.getInstance().closeConnection(con);
			}
		}
		return res;
			
	}

}
