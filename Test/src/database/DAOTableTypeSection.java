package database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import entity.TypeSection;

public class DAOTableTypeSection 
{
	DbAccess db;
	Connection con;
	private static final String INSERT = "INSERT INTO fascia VALUES(?, ?, ?, ?);";
	private static final String FIND_BY_TYPE = "SELECT * FROM fascia WHERE tipo_vettura = ? ;";
	private static final String FIND_BY_NAME = "SELECT * FROM fascia WHERE nome = ? ;";
	
	public DAOTableTypeSection() throws DatabaseConnectionException
	{
		db = new DbAccess();
		db.initConnection();
		con = db.getConnection();
	}
	
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
	
	public TypeSection findByName(String name) throws SQLException 
	{
		PreparedStatement stat = null;
		ResultSet rs = null;
		TypeSection result = null;
		System.out.println("****"+db+"****");
		System.out.println("****"+con+"*****");
		stat = con.prepareStatement(FIND_BY_NAME);
		stat.setString(1, name);
		System.out.println("ECCO: "+stat.toString());
		rs = stat.executeQuery();
		rs.next();
		result = new TypeSection(rs.getString("nome"), rs.getInt("n_porte"), 
				rs.getInt("n_posti"), rs.getString("tipo_vettura"));
		rs.close();
		stat.close();
		return result;
	}
	
	public void closeConncetion() throws SQLException
	{
		con.close();
	}
	
	/*public static void main(String args[]) throws DatabaseConnectionException, SQLException
	{
		System.out.println("############## TEST FASCIA DAO ###########");
		System.out.println("Inizializzo l'accesso al db.");
		DbAccess db = new DbAccess();
		db.initConnection();
		System.out.println("Provo ad inserire un nuovo record.");
		System.out.println("\"C - 9 - 9 - Test\"");
		System.out.println("Creo un'istanza della classe DAO");
		DAOTableTypeSection tts = new DAOTableTypeSection(db);
		tts.insert(new TypeSection("C",9,9,"Test"));
		System.out.println("Ricerco il record appena inserito.");
		TypeSection ts = tts.findByType("Test");
		System.out.println("Trovato: "+ts.toString());
		tts.closeConncetion();
		db.closeConnection();
	}*/
}
