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
		opt = runLoginDialog();
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

	public Optional<Pair<String, String>> runLoginDialog()
	{
		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Login Dialog");
		dialog.setHeaderText("Look, a Custom Login Dialog");

		// Set the icon (must be included in the project).
		//dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField username = new TextField();
		username.setPromptText("Username");
		PasswordField password = new PasswordField();
		password.setPromptText("Password");

		grid.add(new Label("Username:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("Password:"), 0, 1);
		grid.add(password, 1, 1);

		// Enable/Disable login button depending on whether a username was entered.
		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		username.textProperty().addListener((observable, oldValue, newValue) -> {
			loginButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		Platform.runLater(() -> username.requestFocus());

		// Convert the result to a username-password-pair when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				return new Pair<>(username.getText(), password.getText());
			}
			return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(usernamePassword -> {
			System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
		});
		
		return result;
	}

}