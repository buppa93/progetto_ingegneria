package controller;

import java.io.IOException;
import java.util.Optional;

import database.DatabaseConnectionException;
import database.DbAccess;
import database.TableAgency;
import view.AdminView;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class AdminDeleteAgencyController {
	AnchorPane rootPane;
	TextField id_field;
	Button delete_bttn;
	Button cancel_bttn;
	
	@FXML protected void onCancelAction(Event event) throws IOException
	{
	((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(AdminView.class.getResource("FXMLAdminView.fxml")));
	}
	
	@FXML protected void onDeleteAction (Event event) throws DatabaseConnectionException, IOException
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("ATTENZIONE!");
		alert.setHeaderText("Elimina Agenzia");
		alert.setContentText("Confermi di voler eliminare la seguente Agenzia?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK)
		{
			DbAccess db=new DbAccess();
			db.initConnection();
			TableAgency ta= new TableAgency(db);
			ta.deleteAgencyById(id_field.getText());
			
		} else {
			((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(AdminView.class.getResource("FXMLAdminView.fxml")));
		}
	}
}
