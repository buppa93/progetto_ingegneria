package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utility.CarsAvailability;
import entity.Auto;

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
	
	public void deleteAutoByTarga(String targa)
	{
		try {
			Statement st= db.getConnection().createStatement();
			String query="DELETE* FROM"+ DbString.TBL_AUTO + "WHERE"+ FIELD_TARGA+ "="+ targa+";";
			st.execute(query);
			st.close();
		} catch (SQLException e) {
			System.out.println("eliminazione non eseguita"); 
			e.printStackTrace();
		}
		
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
