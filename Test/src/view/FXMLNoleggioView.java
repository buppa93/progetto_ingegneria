package view;
import entity.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class FXMLNoleggioView extends Application 
{
	private Client client;
	private static FXMLNoleggioView istance = new FXMLNoleggioView();
	
	public FXMLNoleggioView() {}
	
	public void start(Stage stage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource(
				"NoleggioView.fxml"));

		Scene scene = new Scene(root, 600, 400);

		stage.setTitle("Noleggio");
		stage.setScene(scene);
		stage.show();
	}
	
	public static FXMLNoleggioView getInstance() {return istance;}
	
	public void setClient(Client client)
	{
		this.client = client;
	}
	
	public Client getClient()
	{
		return this.client;
	}
}