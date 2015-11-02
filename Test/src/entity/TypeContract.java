package entity;

import java.util.StringTokenizer;

public class TypeContract 
{
	String id;
	String tipo_nol;
	String tipo_km;
	String fascia;
	int km;
	double prezzo;
	double prezzo_km;
	
	public TypeContract(){};
	
	public TypeContract(String id, String tipo_nol, String tipo_km, String fascia, int km, double prezzo,
			double prezzo_km)
	{
		this.id=id;
		this.tipo_nol = tipo_nol;
		this.tipo_km = tipo_km;
		this.fascia = fascia;
		this.km = km;
		this.prezzo = prezzo;
		this.prezzo_km = prezzo_km;
	}
	
	public TypeContract(String tipo_nol, String tipo_km, String fascia, int km)
	{
		this.tipo_nol = tipo_nol;
		this.tipo_km = tipo_km;
		this.fascia = fascia;
		this.km = km;
	}
	
	public String getId()
	{return this.id;}
	
	public String getTypeNol()
	{return this.tipo_nol;}
	
	public String getChilometraggio()
	{return this.tipo_km;}
	
	public String getFascia()
	{return this.fascia;}
	
	public int getKm()
	{return this.km;}
	
	public double getPrice()
	{return this.prezzo;}
	
	public double getPricePerKm()
	{return this.prezzo_km;}
	
	public void setId(String id)
	{this.id=id;}
	
	public void setTipoNol(String type)
	{this.tipo_nol=type;}
	
	public void setChilometraggio(String typekm)
	{this.tipo_km=typekm;}
	
	public void setFascia(String fascia)
	{this.fascia = fascia;}
	
	public void setKm(int km)
	{this.km = km;}
	
	public void setPrice(double price)
	{this.prezzo = price;}
	
	public void setPricePerKm(double prezzo_km)
	{this.prezzo_km = prezzo_km;}
	
	/*public boolean isFasciavalid()
	{
		boolean isValid=false;
		if(this.type==Noleggio.GIORNALIERO || this.type==Noleggio.SETTIMANALE || this.typekm==Km.LIMITATO|| this.typekm==Km.ILLIMITATO)	
			isValid=true;
		
		return isValid;
	}*/
	
	public String toString()
	{return this.id + "," + this.tipo_nol + "," + this.tipo_km;}
	
	public static String getIdFromString(String type)
	{
		StringTokenizer st = new StringTokenizer(type, ", ");
		return st.nextToken();
	}
}
