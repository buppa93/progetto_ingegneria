package controller;

import java.net.URL;
import java.util.ResourceBundle;

import view.FinalizationView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class FinalizationController implements Initializable {
	
	@FXML protected void onSubmitAction(ActionEvent event)
	{
		
	}
	@FXML protected void onCancelAction(ActionEvent event){}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		System.out.println(FinalizationView.getInstance().getParameters().get("price"));
		
	}

}
