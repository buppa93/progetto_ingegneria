package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utility.KeyValuePair;
import entity.Agency;

/**
 * Questa classe modella la tabella agenzie del 
 * database seguendo il pattern DAO
 * @author giuseppe
 *
 */
public class DAOTableAgency 
{
	DbAccess db;
	Connection con;
	private static final String SELECT_ALL = "SELECT * FROM agenzie;";
	private static final String REMOVE = "DELETE FROM agenzie WHERE numero = ?;";
	private static final String INSERT ="INSERT INTO agenzie VALUES(?,?,?);";
	private static final String SEARCH_BY_NUMBER_NAME = "SELECT * FROM agenzie WHERE (numero=? AND nome=?);";
	private StringBuffer SEARCH_DYNAMIC = new StringBuffer("SELECT * FROM clienti WHERE ");
	
	/**
	 * Costruttore per la classe
	 * @param db 
	 * @throws DatabaseConnectionException
	 */
	public DAOTableAgency(DbAccess db) throws DatabaseConnectionException
	{
		db.initConnection();
		con = db.getConnection();
	}
	
	/**
	 * Preleva tutte le agenzie presenti nella 
	 * tabella
	 * @return ArrayList<Agency> Lista contenente le agenzie																
	 * @throws SQLException
	 */
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

	public void delete(String id) throws SQLException 
	{
		PreparedStatement stat = con.prepareStatement(REMOVE);
		stat.setString(1, id);
		stat.executeUpdate();
		stat.close();
	}
	
	public int insert(Agency a) throws SQLException
	{
		int result = 0;
		PreparedStatement stat = con.prepareStatement(INSERT);
		stat.setString(1, a.getNumber());
		stat.setString(2, a.getName());
		stat.setString(3, a.getAddress());
		result = stat.executeUpdate();
		stat.close();
		return result;
	}
	
	public Agency searchAgencyByNumberName(String number, String name) throws SQLException
	{
		Agency agency = null;
		ResultSet rs = null;
		PreparedStatement stat = con.prepareStatement(SEARCH_BY_NUMBER_NAME);
		stat.setString(1, number);
		stat.setString(2, name);
		rs = stat.executeQuery();
		rs.next();
		agency = new Agency(rs.getString(1), rs.getString(2), rs.getString(3));
		rs.close();
		stat.close();
		return agency;
	}
	
	public ArrayList<Agency> dynamicSearch(ArrayList<KeyValuePair<String,String>> params) throws SQLException
	{
		ArrayList<Agency> agency = new ArrayList<Agency>();
		int nParams = params.size();
		PreparedStatement stat = null;
		ResultSet rs = null;
		
		switch(nParams)
		{
			case 1:
				SEARCH_DYNAMIC.append(params.get(0).getKey()+"='"+params.get(0).getValue()+"';");
				stat = con.prepareStatement(SEARCH_DYNAMIC.toString());
				rs = stat.executeQuery();
				System.out.println("Ecco la query: "+stat.toString());
				while(rs.next())
				{agency.add(new Agency(rs.getString(1),rs.getString(2),rs.getString(3)));}
				rs.close();
				stat.close();
				break;
			case 2:
				SEARCH_DYNAMIC.append("("+params.get(0).getKey()+"='"+params.get(0).getValue()+"' AND ");
				SEARCH_DYNAMIC.append(params.get(1).getKey()+"='"+params.get(1).getValue()+"');");
				stat = con.prepareStatement(SEARCH_DYNAMIC.toString());
				System.out.println("Ecco la query: "+stat.toString());
				rs = stat.executeQuery();
				while(rs.next())
				{agency.add(new Agency(rs.getString(1),rs.getString(2),rs.getString(3)));}
				rs.close();
				stat.close();
				break;
			case 3:
				SEARCH_DYNAMIC.append("("+params.get(0).getKey()+"='"+params.get(0).getValue()+"' AND ");
				SEARCH_DYNAMIC.append(params.get(1).getKey()+"='"+params.get(1).getValue()+"' AND ");
				SEARCH_DYNAMIC.append(params.get(2).getKey()+"='"+params.get(2).getValue()+"');");
				stat = con.prepareStatement(SEARCH_DYNAMIC.toString());
				System.out.println("Ecco la query: "+stat.toString());
				rs = stat.executeQuery();
				while(rs.next())
				{agency.add(new Agency(rs.getString(1),rs.getString(2),rs.getString(3)));}
				rs.close();
				stat.close();
				break;
		}
		return agency;
	}
}
