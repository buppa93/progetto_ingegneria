package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import view.SQLWarning;

public class DbAccess 
{
	private String DRIVER_CLASS_NAME = "org.gjt.mm.mysql.Driver";
	private final String DBMS = "jdbc:mysql";
	private final String SERVER = "localhost";
	private final String DATABASE = "test_ingegneria";
	private final String PORT = "3306";
	private final String USER_ID = "root";
	private final String PASSWORD = "";
	private Connection conn;
	
	public void initConnection() throws DatabaseConnectionException
	{
		String connectionString = DBMS+"://" + SERVER + ":" + PORT + "/" + DATABASE;
		try 
		{
			Class.forName(DRIVER_CLASS_NAME).newInstance();
		} 
		catch (Exception ex) 
		{
			new SQLWarning();
		}
		try 
		{
			conn = DriverManager.getConnection(connectionString, USER_ID, PASSWORD);
		} 
		catch (SQLException e) 
		{
			new SQLWarning();
		}
	}
	
	public Connection getConnection()
	{
		return this.conn;
	}
	
	public void closeConnection() throws SQLException
	{
		this.conn.close();
	}
	
	
}
