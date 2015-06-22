package entity;

public class Client 
{
	private String id;
	private String name;
	private String surname;
	private String TelephoneNumber;

	public Client()
	{
		//this.id="NULL";
		this.name="NULL";
		this.surname="NULL";
		this.TelephoneNumber="NULL";
	}

	public Client(String name, String surname, String TelephoneNumber)
	{
		this.name=name;
		this.surname=surname;
		this.TelephoneNumber=TelephoneNumber;
	}

	public String getId()
	{return this.id;}
	
	public String getName()
	{return this.name;}
	
	public String getSurname()
	{return this.surname;}
	
	public String getTelephoneNumber()
	{return this.TelephoneNumber;}
	
	public String setID(String id)
	{return this.id=id;}
	
	public String setName(String name)
	{return this.name=name;}
	
	public String setSurname(String surname)
	{return this.surname=surname;}
	
	public String setTelephoneNumber(String TelephoneNumber)
	{return this.TelephoneNumber=TelephoneNumber;}
	
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
