package by.tr.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tr.library.bean.Book;
import by.tr.library.dao.AdminDao;
import by.tr.library.dao.connection.ConnectionPool;
import by.tr.library.dao.connection.ConnectionPoolException;
import by.tr.library.dao.exception.DAOException;

public class SQLAdminDao implements AdminDao {
	
	private final static Logger log = LogManager.getRootLogger();

	@Override
	public boolean addNewBook(Book book) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		boolean res = false;
		try{
			con = ConnectionPool.getInstance().takeConnection();
			String sql = "INSERT INTO catalog (title, author, price) VALUES(?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setInt(3, book.getPrice());
			ps.executeUpdate();
			res = true;
		}
		catch(ConnectionPoolException e){
			log.error("ConnectionPoolException: method addNewBook, class SQLAdminDao");
			throw new DAOException("Adding new book fail", e);
		}
		catch(SQLException e){
			log.error("SQLException: method addNewBook, class SQLAdminDao");
			throw new DAOException("Adding new book fail", e);
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

	@Override
	public boolean deleteUser(String login) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		boolean res = false;
		try{
			con = ConnectionPool.getInstance().takeConnection();
			String sql = "DELETE FROM Users WHERE login=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, login);
			if (ps.executeUpdate() != 0){
				res = true;
			}
		}
		catch(ConnectionPoolException e){
			log.error("ConnectionPoolException: method deleteUser, class SQLAdminDao");
			throw new DAOException("Deleting user fail", e);
		}
		catch(SQLException e){
			log.error("SQLException: method deleteUser, class SQLAdminDao");
			throw new DAOException("Deleting user fail", e);
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

	@Override
	public boolean deleteBook(String title, String author) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		boolean res = false;
		try{
			con = ConnectionPool.getInstance().takeConnection();
			String sql = "DELETE FROM catalog WHERE title=? AND author=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, author);
			if (ps.executeUpdate() != 0){
				res = true;
			}
		}
		catch(ConnectionPoolException e){
			log.error("ConnectionPoolException: method deleteBook, class SQLAdminDao");
			throw new DAOException("Deleting book fail", e);
		}
		catch(SQLException e){
			log.error("SQLException: method deleteBook, class SQLAdminDao");
			throw new DAOException("Deleting book fail", e);
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
