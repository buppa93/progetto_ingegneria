package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

import entity.TypeContract;

public class TableTypeContract 
{
		public static final String FIELD_ID = "num_contratto";
		public static final String FIELD_TYPE_NOLEGGIO = "tipo_noleggio";
		public static final String FIELD_TYPE_KM = "tipo_chilometraggio";
		public static final String FIELD_FASCIA = "fascia";
		public static final String FIELD_KM = "km";
		public static final String FIELD_PRICE = "prezzo";
		public static final String FIELD_PRICE_KM = "prezzo_km";
		
		DbAccess db;
		
		public TableTypeContract(DbAccess db)
		{this.db = db;}
		
		/*public void insertTypeContract (TypeContract typecontract)
		{
			try 
			{
				Statement st = db.getConnection().createStatement();
				String query = "INSERT INTO "+DbString.TBL_TYPECONTRATC+" "
						+ "("+FIELD_ID+","+FIELD_TYPE_NOLEGGIO+","+FIELD_TYPE_KM+") values ('" +typecontract.getTypeContract()+"','"+ typecontract.getTypeContract() +
						"','" + typecontract.getChilometraggio()+","+"');";
				st.executeUpdate(query);
				st.close();
			} 
			catch (SQLException e) 
			{ 
				System.out.println("inserimento non eseguito"); 
				e.printStackTrace();
			}
		}*/
		
		public TypeContract getTypeContractById (int id)
		{
			TypeContract c = null;
			try 
			{
				Statement st = db.getConnection().createStatement();
				c = (TypeContract) st.executeQuery("SELECT * FROM "+DbString.TBL_TYPECONTRATC+" WHERE "+FIELD_ID+"="+id+";");
				st.close();
			} 
			catch (SQLException e) 
			{ 
				System.out.println("inserimento non eseguito"); 
				e.printStackTrace();
			}
			return c;
		}
		
		public String getTypeContract (String tipoNoleggio, String tipoChilometraggio, char fascia, String km) throws SQLException
		{
			String query = "";
			if(tipoChilometraggio.equals("illimitato"))
			{
				query = "SELECT * FROM "+DbString.TBL_TYPECONTRATC+" WHERE ("+FIELD_TYPE_NOLEGGIO+"='"+tipoNoleggio+
						"' AND "+FIELD_TYPE_KM+"='"+tipoChilometraggio+"' AND "+FIELD_FASCIA+"='"+fascia+"' AND "+FIELD_KM+
						"="+0+");";
			}
			else
			{
				query = "SELECT * FROM "+DbString.TBL_TYPECONTRATC+" WHERE ("+FIELD_TYPE_NOLEGGIO+"='"+tipoNoleggio+
						"' AND "+FIELD_TYPE_KM+"='"+tipoChilometraggio+"' AND "+FIELD_FASCIA+"='"+fascia+"' AND "+FIELD_KM+
						"="+Integer.parseInt(km)+");";
			}
			
			System.out.println(query);
			String result = "";
			
			Statement st = db.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next())
			{
				result = rs.getString(1)+", "+rs.getString(2)+", "+rs.getString(3)+", "+rs.getString(4)+", "+rs.getInt(5)+
						", "+rs.getDouble(6)+", "+rs.getDouble(7);
				System.out.println(result);
			}
			
			rs.close();
			st.close();
			return result;
		}

		public double getPrice(String tContract) 
		{
			StringTokenizer st = new StringTokenizer(tContract, ", ");
			String result = "";
			for(int i=0;i<6;i++)
			{
				result = st.nextToken();
			}
			return Double.parseDouble(result);
		}
		
		public String getId(String tContract) 
		{
			StringTokenizer st = new StringTokenizer(tContract, ", ");
			return st.nextToken();
		}

	}
