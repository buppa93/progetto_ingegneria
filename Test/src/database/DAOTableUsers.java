package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import utility.MyUtil;
import entity.User;

public class DAOTableUsers 
{
	DbAccess db;
	Connection con;
	private static final String INSERT = "INSERT INTO user VALUES(?, ?, ?, ?, ?, ?);";
	private static final String SEARCH_BY_ID = "SELECT * FROM user WHERE id_user=?;";
	private static final String SEARCH_BY_NAME_PASS = "SELECT * FROM user WHERE (nome=? AND password=?);";
	
	public DAOTableUsers(DbAccess db) throws DatabaseConnectionException
	{
		db.initConnection();
		con = db.getConnection();
	}
	
	public int insert(User u) throws SQLException
	{
		int result = 0;
		PreparedStatement stat = con.prepareStatement(INSERT);
		stat.setString(1, u.getId());
		stat.setString(2, u.getName());
		stat.setString(3, u.getSurname());
		stat.setString(4, u.getTelephoneNumber());
		stat.setString(5, u.getPassword());
		stat.setString(6, u.getType());
		result = stat.executeUpdate();
		stat.close();
		return result;
	}
	
	public User searchTypeUserByNamePass (String name, String passw) throws SQLException
	{
		User result = null;
		passw = MyUtil.getMD5(passw);
		PreparedStatement stat = con.prepareStatement(SEARCH_BY_NAME_PASS);
		stat.setString(1, name);
		stat.setString(2, passw);
		ResultSet rs = stat.executeQuery();
		rs.next();
		result = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6), rs.getString(5));
		System.out.println(result.toString());
		rs.close();
		stat.close();
		return result;
	}
	
	public User searchById(String id) throws SQLException
	{
		User c = null;
		ResultSet rs = null;
		PreparedStatement stat = con.prepareStatement(SEARCH_BY_ID);
		stat.setString(1, id);
		rs = stat.executeQuery();
		rs.next();
		c = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6),
				rs.getString(5));
		rs.close();
		stat.close();
		return c;
	}
	
	public static String makeId()
	{
		Random random = new Random();
		int j = 1;
		int n = 9-j;
		int k = 0;
		
		StringBuffer result = new StringBuffer (5);
		for(int i=1; i<6; i++)
		{
			k = random.nextInt(n)+j;
			result.append(k);
		}
		return result.toString();
	}
	public void closeConncetion() throws SQLException {con.close();}

}
