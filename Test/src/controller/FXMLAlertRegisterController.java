package controller;
import view.FXMLNewUserView;
import view.LoginDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;


public class FXMLAlertRegisterController 
{
	@FXML CheckBox registered_chbox;
	@FXML CheckBox notregistered_chbox;
	@FXML Button cancel_bttn;
	@FXML Button submit_bttn;
	
	@FXML protected void confirm(ActionEvent event) throws Exception
	{
		if(registered_chbox.isSelected())
		{
			LoginDialog dialog = new LoginDialog();
			dialog.start(new Stage());
			Stage stage = (Stage) submit_bttn.getScene().getWindow();
			stage.close();
		}
		else{
			FXMLNewUserView UserView= new FXMLNewUserView();
			UserView.start(new Stage());
		}
	}
	
	@FXML protected void onCancelEvent(ActionEvent event)
	{
		Stage stage = (Stage) cancel_bttn.getScene().getWindow();
		stage.close();
	}
}
