package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utility.CarsAvailability;
import entity.Auto;
import entity.Contract;
import utility.KeyValuePair;

public class TableAuto
{
	public static final String FIELD_TARGA = "targa";
	public static final String FIELD_MODEL = "modello";
	public static final String FIELD_BRAND = "marca";
	public static final String FIELD_KM = "km";
	public static final String FIELD_DISP = "disponibilita";
	public static final String FIELD_AGENCY = "id_agenzia";
	public static final String FIELD_CLIENT = "contratto";	
	DbAccess db;
	
	public TableAuto(DbAccess db)
	{this.db = db;}
	
	public void insertAuto (Auto auto)
	{
		try 
		{
			Statement st = db.getConnection().createStatement();
			String query = "INSERT INTO "+DbString.TBL_AGENCIES+" "
					+ "("+FIELD_TARGA+","+FIELD_MODEL+","+FIELD_BRAND+","+FIELD_KM+","+FIELD_DISP+","+FIELD_AGENCY+") values ('" +auto.getTarga()+"','"+ auto.getModel() +
					"','" + auto.getBrand()+","+auto.getKm()+","+auto.getAvailability()+","+auto.getNumberOfAgency()+"');";
		
			st.executeUpdate(query);
			st.close();
		} 
		catch (SQLException e) 
		{ 
			System.out.println("inserimento non eseguito"); 
			e.printStackTrace();
		}
	}
	
	public int genericInsertAuto(ArrayList<KeyValuePair<String,?>> values) throws SQLException
	{
		Statement st = db.getConnection().createStatement();
		StringBuffer query = new StringBuffer("INSERT INTO "+DbString.TBL_AUTO+"(");
		for(int i=0;i<values.size()-1;i++)
		{
			query.append(values.get(i).getKey());
			query.append(",");
		}
		query.append(values.get(values.size()-1).getKey());
		query.append(") VALUES (");
		for(int i=0;i<values.size()-1;i++)
		{
			if((values.get(i).getKey().equals("km"))||(values.get(i).getKey().equals("disponibilita")))
			{
				query.append(values.get(i).getValue());
				query.append(",");
			}
			else
			{
				query.append("'"+values.get(i).getValue()+"'");
				query.append(",");
			}
		}
		query.append("'"+values.get(values.size()-1).getValue()+"'");
		query.append(");");
		System.out.println(query);
		System.out.println("In stringa: "+query);
		
		
		int result = st.executeUpdate(query.toString());
		st.close();
		return result;
	}
	
	public boolean deleteAutoByTarga(String targa) throws SQLException
	{
			boolean result = false;
			
			Statement st= db.getConnection().createStatement();
			String query="DELETE * FROM"+ DbString.TBL_AUTO + "WHERE"+ FIELD_TARGA+ "="+ targa+";";
			result = st.execute(query);
			st.close();
			return result;
	}
	
	public int deleteAutoByTargaAndAgency(String targa, String agency) throws SQLException
	{
			int result = 0;
			
			Statement st= db.getConnection().createStatement();
			String query="DELETE FROM "+ DbString.TBL_AUTO + " WHERE ("+FIELD_TARGA+"='"+targa+"' AND "+FIELD_AGENCY+
					" = '"+agency+"');";
			result = st.executeUpdate(query);
			st.close();
			return result;
	}
	
	
	/*public Agency getAgencyByNumber (String number)
	{
		Agency c = null;
		try 
		{
			Statement st = db.getConnection().createStatement();
			c = (Agency) st.executeQuery("SELECT * FROM "+DbString.TBL_AGENCIES+" WHERE "+FIELD_NUMBER+"="+number+";");
			st.close();
		} 
		catch (SQLException e) 
		{ 
			System.out.println("inserimento non eseguito"); 
			e.printStackTrace();
		}
		return c;
	}*/
	
	public int getKm(String targa) throws SQLException
	{
		int km = 0;
		String query = "SELECT * FROM "+DbString.TBL_AUTO+" WHERE "+FIELD_TARGA+"='"+targa+"';";
		Statement st = db.getConnection().createStatement();
		ResultSet rs = st.executeQuery(query);
		
		while(rs.next())
		{
			km = rs.getInt(4);
		}
		rs.close();
		st.close();
		return km;
	}
	
	public void setInNoleggio(String targa) throws SQLException
	{
		CarsAvailability status = new CarsAvailability("noleggio");
		String query = "UPDATE auto SET disponibilita="+ CarsAvailability.toInt("noleggio") +" WHERE targa='"
				+targa+"';";
		System.out.println(query);
		Statement st = db.getConnection().createStatement();
		st.executeUpdate(query);
		st.close();
	}
	
	public void setDisponibile(String targa) throws SQLException
	{
		CarsAvailability status = new CarsAvailability("noleggio");
		String query = "UPDATE auto SET disponibilita="+ CarsAvailability.toInt("disponibile") +" WHERE targa='"
				+targa+"';";
		System.out.println(query);
		Statement st = db.getConnection().createStatement();
		st.executeUpdate(query);
		st.close();
	}
	
	public void setKm(String targa, int km) throws SQLException
	{
		String query = "UPDATE "+DbString.TBL_AUTO+" SET "+FIELD_KM+"="+km+" WHERE "+FIELD_TARGA+"='"+targa+"';";
		Statement st = db.getConnection().createStatement();
		
		st.executeUpdate(query);
		st.close();
		
	}
	
	public void setAgencyReturn(String targa, String idAgency) throws SQLException
	{
		String query = "UPDATE "+DbString.TBL_AUTO+" SET "+FIELD_AGENCY+"='"+idAgency+"' WHERE "+FIELD_TARGA+"='"+targa+"';";
		Statement st = db.getConnection().createStatement();
		
		st.executeUpdate(query);
		st.close();
		
	}

	public ArrayList<Auto> dynamicSearch(ArrayList<KeyValuePair<String,?>> searchParameters) throws SQLException
	{
		ArrayList<Auto> cars = new ArrayList<Auto>();
		Statement st = db.getConnection().createStatement();
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM "+DbString.TBL_AUTO+" WHERE (");
		
		for(int i=0; i<searchParameters.size()-1;i++)
		{
			if(searchParameters.get(i).getKey().equals("km"))
			{
				query.append(searchParameters.get(i).getKey());
				query.append(searchParameters.get(i).getValue()+" AND ");
			}
			else
			{
				query.append(searchParameters.get(i).getKey());
				query.append("='");
				query.append(searchParameters.get(i).getValue()+"' AND ");	
			}
		}
		
		query.append(searchParameters.get(searchParameters.size()-1).getKey());
		query.append("='");
		query.append(searchParameters.get(searchParameters.size()-1).getValue());
		query.append("');");
		System.out.println("Query: "+query);
		
		ResultSet rs = st.executeQuery(query.toString());
		
		while(rs.next())
		{
			cars.add(new Auto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getString(8).charAt(0), rs.getInt(9)));
		}
		
		return cars;
	}
	public Auto searchAutoByTarga(String targa) throws SQLException
	{
		String query = "SELECT * FROM "+DbString.TBL_AUTO+" WHERE "+FIELD_TARGA+"='"+targa+"';";
		Statement st = db.getConnection().createStatement();
		ResultSet rs = st.executeQuery(query);
		
		Auto auto = null;
		while(rs.next())
		{
			auto = new Auto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getString(8).charAt(0), rs.getInt(9));
		}
		rs.close();
		st.close();
		return auto;
	}

}
