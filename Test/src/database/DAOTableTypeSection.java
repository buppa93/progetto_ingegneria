package database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import entity.TypeSection;

/**
 * Questa classe modella la tabella fascia del 
 * database seguendo il pattern DAO
 * @author giuseppe
 *
 */
public class DAOTableTypeSection 
{
	DbAccess db;
	Connection con;
	private static final String INSERT = "INSERT INTO fascia VALUES(?, ?, ?, ?);";
	private static final String FIND_BY_TYPE = "SELECT * FROM fascia WHERE tipo_vettura = ? ;";
	private static final String FIND_BY_NAME = "SELECT * FROM fascia WHERE nome = ? ;";
	
	/**
	 * Costruttore per la classe
	 * @throws DatabaseConnectionException
	 */
	public DAOTableTypeSection() throws DatabaseConnectionException
	{
		db = new DbAccess();
		db.initConnection();
		con = db.getConnection();
	}
	
	/**
	 * Inserisce una nuova fascia nel database
	 * @param section la fascia da inserire
	 * @throws SQLException
	 */
	public void insert(TypeSection section) throws SQLException
	{
		PreparedStatement stat = null;
		stat = con.prepareStatement(INSERT);
		stat.setString(1, section.getSFascia());
		stat.setInt(2, section.getN_porte());
		stat.setInt(3, section.getN_posti());
		stat.setString(4, section.getTipo_vettura());
		stat.executeUpdate();
		stat.close();
	}
	
	/**
	 * Cerca una fascia in base al tipo
	 * @param type utilitaria/berlina/autocarro/monovolume
	 * @return la fascia trovata
	 * @throws SQLException
	 */
	public TypeSection findByType(String type) throws SQLException 
	{
		PreparedStatement stat = null;
		ResultSet rs = null;
		TypeSection result = null;
		stat = con.prepareStatement(FIND_BY_TYPE);
		stat.setString(1, type);
		rs = stat.executeQuery();
		rs.next();
		result = new TypeSection(rs.getString("nome"), rs.getInt("n_porte"), 
				rs.getInt("n_posti"), rs.getString("tipo_vettura"));
		rs.close();
		stat.close();
		return result;
	}
	
	/**
	 * Cerca una fascia in base al nome
	 * @param name A/B/C/D
	 * @return la fascia trovata
	 * @throws SQLException
	 */
	public TypeSection findByName(String name) throws SQLException 
	{
		PreparedStatement stat = null;
		ResultSet rs = null;
		TypeSection result = null;
		stat = con.prepareStatement(FIND_BY_NAME);
		stat.setString(1, name);
		rs = stat.executeQuery();
		rs.next();
		result = new TypeSection(rs.getString("nome"), rs.getInt("n_porte"), 
				rs.getInt("n_posti"), rs.getString("tipo_vettura"));
		rs.close();
		stat.close();
		return result;
	}
	
	/**
	 * Chiude la connessione
	 * @throws SQLException
	 */
	public void closeConncetion() throws SQLException
	{
		con.close();
	}
}
