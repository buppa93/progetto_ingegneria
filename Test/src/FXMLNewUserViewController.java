
import database.DatabaseConnectionException;
import database.DbAccess;
import database.TableClient;
import utility.MyUtil;
import entity.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FXMLNewUserViewController 
{
	@FXML private TextField nameUser_field;
	@FXML private TextField surName_field;
	@FXML private TextField cellNumber_field;
	@FXML private PasswordField pwdUser_field;
	@FXML private ChoiceBox<String> typeUser_Choice;
	
	@FXML protected void submit(ActionEvent event) throws DatabaseConnectionException 
	{
		String type = typeUser_Choice.getValue();
		if(type.equals("Cliente"))
			type = "usr";
		else
			type = "adm";
		
		//TODO aggiungere il metodo di generazione id per user
		String pwd = pwdUser_field.getText();
		pwd = MyUtil.getMD5(pwd);
		String id = TableClient.makeId();
		Client c = new Client(id, nameUser_field.getText(), surName_field.getText(), cellNumber_field.getText(), type, pwd);
		
		DbAccess db = new DbAccess();
		db.initConnection();
		TableClient table = new TableClient(db);
		table.insertClient(c);
	}

}
