package entity;

import java.util.StringTokenizer;

/**
 * Rappresenta la classe Agenzia che astrae una filiale
 * caratterizzata da numero,nome e indirizzo.
 * @author Utente
 *
 */
public class Agency 
{
	String number;
	String name;
	String address;

	/**
	 *  Costruttore che inizializza gli attributi della 
	 *  classe a null
	 */
	public Agency()  
	{
		this.number="NULL";
		this.name="NULL";
		this.address="NULL";
	}
	
	/**
	 * Costruttore che inizializza gli attributi della classe 
	 * Agency in accordo con i valori dei paramentri del costruttore.
	 * @param number
	 * @param name
	 * @param address
	 */
	public Agency(String number, String name, String address)  
	{
		this.number=number;
		this.name=name;
		this.address=address;
	}
	
	/**
	 * Metodo che ritorna il numero dell'agenzia
	 * @return number
	 */
	public String getNumber()  
	{return this.number;}
	
	/**
	 * Metodo che ritorna il nome dell'agenzia
	 * @return name
	 */
	public String getName() 
	{return this.name;}
	
	/**
	 * Metodo che ritorna l'indirizzo dell'agenzia
	 * @return address
	 */
	public String getAddress()   
	{return this.address;}
	
	/**
	 * Metodo che setta il numero dell'agenzia
	 * @param number
	 */
	public void setNumber(String number) 
	{this.number=number;}
	
	/**
	 * Metodo che setta il nome dell'agenzia
	 * @param name
	 */
	public void setName(String name)  
	{this.name=name;}
	
	/**
	 * Metodo che setta l'indirizzo dell'agenzia
	 * @param address
	 */
	public void setAddress(String address) 
	{this.address=address;}
	
	/**
	 * Stabilisce se il numero dell'agenzia e' un numero
	 * valido.
	 * @return boolean
	 */
	public boolean isNumberValid() 
	{
		boolean IsValid=false;
		if(number.length()==5)
			IsValid=true;
		return IsValid;	
	}
	
	/**
	 * Metodo che ritorna gli attributi dell'agenzia
	 * sotto forma di stringa
	 * @return tuple of agency
	 */
	public String toString() 
	{return this.number + "," + this.name + "," + this.address;}

	/**
	 * Metodo che ritorna il numero di un agenzia da una
	 * stringa formattata che ne rappresenta gli attributi
	 * @param agency
	 * @return numberOfAgency
	 */
	public static String getIdFromString(String agency)
	{
		StringTokenizer st = new StringTokenizer(agency, ", ");
		return st.nextToken();
	}
}
