package view;
import java.util.List;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import entity.Auto;

public final class SelectCarView 
{
	private List<Auto> cars; 
	private Map<String, String> parameters; //Parametri della finestra NoleggioView. Vedi NoleggioViewController.search()
	private static SelectCarView instance =  new SelectCarView();
	
	public SelectCarView(){}

	public void start(Stage stage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource("SelectCarView.fxml"));

		Scene scene = new Scene(root, 600, 400);

		stage.setTitle("Seleziona auto");
		stage.setScene(scene);
		stage.show();
	}
	
	public static SelectCarView getInstance() {return instance;};
	
	public void setCars(List<Auto> cars)
	{
		this.cars = cars;
		System.out.println("Macchine: "+this.cars.size());
	}
	
	public void setParameters(Map<String, String> parameters)
	{
		this.parameters = parameters;
	}
	
	public List<Auto> getCars()
	{
		return this.cars;
	}
	
	public Map<String, String> getParameters()
	{
		return this.parameters;
	}
}
