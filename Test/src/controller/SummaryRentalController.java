package controller;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import view.KmInvalidWarning;
import view.PayementDialog;
import view.SQLWarning;
import view.SummaryRentalView;
import view.SalesManView;
import database.CustomSQLException;
import database.DAOTableAuto;
import database.DAOTableTypeContract;
import database.DatabaseConnectionException;
import database.DbAccess;
import entity.Auto;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class SummaryRentalController implements Initializable
{
	@FXML private AnchorPane rootPane;
	@FXML private Button submit_bttn;
	@FXML private Button cancel_bttn;
	@FXML private TextField newKm_field;
	@FXML private Label kmCar_lbl;
	@FXML private Label typeNoleggio_lbl;
	@FXML private Label typeKm_lbl;
	@FXML private Label during_lbl;
	@FXML private Label kmPrevent_lbl;
	@FXML private Label pricePrevent_lbl;
	@FXML private Label deposit_lbl;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		//oscurano il buttone "ok" se i campi sono vuoti
		submit_bttn.setDisable(true);
		newKm_field.textProperty().addListener(new ChangeListener<String>()
        		{

					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) 
					{
						if(newValue.equals(""))
							submit_bttn.setDisable(true);
						else
							submit_bttn.setDisable(false);
					}
        	
        		});
		
		String targa = SummaryRentalView.getInstance().getContract().getTarga();
		
		DbAccess db = new DbAccess();
		
		try 
		{db.initConnection();} 
		catch (DatabaseConnectionException e) 
		{new SQLWarning();}
		
		Auto auto = null;
		DAOTableAuto ta = null;
		try 
		{ta = new DAOTableAuto(db);} 
		catch (DatabaseConnectionException e1) 
		{new SQLWarning();}
		
		try 
		{auto = ta.selectByTarga(targa);} 
		catch (SQLException e) {new SQLWarning();} 
		catch (DatabaseConnectionException e) {new SQLWarning();} 
		
		DAOTableTypeContract tc = null;
		
		try 
		{tc = new DAOTableTypeContract(db);} 
		catch (DatabaseConnectionException e1) {new SQLWarning();}
		
		String typeContract = "";
		String typeKm = "";
		
		int kmPrev = 0;
		
		try 
		{
			typeContract = tc.getTypeContractNameById(SummaryRentalView.getInstance().getContract().getTypeContract());
			typeKm = tc.getTypeKmById(SummaryRentalView.getInstance().getContract().getTypeContract());
			kmPrev = tc.getKmById(SummaryRentalView.getInstance().getContract().getTypeContract());
		} 
		catch (SQLException e) {new SQLWarning();}
		
		kmCar_lbl.setText(Integer.toString(auto.getKm()));
		typeNoleggio_lbl.setText(typeContract);
		typeKm_lbl.setText(typeKm);
		during_lbl.setText(String.valueOf(SummaryRentalView.getInstance().getContract().getDuration()));
		kmPrevent_lbl.setText(String.valueOf(kmPrev));
		pricePrevent_lbl.setText(String.valueOf(SummaryRentalView.getInstance().getContract().getPrice()));
		deposit_lbl.setText(String.valueOf(SummaryRentalView.getInstance().getContract().getDeposit()));
	}
	
	@FXML
	protected void onSubmitAction (ActionEvent event) throws Exception
	{
		int kmPre = 0;
		int kmPost = 0;
		DbAccess db = new DbAccess();
		db.initConnection();
		
		//ricavo la targa dell'auto
		DAOTableAuto ta = new DAOTableAuto(db);
		Auto auto = ta.selectByTarga(SummaryRentalView.getInstance().getContract().getTarga());
		
		//ricavo i km precedenti al noleggio
		kmPre = auto.getKm();
		
		//ricavo i km fatti dal cliente
		kmPost = Integer.parseInt((newKm_field.getText()));
		
		if(kmPost <= kmPre)
		{
			new KmInvalidWarning();
			((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("SummaryRentalView.fxml")));
		}
		else
		{
			//ricavo l'id del tipo di contratto
			String idTypeContract = SummaryRentalView.getInstance().getContract().getTypeContract();
			DAOTableTypeContract tc = new DAOTableTypeContract(db);
		
			//setto i nuovi km dell'auto
			ta.updateKm(Integer.parseInt(newKm_field.getText()), auto.getTarga());
		
			//setto l'auto di nuovo a disponibile
			ta.updateState(1, auto.getTarga());
		
			auto.setIdAgenzia(SummaryRentalView.getInstance().getContract().getAgencyReturn());
			ta.updateAgency(auto.getIdAgenzia(), auto.getTarga());
		
			//calcolo la differenza se il chilometraggio settato == "limitato"
			double difference = 0.0;
			if(tc.getTypeKmById(idTypeContract).equalsIgnoreCase("limitato"))
			{
				double pricePerKm = tc.getPricePerKmById(idTypeContract);
				int kmContr = tc.getKmById(idTypeContract);
				System.out.println("kmPost: "+kmPost);
				System.out.println("pricePerKm: "+pricePerKm);
				System.out.println("kmPre: "+kmPre);
				System.out.println("kmContr: "+kmContr);
				System.out.println("pricePrevent: "+pricePrevent_lbl.getText());
				System.out.println("deposit: "+deposit_lbl.getText());
				difference = getDifference(pricePerKm, estimateKm(kmPost, kmPre, kmContr)) + 
					(Double.parseDouble(pricePrevent_lbl.getText()) - Double.parseDouble(deposit_lbl.getText()));
				System.out.println("Differenza: "+ difference);
			
				//Setto i parametri perla finestra di pagamento
				setPayementParameters(difference);
			
				//lancio la finestra di pagamento
				PayementDialog.getInstance().start(new Stage());
			}
			else
			{
				difference = Double.parseDouble(pricePrevent_lbl.getText()) - Double.parseDouble(deposit_lbl.getText());
				setPayementParameters(difference);
				PayementDialog.getInstance().start(new Stage());
			}
		
			((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("NothingView.fxml")));
		}
	}
	
	@FXML protected void onCancelAction(ActionEvent event) throws IOException
	{((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("NothingView.fxml")));}
	
	public void setPayementParameters(double price)
	{
		PayementDialog.getInstance().setPayoff(price);
		PayementDialog.getInstance().setIdContract(SummaryRentalView.getInstance().getContract().getNumeroOrdine());
		PayementDialog.getInstance().setPhoneClient(SummaryRentalView.getInstance().getContract().getClientId());
		PayementDialog.getInstance().setTarga(SummaryRentalView.getInstance().getContract().getTarga());
	}
	
	public double getDifference(double pricePerKm, int km)
	{return km*pricePerKm;}
	
	public int estimateKm(int kmPost, int kmPre, int kmContr)
	{return kmPost-kmPre-kmContr;}
}
