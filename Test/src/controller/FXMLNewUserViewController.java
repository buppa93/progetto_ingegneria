package controller;
import database.DbAccess;
import database.TableClients;
import database.TableUsers;
import utility.MyUtil;
import view.FXMLNoleggioView;
import entity.Client;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLNewUserViewController 
{
	@FXML private TextField name_field;
	@FXML private TextField surname_field;
	@FXML private TextField phone_field;
	@FXML private Button cancel_bttn;
	@FXML private Button submit_bttn;
	@FXML private AnchorPane rootLayout;
	
	@FXML protected void submit(ActionEvent event) throws Exception 
	{
		Client c = new Client(name_field.getText(), surname_field.getText(), phone_field.getText());
		
		DbAccess db = new DbAccess();
		db.initConnection();
		TableClients table = new TableClients(db);
		table.insertClient(c);
		
		//TODO passare il cliente a noleggioView
		//TODO aprire noleggio view
		/*FXMLNoleggioView view = new FXMLNoleggioView();
		view.start(new Stage());
		
		Stage stage = (Stage) submit_bttn.getScene().getWindow();
		stage.close();*/
	}
	
	@FXML protected void onCancelEvent(ActionEvent event)
	{
		Stage stage = (Stage) cancel_bttn.getScene().getWindow();
		stage.close();
	}

}
