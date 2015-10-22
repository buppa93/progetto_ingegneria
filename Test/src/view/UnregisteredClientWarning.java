package view;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class UnregisteredClientWarning 
{
	public UnregisteredClientWarning()
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Cliente non registrato");
		alert.setHeaderText("Il cliente non e' stato trovato nel database.");
		alert.setContentText("Controlla le credenziali!");

		alert.showAndWait();
	}

}
