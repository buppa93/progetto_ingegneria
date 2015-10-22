package entity;

import java.util.StringTokenizer;

public class TypeContract 
{
	int id;
	public enum Noleggio { GIORNALIERO, SETTIMANALE}
	Noleggio type;
	public enum Km { LIMITATO, ILLIMITATO}
	Km typekm;
	
	public TypeContract(){};
	
	public TypeContract(int id, Noleggio type, Km typekm)
	{
		this.id=id;
		this.type=type;
		this.typekm=typekm;
	}
	
	public int getId()
	{return this.id;}
	
	public Noleggio getTypeContract()
	{return this.type;}
	
	public Km getChilometraggio()
	{return this.typekm;}
	
	public void setId(int id)
	{this.id=id;}
	
	public void setTipo(Noleggio type)
	{this.type=type;}
	
	public void setChilometraggio(Km typekm)
	{this.typekm=typekm;}
	
	public boolean isFasciavalid()
	{
		boolean isValid=false;
		if(this.type==Noleggio.GIORNALIERO || this.type==Noleggio.SETTIMANALE || this.typekm==Km.LIMITATO|| this.typekm==Km.ILLIMITATO)	
			isValid=true;
		
		return isValid;
	}
	
	public String toString()
	{return this.id + "," + this.type + "," + this.typekm;}
	
	public static String getIdFromString(String type)
	{
		StringTokenizer st = new StringTokenizer(type, ", ");
		return st.nextToken();
	}
}
