package controller;
import database.DbAccess;
import database.TableUsers;
import utility.MyUtil;
import view.FXMLNoleggioView;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLNewUserViewController 
{
	@FXML private TextField nameUser_field;
	@FXML private TextField surName_field;
	@FXML private TextField cellNumber_field;
	@FXML private PasswordField pwdUser_field;
	@FXML private ChoiceBox<String> typeUser_Choice;
	@FXML private Button cancel_bttn;
	@FXML private Button submit_bttn;
	
	@FXML protected void submit(ActionEvent event) throws Exception 
	{
		String type = typeUser_Choice.getValue();
		if(type.equals("Cliente"))
			type = "usr";
		else
			type = "adm";
		
		String pwd = pwdUser_field.getText();
		pwd = MyUtil.getMD5(pwd);
		String id = TableUsers.makeId();
		User c = new User(id, nameUser_field.getText(), surName_field.getText(), cellNumber_field.getText(), type, pwd);
		
		DbAccess db = new DbAccess();
		db.initConnection();
		TableUsers table = new TableUsers(db);
		table.insertClient(c);
		
		FXMLNoleggioView view = new FXMLNoleggioView();
		view.start(new Stage());
		
		Stage stage = (Stage) submit_bttn.getScene().getWindow();
		stage.close();
	}
	
	@FXML protected void onCancelEvent(ActionEvent event)
	{
		Stage stage = (Stage) cancel_bttn.getScene().getWindow();
		stage.close();
	}

}
