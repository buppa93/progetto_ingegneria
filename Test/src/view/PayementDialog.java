package view;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PayementDialog extends Application 
{
	private static PayementDialog instance = new PayementDialog();
	private double payoff;
	private String id_contratto;
	private String phone_client;
	private String targa;
	
	public PayementDialog(){}
	
	public static PayementDialog getInstance()
	{
		return instance;
	}
	
	public double getPayoff()
	{
		return this.payoff;
	}
	
	public String getIdContract()
	{
		return this.id_contratto;
	}
	
	public String getPhoneClient()
	{
		return this.phone_client;
	}
	
	public String getTarga()
	{
		return this.targa;
	}
	
	public void setPayoff(double payoff)
	{
		this.payoff = payoff;
	}
	
	public void setIdContract(String idContract)
	{
		this.id_contratto = idContract;
	}
	
	public void setPhoneClient(String phoneClient)
	{
		this.phone_client = phoneClient;
	}
	
	public void setTarga(String targa)
	{
		this.targa = targa;
	}
	
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("PayementDialog.fxml"));
     
         Scene scene = new Scene(root, 400, 134);
     
         stage.setTitle("Saldo");
         stage.setScene(scene);
         stage.show();
     }
}