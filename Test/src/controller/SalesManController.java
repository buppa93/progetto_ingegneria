package controller;

import java.io.IOException;
import java.util.ResourceBundle;

import view.SalesManView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class SalesManController implements Initializable
{
	@FXML private MenuBar menuBar;
	@FXML private BorderPane rootLayout;
	@FXML private Pane work_pane;
	
	public void initRootLayout() 
	{
        try 
        {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SalesManView.class.getResource("SalesManView.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            //Scene scene = new Scene(rootLayout);
           // primaryStage.setScene(scene);
            //primaryStage.show();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

	/**
	 * Handle action related to "Nuovo CLiente" menu item.
	 * 
	 * @param event Event on "Nuovo Cliente" menu item.
	 * @throws IOException 
	 */
	@FXML
	private void handleNewClientAction(final ActionEvent event) throws IOException
	{
		provideNewClientFunctionality();
	}

	/**
	 * Perform functionality associated with "About" menu selection or CTRL-A.
	 * @throws IOException 
	 */
	private void provideNewClientFunctionality() throws IOException
	{
		System.out.println("You clicked on Nuovo Cliente!");
		//work_pane.getChildren().addAll(FXMLLoader.load(SalesManView.class.getResource("LoginDialog.fxml")));
		//rootLayout.getChildren().addAll(FXMLLoader.load(SalesManView.class.getResource("LoginDialog.fxml")));
		rootLayout.setCenter(FXMLLoader.load(SalesManView.class.getResource("NewUserView.fxml")));
		System.out.println("");
	}


	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) 
	{
		menuBar.setFocusTraversable(true);

	}

	public void setVista(Node node) {
		// TODO Auto-generated method stub
		work_pane.getChildren().setAll(node);
		
	}   
}