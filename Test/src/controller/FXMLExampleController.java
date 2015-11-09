package controller;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Optional;

import utility.MyUtil;
import view.FXMLAlertRegister;
import view.FXMLNewClientView;
import view.LoginAlertView;
import database.DatabaseConnectionException;
import database.DbAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Pair;
import javafx.stage.Stage;

public class FXMLExampleController 
{
	@FXML private Label actiontarget;

	@FXML protected void loginUser(ActionEvent event) throws DatabaseConnectionException, NoSuchAlgorithmException 
	{
		actiontarget.setText("login utente");
		Optional<Pair<String, String>> opt;
		opt = LoginAlertView.runLoginDialog();
		Pair<String, String> pair = opt.get();
		try
		{
			DbAccess db =  new DbAccess();
			db.initConnection();
			String name = pair.getKey();
			String pwd = pair.getValue();
			boolean login = MyUtil.login(db,name,pwd);
			if(login)
			{
				actiontarget.setText("login user ok");
			}
		}
		catch(SQLException e){new SQLException();}
	}

	@FXML protected void loginAdmin(ActionEvent event) 
	{
		actiontarget.setText("login admin");
	}
	
	@FXML protected void newUser(ActionEvent event) throws Exception 
	{
		actiontarget.setText("nuovo utente");
		//Stage stage = new Stage();
		FXMLNewClientView newuserview =  new FXMLNewClientView();
		newuserview.start(new Stage());
	}
	
	@FXML protected void noleggia(ActionEvent event) throws Exception 
	{
		actiontarget.setText("noleggia");
		//Stage stage = new Stage();
		FXMLAlertRegister controller =  new FXMLAlertRegister();
		controller.start(new Stage());
	}

}