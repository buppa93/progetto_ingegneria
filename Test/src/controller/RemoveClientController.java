package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import view.DeleteClientWarning;
import view.NotFoundClientWarning;
import view.SalesManView;
import database.DatabaseConnectionException;
import database.DbAccess;
import database.TableClients;
import entity.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class RemoveClientController implements Initializable
{
	@FXML private AnchorPane rootPane;
	@FXML private TextField  phone_field;
	@FXML private Button submit_bttn;
	@FXML private Button cancel_bttn;
	
	@FXML protected void handleSubmitAction(ActionEvent event) throws DatabaseConnectionException
	{
		String phone = phone_field.getText();
		DbAccess db = new DbAccess();
		db.initConnection();
		TableClients tc = new TableClients(db);
		Client c = tc.searchClientByPhone(phone);
		if(c == null)
		{
			new NotFoundClientWarning();
		}
		else
		{
			DeleteClientWarning.getInstance().setClient(c);
			DeleteClientWarning.getInstance().show();
		}
		
	}
	
	@FXML protected void handleCancelAction(ActionEvent event) throws IOException
	{
		((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("NothingView.fxml")));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		 phone_field.textProperty().addListener(new ChangeListener<String>()
	        		{
						@Override
						public void changed(
								ObservableValue<? extends String> observable,
								String oldValue, String newValue) 
						{
							if(newValue.equals(""))
								submit_bttn.setDisable(true);
						}
	        	
	        		});
		
	}

}
