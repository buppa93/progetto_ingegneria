package view;

import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import entity.Auto;

public final class SearchCarsResultView 
{
	private static SearchCarsResultView instance = new SearchCarsResultView();
	private ArrayList<Auto> searchResult;
	
	public static SearchCarsResultView getInstance()
	{
		return instance;
	}
	
	public ArrayList<Auto> getSearchResult()
	{
		return searchResult;
	}
	
	public void setSearchResult(ArrayList<Auto> list)
	{
		searchResult = list;
	}
	
	public void start(Stage stage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource("SearchCarsResultView.fxml"));

		Scene scene = new Scene(root, 600, 400);

		//stage.setTitle("Seleziona auto");
		stage.setScene(scene);
		stage.show();
	}

}
