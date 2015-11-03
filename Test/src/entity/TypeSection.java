package entity;

public class TypeSection 
{
	public enum Nome{A, B, C, D}
	Nome section;
	String fascia;
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
	
	public TypeSection(String fascia, int n_porte, int n_posti, String tipo_auto)
	{
		this.fascia=fascia;
		this.n_porte=n_porte;
		this.n_posti=n_posti;
		this.tipo_auto=tipo_auto;
	}
	
	public String getSFascia()
	{return this.fascia;}
	
	public Nome getFascia()
	{return this.section;}
	
	public int getN_porte()
	{return this.n_porte;}
	
	public int getN_posti()
	{return this.n_posti;}
	
	public String getTipo_vettura()
	{return this.tipo_auto;}
	
	public void setSFascia(String fascia)
	{this.fascia=fascia;}
	
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
	
	/*public String toString()
	{return this.section + "," + this.n_porte + "," + this.n_posti + "," + this.tipo_auto;}*/
	public String toString()
	{
		return this.fascia+", "+this.n_porte+", "+this.n_posti+", "+this.tipo_auto+";";
	}
	
	public static char resolvType(String type)
	{
		char t = 0;
		switch (type)
		{
			case "Utilitaria":
				t = 'A';
				break;
			
			case "Autocarro":
				t = 'C';
				break;
				
			case "Berlina":
				t = 'B';
				break;
			
			case "Monovolume":
				t = 'D';
				break;
				
			default:
				t = '0';
				break;
		}
		return t;
		
	}
	
	public static String resolvName(char type)
	{
		String t = "";
		switch (type)
		{
			case 'A':
				t = "Utilitaria";
				break;
			
			case 'C':
				t = "Autocarro";
				break;
				
			case 'B':
				t = "Berlina";
				break;
			
			case 'D':
				t = "Monovolume";
				break;
			
			default:
				t = "null";
				break;
		}
		return t;
		
	}

}


