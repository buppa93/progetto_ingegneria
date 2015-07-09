package view;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import entity.Auto;
import entity.Client;

public final class EstimateView 
{
	private Client client;
	private Auto auto;
	private Map<String, String> parameters;
	private static EstimateView instance =  new EstimateView();
	
	public EstimateView(){}

	public void start(Stage stage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource("SelectCarView.fxml"));

		Scene scene = new Scene(root, 600, 400);

		stage.setTitle("Seleziona auto");
		stage.setScene(scene);
		stage.show();
	}
	
	public static EstimateView getInstance() {return instance;};
	
	public void setClient(Client client)
	{
		this.client = client;
	}
	
	public void setAuto(Auto auto)
	{
		this.auto = auto;
	}
	
	public void setParameters(Map<String, String> parameters)
	{
		this.parameters = parameters;
	}
	
	public Client getClient()
	{
		return this.client;
	}
	
	public Auto getAuto()
	{
		return this.auto;
	}
	
	public Map<String, String> getParameters()
	{
		return this.parameters;
	}
}
