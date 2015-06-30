import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import utility.MyUtil;
import database.DatabaseConnectionException;
import database.DbAccess;

public class FXMLNoleggioViewController 
{
	@FXML private DatePicker start_cld 	;
	@FXML private DatePicker end_cld;
	@FXML private ChoiceBox<String> taking_chbox;
	@FXML private ChoiceBox<String> return_chbox;
	@FXML private ChoiceBox<String> typeKm_chbox;
	@FXML private TextField km_field;
	@FXML private ChoiceBox<String> typeCar_chbox;

	@FXML protected void search(ActionEvent event) throws DatabaseConnectionException 
	{
		String date_start = start_cld.getValue().toString();
		String date_end = end_cld.getValue().toString();
		String agencyTake = taking_chbox.getValue();
		String agencyReturn = return_chbox.getValue();
		String typeKm = typeKm_chbox.getValue();
		String km = km_field.getText();
		String typeCar = typeCar_chbox.getValue();
	}
}

