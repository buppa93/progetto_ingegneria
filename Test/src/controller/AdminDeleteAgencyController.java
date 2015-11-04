package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import database.DAOTableAgency;
import database.DatabaseConnectionException;
import database.DbAccess;
import view.AdminView;
import javafx.event.ActionEvent;
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
	
	@FXML protected void onCancelAction(ActionEvent event) throws IOException
	{
	((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(AdminView.class.getResource("FXMLAdminView.fxml")));
	}
	
	@FXML protected void onDeleteAction (Event event) throws DatabaseConnectionException, IOException, SQLException
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
			DAOTableAgency ta= new DAOTableAgency(db);
			ta.delete(id_field.getText());
			
		} else {
			((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(AdminView.class.getResource("FXMLAdminView.fxml")));
		}
	}
}
