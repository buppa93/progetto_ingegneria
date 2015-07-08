package entity;
/**
 * represent contract between customer and agency
 * @author Utente
 *
 */
public class Contract extends TypeContract
{
	String numero_ordine;
	Agency number;
	User idCliente;
	String data_inizio;
	String data_fine;
	Agency restit;
	int kmmax;
	
	/**
	 * it initializes class Type Contract to null
	 */
	public Contract(){};
	
	/**
	 * it initializes class type contract through input parameter
	 * @param numero_ordine
	 * @param number
	 * @param idCliente
	 * @param data_inizio
	 * @param data_fine
	 * @param restit
	 * @param id
	 * @param type
	 * @param typekm
	 * @param kmmax
	 */
	public Contract(String numero_ordine, Agency number, User idCliente, String data_inizio, String data_fine, Agency restit, int id, Noleggio type, Km typekm, int kmmax)
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
	
	/**
	 * it gets number of agency of contract
	 * @return number 
	 */
	public String getNumberOfAgency()
	{
		return number.getNumber();
	}
	
	/**
	 * it gets number of customer of contract
	 * @return id
	 */
	public String getNumberCliente(){
		return idCliente.getId();
	}
	
	/** 
	 * it gets number of contract
	 * @return number of contract
	 */
	public String getNumero_ordine()
	{return this.numero_ordine;}
	
	/**
	 * it gets start date of contract
	 * @return start date
	 */
	public String getData_inizio()
	{return this.data_inizio;}
	
	/**
	 * it gets end date of contract
	 * @return end data
	 */
	public String getData_fine()
	{return this.data_fine;}
	
	/**
	 * it gets maximum mileage
	 * @return maximum mileage
	 */
	public int getKmmax()
	{return this.kmmax;}
	
	/**
	 * sets contract's number through input parameter
	 * @param numero_ordine
	 */
	public void setNumero_ordine(String numero_ordine)
	{this.numero_ordine=numero_ordine;}
	
	/**
	 * sets start data through input parameter
	 * @param data_inizio
	 */
	public void setData_inizio(String data_inizio)
	{this.data_inizio=data_inizio;}
	
	/**
	 * sets end data through input parameter
	 * @param data_fine
	 */
	public void setData_fine(String data_fine)
	{this.data_fine=data_fine;}
	
	/**
	 * sets maximum mileage through input parameter
	 * @param kmmax
	 */
	public void setKmmax(int kmmax)
	{this.kmmax=kmmax;}
	
	/**
	 * establishes if number of contract is valid. If his length is 5, it is valid
	 * @return boolean
	 */
	public boolean isValid()
	{
		boolean isValid=true;
		if(numero_ordine.length()!=5)
			isValid=false;
		return isValid;
	}
	
	/**
	 * return tuple of contract
	 * @return attributes of contract
	 */
	public String toString()
	{
		return this.numero_ordine + "," + this.number + "," + this.idCliente + "," + this.data_inizio + "," + this.data_fine + "," +this.restit+","+this.kmmax;
	}
		
}
