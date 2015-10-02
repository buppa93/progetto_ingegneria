package controller;

import java.net.URL;
import java.util.ResourceBundle;

import entity.Auto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class AdminShowCarController implements Initializable
{
	@FXML private ListView<String> showcar_list;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Auto auto= new Auto();
		showcar_list=new ListView<>();
		ObservableList<String> items =FXCollections.observableArrayList(auto.toString());
		showcar_list.setItems(items);

	}
	
}
