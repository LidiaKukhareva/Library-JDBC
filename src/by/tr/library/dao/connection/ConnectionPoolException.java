package by.tr.library.dao.connection;

public class ConnectionPoolException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public ConnectionPoolException(String message, Exception e){
		super(message, e);
	}
}
