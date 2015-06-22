package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utility.MyUtil;
import entity.Client;

public class TableClient
{
	public static final String FIELD_ID = "id_cliente";
	public static final String FIELD_NAME = "nome";
	public static final String FIELD_SURNAME = "cognome";
	public static final String FIELD_PWD = "pwd";
	public static final String FIELD_PHONE = "num_telefono";
	
	DbAccess db;
	
	public TableClient(DbAccess db)
	{this.db = db;}
	
	public void insertClient (Client client)
	{
		try 
		{
			Statement st = db.getConnection().createStatement();
			st.executeUpdate("INSERT INTO "+DbString.TBL_CLIENTS+" "
					+ "("+FIELD_ID+","+FIELD_NAME+","+FIELD_SURNAME+","+FIELD_PHONE+") values ('" +client.getId() +",'" + client.getName() +
							",'" + client.getSurname() + "','" + client.getTelephoneNumber() + "')");
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

}
