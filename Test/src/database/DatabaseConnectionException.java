package database;

import view.SQLWarning;

public class DatabaseConnectionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DatabaseConnectionException(String msg)
	{super(msg);}

	public DatabaseConnectionException() {}

	public void NoValue()
	{new SQLWarning();}

}
