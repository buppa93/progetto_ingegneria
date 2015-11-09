package entity;
/**
 * Classe che astrae un utente del sistema
 * @author Utente
 *
 */
public class User 
{
	private String id;
	private String name;
	private String surname;
	private String TelephoneNumber;
	private String Type;
	private String pwd;
	private String id_agenzia;

	/**
	 * Costruttore per la classe che 
	 * inizializza tutti gli attributi
	 * a null
	 */
	public User()
	{
		//this.id="NULL";
		this.name="NULL";
		this.surname="NULL";
		this.TelephoneNumber="NULL";
		this.Type = "NULL";
		this.pwd = "NULL";
	}
    
	/**
	 * Costruttore per la classe
	 * @param id id dell'utente
	 * @param id_agenzia numero agenzia di appartenenza
	 * @param name nome utente
	 * @param surname cognome utente
	 * @param TelephoneNumber numero di telefono utente
	 * @param pwd password utente (md5)
	 * @param type tipo utente usr/adm
	 */
 	public User(String id, String id_agenzia, String name, String surname, String TelephoneNumber, String pwd, String type)
	{
		this.id = id;
		this.name=name;
		this.surname=surname;
		this.TelephoneNumber=TelephoneNumber;
		this.Type = type;
		this.pwd = pwd;
		this.id_agenzia = id_agenzia;
	}
	
 	/**
 	 * Costruttore per la classe
 	 * @param id id dell'utente
 	 * @param name nome utente
	 * @param surname cognome utente
	 * @param TelephoneNumber numero di telefono utente
 	 * @param type tipo utente usr/adm
 	 */
	public User(String id, String name, String surname, String TelephoneNumber, String type)
	{
		this.id = id;
		this.name=name;
		this.surname=surname;
		this.TelephoneNumber=TelephoneNumber;
		this.Type = type;
	}

	/**
	 * Ritotna l'id dell'utente
	 * @return String id 
	 */
	public String getId()
	{return this.id;}
     
	/**
	 * Ritotna il nome dell'utente
	 * @return String nome 
	 */
	public String getName()
	{return this.name;}
	
	/**
	 * Ritotna il cognome dell'utente
	 * @return String cognome 
	 */
	public String getSurname()
	{return this.surname;}
	
	/**
	 * Ritotna il nnuemro di telefono
	 * dell'utente
	 * @return String numero di telefono 
	 */
	public String getTelephoneNumber()
	{return this.TelephoneNumber;}
	
	/**
	 * Ritotna il tipo dell'utente
	 * @return String usr/adm 
	 */
	public String getType()
	{return this.Type;}
	
	/**
	 * Ritotna la password dell'utente
	 * @return String password (md5) 
	 */
	public String getPassword()
	{return this.pwd;}
	
	/**
	 * Ritotna il numero dell'agenzia di 
	 * appartenenza dell'utente
	 * @return String numero agenzia 
	 */
	public String getIdAgency()
	{return this.id_agenzia;}
	
	/**
	 * Setta l'id dell'utente
	 * @param id id utente
	 */
	public void setID(String id)
	{this.id=id;}
	
	/**
	 * Setta il numero di agenzia di
	 * appartenenza dell'utente
	 * @param id numero agenzia
	 */
	public void setIdAgency(String id)
	{this.id_agenzia=id;}
	
	/**
	 * Setta il nome dell'utente
	 * @param name nome utente
	 */
	public void setName(String name)
	{this.name=name;}
	
	/**
	 * Setta il cognome dell'utente
	 * @param surname cognome utente
	 */
	public void setSurname(String surname)
	{this.surname=surname;}
	
	/**
	 * Setta il numero di telefono 
	 * dell'utente
	 * @param TelephoneNumber numero telefono utente
	 */
	public void setTelephoneNumber(String TelephoneNumber)
	{this.TelephoneNumber=TelephoneNumber;}
	
	/**
	 * Setta il tipo dell'utente
	 * @param type urs/adm
	 */
	public void setType(String type)
	{this.Type=type;}
	
	/**
	 * Setta la password in md5 dell'utente
	 * @param pwd password utente (md5)
	 */
	public void setPassword(String pwd)
	{this.pwd=pwd;}
	
	/**
	 * Ritorna una stringa descrittiva
	 * dell'oggetto
	 * @return String id; nome; cognome; numeroTelefono; tipo; pwd.
	 */
	public String toString()
	{
		return "ID of the customer:" + this.id + " ; " + "Name of the customer:" + this.name + "; "
				+ "Surname of the customer:" + this.surname + "; " + "Telephone Number of Customer:"
				+ this.TelephoneNumber +", Type: "+this.Type+", Password of Customer: "+this.pwd+ ". ";
	}
	/*
	/**
	 * establishes if customer's id is valid. If his length is 5, it is valid
	 * @return
	 *//*
	public boolean isIdValid()
	{ 
		//if numbers of ID is 5, it returns true
		boolean IsValid=false;
		if(id.length()==5)
			IsValid=true;
		return IsValid;	
	}*/
	
	/**
	 * Stabilisce se il numero di telefono 
	 * e' valido
	 * @return boolean vero se valido, falso altrimenti
	 */
	public boolean isTelephoneValid()
	{
		//if length of telephone number is 10, it returns true
		boolean IsValid=false;
		if(id.length()==10)
			IsValid=true;
		return IsValid;
	}

}
