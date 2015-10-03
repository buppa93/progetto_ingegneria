package controller;
import java.net.URL;
import java.util.ResourceBundle;

import utility.MyUtil;
import view.AdminView;
import view.SalesManView;
import view.UnregisteredUserWarning;
import database.DbAccess;
import database.TableUsers;
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
		TableUsers user=new TableUsers(db);

		if(MyUtil.login(db, usr, psswd))
		{
			System.out.println("Sono: "+user.getTypeUserBynamepass(usr, psswd));
			
			if(user.getTypeUserBynamepass(usr, psswd).equals("adm"))
			{
				System.out.println("sono admin");

				AdminView adminview= new AdminView();
				adminview.start(new Stage());
				Stage stage = (Stage) cancel_bttn.getScene().getWindow();
				stage.close();
			}
			else if(user.getTypeUserBynamepass(usr, psswd).equals("usr"))
			{
				System.out.println("sono user");
				
				SalesManView salesman = new SalesManView();
				salesman.start(new Stage());
				Stage stage = (Stage) cancel_bttn.getScene().getWindow();
				stage.close();
			}
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
