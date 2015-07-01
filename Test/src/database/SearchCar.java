package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class SearchCar 
{
	private static SearchCar instance =  new SearchCar();
	private String date_start;
	private String taking_agency;
	private String type_car;
	
	SearchCar() {}
	
	public static SearchCar getInstance() {return instance;};
	
	public void setDateStart(String date_start)
	{this.date_start = date_start;}
	
	public void setTakingAgency(String taking_agency)
	{this.taking_agency = taking_agency;}
	
	public void setTypeCar(String type_car)
	{this.type_car = type_car;}
	
	public String getDateStart()
	{return date_start;}
	
	public String getTakingAgency()
	{return taking_agency;}
	
	public String getTypeCar()
	{return type_car;}
	
	public ResultSet search() throws DatabaseConnectionException, SQLException
	{
		String query = "SELECT * FROM auto WHERE (fascia='"+this.type_car+"' AND id_agenzia='"
					+this.taking_agency+"' AND disponibilità=1);";
		
		DbAccess db = new DbAccess();
		db.initConnection();
		Statement st = db.getConnection().createStatement();
		ResultSet rs = st.executeQuery(query);
		st.close();
		return rs;
			
	}
}
