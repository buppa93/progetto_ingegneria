package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	public Client searchClientByPhone(String phone)
	{
		Client client = null;
		String query = "SELECT * FROM "+DbString.TBL_CLIENT+" WHERE telefono='"+phone+"';";
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

	public void deleteClientByPhone(String phone)
	{
		try {
			Statement st= db.getConnection().createStatement();
			String query="DELETE FROM "+ DbString.TBL_CLIENT + " WHERE "+ FIELD_PHONE+ "='"+phone+"';";
			System.out.println(query);
			st.execute(query);
			st.close();
		} catch (SQLException e) {
			System.out.println("eliminazione non eseguita"); 
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Client> searchByName(String name)
	{
		ArrayList<Client> list = new ArrayList<Client>();
		String query = "SELECT * FROM "+DbString.TBL_CLIENT+" WHERE nome='"+name+"';";
		try
		{
			Statement st = db.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next())
			{
				Client c = new Client(rs.getString(1), rs.getString(2), rs.getString(3));
				list.add(c);
			}
			rs.close();
			st.close();
		}
		catch (SQLException e) 
		{ 
			System.out.println("query non eseguita"); 
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Client> searchBySurname(String surname)
	{
		ArrayList<Client> list = new ArrayList<Client>();
		String query = "SELECT * FROM "+DbString.TBL_CLIENT+" WHERE cognome='"+surname+"';";
		try
		{
			Statement st = db.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next())
			{
				Client c = new Client(rs.getString(1), rs.getString(2), rs.getString(3));
				list.add(c);
			}
			rs.close();
			st.close();
		}
		catch (SQLException e) 
		{ 
			System.out.println("query non eseguita"); 
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Client> searchByPhone(String phone)
	{
		ArrayList<Client> list = new ArrayList<Client>();
		String query = "SELECT * FROM "+DbString.TBL_CLIENT+" WHERE telefono='"+phone+"';";
		try
		{
			Statement st = db.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next())
			{
				Client c = new Client(rs.getString(1), rs.getString(2), rs.getString(3));
				list.add(c);
			}
			rs.close();
			st.close();
		}
		catch (SQLException e) 
		{ 
			System.out.println("query non eseguita"); 
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Client> searchByNameAndSurname(String name, String surname)
	{
		ArrayList<Client> list = new ArrayList<Client>();
		String query = "SELECT * FROM "+DbString.TBL_CLIENT+" WHERE (nome='"+name+"' AND cognome='"+surname+"');";
		try
		{
			Statement st = db.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next())
			{
				Client c = new Client(rs.getString(1), rs.getString(2), rs.getString(3));
				list.add(c);
			}
			rs.close();
			st.close();
		}
		catch (SQLException e) 
		{ 
			System.out.println("query non eseguita"); 
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Client> searchByNameAndPhone(String name, String phone)
	{
		ArrayList<Client> list = new ArrayList<Client>();
		String query = "SELECT * FROM "+DbString.TBL_CLIENT+" WHERE (nome='"+name+"' AND telefono='"+phone+"');";
		try
		{
			Statement st = db.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next())
			{
				Client c = new Client(rs.getString(1), rs.getString(2), rs.getString(3));
				list.add(c);
			}
			rs.close();
			st.close();
		}
		catch (SQLException e) 
		{ 
			System.out.println("query non eseguita"); 
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Client> searchBySurnameAndPhone(String surname, String phone)
	{
		ArrayList<Client> list = new ArrayList<Client>();
		String query = "SELECT * FROM "+DbString.TBL_CLIENT+" WHERE (cognome='"+surname+"' AND telefono='"+phone+"');";
		try
		{
			Statement st = db.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next())
			{
				Client c = new Client(rs.getString(1), rs.getString(2), rs.getString(3));
				list.add(c);
			}
			rs.close();
			st.close();
		}
		catch (SQLException e) 
		{ 
			System.out.println("query non eseguita"); 
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Client> searchByAllField(String name, String surname, String phone)
	{
		ArrayList<Client> list = new ArrayList<Client>();
		String query = "SELECT * FROM "+DbString.TBL_CLIENT+" WHERE (nome='"+name+"' AND cognome='"+surname+"'"
				+ " AND telefono='"+phone+"');";
		try
		{
			Statement st = db.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next())
			{
				Client c = new Client(rs.getString(1), rs.getString(2), rs.getString(3));
				list.add(c);
			}
			rs.close();
			st.close();
		}
		catch (SQLException e) 
		{ 
			System.out.println("query non eseguita"); 
			e.printStackTrace();
		}
		return list;
	}
}
