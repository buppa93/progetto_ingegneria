package database;

import java.sql.SQLException;
import java.sql.Statement;

import entity.Contract;


public class TableContract 
{
			public static final String FIELD_NUMBERORDINE = "numero_ordine";
			public static final String FIELD_NUMAGENCY = "id_agenzia";
			public static final String FIELD_CLIENTE = "id_cliente";
			public static final String FIELD_INIZIONOLEGGIO = "data_inizio";
			public static final String FIELD__DURATION = "duration";
			public static final String FIELD_AGENZIARESTITUZIONE = "agenzia_rest";
			public static final String FIELD_TYPE = "tipo";
			public static final String FIELD_PRICE = "prezzo";
			public static final String FIELD_DEPOSIT = "acconto";
			
			DbAccess db;
			
			public TableContract(DbAccess db)
			{this.db = db;}
			
			/*public void insertContract (Contract contract)
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
			}*/
			
			/*public Contract getContractByNumeroOrdine (int numOrdine)
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
			}*/
			
			public void insert(String id, String agencyTakeId, String phoneClient, String dateStart, String duration,
									String agencyReturnId, String typeId, String price, String deposit) throws SQLException
			{
				String query = "INSERT INTO contratto (numero_ordine, id_agenzia, id_cliente, data_inizio, durata, agenzia_rest,"
						+ " tipo, prezzo, acconto) VALUES ('"+id+"', '"+agencyTakeId+"', '"+phoneClient+"', '"+dateStart+"', '"
						+duration+"', '"+agencyReturnId+"', '"+typeId+"', '"+price+"', '"+deposit+"');";
				System.out.println(query);
				
				Statement st = db.getConnection().createStatement();
				st.executeUpdate(query);
				st.close();
			}

		}
