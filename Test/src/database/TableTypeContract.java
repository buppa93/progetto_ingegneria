package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;

import entity.TypeContract;

public class TableTypeContract 
{
		public static final String FIELD_ID = "id_tipo";
		public static final String FIELD_TYPE_NOLEGGIO = "tipo_noleggio";
		public static final String FIELD_TYPE_KM = "tipo_chilometraggio";
		public static final String FIELD_FASCIA = "fascia";
		public static final String FIELD_KM = "km";
		public static final String FIELD_PRICE = "prezzo";
		public static final String FIELD_PRICE_KM = "prezzo_km";
		
		DbAccess db;
		
		public TableTypeContract(DbAccess db)
		{this.db = db;}
		
		public TypeContract getTypeContractById (String id)
		{
			TypeContract c = null;
			try 
			{
				Statement st = db.getConnection().createStatement();
				c = (TypeContract) st.executeQuery("SELECT * FROM "+DbString.TBL_TYPECONTRATC+" WHERE "+FIELD_ID+"='"+id+"';");
				st.close();
			} 
			catch (SQLException e) 
			{ 
				System.out.println("inserimento non eseguito"); 
				e.printStackTrace();
			}
			return c;
		}
		
		
		public String getTypeContractNameById(String id) throws SQLException
		{
			String query = "SELECT * FROM "+DbString.TBL_TYPECONTRATC+" WHERE "+FIELD_ID+"='"+id+"';";
			Statement st = db.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			
			String type = "";
			
			while(rs.next())
			{
				type = rs.getString(2);
			}
			
			return type;
		}
		
		public int getKmById(String id) throws SQLException
		{
			String query = "SELECT * FROM "+DbString.TBL_TYPECONTRATC+" WHERE "+FIELD_ID+"='"+id+"';";
			Statement st = db.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			
			int km = 0;
			
			while(rs.next())
			{
				km = rs.getInt(5);
			}
			
			return km;
		}
		
		public String getTypeKmById(String id) throws SQLException
		{
			String query = "SELECT * FROM "+DbString.TBL_TYPECONTRATC+" WHERE "+FIELD_ID+"='"+id+"';";
			Statement st = db.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			
			String type = "";
			
			while(rs.next())
			{
				type = rs.getString(3);
			}
			
			return type;
		}
		
		public ArrayList<String> getAll() throws SQLException
		{
			ArrayList<String> list = new ArrayList<String>();
			String query = "SELECT * FROM "+DbString.TBL_TYPECONTRATC+";";
			
			Statement st = db.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next())
			{
				list.add(rs.getString(1)+", "+rs.getString(2)+", "+rs.getString(3)+", "+rs.getString(4));
			}
			
			rs.close();
			st.close();
			return list;
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
		
		public double getPricePerKmById(String id) throws SQLException 
		{
			String query = "SELECT * FROM "+DbString.TBL_TYPECONTRATC+" WHERE "+FIELD_ID+"='"+id+"';";
			Statement st = db.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			
			double result = 0.0;
			while(rs.next())
			{
				result = rs.getDouble(7);
			}
			rs.close();
			st.close();
			
			return result;
		}
		
		public String getId(String tContract) 
		{
			StringTokenizer st = new StringTokenizer(tContract, ", ");
			return st.nextToken();
		}

	}
