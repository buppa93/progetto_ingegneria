package database;

import java.sql.SQLException;
import java.sql.Statement;

import entity.Contract;


public class TableContract 
{
			public static final String FIELD_NUMBER_ORDINE = "numero ordine";
			public static final String FIELD_NUM_AGENCY = "numero agenzia";
			public static final String FIELD_CLIENTE = "cliente";
			public static final String FIELD_INIZIO_NOLEGGIO = "inizio noleggio";
			public static final String FIELD__FINE_NOLEGGIO = "fine noleggio";
			public static final String FIELD_AGENZIA_RESTIT = "agenzia restituzione";
			public static final String FIELD_MAX_KM = "massimo chilometraggio";
			
			DbAccess db;
			
			public TableContract(DbAccess db)
			{this.db = db;}
			
			public void insertContract (Contract contract)
			{
				try 
				{
					Statement st = db.getConnection().createStatement();
					String query = "INSERT INTO "+DbString.TBL_CONTRACTS+" "
							+ "("+FIELD_NUMBER_ORDINE+","+FIELD_NUM_AGENCY+","+FIELD_CLIENTE+","+FIELD_INIZIO_NOLEGGIO+","+FIELD__FINE_NOLEGGIO+","+FIELD_AGENZIA_RESTIT+","+FIELD_MAX_KM +") values ('" +contract.getNumero_ordine()+"','"+ contract.getNumberOfAgency() +
							"','" + contract.getNumberCliente()+","+contract.getData_inizio()+","+contract.getData_fine()+","+ contract.getNumberOfAgency()+","+contract.getKmmax()+"');";
					st.executeUpdate(query);
					st.close();
				} 
				catch (SQLException e) 
				{ 
					System.out.println("inserimento non eseguito"); 
					e.printStackTrace();
				}
			}
			
			public Contract getContractByNumeroOrdine (int numOrdine)
			{
				Contract c = null;
				try 
				{
					Statement st = db.getConnection().createStatement();
					c = (Contract) st.executeQuery("SELECT * FROM "+DbString.TBL_CONTRACTS+" WHERE "+FIELD_NUMBER_ORDINE+"="+numOrdine+";");
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
