package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utility.KeyValuePair;
import entity.Client;

public class DAOTableClients 
{
	DbAccess db;
	Connection con;
	private static final String INSERT = "INSERT INTO clienti VALUES(?, ?, ?);";
	private static final String SEARCH = "SELECT * FROM clienti WHERE(nome=? AND cognome=? AND telefono=?);";
	private static final String SEARCH_BY_PHONE = "SELECT * FROM clienti WHERE telefono=?;";
	private static final String REMOVE = "DELETE FROM clienti WHERE telefono = ?;";
	private StringBuffer SEARCH_DYNAMIC = new StringBuffer("SELECT * FROM clienti WHERE ");
	
	
	public DAOTableClients(DbAccess db) throws DatabaseConnectionException
	{
		db.initConnection();
		con = db.getConnection();
	}
	
	public int insert(Client c) throws SQLException
	{
		int result = 0;
		PreparedStatement stat = con.prepareStatement(INSERT);
		stat.setString(1, c.getName());
		stat.setString(2, c.getSurname());
		stat.setString(3, c.getPhone());
		result = stat.executeUpdate();
		stat.close();
		return result;
	}
	
	public Client search(String name, String surname, String phone) throws SQLException
	{
		Client c = null;
		ResultSet rs = null;
		PreparedStatement stat = con.prepareStatement(SEARCH);
		stat.setString(1, name);
		stat.setString(2, surname);
		stat.setString(3, phone);
		rs = stat.executeQuery();
		rs.next();
		c = new Client(rs.getString(1), rs.getString(2), rs.getString(3));
		rs.close();
		stat.close();
		return c;
	}
	
	public Client searchClientByPhone(String phone) throws SQLException
	{
		Client client = null;
		ResultSet rs = null;
		PreparedStatement stat = con.prepareStatement(SEARCH_BY_PHONE);
		stat.setString(1, phone);
		rs = stat.executeQuery();
		rs.next();
		client = new Client(rs.getString(1), rs.getString(2), rs.getString(3));
		rs.close();
		stat.close();
		return client;
	}
	
	public ArrayList<Client> dynamicSearch(ArrayList<KeyValuePair<String,String>> params) throws SQLException
	{
		ArrayList<Client> clients = new ArrayList<Client>();
		int nParams = params.size();
		PreparedStatement stat = null;
		ResultSet rs = null;
		
		switch(nParams)
		{
			case 1:
				SEARCH_DYNAMIC.append(params.get(0).getKey()+"='"+params.get(0).getValue()+"';");
				stat = con.prepareStatement(SEARCH_DYNAMIC.toString());
				rs = stat.executeQuery();
				while(rs.next())
				{clients.add(new Client(rs.getString(1),rs.getString(2),rs.getString(3)));}
				rs.close();
				stat.close();
				break;
			case 2:
				SEARCH_DYNAMIC.append("("+params.get(0).getKey()+"='"+params.get(0).getValue()+"' AND ");
				SEARCH_DYNAMIC.append(params.get(1).getKey()+"='"+params.get(1).getValue()+"');");
				stat = con.prepareStatement(SEARCH_DYNAMIC.toString());
				rs = stat.executeQuery();
				while(rs.next())
				{clients.add(new Client(rs.getString(1),rs.getString(2),rs.getString(3)));}
				rs.close();
				stat.close();
				break;
			case 3:
				SEARCH_DYNAMIC.append("("+params.get(0).getKey()+"='"+params.get(0).getValue()+"' AND ");
				SEARCH_DYNAMIC.append(params.get(1).getKey()+"='"+params.get(1).getValue()+"' AND ");
				SEARCH_DYNAMIC.append(params.get(2).getKey()+"='"+params.get(2).getValue()+"');");
				stat = con.prepareStatement(SEARCH_DYNAMIC.toString());
				rs = stat.executeQuery();
				while(rs.next())
				{clients.add(new Client(rs.getString(1),rs.getString(2),rs.getString(3)));}
				rs.close();
				stat.close();
				break;
		}
		return clients;
	}
	
	public void delete(String phone) throws SQLException
	{
		PreparedStatement stat = con.prepareStatement(REMOVE);
		stat.setString(1, phone);
		stat.executeUpdate();
		stat.close();
	}
	public void closeConncetion() throws SQLException {con.close();}

}
