package view;

import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import entity.Contract;

public final class SearchContractResultView 
{
	private static SearchContractResultView instance = new SearchContractResultView();
	private ArrayList<Contract> searchResult;
	
	public static SearchContractResultView getInstance()
	{
		return instance;
	}
	
	public ArrayList<Contract> getSearchResult()
	{
		return searchResult;
	}
	
	public void setSearchResult(ArrayList<Contract> list)
	{
		searchResult = list;
	}
	
	public void start(Stage stage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource("SearchContractResultView.fxml"));

		Scene scene = new Scene(root, 600, 400);

		//stage.setTitle("Seleziona auto");
		stage.setScene(scene);
		stage.show();
	}

}
