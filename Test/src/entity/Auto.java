package entity;
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
	//public enum Availability {ND,NOLEGGIO, MANUTENZIONE_STRAORDINARIA, MANUTENZIONE_ORDINARIA}
	/*Availability*/int disp;
	Agency numero;
	Client client;
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
public Auto(/*Nome section*//* int n_porte, int n_posti, String tipo_auto,*/String targa, String model, String brand, int km,  /*Availability*/int disp)
	{
		//super(section,n_porte, n_posti, tipo_auto);
		this.targa=targa;
		this.model=model;
		this.brand=brand;
		this.km=km;
		this.disp=disp;
	}
	/**
	 *  get the number of agency of auto
	 * @return number of agency 
	 */
	public String getNumberOfAgency()
	{
		return numero.getNumber();
	}
	/**
	 * get number plate of auto 
	 * @return number plate
	 */
	public String getTarga()
	{return this.targa;}
	/**
	 * get model of auto
	 * @return model
	 */
	public String getModel()
	{return this.model;}
	/**
	 * get brand of auto
	 * @return
	 */
	public String getBrand()
	{return this.brand;}
	/**
	 * get mileage of auto
	 * @return mileage
	 */
	public int getKm()
	{return this.km;}
	
	/**
	 * it establishes if auto is "not available", "available", "extraordinary maintenance" or "ordinary maintenance"
	 * @return availability
	 */
	public /*Availability*/int getAvailability()
	{return this.disp;}
	
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
	public void setDisp (/*Availability*/int disp)
	{this.disp=disp;}
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
		return this.targa + "," + this.model + "," + this.brand + "," + this.km + "," + this.disp;
	}
}

