import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;


public class FXMLAlertRegisterController {
	@FXML CheckBox registered_chbox;
	@FXML CheckBox notregistered_chbox;
	@FXML Button cancel_bttn;
	@FXML Button submit_bttn;
	
	@FXML protected void confirm(ActionEvent event)
	{
		if(registered_chbox.isSelected()){
			LoginAlertView.runLoginDialog();
		}
	}
}
