package view;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class SQLWarning 
{
	public SQLWarning()
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Errore SQL");
		alert.setHeaderText("Errore nella comunicazione con il database.");
		alert.setContentText("Contatta l'assistenza clienti!");

		alert.showAndWait();
	}

}
