package entity;
/**
 * represent contract between customer and agency
 * @author Utente
 *
 */
public class Contract 
{
	String numero_ordine;
	String agency_number;
	String idCliente;
	String data_inizio;
	String data_fine;
	String agency_return;
	int km;
	String type_km;
	double price;
	double deposit;
	
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
	public Contract(String numero_ordine, String agency_number, String idCliente, String data_inizio, String data_fine, String agency_return, int km, String type_km, double price, double deposit)
	{
		
		this.numero_ordine=numero_ordine;
		this.agency_number=agency_number;
		this.idCliente=idCliente;
		this.data_inizio=data_inizio;
		this.data_fine=data_fine;
		this.agency_return=agency_return;
		this.km=km;
		this.type_km = type_km;
		this.price = price;
		this.deposit = deposit;
	}
	
	/**
	 * it gets number of agency of contract
	 * @return number 
	 */
	public String getAgencyId()
	{
		return agency_number;
	}
	
	/**
	 * it gets number of customer of contract
	 * @return id
	 */
	public String getClientId(){
		return idCliente;
	}
	
	/** 
	 * it gets number of contract
	 * @return number of contract
	 */
	public String getNumeroOrdine()
	{return this.numero_ordine;}
	
	/**
	 * it gets start date of contract
	 * @return start date
	 */
	public String getDataInizio()
	{return this.data_inizio;}
	
	/**
	 * it gets end date of contract
	 * @return end data
	 */
	public String getDataFine()
	{return this.data_fine;}
	
	public String getAgencyReturn()
	{
		return this.agency_return;
	}
	/**
	 * it gets maximum mileage
	 * @return maximum mileage
	 */
	public int getKm()
	{return this.km;}
	
	public String getTypeKm()
	{
		return this.type_km;
	}
	
	public double getPrice()
	{
		return this.price;
	}
	
	public double getDeposit()
	{
		return this.deposit;
	}
	
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
	public void setKm(int km)
	{this.km=km;}
	
	public void setAgencyReturn(String agency_return)
	{
		this.agency_return = agency_return;
	}
	
	public void setTypeKm(String type_km)
	{
		this.type_km = type_km;
	}
	
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	public void setDeposit(double deposit)
	{
		this.deposit = deposit;
	}
	
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
	/*public String toString()
	{
		return this.numero_ordine + "," + this.number + "," + this.idCliente + "," + this.data_inizio + "," + this.data_fine + "," +this.restit+","+this.kmmax;
	}*/
		
}
