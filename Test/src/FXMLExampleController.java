import java.sql.SQLException;
import java.util.Optional;
import utility.MyUtil;
import database.DatabaseConnectionException;
import database.DbAccess;
import database.TableClient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import javafx.stage.Stage;

public class FXMLExampleController 
{
	@FXML private Label actiontarget;

	@FXML protected void loginUser(ActionEvent event) throws DatabaseConnectionException 
	{
		actiontarget.setText("login utente");
		Optional<Pair<String, String>> opt;
		opt = LoginAlertView.runLoginDialog();
		Pair<String, String> pair = opt.get();
		try
		{
			DbAccess db =  new DbAccess();
			db.initConnection();
			System.out.println(db.toString());
			String name = pair.getKey();
			String pwd = pair.getValue();
			boolean login = MyUtil.login(db,name,pwd);
			System.out.println(login);
			if(login)
			{
				actiontarget.setText("login user ok");
			}
		}
		catch(SQLException e){}
	}

	@FXML protected void loginAdmin(ActionEvent event) 
	{
		actiontarget.setText("login admin");
	}
	
	@FXML protected void newUser(ActionEvent event) throws Exception 
	{
		actiontarget.setText("nuovo utente");
		//Stage stage = new Stage();
		FXMLNewUserView newuserview =  new FXMLNewUserView();
		newuserview.start(new Stage());
	}
	
	@FXML protected void noleggia(ActionEvent event) throws Exception 
	{
		actiontarget.setText("noleggia");
		//Stage stage = new Stage();
		LoginDialog dialog =  new LoginDialog();
		dialog.start(new Stage());
	}

}