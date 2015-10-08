package view;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class GenericWarning
{
	String header;
	String message;
	
	public GenericWarning(String header, String message)
	{
		this.header = header;
		this.message = message;
	}
	
	public void start()
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Attenzione!");
		alert.setHeaderText(header);
		alert.setContentText(message);
		//alert.setContentText("Contatta l'assistenza!");

		alert.showAndWait();
	}

}


