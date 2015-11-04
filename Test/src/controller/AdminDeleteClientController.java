package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import view.AdminView;
import database.DAOTableClients;
import database.DatabaseConnectionException;
import database.DbAccess;
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
			DAOTableClients tb= new DAOTableClients(db);
			try {
				tb.delete(cell_field.getText());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(AdminView.class.getResource("FXMLAdminView.fxml")));
		}
		
	}
	
	@FXML protected void OnCancelAction(ActionEvent event) throws IOException
	{
		((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(AdminView.class.getResource("FXMLAdminView.fxml")));
	}
}
