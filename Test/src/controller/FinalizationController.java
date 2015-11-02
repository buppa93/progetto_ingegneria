package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import database.DAOTableAuto;
import database.DAOTableContract;
import database.DatabaseConnectionException;
import database.DbAccess;
import utility.CarsAvailability;
import utility.PDFFunctionality;
import entity.Agency;
import entity.Contract;
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
		String acconto = acconto_field.getText();
		DbAccess db = new DbAccess();
		
		DAOTableContract tc = new DAOTableContract(db);
		String idContratto = MyUtil.makeId();
		Map<String, String> param = FinalizationView.getInstance().getParameters();
		param.put("idContract", idContratto);
		param.put("acconto", acconto);
		FinalizationView.getInstance().setParameters(param);
		
		String agenziaPrelievo = Agency.getIdFromString(FinalizationView.getInstance().getParameters().get("agencyTake"));
		String agenziaRitorno = Agency.getIdFromString(FinalizationView.getInstance().getParameters().get("agencyReturn"));
		Contract nuovo = new Contract(idContratto,agenziaPrelievo,FinalizationView.getInstance().getClient().getPhone(),
				FinalizationView.getInstance().getParameters().get("dataStart"),
				Integer.parseInt(FinalizationView.getInstance().getParameters().get("during")),
				FinalizationView.getInstance().getParameters().get("dataEnd"),agenziaRitorno,
				FinalizationView.getInstance().getAuto().getTarga(),
				FinalizationView.getInstance().getParameters().get("idTypeContrat"),
				Double.parseDouble(acconto),
				Double.parseDouble(FinalizationView.getInstance().getParameters().get("price"))
	);
		tc.insert(nuovo);
		tc.closeConncetion();
		DAOTableAuto ta = new DAOTableAuto(db);
		ta.updateState(CarsAvailability.toInt("noleggio"), FinalizationView.getInstance().getAuto().getTarga());
		ta.closeConncetion();
		db.closeConnection();
		/*Image value = new Image("../img/business_success.png");
		image_view.setImage(value);*/
		PDFFunctionality pdf = new PDFFunctionality();
		pdf.creaPrestampato(pdf.crateFileUrl());
		success_lbl.setText("Transazione eseguita con successo!");
		
		((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("NothingView.fxml")));
		
	}
	
	@FXML protected void onCancelAction(ActionEvent event) throws IOException
	{((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("NothingView.fxml")));}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {}
}
