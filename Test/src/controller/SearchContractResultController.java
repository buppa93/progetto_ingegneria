package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import view.SearchClientResultView;
import view.SearchContractResultView;
import view.SelectCarView;
import entity.Auto;
import entity.Client;
import entity.Contract;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SearchContractResultController implements Initializable
{
	@FXML private AnchorPane rootPane;
	@FXML private ListView<String> contract_lst;
	@FXML private Button close_bttn;
	ArrayList<Contract> contracts;
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		List<String> contractString = new ArrayList<String>();
		contracts = new ArrayList<Contract>();
		contracts = SearchContractResultView.getInstance().getSearchResult();
		
		Iterator<Contract> it = contracts.iterator();
		while(it.hasNext())
		{
			contractString.add(it.next().toLabel());
		}
		
		ObservableList<String> contract_list = FXCollections.observableArrayList(contractString);
		contract_lst.setItems(contract_list);
	}
	
	@FXML protected void onCloseAction(ActionEvent event)
	{
		Stage stage = (Stage) close_bttn.getScene().getWindow();
		stage.close();
	}

}
