package controller;

import java.io.IOException;
import java.util.Optional;

import database.DatabaseConnectionException;
import database.DbAccess;
import database.TableAuto;
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

public class AdminDeleteCarController {
	@FXML 	AnchorPane rootPane;
	@FXML	TextField targa_field;
	@FXML	Button confirm_bttn;
	@FXML	Button cancel_bttn;
	
	@FXML protected void OnCancelAction(Event event) throws IOException
	{
	((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(AdminView.class.getResource("FXMLAdminView.fxml")));
	}
	
	@FXML protected void OnConfirmAction (Event event) throws DatabaseConnectionException, IOException{
	

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("ATTENZIONE!");
		alert.setHeaderText("Elimina Auto");
		alert.setContentText("Confermi di voler eliminare la seguente Auto?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK)
		{
			DbAccess db=new DbAccess();
			db.initConnection();
			TableAuto ta= new TableAuto(db);
			ta.deleteAutoByTarga(targa_field.getText());
			
		} else {
			((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(AdminView.class.getResource("FXMLAdminView.fxml")));
		}
	}
}