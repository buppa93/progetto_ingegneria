package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.Price;

public class TablePrices 
{
	public static final String FIELD_FASCIA = "fascia";
	public static final String FIELD_TYPEKM = "tipo_km";
	public static final String TBL_COSTOKM = "costo_per_km";
	public static final String TBL_COSTOGG = "costo_giornaliero";
	DbAccess db;
	
	public TablePrices(DbAccess db)
	{
		this.db = db;
	}
	
	public Price getPrice(char fascia, String type_km) throws SQLException
	{
		String query = "SELECT * FROM "+DbString.TBL_PRICES+" WHERE ("+FIELD_FASCIA+"='"+fascia+"' AND "+FIELD_TYPEKM+"='"+type_km.toLowerCase()+"');";
		Statement st = db.getConnection().createStatement();
		ResultSet rs = st.executeQuery(query);
		
		Price p = null;
		
		while(rs.next())
		{
			p = new Price(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4));
		}
		return p;
	}
	

}
