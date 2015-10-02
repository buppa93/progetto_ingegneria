package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import view.AdminView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.MenuBar;

public class FXMLAdminViewController implements Initializable
{
	private static FXMLAdminViewController instance= new FXMLAdminViewController();
	@FXML private BorderPane rootLayout;
	@FXML private MenuBar MenuBar;
	@FXML private Pane adminPane;
	
	@FXML
	private void handleAdminShowClientsAction(final ActionEvent event) throws IOException
	{
		provideAdminShowClientsFunctionality();
	}
	
	/**
	 * Handle action related to "Rimuovi Cliente" menu item.
	 * 
	 * @param event Event on "Rimuovi Cliente" menu item.
	 * @throws IOException 
	 */
	@FXML
	private void handleAdminDeleteClientAction(final ActionEvent event) throws IOException
	{
		provideAdminDeleteClientFunctionality();
	}

	/**
	 * Handle action related to "Visualizza Agenzia" menu item.
	 * 
	 * @param event Event on "Visualizza Agenzia" menu item.
	 * @throws IOException 
	 */
	@FXML
	private void handleAdminShowAgencyAction(final ActionEvent event) throws IOException
	{
		provideAdminShowAgencyFunctionality();
	}
	
	/**
	 * Handle action related to "Cerca Agenzia" menu item.
	 * 
	 * @param event Event on "Cerca Agenzia" menu item.
	 * @throws IOException 
	 */
@FXML
	private void handleAdminSearchAgencyAction(final ActionEvent event) throws IOException
	{
		provideAdminSearchAgencyFunctionality();
	}
	
	/**
	 * Handle action related to "Rimuovi Agenzia" menu item.
	 * 
	 * @param event Event on "Rimuovi Agenzia" menu item.
	 * @throws IOException 
	 */
	@FXML
	private void handleAdminDeleteAgencyAction(final ActionEvent event) throws IOException
	{
		provideAdminDeleteAgencyFunctionality();
	}

	/**
	 * Handle action related to "Aggiungi Agenzia" menu item.
	 * 
	 * @param event Event on "Aggiungi Agenzia" menu item.
	 * @throws IOException 
	 */
@FXML
	private void handleAdminNewAgencyAction(final ActionEvent event) throws IOException
	{
		provideAdminNewAgencyFunctionality();
	}

	/**
	 * Handle action related to "Visualizza Automobili" menu item.
	 * 
	 * @param event Event on "Visualizza Automobili" menu item.
	 * @throws IOException 
	 */
@FXML
	private void handleAdminShowCarAction(final ActionEvent event) throws IOException
	{
		provideAdminShowCarFunctionality();
	}

	/**
	 * Handle action related to "Aggiungi Automobile" menu item.
	 * 
	 * @param event Event on "Aggiungi Automobile" menu item.
	 * @throws IOException 
	 */
@FXML
	private void handleAdminNewCarAction(final ActionEvent event) throws IOException
	{
		provideAdminNewCarFunctionality();
	}

	/**
	 * Handle action related to "Rimuovi Automobile" menu item.
	 * 
	 * @param event Event on "Rimuovi Automobile" menu item.
	 * @throws IOException 
	 */
@FXML
	private void handleAdminDeleteCarAction(final ActionEvent event) throws IOException
	{
		provideAdminDeleteCarFunctionality();
	}

	/**
	 * Handle action related to "Visualizza Contratti" menu item.
	 * 
	 * @param event Event on "Visualizza Contratti" menu item.
	 * @throws IOException 
	 */
@FXML
	private void handleAdminShowContractsAction(final ActionEvent event) throws IOException
	{
		provideAdminShowContractsFunctionality();
	}

/**
 * Handle action related to "Rimuovi Contratti Scaduti" menu item.
 * 
 * @param event Event on "Rimuovi Contratti Scaduti" menu item.
 * @throws IOException 
 */
@FXML
private void handleAdminDeleteExpiredContractsAction(final ActionEvent event) throws IOException
{
	provideAdminDeleteExpiredContractsFunctionality();
}

/**
 * Perform functionality associated with "Rimuovi Contratti Scaduti" menu selection or CTRL-A.
 * @throws IOException 
 */
private void provideAdminDeleteExpiredContractsFunctionality() throws IOException
{
	System.out.println("You clicked on Rimuovi Contratti Scaduti!");
	rootLayout.setCenter(FXMLLoader.load(AdminView.class.getResource(/*"AdminShowClients.fxml"*/)));
	System.out.println("");
}

	/**
	 * Perform functionality associated with "Visualizza Clienti" menu selection or CTRL-A.
	 * @throws IOException 
	 */
	private void provideAdminShowClientsFunctionality() throws IOException
	{
		System.out.println("You clicked on Visualizza clienti!");
		rootLayout.setCenter(FXMLLoader.load(AdminView.class.getResource("AdminShowClients.fxml")));
		System.out.println("");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MenuBar.setFocusTraversable(true);
		
	}
	
	/**
	 * Perform functionality associated with "Rimuovi cliente" menu selection or CTRL-A.
	 * @throws IOException 
	 */
	private void provideAdminDeleteClientFunctionality() throws IOException
	{
		System.out.println("You clicked on Rimuovi Cliente!");
		rootLayout.setCenter(FXMLLoader.load(AdminView.class.getResource("FXMLAdminDeleteClient.fxml")));
		System.out.println("");
	}

	/**
	 * Perform functionality associated with "Visualizza Agenzia" menu selection or CTRL-A.
	 * @throws IOException 
	 */
	private void provideAdminShowAgencyFunctionality() throws IOException
	{
		System.out.println("You clicked on Visualizza Agenzia!");
		rootLayout.setCenter(FXMLLoader.load(AdminView.class.getResource("AdminShowAgency.fxml")));
		System.out.println("");
	}
	
	/**
	 * Perform functionality associated with "Cerca Agenzia" menu selection or CTRL-A.
	 * @throws IOException 
	 */
	private void provideAdminSearchAgencyFunctionality() throws IOException
	{
		System.out.println("You clicked on Cerca Agenzia!");
		rootLayout.setCenter(FXMLLoader.load(AdminView.class.getResource("AdminSearchAgency.fxml")));
		System.out.println("");
	}
	
	/**
	 * Perform functionality associated with "Rimuovi agenzia" menu selection or CTRL-A.
	 * @throws IOException 
	 */
	private void provideAdminDeleteAgencyFunctionality() throws IOException
	{
		System.out.println("You clicked on Rimuovi Agenzia!");
		rootLayout.setCenter(FXMLLoader.load(AdminView.class.getResource("AdminDeleteAgency.fxml")));
		System.out.println("");
	}
	
	/**
	 * Perform functionality associated with "Aggiungi Agenzia" menu selection or CTRL-A.
	 * @throws IOException 
	 */
	private void provideAdminNewAgencyFunctionality() throws IOException
	{
		System.out.println("You clicked on Aggiungi Agenzia!");
		rootLayout.setCenter(FXMLLoader.load(AdminView.class.getResource("AdminNewAgency.fxml")));
		System.out.println("");
	}

	/**
	 * Perform functionality associated with "Visualizza Automobili" menu selection or CTRL-A.
	 * @throws IOException 
	 */
	private void provideAdminShowCarFunctionality() throws IOException
	{
		System.out.println("You clicked on Visualizza automobili!");
		rootLayout.setCenter(FXMLLoader.load(AdminView.class.getResource("AdminShowCar.fxml")));
		System.out.println("");
	}

	/**
	 * Perform functionality associated with "Aggiungi Automobile" menu selection or CTRL-A.
	 * @throws IOException 
	 */
	private void provideAdminNewCarFunctionality() throws IOException
	{
		System.out.println("You clicked on Aggiungi Automobile!");
		rootLayout.setCenter(FXMLLoader.load(AdminView.class.getResource("AdminNewCar.fxml")));
		System.out.println("");
	}
	
	/**
	 * Perform functionality associated with "Rimuovi automobile" menu selection or CTRL-A.
	 * @throws IOException 
	 */
	private void provideAdminDeleteCarFunctionality() throws IOException
	{
		System.out.println("You clicked on Rimuovi Automobile!");
		rootLayout.setCenter(FXMLLoader.load(AdminView.class.getResource("AdminDeleteCar.fxml")));
		System.out.println("");
	}
	
	/**
	 * Perform functionality associated with "Visualizza Contratti" menu selection or CTRL-A.
	 * @throws IOException 
	 */
	private void provideAdminShowContractsFunctionality() throws IOException
	{
		System.out.println("You clicked on Visualizza contratti!");
		rootLayout.setCenter(FXMLLoader.load(AdminView.class.getResource("AdminShowContracts.fxml")));
		System.out.println("");
	}
	
	public final void setRootLayoutView(String fxml) throws IOException
	{
		rootLayout.setCenter(FXMLLoader.load(AdminView.class.getResource(fxml)));
	}

	public static FXMLAdminViewController getInstance()
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