import java.sql.ResultSet;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SelectCarView 
{
	private ResultSet car;
	private Map<String, String> parameters;

	SelectCarView(ResultSet car, Map<String, String> parameters)
	{
		this.car = car;
		this.parameters = parameters;
	}

	public void start(Stage stage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource("SelectCarView.fxml"));

		Scene scene = new Scene(root, 600, 400);

		stage.setTitle("Seleziona auto");
		stage.setScene(scene);
		stage.show();
	}
}
