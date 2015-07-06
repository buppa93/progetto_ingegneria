package controller;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import view.SelectCarView;
import entity.Auto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;


public class SelectCarViewController implements Initializable
{
	@FXML private ListView<String> listCar_lst;
	@FXML private Button submit_bttn;
	@FXML private Button cancel_bttn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		List<String> carsString = new ArrayList<String>();
		List<Auto> carss = new ArrayList<Auto>();
		carss = SelectCarView.getInstance().getCars();
		
		Iterator<Auto> it = carss.iterator();
		while(it.hasNext())
		{
			carsString.add(it.next().toLabel());
		}
		
		ObservableList<String> cars = FXCollections.observableArrayList(carsString);
		listCar_lst.setItems(cars);
	}
}
