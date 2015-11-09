package controller;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.xml.parsers.ParserConfigurationException;
import utility.MyUtil;
import view.AdminView;
import view.GenericWarning;
import view.SQLWarning;
import view.SalesManView;
import view.UnregisteredUserWarning;
import database.DAOTableUsers;
import database.DatabaseConnectionException;
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
	protected void loginAction (ActionEvent event)
	{
		
		String usr = userName_field.getText();
		String psswd = pwd_field.getText();
		DbAccess db = new DbAccess();
		try 
		{
			db.initConnection();
		} 
		catch (DatabaseConnectionException e) 
		{
			new SQLWarning();
		}
		DAOTableUsers user = null;
		try 
		{
			user = new DAOTableUsers(db);
		} 
		catch (DatabaseConnectionException e) 
		{
			new SQLWarning();
		}

		try 
		{
			if(MyUtil.login(db, usr, psswd))
			{
				User gen = user.searchTypeUserByNamePass(usr, psswd);
				
				if(gen.getType().equals("adm"))
				{
					Stage stage = (Stage) cancel_bttn.getScene().getWindow();
					stage.close();
					new AdminView(gen);
					
				}
				else if(gen.getType().equals("usr"))
				{
					Stage stage = (Stage) cancel_bttn.getScene().getWindow();
					stage.close();
					new SalesManView(gen);
					
				}
			}
			
			else {new UnregisteredUserWarning();}
		} 
		catch (SQLException e) 
		{
			new SQLWarning();
		} 
		catch (ParserConfigurationException e) 
		{
			new GenericWarning("Error", "ParserConfigurationException").start();
		} 
		catch (IOException e) {
			new GenericWarning("Error", "IOException").start();
		} 
		catch (Exception e) 
		{
			new GenericWarning("Error", "GenericException").start();
		}
		try 
		{
			user.closeConncetion();
		} 
		catch (SQLException e) 
		{
			new SQLWarning();
		}
		try 
		{
			db.closeConnection();
		} 
		catch (SQLException e) 
		{
			new SQLWarning();
		}
		
	}
	
	@FXML protected void onCancelEvent(ActionEvent event)
	{
		Stage stage = (Stage) cancel_bttn.getScene().getWindow();
		stage.close();
	}
}