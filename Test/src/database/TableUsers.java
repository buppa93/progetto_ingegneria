package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import utility.MyUtil;
import entity.User;

public class TableUsers
{
	public static final String FIELD_ID = "id_user";
	public static final String FIELD_NAME = "nome";
	public static final String FIELD_SURNAME = "cognome";
	public static final String FIELD_PWD = "password";
	public static final String FIELD_PHONE = "num_telefono";
	public static final String FIELD_TYPE = "tipo";
	
	DbAccess db;
	
	public TableUsers(DbAccess db)
	{this.db = db;}
	
	public void insertClient (User client)
	{
		try 
		{
			Statement st = db.getConnection().createStatement();
			String query = "INSERT INTO "+DbString.TBL_CLIENTS+" "
					+ "("+FIELD_ID+","+FIELD_NAME+","+FIELD_SURNAME+","+FIELD_PHONE+","+FIELD_PWD+","+FIELD_TYPE+") values ('" +client.getId()+"','"+ client.getName() +
					"','" + client.getSurname() + "','" + client.getTelephoneNumber() + "','" + client.getPassword() +"','" + client.getType() +"');";
			st.executeUpdate(query);
			st.close();
		} 
		catch (SQLException e) 
		{ 
			System.out.println("inserimento non eseguito"); 
			e.printStackTrace();
		}
	}
	
	public User getClientById (String ID)
	{
		User c = null;
		try 
		{
			Statement st = db.getConnection().createStatement();
			c = (User) st.executeQuery("SELECT * FROM "+DbString.TBL_CLIENTS+" WHERE "+FIELD_ID+"="+ID+";");
			st.close();
		} 
		catch (SQLException e) 
		{ 
			System.out.println("inserimento non eseguito"); 
			e.printStackTrace();
		}
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
	
	public String getTypeUserBynamepass (String name, String passw) throws SQLException
	{
		String result = "";
		String c="";
		passw = MyUtil.getMD5(passw);
		Statement st = db.getConnection().createStatement();
		c = "SELECT "+FIELD_TYPE +" FROM "+DbString.TBL_CLIENTS+" WHERE ("+FIELD_NAME+"='"+name+"' AND "+FIELD_PWD+"='"+passw+"');";
		ResultSet rs = st.executeQuery(c);
		rs.next();
		result=rs.getString(1);
		rs.close();
		st.close();
		
		return result;
	}
}
