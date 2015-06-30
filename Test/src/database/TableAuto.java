package database;

import java.sql.SQLException;
import java.sql.Statement;

import entity.Agency;
import entity.Auto;
import entity.Client;

public class TableAuto
{
	public static final String FIELD_TARGA = "targa";
	public static final String FIELD_MODEL = "modello";
	public static final String FIELD_BRAND = "marca";
	public static final String FIELD_KM = "km";
	public static final String FIELD_DISP = "disponibilitÓ";
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
			System.out.println(query);
			st.executeUpdate(query);
			System.out.println(st.toString());
			st.close();
		} 
		catch (SQLException e) 
		{ 
			System.out.println("inserimento non eseguito"); 
			e.printStackTrace();
		}
	}
	
	public Agency getAgencyByNumber (String number)
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
	}

}
