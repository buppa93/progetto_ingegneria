package controller;

import java.io.IOException;
import java.util.ArrayList;

import database.DAOTableAgency;
import database.DbAccess;
import entity.Agency;
import utility.KeyValuePair;
import view.AdminView;
import view.GenericWarning;
import view.SearchAgencyResultView;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AdminSearchAgencyController {
	@FXML private AnchorPane rootPane;
	@FXML private TextField number_field;
	@FXML private TextField name_field;
	@FXML private Label result_label;
	
	@FXML protected void onCancelAction(ActionEvent event) throws IOException
	{
		((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(AdminView.class.getResource("FXMLAdminView.fxml")));
	}
	
	@FXML protected void onSearchAction(Event event) throws Exception
	{
		DbAccess db = new DbAccess();
		db.initConnection();
		DAOTableAgency ta=new DAOTableAgency(db);
		//result_label.setText(ta.searchAgencyByNumberName(number_field.getText(), name_field.getText()));
		ArrayList<Agency> result= new ArrayList<Agency>();
		
		if(get_Data()!=null)
		{
			result = ta.dynamicSearch(get_Data());
			if(result.size()>0)
			{
				SearchAgencyResultView.getInstance().setSearchResult(result);
				SearchAgencyResultView.getInstance().start(new Stage());
			}
			else
			{new GenericWarning("Attenzione","Non sono state trovate corrispondenze.").start();}
		}
		else
		{new GenericWarning("Attenzione","Deve essere riempito almeno un campo.").start();}
		
	}
	
	public ArrayList<KeyValuePair<String,String>> get_Data()
	{
		ArrayList<KeyValuePair<String,String>> data = new ArrayList<KeyValuePair<String,String>>();
		if((number_field.getText().equals(""))&&(name_field.getText().equals("")))
			return null;
		else
		{
			if(!number_field.getText().equals(""))
				data.add(new KeyValuePair<String,String>("numero",number_field.getText()));
			if(!name_field.getText().equals(""))
				data.add(new KeyValuePair<String,String>("nome",name_field.getText()));
		}
		return data;
		
	}
}
