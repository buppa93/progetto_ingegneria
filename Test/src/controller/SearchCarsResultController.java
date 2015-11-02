package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import view.SearchCarsResultView;
import entity.Auto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SearchCarsResultController implements Initializable
{
	@FXML private AnchorPane rootPane;
	@FXML private ListView<String> cars_lst;
	@FXML private Button close_bttn;
	ArrayList<Auto> cars;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		List<String> carsString = new ArrayList<String>();
		cars = new ArrayList<Auto>();
		cars = SearchCarsResultView.getInstance().getSearchResult();
		
		Iterator<Auto> it = cars.iterator();
		while(it.hasNext())
		{carsString.add(it.next().toLabelAllAttribute());}
		
		ObservableList<String> cars_list = FXCollections.observableArrayList(carsString);
		cars_lst.setItems(cars_list);
	}
	
	@FXML protected void onCloseAction(ActionEvent event)
	{
		Stage stage = (Stage) close_bttn.getScene().getWindow();
		stage.close();
	}

}
