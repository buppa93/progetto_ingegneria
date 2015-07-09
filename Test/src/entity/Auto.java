package entity;

import java.util.StringTokenizer;

import utility.CarsAvailability;

/**
 * is the car of agency characterized by number plate, model, brand, mileage. 
 * About the car says how is available, previous contracts, previous customers and where it is now
 * @author Utente
 *
 */
public class Auto extends TypeSection
{
	String targa;
	String model;
	String brand;
	int km; // km traveled
	CarsAvailability availability;
	Agency numero;
	User client;
	Contract contract;
	
	/**
	 * it initializes the class Auto to null
	 */
	public Auto(){};
	
	/**
	 * it initializes the class auto through input parameters
	 * @param section
	 * @param n_porte
	 * @param n_posti
	 * @param tipo_auto
	 * @param targa
	 * @param model
	 * @param brand
	 * @param km
	 * @param disp
	 */
	public Auto(Nome section, int n_porte, int n_posti, String tipo_auto,String targa, String model, String brand, int km,  String disp)
	{
		super(section,n_porte, n_posti, tipo_auto);
		this.targa=targa;
		this.model=model;
		this.brand=brand;
		this.km=km;
		this.availability=new CarsAvailability(disp);
	}
	
	public Auto(Nome section, int n_porte, int n_posti, String tipo_auto,String targa, String model, String brand, int km,  int disp)
	{
		super(section,n_porte, n_posti, tipo_auto);
		this.targa=targa;
		this.model=model;
		this.brand=brand;
		this.km=km;
		this.availability=new CarsAvailability(disp);
	}
	
	public Auto(String targa, String model, String brand, int km, int disp)
	{
		this.targa = targa;
		this.model = model;
		this.brand = brand;
		this.km = km;
		this.availability = new CarsAvailability(disp);
	}
	
	/**
	 *  it gets the number of agency of auto
	 * @return number of agency 
	 */
	public String getNumberOfAgency()
	{
		return numero.getNumber();
	}
	
	/**
	 * it gets number plate of auto 
	 * @return number plate
	 */
	public String getTarga()
	{return this.targa;}
	
	/**
	 * it gets model of auto
	 * @return model
	 */
	public String getModel()
	{return this.model;}
	
	/**
	 * it gets brand of auto
	 * @return
	 */
	public String getBrand()
	{return this.brand;}
	
	/**
	 * it gets mileage of auto
	 * @return mileage
	 */
	public int getKm()
	{return this.km;}
	
	/**
	 * it establishes if auto is "not available", "available", "extraordinary maintenance" or "ordinary maintenance"
	 * @return availability
	 */
	public String getAvailability()
	{return this.availability.getStatus();}
	
	/**
	 * sets number plate of auto through input parameter
	 * @param targa
	 */
	public void setTarga(String targa)
	{this.targa=targa;}
	
	/**
	 * sets model of auto through input parameter
	 * @param model
	 */
	public void setModel(String model)
	{this.model=model;}
	
	/**
	 * sets brand of auto through input parameter
	 * @param brand
	 */
	public void setBrand(String brand)
	{this.brand=brand;}
	
	/**
	 * sets mileage through input parameter
	 * @param km
	 */
	public void setKm(int km)
	{this.km=km;}
	
	/**
	 * sets availability of auto through input parameter
	 * @param disp
	 */
	public void setDisp (int disp)
	{this.availability.setStatus(disp);}
	
	/**
	 * sets availability of auto through input parameter
	 * @param disp
	 */
	public void setDisp (String disp)
	{this.availability.setStatus(disp);}
	
	/**
	 * it establishes if number plate is valid. If his length is 7 it is valid
	 * @return boolean
	 */
	public boolean isTargaValid()
	{
		boolean IsValid=false;
		if(targa.length()==7)
			IsValid=true;
		return IsValid;	
	}
	
	/**
	 * it returns tuple of auto
	 * @return string of attributes of auto
	 */
	public String toString()
	{
		return this.targa + "," + this.model + "," + this.brand + "," + this.km + "," + this.availability.getStatus();
	}

	public String toLabel()
	{
		return this.targa + ", " + this.model + ", " + this.brand + ", " + this.km;
	}
	
	public static String getTargaFromString(String auto)
	{
		StringTokenizer st = new StringTokenizer(auto, "; ");
		return st.nextToken();
	}
	
}
