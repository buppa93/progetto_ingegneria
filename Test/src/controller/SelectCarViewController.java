package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import view.SalesManView;
import view.SelectCarView;
import entity.Auto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class SelectCarViewController implements Initializable
{
	@FXML private ListView<String> listCar_lst;
	@FXML private Button submit_bttn;
	@FXML private Button cancel_bttn;
	@FXML private AnchorPane rootPane;
	
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
	
	@FXML protected void onSubmitAction(ActionEvent event) throws IOException
	{
		//TODO passare la macchina selezionata al prossimo frame
		((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("EstimateView.fxml")));
	}
	
	@FXML protected void onCancelAction(ActionEvent event) throws IOException
	{
		((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("NothingView.fxml")));
	}
	
	@FXML protected void onBackAction(ActionEvent event) throws IOException
	{
		((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("NoleggioView.fxml")));
	}
}
