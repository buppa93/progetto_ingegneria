package controller;


import java.net.URL;
import java.util.ResourceBundle;

import view.SummaryRentalView;
import view.SalesManView;
import database.DbAccess;
import database.TableContract;
import entity.Contract;
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

public class RemoveContractController implements Initializable
{
	@FXML private AnchorPane rootPane;
	@FXML private TextField phoneClient_field;
	@FXML private TextField targa_field;
	@FXML private Button submit_bttn;
	
	@FXML protected void onSubmitAction(ActionEvent event) throws Exception
	{
		String phone, targa;
		
		phone = phoneClient_field.getText();
		targa = targa_field.getText();
		
		DbAccess db = new DbAccess();
		db.initConnection();
		TableContract tc = new TableContract(db);
		
		Contract contratto = tc.searchContract(phone, targa);
		
		SummaryRentalView.getInstance().setContract(contratto);
		//TODO se il contratto non viene trovato, mostrare messagio errore
		((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("SummaryRentalView.fxml")));
	}
	
	@FXML protected void onCancelAction(ActionEvent event) {}

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		submit_bttn.setDisable(true);
		
		//TODO risolvere perche' non funziona correttamente
		phoneClient_field.textProperty().addListener(new ChangeListener<String>()
        		{

					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) 
					{
						// TODO Auto-generated method stub
						if(newValue.equals(""))
							submit_bttn.setDisable(true);
						else
							submit_bttn.setDisable(false);
					}
        	
        		});
		
		targa_field.textProperty().addListener(new ChangeListener<String>()
        		{

					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) 
					{
						// TODO Auto-generated method stub
						if(newValue.equals(""))
							submit_bttn.setDisable(true);
						else
							submit_bttn.setDisable(false);
					}
        	
        		});
	}
}
