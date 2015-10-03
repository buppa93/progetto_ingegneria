package controller;

import java.net.URL;
import java.util.ResourceBundle;

import entity.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.ListView;

public class AdminShowClientsController implements Initializable
{
	@FXML private ListView<String> showClients_list;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Client client= new Client();
		showClients_list = new ListView<>();		
		ObservableList<String> items =FXCollections.observableArrayList(client.toString());
		showClients_list.setItems(items);
	}
	
}