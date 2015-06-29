package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import utility.MyUtil;
import entity.Client;

public class TableClient
{
	public static final String FIELD_ID = "id_user";
	public static final String FIELD_NAME = "nome";
	public static final String FIELD_SURNAME = "cognome";
	public static final String FIELD_PWD = "password";
	public static final String FIELD_PHONE = "num_telefono";
	public static final String FIELD_TYPE = "tipo";
	
	DbAccess db;
	
	public TableClient(DbAccess db)
	{this.db = db;}
	
	public void insertClient (Client client)
	{
		try 
		{
			Statement st = db.getConnection().createStatement();
			String query = "INSERT INTO "+DbString.TBL_CLIENTS+" "
					+ "("+FIELD_ID+","+FIELD_NAME+","+FIELD_SURNAME+","+FIELD_PHONE+","+FIELD_PWD+","+FIELD_TYPE+") values ('" +client.getId()+"','"+ client.getName() +
					"','" + client.getSurname() + "','" + client.getTelephoneNumber() + "','" + client.getPassword() +"','" + client.getType() +"');";
			System.out.println(query);
			st.executeUpdate(query);
			System.out.println(st.toString());
			st.close();
		} 
		catch (SQLException e) 
		{ 
			System.out.println("inserimento non eseguito"); 
			e.printStackTrace();
		}
	}
	
	public Client getClientById (String ID)
	{
		Client c = null;
		try 
		{
			Statement st = db.getConnection().createStatement();
			c = (Client) st.executeQuery("SELECT * FROM "+DbString.TBL_CLIENTS+" WHERE "+FIELD_ID+"="+ID+";");
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
		String id="";
		int j = 1;
		int n = 9-j;
		int k = 0;
		
		for(int i=1; i<6; i++)
		{
			k = random.nextInt(n)+j;
			id+=k;
		}
		System.out.println(id);
		return id;
	}

}
