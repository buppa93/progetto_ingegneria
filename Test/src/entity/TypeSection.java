package entity;

/**
 * Classe che astrae il tipo di fascia automobile
 * @author giuseppe
 *
 */
public class TypeSection 
{
	//public enum Nome{A, B, C, D}
	//Nome section;
	private String fascia;
	private int n_porte;
	private int n_posti;
	private String tipo_auto;
	
	public TypeSection(){};
	
	/*public TypeSection(Nome section, int n_porte, int n_posti, String tipo_auto)
	{
		this.section=section;
		this.n_porte=n_porte;
		this.n_posti=n_posti;
		this.tipo_auto=tipo_auto;
	}*/
	
	/**
	 * Costruttore per la classe
	 * @param fascia A/B/C/D
	 * @param n_porte numero porte dell'auto
	 * @param n_posti numero posti dell'auto
	 * @param tipo_auto utilitaria, berlina, autocarro...
	 */
	public TypeSection(String fascia, int n_porte, int n_posti, String tipo_auto)
	{
		this.fascia=fascia;
		this.n_porte=n_porte;
		this.n_posti=n_posti;
		this.tipo_auto=tipo_auto;
	}
	
	/**
	 * Metodo che ritorna la fascia dell'
	 * automobile
	 * @return fascia A/B/C/D
	 */
	public String getSFascia()
	{return this.fascia;}
	/*
	public Nome getFascia()
	{return this.section;}
	 */
	
	/**
	 * Ritorna il numero di porte dell'
	 * auto
	 * @return n_porte numero di porte
	 */
	public int getN_porte()
	{return this.n_porte;}
	
	/**
	 * Ritorna il numero di posti dell'
	 * auto
	 * @return n_posti numero di posti
	 */
	public int getN_posti()
	{return this.n_posti;}
	
	/**
	 * Ritorna il tipo di autovettura
	 * @return tipo_auto utilitaria/berlina/autocarro/monovolume
	 */
	public String getTipo_vettura()
	{return this.tipo_auto;}
	
	/**
	 * Setta la fascia dell'automobile
	 * @param fascia A/B/C/D
	 */
	public void setSFascia(String fascia)
	{this.fascia=fascia;}
	
	/*
	public void setFascia(Nome section)
	{this.section=section;}
	*/
	
	/**
	 * Setta il numero di porte
	 *  dell'automobile
	 * @param n_porte numero porte
	 */
	public void setN_porte(int n_porte)
	{this.n_porte=n_porte;}
	
	
	/**
	 * Setta il numero di posti
	 * dell'automobile
	 * @param n_posti numero posti
	 */
	public void setN_posti(int n_posti)
	{this.n_posti=n_posti;}
	
	/**
	 * Setta il tipo dell'automobile
	 * @param tipo_auto utilitaria/berlina/autocarro/monovolume
	 */
	public void setTipo_vettura(String tipo_auto)
	{this.tipo_auto=tipo_auto;}
	
	/*
	public boolean isFasciavalid()
	{
		boolean isValid=false;
		if(this.section==Nome.A || this.section==Nome.B || this.section==Nome.C|| this.section==Nome.D)	
			isValid=true;
		
		return isValid;	
	}
	*/
	/*public String toString()
	{return this.section + "," + this.n_porte + "," + this.n_posti + "," + this.tipo_auto;}*/
	
	/**
	 * Ritorna una stringa descrittiva
	 * dell'oggetto
	 * @return String fascia, nPorte, nPosti, tipoAuto;
	 */
	public String toString()
	{
		return this.fascia+", "+this.n_porte+", "+this.n_posti+", "+this.tipo_auto+";";
	}
	
	
	/**
	 * Ricava la fascia dell'auto in base
	 * al suo tipo
	 * @param type utilitaria/monovolume/berlina/autocarro
	 * @return char A/B/C/D
	 */
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
	
	/**
	 * Ricava il tipo dell'auto in base
	 * alla sua fascia
	 * @param type A/B/C/D
	 * @return String utilitaria/monovolume/berlina/autocarro
	 */
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


