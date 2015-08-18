package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import view.SearchClientResultView;
import view.SelectCarView;
import entity.Auto;
import entity.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SearchClientResultController implements Initializable
{
	@FXML private AnchorPane rootPane;
	@FXML private ListView<String> client_lst;
	@FXML private Button close_bttn;
	ArrayList<Client> clients;
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		List<String> clientString = new ArrayList<String>();
		clients = new ArrayList<Client>();
		clients = SearchClientResultView.getInstance().getSearchResult();
		
		Iterator<Client> it = clients.iterator();
		while(it.hasNext())
		{
			clientString.add(it.next().toLabel());
		}
		
		ObservableList<String> clients_list = FXCollections.observableArrayList(clientString);
		client_lst.setItems(clients_list);
	}
	
	@FXML protected void onCloseAction(ActionEvent event)
	{
		Stage stage = (Stage) close_bttn.getScene().getWindow();
		stage.close();
	}

}
