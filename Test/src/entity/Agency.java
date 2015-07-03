package entity;

/**
 * It is a rental agency characterized by name, address and identification number
 * @author Utente
 *
 */
public class Agency 
{
	String number;
	String name;
	String address;
/**
 *  it initializes the class agency to null
 */
	public Agency()  
	{
		this.number="NULL";
		this.name="NULL";
		this.address="NULL";
	}
	/**
	 * initializes the agency class through the input parameters
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
	 * it returns number of agency
	 * @return number
	 */
	public String getNumber()  
	{return this.number;}
	/**
	 * it returns name of agency
	 * @return name
	 */
	public String getName() 
	{return this.name;}
	/**
	 * it returns address of agency
	 * @return address
	 */
	public String getAddress()   
	{return this.address;}
	/**
	 * it sets number of agency
	 * @param number
	 */
	public void setNumber(String number) 
	{this.number=number;}
	/**
	 * it sets name of agency
	 * @param name
	 */
	public void setName(String name)  
	{this.name=name;}
	/**
	 * it sets address of agency
	 * @param address
	 */
	public void setAddress(String address) 
	{this.address=address;}
	/**
	 * it establishes if the number of agency is valid. The valid number must be 5 digits
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
	 * it returns all attributes of agency
	 * @return tuple of agency
	 */
	public String toString() 
	{return this.number + "," + this.name + "," + this.address;}
}

