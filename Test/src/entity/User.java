package entity;
/**
 * it represents customer data
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

 /**
 * it initializes the client class to null
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
 * it initializes client class to input parameters
 * @param id
 * @param name
 * @param surname
 * @param TelephoneNumber
 * @param type
 * @param pwd
 */
	public User(String id, String name, String surname, String TelephoneNumber, String type, String pwd)
	{
		this.id = id;
		this.name=name;
		this.surname=surname;
		this.TelephoneNumber=TelephoneNumber;
		this.Type = type;
		this.pwd = pwd;
	}

 /**
 * it gets customer's id
 * @return id
 */
	public String getId()
	{return this.id;}
     
	/**
	* it gets customer's name
	* @return name
	*/
	public String getName()
	{return this.name;}
	
	/**
	 * it gets customer's surname
	 * @return surname
	 */
	public String getSurname()
	{return this.surname;}
	
	/**
	 * it gets customer's telephone number
	 * @return number of telephone
	 */
	public String getTelephoneNumber()
	{return this.TelephoneNumber;}
	
	/**
	 * it establishes if client is administrator or user
	 * @return type
	 */
	public String getType()
	{return this.Type;}
	
	/**
	 * it gets password of client
	 * @return password
	 */
	public String getPassword()
	{return this.pwd;}
	
	/**
	 * sets id through input parameter
	 * @param id
	 */
	public void setID(String id)
	{this.id=id;}
	
	/**
	 * sets customer's name through input parameter
	 * @param name
	 */
	public void setName(String name)
	{this.name=name;}
	
	/**
	 * sets customer's surname through input parameter
	 * @param surname
	 */
	public void setSurname(String surname)
	{this.surname=surname;}
	
	/**
	 * sets customer's telephone number through input parameter
	 * @param TelephoneNumber
	 */
	public void setTelephoneNumber(String TelephoneNumber)
	{this.TelephoneNumber=TelephoneNumber;}
	
	/**
	 * sets customer's type (admin or user) through input parameter
	 * @param type
	 */
	public void setType(String type)
	{this.Type=type;}
	
	/**
	 * sets customer's password through input parameter
	 * @param pwd
	 */
	public void setPassword(String pwd)
	{this.pwd=pwd;}
	
	/**
	 * returns tuple of client
	 * @return string of attribute of client
	 */
	public String toString()
	{
		return "ID of the customer:" + this.id + " ; " + "Name of the customer:" + this.name + "; "
				+ "Surname of the customer:" + this.surname + "; " + "Telephone Number of Customer:"
				+ this.TelephoneNumber +", Type: "+this.Type+", Password of Customer: "+this.pwd+ ". ";
	}
	
	/**
	 * establishes if customer's id is valid. If his length is 5, it is valid
	 * @return
	 */
	public boolean isIdValid()
	{ 
		//if numbers of ID is 5, it returns true
		boolean IsValid=false;
		if(id.length()==5)
			IsValid=true;
		return IsValid;	
	}
	
	/**
	 * establishes if customer's telephone number is valid. If his length is 10, it is valid
	 * @return
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
