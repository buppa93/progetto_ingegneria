import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Pair;
import utility.MyUtil;
import database.DatabaseConnectionException;
import database.DbAccess;
import database.SearchCar;
import database.TableAgency;

public class FXMLNoleggioViewController implements Initializable
{
	@FXML private DatePicker start_cld 	;
	@FXML private DatePicker end_cld;
	@FXML private ChoiceBox<String> taking_chbox;
	@FXML private ChoiceBox<String> return_chbox;
	@FXML private ChoiceBox<String> typeKm_chbox;
	@FXML private TextField km_field;
	@FXML private ChoiceBox<String> typeCar_chbox;
	
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) 
	{
        DbAccess db = new DbAccess();
        
        try 
        {db.initConnection();} 
        catch (DatabaseConnectionException e1) 
        {e1.printStackTrace();}
        
        TableAgency tableagency = new TableAgency(db);
        List<String> agencies = new ArrayList<String>();
        
        try 
        {agencies = tableagency.getAllAgency();} 
        catch (SQLException e) 
        {e.printStackTrace();}
        
        ObservableList<String> agenzie = FXCollections.observableArrayList(agencies);
        taking_chbox.getItems().addAll(agenzie);
        return_chbox.setItems(agenzie);
    }

	@FXML protected void search(ActionEvent event) throws DatabaseConnectionException, SQLException 
	{
		String date_start = start_cld.getValue().toString();
		String date_end = end_cld.getValue().toString();
		String agencyTake = taking_chbox.getValue();
		String agencyReturn = return_chbox.getValue();
		String typeKm = typeKm_chbox.getValue();
		String km = km_field.getText();
		String typeCar = typeCar_chbox.getValue();
		
		SearchCar.getInstance().setDateStart(date_start);
		SearchCar.getInstance().setTakingAgency(agencyTake);
		SearchCar.getInstance().setTypeCar(typeCar);
		
		ResultSet car = SearchCar.getInstance().search();
		
		Map<String, String> parameters = new TreeMap<String, String>();
		parameters.put("dataEnd", date_end);
		parameters.put("agencyReturn",agencyReturn);
		parameters.put("typeKm",typeKm);
		parameters.put("km",km);
		
		SelectCarView selectCar = new SelectCarView(car, parameters);
		try 
		{selectCar.start(new Stage());} 
		catch (Exception e) 
		{e.printStackTrace();}
	}
}

