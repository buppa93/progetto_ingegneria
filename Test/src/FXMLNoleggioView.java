import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.DbAccess;
import database.TableAgency;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class FXMLNoleggioView extends Application 
{
	public void start(Stage stage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource(
				"NoleggioView.fxml"));

		Scene scene = new Scene(root, 600, 400);

		stage.setTitle("Noleggio");
		stage.setScene(scene);
		stage.show();
	}
}