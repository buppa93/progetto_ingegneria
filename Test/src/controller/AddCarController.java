package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import database.DatabaseConnectionException;
import database.DbAccess;
import database.TableAuto;
import entity.TypeSection;
import utility.CarsAvailability;
import utility.KeyValuePair;
import view.GenericDialogView;
import view.GenericWarning;
import view.SQLWarning;
import view.SalesManView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class AddCarController implements Initializable
{
	@FXML private AnchorPane rootLayout;
	@FXML private TextField brand_field;
	@FXML private TextField model_field;
	@FXML private TextField targa_field;
	@FXML private TextField km_field;
	@FXML private ChoiceBox<String> fascia_chbox;
	@FXML private ChoiceBox<String> state_chbox;
	@FXML private Button submit_bttn;
	@FXML private Button cancel_bttn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		ObservableList<String> fascialst = FXCollections.observableArrayList("Utilitaria","Berlina","");
		fascia_chbox.setItems(fascialst);
		ObservableList<String> statelst = FXCollections.observableArrayList("disponibile","noleggio",
				"manutenzione_straordinaria","manutenzione_ordinaria","ND","");
		state_chbox.setItems(statelst);

		state_chbox.getSelectionModel().selectLast();
		fascia_chbox.getSelectionModel().selectLast();
	}
	
	@FXML protected void onSubmitAction(ActionEvent event) throws SQLException, DatabaseConnectionException
	{
		if(verificationFields())
		{
			ArrayList<KeyValuePair<String,?>> value = new ArrayList<KeyValuePair<String,?>>();
			value.add(new KeyValuePair<String,String>("targa",targa_field.getText()));
			value.add(new KeyValuePair<String,String>("modello",model_field.getText()));
			value.add(new KeyValuePair<String,String>("marca",brand_field.getText()));
			value.add(new KeyValuePair<String,String>("km",km_field.getText()));
			value.add(new KeyValuePair<String,String>("fascia",Character.toString(TypeSection.resolvType(fascia_chbox.getValue()))));
			CarsAvailability ca = new CarsAvailability(state_chbox.getValue());
			value.add(new KeyValuePair<String,Integer>("disponibilita",ca.toInt()));
			value.add(new KeyValuePair<String,String>("id_agenzia",SalesManView.session.filiale.getNumber()));
			if(insert(value)==1)
			{
				new GenericDialogView("Transazione eseguita con sussesso.", "Automobile aggiunta con successo.").start();
				targa_field.setText("");
				model_field.setText("");
				brand_field.setText("");
				km_field.setText("");
				state_chbox.getSelectionModel().selectLast();
				fascia_chbox.getSelectionModel().selectLast();
			}
			else
			{
				new SQLWarning();
			}
		}
		else
		{
			new GenericWarning("Tutti i campi devono essere riempiti!",
					"Assicurati di aver riempito tutti i campi correttamente.").start();
		}
	}
	
	@FXML protected void onCancelAction(ActionEvent event) throws IOException
	{
		((BorderPane) rootLayout.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("NothingView.fxml")));
	}
	protected boolean verificationFields()
	{
		if((brand_field.getText().equals(""))||(model_field.getText().equals(""))||(targa_field.getText().equals(""))
				||(km_field.getText().equals(""))||(fascia_chbox.getValue().equals(""))||(state_chbox.getValue().equals("")))
		{
			return false;
		}
		
		return true;
		
	}
	
	public int insert(ArrayList<KeyValuePair<String,?>> values) throws SQLException, DatabaseConnectionException
	{
		DbAccess db = new DbAccess();
		db.initConnection();
		TableAuto ta = new TableAuto(db);
		int result = ta.genericInsertAuto(values);
		return result;
	}

}
