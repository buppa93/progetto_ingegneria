package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import database.DbAccess;
import database.TableClients;
import view.FXMLNoleggioView;
import view.SalesManView;
import entity.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class FXMLNewClientController implements Initializable
{
	@FXML private TextField name_field;
	@FXML private TextField surname_field;
	@FXML private TextField phone_field;
	@FXML private Button cancel_bttn;
	@FXML private Button submit_bttn;
	@FXML private AnchorPane rootPane;
	
	@FXML protected void submit(ActionEvent event) throws Exception 
	{
		Client c = new Client(name_field.getText(), surname_field.getText(), phone_field.getText());
		
		DbAccess db = new DbAccess();
		db.initConnection();
		TableClients table = new TableClients(db);
		table.insertClient(c);
		
		FXMLNoleggioView.getInstance().setClient(c);
		((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("NoleggioView.fxml")));
	}
	
	@FXML protected void onCancelEvent(ActionEvent event) throws IOException
	{
		((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("NothingView.fxml")));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		submit_bttn.setDisable(true);
		
		//TODO corregere bug
		name_field.textProperty().addListener(new ChangeListener<String>()
				{

					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) 
					{
						if(newValue.equals(""))
							submit_bttn.setDisable(true);
						surname_field.textProperty().addListener(new ChangeListener<String>()
								{

									@Override
									public void changed(
											ObservableValue<? extends String> observable,
											String oldValue, String newValue) 
									{
										if(newValue.equals(""))
											submit_bttn.setDisable(true);
										phone_field.textProperty().addListener(new ChangeListener<String>()
												{

													@Override
													public void changed(
															ObservableValue<? extends String> observable,
															String oldValue,
															String newValue) 
													{
														if((!name_field.getText().equals(""))&&(!surname_field.getText().equals(""))
																&&(!phone_field.getText().equals("")))
															submit_bttn.setDisable(false);
														if(newValue.equals(""))
															submit_bttn.setDisable(true);
														
													}
											
												});
										
									}
							
								});
						
					}
			
				});
		
	}

}
