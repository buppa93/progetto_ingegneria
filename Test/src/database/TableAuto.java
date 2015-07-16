package database;

import java.sql.SQLException;
import java.sql.Statement;

import entity.Auto;

public class TableAuto
{
	public static final String FIELD_TARGA = "targa";
	public static final String FIELD_MODEL = "modello";
	public static final String FIELD_BRAND = "marca";
	public static final String FIELD_KM = "km";
	public static final String FIELD_DISP = "disponibilita";
	public static final String FIELD_AGENCY = "agenzia";
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

}
