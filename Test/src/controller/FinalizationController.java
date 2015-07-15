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
		//TODO devo gestire l'acconto
		System.out.println("devo gestire l'acconto");
		System.out.println(FinalizationView.getInstance().getAuto().toLabel());
		System.out.println(FinalizationView.getInstance().getClient().toString());
		System.out.println("data inizio: "+FinalizationView.getInstance().getParameters().get("dataStart"));
		System.out.println("durata: "+FinalizationView.getInstance().getParameters().get("during"));
		System.out.println("base: "+FinalizationView.getInstance().getParameters().get("base"));
		System.out.println("agenzia prelievo: "+FinalizationView.getInstance().getParameters().get("agencyTake"));
		System.out.println("agenzia ritorno: "+FinalizationView.getInstance().getParameters().get("agencyReturn"));
		System.out.println("tipo chilometraggio: "+FinalizationView.getInstance().getParameters().get("typeKm"));
		System.out.println("chilometri: "+FinalizationView.getInstance().getParameters().get("km"));
		System.out.println("tipo macchina: "+FinalizationView.getInstance().getParameters().get("typeCar"));
		System.out.println("prezzo: "+FinalizationView.getInstance().getParameters().get("price"));
		System.out.println("id tipo contratto: "+FinalizationView.getInstance().getParameters().get("idTypeContrat"));
		
		
	}
	@FXML protected void onCancelAction(ActionEvent event){}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		System.out.println(FinalizationView.getInstance().getParameters().get("price"));
		
	}

}
