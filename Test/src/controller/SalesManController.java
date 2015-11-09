package controller;

import java.io.IOException;
import java.util.ResourceBundle;

import view.GenericWarning;
import view.LoginDialog;
import view.SalesManView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public final class SalesManController implements Initializable
{
	private static SalesManController instance = new SalesManController();
	@FXML private MenuBar menuBar;
	@FXML private BorderPane rootLayout;
	@FXML private Pane work_pane;
	@FXML private AnchorPane mainPane;

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
	
	@FXML private void handleCloseAction(final ActionEvent event)
	{
		provideCloseFunctionality();
	}
	
	@FXML private void handleAddCarAction(final ActionEvent event) throws IOException
	{
		provideAddCarFunctionality();
	}
	
	@FXML private void handleRemoveCarAction(final ActionEvent event) throws IOException
	{
		provideRemoveCarFunctionality();
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
	 * Handle action related to "Cerca CLiente" menu item.
	 * 
	 * @param event Event on "Cerca Cliente" menu item.
	 * @throws IOException 
	 */
	@FXML
	private void handleRemoveClientAction(final ActionEvent event) throws IOException
	{
		provideRemoveClientFunctionality();
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
	 * Handle action related to "Rimuovi contratto" menu item.
	 * 
	 * @param event Event on "Rimuovi contratto" menu item.
	 * @throws IOException 
	 */
	@FXML
	private void handleRemoveContractAction(final ActionEvent event) throws IOException
	{
		provideRemoveContractFunctionality();
	}
	
	@FXML
	private void handleSearchContractAction(final ActionEvent event) throws IOException
	{
		provideSearchContractFunctionality();
	}

	private void provideSearchContractFunctionality() throws IOException 
	{
		rootLayout.setCenter(FXMLLoader.load(SalesManView.class.getResource("SearchContractView.fxml")));
	}

	/**
	 * Perform functionality associated with "About" menu selection or CTRL-A.
	 * @throws IOException 
	 */
	private void provideNewClientFunctionality() throws IOException
	{
		rootLayout.setCenter(FXMLLoader.load(SalesManView.class.getResource("NewClientView.fxml")));
	}
	
	private void provideRemoveClientFunctionality() throws IOException 
	{
		rootLayout.setCenter(FXMLLoader.load(SalesManView.class.getResource("RemoveClientView.fxml")));
		
	}
	private void provideCloseFunctionality() 
	{
		Stage stage = (Stage) mainPane.getScene().getWindow();
		stage.close();
	}
	
	private void provideAddCarFunctionality() throws IOException 
	{
		rootLayout.setCenter(FXMLLoader.load(SalesManView.class.getResource("AddCarView.fxml")));
	}
	private void provideRemoveCarFunctionality() throws IOException 
	{
		rootLayout.setCenter(FXMLLoader.load(SalesManView.class.getResource("RemoveCarView.fxml")));
	}
	/**
	 * Perform functionality associated with "About" menu selection or CTRL-A.
	 * @throws IOException 
	 */
	private void provideSearchClientFunctionality() throws IOException
	{
		rootLayout.setCenter(FXMLLoader.load(SalesManView.class.getResource("SearchClientView.fxml")));
	}

	/**
	 * Perform functionality associated with "Nuova auto" menu selection or CTRL-A.
	 * @throws IOException 
	 */
	private void provideSearchCarFunctionality() throws IOException
	{
		rootLayout.setCenter(FXMLLoader.load(SalesManView.class.getResource("SearchAutoView.fxml")));
	}
	
	/**
	 * Perform functionality associated with "Nuova auto" menu selection or CTRL-A.
	 * @throws IOException 
	 */
	private void provideNewContractFunctionality() throws IOException
	{
		rootLayout.setCenter(FXMLLoader.load(SalesManView.class.getResource("FXMLAlertRegister.fxml")));
	}
	
	/**
	 * Perform functionality associated with "Nuova auto" menu selection or CTRL-A.
	 * @throws IOException 
	 */
	private void provideRemoveContractFunctionality() throws IOException
	{
		rootLayout.setCenter(FXMLLoader.load(SalesManView.class.getResource("RemoveContractView.fxml")));
	}
	
	public final void setRootLayoutView(String fxml) throws IOException
	{
		rootLayout.setCenter(FXMLLoader.load(SalesManView.class.getResource(fxml)));
	}

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) 
	{
		menuBar.setFocusTraversable(true);
		/*if(SalesManView.session.validateUsr())
		{}
		else
		{
			new GenericWarning("Attenzione", "Non puoi loggarti in questa agenzia.").start();
			//try {
			LoginDialog login = new LoginDialog();
				try {
					login.start(new Stage());
				} catch (IOException e) {
					//TODO vedere perche' da eccezione
					e.printStackTrace();
					System.out.println("here");
					new GenericWarning("Errore","GenericException").start();
				}*/
			//} catch (Exception e) {
				
			//}
			//Stage stage = (Stage) mainPane.getScene().getWindow();
			//stage.close();
				
		

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