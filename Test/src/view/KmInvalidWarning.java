package view;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class KmInvalidWarning 
{
	public KmInvalidWarning()
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Errore");
		alert.setHeaderText("Chilometri inferiori a quelli già presenti nel database.");
		alert.setContentText("Controlla i chilometri effettivi dell'autovettura!");

		alert.showAndWait();
	}

}
