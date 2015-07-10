package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.Client;

public class TableClients
{
	public static final String FIELD_NAME = "nome";
	public static final String FIELD_SURNAME = "cognome";
	public static final String FIELD_PHONE = "telefono";
	
	DbAccess db;
	
	public TableClients(DbAccess db)
	{this.db = db;}
	
	public void insertClient (Client client)
	{
		try 
		{
			Statement st = db.getConnection().createStatement();
			String query = "INSERT INTO "+DbString.TBL_CLIENT+" "
					+ "("+FIELD_NAME+","+FIELD_SURNAME+","+FIELD_PHONE+") values ('"+ client.getName() +
					"','" + client.getSurname() + "','" + client.getPhone() +"');";
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

	public Client searchClient(String name, String surname, String phone)
	{
		Client client = null;
		String query = "SELECT * FROM "+DbString.TBL_CLIENT+" WHERE (nome='"+name+
				"' AND cognome='"+surname+"' AND telefono='"+phone+"');";
		try
		{
			Statement st = db.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next())
			{
				client = new Client(rs.getString(1), rs.getString(2), rs.getString(3));
			}
			rs.close();
			st.close();
		}
		catch (SQLException e) 
		{ 
			System.out.println("query non eseguita"); 
			e.printStackTrace();
		}
		return client;
	}

}
