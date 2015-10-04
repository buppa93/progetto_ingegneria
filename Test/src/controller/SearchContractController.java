package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import utility.KeyValuePair;
import view.SalesManView;
import view.SearchContractResultView;
import database.DatabaseConnectionException;
import database.DbAccess;
import database.TableContract;
import database.TableTypeContract;
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

//TODO da testare
public class SearchContractController implements Initializable 
{
	@FXML private AnchorPane rootPane;
	@FXML private TextField nOrder_field;
	@FXML private TextField client_field;
	@FXML private TextField targa_field;
	@FXML private ChoiceBox<String> type_chbox;
	@FXML private DatePicker data_field;
	
	public static final int nof = 1;  //nOrder_field value
	public static final int cf = 2;   //client_field value
	public static final int tf = 4;   //targa_field value
	public static final int tcb = 8; //type_chbox value
	public static final int df = 16;  //data_field value
	
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
        {e.printStackTrace();}
        
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
		searchParameters.add(new KeyValuePair<String, String>("id_agenzia", nAgency));
		
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
				//cerca per tipo
				String type = type_chbox.getValue();
				type = TypeContract.getIdFromString(type);
				searchParameters.add(new KeyValuePair<String, String>("tipo", type));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 9:
				//cerca per numero ordine + tipo contratto
				searchParameters.add(new KeyValuePair<String, String>("numero_ordine", nOrder_field.getText()));
				type = type_chbox.getValue();
				type = TypeContract.getIdFromString(type);
				searchParameters.add(new KeyValuePair<String, String>("tipo", type));
				result = tc.dynamicSearch(searchParameters);
				break;
				
			case 10:
				//cerca per codice cliente + tipo contratto
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				type = type_chbox.getValue();
				type = TypeContract.getIdFromString(type);
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 11:
				//cerca per numero ordine + codice cliente + tipo contratto
				searchParameters.add(new KeyValuePair<String, String>("numero_ordine", nOrder_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				type = type_chbox.getValue();
				type = TypeContract.getIdFromString(type);
				result = tc.dynamicSearch(searchParameters);
				break;
				
			case 12:
				//cerca per targa + tipo contratto
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				type = type_chbox.getValue();
				type = TypeContract.getIdFromString(type);
				result = tc.dynamicSearch(searchParameters);
				break;
				
			case 14:
				//cerca per codice cliente + targa + tipo contratto
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				type = type_chbox.getValue();
				type = TypeContract.getIdFromString(type);
				result = tc.dynamicSearch(searchParameters);
				break;
				
			case 15:
				//cerca per numero ordine + codice cliente + targa + tipo contratto
				searchParameters.add(new KeyValuePair<String, String>("numero_ordine", nOrder_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				type = type_chbox.getValue();
				type = TypeContract.getIdFromString(type);
				result = tc.dynamicSearch(searchParameters);
				break;
				
			case 16:
				//cerca per data
				searchParameters.add(new KeyValuePair<String, String>("data_inizio", data_field.getValue().toString()));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 17:
				//cerca per numero ordine + data
				searchParameters.add(new KeyValuePair<String, String>("numero_ordine", nOrder_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("data_inizio", data_field.getValue().toString()));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 18:
				//cerca per codice cliente + data
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("data_inizio", data_field.getValue().toString()));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 19:
				//cerca per numero ordine + codice cliente + data
				searchParameters.add(new KeyValuePair<String, String>("numero_ordine", nOrder_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("data_inizio", data_field.getValue().toString()));
				result = tc.dynamicSearch(searchParameters);
				break;
				
			case 20:
				//cerca per targa + data
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("data_inizio", data_field.getValue().toString()));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 22:
				//cerca per codice cliente + targa + data
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("data_inizio", data_field.getValue().toString()));
				result = tc.dynamicSearch(searchParameters);
				break;
				
			case 23:
				//cerca per numero ordine + codice cliente + targa + data
				searchParameters.add(new KeyValuePair<String, String>("numero_ordine", nOrder_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("id_cliente", client_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("targa", targa_field.getText()));
				searchParameters.add(new KeyValuePair<String, String>("data_inizio", data_field.getValue().toString()));
				result = tc.dynamicSearch(searchParameters);
				break;
			
			case 24:
				//cerca per tipo + dataì
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
				&&(type_chbox.getValue().equals(""))&&(data_field.getValue() == null))
			tot = nof; // 1
		
		//2
		if((nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(type_chbox.getValue().equals(""))&&(data_field.getValue() == null))
			tot = cf; // 2
		
		//3
		if((nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(type_chbox.getValue().equals(""))&&(data_field.getValue() == null))
			tot = tf; // 4
		
		//4
		if((nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(!type_chbox.getValue().equals(""))&&(data_field.getValue() == null))
			tot = tcb; // 8
		
		//5
		if((nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(type_chbox.getValue().equals(""))&&(data_field.getValue() != null))
			tot = df; // 16
		
		//6
		if((!nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(type_chbox.getValue().equals(""))&&(data_field.getValue() == null))
			tot = nof + cf; // 3
		
		//7
		if((!nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(type_chbox.getValue().equals(""))&&(data_field.getValue() == null))
			tot = nof + tf; // 5
		
		//8
		if((!nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(!type_chbox.getValue().equals(""))&&(data_field.getValue() == null))
			tot = nof + tcb; // 9
		
		//9
		if((!nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(type_chbox.getValue().equals(""))&&(data_field.getValue() == null))
			tot = nof + df; // 17
		
		//10
		if((nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(type_chbox.getValue().equals(""))&&(data_field.getValue() == null))
			tot = cf + tf; // 6
		
		//11
		if((nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(!type_chbox.getValue().equals(""))&&(data_field.getValue() == null))
			tot = cf + tcb; // 10
		
		//12
		if((nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(type_chbox.getValue().equals(""))&&(data_field.getValue() != null))
			tot = cf + df; // 18
	
		//13
		if((nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(!type_chbox.getValue().equals(""))&&(data_field.getValue() == null))
			tot = tf + tcb; // 12
		
		//14
		if((nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(type_chbox.getValue().equals(""))&&(data_field.getValue() != null))
			tot = tf + df; // 20
		
		//15
		if((nOrder_field.getText().equals(""))&&(client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(!type_chbox.getValue().equals(""))&&(data_field.getValue() != null))
			tot = tcb + df; // 24
		
		//16
		if((!nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(type_chbox.getValue().equals(""))&&(data_field.getValue() == null))
			tot = nof + cf + tf; // 7
		
		//17
		if((!nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(!type_chbox.getValue().equals(""))&&(data_field.getValue() == null))
			tot = nof + cf + tcb; // 11
		
		//18
		if((!nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(targa_field.getText().equals(""))
				&&(type_chbox.getValue().equals(""))&&(data_field.getValue() != null))
			tot = nof + cf + df; // 19
		
		//19
		if((nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(!type_chbox.getValue().equals(""))&&(data_field.getValue() == null))
			tot = cf + tf + tcb; // 14
		
		//20
		if((nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(type_chbox.getValue().equals(""))&&(data_field.getValue() != null))
			tot = cf + tf + df; // 22
		
		//21
		if((!nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(!type_chbox.getValue().equals(""))&&(data_field.getValue() == null))
			tot = nof + cf + tf + tcb; // 15
		
		//22
		if((!nOrder_field.getText().equals(""))&&(!client_field.getText().equals(""))&&(!targa_field.getText().equals(""))
				&&(type_chbox.getValue().equals(""))&&(data_field.getValue() != null))
			tot = nof + cf + tf + df; // 23
		
		System.out.println(tot);
		return tot;
	}
}
