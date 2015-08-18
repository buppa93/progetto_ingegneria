package view;

import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import entity.Client;

public final class SearchClientResultView 
{
	private static SearchClientResultView instance = new SearchClientResultView();
	private ArrayList<Client> searchResult;
	
	public static SearchClientResultView getInstance()
	{
		return instance;
	}
	
	public ArrayList<Client> getSearchResult()
	{
		return searchResult;
	}
	
	public void setSearchResult(ArrayList<Client> list)
	{
		searchResult = list;
	}
	
	public void start(Stage stage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource("SearchClientResultView.fxml"));

		Scene scene = new Scene(root, 600, 400);

		//stage.setTitle("Seleziona auto");
		stage.setScene(scene);
		stage.show();
	}

}
