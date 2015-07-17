package utility;

public class CarsAvailability 
{
	private String status;
	
	public CarsAvailability(int stat)
	{
		switch(stat)
		{
			case 1:
				status = "disponibile";
				break;
			case 2:
				status = "noleggio";
				break;
			case 3:
				status = "manutenzione_starordinaria";
				break;
			case 4:
				status = "manutenzione_ordinaria";
				break;
			default:
				status = "ND";
				break;
		}
	}
	
	public CarsAvailability(String status)
	{
		this.status = status;
	}
	
	public String getStatus()
	{
		return this.getStatus();
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public void setStatus(int stat)
	{		
		switch(stat)
		{
			case 1:
				status = "disponibile";
				break;
			case 2:
				status = "noleggio";
				break;
			case 3:
				status = "manutenzione_starordinaria";
				break;
			case 4:
				status = "manutenzione_ordinaria";
				break;
			default:
				status = "ND";
				break;
		}
	}
	
	public int toInt()
	{
		int rtrn = 0;
		
		switch(this.getStatus())
		{
			case "disponibile":
				rtrn = 1;
				break;
			case "noleggio":
				rtrn = 2;
				break;
			case "manutenzione_starordinaria":
				rtrn = 3;
				break;
			case "manutenzione_ordinaria":
				rtrn = 4;
				break;
			default:
				rtrn = 0;
				break;
		}
		return rtrn;
	}
	
	public static int toInt(String disp)
	{
		int rtrn = 0;
		
		switch(disp)
		{
			case "disponibile":
				rtrn = 1;
				break;
			case "noleggio":
				rtrn = 2;
				break;
			case "manutenzione_starordinaria":
				rtrn = 3;
				break;
			case "manutenzione_ordinaria":
				rtrn = 4;
				break;
			default:
				rtrn = 0;
				break;
		}
		return rtrn;
	}
	
	public static String toString(int disp)
	{
		String rtrn = "";
		
		switch(disp)
		{
			case 1:
				rtrn = "disponiblile";
				break;
			case 2:
				rtrn = "noleggio";
				break;
			case 3:
				rtrn = "manutenzione_straordinaria";
				break;
			case 4:
				rtrn = "manutenzione_ordinaria";
				break;
			default:
				rtrn = "N/D";
				break;
		}
		return rtrn;
	}

}
