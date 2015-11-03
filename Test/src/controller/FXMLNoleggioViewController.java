package controller;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.TreeMap;
import view.FXMLNoleggioView;
import view.GenericWarning;
import view.SQLWarning;
import view.SalesManView;
import view.SelectCarView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import database.DAOTableAgency;
import database.DAOTableAuto;
import database.DAOTableTypeSection;
import database.DatabaseConnectionException;
import database.DbAccess;
import entity.Agency;
import entity.Auto;
import entity.TypeSection;

public class FXMLNoleggioViewController implements Initializable
{
	@FXML private DatePicker start_cld;
	@FXML private DatePicker end_cld;
	@FXML private ChoiceBox<String> base_chbox;
	@FXML private ChoiceBox<String> return_chbox;
	@FXML private ChoiceBox<String> take_chbox;
	@FXML private ChoiceBox<String> typeKm_chbox;
	@FXML private ChoiceBox<String> km_chbox;
	@FXML private ChoiceBox<String> typeCar_chbox;
	@FXML private TextField duration_field;
	@FXML private Label base_lbl;
	@FXML private Button cancel_bttn;
	@FXML private AnchorPane rootPane;
	
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) 
	{
        DbAccess db = new DbAccess();
        
        try 
        {db.initConnection();} 
        catch (DatabaseConnectionException e1) {new SQLWarning();}
        
        DAOTableAgency tableagency = null;
		try {
			tableagency = new DAOTableAgency(db);
		} catch (DatabaseConnectionException e1) {
			new SQLWarning();
		}
        ArrayList<Agency> agencies = new ArrayList<Agency>();
        
        try 
        {agencies = tableagency.getAllAgency();} 
        catch (SQLException e) {new SQLWarning();}
        
        ArrayList<String> agenciesString = new ArrayList<String>();
        int size = agencies.size();
        for(int i=0; i<size;i++)
        {
        	agenciesString.add(agencies.get(i).toString());
        }
        ObservableList<String> agenzie = FXCollections.observableArrayList(agenciesString);
        return_chbox.setItems(agenzie);
        take_chbox.setItems(agenzie);
        
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() 
        {
            @Override
            public DateCell call(final DatePicker datePicker) 
            {
              return new DateCell() 
              {
                @Override
                public void updateItem(LocalDate item, boolean empty) 
                {
                  super.updateItem(item, empty);

                  if (item.isBefore(LocalDate.now()))
                  {
                    setDisable(true);
                    setStyle("-fx-background-color: #EEEEEE;");
                  }
                }
              };
            }
          };
          start_cld.setDayCellFactory(dayCellFactory);
        typeKm_chbox.valueProperty().addListener(new ChangeListener<String>()
        		{

					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) 
					{
						if(newValue.equals("Illimitato"))
							km_chbox.setDisable(true);
						if(newValue.equals("Limitato"))
							km_chbox.setDisable(false);
					}
        	
        		});
        base_chbox.valueProperty().addListener(new ChangeListener<String>()
        		{
        			@Override
        			public void changed(ObservableValue<? extends String> observable,
        					String oldValue, String newValue)
        			{
        		            if(newValue.equals("Giornaliero"))
        		            {
        		            	ObservableList<String> kms = FXCollections.observableArrayList("100","200","300");
        		            	km_chbox.setItems(kms);
        		            	base_lbl.setText("Giorni");
        		            }
        		            if(newValue.equals("Settimanale"))
        		            {
        		            	ObservableList<String> kms = FXCollections.observableArrayList("400","500","600");
        		            	km_chbox.setItems(kms);
        		            	base_lbl.setText("Settimane");
        		            }
        				
        			}
        		});
    }

	@FXML protected void search(ActionEvent event) throws DatabaseConnectionException, SQLException 
	{
		String date_start = start_cld.getValue().toString();
		String agencyReturn = return_chbox.getValue();
		String typeKm = typeKm_chbox.getValue().toLowerCase();
		String typeCar = typeCar_chbox.getValue();
		String base = base_chbox.getValue().toLowerCase();
		String during = duration_field.getText();
		String km = km_chbox.getValue();
		String agencyTake= take_chbox.getValue();
		
		List<Auto> car = new ArrayList<Auto>();
		DbAccess db = new DbAccess();
		db.initConnection();
		DAOTableTypeSection tts = new DAOTableTypeSection();
		TypeSection type = tts.findByType(typeCar);
		DAOTableAuto ta = new DAOTableAuto(db);
		car = ta.searchFree(type, date_start);
		
		Map<String, String> parameters = new TreeMap<String, String>();
		parameters.put("dataStart", date_start);
		parameters.put("agencyTake", agencyTake);
		parameters.put("agencyReturn",agencyReturn);
		parameters.put("typeKm",typeKm);
		parameters.put("km",km);
		parameters.put("typeCar",typeCar);
		parameters.put("base",base);
		parameters.put("during",during);
		parameters.put("dataEnd",estimatedEndDate(date_start,base,Integer.parseInt(during)));
		
		SelectCarView.getInstance().setCars(car);
		SelectCarView.getInstance().setParameters(parameters);
		SelectCarView.getInstance().setClient(FXMLNoleggioView.getInstance().getClient());
		tts.closeConncetion();
		ta.closeConncetion();
		db.closeConnection();
		
		try 
		{((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("SelectCarView.fxml")));} 
		catch (Exception e) {new GenericWarning("Attenzione!", "Impossibile aprire la nuova finestra.").start();}
	}
	
	@FXML protected void onCancelAction(ActionEvent event) throws IOException
	{((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("NothingView.fxml")));}
	
	@FXML protected void onBackAction(ActionEvent event) throws IOException
	{((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("InsertClientDataView.fxml")));}
	
	public String estimatedEndDate(String date_start, String base, int n)
	{
		String endDate = "";
		Calendar data = Calendar.getInstance();
		int year = 0;
		int month = 0;
		int day = 0;
		StringTokenizer tokens = new StringTokenizer(date_start,"-");
		year = Integer.parseInt(tokens.nextToken());
		month = Integer.parseInt(tokens.nextToken());
		day = Integer.parseInt(tokens.nextToken());
		data.set(year, month, day);
		
		if(base.equals("settimanale"))
		{
			data.add(Calendar.WEEK_OF_MONTH, n);
		}
		else
		{
			data.add(Calendar.DAY_OF_YEAR, n);
		}
		endDate = data.get(Calendar.YEAR)+"-"+data.get(Calendar.MONTH)+"-"+data.get(Calendar.DAY_OF_MONTH);
		return endDate;
	}
}

