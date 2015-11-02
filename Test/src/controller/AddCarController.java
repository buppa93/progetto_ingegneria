package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.DAOTableAuto;
import database.DAOTableTypeSection;
import database.DatabaseConnectionException;
import database.DbAccess;
import entity.Auto;
import entity.TypeSection;
import utility.CarsAvailability;
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
			CarsAvailability ca = new CarsAvailability(state_chbox.getValue());
			
			DbAccess db = new DbAccess();
			db.initConnection();
			DAOTableTypeSection tts = new DAOTableTypeSection();
			TypeSection ts = tts.findByName(Character.toString(TypeSection.resolvType(fascia_chbox.getValue())));
			tts.closeConncetion();
			db.closeConnection();
			
			Auto auto = new Auto(targa_field.getText(),model_field.getText(),brand_field.getText(),
					Integer.valueOf(km_field.getText()),SalesManView.session.filiale.getNumber(),ts,
					ca.toInt());
			if(insert(auto)==1)
			{
				new GenericDialogView("Transazione eseguita con sussesso.", "Automobile aggiunta con successo.").start();
				targa_field.setText("");
				model_field.setText("");
				brand_field.setText("");
				km_field.setText("");
				state_chbox.getSelectionModel().selectLast();
				fascia_chbox.getSelectionModel().selectLast();
			}
			else {new SQLWarning();}
		}
		else
		{
			new GenericWarning("Tutti i campi devono essere riempiti!",
					"Assicurati di aver riempito tutti i campi correttamente.").start();
		}
	}
	
	@FXML protected void onCancelAction(ActionEvent event) throws IOException
	{((BorderPane) rootLayout.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("NothingView.fxml")));}
	
	protected boolean verificationFields()
	{
		if((brand_field.getText().equals(""))||(model_field.getText().equals(""))||(targa_field.getText().equals(""))
				||(km_field.getText().equals(""))||(fascia_chbox.getValue().equals(""))||(state_chbox.getValue().equals("")))
		{
			return false;
		}
		
		return true;
		
	}
	
	public int insert(Auto auto) throws SQLException, DatabaseConnectionException
	{
		DbAccess db = new DbAccess();
		db.initConnection();
		DAOTableAuto ta = new DAOTableAuto(db);
		return ta.insert(auto);
	}

}
