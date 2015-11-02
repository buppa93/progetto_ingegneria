package controller;

import java.io.IOException;
import java.util.ArrayList;

import utility.KeyValuePair;
import view.GenericWarning;
import view.SalesManView;
import view.SearchClientResultView;
import database.DAOTableClients;
import database.DbAccess;
import entity.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/*
 * TODO migliorare come la ricerca auto
 * TODO passare in DAO
 */
public class SearchClientCrontoller 
{
	@FXML private AnchorPane rootPane;
	@FXML private TextField name_field;
	@FXML private TextField surname_field;
	@FXML private TextField phone_field;
	@FXML private Button submit_bttn;
	@FXML private Button cancel_bttn;
	
	public static final int nf = 1; //name_field value
	public static final int sf = 2; //surname_field value
	public static final int pf = 4; //phone_field value
	
	
	@FXML protected void onSubmitAction(ActionEvent event) throws Exception
	{	
		DbAccess db = new DbAccess();
		db.initConnection();
		DAOTableClients tc = new DAOTableClients(db);
		ArrayList<Client> result = new ArrayList<Client>();
		
		if(getData()!=null)
		{
			result = tc.dynamicSearch(getData());
			if(result.size()>0)
			{
				SearchClientResultView.getInstance().setSearchResult(result);
				SearchClientResultView.getInstance().start(new Stage());
			}
			else
			{new GenericWarning("Attenzione","Non sono state trovate corrispondenze.").start();}
		}
		else
		{new GenericWarning("Attenzione","Deve essere riempito almeno un campo.").start();}
	}
	
	public ArrayList<KeyValuePair<String,String>> getData()
	{
		ArrayList<KeyValuePair<String,String>> data = new ArrayList<KeyValuePair<String,String>>();
		if((name_field.getText().equals(""))&&(surname_field.getText().equals(""))&&
				phone_field.getText().equals(""))
			return null;
		else
		{
			if(!name_field.getText().equals(""))
				data.add(new KeyValuePair<String,String>("nome",name_field.getText()));
			if(!surname_field.getText().equals(""))
				data.add(new KeyValuePair<String,String>("cognome",surname_field.getText()));
			if(!phone_field.getText().equals(""))
				data.add(new KeyValuePair<String,String>("telefono",phone_field.getText()));
		}
		return data;
		
	}
	
	@FXML protected void onCancelAction(ActionEvent event) throws IOException
	{
		((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("NothingView.fxml")));
	}
}
