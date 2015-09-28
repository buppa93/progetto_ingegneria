package controller;

import java.io.IOException;
import java.util.ArrayList;

import view.SalesManView;
import view.SearchClientResultView;
import database.DatabaseConnectionException;
import database.DbAccess;
import database.DbString;
import database.TableClients;
import entity.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SearchClientCrontoller 
{
	@FXML private AnchorPane rootPane;
	@FXML private TextField name_field;
	@FXML private TextField surname_field;
	@FXML private TextField phone_field;
	@FXML private Button submit_bttn;
	@FXML private Button cancel_bttn;
	
	public static int nf = 1; //name_field value
	public static int sf = 2; //surname_field value
	public static int pf = 4; //phone_field value
	
	
	@FXML protected void onSubmitAction(ActionEvent event) throws Exception
	{	
		int tot = calculate();
		DbAccess db = new DbAccess();
		db.initConnection();
		TableClients tc = new TableClients(db);
		ArrayList<Client> result = new ArrayList<Client>();
		
		if(tot == 1)
			result = tc.searchByName(name_field.getText());
		
		if(tot == 2)
			result = tc.searchBySurname(surname_field.getText());
		
		if(tot == 4)
			result = tc.searchByPhone(phone_field.getText());
		
		if(tot == 3)
			result = tc.searchByNameAndSurname(name_field.getText(), surname_field.getText());
		
		if(tot == 5)
			result = tc.searchByNameAndPhone(name_field.getText(), phone_field.getText());
		
		if(tot == 6)
			result = tc.searchBySurnameAndPhone(surname_field.getText(), phone_field.getText());
		
		if(tot == 7)
			result = tc.searchByAllField(surname_field.getText(), surname_field.getText(), phone_field.getText());
		
		//TODO mostrare finestra con i clienti trovati
		SearchClientResultView.getInstance().setSearchResult(result);
		SearchClientResultView.getInstance().start(new Stage());
	}
	
	@FXML protected void onCancelAction(ActionEvent event) throws IOException
	{
		((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("NothingView.fxml")));
	}
	
	public int calculate()
	{
		int tot = 0;
		
		if((!name_field.getText().equals(""))&&(surname_field.getText().equals(""))&&
				(phone_field.getText().equals("")))
			tot = nf;
		if((name_field.getText().equals(""))&&(!surname_field.getText().equals(""))&&
				(phone_field.getText().equals("")))
			tot = sf;
		if((name_field.getText().equals(""))&&(surname_field.getText().equals(""))&&
				(!phone_field.getText().equals("")))
			tot = pf;
		if((!name_field.getText().equals(""))&&(!surname_field.getText().equals(""))&&
				(phone_field.getText().equals("")))
			tot = nf+sf;
		if((!name_field.getText().equals(""))&&(surname_field.getText().equals(""))&&
				(!phone_field.getText().equals("")))
			tot = nf+pf;
		if((name_field.getText().equals(""))&&(!surname_field.getText().equals(""))&&
				(!phone_field.getText().equals("")))
			tot = sf+pf;
		if((!name_field.getText().equals(""))&&(!surname_field.getText().equals(""))&&
				(!phone_field.getText().equals("")))
			tot = nf+sf+pf;
		
		return tot;
	}
}
