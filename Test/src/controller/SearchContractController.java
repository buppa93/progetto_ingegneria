package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import utility.KeyValuePair;
import view.SearchContractResultView;
import database.DatabaseConnectionException;
import database.DbAccess;
import database.TableAgency;
import database.TableContract;
import database.TableTypeContract;
import entity.Agency;
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
	@FXML private ChoiceBox<String> agency_chbox;
	@FXML private ChoiceBox<String> type_chbox;
	@FXML private DatePicker data_field;
	
	public static int nof = 1;  //nOrder_field value
	public static int cf = 2;   //client_field value
	public static int tf = 4;   //targa_field value
	public static int acb = 8;  //agency_chbox value
	public static int tcb = 16; //type_chbox value
	public static int df = 32;  //data_field value
	
	ArrayList<Contract> result = new ArrayList<Contract>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		//setto i choicebox
		DbAccess db = new DbAccess();
        
        try 
        {db.initConnection();} 
        catch (DatabaseConnectionException e) 
        {e.printStackTrace();}
        
        TableAgency tableagency = new TableAgency(db);
        List<String> agencies = new ArrayList<String>();
        
        try 
        {
        	agencies = tableagency.getAllAgency();
        	agencies.add("");
        } 
        catch (SQLException e) 
        {e.printStackTrace();}
        
        ObservableList<String> agenzie = FXCollections.observableArrayList(agencies);
        agency_chbox.setItems(agenzie);
        agency_chbox.getSelectionModel().selectLast();
        
        
        TableTypeContract ttc = new TableTypeContract(db);
        List<String> typeContract = new ArrayList<String>();
        
        try 
        {
        	typeContract = ttc.getAll();
        	typeContract.add("");
        } 
        catch (SQLException e) 
        {e.printStackTrace();}
        
        ObservableList<String> choiceboxlst = FXCollections.observableArrayList(typeContract);
        type_chbox.setItems(choiceboxlst);
        type_chbox.getSelectionModel().selectLast();
	}
	
	@FXML protected void onSubmitAction(ActionEvent event) throws Exception
	{	
		System.out.println("nOrder_field -> "+nOrder_field.getText());
		System.out.println("client_field -> "+client_field.getText());
		System.out.println("targa_field -> "+targa_field.getText());
		System.out.println("agency_chbox -> "+agency_chbox.getValue());
		System.out.println("type_chbox -> "+type_chbox.getValue());
		System.out.println("data_field -> "+data_field.getValue());
		search();
		SearchContractResultView.getInstance().setSearchResult(result);
		SearchContractResultView.getInstance().start(new Stage());
	}
	
	public void search() throws DatabaseConnectionException, SQLException
	{
		int tot = calculate();
		
		DbAccess db = new DbAccess();
		db.initConnection();
		TableContract tc = new TableContract(db);
	
		ArrayList<KeyValuePair<String,String>> searchParameters = new ArrayList<KeyValuePair<String,String>>();
		
		switch(tot)
		{
			case 1:
				//cerca per numero ordine
				searchParameters.add(new KeyValuePair<String, String>("numero_ordine", nOrder_field.getText()));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 2:
				//cerca per cliente
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 3:
				//cerca per numero ordine + codice cliente
				searchParameters.add(new KeyValuePair<String, String>("numero_ordine", nOrder_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				result = tc.dynamicSearch(searchParameters);
				break;
				
			case 4:
				//cerca per targa;
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 5:
				//cerca per numero ordine + targa
				searchParameters.add(new KeyValuePair<String, String>("numero_ordine", nOrder_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 6:
				//cerca per codice cliente + targa
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				result = tc.dynamicSearch(searchParameters);
				break;
				
			case 7:
				//cerca per numero ordine + codice cliente + targa
				searchParameters.add(new KeyValuePair<String, String>("numero_ordine", nOrder_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 8:
				//cerca per agenzia
				String id_agency = agency_chbox.getValue();
				id_agency = Agency.getIdFromString(id_agency);
				searchParameters.add(new KeyValuePair<String, String>("id_agenzia", id_agency));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 10:
				//cerca per codice cliente + agenzia
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				id_agency = agency_chbox.getValue();
				id_agency = Agency.getIdFromString(id_agency);
				searchParameters.add(new KeyValuePair<String, String>("id_agenzia", id_agency));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 11:
				//cerca per numero ordine + codice cliente + agenzia
				searchParameters.add(new KeyValuePair<String, String>("numero_ordine", nOrder_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				id_agency = agency_chbox.getValue();
				id_agency = Agency.getIdFromString(id_agency);
				searchParameters.add(new KeyValuePair<String, String>("id_agenzia", id_agency));
				result = tc.dynamicSearch(searchParameters);
				break;
				
			case 12:
				//cerca per targa + agenzia
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				id_agency = agency_chbox.getValue();
				id_agency = Agency.getIdFromString(id_agency);
				searchParameters.add(new KeyValuePair<String, String>("id_agenzia", id_agency));
				result = tc.dynamicSearch(searchParameters);
				break;
				
			case 14:
				//cerca per codice cliente + targa + agenzia
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				id_agency = agency_chbox.getValue();
				id_agency = Agency.getIdFromString(id_agency);
				searchParameters.add(new KeyValuePair<String, String>("id_agenzia", id_agency));
				result = tc.dynamicSearch(searchParameters);
				break;
				
			case 15:
				//cerca per numero ordine + codice cliente + targa + agenzia
				searchParameters.add(new KeyValuePair<String, String>("numero_ordine", nOrder_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				id_agency = agency_chbox.getValue();
				id_agency = Agency.getIdFromString(id_agency);
				searchParameters.add(new KeyValuePair<String, String>("id_agenzia", id_agency));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 16:
				//cerca per tipo
				String type = type_chbox.getValue();
				type = TypeContract.getIdFromString(type);
				searchParameters.add(new KeyValuePair<String, String>("tipo", type));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 17:
				//cerca per numero ordine + tipo
				searchParameters.add(new KeyValuePair<String, String>("numero_ordine", nOrder_field.getText()));
				type = type_chbox.getValue();
				type = TypeContract.getIdFromString(type);
				searchParameters.add(new KeyValuePair<String, String>("tipo", type));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 18:
				//cerca per codice cliente + tipo
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				type = type_chbox.getValue();
				type = TypeContract.getIdFromString(type);
				searchParameters.add(new KeyValuePair<String, String>("tipo", type));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 19:
				//cerca per numero ordine + codice cliente + tipo
				searchParameters.add(new KeyValuePair<String, String>("numero_ordine", nOrder_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				type = type_chbox.getValue();
				type = TypeContract.getIdFromString(type);
				searchParameters.add(new KeyValuePair<String, String>("tipo", type));
				result = tc.dynamicSearch(searchParameters);
				break;
				
			case 20:
				//cerca per targa + tipo
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				type = type_chbox.getValue();
				type = TypeContract.getIdFromString(type);
				searchParameters.add(new KeyValuePair<String, String>("tipo", type));
				result = tc.dynamicSearch(searchParameters);
				break;
				
			case 21:
				//cerca per tipo + data
				type = type_chbox.getValue();
				type = TypeContract.getIdFromString(type);
				searchParameters.add(new KeyValuePair<String, String>("tipo", type));
				searchParameters.add(new KeyValuePair<String, String>("data_inizio", data_field.getValue().toString()));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 22:
				//cerca per codice cliente + targa + tipo
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				type = type_chbox.getValue();
				type = TypeContract.getIdFromString(type);
				searchParameters.add(new KeyValuePair<String, String>("tipo", type));
				result = tc.dynamicSearch(searchParameters);
				break;
				
			case 23:
				//cerca per numero ordine + codice cliente + targa + tipo
				searchParameters.add(new KeyValuePair<String, String>("numero_ordine", nOrder_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				type = type_chbox.getValue();
				type = TypeContract.getIdFromString(type);
				searchParameters.add(new KeyValuePair<String, String>("tipo", type));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 24:
				//cerca per agenzia + tipo
				id_agency = agency_chbox.getValue();
				id_agency = Agency.getIdFromString(id_agency);
				searchParameters.add(new KeyValuePair<String, String>("id_agenzia", id_agency));
				type = type_chbox.getValue();
				type = TypeContract.getIdFromString(type);
				searchParameters.add(new KeyValuePair<String, String>("tipo", type));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 28:
				//cerca per targa + agenzia + tipo
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				id_agency = agency_chbox.getValue();
				id_agency = Agency.getIdFromString(id_agency);
				searchParameters.add(new KeyValuePair<String, String>("id_agenzia", id_agency));
				type = type_chbox.getValue();
				type = TypeContract.getIdFromString(type);
				searchParameters.add(new KeyValuePair<String, String>("tipo", type));
				result = tc.dynamicSearch(searchParameters);
				break;
				
			case 30:
				//cerca per codice cliente + targa + agenzia + tipo
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				id_agency = agency_chbox.getValue();
				id_agency = Agency.getIdFromString(id_agency);
				searchParameters.add(new KeyValuePair<String, String>("id_agenzia", id_agency));
				type = type_chbox.getValue();
				type = TypeContract.getIdFromString(type);
				searchParameters.add(new KeyValuePair<String, String>("tipo", type));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 31:
				//cerca per numero ordine + codice cliente + targa + agenzia + tipo
				searchParameters.add(new KeyValuePair<String, String>("numero_ordine", nOrder_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				id_agency = agency_chbox.getValue();
				id_agency = Agency.getIdFromString(id_agency);
				searchParameters.add(new KeyValuePair<String, String>("id_agenzia", id_agency));
				type = type_chbox.getValue();
				type = TypeContract.getIdFromString(type);
				searchParameters.add(new KeyValuePair<String, String>("tipo", type));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 32:
				//cerca per data
				searchParameters.add(new KeyValuePair<String, String>("data_inizio", data_field.getValue().toString()));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 33:
				//cerca per numero ordine + data
				searchParameters.add(new KeyValuePair<String, String>("numero_ordine", nOrder_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("data_inizio", data_field.getValue().toString()));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 34:
				//cerca per codice cliente + data
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("data_inizio", data_field.getValue().toString()));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 35:
				//cerca per numero ordine + codice cliente + data
				searchParameters.add(new KeyValuePair<String, String>("numero_ordine", nOrder_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("data_inizio", data_field.getValue().toString()));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 38:
				//cerca per targa + data
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("data_inizio", data_field.getValue().toString()));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 39:
				//cerca per numero ordine + codice cliente + targa + data
				searchParameters.add(new KeyValuePair<String, String>("numero_ordine", nOrder_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("data_inizio", data_field.getValue().toString()));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 40:
				//cerca per agenzia + data
				id_agency = agency_chbox.getValue();
				id_agency = Agency.getIdFromString(id_agency);
				searchParameters.add(new KeyValuePair<String, String>("id_agenzia", id_agency));
				searchParameters.add(new KeyValuePair<String, String>("data_inizio", data_field.getValue().toString()));
				result = tc.dynamicSearch(searchParameters);
				break;
				
			case 44:
				//cerca per targa + agenzia + data
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				id_agency = agency_chbox.getValue();
				id_agency = Agency.getIdFromString(id_agency);
				searchParameters.add(new KeyValuePair<String, String>("id_agenzia", id_agency));
				searchParameters.add(new KeyValuePair<String, String>("data_inizio", data_field.getValue().toString()));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 46:
				//cerca per codice cliente + targa + agenzia + data
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				id_agency = agency_chbox.getValue();
				id_agency = Agency.getIdFromString(id_agency);
				searchParameters.add(new KeyValuePair<String, String>("id_agenzia", id_agency));
				searchParameters.add(new KeyValuePair<String, String>("data_inizio", data_field.getValue().toString()));
				result = tc.dynamicSearch(searchParameters);
				break;
				
			case 47:
				//cerca per numero ordine + codice cliente + targa + agenzia + data
				searchParameters.add(new KeyValuePair<String, String>("numero_ordine", nOrder_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				id_agency = agency_chbox.getValue();
				id_agency = Agency.getIdFromString(id_agency);
				searchParameters.add(new KeyValuePair<String, String>("id_agenzia", id_agency));
				searchParameters.add(new KeyValuePair<String, String>("data_inizio", data_field.getValue().toString()));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 48:
				//cerca per tipo + data
				type = type_chbox.getValue();
				type = TypeContract.getIdFromString(type);
				searchParameters.add(new KeyValuePair<String, String>("tipo", type));
				searchParameters.add(new KeyValuePair<String, String>("data_inizio", data_field.getValue().toString()));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 56:
				//cerca per agenzia + tipo + data
				id_agency = agency_chbox.getValue();
				id_agency = Agency.getIdFromString(id_agency);
				searchParameters.add(new KeyValuePair<String, String>("id_agenzia", id_agency));
				type = type_chbox.getValue();
				type = TypeContract.getIdFromString(type);
				searchParameters.add(new KeyValuePair<String, String>("tipo", type));
				searchParameters.add(new KeyValuePair<String, String>("data_inizio", data_field.getValue().toString()));
				result = tc.dynamicSearch(searchParameters);
				break;
				
			case 60:
				//cerca per targa + agenzia + tipo + data
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				id_agency = agency_chbox.getValue();
				id_agency = Agency.getIdFromString(id_agency);
				searchParameters.add(new KeyValuePair<String, String>("id_agenzia", id_agency));
				type = type_chbox.getValue();
				type = TypeContract.getIdFromString(type);
				searchParameters.add(new KeyValuePair<String, String>("tipo", type));
				searchParameters.add(new KeyValuePair<String, String>("data_inizio", data_field.getValue().toString()));
				result = tc.dynamicSearch(searchParameters);
			
			case 62:
				//cerca per codice cliente + targa + agenzia + tipo + data
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				id_agency = agency_chbox.getValue();
				id_agency = Agency.getIdFromString(id_agency);
				searchParameters.add(new KeyValuePair<String, String>("id_agenzia", id_agency));
				type = type_chbox.getValue();
				type = TypeContract.getIdFromString(type);
				searchParameters.add(new KeyValuePair<String, String>("tipo", type));
				searchParameters.add(new KeyValuePair<String, String>("data_inizio", data_field.getValue().toString()));
				result = tc.dynamicSearch(searchParameters);
				break;
				
			case 63:
				//cerca per numero ordine + codice cliente + targa + agenzia + tipo + data
				searchParameters.add(new KeyValuePair<String, String>("numero_ordine", nOrder_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				id_agency = agency_chbox.getValue();
				id_agency = Agency.getIdFromString(id_agency);
				searchParameters.add(new KeyValuePair<String, String>("id_agenzia", id_agency));
				type = type_chbox.getValue();
				type = TypeContract.getIdFromString(type);
				searchParameters.add(new KeyValuePair<String, String>("tipo", type));
				searchParameters.add(new KeyValuePair<String, String>("data_inizio", data_field.getValue().toString()));
				result = tc.dynamicSearch(searchParameters);
				break;
		}
		
	}
	
	public int calculate()
	{
		int tot = 0;
		
		//1
		if((!nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = nof; // 1
		
		//2
		if((nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = cf; // 2
		
		//3
		if((nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = tf; // 4
		
		//4
		if((nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(!agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field == null))
		{
			//TODO vedere perche' non entra qui!!!!!!!
			System.out.println("sono qui 2");
			tot = acb; // 8
		}
		
		//5
		if((nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(agency_chbox.getValue().equals(""))&&(!type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = tcb; // 16
		
		//6
		if((nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field.getValue() != null))
			tot = df; // 32
		
		//7
		if((!nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = nof + cf; // 3
		
		//8
		if((!nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = nof + tf; // 5
		
		//9
		if((!nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(!agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = nof + acb;  // 9
		
		//10
		if((!nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(agency_chbox.getValue().equals(""))&&(!type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = nof + tcb; // 17
		
		//11
		if((!nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = nof + df; // 33
		
		//12
		if((nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = cf + tf; // 6
		
		//13
		if((nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(!agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = cf + acb; // 10
		
		//14
		if((nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(agency_chbox.getValue().equals(""))&&(!type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = cf + tcb; // 18
		
		//15
		if((nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field.getValue() != null))
			tot = cf + df; // 34
		
		//16
		if((nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(!agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = tf + acb; // 12
		
		//17
		if((nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(agency_chbox.getValue().equals(""))&&(!type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = tf + tcb; // 20
		
		//18
		if((nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field.getValue() != null))
			tot = tf + df; // 38
		
		//19
		if((nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(!agency_chbox.getValue().equals(""))&&(!type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = acb + tcb; // 24
		
		//20
		if((nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(!agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field.getValue() != null))
			tot = acb + df; // 40
		
		//21
		if((nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(agency_chbox.getValue().equals(""))&&(!type_chbox.getValue().equals(""))
				&&(data_field.getValue() != null))
			tot = tcb + df; // 48
		
		//22
		if((!nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = nof + cf + tf; // 7
		
		//23
		if((!nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(!agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = nof + cf + acb; // 11
		
		//24
		if((!nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(agency_chbox.getValue().equals(""))&&(!type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = nof + cf + tcb; // 19
		
		//25
		if((!nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field.getValue() != null))
			tot = nof + cf + df; // 35
		
		//26
		if((nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(!agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = cf + tf + acb; // 14
		
		//27
		if((nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(agency_chbox.getValue().equals(""))&&(!type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = cf + tf + tcb; // 22
		
		//28
		if((nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field.getValue() != null))
			tot = cf + tf + df; // 38
		
		//29
		if((nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(!agency_chbox.getValue().equals(""))&&(!type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = tf + acb + tcb; // 28
		
		//30
		if((nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(!agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field.getValue() != null))
			tot = tf + acb + df; // 44
		
		//31
		if((nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(!agency_chbox.getValue().equals(""))&&(!type_chbox.getValue().equals(""))
				&&(data_field.getValue() != null))
			tot = acb + tcb + df; // 56
		
		//32
		if((!nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(!agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = nof + cf + tf + acb; // 15
		
		//33
		if((!nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(agency_chbox.getValue().equals(""))&&(!type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = nof + cf + tf + tcb; // 23
		
		//34
		if((!nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field.getValue() != null))
			tot = nof + cf + tf + df; // 39
		
		//35
		if((nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(!agency_chbox.getValue().equals(""))&&(!type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = cf + tf + acb + tcb; // 30
		
		//36
		if((nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(!agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field.getValue() != null))
			tot = cf + tf + acb + df; // 46
		
		//37
		if((nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(!agency_chbox.getValue().equals(""))&&(!type_chbox.getValue().equals(""))
				&&(data_field.getValue() != null))
			tot = tf + acb + tcb + df; // 60
		
		//38
		if((!nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(!agency_chbox.getValue().equals(""))&&(!type_chbox.getValue().equals(""))
				&&(data_field.getValue() == null))
			tot = nof + cf + tf + acb + tcb; // 31
		
		//39
		if((!nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(!agency_chbox.getValue().equals(""))&&(type_chbox.getValue().equals(""))
				&&(data_field.getValue() != null))
			tot = nof + cf + tf + acb + df; // 47
		
		//40
		if((nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(!agency_chbox.getValue().equals(""))&&(!type_chbox.getValue().equals(""))
				&&(data_field.getValue() != null))
			tot = cf + tf + acb + tcb + df; // 62
		
		//41
		if((!nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(!agency_chbox.getValue().equals(""))&&(!type_chbox.getValue().equals(""))
				&&(data_field.getValue() != null))
			tot = nof + cf + tf + acb + tcb + df; // 63
		
		System.out.println(tot);
		return tot;
	}
}
