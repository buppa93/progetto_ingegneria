package entity;
/**
 * Classe che astrae un cliente
 * @author Utente
 *
 */
public class Client 
{
	/**
	 * @param name il nome del cliente
	 * @param surname il cognome del cliente
	 * @param il numero di telefono del cliente
	 */
	private String name;
	private String surname;
	private String phone;

 
	/**
	 * Costruttore che inizzializza gli attributi della classe 
	 * a null
	 */
	public Client()
	{
		//this.id="NULL";
		this.name="NULL";
		this.surname="NULL";
		this.phone="NULL";
	}

	/**
	 * Costruttore per la classe Client
	 * @param name il nome del cliente
	 * @param surname il cognome del cliente
	 * @param TelephoneNumber il numero di telefono del cliente
	 */
	public Client(String name, String surname, String TelephoneNumber)
	{
		this.name=name;
		this.surname=surname;
		this.phone=TelephoneNumber;
	}
     
	/**
	* Metodo che ritorna il nome del 
	* cliente
	* @return name il nome del cliente
	*/
	public String getName()
	{return this.name;}
	
	/**
	 * Metodo che ritorna il cognome 
	 * del cliente
	 * @return surname il cognome del cliente
	 */
	public String getSurname()
	{return this.surname;}
	
	/**
	 * Metodo che ritorna il numero di telefono
	 * del cliente
	 * @return phone il numero di telefono
	 */
	public String getPhone()
	{return this.phone;}
	
	/**
	 * Metodo che setta il nome del cliente
	 * @param name nome del cliente
	 */
	public void setName(String name)
	{this.name=name;}
	
	/**
	 * Metodo che setta il cognome del
	 * cliente
	 * @param surname cognome del cliente
	 */
	public void setSurname(String surname)
	{this.surname=surname;}
	
	/**
	 * Metodo che setta il numero di telefono 
	 * del cliente
	 * @param TelephoneNumber numero di telefono del cliente
	 */
	public void setPhone(String TelephoneNumber)
	{this.phone=TelephoneNumber;}
	
	/**
	 * Metodo che ritorna una stringa rappresentante
	 * il Cliente
	 * @return string attributi del cliente
	 */
	public String toString()
	{
		return "Name of the customer:" + this.name + "; " + "Surname of the customer:" + this.surname + "; " + "Telephone Number of Customer:"
				+ this.phone+ ". ";
	}
	
	/**
	 * Metodo che ritorna una stringa rappresentante
	 * il Cliente 
	 * @return string nome, cognome, telefono
	 */
	public String toLabel()
	{
		return this.name + ", " + this.surname + ", " + this.phone; 
	}
	
	/**
	 * Metodo che controlla se il numero di telefono e' 
	 * valido
	 * @return boolean vero se e' valido, false altrimenti
	 */
	public boolean isTelephoneValid()
	{
		//if length of telephone number is 10, it returns true
		boolean IsValid=false;
		if(phone.length()==10)
			IsValid=true;
		return IsValid;
	}

}
