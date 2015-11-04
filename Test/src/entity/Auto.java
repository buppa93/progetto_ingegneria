package entity;

import java.util.StringTokenizer;

import utility.CarsAvailability;


//TODO Sistemare i javadoc
/**
 * Classe che astrae un'automobile secondo gli attributi di: targa, modello, marca,
 * chilometri, disponibilita', numero cliente, numero agenzia, fascia.
 * @author Utente
 */
public class Auto
{
	String targa;
	String model;
	String brand;
	int km; // km traveled
	CarsAvailability availability;
	String id_cliente;
	String id_contratto;
	String id_agenzia;
	TypeSection fascia;
	String disp;
	
	/**
	 * Costruttore vuoto
	 */
	public Auto(){};
	
	/**
	 * Costruttore che inizialliza gli attributi della classe
	 * in accordo con i parametri passati.
	 * @param targa Stringa contenente la targa dell'auto
	 * @param model Stringa contenente il modello dell'auto
	 * @param brand Stringa contenente la marca dell'auto
	 * @param km Intero rappresentante i km dell'auto
	 * @param disp Intero rappresentante la disponibilita' dell'auto
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
	 * @param targa Stringa contenente la targa dell'auto
	 * @param modello Stringa contenente il modello dell'auto
	 * @param marca Stringa contenente la marca dell'auto
	 * @param km Intero rappresentante i km dell'auto
	 * @param id_cliente Stringa contenente il numero di telefono del cliente
	 * @param id_contratto Stringa contenente il numero di contratto
	 * @param id_agenzia Stringa contenente il numero dell'agenzia che ha stipulato il contratto
	 * @param fascia TypeSection contenente il tipo dell'auto.
	 * @param disp Intero rappresentante la disponibilita' dell'auto
	 */
	public Auto(String targa, String modello, String marca, int km, String id_cliente, String id_contratto, 
			String id_agenzia, TypeSection fascia, int disp)
	{
		this.targa = targa;
		this.model = modello;
		this.brand = marca;
		this.km = km;
		this.id_cliente = id_cliente;
		this.id_contratto = id_contratto;
		this.id_agenzia = id_agenzia;
		this.fascia = fascia;
		this.disp = CarsAvailability.toString(disp);
		availability = new CarsAvailability(disp);
	}
	
	/**
	 * Costruttore che inizialliza gli attributi della classe
	 * in accordo con i parametri passati.
	 * @param targa Stringa contenente la targa dell'auto
	 * @param modello Stringa contenente il modello dell'auto
	 * @param marca Stringa contenente la marca dell'auto
	 * @param km Intero rappresentante i km dell'auto
	 * @param id_agenzia Stringa contenente il numero dell'agenzia che ha stipulato il contratto
	 * @param fascia TypeSection contenente il tipo dell'auto.
	 * @param disp Intero rappresentante la disponibilita' dell'auto
	 */
	public Auto(String targa, String modello, String marca, int km, String id_agenzia, TypeSection fascia, int disp)
	{
		this.targa = targa;
		this.model = modello;
		this.brand = marca;
		this.km = km;
		this.id_cliente = null;
		this.id_contratto = null;
		this.id_agenzia = id_agenzia;
		this.fascia = fascia;
		this.disp = CarsAvailability.toString(disp);

		availability = new CarsAvailability(disp);
	}
	
	
	/**
	 * Metodo che ritorna la targa dell'auto
	 * @return Stringa contente il numero di targa
	 */
	public String getTarga()
	{return this.targa;}
	
	/**
	 * Metodo che ritorna il modello dell'auto
	 * @return il modello di auto
	 */
	public String getModel()
	{return this.model;}
	
	/**
	 * Metodo che ritorna il numero dell'agenzia in cui
	 * si trova l'auto
	 * @return l'agenzia proprietaria
	 */
	public String getIdAgenzia()
	{
		return this.id_agenzia;
	}
	
	/**
	 * Metodo che ritorna la marc dell'auto
	 * @return la marca dell'auto
	 */
	public String getBrand()
	{return this.brand;}
	
	/**
	 * Metodo che ritorna i chilometri dell'auto
	 * @return i km percorsi
	 */
	public int getKm()
	{return this.km;}
	
	/**
	 * Metodo che ritorna lo stato dell'auto
	 * @return lo stato
	 */
	public String getAvailability()
	{return this.availability.getStatus();}
	
	/**
	 * Metodo che ritorna lo stato dell'auto
	 * sotto forma di numero
	 * @return lo stato sotto forma di numero
	 */
	public int getAvailabilityNumber()
	{
		return this.availability.toInt();
	}
	
	/**
	 * Metodo che ritorna la fascia di
	 * automobile
	 * @return il tipo di auto
	 */
	public TypeSection getFascia()
	{return this.fascia;}
	
	/**
	 * Metodo che setta la targa dell'auto
	 * @param la targa
	 */
	public void setTarga(String targa)
	{this.targa=targa;}
	
	/**
	 * Metodo che setta il numero dell'agenzia in cui
	 * si trova l'auto
	 * @param id numero dell'agenzia proprietaria
	 */
	public void setIdAgenzia(String id)
	{
		this.id_agenzia = id;
	}
	
	/**
	 * Metodo che setta il modello dell'auto
	 * @param model modello dell'auto
	 */
	public void setModel(String model)
	{this.model=model;}
	
	/**
	 * Metodo che setta la marca dell'auto
	 * @param brand marca dell'auto
	 */
	public void setBrand(String brand)
	{this.brand=brand;}
	
	/**
	 * Metodo che setta i chilometri dell'auto
	 * @param km i km percorsi
	 */
	public void setKm(int km)
	{this.km=km;}
	
	/**
	 * Metodo che setta la disponibilita' dell'auto
	 * (input numerico)
	 * @param disp lo stato
	 */
	public void setDisp (int disp)
	{this.availability.setStatus(disp);}
	
	/**
	 * Metodo che setta la disponibilita' dell'auto
	 * (input testuale)
	 * @param disp lo stato
	 */
	public void setDisp (String disp)
	{this.availability.setStatus(disp);}
	
	/**
	 * Metodo che setta il tipo id auto
	 * @param fascia il tipo
	 */
	public void setFascia(TypeSection fascia)
	{this.fascia = fascia;}
	
	/**
	 * Metodo che stabilisce se la targa dell'auto e' valida
	 * @return boolean vero se la targa e' valida, falso altrimenti
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
	 * @return Stringa: targa,modello,marca,km,disponibilita'
	 */
	public String toString()
	{
		return this.targa + "," + this.model + "," + this.brand + "," + this.km + "," + this.availability.getStatus();
	}
	
	/**
	 * Metodo che ritorna una descrizione dell'auto
	 * sottoforma di striga appositamente preparata
	 * per essere usata in un'etichetta
	 * @return Stringa: targa, modello, marca, km
	 */
	public String toLabel()
	{
		return this.targa + ", " + this.model + ", " + this.brand + ", " + this.km;
	}
	
	/**
	 * Metodo che ritorna una descrizione dell'auto
	 * con tutti gli attributi
	 * @return Stringa: targa, modello, marca, km, numeroCliente, numeroContratto, numeroAgenzia, fascia, disponibilita'
	 */
	public String toLabelAllAttribute()
	{
		return this.targa + ", " + this.model + ", " + this.brand + ", " + this.km + ", " + this.id_cliente + ", " + 
				this.id_contratto + ", " + this.id_agenzia + ", " + this.fascia.toString() + ", " + this.disp;
	}
	
	/**
	 * Metodo che ritorna la targa di un auto da una
	 * stringa formattata che ne rappresenta gli attributi
	 * @param auto
	 * @return String la targa 
	 */
	public static String getTargaFromString(String auto)
	{
		StringTokenizer st = new StringTokenizer(auto, "; ");
		return st.nextToken();
	}
	
}
