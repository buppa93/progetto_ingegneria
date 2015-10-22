package view;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;


public class FXMLNewClientView extends Application 
{
	
	public void start(Stage stage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource("NewClientView.fxml"));

		Scene scene = new Scene(root, 600, 400);
		ChoiceBox<String> typeUser_Choice = new ChoiceBox<String>();
		typeUser_Choice.getItems().addAll("1","2");
		stage.setTitle("Nuovo Utente");
		stage.setScene(scene);
		stage.show();
	}

}
