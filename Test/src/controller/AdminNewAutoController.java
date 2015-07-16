package controller;

import java.io.IOException;

import view.AdminView;
import database.DatabaseConnectionException;
import database.DbAccess;
import database.TableAuto;
import entity.Auto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class AdminNewAutoController {
	@FXML AnchorPane rootPane;
	@FXML TextField targa_field;
	@FXML TextField modello_field;
	@FXML TextField marca_field;
	@FXML TextField kmpercorsi_field;
	@FXML TextField agenzia_field;
	
	@FXML protected void OnInsertAction(ActionEvent event) throws DatabaseConnectionException
	{
		Auto a= new Auto(targa_field.getText(), modello_field.getText(), marca_field.getText(), (Integer.parseInt(kmpercorsi_field.getText())), 1);
		DbAccess db=new DbAccess();
		db.initConnection();
		TableAuto taauto= new TableAuto(db);
		taauto.insertAuto(a);
	}	
	
	@FXML protected void OnCancelAction(ActionEvent event) throws IOException
	{
		((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(AdminView.class.getResource("FXMLAdminView.fxml")));
	}
	
}
