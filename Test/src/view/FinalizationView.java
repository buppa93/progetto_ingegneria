package view;

import java.util.Map;

import entity.Auto;
import entity.Client;

public final class FinalizationView 
{
	private static FinalizationView instance = new FinalizationView();
	private Client client;
	private Auto auto;
	private Map<String, String> parameters;
	
	public FinalizationView(){}
	
	public void setClient(Client c)
	{
		this.client = c;
	}
	
	public void setAuto(Auto a)
	{
		this.auto = a;
	}
	
	public void setParameters(Map<String, String> parameters)
	{
		this.parameters = parameters;
	}
	
	public Client getClient()
	{
		return this.client;
	}
	
	public Auto getAuto()
	{
		return this.auto;
	}
	
	public Map<String, String> getParameters()
	{
		return this.parameters;
	}
	
	public static FinalizationView getInstance()
	{
		return instance;
	}

}
