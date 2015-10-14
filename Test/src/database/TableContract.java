package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utility.KeyValuePair;
import entity.Contract;


public class TableContract 
{
			public static final String FIELD_NUMBERORDINE = "numero_ordine";
			public static final String FIELD_NUMAGENCY = "id_agenzia";
			public static final String FIELD_CLIENTE = "id_cliente";
			public static final String FIELD_INIZIONOLEGGIO = "data_inizio";
			public static final String FIELD__DURATION = "duration";
			public static final String FIELD__FINENOLEGGIO = "data_fine";
			public static final String FIELD_AGENZIARESTITUZIONE = "agenzia_rest";
			public static final String FIELD_TYPE = "tipo";
			public static final String FIELD_PRICE = "prezzo";
			public static final String FIELD_DEPOSIT = "acconto";
			public static final String FIELD_TARGA = "targa";
			
			DbAccess db;
			
			public TableContract(DbAccess db)
			{this.db = db;}
			
			public void insert(String id, String agencyTakeId, String phoneClient, String dateStart, String duration, String dateEnd,
									String agencyReturnId, String typeId, String price, String deposit, String targa) throws SQLException
			{
				String query = "INSERT INTO contratto (numero_ordine, id_agenzia, id_cliente, data_inizio, durata, data_fine, agenzia_rest,"
						+ " tipo, prezzo, acconto, targa) VALUES ('"+id+"', '"+agencyTakeId+"', '"+phoneClient+"', '"+dateStart+"', '"
						+duration+"', '"+dateEnd+"', '"+agencyReturnId+"', '"+typeId+"', '"+price+"', '"+deposit+"', '"+targa+"');";
				System.out.println(query);
				
				Statement st = db.getConnection().createStatement();
				st.executeUpdate(query);
				st.close();
			}
			
			public Contract searchContract (String phoneCli, String targa) throws SQLException
			{
				String query = "SELECT * FROM contratto WHERE ("+FIELD_CLIENTE+"='"+phoneCli+"' AND "+FIELD_TARGA+"='"+
							targa+"');";
				Contract con = null;
				System.out.println(query);
				Statement st = db.getConnection().createStatement();
				ResultSet rs = st.executeQuery(query);
				
				while(rs.next())
				{
					con = new Contract(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), 
							rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9), rs.getString(10));
				}
				
				rs.close();
				st.close();
				return con;
			}
			
			
			public void remove(String id) throws SQLException
			{
				String query = "DELETE FROM "+DbString.TBL_CONTRACTS+" WHERE "+FIELD_NUMBERORDINE+"='"+id+"';";
				System.out.println(query);
				Statement st = db.getConnection().createStatement();
				st.executeUpdate(query);
				st.close();
			}
			
			public ArrayList<Contract> dynamicSearch(ArrayList<KeyValuePair<String, String>> parameters) throws SQLException
			{
				ArrayList<Contract> result = new ArrayList<Contract>();
				Contract con = null;
				
				/***** Costruzione delle query ******/
				StringBuffer query = new StringBuffer();
				query.append("SELECT * FROM "+ DbString.TBL_CONTRACTS+" WHERE (");
				for(int i=0; i<parameters.size()-1; i++)
				{
					query.append(parameters.get(i).getKey());
					query.append("='");
					query.append(parameters.get(i).getValue());
					query.append("' AND ");
				}
				
				query.append(parameters.get(parameters.size()-1).getKey());
				query.append("='");
				query.append(parameters.get(parameters.size()-1).getValue());
				query.append("');");
				
				System.out.println(query);
				
				/******** esecuzione della query *****************/
				Statement st = db.getConnection().createStatement();
				ResultSet rs = st.executeQuery(query.toString());
				
				
				/*************** elaborazione dei risultati ***************/
				while(rs.next())
				{
					con = new Contract(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), 
							rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9), rs.getString(10));
					result.add(con);
				}
				
				st.close();
				rs.close();
				return result;
			}

		}
