package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import utility.KeyValuePair;
import view.GenericDialogView;
import view.GenericWarning;
import view.SQLWarning;
import view.SalesManView;
import view.SearchContractResultView;
import database.DAOTableContract;
import database.DAOTableTypeContract;
import database.DatabaseConnectionException;
import database.DbAccess;
import entity.Contract;
import entity.TypeContract;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SearchContractController implements Initializable 
{
	@FXML private AnchorPane rootPane;
	@FXML private TextField nOrder_field;
	@FXML private TextField client_field;
	@FXML private TextField targa_field;
	@FXML private ChoiceBox<String> type_chbox;
	@FXML private DatePicker data_field;
	
	ArrayList<Contract> result = new ArrayList<Contract>();
	String nAgency = SalesManView.session.filiale.getNumber();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		//setto i choicebox
		DbAccess db = new DbAccess();
        
        try 
        {db.initConnection();} 
        catch (DatabaseConnectionException e) 
        {new SQLWarning();}
        
        DAOTableTypeContract ttc = null;
		try {
			ttc = new DAOTableTypeContract(db);
		} catch (DatabaseConnectionException e1) {
			new SQLWarning();
		}
        ArrayList<TypeContract> typeContract = new ArrayList<TypeContract>();
        
        try 
        {
        	typeContract = ttc.getAll();
        } 
        catch (SQLException e) 
        {new SQLWarning();}
        ArrayList<String> typeContractString = new ArrayList<String>();
        int size = typeContract.size();
        for(int i=0;i<size;i++)
        {
        	typeContractString.add(typeContract.get(i).toString());
        }
        typeContractString.add("");
        ObservableList<String> choiceboxlst = FXCollections.observableArrayList(typeContractString);
        type_chbox.setItems(choiceboxlst);
        type_chbox.getSelectionModel().selectLast();
	}
	
	@FXML protected void onSubmitAction(ActionEvent event) throws Exception
	{	
		search();
	}
	
	public void search() throws Exception
	{
		
		DbAccess db = new DbAccess();
		db.initConnection();
		DAOTableContract tc = new DAOTableContract(db);
	
		ArrayList<KeyValuePair<String,?>> searchParameters = new ArrayList<KeyValuePair<String,?>>();
		searchParameters = getParameters();
		searchParameters.add(new KeyValuePair<String, String>("id_agenzia", nAgency));
		if(searchParameters.size()>1)
		{
			result = tc.dynamicSearch(searchParameters);
			if(result.size()>0)
			{
				SearchContractResultView.getInstance().setSearchResult(result);
				SearchContractResultView.getInstance().start(new Stage());
			}
			else
			{new GenericDialogView("Contratti non trovati", "Non sono presenti contratti corrispondenti nel database.").start();}
		}
		else
		{new GenericWarning("Attenzione","Deve essere riempito almeno un campo.").start();}
		
	}
	
	public ArrayList<KeyValuePair<String,?>> getParameters()
	{
		ArrayList<KeyValuePair<String,?>> params = new ArrayList<KeyValuePair<String,?>>();
		if(!nOrder_field.getText().equals(""))
			params.add(new KeyValuePair<String, Object>("numero_ordine",nOrder_field.getText()));
		if(!client_field.getText().equals(""))
			params.add(new KeyValuePair<String, Object>("id_cliente",client_field.getText()));
		if(!targa_field.getText().equals(""))
			params.add(new KeyValuePair<String, Object>("targa",targa_field.getText()));
		if(!type_chbox.getValue().equals(""))
			params.add(new KeyValuePair<String, Object>("tipo",TypeContract.getIdFromString(type_chbox.getValue())));
		if(!(data_field.getValue()==null))
			params.add(new KeyValuePair<String, Object>("data_inizio",data_field.getValue().toString()));
		return params;
	}
}
