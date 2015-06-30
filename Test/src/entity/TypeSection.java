package entity;

public class TypeSection 
{
	public enum Nome{A, B, C, D}
	Nome section;
	int n_porte;
	int n_posti;
	String tipo_auto;
	
	public TypeSection(){};
	
	public TypeSection(Nome section, int n_porte, int n_posti, String tipo_auto)
	{
		this.section=section;
		this.n_porte=n_porte;
		this.n_posti=n_posti;
		this.tipo_auto=tipo_auto;
	}
	
	public Nome getFascia()
	{return this.section;}
	
	public int getN_porte()
	{return this.n_porte;}
	
	public int getN_posti()
	{return this.n_posti;}
	
	public String getTipo_vettura()
	{return this.tipo_auto;}
	
	public void setFascia(Nome section)
	{this.section=section;}
	
	public void setN_porte(int n_porte)
	{this.n_porte=n_porte;}
	
	public void setN_posti(int n_posti)
	{this.n_posti=n_posti;}
	
	public void setTipo_vettura(String tipo_auto)
	{this.tipo_auto=tipo_auto;}
	
	public boolean isFasciavalid()
	{
		boolean isValid=false;
		if(this.section==Nome.A || this.section==Nome.B || this.section==Nome.C|| this.section==Nome.D)	
			isValid=true;
		
		return isValid;	
	}
	
	public String toString()
	{return this.section + "," + this.n_porte + "," + this.n_posti + "," + this.tipo_auto;}

}


