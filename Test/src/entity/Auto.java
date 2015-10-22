package entity;

import java.util.StringTokenizer;

import utility.CarsAvailability;

/**
 * Classe che astrae un'automobile secondo gli attributi di: targa, modello, marca,
 * chilometri, disponibilita', fascia.
 * @author Utente
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
	String id_cliente;
	String id_contratto;
	String id_agenzia;
	String fascia;
	String disp;
	
	/**
	 * Costruttore vuoto
	 */
	public Auto(){};
	
	/**
	 * Costruttore che inizialliza gli attributi della classe
	 * in accordo con i parametri passati.
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
	
	/**
	 * Costruttore che inizialliza gli attributi della classe
	 * in accordo con i parametri passati.
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
	public Auto(Nome section, int n_porte, int n_posti, String tipo_auto,String targa, String model, String brand, int km,  int disp)
	{
		super(section,n_porte, n_posti, tipo_auto);
		this.targa=targa;
		this.model=model;
		this.brand=brand;
		this.km=km;
		this.availability=new CarsAvailability(disp);
	}
	/**
	 * Costruttore che inizialliza gli attributi della classe
	 * in accordo con i parametri passati.
	 * @param targa
	 * @param model
	 * @param brand
	 * @param km
	 * @param disp
	 */
	public Auto(String targa, String model, String brand, int km, int disp)
	{
		this.targa = targa;
		this.model = model;
		this.brand = brand;
		this.km = km;
		this.availability = new CarsAvailability(disp);
	}
	
	/**
	 * Costruttore che inizialliza gli attributi della classe
	 * in accordo con i parametri passati.
	 * @param targa
	 * @param modello
	 * @param marca
	 * @param km
	 * @param id_cliente
	 * @param id_contratto
	 * @param id_agenzia
	 * @param fascia
	 * @param disp
	 */
	public Auto(String targa, String modello, String marca, int km, String id_cliente, String id_contratto, 
			String id_agenzia, char fascia, int disp)
	{
		this.targa = targa;
		this.model = modello;
		this.brand = marca;
		this.km = km;
		this.id_cliente = id_cliente;
		this.id_contratto = id_contratto;
		this.id_agenzia = id_agenzia;
		this.fascia = TypeSection.resolvName(fascia);
		this.disp = CarsAvailability.toString(disp);
		
	}
	
	/**
	 * Metodo che ritorna il numero dell'agenia in cui
	 * si trova l'auto
	 * @return number of agency 
	 */
	public String getNumberOfAgency()
	{
		return numero.getNumber();
	}
	
	/**
	 * Metodo che ritorna la targa dell'auto
	 * @return number plate
	 */
	public String getTarga()
	{return this.targa;}
	
	/**
	 * Metodo che ritorna il modello dell'auto
	 * @return model
	 */
	public String getModel()
	{return this.model;}
	
	/**
	 * Metodo che ritorna il numero dell'agenzia in cui
	 * si trova l'auto
	 * @return id_agenzia
	 */
	public String getIdAgenzia()
	{
		return this.id_agenzia;
	}
	
	/**
	 * Metodo che ritorna la marc dell'auto
	 * @return
	 */
	public String getBrand()
	{return this.brand;}
	
	/**
	 * Metodo che ritorna i chilometri dell'auto
	 * @return mileage
	 */
	public int getKm()
	{return this.km;}
	
	/**
	 * Metodo che ritorna lo stato dell'auto
	 * @return availability
	 */
	public String getAvailability()
	{return this.availability.getStatus();}
	
	/**
	 * Metodo che setta la targa dell'auto
	 * @param targa
	 */
	public void setTarga(String targa)
	{this.targa=targa;}
	
	/**
	 * Metodo che setta il numero dell'agenzia in cui
	 * si trova l'auto
	 * @param id
	 */
	public void setIdAgenzia(String id)
	{
		this.id_agenzia = id;
	}
	
	/**
	 * Metodo che setta il modello dell'auto
	 * @param model
	 */
	public void setModel(String model)
	{this.model=model;}
	
	/**
	 * Metodo che setta la marca dell'auto
	 * @param brand
	 */
	public void setBrand(String brand)
	{this.brand=brand;}
	
	/**
	 * Metodo che setta i chilometri dell'auto
	 * @param km
	 */
	public void setKm(int km)
	{this.km=km;}
	
	/**
	 * Metodo che setta la disponibilita' dell'auto
	 * (input numerico)
	 * @param disp
	 */
	public void setDisp (int disp)
	{this.availability.setStatus(disp);}
	
	/**
	 * Metodo che setta la disponibilita' dell'auto
	 * (input testuale)
	 * @param disp
	 */
	public void setDisp (String disp)
	{this.availability.setStatus(disp);}
	
	/**
	 * Metodo che stabilisce se la targa dell'auto e' valida
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
	 * Metodo che ritorna una descrizione dell'auto
	 * sottoforma di stringa
	 * @return string of attributes of auto
	 */
	public String toString()
	{
		return this.targa + "," + this.model + "," + this.brand + "," + this.km + "," + this.availability.getStatus();
	}
	
	/**
	 * Metodo che ritorna una descrizione dell'auto
	 * sottoforma di striga appositamente preparata
	 * per essere usata in un'etichetta
	 * @return string of attributes of auto
	 */
	public String toLabel()
	{
		return this.targa + ", " + this.model + ", " + this.brand + ", " + this.km;
	}
	
	public String toLabelAllAttribute()
	{
		return this.targa + ", " + this.model + ", " + this.brand + ", " + this.km + ", " + this.id_cliente + ", " + 
				this.id_contratto + ", " + this.id_agenzia + ", " + this.fascia + ", " + this.disp;
	}
	
	/**
	 * Metodo che ritorna la targa di un auto da una
	 * stringa formattata che ne rappresenta gli attributi
	 * @param auto
	 * @return targaOfAuto
	 */
	public static String getTargaFromString(String auto)
	{
		StringTokenizer st = new StringTokenizer(auto, "; ");
		return st.nextToken();
	}
	
}
