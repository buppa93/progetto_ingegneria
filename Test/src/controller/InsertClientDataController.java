package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import view.SalesManView;
import view.UnregisteredClientWarning;
import database.DatabaseConnectionException;
import database.DbAccess;
import database.TableClients;
import entity.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class InsertClientDataController implements Initializable
{
	@FXML private AnchorPane rootPane;
	@FXML private TextField name_field;
	@FXML private TextField surname_field;
	@FXML private TextField phone_field;
	@FXML private Button cancel_bttn;
	@FXML private Button back_bttn;
	@FXML private Button forward_bttn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO fare in modo che nessun campo sia vuoto
		
	}
	
	@FXML protected void onForwardAction(ActionEvent event) throws IOException, DatabaseConnectionException
	{
		DbAccess db = new DbAccess();
		db.initConnection();
		TableClients tc = new TableClients(db);
		Client c = tc.searchClient(name_field.getText(), surname_field.getText(), phone_field.getText());
		if(c != null)
		{
			//TODO passare dati utente
			((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("NoleggioView.fxml")));
		}
		else
		{
			UnregisteredClientWarning alert = new UnregisteredClientWarning();
		}
	}
	
	@FXML protected void onBackAction(ActionEvent event) throws IOException
	{
		((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("FXMLAlertRegister.fxml")));
	}
	
	@FXML protected void onCancelAction(ActionEvent event) throws IOException
	{
		((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("NothingView.fxml")));
	}
	

}
