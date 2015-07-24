package view;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class NotFoundClientWarning 
{
	public NotFoundClientWarning()
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Errore");
		alert.setHeaderText("Cliente non trovato.");
		alert.setContentText("Controlla il numero di telefono.");

		alert.showAndWait();
	}

}
