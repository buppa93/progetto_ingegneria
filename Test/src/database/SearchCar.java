package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import view.SalesManView;
import entity.Agency;
import entity.Auto;
import entity.TypeSection;


public final class SearchCar 
{
	private static SearchCar instance =  new SearchCar();
	private String date_start;
	private String date_end;
	private String taking_agency;
	private String type_car;
	
	SearchCar() {}
	
	public static SearchCar getInstance() {return instance;};
	
	public void setDateStart(String date_start)
	{this.date_start = date_start;}
	
	public void setDateEnd(String date_end)
	{this.date_end = date_end;}
	
	public void setTakingAgency(String taking_agency)
	{this.taking_agency = taking_agency;}
	
	public void setTypeCar(String type_car)
	{this.type_car = type_car;}
	
	public String getDateStart()
	{return date_start;}
	
	public String getDateEnd()
	{return date_end;}
	
	public String getTakingAgency()
	{return taking_agency;}
	
	public String getTypeCar()
	{return type_car;}
	
	public List<Auto> search() throws DatabaseConnectionException, SQLException
	{
		String query = "SELECT * FROM auto WHERE (fascia='"+TypeSection.resolvType(this.type_car)+"' AND id_agenzia='"
					+Agency.getIdFromString(this.taking_agency)+"' AND disponibilita=1);";
		
		/*String query = "SELECT auto.targa, auto.modello, auto.marca, auto.km FROM auto, contratto WHERE "
				+ "(auto.fascia='"+TypeSection.resolvType(this.type_car)+"' AND auto.id_agenzia='"
				+Agency.getIdFromString(this.taking_agency)+"' AND auto.disponibilita=1 AND contratto.data_fine<'"+
				date_start+"' AND contratto.agenzia_rest='"+SalesManView.session.filiale.getNumber()+"');";*/
		System.out.println(query);
		
		List<Auto> cars = new ArrayList<Auto>();
		DbAccess db = new DbAccess();
		db.initConnection();
		Statement st = db.getConnection().createStatement();
		ResultSet rs = st.executeQuery(query);
		
		while(rs.next())
		{
			//String targa, String model, String brand, int km,  Availability disp
			cars.add(new Auto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),rs.getInt(5)));
		}

		st.close();
		rs.close();
		
		cars.addAll(searchByTargaInNol(date_start));
		return cars;
			
	}
	
	public List<Auto> searchByTargaInNol(String data_ref) throws DatabaseConnectionException, SQLException
	{
		String query = "SELECT targa FROM contratto WHERE (agenzia_rest='"+SalesManView.session.filiale.getNumber()+
				"' AND data_fine<'"+data_ref+"');";
		System.out.println(query);
		
		List<String> targhe = new ArrayList<String>();
		List<Auto> auto = new ArrayList<Auto>();
		
		DbAccess db = new DbAccess();
		db.initConnection();
		Statement st = db.getConnection().createStatement();
		ResultSet rs = st.executeQuery(query);
		
		while(rs.next())
		{
			targhe.add(rs.getString(1));
		}
		
		for(int i=0;i<targhe.size(); i++)
		{
			System.out.println("targa: "+targhe.get(i));
			query = "SELECT * FROM auto WHERE targa='"+targhe.get(i)+"';";
			System.out.println(query);
			rs = st.executeQuery(query);
			
			rs.next();
			auto.add(new Auto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),rs.getInt(5)));
			
		}
		rs.close();
		st.close();
		return auto;
	}
}
