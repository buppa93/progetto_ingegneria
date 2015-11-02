package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Agency;

public class DAOTableAgency 
{
	DbAccess db;
	Connection con;
	private static final String SELECT_ALL = "SELECT * FROM agenzie;";
	
	public DAOTableAgency(DbAccess db) throws DatabaseConnectionException
	{
		db.initConnection();
		con = db.getConnection();
	}
	
	public ArrayList<Agency> getAllAgency() throws SQLException
	{
		ArrayList<Agency> agencies = new ArrayList<Agency>();
		PreparedStatement stat = con.prepareStatement(SELECT_ALL);
		ResultSet rs = stat.executeQuery();
		while(rs.next())
		{
			agencies.add(new Agency(rs.getString(1),rs.getString(2),rs.getString(3)));
		}
		rs.close();
		stat.close();
		return agencies;
	}
}
