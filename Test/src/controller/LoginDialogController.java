package controller;
import java.net.URL;
import java.util.ResourceBundle;

import utility.MyUtil;
import view.SalesManView;
import view.UnregisteredUserWarning;
import database.DbAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginDialogController implements Initializable
{
	@FXML private Button submit_bttn;
	@FXML private Button cancel_bttn;
	@FXML private TextField userName_field;
	@FXML private PasswordField pwd_field;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
	
	}
	
	@FXML
	protected void loginAction (ActionEvent event) throws Exception
	{
		String usr = userName_field.getText();
		String psswd = pwd_field.getText();
		DbAccess db = new DbAccess();
		db.initConnection();
		System.out.println("sono qui");
		if(MyUtil.login(db, usr, psswd))
		{
			System.out.println("sono qui");
			SalesManView salesman = new SalesManView();
			salesman.start(new Stage());
			Stage stage = (Stage) cancel_bttn.getScene().getWindow();
			stage.close();
		
		}
		else
		{
			new UnregisteredUserWarning();
		}
	}
	
	@FXML protected void onCancelEvent(ActionEvent event)
	{
		Stage stage = (Stage) cancel_bttn.getScene().getWindow();
		stage.close();
	}
}
