package controller;

import java.net.URL;
import java.util.ResourceBundle;

import entity.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public class AdminShowClientsController implements Initializable{
//	@FXML private TreeTableView<Client> Tableview;
//	@FXML private TreeTableColumn<TreeTableView<Client>,String> ClientName_tablecolumn;
//	@FXML private TreeTableColumn<TreeTableView<Client>,String> ClientSurname_tablecolumn;
//	@FXML private TreeTableColumn<TreeTableView<Client>,String> ClientTelephone_tablecolumn;
	
@FXML	TreeTableView<Client> treeTable = new TreeTableView<>();
@FXML	TreeTableColumn<Client,String> ClientName_tablecolumn;
@FXML	TreeTableColumn<Client,String> ClientSurname_tablecolumn;
@FXML	TreeTableColumn<Client,String> ClientTelephone_tablecolumn;
@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	
}
	
}