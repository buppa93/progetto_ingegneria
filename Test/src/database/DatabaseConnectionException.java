package database;

import view.SQLWarning;

public class DatabaseConnectionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//TODO  modellare il fallimento nella connessione al database
	
	public DatabaseConnectionException(String msg)
	{super(msg);}

	public DatabaseConnectionException() {}

	public void NoValue()
	{new SQLWarning();}

}
