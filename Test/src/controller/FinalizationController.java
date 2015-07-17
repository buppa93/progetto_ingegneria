package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.DatabaseConnectionException;
import database.DbAccess;
import database.TableAuto;
import database.TableContract;
import entity.Agency;
import utility.MyUtil;
import view.FinalizationView;
import view.SalesManView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class FinalizationController implements Initializable 
{
	@FXML private TextField acconto_field;
	@FXML private ImageView image_view;
	@FXML private Label success_lbl;
	@FXML private AnchorPane rootPane;
	
	@FXML protected void onSubmitAction(ActionEvent event) throws DatabaseConnectionException, SQLException, IOException
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
		
		String acconto = acconto_field.getText();
		
		DbAccess db = new DbAccess();
		db.initConnection();
		TableContract tc = new TableContract(db);
		
		String agenziaPrelievo = Agency.getIdFromString(FinalizationView.getInstance().getParameters().get("agencyTake"));
		String agenziaRitorno = Agency.getIdFromString(FinalizationView.getInstance().getParameters().get("agencyReturn"));
		tc.insert(MyUtil.makeId(), agenziaPrelievo, FinalizationView.getInstance().getClient().getPhone(), FinalizationView.getInstance().getParameters().get("dataStart"), 
				FinalizationView.getInstance().getParameters().get("during"), agenziaRitorno, 
				FinalizationView.getInstance().getParameters().get("idTypeContrat"), 
				FinalizationView.getInstance().getParameters().get("price"), acconto,
				FinalizationView.getInstance().getAuto().getTarga());
		//Devo mettere la macchina "in noleggio" nel databese
		TableAuto ta = new TableAuto(db);
		ta.setInNoleggio(FinalizationView.getInstance().getAuto().getTarga());
		/*Image value = new Image("../img/business_success.png");
		image_view.setImage(value);*/
		
		success_lbl.setText("Transazione eseguita con successo!");
		
		((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("NothingView.fxml")));
		
	}
	
	@FXML protected void onCancelAction(ActionEvent event) throws IOException
	{
		((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("NothingView.fxml")));
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		System.out.println(FinalizationView.getInstance().getParameters().get("price"));
		
	}

}
