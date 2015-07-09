package controller;

import java.net.URL;
import java.util.ResourceBundle;

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
		
	}
	
	@FXML protected void onSubmitAction(ActionEvent event){}
	
	@FXML protected void onBackAction(ActionEvent event){}
	
	@FXML protected void onCancelAction(ActionEvent event){}

}
