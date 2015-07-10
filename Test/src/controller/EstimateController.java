package controller;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import entity.TypeSection;
import utility.MyUtil;
import view.EstimateView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

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
		end_lbl.setText(EstimateView.getInstance().getParameters().get("dataEnd"));
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
		//TODO calcolare il prezzo
		int days = 0;
		try {
			days = MyUtil.estimatedNumberOfDays(EstimateView.getInstance().getParameters().get("dataStart"),EstimateView.getInstance().getParameters().get("dataEnd"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Numero giorni: "+days);
		double price = quote(fascia, EstimateView.getInstance().getParameters().get("typeKm"), days);
	}
	
	@FXML protected void onSubmitAction(ActionEvent event){}
	
	@FXML protected void onBackAction(ActionEvent event){}
	
	@FXML protected void onCancelAction(ActionEvent event){}
	
	/**
	 * Calcola il preventivo
	 * @return
	 */
	public double quote(char facsia, String tipo_km, int n_giorni)
	{
		double price = 0.0;
		return price;
	}

}
