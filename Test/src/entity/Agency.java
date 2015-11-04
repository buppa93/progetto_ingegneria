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
	 * @param number Stringa contenente il numero dell'agenzia
	 * @param name	Stringa contenente il nome dell'agenzia
	 * @param address Stringa contenente l'indirizzo dell'agenzia
	 */
	public Agency(String number, String name, String address)  
	{
		this.number=number;
		this.name=name;
		this.address=address;
	}
	
	/**
	 * Metodo che ritorna il numero dell'agenzia
	 * @return number ritorna il numero dell'agenzia
	 */
	public String getNumber()  
	{return this.number;}
	
	/**
	 * Metodo che ritorna il nome dell'agenzia
	 * @return name ritorna il nome dell'agenzia
	 */
	public String getName() 
	{return this.name;}
	
	/**
	 * Metodo che ritorna l'indirizzo dell'agenzia
	 * @return address ritorna l'indirizzo dell'agenzia
	 */
	public String getAddress()   
	{return this.address;}
	
	/**
	 * Metodo che setta il numero dell'agenzia
	 * @param number il numero dell'agenzia
	 */
	public void setNumber(String number) 
	{this.number=number;}
	
	/**
	 * Metodo che setta il nome dell'agenzia
	 * @param name il nome dell'agenzia
	 */
	public void setName(String name)  
	{this.name=name;}
	
	/**
	 * Metodo che setta l'indirizzo dell'agenzia
	 * @param address l'indirizzo dell'agenzia
	 */
	public void setAddress(String address) 
	{this.address=address;}
	
	/**
	 * Stabilisce se il numero dell'agenzia e' un numero
	 * valido.
	 * @return boolean vero se e' un numero valido, falso altrimenti
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
	 * @return string: numero, + nome, + indirizzo
	 */
	public String toString() 
	{return this.number + "," + this.name + "," + this.address;}

	/**
	 * Metodo che ritorna il numero di un agenzia da una
	 * stringa formattata che ne rappresenta gli attributi
	 * @param agency stringa: numero, + nome, + indirizzo
	 * @return numberOfAgency
	 */
	public static String getIdFromString(String agency)
	{
		StringTokenizer st = new StringTokenizer(agency, ", ");
		return st.nextToken();
	}
}
