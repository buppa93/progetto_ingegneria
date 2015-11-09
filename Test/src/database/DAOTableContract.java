package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utility.KeyValuePair;
import view.SQLWarning;
import entity.Contract;

/**
 * Questa classe modella la tabella contratto del 
 * database seguendo il pattern DAO
 * @author giuseppe
 *
 */
public class DAOTableContract 
{
	DbAccess db;
	Connection con;
	
	private static final String INSERT = "INSERT INTO contratto VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String SEARCH = "SELECT * FROM contratto WHERE (id_cliente=? AND targa=?);";
	private static final String REMOVE  = "DELETE FROM contratto WHERE numero_ordine=?;";
	private StringBuffer SEARCH_DYNAMIC = new StringBuffer("SELECT * FROM contratto WHERE ");
	
	/**
	 * Costruttore per la classe
	 * @param db
	 * @throws DatabaseConnectionException
	 */
	public DAOTableContract(DbAccess db) throws DatabaseConnectionException
	{
		db.initConnection();
		con = db.getConnection();
	}
	
	/**
	 * Inserisce un nuovo contratto nel database
	 * @param contract contratto da inserire
	 * @throws SQLException
	 */
	public void insert(Contract contract) throws SQLException
	{
		PreparedStatement stat = con.prepareStatement(INSERT);
		stat.setString(1, contract.getNumeroOrdine());
		stat.setString(2, contract.getAgencyId());
		stat.setString(3, contract.getClientId());
		stat.setString(4, contract.getDataInizio());
		stat.setInt(5,contract.getDuration());
		stat.setString(6, contract.getDataFine());
		stat.setString(7, contract.getAgencyReturn());
		stat.setString(8, contract.getTypeContract());
		stat.setDouble(9, contract.getDeposit());
		stat.setDouble(10, contract.getPrice());
		stat.setString(11, contract.getTarga());
		stat.executeUpdate();
		stat.close();
	}
	
	/**
	 * Elimina un contratto dal database
	 * @param id numero di contratto
	 * @throws SQLException
	 */
	public void remove(String id) throws SQLException
	{
		PreparedStatement stat = con.prepareStatement(REMOVE);
		stat.setString(1, id);
		stat.executeUpdate();
		stat.close();
	}
	
	/**
	 * Cerca un contratto
	 * @param phoneCli numero telefono cliente
	 * @param targa targa dell'auto noleggiata
	 * @return Contract il contratto trovato
	 * @throws SQLException
	 */
	public Contract searchContract (String phoneCli, String targa) throws SQLException
	{
		Contract contract = null;
		ResultSet rs = null;
		PreparedStatement stat = con.prepareStatement(SEARCH);
		stat.setString(1, phoneCli);
		stat.setString(2, targa);
		rs = stat.executeQuery();	
		rs.next();
		contract = new Contract(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), 
					rs.getString(6), rs.getString(7), rs.getString(11), rs.getString(8), rs.getDouble(9),
					rs.getDouble(10));
		
		
		rs.close();
		stat.close();
		return contract;
	}
	
	/**
	 * Funzione che effettua una ricerca attraverso una 
	 * lista di campi passati come paramelista di campitro
	 * @param params 
	 * @return ArrayList<Contract> ArrayList contenente i contratti trovati
	 * @throws SQLException
	 */
	public ArrayList<Contract> dynamicSearch(ArrayList<KeyValuePair<String,?>> params) throws SQLException
	{
		ArrayList<Contract> contracts = new ArrayList<Contract>();
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
				{
					contracts.add(new Contract(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(11),rs.getString(8),rs.getDouble(10),
						rs.getDouble(9)));
				}
				rs.close();
				stat.close();
				break;
			case 2:
				SEARCH_DYNAMIC.append("("+params.get(0).getKey()+"='"+params.get(0).getValue()+"' AND ");
				SEARCH_DYNAMIC.append(params.get(1).getKey()+"='"+params.get(1).getValue()+"');");
				stat = con.prepareStatement(SEARCH_DYNAMIC.toString());
				rs = stat.executeQuery();
				while(rs.next())
				{
				
					contracts.add(new Contract(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(11),rs.getString(8),rs.getDouble(10),
						rs.getDouble(9)));
				}
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
				{
				
					contracts.add(new Contract(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(11),rs.getString(8),rs.getDouble(10),
						rs.getDouble(9)));
				}
				rs.close();
				stat.close();
				break;
			case 4:
				SEARCH_DYNAMIC.append("("+params.get(0).getKey()+"='"+params.get(0).getValue()+"' AND ");
				SEARCH_DYNAMIC.append(params.get(1).getKey()+"='"+params.get(1).getValue()+"' AND ");
				SEARCH_DYNAMIC.append("("+params.get(0).getKey()+"='"+params.get(0).getValue()+"' AND ");
				SEARCH_DYNAMIC.append(params.get(2).getKey()+"='"+params.get(2).getValue()+"' AND ");
				SEARCH_DYNAMIC.append(params.get(3).getKey()+"='"+params.get(3).getValue()+"');");
				stat = con.prepareStatement(SEARCH_DYNAMIC.toString());
				rs = stat.executeQuery();
				while(rs.next())
				{
				
					contracts.add(new Contract(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(11),rs.getString(8),rs.getDouble(10),
						rs.getDouble(9)));
				}
				rs.close();
				stat.close();
				break;
			case 5:
				SEARCH_DYNAMIC.append("("+params.get(0).getKey()+"='"+params.get(0).getValue()+"' AND ");
				SEARCH_DYNAMIC.append(params.get(1).getKey()+"='"+params.get(1).getValue()+"' AND ");
				SEARCH_DYNAMIC.append("("+params.get(0).getKey()+"='"+params.get(0).getValue()+"' AND ");
				SEARCH_DYNAMIC.append(params.get(2).getKey()+"='"+params.get(2).getValue()+"' AND ");
				SEARCH_DYNAMIC.append(params.get(3).getKey()+"='"+params.get(3).getValue()+"' AND ");
				SEARCH_DYNAMIC.append(params.get(4).getKey()+"='"+params.get(4).getValue()+"');");
				stat = con.prepareStatement(SEARCH_DYNAMIC.toString());
				rs = stat.executeQuery();
				while(rs.next())
				{
				
					contracts.add(new Contract(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(11),rs.getString(8),rs.getDouble(10),
						rs.getDouble(9)));
				}
				rs.close();
				stat.close();
				break;
			default:
				new SQLWarning();
				break;
		}
		return contracts;
	}
	
	/**
	 * Chiude la connessione
	 * @throws SQLException
	 */
	public void closeConncetion() throws SQLException
	{con.close();}
}
