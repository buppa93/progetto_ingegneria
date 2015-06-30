package entity;

public class Contract extends TypeContract
{
	String numero_ordine;
	Agency number;
	Client idCliente;
	String data_inizio;
	String data_fine;
	Agency restit;
	int kmmax;
	
	public Contract(){};
	
	public Contract(String numero_ordine, Agency number, Client idCliente, String data_inizio, String data_fine, Agency restit, int id, Noleggio type, Km typekm, int kmmax)
	{
		super(id, type, typekm );
		this.numero_ordine=numero_ordine;
		this.number=number;
		//this.idCliente=idCliente;
		//this.data_inizio=data_inizio;
		this.data_fine=data_fine;
		//this.restit=restit;
		this.kmmax=kmmax;
	}
	
	public String getNumero_ordine()
	{return this.numero_ordine;}
	
	public String getData_inizio()
	{return this.data_inizio;}
	
	public String getData_fine()
	{return this.data_fine;}
	
	public int getKmmax()
	{return this.kmmax;}
	
	public void setNumero_ordine(String numero_ordine)
	{this.numero_ordine=numero_ordine;}
	
	public void setData_inizio(String data_inizio)
	{this.data_inizio=data_inizio;}
	
	public void setData_fine(String data_fine)
	{this.data_fine=data_fine;}
	
	public void setKmmax(int kmmax)
	{this.kmmax=kmmax;}
	
	public boolean isValid()
	{
		boolean isValid=true;
		if(numero_ordine.length()!=5)
			isValid=false;
		return isValid;
	}
	
	public String toString()
	{
		return this.numero_ordine + "," + this.number + "," + this.idCliente + "," + this.data_inizio + "," + this.data_fine + "," +this.restit+","+this.kmmax;
	}
		
}
