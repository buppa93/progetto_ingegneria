package controller;

import java.net.URL;
import java.util.ResourceBundle;
import entity.Agency;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class AdminShowAgencyController implements Initializable
{
	@FXML private ListView<String> showagency_list;
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		Agency agency= new Agency();
		showagency_list= new ListView<>();
		ObservableList<String> items =FXCollections.observableArrayList(agency.toString());
		showagency_list.setItems(items);	
	}
}
