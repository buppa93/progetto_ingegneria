package database;

public class SearchCar 
{
	private static SearchCar instance =  new SearchCar();
	private String date_start;
	private String date_end;
	private String taking_agency;
	private String return_agency;
	private String type_km;
	private int km;
	private String type_car;
	
	SearchCar() {}
	//TODO da fare con steXXX()
	public SearchCar(String date_start, String date_end, String taking_agency, String return_agency, String type_km, int  km, String type_car)
	{
		this.date_start = date_start;
		this.date_end = date_end;
		this.taking_agency = taking_agency;
		this.return_agency = return_agency;
		this.type_km = type_km;
		this.km = km;
		this.type_car = type_car;
	}

}
