package entity;

public class Price 
{
	private String fascia;
	private String type_km;
	private double cost_per_km;
	private double cost_gg;
	
	public Price(String fascia, String type_km, double cost_per_km, double cost_gg)
	{
		this.fascia = fascia;
		this.type_km = type_km;
		this.cost_per_km = cost_per_km;
		this.cost_gg = cost_gg;
	}
	
	public String getFascia()
	{
		return this.fascia;
	}
	
	public String getTypeKm()
	{
		return this.type_km;
	}
	
	public double getCostPerKm()
	{
		return this.cost_per_km;
	}
	
	public double getCostGg()
	{
		return this.cost_gg;
	}

}
