package entity;

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
	
	public Auto(){};
	
	public Auto(/*Nome section*//* int n_porte, int n_posti, String tipo_auto,*/String targa, String model, String brand, int km,  /*Availability*/int disp)
	{
		//super(section,n_porte, n_posti, tipo_auto);
		this.targa=targa;
		this.model=model;
		this.brand=brand;
		this.km=km;
		this.disp=disp;
	}
	
	public String getNumberOfAgency()
	{
		return numero.getNumber();
	}
	
	public String getTarga()
	{return this.targa;}
	
	public String getModel()
	{return this.model;}
	
	public String getBrand()
	{return this.brand;}
	
	public int getKm()
	{return this.km;}
	
	public /*Availability*/int getAvailability()
	{return this.disp;}
	
	public void setTarga(String targa)
	{this.targa=targa;}
	
	public void setModel(String model)
	{this.model=model;}
	
	public void setBrand(String brand)
	{this.brand=brand;}
	
	public void setKm(int km)
	{this.km=km;}
	
	public void setDisp (/*Availability*/int disp)
	{this.disp=disp;}
	
	public boolean isTargaValid()
	{
		boolean IsValid=false;
		if(targa.length()==7)
			IsValid=true;
		return IsValid;	
	}
	
	public String toString()
	{
		return this.targa + "," + this.model + "," + this.brand + "," + this.km + "," + this.disp;
	}
}

