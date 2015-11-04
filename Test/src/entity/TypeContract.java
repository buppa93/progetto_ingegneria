package entity;

import java.util.StringTokenizer;

/**
 * Classe che astrae il tipo di contratto
 * @author giuseppe
 *
 */
public class TypeContract 
{
	
	/**
	 * @param id id sulla tabella nel database
	 * @param tipo_nol giornaliero/settimanale
	 * @param tipo_km limitato/illimitato
	 * @param fascia A/B/C/D 
	 * @param km km massimi percorribili
	 * @param prezzo prezzo totale
	 * @param prezzo_km prezzo per km nel caso di tipo_km "limitato", null altrimenti
	 */
	String id; 
	String tipo_nol;
	String tipo_km;
	String fascia;
	int km;
	double prezzo;
	double prezzo_km;
	
	/**
	 * Costruttore vuoto
	 */
	public TypeContract(){};
	
	/**
	 * Costruttore per la classe
	 * @param id id sulla tabella nel database
	 * @param tipo_nol giornaliero/settimanale
	 * @param tipo_km limitato/illimitato
	 * @param fascia A/B/C/D
	 * @param km km massimi percorribili
	 * @param prezzo prezzo totale
	 * @param prezzo_km prezzo per km nel caso di tipo_km "limitato", null altrimenti
	 */
	public TypeContract(String id, String tipo_nol, String tipo_km, String fascia, int km, double prezzo,
			double prezzo_km)
	{
		this.id=id;
		this.tipo_nol = tipo_nol;
		this.tipo_km = tipo_km;
		this.fascia = fascia;
		this.km = km;
		this.prezzo = prezzo;
		this.prezzo_km = prezzo_km;
	}
	
	/**
	 * Costruttore per la classe
	 * @param tipo_nol giornaliero/settimanale
	 * @param tipo_km limitato/illimitato
	 * @param fascia A/B/C/D
	 * @param km prezzo totale
	 */
	public TypeContract(String tipo_nol, String tipo_km, String fascia, int km)
	{
		this.tipo_nol = tipo_nol;
		this.tipo_km = tipo_km;
		this.fascia = fascia;
		this.km = km;
	}
	
	/**
	 * Metodo che ritorna l'identificativo nel database
	 * @return id l'id nella tabella del database
	 */
	public String getId()
	{return this.id;}
	
	/**
	 * Metodo che ritorna il tipo di noleggio
	 * @return giornaliero/settimanale
	 */
	public String getTypeNol()
	{return this.tipo_nol;}
	
	/**
	 * Metodo che ritorna il tipo di chilometraggio
	 * @return tipo_km limitato/illimitato
	 */
	public String getChilometraggio()
	{return this.tipo_km;}
	
	/**
	 * Metodo che ritorna la fascia di automobile
	 * @return fascia A/B/C/D
	 */
	public String getFascia()
	{return this.fascia;}
	
	/**
	 * Metodo che ritorna i chilometri massimi percorribili
	 * @return km km massimi
	 */
	public int getKm()
	{return this.km;}
	
	/**
	 * Metodo che ritorna il prezzo totale per quegli attributi
	 * @return prezzo prezzo totale
	 */
	public double getPrice()
	{return this.prezzo;}
	
	/**
	 * Metodo che ritorna il prezzo per km nel caso in cui,
	 * type_km = "limitato"
	 * @return prezzo_km prezzo per chilometro nel caso in cui type_km = "limitato", null altrimenti
	 */
	public double getPricePerKm()
	{return this.prezzo_km;}
	
	/**
	 * Metodo che setta l'id
	 * @param id identificativo
	 */
	public void setId(String id)
	{this.id=id;}
	
	/**
	 * Setta il tipo di noleggio
	 * @param type "giornaliero"/"settimanale"
	 */
	public void setTipoNol(String type)
	{this.tipo_nol=type;}
	
	/**
	 * Setta il tipo di chilometraggio
	 * @param typekm "limitato"/"illimitato"
	 */
	public void setChilometraggio(String typekm)
	{this.tipo_km=typekm;}
	
	/**
	 * Setta la fascia dell'automobile
	 * @param fascia A/B/C/D
	 */
	public void setFascia(String fascia)
	{this.fascia = fascia;}
	
	/**
	 * Setta i km massimi
	 * @param km km massimi
	 */
	public void setKm(int km)
	{this.km = km;}
	
	/**
	 * Setta il prezzo totale
	 * @param price prezzo
	 */
	public void setPrice(double price)
	{this.prezzo = price;}
	
	/**
	 * Setta il prezzo per km
	 * @param prezzo_km prezzo per km
	 */
	public void setPricePerKm(double prezzo_km)
	{this.prezzo_km = prezzo_km;}
	
	/**
	 * Ritorna una stringa descrittiva per un oggetto
	 * della classe
	 * @return String id,tipo_nol,tipo_km
	 */
	public String toString()
	{return this.id + "," + this.tipo_nol + "," + this.tipo_km;}
	
	/**
	 * Ricava k0id da una stringa descrittiva
	 * @param type stringa descrittiva
	 * @return id id dell'oggetto della classe
	 */
	public static String getIdFromString(String type)
	{
		StringTokenizer st = new StringTokenizer(type, ", ");
		return st.nextToken();
	}
}
