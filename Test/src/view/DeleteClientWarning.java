package view;

import java.util.Optional;

import database.DbAccess;
import database.TableClients;
import entity.Client;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;import javafx.scene.control.ButtonType;


public final class DeleteClientWarning 
{
	private static DeleteClientWarning instance = new DeleteClientWarning();
	private Client client;
	
	public DeleteClientWarning(){}
	
	public static DeleteClientWarning getInstance()
	{
		return instance;
	}
	
	public Client getClient()
	{
		return this.client;
	}
	
	public void setClient(Client client)
	{
		this.client = client;
	}
	
	public void show()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("ATTENZIONE!");
		alert.setHeaderText("Stai per eliminare il cliente:");
		alert.setContentText(client.getName()+" "+client.getSurname()+" "+ client.getPhone()+"");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK)
		{
			DbAccess db = new DbAccess();
			try 
			{
				db.initConnection();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			TableClients tc = new TableClients(db);
			tc.deleteClientByPhone(client.getPhone());
		} 
		else 
		{
		   alert.close();
		}

	}

}
