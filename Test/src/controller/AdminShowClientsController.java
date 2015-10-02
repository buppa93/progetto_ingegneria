package controller;

import java.net.URL;
import java.util.ResourceBundle;

import entity.Client;
<<<<<<< HEAD
=======
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
>>>>>>> branch 'master' of https://github.com/buppa93/progetto_ingegneria.git
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
<<<<<<< HEAD
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
=======
import javafx.scene.control.ListView;
>>>>>>> branch 'master' of https://github.com/buppa93/progetto_ingegneria.git

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