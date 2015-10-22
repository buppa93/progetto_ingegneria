package view;
import entity.Contract;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SummaryRentalView extends Application 
{
	private static SummaryRentalView instance = new SummaryRentalView();
	private Contract contract;
	
	public SummaryRentalView(){}
	
	public static SummaryRentalView getInstance()
	{
		return instance;
	}
	
	public Contract getContract()
	{
		return contract;
	}
	
	public void setContract(Contract c)
	{
		contract = c;
	}
	
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("SummaryRentalView.fxml"));
     
         Scene scene = new Scene(root, 400, 134);
     
         stage.setTitle("Inserisci nuovo chilometraggio.");
         stage.setScene(scene);
         stage.show();
     }
}