package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Agency;

public class TableAgency
{
	public static final String FIELD_NUMBER = "numero";
	public static final String FIELD_NAME = "nome";
	public static final String FIELD_ADDRESS = "indirizzo";
	
	DbAccess db;
	
	public TableAgency(DbAccess db)
	{this.db = db;}
	
	public void insertAgency (Agency agency)
	{
		try 
		{
			Statement st = db.getConnection().createStatement();
			String query = "INSERT INTO "+DbString.TBL_AGENCIES+" "
					+ "("+FIELD_NUMBER+","+FIELD_NAME+","+FIELD_ADDRESS+") values ('" +agency.getNumber()+"','"+ agency.getName() +
					"','" + agency.getAddress() +"');";
			st.executeUpdate(query);
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
			System.out.println("query non eseguita"); 
			e.printStackTrace();
		}
		return c;
	}
	
	public List<String> getAllAgency() throws SQLException
	{
			List<String> agencies = new ArrayList<String>();
			Statement st = db.getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM "+DbString.TBL_AGENCIES+";");
			
			
			while(rs.next())
			{
				agencies.add(rs.getString(1)+", "+rs.getString(2)+", "+rs.getString(3));
			}
			st.close();
			rs.close();
		return agencies;
	}

}
