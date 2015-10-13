package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import database.DatabaseConnectionException;
import database.DbAccess;
import database.TableAuto;
import entity.Auto;
import entity.TypeSection;
import utility.KeyValuePair;
import view.GenericDialogView;
import view.SalesManView;
import view.SearchCarsResultView;
import view.SearchContractResultView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SearchAutoController implements Initializable
{
	@FXML private AnchorPane rootLayout;
	@FXML private TextField targa_field;
	@FXML private TextField model_field;
	@FXML private TextField brand_field;
	@FXML private TextField km_field;
	@FXML private ChoiceBox<String> type_chbox;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		ObservableList<String> fascialst = FXCollections.observableArrayList("Utilitaria","Berlina","");
		type_chbox.setItems(fascialst);
		type_chbox.getSelectionModel().selectLast();
	}
	
	@FXML protected void onSubmitAction(ActionEvent event) throws Exception
	{
		ArrayList<KeyValuePair<String,?>> parameters = getSearchParameters();
		DbAccess db = new DbAccess();
		db.initConnection();
		TableAuto ta = new TableAuto(db);
		ArrayList<Auto> cars = ta.dynamicSearch(parameters);
		if(cars.size()!=0)
		{
			SearchCarsResultView.getInstance().setSearchResult(cars);
			SearchCarsResultView.getInstance().start(new Stage());
		}
		else
		{
			new GenericDialogView("Auto non trovate", "Non sono presenti auto corrispondenti nel database.");
		}
	}
	
	@FXML protected void onCancelAction(ActionEvent event) throws IOException
	{
		((BorderPane) rootLayout.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("NothingView.fxml")));
	}
	
	public ArrayList<KeyValuePair<String,?>> getSearchParameters()
	{
		ArrayList<KeyValuePair<String,?>> searchParameters = new ArrayList<KeyValuePair<String,?>>();
		if(!targa_field.getText().equals(""))
			searchParameters.add(new KeyValuePair<String,String>("targa",targa_field.getText()));
		
		if(!model_field.getText().equals(""))
			searchParameters.add(new KeyValuePair<String,String>("modello",model_field.getText()));
		
		if(!brand_field.getText().equals(""))
			searchParameters.add(new KeyValuePair<String,String>("marca",brand_field.getText()));
		
		if(!km_field.getText().equals(""))
			searchParameters.add(new KeyValuePair<String,Integer>("km",Integer.parseInt(km_field.getText())));
		
		if(!type_chbox.getValue().equals(""))
		{
			char section = TypeSection.resolvType(type_chbox.getValue());
			searchParameters.add(new KeyValuePair<String,Character>("fascia",section));
		}
		
		searchParameters.add(new KeyValuePair<String,String>("id_agenzia",SalesManView.session.filiale.getNumber()));
		
		return searchParameters;
	}

}
