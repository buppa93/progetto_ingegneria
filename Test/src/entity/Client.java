package entity;
/**
 * it represents customer data
 * @author Utente
 *
 */
public class Client 
{
	private String name;
	private String surname;
	private String phone;

 /**
 * it initializes the client class to null
 */
	public Client()
	{
		//this.id="NULL";
		this.name="NULL";
		this.surname="NULL";
		this.phone="NULL";
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
	public Client(String name, String surname, String TelephoneNumber)
	{
		this.name=name;
		this.surname=surname;
		this.phone=TelephoneNumber;
	}
     
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
	public String getPhone()
	{return this.phone;}
	
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
	public void setPhone(String TelephoneNumber)
	{this.phone=TelephoneNumber;}
	/**
	 * returns tuple of client
	 * @return string of attribute of client
	 */
	public String toString()
	{
		return "Name of the customer:" + this.name + "; " + "Surname of the customer:" + this.surname + "; " + "Telephone Number of Customer:"
				+ this.phone+ ". ";
	}
	
	/**
	 * establishes if customer's telephone number is valid. If his length is 10, it is valid
	 * @return
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
