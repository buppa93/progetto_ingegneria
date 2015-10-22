package controller;

import java.io.IOException;
import java.util.Optional;

import view.AdminView;
import database.DatabaseConnectionException;
import database.DbAccess;
import database.TableClients;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class AdminDeleteClientController {
	@FXML AnchorPane rootPane;
	@FXML TextField cell_field;
	
	@FXML protected void OnDeleteAction (Event event) throws DatabaseConnectionException, IOException
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("ATTENZIONE!");
		alert.setHeaderText("Elimina Cliente");
		alert.setContentText("Confermi di voler eliminare il cliente?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK)
		{
			DbAccess db=new DbAccess();
			db.initConnection();
			TableClients tb= new TableClients(db);
			tb.deleteClientByPhone(cell_field.getText());
			
		} else {
			((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(AdminView.class.getResource("FXMLAdminView.fxml")));
		}
		
	}
	
	@FXML protected void OnCancelAction(ActionEvent event) throws IOException
	{
		((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(AdminView.class.getResource("FXMLAdminView.fxml")));
	}
}
