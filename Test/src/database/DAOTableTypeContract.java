package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entity.TypeContract;

/**
 * Questa classe modella la tabella tipo_contratto del 
 * database seguendo il pattern DAO
 * @author giuseppe
 *
 */
public class DAOTableTypeContract 
{
	DbAccess db;
	Connection con;
	
	private static final String INSERT = "INSERT INTO tipo_contratto VALUES (?, ?, ?, ?, ?, ?, ?);";
	private static final String SELECT_TYPE = "SELECT * FROM tipo_contratto WHERE (tipo_noleggio=? AND tipo_chilometraggio=? AND fascia=?"
			+ " AND km=?);";
	private static final String SELECT_TYPE_NOL = "SELECT tipo_noleggio FROM tipo_contratto WHERE id_tipo=?;";
	private static final String SELECT_TYPE_KM = "SELECT tipo_chilometraggio FROM tipo_contratto WHERE id_tipo=?;";
	private static final String SELECT_PRICE_KM = "SELECT prezzo_km FROM tipo_contratto WHERE id_tipo=?;";
	private static final String SELECT_ALL = "SELECT * FROM tipo_contratto;";
	private static final String SELECT_KM = "SELECT km FROM tipo_contratto WHERE id_tipo=?;";
	
	/**
	 * Costruttore per la classe
	 * @param db
	 * @throws DatabaseConnectionException
	 */
	public DAOTableTypeContract(DbAccess db) throws DatabaseConnectionException
	{
		db.initConnection();
		con = db.getConnection();
	}
	
	/**
	 * Inserisce un nuovo tipo di contratto
	 * @param typeC tipo di contratto da inserire
	 * @throws SQLException
	 */
	public void insert(TypeContract typeC) throws SQLException
	{
		PreparedStatement stat = con.prepareStatement(INSERT);
		stat.setString(1, typeC.getId());
		stat.setString(2, typeC.getTypeNol());
		stat.setString(3, typeC.getChilometraggio());
		stat.setString(4, typeC.getFascia());
		stat.setInt(5, typeC.getKm());
		stat.setDouble(6, typeC.getPrice());
		stat.setDouble(7, typeC.getPricePerKm());
		stat.executeUpdate();
		stat.close();
	}
	
	/**
	 * Preleva tutti i tipi di contratto 
	 * presenti sul database
	 * @return ArrayList<TypeContract> ArrayList contenente i tipi di contratto presenti nel database
	 * @throws SQLException
	 */
	public ArrayList<TypeContract> getAll() throws SQLException
	{
		ArrayList<TypeContract> all = new ArrayList<TypeContract>();
		ResultSet rs = null;
		PreparedStatement stat = con.prepareStatement(SELECT_ALL);
		rs = stat.executeQuery();
		while(rs.next())
		{
			all.add(new TypeContract(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
					rs.getInt(5),rs.getDouble(6),rs.getDouble(7)));
		}
		rs.close();
		stat.close();
		return all;
	}
	
	/**
	 * Ricava un tipo di contratto cercato in base ai parametri
	 * @param typeNol tipo di noleggio Giornaliero/settimanale
	 * @param typeKm tipo di chilometraggio limitato/illimitato
	 * @param fascia la fascia dell'automobile A/B/C/D
	 * @param km massimi km percorribili
	 * @return il tipo di contratto trovato
	 * @throws SQLException
	 */
	public TypeContract selectTypeContract(String typeNol, String typeKm, String fascia, int km) throws SQLException
	{
		TypeContract typeC = null;
		ResultSet rs = null;
		PreparedStatement stat = con.prepareStatement(SELECT_TYPE);
		
		stat.setString(1, typeNol);
		stat.setString(2, typeKm);
		stat.setString(3, fascia);
		if(typeKm.equalsIgnoreCase("illimitato"))
			stat.setInt(4, 0);
		else
			stat.setInt(4, km);
		
		rs = stat.executeQuery();
		
		rs.next();
		typeC = new TypeContract(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
				rs.getDouble(6), rs.getDouble(7));
		
		rs.close();
		stat.close();
		return typeC;
	}
	
	/**
	 * Ritorna il nome del tipo di contratto 
	 * @param id id del tipo di contratto
	 * @return String stringa contenente il nome del tipo di contratto
	 * @throws SQLException
	 */
	public String getTypeContractNameById(String id) throws SQLException
	{
		ResultSet rs = null;
		String tipoNol = "";
		PreparedStatement stat = con.prepareStatement(SELECT_TYPE_NOL);
		stat.setString(1, id);
		rs = stat.executeQuery();
		rs.next();
		tipoNol = rs.getString(1);
		rs.close();
		stat.close();
		return tipoNol;
	}
	
	/**
	 * Ritorna il tipo di chilometraggio
	 * @param id id del tipo di contratto
	 * @return String limitato/illimitato
	 * @throws SQLException
	 */
	public String getTypeKmById(String id) throws SQLException
	{
		ResultSet rs = null;
		String tipoKm = "";
		PreparedStatement stat = con.prepareStatement(SELECT_TYPE_KM);
		stat.setString(1, id);
		rs = stat.executeQuery();
		rs.next();
		tipoKm = rs.getString(1);
		rs.close();
		stat.close();
		return tipoKm;
	}
	
	/**
	 * Ritorma i massimi km percorribili
	 * @param id id del tipo di contratto
	 * @return int massimi km percorribili
	 * @throws SQLException
	 */
	public int getKmById(String id) throws SQLException
	{
		ResultSet rs = null;
		int km = 0;
		PreparedStatement stat = con.prepareStatement(SELECT_KM);
		stat.setString(1, id);
		rs = stat.executeQuery();
		rs.next();
		km = rs.getInt(1);
		rs.close();
		stat.close();
		return km;
	}
	
	/**
	 * Ritorna il prezzo per km 
	 * @param id id del tipo di contratto
	 * @return double il prezzo per km
	 * @throws SQLException
	 */
	public double getPricePerKmById(String id) throws SQLException
	{
		double pricePerKm = 0;
		ResultSet rs = null;
		PreparedStatement stat = con.prepareStatement(SELECT_PRICE_KM);
		stat.setString(1, id);
		rs = stat.executeQuery();
		rs.next();
		pricePerKm = rs.getDouble(1);
		rs.close();
		stat.close();
		return pricePerKm;
	}
	
	/**
	 * Chiude la connessione
	 * @throws SQLException
	 */
	public void closeConncetion() throws SQLException
	{con.close();}
}
