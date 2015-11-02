package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.DAOTableContract;
import database.DatabaseConnectionException;
import database.DbAccess;
import view.PayementDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PayementDialogController implements Initializable
{
	@FXML private Label price_lbl;
	@FXML private Button cancel_bttn;

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{price_lbl.setText(String.valueOf(PayementDialog.getInstance().getPayoff()));}
	
	@FXML protected void onSubmitAction(ActionEvent event) throws DatabaseConnectionException, SQLException
	{
		//rimuovo il contratto
		DbAccess db = new DbAccess();
		db.initConnection();
		/*TableContract tc = new TableContract(db);
		tc.remove(PayementDialog.getInstance().getIdContract());*/
		DAOTableContract tc = new DAOTableContract(db);
		tc.remove(PayementDialog.getInstance().getIdContract());
		
		tc.closeConncetion();
		db.closeConnection();
		
		Stage stage = (Stage) cancel_bttn.getScene().getWindow();
		stage.close();
		
	}
	
	@FXML protected void onCancelAction(ActionEvent event)
	{
		
	}

}
