package controller;


import view.SummaryRentalView;
import view.SalesManView;
import database.DbAccess;
import database.TableContract;
import entity.Contract;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class RemoveContractController 
{
	@FXML private AnchorPane rootPane;
	@FXML private TextField phoneClient_field;
	@FXML private TextField targa_field;
	
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
}
