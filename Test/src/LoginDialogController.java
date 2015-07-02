import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import utility.MyUtil;
import database.DatabaseConnectionException;
import database.DbAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginDialogController implements Initializable
{
	@FXML private Button submit_bttn;
	@FXML private Button cancel_bttn;
	@FXML private TextField userName_field;
	@FXML private PasswordField pwd_field;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		//oscurano il buttone "ok" se i campi sono vuoti
		userName_field.textProperty().addListener((observable, oldValue, newValue) -> {
			submit_bttn.setDisable(newValue.trim().isEmpty());
		});
		
		pwd_field.textProperty().addListener((observable, oldValue, newValue) -> {
			submit_bttn.setDisable(newValue.trim().isEmpty());
		});
	
	}
	
	@FXML
	protected void loginAction (ActionEvent event) throws DatabaseConnectionException, SQLException
	{
		String usr = userName_field.getText();
		String psswd = pwd_field.getText();
		DbAccess db = new DbAccess();
		db.initConnection();
		if(MyUtil.login(db, usr, psswd))
		{
			//TODO vai al Cerca auto
			System.out.println("login effettuato");
		}
		else
		{
			//TODO mostra messaggio username o password errata
			System.out.println("login non effettuto");
		}
		
		
		
		
	}
}
