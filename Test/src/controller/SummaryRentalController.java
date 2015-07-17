package controller;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import utility.MyUtil;
import view.SummaryRentalView;
import view.SalesManView;
import view.UnregisteredUserWarning;
import database.DatabaseConnectionException;
import database.DbAccess;
import database.TableAuto;
import database.TableTypeContract;
import entity.Auto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
		newKm_field.textProperty().addListener((observable, oldValue, newValue) -> {
			submit_bttn.setDisable(newValue.trim().isEmpty());
		});
		
		String targa = SummaryRentalView.getInstance().getContract().getTarga();
		DbAccess db = new DbAccess();
		try 
		{
			db.initConnection();
		} 
		catch (DatabaseConnectionException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Auto auto = null;
		TableAuto ta = new TableAuto(db);
		
		try 
		{
			auto = ta.searchAutoByTarga(targa);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TableTypeContract tc = new TableTypeContract(db);
		String typeContract = "";
		String typeKm = "";
		
		int kmPrev = 0;
		
		try {
			typeContract = tc.getTypeContractNameById(SummaryRentalView.getInstance().getContract().getTypeContract());
			typeKm = tc.getTypeKmById(SummaryRentalView.getInstance().getContract().getTypeContract());
			kmPrev = tc.getKmById(SummaryRentalView.getInstance().getContract().getTypeContract());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kmCar_lbl.setText(Integer.toString(auto.getKm()));
		typeNoleggio_lbl.setText(typeContract);
		typeKm_lbl.setText(typeKm);
		during_lbl.setText(String.valueOf(SummaryRentalView.getInstance().getContract().getDuration()));
		kmPrevent_lbl.setText(String.valueOf(kmPrev));
		pricePrevent_lbl.setText(String.valueOf(SummaryRentalView.getInstance().getContract().getPrice()));
		deposit_lbl.setText(String.valueOf(SummaryRentalView.getInstance().getContract().getDeposit()));
	
	}
	
	@FXML
	protected void loginAction (ActionEvent event) throws Exception
	{
		DbAccess db = new DbAccess();
		db.initConnection();
		TableAuto ta = new TableAuto(db);
		ta.setKm(SummaryRentalView.getInstance().getContract().getTarga(), Integer.parseInt(newKm_field.getText()));
		
		/*String usr = userName_field.getText();
		String psswd = pwd_field.getText();
		DbAccess db = new DbAccess();
		db.initConnection();
		System.out.println("sono qui");
		if(MyUtil.login(db, usr, psswd))
		{
			System.out.println("sono qui");
			SalesManView salesman = new SalesManView();
			salesman.start(new Stage());
			Stage stage = (Stage) cancel_bttn.getScene().getWindow();
			stage.close();
		
		}
		else
		{
			UnregisteredUserWarning alert = new UnregisteredUserWarning();
		}*/
	}
	
	@FXML protected void onCancelEvent(ActionEvent event)
	{
		Stage stage = (Stage) cancel_bttn.getScene().getWindow();
		stage.close();
	}
}
