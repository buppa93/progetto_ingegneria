package entity;

/**
 * Classe che astrae un'contratto
 * @author Utente
 *
 */
public class Contract 
{
	
	/**
	 * @param numero_ordine numero di contratto
	 * @param agency_number numero di agenzia che ha stipulato il contratto
	 * @param idCliente il numero di telefono del cliente
	 * @param data_inizio la data di inizio contratto
	 * @param data_fine la data di fine contratto
	 * @param duration la durata in giorni/settimane
	 * @param agency_return numero dell'agenzia in cui si desidera restituire l'auto
	 * @param km il numero di chilometri da percorrere
	 * @param type tipo di contratto (giornaliero/settimanale)
	 * @param type_km il tipo di chilometraggio (limitato/illimitato)
	 * @param price il costo del noleggio
	 * @param deposit acconto lasciato dal cliente
	 */
	String numero_ordine;
	String agency_number;
	String idCliente;
	String data_inizio;
	String data_fine;
	int duration;
	String agency_return;
	int km;
	String type;
	String type_km;
	double price;
	double deposit;
	String targa;
	
	/**
	 * Costruttore vuoto
	 */
	public Contract(){};
	
	/**
	 * Costruttore per la classe Contract
	 * @param numero_ordine numero di contratto
	 * @param agency_number il numero di agenzia
	 * @param idCliente il numero di telefono del cliente
	 * @param data_inizio data inizio contratto
	 * @param data_fine data fine contratto
	 * @param agency_return agenzia in cui lasciare l'auto
	 * @param km i chilometri da percorrere
	 * @param type_km tipo di chilometraggio (limitato/illimitato)
	 * @param price costo del noleggio
	 * @param deposit acconto versato
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
	 * Costruttore per la classe Contract
	 * @param numero_ordine numero di contratto
	 * @param agency_number il numero di agenzia
	 * @param idCliente il numero di telefono del cliente
	 * @param data_inizio data inizio contratto
	 * @param duration la durata delnoleggio (giorni/settimane)
	 * @param data_fine data_fine data fine contratto
	 * @param agency_return agenzia in cui lasciare l'auto
	 * @param targa targa dell'auto
	 * @param type tipo di noleggio (giornaliero/settimanale)
	 * @param deposit deposit acconto versato 
	 * @param price costo del noleggio
	 */
	public Contract(String numero_ordine, String agency_number, String idCliente, String data_inizio, int duration,
			String data_fine, String agency_return, String targa, String type, double deposit, double price)
	{
		
		this.numero_ordine=numero_ordine;
		this.agency_number=agency_number;
		this.idCliente=idCliente;
		this.data_inizio=data_inizio;
		this.data_fine=data_fine;
		this.agency_return=agency_return;
		this.targa=targa;
		this.type = type;
		this.price = price;
		this.deposit = deposit;
		this.duration = duration;
	}
	
	
	/**
	 * Costruttore per la classe Contract
	 * @param numero_ordine numero di contratto
	 * @param agency_number il numero di agenzia
	 * @param idCliente il numero di telefono del cliente
	 * @param data_inizio data inizio contratto
	 * @param duration la durata delnoleggio (giorni/settimane)
	 * @param agency_return agenzia in cui lasciare l'auto
	 * @param type tipo di noleggio (giornaliero/settimanale)
	 * @param price costo del noleggio
	 * @param deposit deposit deposit acconto versato 
	 * @param targa targa dell'auto
	 */
	public Contract(String numero_ordine, String agency_number, String idCliente, String data_inizio, 
			int duration, String agency_return, String type, double price, double deposit, 
			String targa)
	{
		
		this.numero_ordine=numero_ordine;
		this.agency_number=agency_number;
		this.idCliente=idCliente;
		this.data_inizio=data_inizio;
		this.duration = duration;
		this.agency_return=agency_return;
		this.type = type;
		this.price = price;
		this.deposit = deposit;
		this.targa = targa;
	}
	
	/**
	 * Metodo che ritorna il numero di agenzia
	 * dove e' stato stipulato il contratto
	 * @return number  numero di agenzia
	 */
	public String getAgencyId()
	{
		return agency_number;
	}
	
	/**
	 * Metodo che ritorna il numero di telefono
	 * del cliente
	 * @return idCliente numero di telefono del cliente
	 */
	public String getClientId(){
		return idCliente;
	}
	
	/** 
	 * Metodo che ritorna il numero di contratto
	 * @return numero_ordine numero contratto
	 */
	public String getNumeroOrdine()
	{return this.numero_ordine;}
	
	/**
	 * Metodo che ritorna il numero di giorni/settimane 
	 * di durata del noleggio
	 * @return duration numero di giorni/settimane
	 */
	public int getDuration()
	{
		return this.duration;
	}
	
	/**
	 * Metodo che ritorna la data di inizio del contratto
	 * @return data_inizio data inizio
	 */
	public String getDataInizio()
	{return this.data_inizio;}
	
	/**
	 * Metodo che ritorna la data di termine del contratto
	 * @return data_fine data fine
	 */
	public String getDataFine()
	{return this.data_fine;}
	
	/**
	 * Metodo che ritorna il numero di agenzia nella quale
	 * verra' restituita l'auto
	 * @return agency_return agenzia ritorno
	 */
	public String getAgencyReturn()
	{
		return this.agency_return;
	}
	
	/**
	 * Metodo che ritorna il numero massimo di km che si
	 * possono percorrere
	 * @return km km massimi
	 */
	public int getKm()
	{return this.km;}
	
	/**
	 * Metodo che ritorna il tipo di chilometraggio
	 * (limitato/illimitato)
	 * @return type_km tipo chilometraggio (limitato/illimitato)
	 */
	public String getTypeKm()
	{
		return this.type_km;
	}
	
	/**
	 * Metodo che ritorna il costo del noleggio
	 * @return price costo del noleggio
	 */
	public double getPrice()
	{
		return this.price;
	}
	
	/**
	 * Metodo che ritorna l'acconto versato
	 * @return deposit acconto versato 
	 */
	public double getDeposit()
	{
		return this.deposit;
	}
	
	/**
	 * Metodo che ritorna il numero di targa 
	 * dell'auto
	 * @return targa targa
	 */
	public String getTarga()
	{
		return this.targa;
	}
	
	/**
	 * Metodo che ritorna il tipo di contratto
	 * (giornaliero/settimanale)
	 * @return type tipo contratto (giornaliero/settimanale)
	 */
	public String getTypeContract()
	{
		return this.type;
	}
	
	/**
	 * Metodo che setta il numero di contratto
	 * @param numero_ordine numero di contratto
	 */
	public void setNumero_ordine(String numero_ordine)
	{this.numero_ordine=numero_ordine;}
	
	/**
	 * Metodo che setta la data di inizio contratto
	 * @param data_inizio data inizio contratto
	 */
	public void setData_inizio(String data_inizio)
	{this.data_inizio=data_inizio;}
	
	/**
	 * Metodo che setta la data termine del contratto
	 * @param data_fine data termine contratto
	 */
	public void setData_fine(String data_fine)
	{this.data_fine=data_fine;}
	
	/**
	 * Metodo che setta il numero massimo di km 
	 * percorribili
	 * @param km km percorribili
	 */
	public void setKm(int km)
	{this.km=km;}
	
	/**
	 * Metodo che setta il numero dell'agenzia
	 * di restituzione dell'auto
	 * @param agency_return numero agenzia di restituzione
	 */
	public void setAgencyReturn(String agency_return)
	{
		this.agency_return = agency_return;
	}
	
	/**
	 * Metodo che setta il tipo di chilometraggio
	 * (giornaliero/settimanale)
	 * @param type_km tipo di chilometraggio (giornaliero/settimanale)
	 */
	public void setTypeKm(String type_km)
	{
		this.type_km = type_km;
	}
	
	/**
	 * Metodo che setta il costo del contratto
	 * @param price costo del contratto
	 */
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	/**
	 * Metodo che setta l'acconto versato
	 * @param deposit acconto versato
	 */
	public void setDeposit(double deposit)
	{
		this.deposit = deposit;
	}
	
	/**
	 * Stabilisce se il numero di contratto e'
	 * valido
	 * @return boolean vero se e' valido, falso altrimenti
	 */
	public boolean isValid()
	{
		boolean isValid=true;
		if(numero_ordine.length()!=5)
			isValid=false;
		return isValid;
	}

	/**
	 * Metodo che ritorna una stringa che descrive un'oggetto
	 * della classe
	 * @return String numeroOrdine, numeroAgenzia, numeroTelefonoCliente, dataInizio, durata, dataFine, agenziaRitorno, tipo, prezzo, acconto, targa
	 */
	public String toLabel()
	{
		return this.numero_ordine + ", " + this.agency_number + ", " + this.idCliente + ", " + this.data_inizio
				 + ", " + this.duration + ", " + this.data_fine + ", " + this.agency_return + ", " + this.type + ", " + this.price
				 + ", " + this.deposit + ", " + this.targa; 
	}
	
}
