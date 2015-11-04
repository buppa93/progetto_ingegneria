package controller;

import java.io.IOException;
import java.sql.SQLException;

import view.AdminView;
import database.DAOTableAgency;
import database.DatabaseConnectionException;
import database.DbAccess;
import entity.Agency;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class AdminNewAgencyController {
@FXML	TextField number_field;
@FXML	TextField name_field;
@FXML	TextField address_field;
@FXML	Button cancel_bttn;
@FXML	Button insert_bttn;
@FXML	AnchorPane rootPane;
	
	@FXML protected void OnInsertAction(ActionEvent event) throws DatabaseConnectionException, SQLException
	{	
		Agency a=new Agency(number_field.getText(), name_field.getText(), address_field.getText());
		DbAccess db=new DbAccess();
		db.initConnection();
		DAOTableAgency ta= new DAOTableAgency(db);
		ta.insert(a);
		
	}
	@FXML protected void OnCancelAction(ActionEvent event) throws IOException
	{
		((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(AdminView.class.getResource("FXMLAdminView.fxml")));
	}
}
