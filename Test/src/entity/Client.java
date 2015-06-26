package entity;

public class Client 
{
	private String id;
	private String name;
	private String surname;
	private String TelephoneNumber;
	private String Type;
	private String pwd;

	public Client()
	{
		//this.id="NULL";
		this.name="NULL";
		this.surname="NULL";
		this.TelephoneNumber="NULL";
		this.Type = "NULL";
		this.pwd = "NULL";
	}

	public Client(String name, String surname, String TelephoneNumber, String type, String pwd)
	{
		this.name=name;
		this.surname=surname;
		this.TelephoneNumber=TelephoneNumber;
		this.Type = type;
		this.pwd = pwd;
	}

	public String getId()
	{return this.id;}
	
	public String getName()
	{return this.name;}
	
	public String getSurname()
	{return this.surname;}
	
	public String getTelephoneNumber()
	{return this.TelephoneNumber;}
	
	public String getType()
	{return this.Type;}
	
	public String getPassword()
	{return this.pwd;}
	
	public void setID(String id)
	{this.id=id;}
	
	public void setName(String name)
	{this.name=name;}
	
	public void setSurname(String surname)
	{this.surname=surname;}
	
	public void setTelephoneNumber(String TelephoneNumber)
	{this.TelephoneNumber=TelephoneNumber;}
	
	public void setType(String type)
	{this.Type=type;}
	
	public void setPassword(String pwd)
	{this.pwd=pwd;}
	
	
	//TODO modificare toString con tipo e password
	public String toString()
	{
		return "ID of the customer:" + this.id + " ; " + "Name of the customer:" + this.name + "; "
				+ "Surname of the customer:" + this.surname + "; " + "Telephone Number of Customer:"
				+ this.TelephoneNumber + ". ";
	}

	public boolean isIdValid()
	{ 
		//if numbers of ID is 5, it returns true
		boolean IsValid=false;
		if(id.length()==5)
			IsValid=true;
		return IsValid;	
	}
	
	public boolean isTelephoneValid()
	{
		//if length of telephone number is 10, it returns true
		boolean IsValid=false;
		if(id.length()==10)
			IsValid=true;
		return IsValid;
	}

}
