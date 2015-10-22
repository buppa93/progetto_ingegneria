package view;
import entity.Client;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public final class FXMLNoleggioView extends Application 
{
	private Client client;
	private static FXMLNoleggioView istance = new FXMLNoleggioView();
	@FXML private DatePicker start_cld;
	@FXML private DatePicker end_cld;
	@FXML private ChoiceBox<String> base_chbox;
	@FXML private ChoiceBox<String> return_chbox;
	@FXML private ChoiceBox<String> typeKm_chbox;
	@FXML private ChoiceBox<String> km_chbox;
	@FXML private ChoiceBox<String> typeCar_chbox;
	@FXML private Button cancel_bttn;
	@FXML private AnchorPane rootPane;
	
	public FXMLNoleggioView() {}
	
	public void start(Stage stage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource(
				"NoleggioView.fxml"));

		Scene scene = new Scene(root, 600, 400);
		
		System.out.println("base_chbox: ");
		System.out.println(base_chbox);
		

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