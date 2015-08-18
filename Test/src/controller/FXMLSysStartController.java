package controller;

import java.net.URL;
import java.util.ResourceBundle;

import view.LoginDialog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FXMLSysStartController implements Initializable
{
	private @FXML ImageView image_view;
	private @FXML Label label;

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		/*try 
		{
 			Thread.sleep(1000);
 		} 
		catch (InterruptedException e) 
		{
 			e.printStackTrace();
 		}*/
		LoginDialog login = new LoginDialog();
		try 
		{
			login.start(new Stage());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		//Stage stage = (Stage) label.getScene().getWindow();
		//stage.close();
		
		
	}

}
