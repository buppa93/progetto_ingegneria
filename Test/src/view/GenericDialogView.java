package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class GenericDialogView 
{
	String title;
	String message;
	
	public GenericDialogView(String title, String message)
	{
		this.title = title;
		this.message = message;
	}
	
	public void start()
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
	}

}
