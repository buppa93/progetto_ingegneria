package controller;

import java.io.IOException;
import java.util.ResourceBundle;

import view.SalesManView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public final class SalesManController implements Initializable
{
	private static SalesManController instance = new SalesManController();
	@FXML private MenuBar menuBar;
	@FXML private BorderPane rootLayout;
	@FXML private Pane work_pane;

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
	 * Handle action related to "Cerca CLiente" menu item.
	 * 
	 * @param event Event on "Cerca Cliente" menu item.
	 * @throws IOException 
	 */
	@FXML
	private void handleSearchClientAction(final ActionEvent event) throws IOException
	{
		provideSearchClientFunctionality();
	}

	/**
	 * Handle action related to "Nuova auto" menu item.
	 * 
	 * @param event Event on "Nuova auto" menu item.
	 * @throws IOException 
	 */
	@FXML
	private void handleSearchCarAction(final ActionEvent event) throws IOException
	{
		provideSearchCarFunctionality();
	}
	
	/**
	 * Handle action related to "Nuovo contratto" menu item.
	 * 
	 * @param event Event on "Nuovo contratto" menu item.
	 * @throws IOException 
	 */
	@FXML
	private void handleNewContractAction(final ActionEvent event) throws IOException
	{
		provideNewContractFunctionality();
	}

	/**
	 * Perform functionality associated with "About" menu selection or CTRL-A.
	 * @throws IOException 
	 */
	private void provideNewClientFunctionality() throws IOException
	{
		rootLayout.setCenter(FXMLLoader.load(SalesManView.class.getResource("NewUserView.fxml")));
		System.out.println("");
	}
	
	/**
	 * Perform functionality associated with "About" menu selection or CTRL-A.
	 * @throws IOException 
	 */
	private void provideSearchClientFunctionality() throws IOException
	{
		rootLayout.setCenter(FXMLLoader.load(SalesManView.class.getResource("SearchClientView.fxml")));
		System.out.println("");
	}

	/**
	 * Perform functionality associated with "Nuova auto" menu selection or CTRL-A.
	 * @throws IOException 
	 */
	private void provideSearchCarFunctionality() throws IOException
	{
		rootLayout.setCenter(FXMLLoader.load(SalesManView.class.getResource("SearchAutoView.fxml")));
		System.out.println("");
	}
	
	/**
	 * Perform functionality associated with "Nuova auto" menu selection or CTRL-A.
	 * @throws IOException 
	 */
	private void provideNewContractFunctionality() throws IOException
	{
		rootLayout.setCenter(FXMLLoader.load(SalesManView.class.getResource("FXMLAlertRegister.fxml")));
		System.out.println("");
	}
	
	public final void setRootLayoutView(String fxml) throws IOException
	{
		rootLayout.setCenter(FXMLLoader.load(SalesManView.class.getResource(fxml)));
	}

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) 
	{
		menuBar.setFocusTraversable(true);

	}
	
	public static SalesManController getInstance()
	{
		return instance;
	}
	
	public BorderPane getRootLayout()
	{
		return rootLayout;
	}
	
	public void setRootLayout(BorderPane rootLayout)
	{
		this.rootLayout = rootLayout;
	}
}