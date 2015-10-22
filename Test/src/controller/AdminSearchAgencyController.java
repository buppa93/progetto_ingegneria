package controller;

import java.io.IOException;

import database.DatabaseConnectionException;
import database.DbAccess;
import database.TableAgency;
import view.AdminView;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class AdminSearchAgencyController {
	@FXML private AnchorPane rootPane;
	@FXML private TextField number_field;
	@FXML private TextField name_field;
	@FXML private Label result_label;
	
	@FXML protected void onCancelAction(ActionEvent event) throws IOException
	{
		((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(AdminView.class.getResource("FXMLAdminView.fxml")));
	}
	
	@FXML protected void onSearchAction(Event event) throws DatabaseConnectionException
	{
		DbAccess db = new DbAccess();
		db.initConnection();
		TableAgency ta=new TableAgency(db);
		result_label.setText(ta.searchAgency(number_field.getText(), name_field.getText()));
		
	}
}
