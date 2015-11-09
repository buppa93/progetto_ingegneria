package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utility.KeyValuePair;
import view.SalesManView;
import entity.Auto;
import entity.TypeSection;

/**
 * Questa classe modella la tabella auto del 
 * database seguendo il pattern DAO
 * @author giuseppe
 *
 */
public class DAOTableAuto 
{
	DbAccess db = new DbAccess();
	Connection con;
	private static final String INSERT = "INSERT INTO auto VALUES (?, ? ,?, ?, ?, ? ,?, ?, ?);";
	private static final String SELECT_BY_TARGA = "SELECT * FROM auto WHERE targa = ? ;";
	private static final String SELECT_FREE_AFTER_DATA = "SELECT DISTINCT targa FROM contratto WHERE (agenzia_rest = ?"
			+ " AND data_fine < ?);";
	private static final String SELECT_FREE = "SELECT * FROM auto WHERE (fascia = ? AND id_agenzia = ? AND "
			+ "disponibilita = 1);";
	private static final String UPDATE_STATE = "UPDATE auto SET disponibilita= ? WHERE targa= ? ;";
	private static final String UPDATE_KM = "UPDATE auto SET km= ? WHERE targa= ? ;";
	private static final String UPDATE_AGENCY = "UPDATE auto SET id_agenzia= ? WHERE targa= ? ;";
	private static final String DELETE_BY_TARGA_AGENCY = "DELETE FROM auto WHERE (targa=? AND id_agenzia=?);";
	private StringBuffer SEARCH_DYNAMIC = new StringBuffer("SELECT * FROM auto WHERE (");
			
	/**
	 * Costruttore per la classe
	 * @param db
	 * @throws DatabaseConnectionException
	 */
	public DAOTableAuto(DbAccess db) throws DatabaseConnectionException
	{
		db.initConnection();
		con = db.getConnection();
	}
	
	/**
	 * Inserisce una nuova auto nel database
	 * @param auto la nuova automobile
	 * @return 1 se la query e' andata a buon fine
	 * @throws SQLException
	 */
	public int insert(Auto auto) throws SQLException
	{
		PreparedStatement stat = con.prepareStatement(INSERT);
		stat.setString(1, auto.getTarga());
		stat.setString(2, auto.getModel());
		stat.setString(3, auto.getBrand());
		stat.setInt(4, auto.getKm());
		stat.setNull(5, 1);
		stat.setNull(6, 1);
		stat.setString(7, auto.getIdAgenzia());
		stat.setString(8, auto.getFascia().getSFascia());
		stat.setInt(9, auto.getAvailabilityNumber());
		int result = stat.executeUpdate();
		stat.close();
		return result;
	}
	
	/**
	 * Cerca le auto che saranno disponibili dopo 
	 * x data
	 * @param dataEnd data di riferimento
	 * @return ArrayList<Auto> un ArrayList contenente le auto trovate
	 * @throws SQLException
	 */
	public List<Auto> searchFreeAfterData(String dataEnd) throws SQLException
	{
		ResultSet rs = null;
		List<String> targhe = new ArrayList<String>();
		List<Auto> auto = new ArrayList<Auto>();
		
		PreparedStatement stat = con.prepareStatement(SELECT_FREE_AFTER_DATA);
		stat.setString(1, SalesManView.session.filiale.getNumber());
		stat.setString(2, dataEnd);
		rs = stat.executeQuery();
		
		while(rs.next())
		{targhe.add(rs.getString(1));}
		
		stat = con.prepareStatement(SELECT_BY_TARGA);
		int size = targhe.size();
		for(int i=0; i<size;i++)
		{
			stat.setString(1,targhe.get(i));
			rs = stat.executeQuery();
			
			rs.next();
			auto.add(new Auto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),rs.getInt(5)));
		}
		
		rs.close();
		stat.close();
		return auto;
	}
	
	/**
	 * Ricerca tutte le auto di una certa categoria disponibili
	 * a partire da una x data
	 * @param fascia la fascia dell'auto
	 * @param dateStart data di riferimento
	 * @return ArrayList<Auto> un ArrayList contenente tutte le auto trovate
	 * @throws SQLException
	 */
	public List<Auto> searchFree(TypeSection fascia, String dateStart) throws SQLException
	{
		List<Auto> cars = new ArrayList<Auto>();
		ResultSet rs = null;
		PreparedStatement stat = con.prepareStatement(SELECT_FREE);
		
		stat.setString(1, fascia.getSFascia());
		stat.setString(2, SalesManView.session.filiale.getNumber());
		rs = stat.executeQuery();
		
		while(rs.next())
		{cars.add(new Auto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),rs.getInt(5)));}
		
		cars.addAll(searchFreeAfterData(dateStart));
		rs.close();
		stat.close();
		return cars;
	}
	
	/**
	 * Aggiorna lo stato di un auto
	 * @param state stato in cui settare
	 * @param targa targa dell'auto di riferimento
	 * @throws SQLException
	 */
	public void updateState(int state, String targa) throws SQLException
	{
		PreparedStatement stat = con.prepareStatement(UPDATE_STATE);
		stat.setInt(1,state);
		stat.setString(2, targa);
		stat.executeUpdate();
		stat.close();
	}
	
	/**
	 * Aggiorna i chilometri di un automobile
	 * @param km km da settare
	 * @param targa targa dell'auto di riferimento
	 * @throws SQLException
	 */
	public void updateKm(int km, String targa) throws SQLException
	{
		PreparedStatement stat = con.prepareStatement(UPDATE_KM);
		stat.setInt(1,km);
		stat.setString(2, targa);
		stat.executeUpdate();
		stat.close();
	}
	
	/**
	 * Aggiorna l'agenzia in cui l'auto si trova
	 * @param idAgency numero dell'agenzia da settare
	 * @param targa targa dell'auto di riferimento
	 * @throws SQLException
	 */
	public void updateAgency(String idAgency, String targa) throws SQLException
	{
		PreparedStatement stat = con.prepareStatement(UPDATE_AGENCY);
		stat.setString(1,idAgency);
		stat.setString(2, targa);
		stat.executeUpdate();
		stat.close();
	}
	
	/**
	 * Seleziona un auto in base alla targa
	 * @param targa la targa dell'auto
	 * @return Auto oggetto di tipo Auto
	 * @throws SQLException
	 * @throws DatabaseConnectionException
	 */
	public Auto selectByTarga(String targa) throws SQLException, DatabaseConnectionException
	{
		Auto auto = null;
		DAOTableTypeSection ts = new DAOTableTypeSection(); 
		TypeSection tsrs = null;
		ResultSet rs = null;
		PreparedStatement stat =con.prepareStatement(SELECT_BY_TARGA);
		stat.setString(1, targa);
		rs = stat.executeQuery();
		rs.next();
		tsrs = ts.findByName(rs.getString(8));
		auto = new Auto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
				rs.getString(6), rs.getString(7), tsrs, rs.getInt(9));
		ts.closeConncetion();
		stat.close();
		return auto;
	}
	
	/**
	 * Cancella un auto dal database
	 * @param targa targa dell'auto
	 * @param agency numero di agenzia in cui si trova l'auto
	 * @return 1 se la query e' andata a buon fine
	 * @throws SQLException
	 */
	public int deleteAutoByTargaAndAgency(String targa, String agency) throws SQLException
	{
			int result = 0;
			PreparedStatement stat = con.prepareStatement(DELETE_BY_TARGA_AGENCY);
			stat.setString(1, targa);
			stat.setString(2, agency);
			result = stat.executeUpdate();
			stat.close();
			return result;
	}
	
	/**
	 * Funzione che effettua una ricerca con una 
	 * lista di campi passati come parametro
	 * @param params lista di campi
	 * @return ArrayList<Auto> ArrayList contenente i clienti trovati
	 * @throws SQLException
	 * @throws DatabaseConnectionException
	 */
	public ArrayList<Auto> dynamicSearch(ArrayList<KeyValuePair<String,?>> params) throws SQLException, DatabaseConnectionException
	{
		ArrayList<Auto> cars = new ArrayList<Auto>();
		int size = params.size();
		for(int i=0; i<size-1;i++)
		{
			if(params.get(i).getKey().equals("km"))
			{
				SEARCH_DYNAMIC.append(params.get(i).getKey());
				SEARCH_DYNAMIC.append("=");
				SEARCH_DYNAMIC.append(params.get(i).getValue());
				SEARCH_DYNAMIC.append(" AND ");
			}
			else
			{
				SEARCH_DYNAMIC.append(params.get(i).getKey());
				SEARCH_DYNAMIC.append("='");
				SEARCH_DYNAMIC.append(params.get(i).getValue());	
				SEARCH_DYNAMIC.append("' AND ");
			}
		}
		
		SEARCH_DYNAMIC.append(params.get(params.size()-1).getKey());
		SEARCH_DYNAMIC.append("='");
		SEARCH_DYNAMIC.append(params.get(params.size()-1).getValue());
		SEARCH_DYNAMIC.append("');");
		PreparedStatement stat =con.prepareStatement(SEARCH_DYNAMIC.toString());
		ResultSet rs = stat.executeQuery();
		if(!rs.first())
		{return null;}
		else
		{
			DAOTableTypeSection ts = new DAOTableTypeSection(); 
			TypeSection tsrs = null;
			tsrs = ts.findByName(rs.getString(8));
			do
			{
				cars.add(new Auto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getString(7), tsrs, rs.getInt(9)));
			}
			while(rs.next());
		}
		return cars;
		
	}
	
	public void closeConncetion() throws SQLException
	{con.close();}
	
}