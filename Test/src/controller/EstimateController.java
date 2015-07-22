package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import database.DatabaseConnectionException;
import database.DbAccess;
import database.TableTypeContract;
import entity.TypeSection;
import view.EstimateView;
import view.FinalizationView;
import view.SQLWarning;
import view.SalesManView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class EstimateController implements Initializable
{
	@FXML private Label name_lbl;
	@FXML private Label surname_lbl;
	@FXML private Label phone_lbl;
	@FXML private Label start_lbl;
	@FXML private Label end_lbl;
	@FXML private Label take_lbl;
	@FXML private Label return_lbl;
	@FXML private Label typeKm_lbl;
	@FXML private Label km_lbl;
	@FXML private Label typeCar_lbl;
	@FXML private Label targa_lbl;
	@FXML private Label model_lbl;
	@FXML private Label brand_lbl;
	@FXML private Label kmCar_lbl;
	@FXML private Label price_lbl;
	@FXML private Label during_lbl;
	@FXML private Label base_lbl;
	@FXML private Button cancel_bttn;
	@FXML private Button back_bttn;
	@FXML private Button submit_bttn;
	@FXML private AnchorPane rootPane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		name_lbl.setText(EstimateView.getInstance().getClient().getName());
		surname_lbl.setText(EstimateView.getInstance().getClient().getSurname());
		phone_lbl.setText(EstimateView.getInstance().getClient().getPhone());
		targa_lbl.setText(EstimateView.getInstance().getAuto().getTarga());
		model_lbl.setText(EstimateView.getInstance().getAuto().getModel());
		brand_lbl.setText(EstimateView.getInstance().getAuto().getBrand());
		kmCar_lbl.setText(Integer.toString(EstimateView.getInstance().getAuto().getKm()));
		start_lbl.setText(EstimateView.getInstance().getParameters().get("dataStart"));
		during_lbl.setText(EstimateView.getInstance().getParameters().get("during"));
		base_lbl.setText(EstimateView.getInstance().getParameters().get("base"));
		StringTokenizer token = new StringTokenizer(EstimateView.getInstance().getParameters().get("agencyTake"), ", ");
		token.nextToken();
		take_lbl.setText(token.nextToken());
		token = new StringTokenizer(EstimateView.getInstance().getParameters().get("agencyReturn"), ", ");
		token.nextToken();
		return_lbl.setText(token.nextToken());
		typeKm_lbl.setText(EstimateView.getInstance().getParameters().get("typeKm"));
		km_lbl.setText(EstimateView.getInstance().getParameters().get("km"));
		typeCar_lbl.setText(EstimateView.getInstance().getParameters().get("typeCar"));
		
		char fascia = TypeSection.resolvType(EstimateView.getInstance().getParameters().get("typeCar"));
		
		DbAccess db = new DbAccess();
		try {
			db.initConnection();
		} catch (DatabaseConnectionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		TableTypeContract table = new TableTypeContract(db);
		String tContract = "";
		try 
		{
			tContract = table.getTypeContract(EstimateView.getInstance().getParameters().get("base"), 
					EstimateView.getInstance().getParameters().get("typeKm"), 
					TypeSection.resolvType(EstimateView.getInstance().getParameters().get("typeCar")),
					EstimateView.getInstance().getParameters().get("km"));
			System.out.println(tContract);
		} 
		catch (SQLException e) 
		{
			SQLWarning warning = new SQLWarning();
		}
		
		double unit = table.getPrice(tContract);
		String price = Double.toString(quote(unit, Integer.parseInt(EstimateView.getInstance().getParameters().get("during"))));
		price_lbl.setText(price);
		
		System.out.println(table.getId(tContract));
		EstimateView.getInstance().getParameters().put("price", price);
		String idContract = table.getId(tContract);
		EstimateView.getInstance().getParameters().put("idTypeContrat", idContract);
		
	}
	
	@FXML protected void onSubmitAction(ActionEvent event) throws IOException
	{
		FinalizationView.getInstance().setAuto(EstimateView.getInstance().getAuto());
		FinalizationView.getInstance().setClient(EstimateView.getInstance().getClient());
		FinalizationView.getInstance().setParameters(EstimateView.getInstance().getParameters());
		((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("FinalizationView.fxml")));
	}
	
	@FXML protected void onBackAction(ActionEvent event){}
	
	@FXML protected void onCancelAction(ActionEvent event){}
	
	/**
	 * Calcola il preventivo
	 * @return
	 * @throws DatabaseConnectionException 
	 * @throws SQLException 
	 */
	public double quote(double unit, int n)
	{
		double cost = 0.0;
		cost = unit*n;
		return cost;
	}
	
	

}
