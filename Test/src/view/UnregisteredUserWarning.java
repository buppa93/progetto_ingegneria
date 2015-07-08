package view;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class UnregisteredUserWarning 
{
	public UnregisteredUserWarning()
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Utente non registrato");
		alert.setHeaderText("Nome utente o password errato.");
		alert.setContentText("Controlla le credenziali!");

		alert.showAndWait();
	}

}
