package entity;


public class Agency 
{
	String number;
	String name;
	String address;
	
	public Agency()
	{
		this.number="NULL";
		this.name="NULL";
		this.address="NULL";
	}
	
	public Agency(String number, String name, String address)
	{
		this.number=number;
		this.name=name;
		this.address=address;
	}
	
	public String getNumber()
	{return this.number;}
	
	public String getName()
	{return this.name;}
	
	public String getAddress()
	{return this.address;}
	
	public void setNumber(String number)
	{this.number=number;}
	
	public void setName(String name)
	{this.name=name;}
	
	public void setAddress(String address)
	{this.address=address;}
	
	public boolean isNumberValid()
	{
		boolean IsValid=false;
		if(number.length()==5)
			IsValid=true;
		return IsValid;	
	}
	
	public String toString()
	{return this.number + "," + this.name + "," + this.address;}
}

