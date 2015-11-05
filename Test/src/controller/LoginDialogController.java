package controller;
import java.net.URL;
import java.util.ResourceBundle;

import utility.MyUtil;
import view.AdminView;
import view.SalesManView;
import view.UnregisteredUserWarning;
import database.DAOTableUsers;
import database.DbAccess;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * 
 * @author giuseppe
 *abcc
 */
public class LoginDialogController implements Initializable
{
	@FXML private Button submit_bttn;
	@FXML private Button cancel_bttn;
	@FXML private TextField userName_field;
	@FXML private PasswordField pwd_field;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {}
	
	@FXML
	protected void loginAction (ActionEvent event) throws Exception
	{
		
		String usr = userName_field.getText();
		String psswd = pwd_field.getText();
		DbAccess db = new DbAccess();
		db.initConnection();
		DAOTableUsers user = new DAOTableUsers(db);

		if(MyUtil.login(db, usr, psswd))
		{
			User gen = user.searchTypeUserByNamePass(usr, psswd);
			
			if(gen.getType().equals("adm"))
			{
				//new AdminView(gen).start(new Stage());
				AdminView adminview= new AdminView(gen);
				adminview.start(new Stage());
				Stage stage = (Stage) cancel_bttn.getScene().getWindow();
				stage.close();
			}
			else if(gen.getType().equals("usr"))
			{
				new SalesManView(gen).start(new Stage());
				Stage stage = (Stage) cancel_bttn.getScene().getWindow();
				stage.close();
			}
		}
		
		else {new UnregisteredUserWarning();}
		user.closeConncetion();
		db.closeConnection();
	}
	
	@FXML protected void onCancelEvent(ActionEvent event)
	{
		Stage stage = (Stage) cancel_bttn.getScene().getWindow();
		stage.close();
	}
}